package bot;

import api.PublicMethods;
import com.google.gson.Gson;
import domain.Ticker;
import service.PoloniexCallBack;

import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Logger;

/**
 * Created by M.Khabenko on 3/15/2017.
 */
public class TickerCallBack implements PoloniexCallBack<Map<String, Ticker>> {
    public static Map<String, Ticker> tikerMap = new TreeMap();
    @Override
    public void success(Map<String, Ticker> response) {


        Gson gson = new Gson();
        String string = gson.toJson(response);
        Logger logger = Logger.getLogger("HI");
     //   logger.info(string);

        tikerMap.putAll(response);


    }

    @Override
    public void fail(String error) {
        System.out.println(error);
    }




    public static Map<String, Ticker> getTicker(){

        PublicMethods publicMethods = new PublicMethods();
        publicMethods.returnTicker(new TickerCallBack());
        return tikerMap;
    }



}

