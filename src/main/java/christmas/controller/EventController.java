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
