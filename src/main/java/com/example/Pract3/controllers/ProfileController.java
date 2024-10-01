package com.example.Pract3.controllers;

import com.example.Pract3.models.ProfileModel;
import com.example.Pract3.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/profiles")
public class ProfileController {

    private final ProfileService profileService;

    @Autowired
    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping
    public String getAllProfiles(Model model) {
        model.addAttribute("profiles", profileService.getAllProfiles());
        return "profiles";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        ProfileModel profile = profileService.getProfileById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid profile Id:" + id));
        model.addAttribute("profile", profile);
        return "editProfile";
    }

    @PostMapping("/{id}/update")
    public String updateProfile(@PathVariable Long id, @Valid @ModelAttribute("profile") ProfileModel profile, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "editProfile";
        }
        profileService.updateProfile(id, profile);
        return "redirect:/users";
    }

}
