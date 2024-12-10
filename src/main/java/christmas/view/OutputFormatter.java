package christmas.view;

import christmas.model.Order;
import christmas.model.Orders;
import java.util.Map;
import java.util.Map.Entry;
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

    public static String formatBenefitResult(Map<String, Integer> benefitResult) {
        StringJoiner sj = new StringJoiner(NEXT_LINE);

        for (Entry<String, Integer> stringIntegerEntry : benefitResult.entrySet()) {
            if (stringIntegerEntry.getValue() != 0) {
                String format = String.format("%s: -%,d원", stringIntegerEntry.getKey(), stringIntegerEntry.getValue());
                sj.add(format);
            }
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
