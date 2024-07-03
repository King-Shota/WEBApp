package com.example.demo.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.form.admin.AdminSignupForm;
import com.example.demo.form.admin.GeneralSignupForm;
import com.example.demo.service.admin.SignupService;

import jakarta.validation.Valid;

@Controller
public class SignupController {

    @Autowired
    private SignupService signupService;

    @GetMapping("/signup")
    public String showSignupForm(Model model) {
        model.addAttribute("adminSignupForm", new AdminSignupForm());
        model.addAttribute("generalSignupForm", new GeneralSignupForm());
        return "signup";
    }

    @PostMapping("/signup/admin")
    public String signupAdmin(@Valid AdminSignupForm adminSignupForm, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "signup";
        }
        signupService.resistAdminUserInfo(adminSignupForm);
        return "redirect:/signup?success";
    }

    @PostMapping("/signup/user")
    public String signupUser(@Valid GeneralSignupForm generalSignupForm, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "signup";
        }

        MultipartFile profileImage = generalSignupForm.getProfileImage();
        if (!profileImage.isEmpty()) {
            try {
                signupService.saveProfileImage(profileImage);
            } catch (Exception e) {
                model.addAttribute("message", "プロフィール画像の保存に失敗しました。");
                return "signup";
            }
        }

        signupService.resistGeneralUserInfo(generalSignupForm);
        return "redirect:/signup?success";
    }
}
