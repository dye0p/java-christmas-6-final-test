package christmas.model;

import christmas.model.benefit.BenefitManager;
import java.util.Map;

public class EventPlanner {

    private final Date date;
    private final Orders orders;
    private final BenefitManager benefitManager;

    public EventPlanner(Date date, Orders orders, BenefitManager benefitManager) {
        this.date = date;
        this.orders = orders;
        this.benefitManager = benefitManager;
    }

    public Map<String, Integer> getBenefitResult() {
        return benefitManager.calculateBenefit(orders, date);
    }

    public String canGiftMenu() {
        String gift = "없음";
        int totalPrice = orders.calculateBeforeDiscountTotalPrice();
        if (totalPrice >= 120000) {
            gift = Menu.CHAMPAGNE.getName();
        }
        return gift;
    }

    public String getBadge(int totalBenefitPrice) {
        String badge = "없음";

        if (totalBenefitPrice >= 5000 && totalBenefitPrice < 10000) {
            badge = "별";
        }
        if (totalBenefitPrice >= 10000 && totalBenefitPrice < 20000) {
            badge = "트리";
        }
        if (totalBenefitPrice >= 20000) {
            badge = "산타";
        }

        return badge;
    }

    public boolean isPossibleEvent() {
        int totalPrice = orders.calculateBeforeDiscountTotalPrice();
        return totalPrice >= 10000;
    }

    public int getBeforeTotalPrice() {
        return orders.calculateBeforeDiscountTotalPrice();
    }
}
