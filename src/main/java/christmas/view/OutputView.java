package christmas.view;

import christmas.model.Orders;

public class OutputView {

    public void wellComeMessage() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }

    public void printErrorMessage(String message) {
        System.out.println(message);
    }

    public void printEventPreviewMessage(int date) {
        String format = String.format("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!", date);
        System.out.println(format);
    }

    public void printOrderMenus(Orders orders) {
        String ordersFormat = OutputFormatter.formatOrders(orders);
        System.out.print(ordersFormat);
    }
}
