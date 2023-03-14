package assetConrolStrategy;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Calculate {

    public BigDecimal calculateFixedRiskAmt(BigDecimal accountAmt, BigDecimal assetTradingRatio) {
        BigDecimal result = new BigDecimal(BigInteger.ZERO);

        result = accountAmt.divide(assetTradingRatio);

        return result;
    }
}
