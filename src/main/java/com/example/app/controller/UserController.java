package com.example.app.controller;

import com.example.app.model.Role;
import com.example.app.model.User;
import com.example.app.service.RoleService;
import com.example.app.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Slf4j
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    private HashMap<String, Object> params = new HashMap<>();

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("/users")
    public ModelAndView getUsers() {
        List<User> users = userService.findAll();
        params.put("users", users);
        log.info("Получение списка всех пользователей: " + Arrays.toString(users.toArray()));
        return new ModelAndView("user", params);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping("/users")
    public ModelAndView delete(@RequestParam("id") Integer id) {
        userService.deleteById(id);
        params.put("users", userService.findAll());
        log.info("Удаление пользователя: " + id);
        return new ModelAndView("user", params);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping("/edit-user/{id}")
    public ModelAndView editUser(@RequestParam Integer id) {
        User users = userService.findById(id);
        List<Role> roles = roleService.findAll();
        params.put("users", users);
        params.put("roles", roles);
        log.info("Редактирование пользователя: " + users.toString() + "|" + Arrays.toString(roles.toArray()));
        return new ModelAndView("edit-user", params);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping("/edit-user")
    public ModelAndView saveChange
            (@RequestParam Integer id,
             @RequestParam Boolean active,
             @RequestParam String username,
             @RequestParam String password,
             @RequestParam Integer roleId) {
        userService.saveInEditor(id, active, username, password, roleId);
        log.info("Сохранение изменений: " + id + "|" + active + "|" + username + "|" + password + "|" + roleId);
        return new ModelAndView("redirect:/users");
    }

    @GetMapping("/users/profile")
    public ModelAndView getProfile(@AuthenticationPrincipal User user) {
        params.put("users", user);
        log.info("Получение профиля: " + user.toString());
        return new ModelAndView("/profile", params);
    }

    @PostMapping("/users/profile")
    public ModelAndView updateProfile(
            @AuthenticationPrincipal User user,
            @RequestParam String password,
            @RequestParam String email
    ) {
        userService.updateProfile(user, password, email);
        log.info("Обновление профиля: " + user.toString() + "|" + password + "|" + email);
        return new ModelAndView("redirect:/");
    }

}
