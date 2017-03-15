package domain;

public interface Integration {

    String HOST = "https://poloniex.com/";
    String URI_RETURN_TICKER = "public?command=returnTicker";
    String URI_RETURN_24H_VOLUME = "public?command=return24hVolume";
    String URI_RETURN_ORDER_BOOK = "public?command=returnOrderBook";
    String URI_RETURN_TRADE_HISTORY = "public?command=returnTradeHistory";
    String URI_RETURN_CHART = "public?command=returnChartData";
    String URI_RETURN_CURRENCIES = "public?command=returnCurrencies";
    String URI_RETURN_LOAN_ORDERS = "public?command=returnLoanOrders";


}
