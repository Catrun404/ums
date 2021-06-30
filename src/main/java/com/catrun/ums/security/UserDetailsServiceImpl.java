package com.catrun.ums.security;

import com.catrun.ums.api.service.UserAccountService;
import com.catrun.ums.domain.UserAccount;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserAccountService userAccountService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAccount user = userAccountService.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(format("User %s not found! ", username)));
        return SecurityUserFactory.create(user);
    }
}
