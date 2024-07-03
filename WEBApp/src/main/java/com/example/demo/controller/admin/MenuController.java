package com.example.demo.controller.admin;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MenuController {

    @GetMapping("/menu")
    public String showMenu() {
        // ユーザーの権限を取得
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String authority = authentication.getAuthorities().stream()
                                          .findFirst()
                                          .map(auth -> auth.getAuthority())
                                          .orElse("1");

        // 権限に基づいて遷移先を決定
        if ("2".equals(authority)) {
            return "redirect:/adminPage";  // 管理者の遷移先ページ
        } else {
            return "redirect:/userPage";  // 一般ユーザーの遷移先ページ
        }
    }
}
