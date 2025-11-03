package lotto;

import java.util.List;
import java.util.Map;

public class Application {

    private final Input input = new Input();
    private final Output output = new Output();
    private final Validator validator = new Validator();
    private final LottoMachine lottomachine = new LottoMachine();



    public static void main(String[] args) {

        new Application().run();
    }

    private void run() {
        int purchaseAmount = getValidPurchaseAmount();
        List<Lotto> userLottos = issueLottos(purchaseAmount);

        Lotto winningLotto = getValidWinningNumbers();
        int bonusNumber = getValidBonusNumber(winningLotto);

        showResults(userLottos, winningLotto, bonusNumber, purchaseAmount);
    }


    private int getValidPurchaseAmount() {
        try {
            String inputAmount = input.readPurchaseAmount();
            return validator.validatePurchaseAmount(inputAmount);
        } catch (IllegalArgumentException e) {
            output.printError(e.getMessage());
            return getValidPurchaseAmount();
        }
    }

    private List<Lotto> issueLottos(int purchaseAmount) {
        List<Lotto> lottos = lottomachine.getLottos(purchaseAmount);
        output.printLottoCount(lottos.size());
        output.printLotto(lottos);
        return lottos;
    }

    private Lotto getValidWinningNumbers() {
        try {
            String inputNumbers = input.readWinningNumbers();
            return validator.validateWinningNumbers(inputNumbers);
        } catch (IllegalArgumentException e) {
            output.printError(e.getMessage());
            return getValidWinningNumbers();
        }
    }

    private int getValidBonusNumber(Lotto winningLotto) {
        try {
            String inputBonus = input.readBonusNumber();
            return validator.validateBonusNumber(inputBonus, winningLotto);
        } catch (IllegalArgumentException e) {
            output.printError(e.getMessage());
            return getValidBonusNumber(winningLotto);
        }
    }

    private void showResults(List<Lotto> userLottos, Lotto winningLotto, int bonusNumber, int purchaseAmount) {
        Map<Rank, Integer> statistics = lottomachine.calculateStatistics(userLottos, winningLotto, bonusNumber);
        double profitRate = lottomachine.calculateProfitRate(statistics, purchaseAmount);

        output.printHeader();
        output.printStatistics(statistics);
        output.printProfitRate(profitRate);
    }
}
