package christmas.view;

import christmas.model.Orders;

public class OutputView {

    public static final String NEXT_LINE = System.lineSeparator();

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
        System.out.println(ordersFormat);
    }

    public void printBeforeDiscountTotalPrice(int beforeTotalPrice) {
        System.out.println(NEXT_LINE + "<할인 전 총주문 금액>");
        String format = String.format("%,d원", beforeTotalPrice);
        System.out.println(format);
    }

    public void printNonEventResult(int beforeTotalPrice) {
        System.out.println(NEXT_LINE + "<증정 메뉴>");
        System.out.println("없음");

        System.out.println(NEXT_LINE + "<혜택 내역>");
        System.out.println("없음");

        System.out.println(NEXT_LINE + "<총혜택 금액>");
        System.out.println("0원");

        System.out.println(NEXT_LINE + "<할인 후 예상 결제 금액>");
        String format = String.format("%,d원", beforeTotalPrice);
        System.out.println(format);

        System.out.println(NEXT_LINE + "<12월 이벤트 배지>");
        System.out.println("없음");
    }

    public void printGiftMenu(String giftEvent) {
        System.out.println(NEXT_LINE + "<증정 메뉴>");
        System.out.println(giftEvent + " 1개");
    }

    public void printBenefitResult(int christmasDiscount, int weekdayDiscount, int weekendDiscount, int specialDiscount,
                                   int giftDiscount) {

        String benefitResult = OutputFormatter.formatBenefitResult(christmasDiscount, weekdayDiscount, weekendDiscount,
                specialDiscount,
                giftDiscount);

        System.out.println(NEXT_LINE + "<혜택 내역>");
        System.out.println(benefitResult);
    }
}
