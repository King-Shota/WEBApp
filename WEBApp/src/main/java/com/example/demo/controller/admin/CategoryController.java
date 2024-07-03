package com.example.demo.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Category;
import com.example.demo.service.admin.CategoryService;

import jakarta.validation.Valid;

@Controller
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categories")
    public String listCategories(Model model) {
        model.addAttribute("categories", categoryService.findAll());
        return "categoryList";
    }

    @GetMapping("/categories/new")
    public String showCreateCategoryForm(Model model) {
        model.addAttribute("category", new Category());
        return "createCategory";
    }

    @PostMapping("/categories")
    public String createCategory(@Valid @ModelAttribute Category category, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "createCategory";
        }

        try {
            categoryService.save(category);
            redirectAttributes.addFlashAttribute("message", "カテゴリーが正常に追加されました");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "カテゴリーの追加に失敗しました");
        }
        return "redirect:/categories";
    }

    @GetMapping("/categories/edit/{id}")
    public String showEditCategoryForm(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Category category = categoryService.findById(id);
            model.addAttribute("category", category);
            return "editCategory";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "カテゴリーの取得に失敗しました");
            return "redirect:/categories";
        }
    }

    @PostMapping("/categories/edit/{id}")
    public String editCategory(@PathVariable("id") Long id, @Valid @ModelAttribute Category category, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "editCategory";
        }

        try {
            category.setId(id); // Ensure the ID is set correctly
            categoryService.save(category);
            redirectAttributes.addFlashAttribute("message", "カテゴリーが正常に更新されました");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "カテゴリーの更新に失敗しました");
        }
        return "redirect:/categories";
    }

    @PostMapping("/categories/delete/{id}")
    public String deleteCategory(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        try {
            categoryService.deleteById(id);
            redirectAttributes.addFlashAttribute("message", "カテゴリーが正常に削除されました");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "カテゴリーの削除に失敗しました");
        }
        return "redirect:/categories";
    }
}
