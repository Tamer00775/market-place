package kz.halyk.finservice.test.MarketPlace.service.impl;

import kz.halyk.finservice.test.MarketPlace.dto.notifyServices.MessageDto;
import kz.halyk.finservice.test.MarketPlace.entity.Product;
import kz.halyk.finservice.test.MarketPlace.entity.User;
import kz.halyk.finservice.test.MarketPlace.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationServiceImpl implements NotificationService {
    private final MessageService messageService;
    private static final String HEADER = "Только для вас: Уникальная скидка на %s!";

    @Override
    public void notifySubscribersAboutSaleProduct(Product product, boolean isSale) {
        log.info("Start notify users of product id {}", product.getId());
        Set<User> subscribedUsers = product.getSubscribedUsers();
        if (subscribedUsers.isEmpty()) {
            log.info("SubscribedUsers is Empty");
            return;
        }
        if (isSale) {
            subscribedUsers.forEach(user -> {
                MessageDto messageDto = new MessageDto();
                messageDto.setSendTo(user.getEmail());
                constructMessageDto(messageDto, product, user);
                messageService.sendMessage(messageDto);
            });
        }
    }

    private void constructMessageDto(MessageDto messageDto, Product product, User user) {
        messageDto.setMessageDate(LocalDateTime.now());
        messageDto.setHeader(String.format(HEADER, product.getProductName()));
        messageDto.setMessage(String.format("Уважаемый " + user.getFirstName() + ", только для Вас! Уникальная скидка на продукт " +
                " %s . Успейте заказать на сайте MyMarketPlace.com ", product.getProductName()));
    }
}
