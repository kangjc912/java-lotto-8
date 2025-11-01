package lotto;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RankTest {

    @Test

    void 여섯_개가_일치하면_FIRST를_반환한다 () {
        int matchCount = 6;
        boolean bonus = false;

        Rank result = Rank.valueOf(matchCount, bonus);

        assertThat(result).isEqualTo(Rank.FIRST);
    }

    @Test

    void 다섯_개와_보너스_번호가_일치하면_SECOND를_반환한다() {
        Rank result = Rank.valueOf(5, true);
        assertThat(result).isEqualTo(Rank.SECOND);
    }

    @Test

    void 다섯_개가_일치하면_THIRD를_반환한다() {
        Rank result = Rank.valueOf(5, false);
        assertThat(result).isEqualTo(Rank.THIRD);
    }

    @Test

    void 네_개가_일치하면_FOURTH를_반환한다() {
        Rank result = Rank.valueOf(4, false);
        assertThat(result).isEqualTo(Rank.FOURTH);
    }

    @Test

    void 세_개가_일치하면_FOURTH를_반환한다() {
        Rank result = Rank.valueOf(3, false);
        assertThat(result).isEqualTo(Rank.FIFTH);
    }

    @Test

    void 두_개_이하가_일치하면_FOURTH를_반환한다() {
        Rank result2 = Rank.valueOf(2, true);
        Rank result1 = Rank.valueOf(1, false);
        Rank result0 = Rank.valueOf(0, true);

        assertThat(result2).isEqualTo(Rank.MISS);
        assertThat(result1).isEqualTo(Rank.MISS);
        assertThat(result0).isEqualTo(Rank.MISS);
    }
}
