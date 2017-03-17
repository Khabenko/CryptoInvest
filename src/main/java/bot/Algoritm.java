package bot;

import domain.ChartData;
import tradingapi.HttpURLConnection;

import java.math.BigDecimal;
import java.util.ArrayList;

import static bot.ChartDataCallBack.candlesMap;
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
    private BigDecimal limit = new BigDecimal(0.0002);
    private ArrayList<ChartData> candlesList = new ArrayList<>();

    private HttpURLConnection http  = new HttpURLConnection();


    private int candel1;
    private int candel2;
    private int candel3;
    private int lastCendelBuy;
    private  boolean openOorder;




    public void  runAlgoritm (String currencyPair, String candelPeriod, String marga) throws Exception {

        while (true){
            System.out.println("------------"+currencyPair+"-----------------");
            String[] curecyforbalance = currencyPair.split("_");
            getTicker();
            new ChartDataCallBack(currencyPair).getCandlesMap(currencyPair, candelPeriod);



        http.sendPostAvailableAccountBalances();
        http.sendPostReturnOpenOrders(currencyPair);

        Thread.sleep(1000);
        try {

                    candlesList = candlesMap.get(currencyPair);
                    balanceBTC = new BigDecimal(mapBalance.get("exchange").get("BTC"));


        }
        catch (Exception e)
        {
            balanceBTC = null;


        }
            try {

                balanceCurrenc = new BigDecimal(mapBalance.get("exchange").get(curecyforbalance[1]));
            }
            catch (Exception e)
            {
                balanceCurrenc =null;


            }

            System.out.println("BTC баланс "+balanceBTC);
            System.out.println("CurBalance "+curecyforbalance[1]+" "+balanceCurrenc);
            System.out.println(http.getOpenOrder(currencyPair));
                System.out.println("Open order "+String.valueOf(!http.getOpenOrder(currencyPair).equals("[]")));
         //   System.out.println(new BigDecimal(mapBalance.get("exchange").get("RTY")));

/*
            if (tikerMap.size() > 0) {
               Ticker tiker = tikerMap.get(currencyPair);
                LastPrice = tiker.getLast();
             //   System.out.println("Last price: "+tikerMap.get(currencyPair).getLast());


            }
*/

            if (candlesList != null) {
                ChartData lastCandel = candlesList.get(candlesList.size() - 1);
                System.out.println("Last candel open "+lastCandel.getOpen());
                System.out.println("Last candel close"+lastCandel.getClose());
                System.out.println("Amout of candel "+candlesList.size());
                System.out.println("Lower ask "+tikerMap.get(currencyPair).getLowerAsk());


                lowerAsk = lastCandel.getOpen().multiply(BigDecimal.valueOf(0.90));
                lastCendelBuy = lastCandel.getClose().compareTo(lowerAsk);



                //    candel1 = candlesList.get(candlesList.size() - 4).getOpen().compareTo(candlesList.get(candlesList.size() - 2).getClose());
                //      candel2 = candlesList.get(candlesList.size() - 3).getOpen().compareTo(candlesList.get(candlesList.size() - 2).getClose());
                      candel3 = candlesList.get(candlesList.size() - 2).getOpen().compareTo(candlesList.get(candlesList.size() - 2).getClose());

                System.out.println(candel3 == 1 && balanceBTC !=null && http.getOpenOrder(currencyPair).equals("[]"));

                    if ( candel3 == 1 && balanceBTC !=null && http.getOpenOrder(currencyPair).equals("[]")) {
                        System.out.println("-------------");
                        System.out.println("Покупка: Закрылась красная свеча");
                        try {
                            /// Добавить проврку на баланс
                            Thread.sleep(100);
                            if (balanceBTC.compareTo(limit)== -1) {
                                System.out.println("Без лимита");
                         //       http.sendPostBuy(currencyPair, String.valueOf(tikerMap.get(currencyPair).getLowerAsk()), String.valueOf(balanceBTC.divide(tikerMap.get(currencyPair).getLowerAsk(), 8, 1 / 3)));
                            }
                           else {
                                System.out.println("По лимиту");
                             //   http.sendPostBuy(currencyPair,String.valueOf(tikerMap.get(currencyPair).getLowerAsk()),String.valueOf(limit.divide(tikerMap.get(currencyPair).getLowerAsk(), 8, 1 / 3)));
                            }
                        } catch (Exception e) {
                            System.out.println("Buy error");
                            System.out.println(e);
                        }

                    }

                if ( lastCendelBuy == -1 && balanceBTC !=null && http.getOpenOrder(currencyPair).equals("[]")) {
                    System.out.println("-------------");
                    System.out.println("Покупка: Резкий скачек");
                    try {
                        Thread.sleep(100);
                        if (balanceBTC.compareTo(limit)== -1) {
                            System.out.println("Без лимита");
                            http.sendPostBuy(currencyPair, String.valueOf(lastCandel.getClose()), String.valueOf(balanceBTC.divide(lastCandel.getClose(), 8, 1 / 3)));
                        }
                        else  {
                            System.out.println("По лимиту");
                            http.sendPostBuy(currencyPair,String.valueOf(lastCandel.getClose()),String.valueOf(limit.divide(lastCandel.getClose(), 8, 1 / 3)));

                        }
                    } catch (Exception e) {
                        System.out.println("Buy error");
                        e.printStackTrace();
                    }

                }
                if (balanceCurrenc != null){
                        try {
                            /// Добавить проврку на баланс
                            System.out.println("OPEN "+lastCandel.getOpen());
                          http.sendPostSell(currencyPair,String.valueOf(tikerMap.get(currencyPair).getLowerAsk().multiply(BigDecimal.valueOf(1.01))), String.valueOf(balanceCurrenc));
                        } catch (Exception e) {
                            System.out.println("Sell error");
                            System.out.println(e);
                        }

                    }

            }

        }
    }
}
