package christmas.view;

import christmas.exception.ErrorMessage;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.PatternSyntaxException;

public class InputConverter {

    public static int convertDate(String date) {
        validate(date);

        return parseDate(date);
    }

    public static List<String[]> convertOrder(String order) {
        validateOrder(order);
        return splitOrder(order);
    }

    private static List<String[]> splitOrder(String inputOrders) {
        try {
            String[] splitOrder = inputOrders.trim().split(",");

            List<String[]> orders = new ArrayList<>();
            for (String order : splitOrder) {
                String[] menuAndQuantity = order.split("-");

                orders.add(menuAndQuantity);
            }

            return orders;
        } catch (PatternSyntaxException exception) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER.getErrorMessage());
        }
    }

    private static void validateOrder(String input) {
        if (input.contains(" ") || input.isEmpty()) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER.getErrorMessage());
        }
    }


    private static void validate(String input) {
        if (input.contains(" ") || input.isEmpty()) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_DATE.getErrorMessage());
        }
    }

    private static int parseDate(String date) {
        try {
            return Integer.parseInt(date.trim());
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_DATE.getErrorMessage());
        }
    }
}
