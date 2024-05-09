package kz.halyk.finservice.test.MarketPlace.service;


import kz.halyk.finservice.test.MarketPlace.converter.user.UserConverter;
import kz.halyk.finservice.test.MarketPlace.converter.user.UserDtoConverter;
import kz.halyk.finservice.test.MarketPlace.dto.orderDetails.OrderDetailsDto;
import kz.halyk.finservice.test.MarketPlace.dto.user.UserDto;
import kz.halyk.finservice.test.MarketPlace.entity.User;
import kz.halyk.finservice.test.MarketPlace.utils.SecurityUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.security.SecurityUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@Slf4j
@AllArgsConstructor
public class UserOrdersHistoryService {

    private final UserDtoConverter userDtoConverter;
    private final OrderDetailsService orderDetailsService;

    public List<OrderDetailsDto> orderHistory() {
        User user = Objects.requireNonNull(SecurityUtils.getCurrentPerson());
        return orderDetailsService
                .findAllByUser(user);
    }
}
