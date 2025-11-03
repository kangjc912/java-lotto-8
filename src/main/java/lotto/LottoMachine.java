package lotto;


import camp.nextstep.edu.missionutils.Randoms; // [필수] 라이브러리 import
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;


public class LottoMachine {

    public static final int LOTTO_PRICE = 1_000;

    // 로또 발급
    public List<Lotto> getLottos(int purchaseAmount) {
        int count = purchaseAmount/LOTTO_PRICE;
        List<Lotto> lottos = new ArrayList<>();

        for(int i = 0; i<count; i++) {
            lottos.add(generateLotto());
        }
        return lottos;
    }

    //로또 숫자 생성
    public Lotto generateLotto() {
        List<Integer> numbers = Randoms.pickUniqueNumbersInRange(1, 45, 6);
        return new Lotto(numbers);
    }

    //당첨 통계 계산
    public Map<Rank, Integer> calculateStatistics(List<Lotto> Lottos, Lotto WinningLotto, int bonusNumber) {
        Map<Rank, Integer> statistics = initializeStatisticsMap();
        for(Lotto lotto : Lottos) {
            getLottoRank(statistics, lotto, WinningLotto, bonusNumber);
        }
        return statistics;
    }


    //로또 맵 초기화
    private Map<Rank, Integer> initializeStatisticsMap() {
        Map<Rank, Integer> statistics = new EnumMap<>(Rank.class);
        for(Rank rank : Rank.values()) {
            initializeRank(statistics, rank);
        }
        return statistics;
    }

    private void initializeRank(Map<Rank, Integer> statistics, Rank rank) {
        if(rank != Rank.MISS) {
            statistics.put(rank, 0);
        }
    }



    //통계 집계
    private void getLottoRank(Map<Rank, Integer> statistics, Lotto lotto, Lotto WinningLotto, int bonusNumber) {
        Rank rank = calculateRank(lotto, WinningLotto, bonusNumber);
        if(rank != Rank.MISS) {
            statistics.put(rank, statistics.get(rank)+1);
        }
    }


    //Rank 계산
    private Rank calculateRank(Lotto userLotto, Lotto WinningLotto, int bonusNumber) {
        long MatchCount = calculateMatchCount(userLotto, WinningLotto);
        boolean bonusMatch = userLotto.getNumbers().contains(bonusNumber);
        return Rank.valueOf((int) MatchCount, bonusMatch);
    }


    //맞춘 개수 확인
    private long calculateMatchCount(Lotto userLotto, Lotto WinningLotto) {
        List<Integer> userNumbers = userLotto.getNumbers();
        List<Integer> winningNumbers = WinningLotto.getNumbers();


        return userNumbers.stream().filter(winningNumbers::contains).count();
    }


    //수익률 계산
    public double calculateProfitRate(Map<Rank, Integer> statistics, int purchaseAmount) {
        long totalPrizeMoney = calculateTotalPrize(statistics);

        if(purchaseAmount == 0) {
            return 0.0;
        }
        return((double) totalPrizeMoney/purchaseAmount) * 100.0;
    }


    //총 상금 계산
    private long calculateTotalPrize(Map<Rank, Integer> statistics) {
        long totalPrizeMoney = 0;
        for(Map.Entry<Rank, Integer> entry: statistics.entrySet()) {
            Rank rank = entry.getKey();
            int count = entry.getValue();
            totalPrizeMoney += (long) rank.getPrice() * count;
        }
        return totalPrizeMoney;
    }


}
