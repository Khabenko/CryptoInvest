import bot.CurrencyThread;

/**
 * Created by M.Khabenko on 3/13/2017.
 */
public class Main {


    public static void main(String[] args)   {

    ///    CurrencyThread currencyThread1 = new CurrencyThread("BTC_XMR","300","1.3");
    //    currencyThread1.start();

        CurrencyThread currencyThread2 = new CurrencyThread("BTC_DASH","300","1.3");
        currencyThread2.start();

   //     CurrencyThread currencyThread3 = new CurrencyThread("BTC_ETH","300","1.3");
    //    currencyThread3.start();


//14400

/*
        try {


            new Algoritm().runAlgoritm("BTC_XMR","14400","1.3");
            new Algoritm().runAlgoritm("BTC_DASH","14400","1.3");
            new Algoritm().runAlgoritm("BTC_ETX","14400","1.3");
        } catch (Exception e) {
            System.out.println("Что-то не так");
            System.out.println(e);
        }
*/
    }


    }
