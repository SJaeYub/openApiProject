package backTesting;

import org.ta4j.core.*;
import org.ta4j.core.criteria.VersusBuyAndHoldCriterion;
import org.ta4j.core.criteria.WinningPositionsRatioCriterion;
import org.ta4j.core.indicators.RSIIndicator;
import org.ta4j.core.indicators.SMAIndicator;
import org.ta4j.core.indicators.helpers.ClosePriceIndicator;
import org.ta4j.core.num.Num;
import org.ta4j.core.rules.CrossedDownIndicatorRule;
import org.ta4j.core.rules.CrossedUpIndicatorRule;
import org.ta4j.core.rules.StopGainRule;
import org.ta4j.core.rules.StopLossRule;

import java.time.ZonedDateTime;

public class PracticeTa4J {


    public static void main(String[] args) {

        BarSeries series = new BaseBarSeriesBuilder().withName("AXP_stock").build();

        for (int i = 0; i < 50; i++) {
            series.addBar(ZonedDateTime.now(), 105.42 + i, 112.99, 104.01, 111.42 + i, 1337);
        }


        // Getting the close price of the ticks
        Num firstClosePrice = series.getBar(0).getClosePrice();
        System.out.println("First close price: " + firstClosePrice.doubleValue());
// Or within an indicator:
        ClosePriceIndicator closePrice = new ClosePriceIndicator(series);
// Here is the same close price:
        System.out.println(firstClosePrice.isEqual(closePrice.getValue(0))); // equal to firstClosePrice

// Getting the simple moving average (SMA) of the close price over the last 5 ticks
        SMAIndicator shortSma = new SMAIndicator(closePrice, 5);
// Here is the 5-ticks-SMA value at the 42nd index
        System.out.println("5-ticks-SMA value at the 42nd index: " + shortSma.getValue(42).doubleValue());
//       sma size
        System.out.println("5-ticks-SMA size : " + shortSma.getBarSeries().getBarCount());

        RSIIndicator shortRsi = new RSIIndicator(closePrice, 5);

        System.out.println("5-ticks-RSI value at the 42nd index: " + shortRsi.getValue(42).doubleValue());

// Getting a longer SMA (e.g. over the 30 last ticks)
        SMAIndicator longSma = new SMAIndicator(closePrice, 30);

        // Buying rules
// We want to buy:
//  - if the 5-ticks SMA crosses over 30-ticks SMA
//  - or if the price goes below a defined price (e.g $800.00)
        Rule buyingRule = new CrossedUpIndicatorRule(shortSma, longSma)
                .or(new CrossedDownIndicatorRule(closePrice, 800d));

// Selling rules
// We want to sell:
//  - if the 5-ticks SMA crosses under 30-ticks SMA
//  - or if the price loses more than 3%
//  - or if the price earns more than 2%
        Rule sellingRule = new CrossedDownIndicatorRule(shortSma, longSma)
                .or(new StopLossRule(closePrice, 3.0))
                .or(new StopGainRule(closePrice, 2.0));

        Strategy strategy = new BaseStrategy(buyingRule, sellingRule);

        BarSeriesManager manager = new BarSeriesManager(series);

        TradingRecord tradingRecord = manager.run(strategy);
//        tradingRecord.operate(2);
//        System.out.println(tradingRecord.getLastTrade());
        System.out.println("Number of trades for our strategy: " + tradingRecord.getLastExit());

        AnalysisCriterion criterion = new WinningPositionsRatioCriterion();
        Num calculate = criterion.calculate(series, tradingRecord);
        System.out.println("WinningPositionsRatioCriterion calculate : " + calculate);


//        20221231 현재 깃에 올라온 버전과 일부 맞지 않아보임
        // Getting the profitable trades ratio
//        AnalysisCriterion profitTradesRatio = new AverageProfitableTradesCriterion();
//        System.out.println("Profitable trades ratio: " + profitTradesRatio.calculate(series, tradingRecord));
//// Getting the reward-risk ratio
//        AnalysisCriterion rewardRiskRatio = new RewardRiskRatioCriterion();
//        System.out.println("Reward-risk ratio: " + rewardRiskRatio.calculate(series, tradingRecord));
//
//// Total profit of our strategy
//// vs total profit of a buy-and-hold strategy
//        AnalysisCriterion vsBuyAndHold = new VersusBuyAndHoldCriterion(new TotalProfitCriterion());
//        System.out.println("Our profit vs buy-and-hold profit: " + vsBuyAndHold.calculate(series, tradingRecord));

    }
}
