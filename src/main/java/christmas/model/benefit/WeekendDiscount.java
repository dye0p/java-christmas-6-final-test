package christmas.model.benefit;

import christmas.model.Date;
import christmas.model.Order;
import christmas.model.Orders;

public class WeekendDiscount implements DiscountStrategy {

    private static final String DISCOUNT_NAME = "주말 할인";

    @Override
    public String discountName() {
        return DISCOUNT_NAME;
    }

    @Override
    public int discount(Orders orders, Date date) {
        int discount = 0;
        if (date.isWeekend()) {
            //주말 할인(금요일, 토요일): 주말에는 메인 메뉴를 메뉴 1개당 2,023원 할인
            int mainQuantity = 0;
            for (Order order : orders.getOrders()) {
                mainQuantity += order.calculateMainQuantity();
            }
            discount = mainQuantity * 2023;
        }
        return discount;
    }
}
