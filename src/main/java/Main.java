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
    }
}
