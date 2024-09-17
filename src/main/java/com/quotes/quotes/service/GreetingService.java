package com.quotes.quotes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.quotes.quotes.config.ApiConfig;

@Service
public class GreetingService {

    @Autowired
    private ApiConfig apiConfig;

    public String getGreetingQuote() {
        String apiUrl = apiConfig.getApiUrl();
        String apiKey = apiConfig.getApiKey();

        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(apiUrl + "?key=" + apiKey, String.class);
        return response;
    }
}