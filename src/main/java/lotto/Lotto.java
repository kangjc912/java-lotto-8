package lotto;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


public class Lotto {
    private final List<Integer> numbers;


    private static final int LOTTO_NUMBER_COUNT = 6;
    private static final int MIN_LOTTO_NUMBER = 1;
    private static final int MAX_LOTTO_NUMBER = 45;

    // 💡 에러 메시지(Magic String) 상수 정의
    // (에러 메시지는 요구사항에 따라 더 구체적으로 바꿀 수도 있습니다.)
    private static final String ERROR_INVALID_COUNT = "[ERROR] 로또 번호는 " + LOTTO_NUMBER_COUNT + "개여야 합니다.";
    private static final String ERROR_DUPLICATE_NUMBER = "[ERROR] 중복된 숫자가 있습니다.";
    private static final String ERROR_OUT_OF_RANGE = "[ERROR] 로또 번호는 " + MIN_LOTTO_NUMBER + "부터 "
            + MAX_LOTTO_NUMBER + " 사이의 숫자여야 합니다.";


    public Lotto(List<Integer> numbers) {
        validate(numbers);
        List<Integer> sortedNumbers = new ArrayList<>(numbers);
        Collections.sort(sortedNumbers);
        this.numbers = sortedNumbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException(ERROR_INVALID_COUNT);
        }

        validateDuplicate(numbers);
        validateRange(numbers);
    }


    //중복여부 테스트
    private void validateDuplicate(List<Integer> numbers) {
        Set<Integer> uniqueNumbers = new HashSet<>(numbers);
        if(uniqueNumbers.size() != numbers.size()) {
            throw new IllegalArgumentException(ERROR_DUPLICATE_NUMBER);
        }
    }

    private void validateRange(List<Integer> numbers) {
        for(Integer number : numbers){
            checkRange(number);
        }
    }


    private void checkRange(int number) {
        if(number<1 || number>45) {
            throw new IllegalArgumentException(ERROR_OUT_OF_RANGE);
        }
    }

    public List<Integer> getNumbers() {
        return Collections.unmodifiableList(this.numbers);
    }

    @Override
    public String toString() {
        return this.numbers.toString();
    }

}
