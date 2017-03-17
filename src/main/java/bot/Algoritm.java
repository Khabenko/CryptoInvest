package bot;

import domain.ChartData;
import tradingapi.HttpURLConnection;

import java.math.BigDecimal;

import static bot.ChartDataCallBack.candlesList;
import static bot.ChartDataCallBack.getCandels;
import static bot.TickerCallBack.getTicker;
import static bot.TickerCallBack.tikerMap;
import static tradingapi.HttpURLConnection.mapBalance;

/**
 * Created by M.Khabenko on 3/15/2017.
 */
public class Algoritm {
    private static BigDecimal balanceBTC = null;
    private BigDecimal balanceCurrenc = null;
    private BigDecimal lowerAsk;
    private BigDecimal LastPrice;
    private BigDecimal limit = new BigDecimal(0.01);

    private HttpURLConnection http  = new HttpURLConnection();


    private int candel1;
    private int candel2;
    private int candel3;
    private int lastCendelBuy;
    private  boolean openOorder;




    public void  runAlgoritm (String currencyPair, String candelPeriod, String marga) throws Exception {

        while (true){
            System.out.println("------------"+currencyPair+"-----------------");
        Thread.sleep(1000);
        ///   getCandels("BTC_ETH", "7200");
        getCandels(currencyPair, candelPeriod);
        getTicker();
        http.sendPostAvailableAccountBalances();
        http.sendPostReturnOpenOrders(currencyPair);


        try {
            balanceBTC = new BigDecimal(mapBalance.get("exchange").get("BTC"));
        }
        catch (Exception e)
        {
            balanceBTC = null;

        }
            try {

                balanceCurrenc = new BigDecimal(mapBalance.get("exchange").get("XMR"));
            }
            catch (Exception e)
            {
                balanceCurrenc =null;

            }

            System.out.println("BTC баланс "+balanceBTC);
            System.out.println("CurBalance "+balanceCurrenc);
            System.out.println("Open order "+http.getOpenOrder()=="[]");
         //   System.out.println(new BigDecimal(mapBalance.get("exchange").get("RTY")));

/*
            if (tikerMap.size() > 0) {
               Ticker tiker = tikerMap.get(currencyPair);
                LastPrice = tiker.getLast();
             //   System.out.println("Last price: "+tikerMap.get(currencyPair).getLast());


            }
*/

            if (candlesList.size() > 0) {
                ChartData lastCandel = candlesList.get(candlesList.size() - 1);
                System.out.println("Last candel open "+lastCandel.getOpen());
                System.out.println("Last candel close"+lastCandel.getClose());

                lowerAsk = lastCandel.getOpen().multiply(BigDecimal.valueOf(0.90));
                lastCendelBuy = lastCandel.getClose().compareTo(lowerAsk);



                //     candel1 = candlesList.get(candlesList.size() - 4).getOpen().compareTo(candlesList.get(candlesList.size() - 2).getClose());
                      candel2 = candlesList.get(candlesList.size() - 3).getOpen().compareTo(candlesList.get(candlesList.size() - 2).getClose());
                      candel3 = candlesList.get(candlesList.size() - 2).getOpen().compareTo(candlesList.get(candlesList.size() - 2).getClose());




                    if (candel2 == 1&& candel3 == 1 && balanceBTC !=null && http.getOpenOrder()=="[]") {
                        System.out.println("-------------");
                        System.out.println("Покупка: Закрылась красная свеча");
                        try {
                            /// Добавить проврку на баланс
                            Thread.sleep(100);
                            if (balanceBTC.compareTo(limit)== -1)
                           http.sendPostBuy(currencyPair,String.valueOf(tikerMap.get(currencyPair).getLowerAsk()),String.valueOf(balanceBTC.divide(tikerMap.get(currencyPair).getLowerAsk(), 8, 1 / 3)));
                            else http.sendPostBuy(currencyPair,String.valueOf(tikerMap.get(currencyPair).getLowerAsk()),String.valueOf(limit.divide(tikerMap.get(currencyPair).getLowerAsk(), 8, 1 / 3)));
                        } catch (Exception e) {
                            System.out.println("Buy error");
                            System.out.println(e);
                        }

                    }

                if ( lastCendelBuy == -1 && balanceBTC !=null && http.getOpenOrder()=="[]") {
                    System.out.println("-------------");
                    System.out.println("Покупка: Резкий скачек");
                    try {
                        Thread.sleep(100);
                        if (balanceBTC.compareTo(limit)== -1)
                             http.sendPostBuy(currencyPair,String.valueOf(lastCandel.getClose()),String.valueOf(balanceBTC.divide(lastCandel.getClose(), 8, 1 / 3)));
                        else       http.sendPostBuy(currencyPair,String.valueOf(lastCandel.getClose()),String.valueOf(limit.divide(lastCandel.getClose(), 8, 1 / 3)));
                    } catch (Exception e) {
                        System.out.println("Buy error");
                        System.out.println(e);
                    }

                }





                    if (balanceCurrenc != null){
                        try {
                            /// Добавить проврку на баланс
                            System.out.println("OPEN "+lastCandel.getOpen());
                          http.sendPostSell(currencyPair,String.valueOf(tikerMap.get(currencyPair).getLowerAsk().multiply(BigDecimal.valueOf(1.10))), String.valueOf(balanceCurrenc));
                        } catch (Exception e) {
                            System.out.println("Sell error");
                            System.out.println(e);
                        }




                    }














            }


        }
    }
}
