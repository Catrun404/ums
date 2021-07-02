package com.catrun.ums.controller;

import com.catrun.ums.api.service.UserAccountService;
import com.catrun.ums.domain.Role;
import com.catrun.ums.domain.Status;
import com.catrun.ums.domain.UserAccount;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.String.format;

@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class UserAccountController {

    private final UserAccountService userAccountService;
    private static final List<Role> ROLES = new ArrayList<>(Arrays.asList(Role.values()));
    private static final List<Status> STATUSES = new ArrayList<>(Arrays.asList(Status.values()));
    private static final Map<String, List<? extends Enum<?>>> ENUM_ATTRIBUTES = new HashMap<>() {{
        put("roles", ROLES);
        put("statuses", STATUSES);
    }};

    @PreAuthorize("hasAnyRole({'ROLE_ADMIN', 'ROLE_USER'})")
    @GetMapping
    public String getUsers(@RequestParam(required = false, defaultValue = "") Role role,
                           @RequestParam(required = false, defaultValue = "") String username,
                           @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable,
                           Model model) {
        Page<UserAccount> page = userAccountService.findAll(role, username, pageable);
        model.addAttribute("page", page);
        model.addAttribute("roles", ROLES);
        model.addAttribute("username", username);
        model.addAttribute("url", "/user");
        return "list";
    }

    @PreAuthorize("hasAnyRole({'ROLE_ADMIN', 'ROLE_USER'})")
    @GetMapping("/{id}")
    public String getUser(@PathVariable("id") UserAccount user,
                          Model model) {
        model.addAttribute("user", user);
        return "user_view";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/new")
    public String getRegistrationPage(Model model) {
        model.addAllAttributes(ENUM_ATTRIBUTES);
        model.addAttribute("user", new UserAccount());
        return "new";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/new")
    public String createUserAccount(@Valid @ModelAttribute("user") UserAccount user,
                                    BindingResult bindingResult,
                                    Model model) {
        boolean userExist = userAccountService.isUserExist(user.getUsername());
        model.addAllAttributes(ENUM_ATTRIBUTES);
        model.addAttribute("user", user);
        if (userExist) {
            model.addAttribute("message", "User already exists!");
            return "new";
        }
        if (!bindingResult.hasErrors()) {
            userAccountService.createUserAccount(user);
            return "redirect:/login";
        }
        return "new";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{id}/edit")
    public String getEditPage(@PathVariable("id") UserAccount user, Model model) {
        model.addAllAttributes(ENUM_ATTRIBUTES);
        model.addAttribute("user", user);
        return "edit";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/{id}/edit")
    public String updateUserAccount(@PathVariable("id") Long id,
                                    @Valid @ModelAttribute("user") UserAccount user,
                                    BindingResult bindingResult,
                                    Model model) {
        user.setId(id);
        model.addAttribute("user", user);
        if (bindingResult.hasErrors()) {
            return "edit";
        }
        userAccountService.updateUserAccount(user);
        return format("redirect:/user/%s", user.getId());
    }
}