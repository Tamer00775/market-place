package kz.halyk.finservice.test.MarketPlace.service.impl;


import kz.halyk.finservice.test.MarketPlace.converter.paymentDetails.PaymentDetailsDtoConverter;
import kz.halyk.finservice.test.MarketPlace.dto.paymentDetails.PaymentDetailsDto;
import kz.halyk.finservice.test.MarketPlace.entity.PaymentDetails;
import kz.halyk.finservice.test.MarketPlace.exception.MarketPlaceException;
import kz.halyk.finservice.test.MarketPlace.repository.PaymentDetailsRepository;
import kz.halyk.finservice.test.MarketPlace.service.PaymentDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PaymentDetailsServiceImpl implements PaymentDetailsService {

    private final PaymentDetailsRepository paymentDetailsRepository;

    private final PaymentDetailsDtoConverter paymentDetailsDtoConverter;
    @Override
    public PaymentDetailsDto findById(Long id) {
        return paymentDetailsRepository
                .findById(id)
                .map(paymentDetailsDtoConverter::convert)
                .orElseThrow(
                        () -> new MarketPlaceException(String.format("PaymentDetails with id %s not found", id),
                                LocalDateTime.now(),
                                HttpStatus.BAD_REQUEST));
    }

    @Override
    public List<PaymentDetailsDto> findAll() {
        return paymentDetailsRepository
                .findAll()
                .stream()
                .map(paymentDetailsDtoConverter::convert)
                .collect(Collectors.toList());
    }

    @Override
    public Page<PaymentDetailsDto> findAll(Pageable pageable) {
        return paymentDetailsRepository
                .findAll(pageable)
                .map(paymentDetailsDtoConverter::convert);
    }

    @Override
    public void deleteById(Long id) {
        paymentDetailsRepository.deleteById(id);
    }

    @Override
    public PaymentDetails create(PaymentDetailsDto dto) {
        PaymentDetails paymentDetails = new PaymentDetails();
        paymentDetails.setProductName(dto.getProductName());
        paymentDetails.setDescription(dto.getDescription());
        paymentDetails.setQuantity(dto.getQuantity());
        paymentDetails.setTotalPrice(dto.getTotalPrice());
        paymentDetails.setIsDeleted(dto.getIsDeleted());
        paymentDetails.setOrderDetails(dto.getOrderDetails());
        paymentDetails.setUserPayment(dto.getUserPayment());
        paymentDetails.setPaymentStatus(dto.getPaymentStatus());
        return paymentDetails;
    }

    public List<PaymentDetailsDto> paymentHistory() {
        return null;
    }
}
