package lotto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

class ValidatorTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        validator = new Validator();
    }


    @Test
    void 구입_금액_검증_숫자가_아니면_예외_발생() {
        assertThatThrownBy(() -> validator.validatePurchaseAmount("8000a"))
                .isInstanceOf(IllegalArgumentException.class);
    }


    @Test
    void 구입_금액_검증_정상적인_경우_금액_반환() {
        int amount = validator.validatePurchaseAmount("8000");
        assertThat(amount).isEqualTo(8000);
    }


    @Test
    void 당첨_번호_검증_6개가_아니면_예외_발생() {
        assertThatThrownBy(() -> validator.validateWinningNumbers("1,2,3,4,5"))
                .isInstanceOf(IllegalArgumentException.class);
    }


    @Test
    void 당첨_번호_검증_숫자가_아닌_값이_포함되면_예외_발생() {
        assertThatThrownBy(() -> validator.validateWinningNumbers("1,2,3,4,5,a"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 당첨_번호_검증_Lotto의_중복_검증을_통과_못하면_예외_발생() {
        assertThatThrownBy(() -> validator.validateWinningNumbers("1,2,3,4,5,5"))
                .isInstanceOf(IllegalArgumentException.class); // Lotto의 에러 메시지
    }

    @Test
    void 당첨_번호_검증_Lotto의_범위_검증을_통과_못하면_예외_발생() {
        assertThatThrownBy(() -> validator.validateWinningNumbers("1,2,3,4,5,46"))
                .isInstanceOf(IllegalArgumentException.class); // Lotto의 에러 메시지
    }


    @Test
    void 보너스_번호_검증_당첨_번호와_중복되면_예외_발생() {
        Lotto winningLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        String bonusInput = "6";

        assertThatThrownBy(() -> validator.validateBonusNumber(bonusInput, winningLotto))
                .isInstanceOf(IllegalArgumentException.class);
    }


    @Test
    void 보너스_번호_검증_1_45_범위를_벗어나면_예외_발생() {
        Lotto winningLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        String bonusInput = "46";

        assertThatThrownBy(() -> validator.validateBonusNumber(bonusInput, winningLotto))
                .isInstanceOf(IllegalArgumentException.class);
    }
}