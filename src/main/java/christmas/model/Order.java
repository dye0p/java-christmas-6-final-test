package christmas.model;


import christmas.exception.ErrorMessage;
import java.util.Objects;

public class Order {

    private final Menu menu;
    private final int quantity;

    public Order(Menu menu, int quantity) {
        validateQuantity(quantity);
        this.menu = menu;
        this.quantity = quantity;
    }

    private void validateQuantity(int quantity) {
        if (quantity < 1) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER.getErrorMessage());
        }
    }

    public Menu getMenu() {
        return menu;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Order order = (Order) o;
        return menu == order.menu;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(menu);
    }

    public int calculateDessertQuantity() {
        int count = 0;
        if (Menu.isDessert(this.menu)) {
            count += this.quantity;
        }
        return count;
    }

    public int calculateMainQuantity() {
        int count = 0;
        if (Menu.isMain(this.menu)) {
            count += this.quantity;
        }
        return count;
    }
}
