package tradingapi;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.codec.binary.Hex;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public  class HttpURLConnection {
      static String secret = "edf8219430736ebdd4294f17a69d249fa609d2ae983d6ddd22aea43b75c7c6653d1fd7e27d7d9857ae7884d014349fcd30304236c3ea6812b0c4af258712295b";
      static String key ="A3VGOXC7-JJZVO6VA-OXWWEHBQ-D7IXEXGE";
    public  static Map<String,Map<String,String>> mapBalance;

    public static void main(String[] args) throws Exception {

        HttpURLConnection http = new HttpURLConnection();

        System.out.println("\nTesting 2 - Send Http POST request");
     ///   http.sendPostBuy("BTC","1","1");
        http.sendPostAvailableAccountBalances();
        System.out.println(mapBalance);


    }


    // HTTP POST request
    public void sendPostBuy(String currencyPair, String rate, String amount ) throws Exception {

        String url = "https://poloniex.com/tradingApi";
        String nonce = String.valueOf(System.currentTimeMillis());
        String queryArgs = "command=buy&currencyPair="+currencyPair+"&rate="+rate+"&amount="+amount+"&nonce=" + nonce;

        Mac shaMac = Mac.getInstance("HmacSHA512");
        SecretKeySpec keySpec = new SecretKeySpec(secret.getBytes(), "HmacSHA512");
        shaMac.init(keySpec);
        final byte[] macData = shaMac.doFinal(queryArgs.getBytes());
        String sign = Hex.encodeHexString(macData);

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost post = new HttpPost(url);
        post.addHeader("Key", key);
        post.addHeader("Sign", sign);

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("command", "buy"));
        params.add(new BasicNameValuePair("currencyPair", currencyPair));
        params.add(new BasicNameValuePair("rate", rate));
        params.add(new BasicNameValuePair("amount", amount));
        params.add(new BasicNameValuePair("nonce", nonce));
        post.setEntity(new UrlEncodedFormEntity(params));

        CloseableHttpResponse response = httpClient.execute(post);
        HttpEntity responseEntity = response.getEntity();
        System.out.println(response.getStatusLine());
        System.out.println(EntityUtils.toString(responseEntity));


    }

    public  void sendPostSell(String currencyPair, String rate, String amount ) throws Exception {


        String url = "https://poloniex.com/tradingApi";
        String nonce = String.valueOf(System.currentTimeMillis());
        String queryArgs = "command=sell&currencyPair="+currencyPair+"&rate="+rate+"&amount="+amount+"&nonce=" + nonce;

        Mac shaMac = Mac.getInstance("HmacSHA512");
        SecretKeySpec keySpec = new SecretKeySpec(secret.getBytes(), "HmacSHA512");
        shaMac.init(keySpec);
        final byte[] macData = shaMac.doFinal(queryArgs.getBytes());
        String sign = Hex.encodeHexString(macData);

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost post = new HttpPost(url);
        post.addHeader("Key", key);
        post.addHeader("Sign", sign);

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("command", "sell"));
        params.add(new BasicNameValuePair("currencyPair", currencyPair));
        params.add(new BasicNameValuePair("rate", rate));
        params.add(new BasicNameValuePair("amount", amount));
        params.add(new BasicNameValuePair("nonce", nonce));
        post.setEntity(new UrlEncodedFormEntity(params));

        CloseableHttpResponse response = httpClient.execute(post);
        HttpEntity responseEntity = response.getEntity();
        System.out.println(response.getStatusLine());
        System.out.println(EntityUtils.toString(responseEntity));



    }


    public void sendPostAvailableAccountBalances() throws Exception {

        String url = "https://poloniex.com/tradingApi";
        String nonce = String.valueOf(System.currentTimeMillis());

            String queryArgs = "command=returnAvailableAccountBalances&nonce=" + nonce;



        Mac shaMac = Mac.getInstance("HmacSHA512");
        SecretKeySpec keySpec = new SecretKeySpec(secret.getBytes(), "HmacSHA512");
        shaMac.init(keySpec);
        final byte[] macData = shaMac.doFinal(queryArgs.getBytes());
        String sign = Hex.encodeHexString(macData);

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost post = new HttpPost(url);
        post.addHeader("Key", key);
        post.addHeader("Sign", sign);

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("command", "returnAvailableAccountBalances"));
        params.add(new BasicNameValuePair("nonce", nonce));
        post.setEntity(new UrlEncodedFormEntity(params));

        CloseableHttpResponse response = httpClient.execute(post);
        HttpEntity responseEntity = response.getEntity();
        System.out.println(response.getStatusLine());
     // System.out.println(EntityUtils.toString(responseEntity));
        String resp = EntityUtils.toString(responseEntity);
        System.out.println(resp);

        Gson gson = new Gson();
        mapBalance = gson.fromJson(resp, new TypeToken<Map<String,HashMap<String,String>>>(){}.getType());










    }














}