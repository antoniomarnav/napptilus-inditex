package com.napptilus.inditex.service.implement;

import com.napptilus.inditex.entity.Price;
import com.napptilus.inditex.controller.model.PriceResponse;
import com.napptilus.inditex.repository.PriceRepository;
import com.napptilus.inditex.service.PriceService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PriceServiceImpl implements PriceService {

    private final PriceRepository priceRepository;

    public PriceServiceImpl(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    /**
     * We obtain the final price to apply based on the entered parameters.
     * @return PriceResponse
     */
    @Override
    public PriceResponse getFinalPrice(LocalDateTime applicationDate, Long productId, Long brandId) {
        Price price = priceRepository.findFirstByProductIdAndBrandIdAndStartDateBeforeAndEndDateAfterOrderByPriorityDesc(
                productId, brandId, applicationDate, applicationDate);

        if (price == null) {
            return null;
        }
        return priceToResponse(price);
    }

    /**
     * We construct the response for the prices.
     * @return PriceResponse
     */
    private PriceResponse priceToResponse(Price price) {
        PriceResponse priceResponse = new PriceResponse();
        priceResponse.setBrandId(price.getBrand().getId());
        priceResponse.setPrice(price.getPrice());
        priceResponse.setPriceList(price.getPriceList());
        priceResponse.setProductId(price.getProductId());
        priceResponse.setStartDate(price.getStartDate());
        priceResponse.setEndDate(price.getEndDate());
        return priceResponse;
    }
}
