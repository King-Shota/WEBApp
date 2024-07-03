package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 255)
    private String profileImage;

    @NotBlank
    @Size(max = 255)
    private String name;

    @NotBlank
    @Size(max = 255)
    @Pattern(regexp = "^[ぁ-ん]+$", message = "ひらがなのみ許可されています")
    private String furigana;

    @NotBlank
    @Pattern(regexp = "^(male|female|other)$", message = "性別は選択式です")
    private String gender;

    @NotNull
    @Max(999)
    private Integer age;

    @Size(max = 1500)
    private String introduction;

   
}
