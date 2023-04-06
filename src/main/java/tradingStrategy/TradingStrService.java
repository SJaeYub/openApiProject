package tradingStrategy;

import callAPI.GetMinCandle;
import cmn.CSVReader;
import com.google.gson.JsonElement;
import parsing.JsonArrayParser;
import personnelFolder.PersonnelKey;

import java.math.BigDecimal;
import java.util.List;

public class TradingStrService {

    /*
    전략 테스트를 위한 단순 구현이며 검증 후 운영 시 MVC 패턴 적용하여 재 구현 예정
    리플 활용
    {"market":"KRW-XRP","korean_name":"리플","english_name":"Ripple"}
     */

    private String accKey = new PersonnelKey().getAccKey();
    private String secKey = new PersonnelKey().getSecKey();

    public static void main(String[] args) {

        TradingStrService strService = new TradingStrService();
        strService.tempReverseTrading();
    }

    public void tempReverseTrading() {
        GetMinCandle minCandle = new GetMinCandle(accKey, secKey);

//            String minCandle1 = minCandle.getMinCandle("BTC-XRP", null, "1", "30");
//            System.out.println(minCandle1);

        CSVReader csvReader = new CSVReader();
        List<List<String>> lists = csvReader.readCSV("/Users/user/IdeaProjects/openApiProject/src/main/java/personnelFolder/FINAL_USO.csv");

        //항상 전일 양-음봉을 가지고 판단
        boolean buy_flag = false;
        boolean sell_flag = false;
        boolean buy_position = false;
        boolean sell_position = false;

        int cnt = 0;
        BigDecimal init_enter_price = new BigDecimal(0);
        BigDecimal init_sell_price = new BigDecimal(0);

        BigDecimal total_margin = new BigDecimal(0);
        BigDecimal buyingPrice = new BigDecimal(0);
        BigDecimal sellingPrice = new BigDecimal(0);

        int trade_cnt = 0;
        int pos_cnt = 0;

        for (int i = 3; i < lists.size(); i++) {

            if(cnt != 0 && cnt < 6) {
                cnt++;
            } else {
                cnt = 0;
                buy_flag = false;
            }

            BigDecimal today_open = new BigDecimal(lists.get(i).get(1));
            BigDecimal today_high = new BigDecimal(lists.get(i).get(2));
            BigDecimal today_low = new BigDecimal(lists.get(i).get(3));
            BigDecimal today_close = new BigDecimal(lists.get(i).get(4));

//            System.out.println(lists.get(i).get(0) + " " + open + " " +
//                    high + " " + low + " " + close);

            BigDecimal yesterday_open = new BigDecimal(lists.get(i - 1).get(1));
            BigDecimal yesterday_high = new BigDecimal(lists.get(i - 1).get(2));
            BigDecimal yesterday_low = new BigDecimal(lists.get(i - 1).get(3));
            BigDecimal yesterday_close = new BigDecimal(lists.get(i - 1).get(4));

            BigDecimal twoDaysAgo_open = new BigDecimal(lists.get(i - 2).get(1));
            BigDecimal twoDaysAgo_high = new BigDecimal(lists.get(i - 2).get(2));
            BigDecimal twoDaysAgo_low = new BigDecimal(lists.get(i - 2).get(3));
            BigDecimal twoDaysAgo_close = new BigDecimal(lists.get(i - 2).get(4));

            boolean twoDaysAgo_signal = determine_candle(twoDaysAgo_open, twoDaysAgo_close);
            boolean pre_signal = determine_candle(yesterday_open, yesterday_close);
            boolean curr_signal = determine_candle(today_open, today_close);

            //두 봉이 연속 양봉이면서 당일 봉이 음봉인 경우
            if (pre_signal && !curr_signal && twoDaysAgo_signal && !buy_position) {
                buy_flag = true;

                init_enter_price = yesterday_high;
                init_sell_price = yesterday_low;
                cnt = 1;
            }

            if (buy_flag && !buy_position) {
                if (init_enter_price.compareTo(today_high) == 1) {
                    //buying - 진입가격 ,직전 양봉 최저점 기록
                    System.out.println("buying" + " " + today_close + " date : " + lists.get(i).get(0));
                    buyingPrice = today_close;
                    buy_flag = false;
                    buy_position = true;
                    continue;
                }
            }

            if (buy_position) {
                if (today_low.compareTo(init_sell_price) == -1) {
                    //selling
                    System.out.println("selling" + " " + today_close + " date : " + lists.get(i).get(0));
//                    sell_position = true;
                    sellingPrice = today_close;
                    total_margin = total_margin.add(sellingPrice.subtract(buyingPrice));
                    trade_cnt++;

                    if (sellingPrice.compareTo(buyingPrice) == 1) {
                        pos_cnt++;
                    }

                    buy_position = false;
                } else {
                    init_sell_price = today_close;
                }
            }

        }

        System.out.println("total margin : " + total_margin);
        System.out.println("total trade cnt : " + trade_cnt);
        System.out.println("total pos cnt : " + pos_cnt);
        System.out.println("winningRate : " + pos_cnt%trade_cnt);
    }

    /**
     * 양-음봉 결정
     *
     * @param open
     * @param close 시가보다 종가가 높거나 같으면 양봉, 낮으면 음봉 판단
     * @return
     */
    public boolean determine_candle(BigDecimal open, BigDecimal close) {
        boolean result = false;

        if (close.compareTo(open) >= 0) {
            result = true;
        }

        return result;
    }

    public String getMarketCode(String target) {
        callAPI.GetMarketCodes marketCodes = new callAPI.GetMarketCodes(accKey, secKey);
        marketCodes.init();

        String marketCodes1 = marketCodes.getMarketCodes(false);
        JsonArrayParser jsonArrayParser = new JsonArrayParser();
        List<JsonElement> list1 = jsonArrayParser.parseJsonArray(marketCodes1);

//        System.out.println(marketCodes1);

        String result = "";
        int i = 0;

        for (JsonElement jsonElement : list1) {
            if (jsonElement.toString().indexOf(target) != -1) {
                result = jsonElement.toString();
                break;
            }
        }

        return result;
    }
}
