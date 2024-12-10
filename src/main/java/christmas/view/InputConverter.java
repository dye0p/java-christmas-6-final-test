package christmas.view;

import christmas.exception.ErrorMessage;

public class InputConverter {

    public static int convertDate(String date) {
        validate(date);

        return parseDate(date);
    }

    private static void validate(String date) {
        if (date.contains(" ") || date.isEmpty()) {
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
