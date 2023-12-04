package com.napptilus.inditex.controller.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PriceResponse {
    private Long productId;
    private Long brandId;
    private int priceList;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Double price;
}