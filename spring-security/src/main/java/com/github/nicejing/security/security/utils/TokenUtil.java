package com.github.nicejing.security.security.utils;

import com.alibaba.fastjson.JSONObject;

import com.github.nicejing.security.security.dto.AdminInfoDTO;
import com.github.nicejing.security.security.support.AdminSecurityProperties;
import com.github.nicejing.security.utils.SpringContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.SignatureVerifier;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author Nathan
 */
@Slf4j
public class TokenUtil {

    public static AdminInfoDTO getUserInfoFormToken(String token) {

        if (StringUtils.isBlank(token)) {
            return null;
        }

        SignatureVerifier verifier = SpringContextUtil.getBean(SignatureVerifier.class);
        try {
            Jwt jwt = JwtHelper.decodeAndVerify(token, verifier);
            String adminId = getAdminId(jwt);
            StringRedisTemplate redisTemplate = SpringContextUtil.getBean(StringRedisTemplate.class);
            AdminSecurityProperties securityProperties = SpringContextUtil.getBean(AdminSecurityProperties.class);
            String key = securityProperties.getAdminRedisNameSpace() + adminId;
            String adminInfoString = redisTemplate.opsForValue().get(key);
            if (adminInfoString != null) {
                return JSONObject.parseObject(adminInfoString, AdminInfoDTO.class);
            }

        } catch (Exception e) {
            // log.warn(LogUtil.build("验证jwt异常", e.getMessage()));
        }

        return null;
    }

    public static void refreshToken(String id) {

        if (StringUtils.isBlank(id)) {
            return;
        }
        AdminSecurityProperties securityProperties = SpringContextUtil.getBean(AdminSecurityProperties.class);
        String key = securityProperties.getAdminRedisNameSpace() + id;
        StringRedisTemplate redisTemplate = SpringContextUtil.getBean(StringRedisTemplate.class);
        redisTemplate.expire(key, securityProperties.getAdminTokenExpire(), TimeUnit.MINUTES);

    }

    public static void saveToken(AdminInfoDTO adminInfoDTO) {
        AdminSecurityProperties securityProperties = SpringContextUtil.getBean(AdminSecurityProperties.class);
        String key = securityProperties.getAdminRedisNameSpace() + adminInfoDTO.getId();
        StringRedisTemplate redisTemplate = SpringContextUtil.getBean(StringRedisTemplate.class);
        // 删除之前登录的token
        redisTemplate.delete(key);
        // 把用户的登录信息存redis
        redisTemplate.opsForValue().set(key, JSONObject.toJSONString(adminInfoDTO),
            securityProperties.getAdminTokenExpire(), TimeUnit.MINUTES);

    }

    public static String createClaims(String id) {
        JSONObject claims = new JSONObject();
        claims.put("id", id);
        claims.put("login_time", LocalDateTime.now().toString());
        claims.put("salt", UUID.randomUUID().toString());
        return claims.toJSONString();
    }

    private static String getAdminId(Jwt jwt) {
        String claims = jwt.getClaims();
        JSONObject jsonObject = JSONObject.parseObject(claims);
        return jsonObject.getString("id");
    }

    public static void clean(String id) {

        AdminSecurityProperties securityProperties = SpringContextUtil.getBean(AdminSecurityProperties.class);
        String key = securityProperties.getAdminRedisNameSpace() + id;
        StringRedisTemplate redisTemplate = SpringContextUtil.getBean(StringRedisTemplate.class);
        redisTemplate.delete(key);

    }

}
