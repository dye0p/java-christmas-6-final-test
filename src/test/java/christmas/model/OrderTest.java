package christmas.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.exception.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderTest {

    @DisplayName("주문 개수가 1개 미만이라면 예외를 발생한다.")
    @Test
    void validateQuantity() {
        //given
        Menu menu = Menu.MUSHROOM_SOUP;
        int quantity = 0;

        //when //then
        assertThatThrownBy(() -> new Order(menu, quantity))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INVALID_ORDER.getErrorMessage());
    }

    @DisplayName("주문한 디저트 개수를 계산한다.")
    @Test
    void calculateDessertQuantity() {
        //given
        Menu menu = Menu.ICE_CREAM;
        int quantity = 5;
        Order order = new Order(menu, quantity);

        //when
        int result = order.calculateDessertQuantity();

        //then
        assertThat(result).isEqualTo(5);
    }

    @DisplayName("주문한 메인 메뉴 개수를 계산한다.")
    @Test
    void calculateMainQuantity() {
        //given
        Menu menu = Menu.CHRISTMAS_PASTA;
        int quantity = 5;
        Order order = new Order(menu, quantity);

        //when
        int result = order.calculateMainQuantity();

        //then
        assertThat(result).isEqualTo(5);
    }
}
