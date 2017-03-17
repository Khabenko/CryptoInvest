package domain;

import com.google.gson.annotations.SerializedName;

public enum TypeTrade {

    @SerializedName("buy")
    BUY("buy"),
    @SerializedName("sell")
    SELL("sell");

    private String type;


    TypeTrade(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


}
