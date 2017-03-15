package bot;

import api.PublicMethods;
import com.google.gson.Gson;
import domain.ChartData;
import service.PoloniexCallBack;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by M.Khabenko on 3/15/2017.
 */

public class ChartDataCallBack implements PoloniexCallBack<List<ChartData>> {
   public static ArrayList<ChartData> candlesList = new ArrayList<>();

    @Override
    public void success(List<ChartData> response) {
        Gson gson = new Gson();
        String string = gson.toJson(response);
        Logger logger = Logger.getLogger("HI");
     //   logger.info(string);

        candlesList.addAll(response);


    }

    @Override
    public void fail(String error) {
        System.out.println(error);
    }

    public static ArrayList<ChartData> getCandels(String currencyPair, String period){
        Date curentdate = new Date();
        String strat = String.valueOf((curentdate.getTime()/1000)-21600); // Дата за который возвразяет свечки

        PublicMethods publicMethods = new PublicMethods();

        publicMethods.returnChartData(new ChartDataCallBack(),currencyPair,strat,String.valueOf(curentdate.getTime()),period);


        return candlesList;
    }
}
