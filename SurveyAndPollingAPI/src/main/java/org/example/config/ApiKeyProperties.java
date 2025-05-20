package org.example.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ApiKeyProperties {

    @Value("${security.api-key}")
    private String apiKey;

    public String getAPI_KEY() {
        return apiKey;
    }
}
