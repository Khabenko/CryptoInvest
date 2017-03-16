import bot.Algoritm;

/**
 * Created by M.Khabenko on 3/13/2017.
 */
public class Main {


    public static void main(String[] args)  {

        try {
            new Algoritm().runAlgoritm("BTC_DASH","7200","1.3");
        } catch (Exception e) {
            System.out.println("Что-то не так");
            System.out.println(e);
        }

    }


    }
