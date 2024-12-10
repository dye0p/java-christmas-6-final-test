package christmas.model.benefit;

import christmas.model.Date;
import christmas.model.Menu;
import christmas.model.Orders;

public class GiftDiscount implements DiscountStrategy {

    private static final String DISCOUNT_NAME = "증정 이벤트";

    @Override
    public String discountName() {
        return DISCOUNT_NAME;
    }

    @Override
    public int discount(Orders orders, Date date) {
        long discount = Menu.getGiftMenu();
        return (int) discount;
    }
}
