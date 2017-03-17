package domain;

import java.math.BigDecimal;

public class CurrencyData {

    private String name;
    private BigDecimal txFee;
    private Integer minConf;
    private String depositAddrees;
    private int disabled;
    private int delisted;
    private int frozen;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getTxFee() {
        return txFee;
    }

    public void setTxFee(BigDecimal txFee) {
        this.txFee = txFee;
    }

    public Integer getMinConf() {
        return minConf;
    }

    public void setMinConf(Integer minConf) {
        this.minConf = minConf;
    }

    public String getDepositAddrees() {
        return depositAddrees;
    }

    public void setDepositAddrees(String depositAddrees) {
        this.depositAddrees = depositAddrees;
    }

    public int getDisabled() {
        return disabled;
    }

    public void setDisabled(int disabled) {
        this.disabled = disabled;
    }

    public int getDelisted() {
        return delisted;
    }

    public void setDelisted(int delisted) {
        this.delisted = delisted;
    }

    public int getFrozen() {
        return frozen;
    }

    public void setFrozen(int frozen) {
        this.frozen = frozen;
    }
}
