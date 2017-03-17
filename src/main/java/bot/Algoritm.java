package bot;

import domain.ChartData;
import tradingapi.HttpURLConnection;

import java.math.BigDecimal;

import static bot.ChartDataCallBack.candlesList;
import static bot.ChartDataCallBack.getCandels;
import static bot.TickerCallBack.getTicker;
import static tradingapi.HttpURLConnection.mapBalance;

/**
 * Created by M.Khabenko on 3/15/2017.
 */
public class Algoritm {
    private static BigDecimal balanceBTC = null;
    private BigDecimal balanceCurrenc = null;
    private BigDecimal lowerAsk;
    private HttpURLConnection http  = new HttpURLConnection();


    private int candel1;
    private int candel2;
    private int candel3;



    public void  runAlgoritm (String currencyPair, String candelPeriod, String marga) throws Exception {

        while (true){
        Thread.sleep(1000);
        ///   getCandels("BTC_ETH", "7200");
        getCandels(currencyPair, candelPeriod);
        getTicker();
        http.sendPostAvailableAccountBalances();

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
         //   System.out.println(new BigDecimal(mapBalance.get("exchange").get("RTY")));

/*
            if (tikerMap.size() > 0) {
               Ticker tiker = tikerMap.get(currencyPair);
                lowerAsk = tiker.getLowerAsk();
                System.out.println("1"+tikerMap.get(currencyPair).getLow24hr());
                System.out.println("2"+lowerAsk);
              //  System.out.println(low);

            }
            */

            if (candlesList.size() > 0) {
                ChartData lastCandel = candlesList.get(candlesList.size() - 1);
                System.out.println("Last candel "+lastCandel.getOpen());

                //    candel1 = candlesList.get(candlesList.size() - 4).getOpen().compareTo(candlesList.get(candlesList.size() - 2).getClose());
               //     candel2 = candlesList.get(candlesList.size() - 3).getOpen().compareTo(candlesList.get(candlesList.size() - 2).getClose());
                      candel3 = candlesList.get(candlesList.size() - 2).getOpen().compareTo(candlesList.get(candlesList.size() - 2).getClose());

                System.out.println(candel3);


                    if ( candel3 == 1 && balanceBTC !=null) {
                        System.out.println("-------------");
                        System.out.println("Покупка");
                        try {
                            /// Добавить проврку на баланс
                            Thread.sleep(1000);
                            http.sendPostBuy(currencyPair,String.valueOf(lastCandel.getOpen()),String.valueOf(balanceBTC.divide(lastCandel.getOpen(), 8, 1 / 3)));
                        } catch (Exception e) {
                            System.out.println("Buy error");
                            System.out.println(e);
                        }

                    }

                    if (balanceCurrenc != null){
                        try {
                            /// Добавить проврку на баланс
                            System.out.println("OPEN "+lastCandel.getOpen());
                          http.sendPostSell(currencyPair,String.valueOf(lastCandel.getOpen().multiply(BigDecimal.valueOf(1.01))), String.valueOf(balanceCurrenc));
                        } catch (Exception e) {
                            System.out.println("Sell error");
                            System.out.println(e);
                        }




                    }














            }


        }
    }
}
