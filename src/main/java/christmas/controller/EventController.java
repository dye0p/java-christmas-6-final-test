package christmas.controller;

import christmas.exception.ErrorMessage;
import christmas.model.BenefitResult;
import christmas.model.Date;
import christmas.model.EventPlanner;
import christmas.model.Menu;
import christmas.model.Order;
import christmas.model.Orders;
import christmas.model.benefit.BenefitManager;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class EventController {

    private final InputView inputView;
    private final OutputView outputView;
    private EventPlanner eventPlanner;

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
        eventPlanner = new EventPlanner(date, orders, new BenefitManager());

        //주문혜택 미리보기 안내
        outputView.printEventPreviewMessage(date.getDate());

        //주문 메뉴 출력
        outputView.printOrderMenus(orders);

        //할인 전 총 주문 금액 출력
        //할인 전 총 주문 금액 계산
        int beforeTotalPrice = eventPlanner.getBeforeTotalPrice();
        outputView.printBeforeDiscountTotalPrice(beforeTotalPrice);

        playEvent(beforeTotalPrice);
    }

    private void playEvent(int beforeTotalPrice) {
        if (!eventPlanner.isPossibleEvent()) {
            outputView.printNonEventResult(beforeTotalPrice);
            return;
        }
        runEvent(beforeTotalPrice);
    }

    private void runEvent(int beforeTotalPrice) {
        //이벤트 참여 가능
        //증정 메뉴 (총 주문 금액이 12만원 이상인 경우)
        String giftEvent = eventPlanner.canGiftMenu();
        outputView.printGiftMenu(giftEvent);

        //혜택 내역
        BenefitResult benefitResult = displayBenefitResult(eventPlanner);

        //할인 금액
        displayBenefitDiscountPrice(beforeTotalPrice, benefitResult);

        //12월 이벤트 배지
        int totalBenefitPrice = benefitResult.calculateTotalBenefitPrice();
        displayEventBadge(totalBenefitPrice);
    }

    private BenefitResult displayBenefitResult(EventPlanner eventPlanner) {
        Map<String, Integer> benefitResult = eventPlanner.getBenefitResult();
        outputView.printBenefitResult(benefitResult);
        return new BenefitResult(benefitResult);
    }

    private void displayBenefitDiscountPrice(int beforeTotalPrice, BenefitResult benefitResult) {
        //총혜택 금액 = 할인 금액의 합계 + 증정 메뉴의 가격
        int totalBenefitPrice = benefitResult.calculateTotalBenefitPrice();
        outputView.printTotalBenefitPrice(totalBenefitPrice);

        //할인 후 예상 결제 금액 = 할인 전 총주문 금액 - 할인 금액
        int afterDiscountPrice = benefitResult.calculateAfterDiscountPrice(beforeTotalPrice);
        outputView.printAfterDiscountPrice(afterDiscountPrice);
    }

    private void displayEventBadge(int totalBenefitPrice) {
        String badge = eventPlanner.getBadge(totalBenefitPrice);
        outputView.printBadge(badge);
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
