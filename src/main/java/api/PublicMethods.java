package api;


import domain.*;
import retrofit2.Call;
import service.PoloniexCallBack;
import service.PoloniexServiceRequest;

import java.util.List;
import java.util.Map;

public class PublicMethods {


    public void returnOrderBook(PoloniexCallBack<OrderBook> poloniexCallBack, String currencyPair, int depth) {
        PoloniexServiceRequest<OrderBook> poloniexServiceRequest = new PoloniexServiceRequest(poloniexCallBack);
        Methods methods = poloniexServiceRequest.getConfiguration().create(Methods.class);
        Call<OrderBook> orderBookCall = methods.returnOrderBook(currencyPair, depth);
        orderBookCall.enqueue(poloniexServiceRequest.getCallback());
    }

    //TODO: Find way to serialize this response
    public void return24hVolume(PoloniexCallBack<Volume> poloniexCallBack) {
        PoloniexServiceRequest<Volume> poloniexServiceRequest = new PoloniexServiceRequest(poloniexCallBack);
        Methods methods = poloniexServiceRequest.getConfiguration().create(Methods.class);
        Call<Volume> orderBookCall = methods.return24hVolume();
        orderBookCall.enqueue(poloniexServiceRequest.getCallback());
    }

    public void returnAllOrderBook(PoloniexCallBack<Map<String, OrderBook>> poloniexCallBack, int depth) {
        PoloniexServiceRequest<Map<String, OrderBook>> poloniexServiceRequest = new PoloniexServiceRequest(poloniexCallBack);
        Methods methods = poloniexServiceRequest.getConfiguration().create(Methods.class);
        Call<Map<String, OrderBook>> orderBookCall = methods.returnAllOrderBook("ALL", depth);
        orderBookCall.enqueue(poloniexServiceRequest.getCallback());
    }

    public void returnTicker(PoloniexCallBack<Map<String, Ticker>> poloniexCallBack) {
        PoloniexServiceRequest<Map<String, Ticker>> poloniexServiceRequest = new PoloniexServiceRequest(poloniexCallBack);
        Methods methods = poloniexServiceRequest.getConfiguration().create(Methods.class);
        Call<Map<String, Ticker>> tickerCall = methods.returnTicker();
        tickerCall.enqueue(poloniexServiceRequest.getCallback());
    }

    public void returnTradeHistory(PoloniexCallBack<List<TradeHistory>> poloniexCallBack, String currencyPair, String start, String end) {
        PoloniexServiceRequest<List<TradeHistory>> poloniexServiceRequest = new PoloniexServiceRequest(poloniexCallBack);
        Methods methods = poloniexServiceRequest.getConfiguration().create(Methods.class);
        Call<List<TradeHistory>> orderBookCall = methods.returnTradeHistory(currencyPair, start, end);
        orderBookCall.enqueue(poloniexServiceRequest.getCallback());
    }

    public void returnChartData(PoloniexCallBack<List<ChartData>> poloniexCallBack, String currencyPair, String start, String end, String period) {
        PoloniexServiceRequest<List<ChartData>> poloniexServiceRequest = new PoloniexServiceRequest(poloniexCallBack);
        Methods methods = poloniexServiceRequest.getConfiguration().create(Methods.class);
        Call<List<ChartData>> orderBookCall = methods.returnChartData(currencyPair, start, end, period);
        orderBookCall.enqueue(poloniexServiceRequest.getCallback());
    }

    public void returnCurrencies(PoloniexCallBack<Map<String, CurrencyData>> poloniexCallBack) {
        PoloniexServiceRequest<Map<String, CurrencyData>> poloniexServiceRequest = new PoloniexServiceRequest(poloniexCallBack);
        Methods methods = poloniexServiceRequest.getConfiguration().create(Methods.class);
        Call<Map<String, CurrencyData>> orderBookCall = methods.returnCurrencies();
        orderBookCall.enqueue(poloniexServiceRequest.getCallback());
    }

    public void returnLoanOrders(PoloniexCallBack<LoanOrders> poloniexCallBack, String currency) {
        PoloniexServiceRequest<LoanOrders> poloniexServiceRequest = new PoloniexServiceRequest(poloniexCallBack);
        Methods methods = poloniexServiceRequest.getConfiguration().create(Methods.class);
        Call<LoanOrders> orderBookCall = methods.returnLoanOrders(currency);
        orderBookCall.enqueue(poloniexServiceRequest.getCallback());
    }

}
