package kz.halyk.finservice.test.MarketPlace.service;

import kz.halyk.finservice.test.MarketPlace.entity.Product;
import org.springframework.stereotype.Component;

@Component
public interface NotificationService {
    void notifySubscribersAboutSaleProduct(Product product, boolean isSale);
}
