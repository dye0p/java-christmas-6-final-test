package christmas.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.exception.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DateTest {

    @DisplayName("날짜가 1일 보다 작고, 31일 보다 크다면 예외가 발생한다.")
    @Test
    void dateValidate() {
        //given
        int date = 0;

        //when //then
        assertThatThrownBy(() -> Date.of(date))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INVALID_DATE.getErrorMessage());
    }

    @DisplayName("크리스마스 기간이라면 true를 반환한다.")
    @Test
    void canChristmasEvent() {
        //given
        Date date = Date.of(25);

        //when
        boolean result = date.canChristmasEvent();

        //then
        assertThat(result).isTrue();
    }

    @DisplayName("평일이라면 true를 반환한다.")
    @Test
    void isWeekday() {
        //given
        Date date = Date.of(4);

        //when
        boolean result = date.isWeekday();

        //then
        assertThat(result).isTrue();
    }

    @DisplayName("주말이라면 true를 반환한다.")
    @Test
    void isWeekend() {
        //given
        Date date = Date.of(2);

        //when
        boolean result = date.isWeekend();

        //then
        assertThat(result).isTrue();
    }

    @DisplayName("이벤트 달력에 별이 있다면 true를 반환한다.")
    @Test
    void isSpecialDay() {
        //given
        Date date = Date.of(3);

        //when
        boolean result = date.isSpecialDay();

        //then
        assertThat(result).isTrue();
    }
}
