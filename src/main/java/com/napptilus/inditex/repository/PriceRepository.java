package com.napptilus.inditex.repository;

import com.napptilus.inditex.entity.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {
    Price findFirstByProductIdAndBrandIdAndStartDateBeforeAndEndDateAfterOrderByPriorityDesc(
            Long ProductId, Long BrandId, LocalDateTime StartDate, LocalDateTime EndDate);
}
