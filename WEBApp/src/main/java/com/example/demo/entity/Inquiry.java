package com.example.demo.entity;

import java.time.LocalDateTime;

import org.springframework.data.relational.core.mapping.Table;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
@Table(name = "inquiry")
public class Inquiry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String furigana;
    private String email;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    private String content;

    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "status", nullable = false, columnDefinition = "VARCHAR(50) DEFAULT '未対応'")
    private String status;

    public Inquiry() {
        this.createdAt = LocalDateTime.now();
        this.status = "未対応";
    }
}
