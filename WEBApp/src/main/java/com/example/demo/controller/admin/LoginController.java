package com.example.demo.controller.admin;

import org.springframework.context.MessageSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.constant.AuthorityKind;
import com.example.demo.form.admin.LoginForm;
import com.example.demo.service.admin.LoginService;
import com.example.demo.util.AppUtil;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final LoginService service;
    private final PasswordEncoder passwordEncoder;
    private final MessageSource messageSource;
    private final HttpSession session;

    @GetMapping("/login")
    public String view(Model model, LoginForm form, @RequestParam(value = "error", required = false) String error) {
        model.addAttribute("loginForm", form);
        if (error != null) {
            String errorMsg = AppUtil.getMessage(messageSource, "login.wrong.input");
            model.addAttribute("errorMsg", errorMsg);
        }
        return "login";
    }

    @PostMapping("/login")
    public String login(Model model, LoginForm form) {
        var userInfo = service.searchUserById(form.getLoginId());
        var isCorrectUserAuth = userInfo.isPresent() && passwordEncoder.matches(form.getPassword(), userInfo.get().getPassword());
        if (isCorrectUserAuth) {
            session.setAttribute("userInfo", userInfo.get());

            // ユーザーの権限を確認し、リダイレクト先を設定
            var authority = userInfo.get().getAuthority().getCode();
            if (authority.equals(AuthorityKind.ADMIN.getCode())) {
                return "redirect:/admindashboard"; // 管理者用ダッシュボードにリダイレクト
            } else {
                return "redirect:/generaldashboard"; // 一般ユーザー用ダッシュボードにリダイレクト
            }
        } else {
            String errorMsg = AppUtil.getMessage(messageSource, "login.wrong.input");
            model.addAttribute("errorMsg", errorMsg);
            model.addAttribute("loginForm", form);
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout() {
        session.invalidate();
        return "redirect:/login";
    }
}
