import bot.Algoritm;

/**
 * Created by M.Khabenko on 3/13/2017.
 */
public class Main {


    public static void main(String[] args)   {
//14400
        try {


            new Algoritm().runAlgoritm("BTC_XMR","14400","1.3");
            new Algoritm().runAlgoritm("BTC_DASH","14400","1.3");
            new Algoritm().runAlgoritm("BTC_ETX","14400","1.3");
        } catch (Exception e) {
            System.out.println("Что-то не так");
            System.out.println(e);
        }

    }


    }
