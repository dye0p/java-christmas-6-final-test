package christmas.model.benefit;

import christmas.model.Date;
import christmas.model.Order;
import christmas.model.Orders;

public class WeekdayDiscount implements DiscountStrategy {

    private static final String DISCOUNT_NAME = "평일 할인";

    @Override
    public String discountName() {
        return DISCOUNT_NAME;
    }

    @Override
    public int discount(Orders orders, Date date) {
        int discount = 0;
        if (date.isWeekday()) {
            //평일 할인(일요일~목요일): 평일에는 디저트 메뉴를 메뉴 1개당 2,023원 할인
            int dessertQuantity = 0;
            for (Order order : orders.getOrders()) {
                dessertQuantity += order.calculateDessertQuantity();
            }
            discount = dessertQuantity * 2023;
        }
        return discount;
    }
}
