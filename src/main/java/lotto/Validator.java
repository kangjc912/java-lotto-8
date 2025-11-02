package lotto;

import java.util.ArrayList;
import java.util.List;

public class Validator {


    private static final int LOTTO_PRICE = 1_000;
    private static final String ERROR_MESSAGE_NOT_NUMBER = "[ERROR] 입력값이 숫자가 아닙니다.";
    private static final String ERROR_MESSAGE_PRICE_UNIT = "[ERROR] 구입 금액은 " + LOTTO_PRICE + "원 단위여야 합니다.";
    private static final String ERROR_MESSAGE_PRICE_MINIMUM = "[ERROR] 구입 금액은 " + LOTTO_PRICE + "원 이상이어야 합니다.";
    private static final String ERROR_MESSAGE_WINNING_NUMBERS_FORMAT = "[ERROR] 당첨 번호는 쉼표(,)로 구분된 6개의 숫자여야 합니다.";
    private static final String ERROR_MESSAGE_BONUS_DUPLICATE = "[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다.";
    private static final String ERROR_MESSAGE_BONUS_RANGE = "[ERROR] 보너스 번호는 1부터 45 사이의 숫자여야 합니다.";


    public int validatePurchaseAmount(String input) {
        int amount = validateNumber(input);
        validateAmountUnit(amount);
        validateMinimumAmount(amount);
        return amount;
    }

    private int validateNumber(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_MESSAGE_NOT_NUMBER);
        }
    }

    private void validateAmountUnit(int amount) {
        if (amount % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException(ERROR_MESSAGE_PRICE_UNIT);
        }
    }

    private void validateMinimumAmount(int amount) {
        if (amount < LOTTO_PRICE) {
            throw new IllegalArgumentException(ERROR_MESSAGE_PRICE_MINIMUM);
        }
    }


    public Lotto validateWinningNumbers(String input) {
        List<String> numberStrings = List.of(input.split(","));
        List<Integer> numbers = new ArrayList<>();

        if (numberStrings.size() != 6) {
            throw new IllegalArgumentException(ERROR_MESSAGE_WINNING_NUMBERS_FORMAT);
        }

        for (String numberStr : numberStrings) {
            numbers.add(validateNumber(numberStr.trim()));
        }


        return new Lotto(numbers);
    }



    public int validateBonusNumber(String input, Lotto winningLotto) {
        int bonusNumber = validateNumber(input);
        if (bonusNumber < 1 || bonusNumber > 45) {
            throw new IllegalArgumentException(ERROR_MESSAGE_BONUS_RANGE);
        }

        validateBonusDuplicate(bonusNumber, winningLotto);
        return bonusNumber;
    }

    private void validateBonusDuplicate(int bonusNumber, Lotto winningLotto) {
        if (winningLotto.getNumbers().contains(bonusNumber)) {
            throw new IllegalArgumentException(ERROR_MESSAGE_BONUS_DUPLICATE);
        }
    }
}