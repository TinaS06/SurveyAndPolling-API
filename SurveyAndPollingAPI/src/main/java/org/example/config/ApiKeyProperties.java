package org.example.config;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiKeyProperties {

    private static final String API_KEY = "1234";
    public String getAPI_KEY() { return API_KEY; }

}