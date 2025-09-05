package com.api.BookCatalogueSpringBootWebApp.security.auth;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import java.util.Collections;

/*
 * Converts the incoming API key into an authentication instance.
 */
public class ApiKeyAuthentication extends AbstractAuthenticationToken {

    private final String apiKey;
    private final String clientName;

    public ApiKeyAuthentication(String apiKey) {
        this(apiKey, null);
    }

    public ApiKeyAuthentication(String apiKey, String clientName) {
        super(Collections.emptyList());
        this.apiKey = apiKey;
        this.clientName = clientName;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return apiKey;
    }

    @Override
    public Object getPrincipal() {
       return clientName != null ? clientName : "API_CLIENT";
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getClientName() {
        return clientName;
    }
    
    @Override
    public String toString() {
        return "ApiKeyAuthentication{" +
            "apiKey='***" + (apiKey != null ? apiKey.substring(Math.max(0, apiKey.length() - 4)) : "null") + "'" + 
            ", clientName='" + clientName + "'" + 
            ", authenticated=" + isAuthenticated() + '}';
    }
}
