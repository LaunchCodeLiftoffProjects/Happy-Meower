package com.happyhour.HappyHour.controllers;

import com.happyhour.HappyHour.data.OwnerRepository;
import com.happyhour.HappyHour.models.Owner;
import com.happyhour.HappyHour.models.dto.LoginFormDTO;
import com.happyhour.HappyHour.models.dto.RegisterFormDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;

@Controller
public class AuthenticationController {

    @Autowired
    OwnerRepository ownerRepository;

    private static final String ownerSessionKey = "owner";

    public Owner getOwnerFromSession(HttpSession session) {
        Integer ownerId = (Integer) session.getAttribute(ownerSessionKey);
        if (ownerId == null) {
            return null;
        }

        Optional<Owner> owner = ownerRepository.findById(ownerId);

        if (owner.isEmpty()) {
            return null;
        }

        return owner.get();
    }

    private static void setOwnerInSession(HttpSession session, Owner owner) {
        session.setAttribute(ownerSessionKey, owner.getId());
    }

    @GetMapping("/owner-registration")
    public String displayRegistrationForm(Model model) {
        model.addAttribute(new RegisterFormDTO());
        model.addAttribute("title", "Register");
        return "owner-registration";
    }

    @PostMapping("/owner-registration")
    public String processRegistrationForm(@ModelAttribute @Valid RegisterFormDTO registerFormDTO,
                                          Errors errors, HttpServletRequest request,
                                          Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Register");
            return "owner-registration";
        }

        Owner existingOwner = ownerRepository.findByUsername(registerFormDTO.getUsername());

        if (existingOwner != null) {
            errors.rejectValue("username", "username.alreadyexists", "A user with that username already exists");
            model.addAttribute("title", "Register");
            return "owner-registration";
        }

        String password = registerFormDTO.getPassword();
        String verifyPassword = registerFormDTO.getVerifyPassword();
        if (!password.equals(verifyPassword)) {
            errors.rejectValue("password", "passwords.mismatch", "Passwords do not match");
            model.addAttribute("title", "Register");
            return "owner-registration";
        }

        Owner newOwner = new Owner(registerFormDTO.getUsername(), registerFormDTO.getPassword());
        ownerRepository.save(newOwner);
        setOwnerInSession(request.getSession(), newOwner);

        return "redirect:";
    }

    @GetMapping("/owner-login")
    public String displayLoginForm(Model model) {
        model.addAttribute(new LoginFormDTO());
        model.addAttribute("title", "Log In");
        return "owner-login";
    }

    @PostMapping("/owner-login")
    public String processLoginForm(@ModelAttribute @Valid LoginFormDTO loginFormDTO,
                                   Errors errors, HttpServletRequest request,
                                   Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Log In");
            return "owner-login";
        }

        Owner theOwner = ownerRepository.findByUsername(loginFormDTO.getUsername());

        if (theOwner == null) {
            errors.rejectValue("username", "owner.invalid", "The given username does not exist");
            model.addAttribute("title", "Log In");
            return "owner-login";
        }

        String password = loginFormDTO.getPassword();

        if (!theOwner.isMatchingPassword(password)) {
            errors.rejectValue("password", "password.invalid", "Invalid password");
            model.addAttribute("title", "Log In");
            return "owner-login";
        }

        setOwnerInSession(request.getSession(), theOwner);

        return "redirect:/owner-home";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        request.getSession().invalidate();
        return "redirect:/owner-login";
    }
}
