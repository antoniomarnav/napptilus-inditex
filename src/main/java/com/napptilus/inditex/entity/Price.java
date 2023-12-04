package com.napptilus.inditex.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "PRICES")
public class Price {
    @Id
    @Column(name = "PRICE_ID")
    private long priceId;
    @ManyToOne
    @JoinColumn(name = "BRAND_ID", nullable = false)
    private Brand brand;
    @Column(name = "START_DATE")
    private LocalDateTime startDate;
    @Column(name = "END_DATE")
    private LocalDateTime endDate;
    @Column(name = "PRICE_LIST")
    private int priceList;
    @Column(name = "PRODUCT_ID")
    private Long productId;
    @Column(name = "PRIORITY")
    private int priority;
    @Column(name = "PRICE")
    private Double price;
    @Column(name = "CURR")
    private String curr;
}