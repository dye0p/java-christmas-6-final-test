package christmas.model;

import christmas.exception.ErrorMessage;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Orders {

    private final List<Order> orders;

    private Orders(List<Order> orders) {
        validateDuplicate(orders);
        validateOnlyDrink(orders);
        validateTotalQuantitySize(orders);
        this.orders = orders;
    }

    private void validateOnlyDrink(List<Order> orders) {
        int drinkCount = calculateDrinkSize(orders);
        validateDrinkSize(orders, drinkCount);
    }

    private int calculateDrinkSize(List<Order> orders) {
        int drinkCount = 0;
        for (Order order : orders) {
            if (!Menu.isDrinkType(order)) {
                drinkCount += 1;
            }
        }
        return drinkCount;
    }

    private void validateDrinkSize(List<Order> orders, int drinkCount) {
        if (drinkCount == orders.size()) {
            throw new IllegalArgumentException(ErrorMessage.NOT_ONLY_DRINK.getErrorMessage());
        }
    }

    public static Orders from(List<Order> orders) {
        return new Orders(orders);
    }

    private void validateTotalQuantitySize(List<Order> orders) {
        int totalQuantity = calculateTotalQuantity(orders);
        validateSize(totalQuantity);
    }

    private void validateSize(int totalQuantity) {
        if (totalQuantity > 20) {
            throw new IllegalArgumentException(ErrorMessage.OUT_OF_RANGE_QUANTITY.getErrorMessage());
        }
    }

    private int calculateTotalQuantity(List<Order> orders) {
        int totalQuantity = 0;
        for (Order order : orders) {
            totalQuantity += order.getQuantity();
        }
        return totalQuantity;
    }

    private void validateDuplicate(List<Order> orders) {
        HashSet<Order> nonDuplicateOrderSet = new HashSet<>(orders);
        if (nonDuplicateOrderSet.size() != orders.size()) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER.getErrorMessage());
        }
    }

    public List<Order> getOrders() {
        return new ArrayList<>(orders);
    }

    public int calculateBeforeDiscountTotalPrice() {
        int totalPrice = 0;
        for (Order order : orders) {
            int quantity = order.getQuantity();
            totalPrice += (int) (order.getMenu().getPrice() * quantity);
        }
        return totalPrice;
    }

    public boolean isPossibleEvent() {
        int totalPrice = calculateBeforeDiscountTotalPrice();

        return totalPrice >= 10000;
    }

    public String canGiftEvent() {
        String gift = "없음";
        int totalPrice = calculateBeforeDiscountTotalPrice();
        if (totalPrice >= 120000) {
            gift = Menu.CHAMPAGNE.getName();
        }
        return gift;
    }

    public int canChristmasEvent(Date date) {
        int discount = 0;
        if (date.canChristmasEvent()) {
            //1,000원으로 시작하여 크리스마스가 다가올수록 날마다 할인 금액이 100원씩 증가
            discount += 1000;
            for (int i = 1; i < date.getDate(); i++) {
                discount += 100;
            }
        }
        return discount;
    }

    public int canWeekdayEvent(Date date) {
        int discount = 0;
        if (date.isWeekday()) {
            //평일 할인(일요일~목요일): 평일에는 디저트 메뉴를 메뉴 1개당 2,023원 할인
            int dessertQuantity = 0;
            for (Order order : orders) {
                dessertQuantity += order.calculateDessertQuantity();
            }
            discount = dessertQuantity * 2023;
        }
        return discount;
    }

    public int canWeekendEvent(Date date) {
        int discount = 0;
        if (date.isWeekend()) {
            //주말 할인(금요일, 토요일): 주말에는 메인 메뉴를 메뉴 1개당 2,023원 할인
            int mainQuantity = 0;
            for (Order order : orders) {
                mainQuantity += order.calculateMainQuantity();
            }
            discount = mainQuantity * 2023;
        }
        return discount;
    }
}
