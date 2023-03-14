package assetConrolStrategy;


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
    private BigDecimal posTradingContractCnt;
    private BigDecimal tradeRiskAmount;

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

    public BigDecimal getPosTradingContractCnt() {
        return posTradingContractCnt;
    }

    public void setPosTradingContractCnt(BigDecimal posTradingContractCnt) {
        this.posTradingContractCnt = posTradingContractCnt;
    }

    public BigDecimal getTradeRiskAmount() {
        return tradeRiskAmount;
    }

    public void setTradeRiskAmount(BigDecimal tradeRiskAmount) {
        this.tradeRiskAmount = tradeRiskAmount;
    }
}
