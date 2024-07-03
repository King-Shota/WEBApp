package com.example.demo.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.service.publicpage.PublicPageService;

@Controller
public class PageController {

    @Autowired
    private PublicPageService publicPageService;

    @GetMapping("/adminPage")
    public String showAdminPage() {
        return "admindashboard";
    }

    @GetMapping("/generaldashboard")
    public String showUserPage(@AuthenticationPrincipal User user, Model model) {
        String loginId = user.getUsername();
        int yearlyLikes = publicPageService.getTotalYearlyLikes(loginId);
        int monthlyLikes = publicPageService.getTotalMonthlyLikes(loginId);
        model.addAttribute("yearlyLikes", yearlyLikes);
        model.addAttribute("monthlyLikes", monthlyLikes);
        return "generaldashboard";
    }
}
