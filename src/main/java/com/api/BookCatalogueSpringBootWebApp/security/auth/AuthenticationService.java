package com.api.BookCatalogueSpringBootWebApp.security.auth;

import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import jakarta.servlet.http.HttpServletRequest;

/*
 * Retrieve the API key header from the request and validate against the API key.
 */
@Service
public class AuthenticationService {
    
    private static final String AUTH_TOKEN_HEADER_NAME = "X-API-KEY";
    private static final String AUTH_TOKEN = "7K3jQ9pR2vZ8sA5xW4dE6fT1gY3hN7mB9cX5zL2kP6jF8dS3vG";

    @Value("${app.api.enabled:true}")
    private boolean apiKeyEnabled;

    private Set<String> validApiKeys = Set.of(AUTH_TOKEN);

    private final Map<String, String> apiKeyToClientMap = Map.of(
        AUTH_TOKEN, "Frontend Application"
    );

    public String extractApiKeyFromRequest(HttpServletRequest request) {
        // Try header first
        String apiKey = request.getHeader(AUTH_TOKEN_HEADER_NAME);
        if (isValidKeyFormat(apiKey)) {
            return apiKey.trim();
        }
        
        // Fallback to query parameter (less secure, use sparingly)
        apiKey = request.getParameter("apiKey");
        if (isValidKeyFormat(apiKey)) {
            return apiKey.trim();
        }
        
        return null;
    }

    public boolean isValidApiKey(String apiKey) {
        if (!apiKeyEnabled) {
            System.out.print("API key authentication is disabled");
            return true; // If disabled, allow all requests
        }
        
        if (!isValidKeyFormat(apiKey)) {
            return false;
        }
        
        boolean isValid = validApiKeys.contains(apiKey);
        
        if (!isValid) {
            System.out.print(String.format("Invalid API key attempted: {}***", 
                       apiKey.length() > 4 ? apiKey.substring(0, 4) : "****"));
        }
        
        return isValid;
    }

    public ApiKeyAuthentication createAuthentication(String apiKey) {
        if (!isValidApiKey(apiKey)) {
            return null;
        }
        
        String clientName = apiKeyToClientMap.get(apiKey);
        return new ApiKeyAuthentication(apiKey, clientName);
    }

    public boolean shouldAuthenticateWithApiKey(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        
        // Only apply API key auth to API endpoints
        return requestURI.startsWith("/api/") || 
               requestURI.startsWith("/rest/") ||
               requestURI.startsWith("/v1/") ||
               requestURI.startsWith("/v2/");
    }

    public boolean isPublicEndpoint(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        String method = request.getMethod();
        
        // Always allow OPTIONS requests (CORS preflight)
        if ("OPTIONS".equals(method)) {
            return true;
        }
        
        // Public endpoints
        return requestURI.startsWith("/actuator/health") ||
               requestURI.startsWith("/public/") ||
               requestURI.startsWith("/swagger-ui") ||
               requestURI.startsWith("/v3/api-docs") ||
               requestURI.equals("/error");
    }

    private boolean isValidKeyFormat(String apiKey) {
        return apiKey != null && 
               !apiKey.trim().isEmpty() && 
               apiKey.length() >= 10; // Minimum key length
    }

    private String getClientIp(HttpServletRequest request) {
        String xForwardedFor = request.getHeader("X-Forwarded-For");
        return (xForwardedFor != null && !xForwardedFor.isEmpty()) 
            ? xForwardedFor.split(",")[0].trim() 
            : request.getRemoteAddr();
    }
}
