package com.quotes.quotes.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
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

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + apiKey);

            HttpEntity<String> entity = new HttpEntity<>(headers);

            ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.GET, entity, String.class);

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(response.getBody());
            JsonNode quoteContents = rootNode.path("contents").path("quotes").get(0);

            String quote = quoteContents.path("quote").asText();
            String author = quoteContents.path("author").asText();

            return "Quote: \"" + quote + "\" - " + author;
        } catch (RestClientException e) {
            return "Error fetching quote: " + e.getMessage();
        } catch (Exception e) {
            return "Error parsing response: " + e.getMessage();
        }
    }
}
