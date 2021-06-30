package com.catrun.ums.api.repository;

import com.catrun.ums.domain.Role;
import com.catrun.ums.domain.UserAccount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {

    boolean existsUserAccountByUsername(String username);

    Optional<UserAccount> findByUsername(String username);

    Page<UserAccount> findByRoleAndUsernameLike(Role role, String username, Pageable pageable);

    Page<UserAccount> findByRole(Role role, Pageable pageable);

    Page<UserAccount> findByUsernameLike(String username, Pageable pageable);
}
