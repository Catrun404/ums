package com.catrun.ums.service;

import com.catrun.ums.api.repository.UserAccountRepository;
import com.catrun.ums.api.service.UserAccountService;
import com.catrun.ums.domain.Role;
import com.catrun.ums.domain.UserAccount;
import com.catrun.ums.service.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static java.lang.String.format;

@Slf4j
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
        log.trace("Method 'findAll' started.");
        if (role != null && username != null && !username.isEmpty()) {
            String search = "%" + username + "%";
            return userAccountRepository.findByRoleAndUsernameLike(role, search, pageable);
        }
        if (username != null && !username.isEmpty()) {
            String search = "%" + username + "%";
            return userAccountRepository.findByUsernameLike(search, pageable);
        }
        if (role != null) {
            return userAccountRepository.findByRole(role, pageable);
        }
        return userAccountRepository.findAll(pageable);
    }

    @Override
    public UserAccount findById(Long id) {
        log.trace("Method 'findById' started with id={}.", id);
        return userAccountRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(format("No user with id %d", id)));
    }

    @Override
    public boolean isUserExist(String username) {
        log.trace("Method 'isUserExist' started.");
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
        log.trace("Method 'updateUserAccount' started.");
        UserAccount userAccountFromDb = userAccountRepository.findById(userAccount.getId())
                .orElseThrow(() -> new NotFoundException(format("No user with id %d", userAccount.getId())));
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
        log.trace("Method 'findByUsername' started with username='{}'.", username);
        return userAccountRepository.findByUsername(username);
    }
}
