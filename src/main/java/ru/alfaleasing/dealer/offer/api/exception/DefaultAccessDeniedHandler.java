package ru.alfaleasing.dealer.offer.api.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Customize error 403
public class DefaultAccessDeniedHandler implements AccessDeniedHandler {

    @Autowired
    private HandlerExceptionResolver handlerExceptionResolver;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException e) {
        handlerExceptionResolver.resolveException(request, response, null, e);
    }
}
