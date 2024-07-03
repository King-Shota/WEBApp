package com.example.demo.controller.publicpage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.entity.UserInfo;
import com.example.demo.service.publicpage.PublicPageService;

@Controller
public class PublicPageController {

    @Autowired
    private PublicPageService publicPageService;

    @GetMapping("/publicPage")
    public String showPublicPage(Model model) {
        List<UserInfo> users = publicPageService.findAllUsersWithRoleUser();
        model.addAttribute("users", users);
        return "publicPage";
    }

    @PostMapping("/like")
    @ResponseBody
    public int likeUser(@RequestParam("loginId") String loginId) {
        return publicPageService.incrementLikes(loginId);
    }
    
    
    
    @GetMapping("/user-details/{loginId}")
    public String showUserDetails(@PathVariable String loginId, Model model) {
        UserInfo user = publicPageService.getUserInfoByLoginId(loginId);
        model.addAttribute("user", user);
        return "userDetails";
    }
    
    
}
