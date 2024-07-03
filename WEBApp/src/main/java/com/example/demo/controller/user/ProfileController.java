package com.example.demo.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.Profile;
import com.example.demo.service.user.ProfileService;

import jakarta.validation.Valid;

@Controller
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @GetMapping("/profile")
    public String showProfileForm(Model model) {
        model.addAttribute("profile", new Profile());
        return "profileForm";
    }

    @PostMapping("/profile")
    public String submitProfileForm(@Valid @ModelAttribute("profile") Profile profile, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "profileForm";
        }
        profileService.saveProfile(profile);
        return "redirect:/profiles";
    }

    @GetMapping("/profiles")
    public String listProfiles(Model model) {
        model.addAttribute("profiles", profileService.getAllProfiles());
        return "profileList";
    }
    
    @PostMapping("/profiles/{id}/like")
    public ResponseEntity<Void> likeProfile(@PathVariable Long id) {
        // いいねの処理を実装
        return ResponseEntity.ok().build();
    }
    
    
    
}
