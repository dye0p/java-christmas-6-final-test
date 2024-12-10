package christmas.model.benefit;

import christmas.model.Date;
import christmas.model.Orders;

public interface DiscountStrategy {

    String discountName();

    int discount(Orders orders, Date date);
}
