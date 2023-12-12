package com.effectivemobile.probation.tms.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping
public class MainController {

    @GetMapping("/")
    public ModelAndView getStart() {
        log.info("SERVER получен запрос GET homepage");
        return new ModelAndView("greetings");
    }
}
