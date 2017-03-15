package domain;

import java.math.BigDecimal;

public class LoanOrder {

    private BigDecimal rate;
    private  BigDecimal amount;
    private long rangeMin;
    private long rangeMax;

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public long getRangeMin() {
        return rangeMin;
    }

    public void setRangeMin(long rangeMin) {
        this.rangeMin = rangeMin;
    }

    public long getRangeMax() {
        return rangeMax;
    }

    public void setRangeMax(long rangeMax) {
        this.rangeMax = rangeMax;
    }
}
