package com.example.demo.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.UserInfo;
import com.example.demo.form.admin.UserSettingsForm;
import com.example.demo.service.admin.UserSettingsService;

import jakarta.validation.Valid;

@Controller
public class GeneralUserSettingsController {

    @Autowired
    private UserSettingsService userSettingsService;

    @GetMapping("/generaluser/settings")
    public String showSettings(@AuthenticationPrincipal User user, Model model) {
        String loginId = user.getUsername();
        UserInfo userInfo = userSettingsService.findUserByLoginId(loginId);
        model.addAttribute("userSettingsForm", new UserSettingsForm(userInfo));
        return "GeneraluserSettings";
    }

    @PostMapping("/generaluser/settings")
    public String updateSettings(@AuthenticationPrincipal User user, @Valid @ModelAttribute UserSettingsForm userSettingsForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "GeneraluserSettings";
        }
        String loginId = user.getUsername();
        userSettingsService.updateUserSettings(loginId, userSettingsForm);
        return "redirect:/generaluser/settings?success";
    }
}
