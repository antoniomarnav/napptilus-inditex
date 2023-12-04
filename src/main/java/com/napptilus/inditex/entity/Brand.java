package com.napptilus.inditex.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import jakarta.persistence.Column;

@Data
@Entity
@Table(name = "BRANDS")
public class Brand {
    @Id
    @Column(name = "BRAND_ID")
    private Long id;
    @Column(name = "BRAND_NAME")
    private String name;
}