import org.json.JSONArray;
import org.json.JSONObject;
import personnelFolder.PersonnelKey;

public class Main {
    // test
    public static void main(String[] args) {
//        GetAccounts testGetAcc = new GetAccounts();
        PersonnelKey pk = new PersonnelKey();

        GetMinCandle minCandle = new GetMinCandle(pk.getAccKey(), pk.getSecKey());
        minCandle.init();

        String minCandle1 = minCandle.getMinCandle("KRW-BTC", null, "1", "30");
        System.out.println(minCandle1);
        String subStr = minCandle1.substring(1, minCandle1.length());

        JSONObject jsonObject = new JSONObject(subStr);
        String market = jsonObject.getString("market");
        System.out.println(market);

    }
}
