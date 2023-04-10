import callAPI.GetAccounts;
import callAPI.GetOrderBook;
import com.google.gson.JsonElement;
import org.json.JSONArray;
import org.json.JSONObject;
import parsing.JsonArrayParser;
import parsing.ParseJsonString;
import personnelFolder.PersonnelKey;
import tradingStrategy.TradingStrService;

import java.util.List;

public class Main {
    // test
    public static void main(String[] args) {
        GetAccounts testGetAcc = new GetAccounts();
        PersonnelKey pk = new PersonnelKey();

        ParseJsonString parserToJson = new ParseJsonString();       //JsonObject 일 때
        JsonArrayParser jsonArrayParser = new JsonArrayParser();    //JsonArray 일 때

//       분캔들 테스트
        for (int i = 0; i < 30; i++) {
            callAPI.GetMinCandle minCandle = new callAPI.GetMinCandle(pk.getAccKey(), pk.getSecKey());
            minCandle.init();

            String minCandle1 = minCandle.getMinCandle("KRW-BTC", null, "1", "1");
            System.out.println(minCandle1);
            String subStr = minCandle1.substring(1, minCandle1.length());

            JSONObject jsonObject = new JSONObject(subStr);
            String market = jsonObject.getString("market");
            System.out.println(market);

            try {
                Thread.sleep(6000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        //호가 정보 조회 테스트
        GetOrderBook orderBook = new GetOrderBook(pk.getAccKey(), pk.getSecKey());
        orderBook.init();

        String orderBook1 = orderBook.getOrderBook("KRW-BTC");
        System.out.println("[MAIN RES BY ORIGIN] : " + orderBook1);

        List<JsonElement> list = jsonArrayParser.parseJsonArray(orderBook1);
        for (JsonElement s : list) {
            System.out.println("[MAIN RES BY JSONARRAY] : " + s);
        }
////////////////////////////////////
//
//        마켓코드 조회 테스트

        callAPI.GetMarketCodes marketCodes = new callAPI.GetMarketCodes(pk.getAccKey(), pk.getSecKey());
        marketCodes.init();

        String marketCodes1 = marketCodes.getMarketCodes(false);
        System.out.println("[marketCode] : " + marketCodes1);
        List<JsonElement> list1 = jsonArrayParser.parseJsonArray(marketCodes1);

        for (JsonElement jsonElement : list1) {
//            if(jsonElement.equals(""))
            System.out.println("[MARKETCODE TO-BE] : " + jsonElement);
        }
//        System.out.println(market);

//        JSONArray koreanName = jsonObject.getJSONArray("korean_name");
//        System.out.println(koreanName);


//        BaseBarSeries practice
//        ZonedDateTime now = ZonedDateTime.now();
//        System.out.println(now);
//
//        BaseBarSeries testBarSeries = new BaseBarSeriesBuilder().withName("test").build();
//        testBarSeries.addBar(now, 105.42,112.99,104.01,111.42,1337);
//
//        BaseBarSeries series = new BaseBarSeries("my_live_series");
//
//        series.addBar(now, 105.42, 112.99, 104.01, 111.42, 1337);
//        series.addPrice(105.44);
//        series.addTrade(105.44, 100);
//        series.addTrade(105.44, 200);
//
////        series.getBar(0).get
//
//        System.out.println(series.getBar(0).getTrades());

        TradingStrService tradingStrService = new TradingStrService();
        String marketCode = tradingStrService.getMarketCode("KRW-XRP");
        System.out.println(marketCode);

        tradingStrService.tempReverseTrading();
    }
}
