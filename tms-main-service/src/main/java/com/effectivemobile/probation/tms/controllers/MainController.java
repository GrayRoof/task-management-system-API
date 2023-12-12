package com.effectivemobile.probation.tms.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/")
public class MainController {

    @GetMapping()
    public String getStart() {
        log.info("SERVER получен запрос GET ");
        return "greetings";
    }
}
