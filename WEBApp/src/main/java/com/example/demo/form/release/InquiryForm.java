package com.example.demo.form.release;

import lombok.Data;

@Data
public class InquiryForm {
    private String name;
    private String furigana;
    private String email;
    private Long categoryId;
    private String content;
}
