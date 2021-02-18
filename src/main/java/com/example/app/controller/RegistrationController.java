package com.example.app.controller;

import com.example.app.model.User;
import com.example.app.model.dto.CaptchaResponseDto;
import com.example.app.service.RoleService;
import com.example.app.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Map;

@Slf4j
@Controller
public class RegistrationController {

    private final static String CAPTCHA_URL = "https://www.google.com/recaptcha/api/siteverify?secret=%s&response=%s";

    @Autowired
    private UserService userService;

    @Value("${recaptcha.secret}")
    private String secret;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RoleService roleService;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(
            @RequestParam("password2") String passwordConfirm,
            @Valid User user,
            @RequestParam("g-recaptcha-response") String captchaResponce,
            BindingResult bindingResult,
            Model model
    ) {
        String url = String.format(CAPTCHA_URL, secret, captchaResponce);
        CaptchaResponseDto response = restTemplate.postForObject(url, Collections.emptyList(), CaptchaResponseDto.class);

        boolean nameEmpty = user.getUsername().isEmpty();

        if (nameEmpty) {
            log.info("Не введен имя пользователя");
            model.addAttribute("usernameError", "Введите имя пользователя");
        }

        boolean emailEmpty = user.getEmail().isEmpty();

        if (emailEmpty) {
            log.info("Не введен адрес электронной почты");
            model.addAttribute("emailError", "Введите адрес электронной почты");
        }

        if (!response.isSuccess()) {
            log.info("Не введен captcha");
            model.addAttribute("captchaError", "Введите captcha");
        }

        boolean password2Empty = StringUtils.isEmpty(passwordConfirm);

        if (password2Empty) {
            log.info("Не подтвержден пароль");
            model.addAttribute("password2Error", "Подтвердите пароль");
        }

        if (user.getPassword() != null && !user.getPassword().equals(passwordConfirm)) {
            log.info("Пароли не совпадают");
            model.addAttribute("passwordError", "Пароли не совпадают");
            return "registration";
        }
        if (emailEmpty || nameEmpty || password2Empty || bindingResult.hasErrors() || !response.isSuccess()) {
            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);
            model.addAttribute(errors);
            return "registration";
        }
        if (!userService.addUser(user)) {
            log.info("Пользователь с таким логином уже существует: " + user);
            model.addAttribute("usernameError", "Пользователь с таким логином уже существует");
            return "registration";
        }
        return "redirect:/login";
    }

    @GetMapping("/activate/{code}")
    public String activate(Model model, @PathVariable String code) {
        boolean isActivated = userService.activateUser(code);
        if (isActivated) {
            log.info("Email успешно подтвержден!");
            model.addAttribute("messageType", "success");
            model.addAttribute("message", "Email успешно подтвержден!");
        } else {
            log.info("Код активации не найден!");
            model.addAttribute("messageType", "danger");
            model.addAttribute("message", "Код активации не найден!");
        }
        return "login";
    }
}
