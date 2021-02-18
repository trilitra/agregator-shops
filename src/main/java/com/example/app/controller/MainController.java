package com.example.app.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Slf4j
@Controller
public class MainController {

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        log.info("greeting");
        return "greeting";
    }

    @GetMapping("/main")
    public String main(Map<String, Object> model) {
        log.info("main");
        return "main";
    }


}
