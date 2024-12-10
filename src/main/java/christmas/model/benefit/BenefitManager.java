package christmas.model.benefit;

import christmas.model.Date;
import christmas.model.Orders;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BenefitManager {

    private final List<DiscountStrategy> discountStrategies;

    public BenefitManager() {
        this.discountStrategies = List.of(
                new ChristmasDiscount(),
                new WeekdayDiscount(),
                new WeekendDiscount(),
                new SpecialDiscount(),
                new GiftDiscount()
        );
    }

    public Map<String, Integer> calculateBenefit(Orders orders, Date date) {
        Map<String, Integer> benefitResultMap = new HashMap<>();
        for (DiscountStrategy strategy : discountStrategies) {
            String discountName = strategy.discountName();
            int discount = strategy.discount(orders, date);

            benefitResultMap.put(discountName, discount);
        }
        return benefitResultMap;
    }
}
