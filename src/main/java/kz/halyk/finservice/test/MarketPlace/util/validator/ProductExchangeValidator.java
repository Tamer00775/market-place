package kz.halyk.finservice.test.MarketPlace.util.validator;

import kz.halyk.finservice.test.MarketPlace.entity.CartItem;
import kz.halyk.finservice.test.MarketPlace.entity.Product;

public class ProductExchangeValidator {

    public static boolean isProductQuantityEnough(Product product, Integer quantity) {
        return product.getInventory().getQuantity() > quantity;
    }
    public static boolean isCartItemHasEnoughQuantity(CartItem cartItem) {
        return cartItem.getQuantity() >= 0;
    }

}
