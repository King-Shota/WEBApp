package com.example.demo.service.admin;

import java.util.List;

import com.example.demo.entity.Inquiry;

public interface InquiryListService {
    List<Inquiry> findAll();
    Inquiry findById(Long id);
    void save(Inquiry inquiry);
    String formatCreatedAt(Inquiry inquiry);
}
