package kz.halyk.finservice.test.MarketPlace.service;

import kz.halyk.finservice.test.MarketPlace.dto.cartItem.CartItemDto;
import kz.halyk.finservice.test.MarketPlace.entity.CartItem;
import kz.halyk.finservice.test.MarketPlace.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CartItemService {

    CartItem findById(Long id);

    List<CartItemDto> findAll() ;

    List<CartItemDto> findAllByUser(User user);

    Page<CartItemDto> findAll(Pageable pageable);

    void deleteById(Long id);

    CartItem create(CartItemDto dto);
}
