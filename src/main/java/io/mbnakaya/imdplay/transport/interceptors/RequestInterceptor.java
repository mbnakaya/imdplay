package io.mbnakaya.imdplay.transport.interceptors;

import io.mbnakaya.imdplay.domain.exceptions.UnauthorizedException;
import io.mbnakaya.imdplay.interactors.port.AuthenticationService;
import io.mbnakaya.imdplay.transport.utils.FreeRoute;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class RequestInterceptor implements HandlerInterceptor {

    @Autowired
    private AuthenticationService service;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        final FreeRoute freeRouteEnabled = ((HandlerMethod)handler).getMethodAnnotation(FreeRoute.class);
        String authToken = request.getHeader("Authorization");

        if (freeRouteEnabled != null) return true;
        if (authToken == null || !service.authenticate(authToken)) throw new UnauthorizedException();

        return true;
    }
}
