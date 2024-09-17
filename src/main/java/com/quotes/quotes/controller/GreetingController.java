package com.quotes.quotes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quotes.quotes.service.GreetingService;

@RestController
@RequestMapping("/api/greeting")
public class GreetingController {

    @Autowired
    private GreetingService greetingService;

    @GetMapping("/quote")
    public String getGreetingQuote() {
        return greetingService.getGreetingQuote();
    }
}