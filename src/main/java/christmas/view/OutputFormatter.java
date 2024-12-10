package christmas.view;

import christmas.model.Order;
import christmas.model.Orders;
import java.util.StringJoiner;

public class OutputFormatter {

    public static final String NEXT_LINE = System.lineSeparator();

    public static String formatOrders(Orders orders) {
        System.out.println(NEXT_LINE + "<주문 메뉴>");

        StringJoiner sj = new StringJoiner(NEXT_LINE);
        for (Order order : orders.getOrders()) {
            String format = String.format("%s %d개", order.getMenu().getName(), order.getQuantity());
            sj.add(format);
        }
        return sj.toString();
    }

    public static String formatBenefitResult(int christmasDiscount, int weekdayDiscount, int weekendDiscount,
                                             int specialDiscount, int giftDiscount) {
        StringJoiner sj = new StringJoiner(NEXT_LINE);
        if (christmasDiscount != 0) {
            String format = String.format("크리스마스 디데이 할인: -%,d원", christmasDiscount);
            sj.add(format);
        }
        if (weekdayDiscount != 0) {
            String format = String.format("평일 할인: -%,d원", weekdayDiscount);
            sj.add(format);
        }
        if (weekendDiscount != 0) {
            String format = String.format("주말 할인: -%,d원", weekendDiscount);
            sj.add(format);
        }
        if (specialDiscount != 0) {
            String format = String.format("특별 할인: -%,d원", specialDiscount);
            sj.add(format);
        }
        if (giftDiscount != 0) {
            String format = String.format("증정 이벤트: -%,d원", giftDiscount);
            sj.add(format);
        }

        return sj.toString();
    }

    public static String formatTotalBenefitPrice(int totalBenefitPrice) {
        String totalBenefitResult = "0원";
        if (totalBenefitPrice != 0) {
            totalBenefitResult = String.format("-%,d원", totalBenefitPrice);
        }
        return totalBenefitResult;
    }
}
