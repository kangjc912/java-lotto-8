package lotto;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;


public class Output {


    private static final String PURCHASE_COUNT_TEXT = "\n%d개를 구매했습니다.";
    private static final String STATISTICS_HEADER = "\n당첨 통계";
    private static final String SEPARATOR_LINE = "---";


    private static final String STATISTICS_ROW = "%s - %d개";


    private static final String PROFIT_RATE_DECIMAL = "#,##0.0";
    private static final String PROFIT_RATE_MESSAGE = "총 수익률은 %s%%입니다."; // %%는 % 문자 자체를 의미

    private static final List<Rank> PRINTABLE_RANKS = List.of(
            Rank.FIFTH,
            Rank.FOURTH,
            Rank.THIRD,
            Rank.SECOND,
            Rank.FIRST
    );
    public void printLottoCount(int count) {
        System.out.println(String.format(PURCHASE_COUNT_TEXT,count));
    }

    public void printLotto(List<Lotto> lottos) {
        for(Lotto lotto : lottos) {
            System.out.println(lotto.toString());
        }
    }

    public void printHeader() {
        System.out.println(STATISTICS_HEADER);
        System.out.println(SEPARATOR_LINE);
    }


    public void printStatistics(Map<Rank, Integer> statistics) {
        System.out.println(String.format(STATISTICS_ROW,Rank.FIFTH.getMessage(),statistics.getOrDefault(Rank.FIFTH,0)));
        System.out.println(String.format(STATISTICS_ROW,Rank.FOURTH.getMessage(),statistics.getOrDefault(Rank.FOURTH,0)));
        System.out.println(String.format(STATISTICS_ROW,Rank.THIRD.getMessage(),statistics.getOrDefault(Rank.THIRD,0)));
        System.out.println(String.format(STATISTICS_ROW,Rank.SECOND.getMessage(),statistics.getOrDefault(Rank.SECOND,0)));
        System.out.println(String.format(STATISTICS_ROW,Rank.FIRST.getMessage(),statistics.getOrDefault(Rank.FIRST,0)));
    }

    public void printProfitRate(double profitRate) {
        DecimalFormat df = new DecimalFormat(PROFIT_RATE_DECIMAL);
        String formattedRate = df.format(profitRate);
        System.out.println(String.format(PROFIT_RATE_MESSAGE, formattedRate));
    }

    public void printError(String text) {
        System.out.println(text);
    }
}
