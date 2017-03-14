package api;


import domain.*;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.util.List;
import java.util.Map;

public interface Methods {

    @GET(Integration.URI_RETURN_TICKER)
    Call<Map<String, Ticker>> returnTicker();

    @GET(Integration.URI_RETURN_ORDER_BOOK)
    Call<OrderBook> returnOrderBook(@Query("currencyPair") String currencyPair, @Query("depth") int depth);

    @GET(Integration.URI_RETURN_ORDER_BOOK)
    Call<Map<String, OrderBook>> returnAllOrderBook(@Query("currencyPair") String currencyPair, @Query("depth") int depth);

    @GET(Integration.URI_RETURN_24H_VOLUME)
    Call<Volume> return24hVolume();

    @GET(Integration.URI_RETURN_TRADE_HISTORY)
    Call<List<TradeHistory>> returnTradeHistory(@Query("currencyPair") String currencyPair, @Query("start") String start, @Query("end") String end);

    @GET(Integration.URI_RETURN_CHART)
    Call<List<ChartData>> returnChartData(@Query("currencyPair") String currencyPair, @Query("start") String start, @Query("end") String end, @Query("period") String period);

    @GET(Integration.URI_RETURN_CURRENCIES)
    Call<Map<String, CurrencyData>> returnCurrencies();

    @GET(Integration.URI_RETURN_LOAN_ORDERS)
    Call<LoanOrders> returnLoanOrders(@Query("currency") String currency);

}
