package assetConrolStrategy.fixRateAmtStr.bean;

import java.math.BigDecimal;

public class Calculate_FixRate {

    public BigDecimal calculateNextAccAmtLevel(BigDecimal currAccAmtLevel, BigDecimal delta,
                                               int currTradingCtrCnt) {

        BigDecimal result = new BigDecimal(0);
        result = currAccAmtLevel.add(new BigDecimal(currTradingCtrCnt).multiply(delta));

        return result;
    }

    public BigDecimal calculateDelta(BigDecimal maxCumulativeLoss, BigDecimal initialMargin) {

        BigDecimal result;
        result = maxCumulativeLoss.add(initialMargin);

        return result;
    }

    public BigDecimal calculateMarginPerCtr(BigDecimal buyingAmt, BigDecimal sellingAmt,
                                            int currTradingCtrCnt) {

        BigDecimal result;

        if (sellingAmt.compareTo(buyingAmt) <= 0) {
            result = new BigDecimal(0);
        } else {
            BigDecimal gubun = sellingAmt.subtract(buyingAmt);
            result = gubun.divide(new BigDecimal(currTradingCtrCnt));
        }

        return result;
    }
}
