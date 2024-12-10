package christmas.model.benefit;

import christmas.model.Date;
import christmas.model.Orders;

public class ChristmasDiscount implements DiscountStrategy {

    private static final String DISCOUNT_NAME = "크리스마스 디데이 할인";

    @Override
    public String discountName() {
        return DISCOUNT_NAME;
    }

    @Override
    public int discount(Orders orders, Date date) {
        int discount = 0;
        if (date.canChristmasEvent()) {
            //1,000원으로 시작하여 크리스마스가 다가올수록 날마다 할인 금액이 100원씩 증가
            discount += 1000;
            for (int i = 1; i < date.getDate(); i++) {
                discount += 100;
            }
        }
        return discount;
    }
}
