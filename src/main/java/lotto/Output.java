package lotto;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;


public class Output {

    public void printLottoCount(int count) {
        System.out.println("\n" + count + "개를 구매했습니다.");
    }

    public void printLotto(List<Lotto> lottos) {
        for(Lotto lotto : lottos) {
            System.out.println(lotto.toString());
        }
    }

    public void printHeader() {
        System.out.println("\n당첨 통계");
        System.out.println("---");
    }


    public void printDetail(Map<Rank, Integer> statistics) {
        System.out.println(Rank.FIFTH.getMessage() + " - " + statistics.getOrDefault(Rank.FIFTH, 0) + "개");
        System.out.println(Rank.FOURTH.getMessage() + " - " + statistics.getOrDefault(Rank.FOURTH, 0) + "개");
        System.out.println(Rank.THIRD.getMessage() + " - " + statistics.getOrDefault(Rank.THIRD, 0) + "개");
        System.out.println(Rank.SECOND.getMessage() + " - " + statistics.getOrDefault(Rank.SECOND, 0) + "개");
        System.out.println(Rank.FIRST.getMessage() + " - " + statistics.getOrDefault(Rank.FIRST, 0) + "개");
    }

    public void printProfitRate(double profitRate) {
        DecimalFormat df = new DecimalFormat("#,##0.0");
        System.out.println("총 수익률은 " + df.format(profitRate) + "%입니다. ");
    }

    public void printError(String text) {
        System.out.println(text);
    }
}
