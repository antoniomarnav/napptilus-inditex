package com.napptilus.inditex.service;

import com.napptilus.inditex.controller.model.PriceResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public interface PriceService {
    PriceResponse getFinalPrice(LocalDateTime applicationDate, Long productId, Long brandId);
}
