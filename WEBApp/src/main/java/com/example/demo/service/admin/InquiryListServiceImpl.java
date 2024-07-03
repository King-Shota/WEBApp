package com.example.demo.service.admin;

import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Inquiry;
import com.example.demo.repository.InquiryListRepository;

@Service
public class InquiryListServiceImpl implements InquiryListService {

    @Autowired
    private InquiryListRepository inquiryListRepository;

    @Override
    public List<Inquiry> findAll() {
        return inquiryListRepository.findAll();
    }

    @Override
    public Inquiry findById(Long id) {
        return inquiryListRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid inquiry Id:" + id));
    }

    @Override
    public void save(Inquiry inquiry) {
        inquiryListRepository.save(inquiry);
    }

    @Override
    public String formatCreatedAt(Inquiry inquiry) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return inquiry.getCreatedAt().format(formatter);
    }
}
