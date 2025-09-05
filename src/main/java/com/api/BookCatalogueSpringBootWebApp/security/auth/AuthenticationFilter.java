package com.api.BookCatalogueSpringBootWebApp.security.auth;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


/*
 * Acts as an authentication filter. 
 * It will evaluate the API key header and set the result to the current SecurityContext holder.
 * If an error occurs, the execution is stopped. 
 */
@Component
public class AuthenticationFilter extends GenericFilterBean{

    private final AuthenticationService authenticationService;
        private static final Logger logger = LoggerFactory.getLogger(AuthenticationFilter.class);
    
    public AuthenticationFilter(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        try {
            // Skip non-API endpoints
            if (!authenticationService.shouldAuthenticateWithApiKey(httpRequest)) {
                logger.debug("Skipping API key authentication for: {}", httpRequest.getRequestURI());
                filterChain.doFilter(request, response);
                return;
            }

            // Skip public endpoints
            if (authenticationService.isPublicEndpoint(httpRequest)) {
                logger.debug("Public endpoint accessed: {}", httpRequest.getRequestURI());
                filterChain.doFilter(request, response);
                return;
            }

            String apiKey = authenticationService.extractApiKeyFromRequest(httpRequest);

            if (apiKey == null) {
                logger.warn("Missing API key for endpoint: {} from IP: {}", 
                           httpRequest.getRequestURI(), getClientIp(httpRequest));
                handleAuthenticationFailure(httpResponse, "Missing API key");
                return;
            }

            // Create authentication
            ApiKeyAuthentication authentication = authenticationService.createAuthentication(apiKey);
            
            if (authentication == null) {
                logger.warn("Invalid API key for endpoint: {} from IP: {}", 
                           httpRequest.getRequestURI(), getClientIp(httpRequest));
                handleAuthenticationFailure(httpResponse, "Invalid API key");
                return;
            }

            // Set authentication in SecurityContext
            SecurityContextHolder.getContext().setAuthentication(authentication);
            
            logger.debug("API key authentication successful for client: {} on endpoint: {}", 
                        authentication.getPrincipal(), httpRequest.getRequestURI());
            
            // Continue with the request
            filterChain.doFilter(request, response);
        }
        catch (Exception e)
        {
            logger.error("Error in API key authentication filter", e);
            handleAuthenticationFailure(httpResponse, "Authentication error");
        }
        finally {
            // Clear the SecurityContext after request processing
            if (authenticationService.shouldAuthenticateWithApiKey(httpRequest)) {
                SecurityContextHolder.clearContext();
            }
        }
    }
    
    private void handleAuthenticationFailure(HttpServletResponse response, String message) 
            throws IOException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        
        String jsonResponse = String.format(
            "{\"error\":\"Unauthorized\",\"message\":\"%s\",\"timestamp\":%d}",
            message, System.currentTimeMillis()
        );
        
        response.getWriter().write(jsonResponse);
    }

    private String getClientIp(HttpServletRequest request) {
        String xForwardedFor = request.getHeader("X-Forwarded-For");
        return (xForwardedFor != null && !xForwardedFor.isEmpty()) 
            ? xForwardedFor.split(",")[0].trim() 
            : request.getRemoteAddr();
    }
}
