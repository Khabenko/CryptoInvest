package bot;

/**
 * Created by Maka on 17.03.2017.
 */
public class CurrencyThread extends Thread {
    private String currencyPair;
    private String candelPeriod;
    private String marga;

    public CurrencyThread (String currencyPair, String candelPeriod, String marga){
       this.currencyPair = currencyPair;
       this.candelPeriod =candelPeriod;
       this.marga =marga;
    }



    @Override
    public void run() {

        try {
            new Algoritm().runAlgoritm(currencyPair,candelPeriod,marga);
        } catch (Exception e) {
            System.out.println("Проблема в потоке");
            System.out.println(e);
            e.printStackTrace();
        }
    }
}
