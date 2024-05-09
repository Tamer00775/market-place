package kz.halyk.finservice.test.MarketPlace.service;

import kz.halyk.finservice.test.MarketPlace.dto.cartItem.CartItemDto;
import kz.halyk.finservice.test.MarketPlace.dto.userPayment.UserPaymentDto;
import kz.halyk.finservice.test.MarketPlace.entity.User;
import kz.halyk.finservice.test.MarketPlace.entity.UserPayment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UserPaymentService {

    UserPaymentDto findById(Long id);

    UserPayment findByUser(User user);

    List<UserPaymentDto> findAll() ;

    Page<UserPaymentDto> findAll(Pageable pageable);

    void deleteById(Long id);

    UserPayment create(UserPaymentDto dto);
}
