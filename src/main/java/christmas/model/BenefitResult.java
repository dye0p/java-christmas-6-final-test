package christmas.model;

import java.util.Map;
import java.util.Map.Entry;

public class BenefitResult {

    private final Map<String, Integer> benefitResult;

    public BenefitResult(Map<String, Integer> benefitResult) {
        this.benefitResult = benefitResult;
    }

    public int calculateTotalBenefitPrice() {
        //총혜택 금액 = 할인 금액의 합계 + 증정 메뉴의 가격
        int totalDiscount = calculateTotalDiscount();
        int giftDiscount = getGiftDiscount();

        return totalDiscount + giftDiscount;
    }

    private int calculateTotalDiscount() {
        int totalDiscount = 0;
        for (Entry<String, Integer> benefitDiscount : benefitResult.entrySet()) {
            if (!benefitDiscount.getKey().equals("증정 이벤트")) {
                totalDiscount += benefitDiscount.getValue();
            }
        }
        return totalDiscount;
    }

    private int getGiftDiscount() {
        return benefitResult.get("증정 이벤트");
    }

    public int calculateAfterDiscountPrice(int beforeTotalPrice) {
        //할인 후 예상 결제 금액 = 할인 전 총주문 금액 - 할인 금액
        return beforeTotalPrice - calculateTotalDiscount();
    }
}
