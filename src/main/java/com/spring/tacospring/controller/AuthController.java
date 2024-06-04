package com.spring.tacospring.controller;

import com.spring.tacospring.data.UserRepository;
import com.spring.tacospring.dto.UserRegistrationDTO;
import com.spring.tacospring.mapper.UserRegistrationMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    private final UserRepository userRepo;
    private final UserRegistrationMapper mapper;

    @GetMapping("/register")
    public String registerForm(Model model) {
        if (!model.containsAttribute("user")) {
            model.addAttribute("user", new UserRegistrationDTO());
        }
        return "registration";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/register")
    public String processRegistration(@Valid @ModelAttribute("user") UserRegistrationDTO user,
                                      final Errors bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.user", bindingResult)
                    .addFlashAttribute("user", user);
            return "redirect:/register";
        }

        if (!user.getPassword().equals(user.getConfirmPassword())) {
            return "redirect:/register";
        }
        if (userRepo.findByUsername(user.getUsername()).isPresent()) {
            return "redirect:/register";
        }
        userRepo.save(mapper.toEntity(user));
        return "redirect:/login";
    }
}
