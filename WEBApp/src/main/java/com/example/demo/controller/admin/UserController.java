package com.example.demo.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.User;
import com.example.demo.service.admin.UserService;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/editUser/{loginId}")
    public String showEditForm(@PathVariable("loginId") String loginId, Model model) {
        User user = userService.findByLoginId(loginId);
        model.addAttribute("userForm", user);
        return "editUser";
    }

    @PostMapping("/updateUser")
    public String updateUser(@ModelAttribute("userForm") User user, BindingResult result, @RequestParam("profileImage") MultipartFile profileImage) {
        if (result.hasErrors()) {
            return "editUser";
        }
        user.setProfileImage(profileImage);
        userService.updateUser(user);
        return "redirect:/userList";
    }
}
