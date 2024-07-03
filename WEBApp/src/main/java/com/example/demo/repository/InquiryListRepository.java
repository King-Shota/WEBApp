package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Inquiry;

public interface InquiryListRepository extends JpaRepository<Inquiry, Long> {
}
