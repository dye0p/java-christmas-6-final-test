package christmas.controller;

import christmas.exception.ErrorMessage;
import christmas.model.Date;
import christmas.model.Menu;
import christmas.model.Order;
import christmas.model.Orders;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class EventController {

    private final InputView inputView;
    private final OutputView outputView;

    public EventController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        //웰컴
        outputView.wellComeMessage();
        //식당 방문 날짜
        Date date = tryReadDate();
        //주문
        Orders orders = tryReadOrder();

        //주문혜택 미리보기 안내
        outputView.printEventPreviewMessage(date.getDate());

        //주문 메뉴 출력
        outputView.printOrderMenus(orders);

        //할인 전 총 주문 금액 출력
        //할인 전 총 주문 금액 계산
        int beforeTotalPrice = orders.calculateBeforeDiscountTotalPrice();
        outputView.printBeforeDiscountTotalPrice(beforeTotalPrice);

        //이벤트에 참여할 수 없을 때
        if (!orders.isPossibleEvent()) {
            outputView.printNonEventResult(beforeTotalPrice);
            return;
        }
        //이벤트 참여 가능
        //증정 메뉴 (총 주문 금액이 12만원 이상인 경우)
        String giftEvent = orders.canGiftMenu();
        outputView.printGiftMenu(giftEvent);

        //혜택 내역
        //크리스마스 디데이 할인
        int christmasDiscount = orders.canChristmasEvent(date);
        //평일 할인
        int weekdayDiscount = orders.canWeekdayEvent(date);

        //주말 할인
        int weekendDiscount = orders.canWeekendEvent(date);

        //특별 할인
        int specialDiscount = orders.canSpecialEvent(date);

        //증정 이벤트
        int giftDiscount = orders.canGiftEvent();

        outputView.printBenefitResult(christmasDiscount, weekdayDiscount, weekendDiscount, specialDiscount,
                giftDiscount);

        //총혜택 금액 = 할인 금액의 합계 + 증정 메뉴의 가격
        //할인 금액
        int totalDiscount = christmasDiscount + weekdayDiscount + weekendDiscount + specialDiscount;
        int totalBenefitPrice = totalDiscount + giftDiscount;

        outputView.printTotalBenefitPrice(totalBenefitPrice);

        //할인 후 예상 결제 금액 = 할인 전 총주문 금액 - 할인 금액
        int afterDiscountPrice = beforeTotalPrice - totalDiscount;

        //12월 이벤트 배지
        String badge = orders.getBadge(totalBenefitPrice);


    }

    private Date tryReadDate() {
        return requestRead(() -> {
            int date = inputView.readDate();
            return Date.of(date);
        });
    }

    private Orders tryReadOrder() {
        return requestRead(() -> {
            List<String[]> menuAndQuantities = inputView.readOrder();

            List<Order> orders = new ArrayList<>();
            for (String[] menuAndQuantity : menuAndQuantities) {
                Menu menu = Menu.of(menuAndQuantity[0]);
                int quantity = parseQuantity(menuAndQuantity);
                Order order = new Order(menu, quantity);

                orders.add(order);
            }

            return Orders.from(orders);
        });
    }

    private int parseQuantity(String[] menuAndQuantity) {
        try {
            return Integer.parseInt(menuAndQuantity[1]);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER.getErrorMessage());
        }
    }

    private <T> T requestRead(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException exception) {
                outputView.printErrorMessage(exception.getMessage());
            }
        }
    }
}
