package com.example.demo.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.service.admin.UserLikesService;

@Controller
public class AdminDashboardController {

    @Autowired
    private UserLikesService userLikesService;

    @GetMapping("/admindashboard")
    public String viewAdminDashboard(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("yearlyLikes", userLikesService.getYearlyLikesRanking());
        model.addAttribute("monthlyLikes", userLikesService.getMonthlyLikesRanking());
        return "admindashboard";
    }
}
