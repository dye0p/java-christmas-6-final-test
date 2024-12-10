package christmas.model;

import christmas.exception.ErrorMessage;
import java.util.Arrays;

public enum Menu {

    MUSHROOM_SOUP(MenuType.APPETIZER, "양송이수프", 6_000L),
    TAPAS(MenuType.APPETIZER, "타파스", 5_500L),
    CAESAR_SALAD(MenuType.APPETIZER, "시저샐러드", 8_000L),
    T_BONE_STEAK(MenuType.MAIN, "티본스테이크", 55_000L),
    PORK_RIB(MenuType.MAIN, "바비큐립", 54_000L),
    SEAFOOD_PASTA(MenuType.MAIN, "해산물파스타", 35_000L),
    CHRISTMAS_PASTA(MenuType.MAIN, "크리스마스파스타", 25_000L),
    CHOCOLATE_CAKE(MenuType.DESSERT, "초코케이크", 15_000L),
    ICE_CREAM(MenuType.DESSERT, "아이스크림", 5_000L),
    ZERO_COLA(MenuType.DRINK, "제로콜라", 3_000L),
    RED_WINE(MenuType.DRINK, "레드와인", 65_000L),
    CHAMPAGNE(MenuType.DRINK, "샴페인", 25_000L),
    ;

    private final MenuType type;
    private final String name;
    private final Long price;

    Menu(MenuType type, String name, Long price) {
        this.type = type;
        this.name = name;
        this.price = price;
    }

    public static Menu of(String menuName) {
        return Arrays.stream(values())
                .filter(menu -> menu.name.equals(menuName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ErrorMessage.INVALID_ORDER.getErrorMessage()));
    }

    public static boolean isDrinkType(Order order) {
        for (Menu value : values()) {
            if (value.name.equals(order.getMenu().getName())) {
                if (value.type.equals(MenuType.DRINK)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isDessert(Menu menu) {
        return menu.getType().equals(MenuType.DESSERT);
    }

    public MenuType getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public Long getPrice() {
        return price;
    }
}
