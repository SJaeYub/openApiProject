package tradingStrategy;

import callAPI.GetMinCandle;
import com.google.gson.JsonElement;
import parsing.JsonArrayParser;
import personnelFolder.PersonnelKey;

import java.util.List;

public class TradingStrService {

    /*
    전략 테스트를 위한 단순 구현이며 검증 후 운영 시 MVC 패턴 적용하여 재 구현 예정
    리플 활용
    {"market":"KRW-XRP","korean_name":"리플","english_name":"Ripple"}
     */

    private String accKey = new PersonnelKey().getAccKey();
    private String secKey = new PersonnelKey().getSecKey();
    public void tempReverseTrading() {
        GetMinCandle minCandle = new GetMinCandle(accKey, secKey);
        String minCandle1 = minCandle.getMinCandle("BTC-XRP", null, "1", "30");
        System.out.println(minCandle1);


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
