package com.github.nicejing.security.security.support;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.Signer;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.github.nicejing.security.biz.AdminBiz;
import com.github.nicejing.security.biz.ElementBiz;
import com.github.nicejing.security.biz.GroupBiz;
import com.github.nicejing.security.biz.RoleBiz;
import com.github.nicejing.security.entity.Admin;
import com.github.nicejing.security.entity.Element;
import com.github.nicejing.security.entity.Group;
import com.github.nicejing.security.entity.Role;
import com.github.nicejing.security.security.dto.AdminInfoDTO;
import com.github.nicejing.security.security.dto.PermissionDTO;
import com.github.nicejing.security.security.enums.ResourceType;
import com.github.nicejing.security.security.utils.TokenUtil;
import com.github.nicejing.security.utils.ResultBean;

/**
 * 登录成功处理
 * @author Nathan
 */
@Component
public class AdminAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private Signer                  signer;
    @Autowired
    private AdminBiz                adminBiz;
    @Autowired
    private RoleBiz                 roleBiz;
    @Autowired
    private GroupBiz                groupBiz;
    @Autowired
    private ElementBiz              elementBiz;
    @Autowired
    private AdminSecurityProperties securityProperties;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws ServletException, IOException {
        String id = AdminSecurityContext.getContext().get("id");

        AdminInfoDTO info = getAdminInfo(id);
        List<String> roles = info.getRoles();

        // 重新设置用户信息
        List<GrantedAuthority> authorities = AuthorityUtils
            .commaSeparatedStringToAuthorityList(StringUtils.join(roles, ","));
        UsernamePasswordAuthenticationToken passwordAuthenticationToken = new UsernamePasswordAuthenticationToken(info,
            null, authorities);
        SecurityContextHolder.getContext().setAuthentication(passwordAuthenticationToken);
        response.getWriter().write(JSONObject.toJSONString(ResultBean.success(info)));
    }

    private AdminInfoDTO getAdminInfo(String adminId) {

        AdminInfoDTO result = new AdminInfoDTO();
        Admin admin = adminBiz.findById(adminId);
        result.setId(admin.getId());
        result.setUsername(admin.getUsername());
        result.setEmail(admin.getEmail());

        // 用户所有的角色
        List<Role> roles = roleBiz.findByAdmin(admin.getId());
        boolean superAdminRole = false;
        // 是否包含超级挂管理员角色
        if (roles.size() > 0) {
            superAdminRole = roles.stream()
                .anyMatch(role -> role.getRoleCode().equalsIgnoreCase(securityProperties.getSuperAdminRoleName()));
            if (superAdminRole) {
                roles = roleBiz.findAll();
            }

        }

        List<String> roleCodeList = new ArrayList<>();
        List<String> roleIds = null;
        if (roles.size() > 0) {
            roleCodeList = roles.stream().map(Role::getRoleCode).collect(Collectors.toList());
            roleIds = roles.stream().map(Role::getId).collect(Collectors.toList());

        }
        result.setRoles(roleCodeList);

        // 生成jwt token
        String claims = TokenUtil.createClaims(admin.getId());
        Jwt jwt = JwtHelper.encode(claims, signer);
        result.setToken(jwt.getEncoded());

        // 获取元素
        List<Element> elementList;
        if (superAdminRole) {
            elementList = handleSuperAdmin();
        } else {
            elementList = handleNotSuperAdmin(adminId, roleIds);
        }

        // 菜单
        List<PermissionDTO> menus = getElement(elementList, ResourceType.MENU.getName());
        result.setMenus(menus);

        // 按钮
        List<PermissionDTO> buttons = getElement(elementList, ResourceType.BUTTON.getName());
        result.setButtons(buttons);

        // uri
        List<PermissionDTO> uris = getElement(elementList, ResourceType.URI.getName());
        result.setUris(uris);

        return result;

    }

    private List<PermissionDTO> getElement(List<Element> elementList, String type) {

        List<Element> menuElements = elementList.stream().filter(element -> element.getType().equalsIgnoreCase(type))
            .collect(Collectors.toList());
        List<PermissionDTO> result = new ArrayList<>();
        for (Element element : menuElements) {
            PermissionDTO dto = new PermissionDTO();
            dto.setCode(element.getCode());
            dto.setName(element.getName());
            dto.setType(type);
            if (ResourceType.URI.getName().equals(type)) {
                dto.setUri(element.getUri());
                dto.setMethod(element.getMethod());
            }
            result.add(dto);
        }
        return result;

    }

    private List<Element> handleSuperAdmin() {
        return elementBiz.findAll();
    }

    private List<Element> handleNotSuperAdmin(String adminId, List<String> roleIds) {
        List<Group> groups = groupBiz.findByAdmin(adminId);
        List<String> groupIds = null;
        if (groups.size() > 0) {
            groupIds = groups.stream().map(Group::getId).collect(Collectors.toList());
            return elementBiz.findAllByAdmin(adminId, roleIds, groupIds);
        }
        return Collections.emptyList();
    }
}
