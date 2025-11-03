package lotto;


import camp.nextstep.edu.missionutils.Console;
public class Input {

    private static final String PURCHASE_AMOUNT = "구입 금액을 입력해 주세요.";
    private static final String WINNING_NUMBERS = "당첨 번호를 입력해 주세요.";
    private static final String BONUS_NUMBER = "보너스 번호를 입력해 주세요.";

    public String readPurchaseAmount() {
        System.out.println(PURCHASE_AMOUNT);
        return Console.readLine();
    }

    public String readWinningNumbers() {
        System.out.println(WINNING_NUMBERS);
        return Console.readLine();
    }

    public String readBonusNumber() {
        System.out.println(BONUS_NUMBER);
        return Console.readLine();
    }

}
