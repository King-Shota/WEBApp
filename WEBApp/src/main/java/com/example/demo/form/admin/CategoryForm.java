package com.example.demo.form.admin;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CategoryForm {

    @NotNull
    @Size(max = 255)
    private String name;

    // Getters and setters
}