package com.schurov.ssu.web.data.model;

import org.springframework.security.core.GrantedAuthority;

import java.util.List;

public class RoleWeb implements GrantedAuthority {
    private final String auth = "USER";
    public static final List<GrantedAuthority> grands = List.of(new RoleWeb());
    @Override
    public String getAuthority() {
        return auth;
    }
}
