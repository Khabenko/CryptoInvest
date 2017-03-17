package bot;

import api.PublicMethods;
import com.google.gson.Gson;
import domain.ChartData;
import service.PoloniexCallBack;

import java.util.*;
import java.util.logging.Logger;

/**
 * Created by M.Khabenko on 3/15/2017.
 */

public class ChartDataCallBack implements PoloniexCallBack<List<ChartData>> {
   public static Map<String,ArrayList<ChartData>> candlesMap = new HashMap<>();
   private String currency;
   private ArrayList<ChartData> candlesList = new ArrayList<>();

    public ChartDataCallBack(String currency) {
        this.currency = currency;
    }

    @Override
    public void success(List<ChartData> response) {
        Gson gson = new Gson();
        String string = gson.toJson(response);
        Logger logger = Logger.getLogger("HI");
      //  logger.info(string);
        try {

            candlesList.addAll(response);
            candlesMap.put(currency, candlesList);
        }catch (Exception e)
        {
            System.out.println("Ошибка при получени масива свечей");
            e.printStackTrace();
        }

    }

    @Override
    public void fail(String error) {
        System.out.println(error);
    }

    public  Map<String,ArrayList<ChartData>> getCandlesMap (String currencyPair, String period){

               Date curentdate = new Date();
               String strat = String.valueOf((curentdate.getTime()/1000)-57600); // Дата за который возвразяет свечки

         PublicMethods publicMethods = new PublicMethods();
         publicMethods.returnChartData(new ChartDataCallBack(currencyPair),currencyPair,strat,String.valueOf(curentdate.getTime()),period);

          return candlesMap;
    }

}
