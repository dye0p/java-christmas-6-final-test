package christmas.model;

import christmas.exception.ErrorMessage;

public class Date {

    private final int date;

    private Date(int date) {
        validate(date);

        this.date = date;
    }

    public static Date of(int date) {
        return new Date(date);
    }

    private void validate(int date) {
        if (date > 31 || date < 1) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_DATE.getErrorMessage());
        }
    }

}
