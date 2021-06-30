package com.catrun.ums.security;

import com.catrun.ums.domain.Status;
import com.catrun.ums.domain.UserAccount;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SecurityUserFactory {

    private static final String ROLE_PREFIX = "ROLE_";

    public static SecurityUser create(UserAccount user) {
        return new SecurityUser(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                getAuthorities(user),
                user.getStatus().equals(Status.ACTIVE));
    }

    private static List<GrantedAuthority> getAuthorities(UserAccount user) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(ROLE_PREFIX + user.getRole().name()));
        return authorities;
    }
}
