package christmas.model;

import christmas.exception.ErrorMessage;
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
}
