package com.catrun.ums.service;

import com.catrun.ums.api.repository.UserAccountRepository;
import com.catrun.ums.api.service.UserAccountService;
import com.catrun.ums.domain.Role;
import com.catrun.ums.domain.UserAccount;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserAccountServiceImpl implements UserAccountService {

    private final UserAccountRepository userAccountRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setPasswordEncoder(@Lazy PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Page<UserAccount> findAll(Role role, String username, Pageable pageable) {
        if (role != null && username != null && !username.isEmpty()) {
            return userAccountRepository.findByRoleAndUsernameLike(role, username, pageable);
        }
        if (username != null && !username.isEmpty()) {
            return userAccountRepository.findByUsernameLike(username, pageable);
        }
        if (role != null) {
            return userAccountRepository.findByRole(role, pageable);
        }
        return userAccountRepository.findAll(pageable);
    }

    @Override
    public UserAccount findById(Long id) {
        return userAccountRepository.findById(id).orElseThrow();
    }

    @Override
    public boolean isUserExist(String username) {
        return userAccountRepository.existsUserAccountByUsername(username);
    }

    @Override
    public UserAccount createUserAccount(UserAccount userAccount) {
        userAccount.setPassword(passwordEncoder.encode(userAccount.getPassword()));
        return userAccountRepository.save(userAccount);
    }

    @Transactional
    @Override
    public UserAccount updateUserAccount(UserAccount userAccount) {
        UserAccount userAccountFromDb = userAccountRepository.findById(userAccount.getId()).orElseThrow();
        if (userAccount.getUsername() != null) {
            userAccountFromDb.setUsername(userAccount.getUsername());
        }
        if (userAccount.getPassword() != null) {
            userAccountFromDb.setPassword(passwordEncoder.encode(userAccount.getPassword()));
        }
        if (userAccount.getFirstName() != null) {
            userAccountFromDb.setFirstName(userAccount.getFirstName());
        }
        if (userAccount.getLastName() != null) {
            userAccountFromDb.setLastName(userAccount.getLastName());
        }
        if (userAccount.getRole() != null) {
            userAccountFromDb.setRole(userAccount.getRole());
        }
        if (userAccount.getStatus() != null) {
            userAccountFromDb.setStatus(userAccount.getStatus());
        }
        return userAccountFromDb;
    }

    @Override
    public Optional<UserAccount> findByUsername(String username) {
        return userAccountRepository.findByUsername(username);
    }
}
