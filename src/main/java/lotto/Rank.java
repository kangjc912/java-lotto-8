package lotto;


import java.text.NumberFormat;
import java.util.function.BiPredicate;
public enum Rank {

    FIRST(6,false,2_000_000_000,"6개 일치 (2,000,000,000원)"),
    SECOND(5, true, 30_000_000, "5개 일치, 보너스 볼 일치 (30,000,000원)"),
    THIRD(5, false, 1_500_000,"5개 일치 (1,500,000원"),
    FOURTH(4, false, 50_000, "4개 일치 (50,000원"),
    FIFTH(3,false, 5_000,"3개 일치 (5,000원"),
    MISS(0, false, 0, "0개 일치 (0원)");


    private final int matchCount;
    private final boolean bonus;
    private final int price;
    private final String text;


    Rank(int matchCount, boolean bonus, int price, String text) {
        this.matchCount = matchCount;
        this.bonus = bonus;
        this.price = price;
        this.text = text;
    }


    public static Rank valueOf(int matchCount, boolean bonus) {
        if(matchCount == 6){
            return FIRST;
        }


        if(matchCount == 5 && bonus == true) {
            return SECOND;
        }


        if(matchCount == 5 && bonus == false) {
            return THIRD;
        }


        if(matchCount == 4) {
            return FOURTH;
        }


        if(matchCount == 3) {
            return FIFTH;
        }


        return MISS;
    }



    public String getMessage() {
        return text;
    }



    public int getPrice() {
        return price;
    }


    public int getMatchCount() {
        return matchCount;
    }

}
