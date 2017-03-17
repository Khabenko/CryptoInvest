package bot;

import domain.ChartData;
import domain.Ticker;

import java.math.BigDecimal;

import static bot.ChartDataCallBack.candlesList;
import static bot.ChartDataCallBack.getCandels;
import static bot.TickerCallBack.getTicker;
import static bot.TickerCallBack.tikerMap;
import static java.math.BigDecimal.ROUND_DOWN;

/**
 * Created by M.Khabenko on 3/15/2017.
 */
public class GetInfo {
    public static void main(String[] args) throws InterruptedException {
        BigDecimal balanceBTC = new BigDecimal("0.010000000");
        BigDecimal balanceDASH = new BigDecimal("0.000000000");
        BigDecimal sale = new BigDecimal("0");
        BigDecimal buy = new BigDecimal("0");


        boolean order = false;

        int candel1;
        int candel2;
        int candel3;

        int candelO;
        int candelC;


        //  while (true) {
        getCandels("BTC_ETH", "7200");
        getTicker();
        Thread.sleep(3000);

        if (tikerMap.size()>0) {
            Ticker tiker = tikerMap.get("BTC_DASH");
            //      System.out.println(tiker.getLast());
        }

        if (candlesList.size()>0) {
            ChartData chartData = candlesList.get(candlesList.size()-1);

            for (int i = 5; i <candlesList.size() ; i++) {


                candel1 =  candlesList.get(i-3).getOpen().compareTo(candlesList.get(i-3).getClose());
                candel2 =  candlesList.get(i-2).getOpen().compareTo(candlesList.get(i-2).getClose());
                candel3 =  candlesList.get(i-1).getOpen().compareTo(candlesList.get(i-1).getClose());

                 if ( candel1 ==1 && candel2 ==1&&candel3 ==1&&order==false) {
                    System.out.println("-------------");
                    System.out.println("Покупка");
                    balanceDASH = balanceBTC.divide(candlesList.get(i).getOpen(),8,1/3);
                    buy = candlesList.get(i).getOpen();
                    System.out.println("Цена закупки: "+candlesList.get(i).getOpen());
                    System.out.println("Количество закупкаи: "+balanceBTC.divide(candlesList.get(i).getOpen(),8,1/3));
                    sale = candlesList.get(i).getOpen().multiply(BigDecimal.valueOf(1.3));
                    System.out.println("Цена sale: "+sale);
                    order = true;
                    balanceBTC = BigDecimal.valueOf(0);
                    System.out.println("BTC:"+balanceBTC);
                    System.out.println("DASH:"+balanceDASH);


                }

                candelO =  candlesList.get(i).getOpen().compareTo(sale);
                candelC =  candlesList.get(i).getClose().compareTo(sale);



                if (candelC >= 0 && order == true) {
                    System.out.println("------------");
                    System.out.println("Продажа");
                    System.out.println("Цена продажи:"+sale);
                    System.out.println("Количество продажи:"+balanceDASH);
                    System.out.println("Количество BTC:"+balanceBTC);

                    balanceBTC = sale.multiply(balanceDASH).setScale(8,ROUND_DOWN);
                    sale = BigDecimal.valueOf(0);
                    order = false;
                    balanceDASH = BigDecimal.valueOf(0);

                    System.out.println("BTC:"+balanceBTC);
                    System.out.println("DASH:"+balanceDASH);


                } else if (candelO >=0&& order == true){
                    System.out.println("------------");
                    System.out.println("Продажа");
                    System.out.println("Цена продажи:"+sale);
                    System.out.println("Количество продажи:"+balanceDASH);
                    System.out.println("Количество BTC:"+balanceBTC);

                    balanceBTC = sale.multiply(balanceDASH).setScale(8,ROUND_DOWN);
                    sale = BigDecimal.valueOf(0);
                    order = false;
                    balanceDASH = BigDecimal.valueOf(0);

                    System.out.println("BTC:"+balanceBTC);
                    System.out.println("DASH:"+balanceDASH);

                }




                //        }
//   System.out.println(chartData.getOpen());
            }
            System.out.println("-----------------");
            System.out.println("Итого:");
            System.out.println("BTC:"+balanceBTC);
            System.out.println("DASH:"+balanceDASH);


            Thread.sleep(1000);


        }
    }






















}
