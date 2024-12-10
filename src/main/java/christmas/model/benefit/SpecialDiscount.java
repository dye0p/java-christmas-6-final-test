package christmas.model.benefit;

import christmas.model.Date;
import christmas.model.Orders;

public class SpecialDiscount implements DiscountStrategy {

    private static final String DISCOUNT_NAME = "특별 할인";

    @Override
    public String discountName() {
        return DISCOUNT_NAME;
    }

    @Override
    public int discount(Orders orders, Date date) {
        int discount = 0;
        if (date.isSpecialDay()) {
            //특별 할인: 이벤트 달력에 별이 있으면 총주문 금액에서 1,000원 할인
            discount = 1000;
        }
        return discount;
    }
}
