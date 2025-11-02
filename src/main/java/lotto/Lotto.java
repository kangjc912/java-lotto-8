package lotto;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        List<Integer> sortedNumbers = new ArrayList<>(numbers);
        Collections.sort(sortedNumbers);
        this.numbers = sortedNumbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 6개여야 합니다.");
        }

        validateDuplicate(numbers);
        validateRange(numbers);
    }


    //중복여부 테스트
    private void validateDuplicate(List<Integer> numbers) {
        Set<Integer> uniqueNumbers = new HashSet<>(numbers);
        if(uniqueNumbers.size() != numbers.size()) {
            throw new IllegalArgumentException("[ERROR] 중복된 숫자");
        }
    }

    private void validateRange(List<Integer> numbers) {
        for(Integer number : numbers){
            checkRange(number);
        }
    }


    private void checkRange(int number) {
        if(number<1 || number>45) {
            throw new IllegalArgumentException("[ERROR] 범위 외의 숫자");
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
