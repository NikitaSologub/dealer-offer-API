package ru.alfaleasing.dealer.offer.api.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// Customize error 401
public class DefaultAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Autowired
    private HandlerExceptionResolver handlerExceptionResolver;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        handlerExceptionResolver.resolveException(request, response, null, e);
    }
}
