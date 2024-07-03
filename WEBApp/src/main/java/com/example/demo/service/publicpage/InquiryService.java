package com.example.demo.service.publicpage;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Inquiry;
import com.example.demo.form.release.InquiryForm;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.InquiryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InquiryService {
    private final InquiryRepository inquiryRepository;
    private final CategoryRepository categoryRepository;

    @Transactional
    public void saveInquiry(InquiryForm form) {
        Inquiry inquiry = new Inquiry();
        inquiry.setName(form.getName());
        inquiry.setFurigana(form.getFurigana());
        inquiry.setEmail(form.getEmail());
        inquiry.setCategory(categoryRepository.findById(form.getCategoryId()).orElseThrow());
        inquiry.setContent(form.getContent());
        inquiryRepository.save(inquiry);
    }
}
