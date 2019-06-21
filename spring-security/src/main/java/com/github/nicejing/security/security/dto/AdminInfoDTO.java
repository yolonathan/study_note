package com.github.nicejing.security.security.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Nathan
 */
@Data
public class AdminInfoDTO implements Serializable {

    private static final long   serialVersionUID = 6461748715058322615L;

    private String              id;

    private String              username;

    private String              email;

    private List<String>        roles;

    private String              token;

    private List<PermissionDTO> menus;

    private List<PermissionDTO> buttons;

    private List<PermissionDTO> uris;

}
