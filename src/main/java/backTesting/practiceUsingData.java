package backTesting;

import org.ta4j.core.*;
import cmn.CSVReader;
import org.ta4j.core.criteria.AbstractAnalysisCriterion;
import org.ta4j.core.criteria.AverageReturnPerBarCriterion;
import org.ta4j.core.criteria.WinningPositionsRatioCriterion;
import org.ta4j.core.indicators.RSIIndicator;
import org.ta4j.core.indicators.SMAIndicator;
import org.ta4j.core.indicators.helpers.ClosePriceIndicator;
import org.ta4j.core.num.Num;
import org.ta4j.core.rules.BooleanRule;
import org.ta4j.core.rules.CrossedDownIndicatorRule;
import org.ta4j.core.rules.CrossedUpIndicatorRule;

import java.time.Year;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;
import java.util.StringTokenizer;

public class practiceUsingData {

    public static void main(String[] args) {

        BarSeries series = new BaseBarSeriesBuilder().withName("bitcoin").build();

        CSVReader csvReader = new CSVReader();
        List<List<String>> lists = csvReader.readCSV("/Users/user/IdeaProjects/openApiProject/src/main/java/personnelFolder/BTC-USD.csv");

        for (int i = 1; i < lists.size(); i++) {
            String[] parsedDate = parsingDate(lists.get(i).get(0));
            ZonedDateTime date = ZonedDateTime.of(Integer.parseInt(parsedDate[0]), Integer.parseInt(parsedDate[1]), Integer.parseInt(parsedDate[2])
                    , 0, 0, 0, 0, ZoneId.of("Asia/Seoul"));
            series.addBar(date, lists.get(i).get(1), lists.get(i).get(2), lists.get(i).get(3), lists.get(i).get(4));
        }

        ClosePriceIndicator closePrice = new ClosePriceIndicator(series);
        SMAIndicator shortSma = new SMAIndicator(closePrice, 34);
        SMAIndicator longSma = new SMAIndicator(closePrice, 250);
        RSIIndicator rsi = new RSIIndicator(closePrice, 10);

//        for (int i = 0; i < rsi.getBarSeries().getBarCount(); i++) {
//            System.out.println("RSIIndicator : " + rsi.getValue(i));
//        }

        Rule buyingRule = new CrossedUpIndicatorRule(closePrice, longSma)
                .and(new CrossedUpIndicatorRule(closePrice, shortSma));
//                .and(new BooleanRule(Integer.parseInt(String.valueOf(rsi.getValue(0))) > 20));
        Rule sellingRule = new CrossedDownIndicatorRule(closePrice, longSma)
                .and(new CrossedDownIndicatorRule(closePrice, shortSma));

        BaseStrategy baseStrategy = new BaseStrategy(buyingRule, sellingRule);
        BarSeriesManager manager = new BarSeriesManager(series);

        TradingRecord tradingRecord = manager.run(baseStrategy);

//        System.out.println(tradingRecord.getLastTrade());

        AnalysisCriterion analysisCriterion = new AverageReturnPerBarCriterion();
        Num analysisCalculate = analysisCriterion.calculate(series, tradingRecord);
        System.out.println("AverageReturnPerBarCriterion calculate : " + analysisCalculate);

        AnalysisCriterion criterion = new WinningPositionsRatioCriterion();
        Num calculate = criterion.calculate(series, tradingRecord);
        System.out.println("WinningPositionsRatioCriterion calculate : " + calculate);

    }

    static public String[] parsingDate(String date) {
        String[] result = new String[3];

        StringTokenizer st = new StringTokenizer(date, "-");

        for (int i = 0; i < 3; i++) {
            result[i] = st.nextToken();
        }

        return result;
    }
}
