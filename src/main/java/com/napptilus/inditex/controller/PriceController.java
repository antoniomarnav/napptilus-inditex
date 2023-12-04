package com.napptilus.inditex.controller;

import com.napptilus.inditex.controller.model.PriceResponse;
import com.napptilus.inditex.service.PriceService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class PriceController {

    private final PriceService priceService;

    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }

    @GetMapping("/price")
    public ResponseEntity<PriceResponse> getFinalPrice(@RequestParam("applicationDate")
                                                           @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime applicationDate,
                                                       @RequestParam("productId") Long productId,
                                                       @RequestParam("brandId") Long brandId){
        try {
            PriceResponse priceResponse = priceService.getFinalPrice(applicationDate, productId, brandId);
            if(priceResponse == null){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return ResponseEntity.ok(priceResponse);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
