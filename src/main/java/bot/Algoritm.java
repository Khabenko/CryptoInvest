package bot;

import domain.ChartData;
import domain.Ticker;
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
    private HttpURLConnection http  = new HttpURLConnection();


    private int candel1;
    private int candel2;
    private int candel3;



    public void  runAlgoritm (String currencyPair, String candelPeriod, String marga) throws Exception {

        while (true){

        ///   getCandels("BTC_ETH", "7200");
        getCandels(currencyPair, candelPeriod);
        getTicker();
        http.sendPostAvailableAccountBalances();

        //    if (mapBalance.size()>1)
                balanceBTC = new BigDecimal(mapBalance.get("exchange").get("BTC"));
      //      else
        //        balanceBTC = new BigDecimal(0);

            if (tikerMap.size() > 0) {
                Ticker tiker = tikerMap.get(currencyPair);
                lowerAsk = tiker.getLowerAsk();

            }

            if (candlesList.size() > 0) {
                ChartData lastCandel = candlesList.get(candlesList.size() - 1);

                    candel1 = candlesList.get(candlesList.size() - 4).getOpen().compareTo(candlesList.get(candlesList.size() - 2).getClose());
                    candel2 = candlesList.get(candlesList.size() - 3).getOpen().compareTo(candlesList.get(candlesList.size() - 2).getClose());
                    candel3 = candlesList.get(candlesList.size() - 2).getOpen().compareTo(candlesList.get(candlesList.size() - 2).getClose());


                    if (candel1 == 1 && candel2 == 1 && candel3 == 1 && balanceBTC !=null) {
                        System.out.println("-------------");
                        System.out.println("Покупка");
                        try {
                            /// Добавить проврку на баланс
                            http.sendPostBuy(currencyPair,String.valueOf(lowerAsk),String.valueOf(balanceBTC.divide(lowerAsk, 8, 1 / 3)));
                        } catch (Exception e) {
                            System.out.println("Buy error");
                            System.out.println(e);
                        }

                    }

                    if (balanceCurrenc != null){
                        try {
                            /// Добавить проврку на баланс
                         http.sendPostSell(currencyPair,String.valueOf(lowerAsk.multiply(BigDecimal.valueOf(Long.parseLong(marga)))), String.valueOf(balanceCurrenc));
                        } catch (Exception e) {
                            System.out.println("Sell error");
                            System.out.println(e);
                        }




                    }














            }


        }
    }
}
