import api.PublicMethods;
import com.google.gson.Gson;
import domain.LoanOrders;
import domain.Ticker;
import service.PoloniexCallBack;

import java.math.BigDecimal;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by M.Khabenko on 3/13/2017.
 */
public class Main {

    public static void main(String[] args) {

        PublicMethods publicMethods = new PublicMethods();
     //   publicMethods.returnLoanOrders(new LoanOrdersCallBack(), "BTC");
        publicMethods.returnTicker(new TickerCallBack());





    }


    private static class LoanOrdersCallBack implements PoloniexCallBack<LoanOrders> {

        @Override
        public void success(LoanOrders response) {
            Gson gson = new Gson();
            String string = gson.toJson(response);
            Logger logger = Logger.getLogger("HI");
            logger.info(string);




            PublicMethods publicMethods = new PublicMethods();
      //      publicMethods.returnLoanOrders(new TestsCallBack(), "BTC");

        }

        @Override
        public void fail(String error) {
            System.out.println(error);
        }
    }



    private static class TickerCallBack implements PoloniexCallBack<Map<String, Ticker>> {

        @Override
        public void success(Map<String, Ticker> response) {


            Gson gson = new Gson();
            String string = gson.toJson(response);
            Logger logger = Logger.getLogger("HI");
       //     logger.info(string);

            Ticker ticker = new Ticker();
            ticker = response.get("BTC_DASH");


            System.out.println(ticker.getPercentChange().multiply(BigDecimal.valueOf(100)));


                PublicMethods publicMethods = new PublicMethods();
                publicMethods.returnTicker(new TickerCallBack());


        }

        @Override
        public void fail(String error) {
            System.out.println(error);
        }
    }









    }
