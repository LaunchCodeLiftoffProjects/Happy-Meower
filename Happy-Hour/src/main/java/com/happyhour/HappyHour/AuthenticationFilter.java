package com.happyhour.HappyHour;

import com.happyhour.HappyHour.controllers.AuthenticationController;
import com.happyhour.HappyHour.data.OwnerRepository;
import com.happyhour.HappyHour.models.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class AuthenticationFilter extends HandlerInterceptorAdapter {

    @Autowired
    OwnerRepository ownerRepository;

    @Autowired
    AuthenticationController authenticationController;

    private static final List<String> whitelist = Arrays.asList("/owner-login", "/owner-registration", "/index", "/results", "/logout", "/css");

    private static boolean isWhitelisted(String path) {
        for (String pathRoot : whitelist) {
            if (path.startsWith(pathRoot)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {

        // Don't require log-in for whitelisted pages
        if (isWhitelisted(request.getRequestURI())) {
            // returning true indicates that the request may proceed
            return true;
        }

        HttpSession session = request.getSession(); //retrieves session object
        Owner owner = authenticationController.getOwnerFromSession(session); //retrieves Owner object corresponding to the given owner if not null

        //if owner is logged in, allow the request to be handled as normal
        if (owner != null) {
            return true;
        }

        //if owner is not logged in already, redirect to login page
        response.sendRedirect("/owner-login");
        return false;
    }
}
