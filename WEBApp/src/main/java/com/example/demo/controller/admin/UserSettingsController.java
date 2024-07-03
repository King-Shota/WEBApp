package com.example.demo.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.UserInfo;
import com.example.demo.form.admin.UserSettingsForm;
import com.example.demo.service.admin.UserSettingsService;

import jakarta.validation.Valid;

@Controller
public class UserSettingsController {

    @Autowired
    private UserSettingsService userSettingsService;

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/user/settings/{loginId}")
    public String showSettings(@PathVariable String loginId, Model model) {
        UserInfo user = userSettingsService.findUserByLoginId(loginId);
        model.addAttribute("userSettingsForm", new UserSettingsForm(user));
        return "userSettings";
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/user/settings/{loginId}")
    public String updateSettings(@PathVariable String loginId, @Valid @ModelAttribute UserSettingsForm userSettingsForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "userSettings";
        }
        userSettingsService.updateUserSettings(loginId, userSettingsForm);
        return "redirect:/user/settings/" + loginId + "?success";
    }
}
