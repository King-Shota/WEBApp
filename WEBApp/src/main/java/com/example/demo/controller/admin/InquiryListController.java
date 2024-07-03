package com.example.demo.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Inquiry;
import com.example.demo.service.admin.InquiryListService;

@Controller
public class InquiryListController {

    @Autowired
    private InquiryListService inquiryListService;

    @GetMapping("/inquiries")
    public String listInquiries(Model model) {
        model.addAttribute("inquiries", inquiryListService.findAll());
        return "inquiryList";
    }

    @GetMapping("/inquiries/{id}")
    public String inquiryDetail(@PathVariable Long id, Model model, @RequestParam(required = false) String message) {
        Inquiry inquiry = inquiryListService.findById(id);
        String formattedCreatedAt = inquiryListService.formatCreatedAt(inquiry);
        model.addAttribute("inquiry", inquiry);
        model.addAttribute("formattedCreatedAt", formattedCreatedAt);
        if (message != null) {
            model.addAttribute("message", message);
        }
        return "inquiryDetail";
    }

    @PostMapping("/inquiries/{id}/updateStatus")
    public String updateInquiryStatus(@PathVariable Long id, @RequestParam("status") String status, RedirectAttributes redirectAttributes) {
        try {
            Inquiry inquiry = inquiryListService.findById(id);
            inquiry.setStatus(status);
            inquiryListService.save(inquiry);
            redirectAttributes.addAttribute("message", "更新が正常に完了しました");
        } catch (Exception e) {
            redirectAttributes.addAttribute("message", "更新処理に失敗しました");
        }
        return "redirect:/inquiries/" + id;
    }
}
