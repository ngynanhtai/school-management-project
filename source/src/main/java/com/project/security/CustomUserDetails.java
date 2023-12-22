package com.project.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.dto.common.UserDTO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
public class CustomUserDetails implements UserDetails {
    private Long id;
    private String userName;
    @JsonIgnore
    private String password;
    private String fullName;
    private String userCode;
    private Collection<? extends GrantedAuthority> role;

    public CustomUserDetails(Long id, String userName, String password, String fullName, String userCode, Collection<? extends GrantedAuthority> role) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.fullName = fullName;
        this.userCode = userCode;
        this.role = role;
    }

    public static CustomUserDetails build(UserDTO user) {
        List<GrantedAuthority> roles = new ArrayList<>(Collections.singleton(new SimpleGrantedAuthority(user.getRoleType())));
        return new CustomUserDetails(
                user.getId(),
                user.getEmail(),
                user.getPassword(),
                user.getFullName(),
                user.getUserCode(),
                roles
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
