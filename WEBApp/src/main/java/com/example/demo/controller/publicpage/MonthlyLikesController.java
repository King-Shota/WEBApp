package com.example.demo.controller.publicpage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.entity.UserInfo;
import com.example.demo.service.publicpage.MonthlyLikesService;

@Controller
public class MonthlyLikesController {

    @Autowired
    private MonthlyLikesService monthlyLikesService;

    @GetMapping("/monthly-likes")
    public String showMonthlyLikes(Model model) {
        List<UserInfo> monthlyLikes = monthlyLikesService.findMonthlyLikes();
        model.addAttribute("monthlyLikes", monthlyLikes);
        return "monthlyLikes";
    }
}
