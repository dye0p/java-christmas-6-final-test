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
}
