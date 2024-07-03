package com.example.demo.controller.publicpage;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Category;
import com.example.demo.form.release.InquiryForm;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.service.publicpage.InquiryService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class InquiryController {
    private final InquiryService inquiryService;
    private final CategoryRepository categoryRepository;

    @GetMapping("/inquiry")
    public String showInquiryForm(Model model, @RequestParam(required = false) String success) {
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("inquiryForm", new InquiryForm());
        if ("true".equals(success)) {
            model.addAttribute("message", "正常に送信が完了しました。");
            model.addAttribute("messageType", "success");
        } else if ("false".equals(success)) {
            model.addAttribute("message", "送信できませんでした。");
            model.addAttribute("messageType", "danger");
        }
        return "inquiryForm";
    }

    @PostMapping("/inquiry")
    public String submitInquiryForm(@Validated InquiryForm form, BindingResult result, Model model) {
        if (result.hasErrors()) {
            List<Category> categories = categoryRepository.findAll();
            model.addAttribute("categories", categories);
            return "inquiryForm";
        }
        try {
            inquiryService.saveInquiry(form);
            return "redirect:/inquiry?success=true";
        } catch (Exception e) {
            return "redirect:/inquiry?success=false";
        }
    }
}
