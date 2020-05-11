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

public class AuthenticationFilter extends HandlerInterceptorAdapter {

    @Autowired
    OwnerRepository ownerRepository;

    @Autowired
    AuthenticationController authenticationController;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {

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
