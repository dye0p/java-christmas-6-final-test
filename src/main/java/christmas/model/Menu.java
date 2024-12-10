package christmas.model;

public enum Menu {

    MUSHROOM_SOUP(MenuType.APPETIZER, "양송이수프", 6_000L),
    TAPAS(MenuType.APPETIZER, "타파스", 5_500L),
    CAESAR_SALAD(MenuType.APPETIZER, "시저샐러드", 8_000L),
    T_BONE_STEAK(MenuType.MAIN, "티본스테이크", 55_000L),
    PORK_RIB(MenuType.MAIN, "바비큐립", 54_000L),
    SEAFOOD_PASTA(MenuType.MAIN, "해산물파스타", 35_000L),
    CHRISTMAS_PASTA(MenuType.MAIN, "크리스마스파스타", 25_000L),
    CHOCOLATE_CAKE(MenuType.DESSERT, "초코케이크", 15_000L),
    ICE_CREAM(MenuType.DRINK, "아이스크림", 5_000L),
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
}
