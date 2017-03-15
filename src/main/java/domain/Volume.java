package domain;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Volume extends HashMap<String, Map<String, BigDecimal>> {

    private BigDecimal totalBTC;
    private BigDecimal totalUSDT;
    private BigDecimal totalXMR;
    private BigDecimal totalXUSD;

    public BigDecimal getTotalBTC() {
        return totalBTC;
    }

    public void setTotalBTC(BigDecimal totalBTC) {
        this.totalBTC = totalBTC;
    }

    public BigDecimal getTotalUSDT() {
        return totalUSDT;
    }

    public void setTotalUSDT(BigDecimal totalUSDT) {
        this.totalUSDT = totalUSDT;
    }

    public BigDecimal getTotalXMR() {
        return totalXMR;
    }

    public void setTotalXMR(BigDecimal totalXMR) {
        this.totalXMR = totalXMR;
    }

    public BigDecimal getTotalXUSD() {
        return totalXUSD;
    }

    public void setTotalXUSD(BigDecimal totalXUSD) {
        this.totalXUSD = totalXUSD;
    }
}
