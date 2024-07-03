package com.example.demo.service.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Category;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.InquiryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private InquiryRepository inquiryRepository;

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("カテゴリーが見つかりません"));
    }

    @Override
    public void save(Category category) {
        categoryRepository.save(category);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        // 関連する問い合わせを削除
        inquiryRepository.deleteByCategoryId(id);
        // カテゴリを削除
        categoryRepository.deleteById(id);
    }
}
