package kz.halyk.finservice.test.MarketPlace.service;

import kz.halyk.finservice.test.MarketPlace.dto.cartItem.CartItemDto;
import kz.halyk.finservice.test.MarketPlace.dto.paymentDetails.PaymentDetailsDto;
import kz.halyk.finservice.test.MarketPlace.entity.PaymentDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PaymentDetailsService {

    PaymentDetailsDto findById(Long id);


    List<PaymentDetailsDto> findAll();

    Page<PaymentDetailsDto> findAll(Pageable pageable);

    void deleteById(Long id);

    PaymentDetails create(PaymentDetailsDto dto);

}
