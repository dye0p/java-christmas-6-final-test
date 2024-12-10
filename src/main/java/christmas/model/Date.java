package christmas.model;

import christmas.exception.ErrorMessage;
import java.time.LocalDate;

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

    public boolean canChristmasEvent() {
        return date > 1 && date < 25;
    }

    public boolean isWeekday() {
        LocalDate localDate = LocalDate.of(2023, 12, this.date);
        int dayOfWeekValue = localDate.getDayOfWeek().getValue();

        return (dayOfWeekValue != 5 && dayOfWeekValue != 6);
    }

    public boolean isWeekend() {
        return !isWeekday();
    }

    public boolean isSpecialDay() {
        LocalDate localDate = LocalDate.of(2023, 12, this.date);
        int dayOfMonthValue = localDate.getDayOfMonth();

        return dayOfMonthValue == 3 || dayOfMonthValue == 10 || dayOfMonthValue == 17 || dayOfMonthValue == 24 ||
                dayOfMonthValue == 25 || dayOfMonthValue == 31;
    }

    public int getDate() {
        return date;
    }
}
