package christmas.model;

public class EventPlanner {

    private final Date date;
    private final Orders orders;

    public EventPlanner(Date date, Orders orders) {
        this.date = date;
        this.orders = orders;
    }

    public int canChristmasEvent() {
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

    public int canWeekdayEvent() {
        int discount = 0;
        if (date.isWeekday()) {
            //평일 할인(일요일~목요일): 평일에는 디저트 메뉴를 메뉴 1개당 2,023원 할인
            int dessertQuantity = 0;
            for (Order order : orders.getOrders()) {
                dessertQuantity += order.calculateDessertQuantity();
            }
            discount = dessertQuantity * 2023;
        }
        return discount;
    }

    public int canWeekendEvent() {
        int discount = 0;
        if (date.isWeekend()) {
            //주말 할인(금요일, 토요일): 주말에는 메인 메뉴를 메뉴 1개당 2,023원 할인
            int mainQuantity = 0;
            for (Order order : orders.getOrders()) {
                mainQuantity += order.calculateMainQuantity();
            }
            discount = mainQuantity * 2023;
        }
        return discount;
    }

    public int canSpecialEvent() {
        int discount = 0;
        if (date.isSpecialDay()) {
            //특별 할인: 이벤트 달력에 별이 있으면 총주문 금액에서 1,000원 할인
            discount = 1000;
        }
        return discount;
    }

    public int canGiftEvent() {
        long discount = 0;

        String giftMenu = canGiftMenu();
        if (!giftMenu.equals("없음")) {
            discount = Menu.getGiftMenu();
        }

        return (int) discount;
    }

    public String canGiftMenu() {
        String gift = "없음";
        int totalPrice = orders.calculateBeforeDiscountTotalPrice();
        if (totalPrice >= 120000) {
            gift = Menu.CHAMPAGNE.getName();
        }
        return gift;
    }

    public String getBadge() {
        int totalBenefitPrice = calculateTotalBenefitPrice();

        String badge = "없음";

        if (totalBenefitPrice >= 5000 && totalBenefitPrice < 10000) {
            badge = "별";
        }
        if (totalBenefitPrice >= 10000 && totalBenefitPrice < 20000) {
            badge = "트리";
        }
        if (totalBenefitPrice >= 20000) {
            badge = "산타";
        }

        return badge;
    }

    public int calculateTotalDiscount() {
        return canChristmasEvent() + canWeekdayEvent() + canWeekendEvent() + canSpecialEvent();
    }

    public int calculateTotalBenefitPrice() {
        return calculateTotalDiscount() + canGiftEvent();
    }

    public int calculateAfterDiscountPrice() {
        return orders.calculateBeforeDiscountTotalPrice() - calculateTotalDiscount();
    }

    public boolean isPossibleEvent() {
        int totalPrice = orders.calculateBeforeDiscountTotalPrice();
        return totalPrice >= 10000;
    }
}
