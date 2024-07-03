package com.example.demo.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.service.admin.DeletedUserService;

@Controller
@RequestMapping("/deletedUserList")
public class DeletedUserController {

    @Autowired
    private DeletedUserService deletedUserService;

    @GetMapping
    public String showDeletedUsers(Model model) {
        model.addAttribute("deletedUsers", deletedUserService.findDeletedUsers());
        return "deletedUserList";
    }

    @PostMapping("/deleteUserPermanently")
    public String deleteUserPermanently(@RequestParam("loginId") String loginId) {
        deletedUserService.deleteUserPermanently(loginId);
        return "redirect:/deletedUserList";
    }

    @PostMapping("/restoreUser")
    public String restoreUser(@RequestParam("loginId") String loginId) {
        deletedUserService.restoreUser(loginId);
        return "redirect:/deletedUserList";
    }
    
    
    @PostMapping("/deleteUser")
    public String deleteUser(@RequestParam("loginId") String loginId) {
        deletedUserService.softDeleteUser(loginId);
        return "redirect:/userList";
    }
    
}
