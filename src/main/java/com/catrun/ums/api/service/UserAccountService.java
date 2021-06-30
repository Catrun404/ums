package com.catrun.ums.api.service;

import com.catrun.ums.domain.Role;
import com.catrun.ums.domain.UserAccount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserAccountService {

    Page<UserAccount> findAll(Role role, String username, Pageable pageable);

    UserAccount findById(Long id);

    boolean isUserExist(String username);

    UserAccount createUserAccount(UserAccount userAccount);

    UserAccount updateUserAccount(UserAccount userAccount);

    Optional<UserAccount> findByUsername(String username);
}
