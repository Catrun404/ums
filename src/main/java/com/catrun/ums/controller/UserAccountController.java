package com.catrun.ums.controller;

import com.catrun.ums.api.service.UserAccountService;
import com.catrun.ums.domain.Role;
import com.catrun.ums.domain.UserAccount;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class UserAccountController {
    private final UserAccountService userAccountService;

    @PreAuthorize("hasAnyRole({'ROLE_ADMIN', 'ROLE_USER'})")
    @GetMapping
    public String getUsers(@RequestParam(required = false, defaultValue = "") Role role,
                            @RequestParam(required = false, defaultValue = "") String username,
                            @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable,
                            Model model) {
        Page<UserAccount> page = userAccountService.findAll(role, username, pageable);
        model.addAttribute("page", page);
        model.addAttribute("role", role);
        model.addAttribute("username", username);
        model.addAttribute("url", "/user");
        return "list";
    }

    @PreAuthorize("hasAnyRole({'ROLE_ADMIN', 'ROLE_USER'})")
    @GetMapping("/{id}")
    public String getUser(@PathVariable Long id, Model model) {
        UserAccount user = userAccountService.findById(id);
        model.addAttribute("user", user);
        return "view";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/new")
    public String getRegistrationPage() {
        return "new";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/new")
    public String createUserAccount(UserAccount user, Model model) {
        boolean userExist = userAccountService.isUserExist(user.getUsername());
        if (userExist) {
            model.addAttribute("message", "User already exists!");
            return "new";
        }
        userAccountService.createUserAccount(user);
        return "redirect:/login";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{id}/edit")
    public String getEditPage(@PathVariable Long id, UserAccount user, Model model) {
        user.setId(id);
        model.addAttribute("user", user);
        return "edit";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/{id}/edit")
    public String updateUserAccount(@PathVariable Long id, UserAccount user) {
        user.setId(id);
        userAccountService.updateUserAccount(user);
        return String.format("redirect:/user/%s", id);
    }
}