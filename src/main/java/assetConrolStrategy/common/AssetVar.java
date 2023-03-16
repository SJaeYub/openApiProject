package assetConrolStrategy.common;


import java.math.BigDecimal;

//fixedRiskAmt  고정된 리스크 금액
//accountAmt    계좌자산
//assetTradingRatio 자산대비 매매 횟수
//posTradingContractCnt 매매 가능 계약 수
//tradeRiskAmount       매매별 리스크 금액
public class AssetVar {

    private BigDecimal fixedRiskAmt;
    private BigDecimal accountAmt;
    private BigDecimal assetTradingRatio;
    private int posTradingContractCnt;
    private BigDecimal tradeRiskAmount;
    private BigDecimal fixAssetUnitPerContract;
    private BigDecimal fixAssetUnit;
    private BigDecimal maxCumulativeLoss;
    private BigDecimal acceptableLossRatio;

    public BigDecimal getFixAssetUnitPerContract() {
        return fixAssetUnitPerContract;
    }

    public void setFixAssetUnitPerContract(BigDecimal fixAssetUnitPerContract) {
        this.fixAssetUnitPerContract = fixAssetUnitPerContract;
    }

    public BigDecimal getFixAssetUnit() {
        return fixAssetUnit;
    }

    public void setFixAssetUnit(BigDecimal fixAssetUnit) {
        this.fixAssetUnit = fixAssetUnit;
    }

    public BigDecimal getMaxCumulativeLoss() {
        return maxCumulativeLoss;
    }

    public void setMaxCumulativeLoss(BigDecimal axCumulativeLoss) {
        this.maxCumulativeLoss = axCumulativeLoss;
    }

    public BigDecimal getAcceptableLossRatio() {
        return acceptableLossRatio;
    }

    public void setAcceptableLossRatio(BigDecimal acceptableLossRatio) {
        this.acceptableLossRatio = acceptableLossRatio;
    }

    public BigDecimal getFixedRiskAmt() {
        return fixedRiskAmt;
    }

    public void setFixedRiskAmt(BigDecimal fixedRiskAmt) {
        this.fixedRiskAmt = fixedRiskAmt;
    }

    public BigDecimal getAccountAmt() {
        return accountAmt;
    }

    public void setAccountAmt(BigDecimal accountAmt) {
        this.accountAmt = accountAmt;
    }

    public BigDecimal getAssetTradingRatio() {
        return assetTradingRatio;
    }

    public void setAssetTradingRatio(BigDecimal assetTradingRatio) {
        this.assetTradingRatio = assetTradingRatio;
    }

    public int getPosTradingContractCnt() {
        return posTradingContractCnt;
    }

    public void setPosTradingContractCnt(int posTradingContractCnt) {
        this.posTradingContractCnt = posTradingContractCnt;
    }

    public BigDecimal getTradeRiskAmount() {
        return tradeRiskAmount;
    }

    public void setTradeRiskAmount(BigDecimal tradeRiskAmount) {
        this.tradeRiskAmount = tradeRiskAmount;
    }
}
