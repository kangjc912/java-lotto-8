package lotto;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoMachineTest {
    private LottoMachine lottomachine;

    @BeforeEach
    void setUp() {
        lottomachine = new LottoMachine();
    }

    @DisplayName("구입 금액만큼 로또를 발급한다.")
    @Test
    void 구입_금액만큼_로또를_발급한다() {

        int purchaseAmount = 8000;


        List<Lotto> lottos = lottomachine.getLottos(purchaseAmount);


        assertThat(lottos).hasSize(8); // 8,000원이면 8개
    }

    @DisplayName("당첨 통계를 정확히 계산한다.")
    @Test
    void 당첨_통계를_정확히_계산한다() {

        Lotto winningLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        int bonusNumber = 7;


        List<Lotto> userLottos = List.of(
                new Lotto(List.of(1, 2, 3, 10, 11, 12)), // 5등 (3개 일치)
                new Lotto(List.of(40, 41, 42, 43, 44, 45))  // 꽝 (0개 일치)
        );


        Map<Rank, Integer> statistics = lottomachine.calculateStatistics(userLottos, winningLotto, bonusNumber);


        assertThat(statistics.get(Rank.FIFTH)).isEqualTo(1); // 5등은 1개
        assertThat(statistics.get(Rank.FOURTH)).isEqualTo(0); // 4등은 0개
        assertThat(statistics.get(Rank.THIRD)).isEqualTo(0);
        assertThat(statistics.get(Rank.SECOND)).isEqualTo(0);
        assertThat(statistics.get(Rank.FIRST)).isEqualTo(0);
    }

    @DisplayName("총 수익률을 소수점 둘째 자리에서 반올림하여 계산한다.")
    @Test
    void 총_수익률을_소수점_둘째_자리에서_반올림하여_계산한다() {

        Map<Rank, Integer> statistics = Map.of(
                Rank.FIFTH, 1,
                Rank.FOURTH, 0,
                Rank.THIRD, 0,
                Rank.SECOND, 0,
                Rank.FIRST, 0
        );
        int purchaseAmount = 8000;


        double profitRate = lottomachine.calculateProfitRate(statistics, purchaseAmount);


        assertThat(profitRate).isEqualTo(62.5);
    }
}
