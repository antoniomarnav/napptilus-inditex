package com.napptilus.inditex.controller;

import com.napptilus.inditex.controller.model.PriceResponse;
import com.napptilus.inditex.service.PriceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(PriceController.class)
class PriceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PriceService priceService;

    private static final Long brandId = 1L;
    private static final Long productId = 35455L;

    @Test
    void when_request_at_10am_on_the_14th_day() throws Exception {
        LocalDateTime applicationDate = LocalDateTime.parse("2020-06-14 10:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        // Mock the service response
        PriceResponse mockResponse = new PriceResponse();
        mockResponse.setBrandId(brandId);
        mockResponse.setPrice(35.50);
        mockResponse.setPriceList(1);
        mockResponse.setProductId(productId);
        mockResponse.setStartDate(LocalDateTime.parse("2020-06-14 00:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        mockResponse.setEndDate(LocalDateTime.parse("2020-12-31 23:59:59", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        when(priceService.getFinalPrice(applicationDate, productId, brandId)).thenReturn(mockResponse);

        //Get
        mockMvc.perform(get("/price")
                        .param("applicationDate", applicationDate.toString())
                        .param("productId", productId.toString())
                        .param("brandId", brandId.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("productId").value(35455))
                .andExpect(jsonPath("brandId").value(1))
                .andExpect(jsonPath("priceList").value(1))
                .andExpect(jsonPath("startDate").value("2020-06-14T00:00:00"))
                .andExpect(jsonPath("endDate").value("2020-12-31T23:59:59"))
                .andExpect(jsonPath("price").value(35.5));
    }

    @Test
    void when_request_at_4pm_on_the_14th_day() throws Exception {
        LocalDateTime applicationDate = LocalDateTime.parse("2020-06-14 16:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        // Mock the service response
        PriceResponse mockResponse = new PriceResponse();
        mockResponse.setBrandId(brandId);
        mockResponse.setPrice(25.45);
        mockResponse.setPriceList(2);
        mockResponse.setProductId(productId);
        mockResponse.setStartDate(LocalDateTime.parse("2020-06-14 15:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        mockResponse.setEndDate(LocalDateTime.parse("2020-06-14 18:30:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        when(priceService.getFinalPrice(applicationDate, productId, brandId)).thenReturn(mockResponse);

        // Get
        mockMvc.perform(get("/price")
                        .param("applicationDate", applicationDate.toString())
                        .param("productId", productId.toString())
                        .param("brandId", brandId.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("productId").value(35455))
                .andExpect(jsonPath("brandId").value(1))
                .andExpect(jsonPath("priceList").value(2))
                .andExpect(jsonPath("startDate").value("2020-06-14T15:00:00"))
                .andExpect(jsonPath("endDate").value("2020-06-14T18:30:00"))
                .andExpect(jsonPath("price").value(25.45));
    }

    @Test
    void when_request_at_9pm_on_the_14th_day() throws Exception {
        // Given
        LocalDateTime applicationDate = LocalDateTime.parse("2020-06-14 21:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        // Mock the service response
        PriceResponse mockResponse = new PriceResponse();
        mockResponse.setBrandId(brandId);
        mockResponse.setPrice(35.5);
        mockResponse.setPriceList(1);
        mockResponse.setProductId(productId);
        mockResponse.setStartDate(LocalDateTime.parse("2020-06-14 00:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        mockResponse.setEndDate(LocalDateTime.parse("2020-12-31 23:59:59", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        when(priceService.getFinalPrice(applicationDate, productId, brandId)).thenReturn(mockResponse);

        // When/Then
        mockMvc.perform(get("/price")
                        .param("applicationDate", applicationDate.toString())
                        .param("productId", productId.toString())
                        .param("brandId", brandId.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("productId").value(35455))
                .andExpect(jsonPath("brandId").value(1))
                .andExpect(jsonPath("priceList").value(1))
                .andExpect(jsonPath("startDate").value("2020-06-14T00:00:00"))
                .andExpect(jsonPath("endDate").value("2020-12-31T23:59:59"))
                .andExpect(jsonPath("price").value(35.5));
    }

    @Test
    void when_request_at_10am_on_the_15th_day() throws Exception {
        // Given
        LocalDateTime applicationDate = LocalDateTime.parse("2020-06-15 10:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        // Mock the service response
        PriceResponse mockResponse = new PriceResponse();
        mockResponse.setBrandId(brandId);
        mockResponse.setPrice(30.50);
        mockResponse.setPriceList(3);
        mockResponse.setProductId(productId);
        mockResponse.setStartDate(LocalDateTime.parse("2020-06-15 00:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        mockResponse.setEndDate(LocalDateTime.parse("2020-06-15 11:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        when(priceService.getFinalPrice(applicationDate, productId, brandId)).thenReturn(mockResponse);

        // When/Then
        mockMvc.perform(get("/price")
                        .param("applicationDate", applicationDate.toString())
                        .param("productId", productId.toString())
                        .param("brandId", brandId.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("productId").value(35455))
                .andExpect(jsonPath("brandId").value(1))
                .andExpect(jsonPath("priceList").value(3))
                .andExpect(jsonPath("startDate").value("2020-06-15T00:00:00"))
                .andExpect(jsonPath("endDate").value("2020-06-15T11:00:00"))
                .andExpect(jsonPath("price").value(30.5));
    }

    @Test
    void when_request_at_9pm_on_the_16th_day() throws Exception {
        // Given
        LocalDateTime applicationDate = LocalDateTime.parse("2020-06-16 21:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        // Mock the service response
        PriceResponse mockResponse = new PriceResponse();
        mockResponse.setBrandId(brandId);
        mockResponse.setPrice(38.95);
        mockResponse.setPriceList(4);
        mockResponse.setProductId(productId);
        mockResponse.setStartDate(LocalDateTime.parse("2020-06-15 16:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        mockResponse.setEndDate(LocalDateTime.parse("2020-12-31 23:59:59", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        when(priceService.getFinalPrice(applicationDate, productId, brandId)).thenReturn(mockResponse);

        // When/Then
        mockMvc.perform(get("/price")
                        .param("applicationDate", applicationDate.toString())
                        .param("productId", productId.toString())
                        .param("brandId", brandId.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("productId").value(35455))
                .andExpect(jsonPath("brandId").value(1))
                .andExpect(jsonPath("priceList").value(4))
                .andExpect(jsonPath("startDate").value("2020-06-15T16:00:00"))
                .andExpect(jsonPath("endDate").value("2020-12-31T23:59:59"))
                .andExpect(jsonPath("price").value(38.95));
    }
}