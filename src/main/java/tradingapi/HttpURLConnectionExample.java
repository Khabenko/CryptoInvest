package tradingapi;

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
import java.util.List;

public  class HttpURLConnectionExample {
      private static String secret = "edf8219430736ebdd4294f17a69d249fa609d2ae983d6ddd22aea43b75c7c6653d1fd7e27d7d9857ae7884d014349fcd30304236c3ea6812b0c4af258712295b";
      private static String key ="A3VGOXC7-JJZVO6VA-OXWWEHBQ-D7IXEXGE";

    public static void main(String[] args) throws Exception {

        HttpURLConnectionExample http = new HttpURLConnectionExample();

        System.out.println("\nTesting 2 - Send Http POST request");
        http.sendPost();

    }


    // HTTP POST request
    private void sendPost() throws Exception {


        String url = "https://poloniex.com/tradingApi";
        String nonce = String.valueOf(System.currentTimeMillis());
        String queryArgs = "command=returnBalances&nonce=" + nonce;

        Mac shaMac = Mac.getInstance("HmacSHA512");
        SecretKeySpec keySpec = new SecretKeySpec(secret.getBytes(), "HmacSHA512");
        shaMac.init(keySpec);
        final byte[] macData = shaMac.doFinal(queryArgs.getBytes());
        String sign = Hex.encodeHexString(macData);

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost post = new HttpPost(url);
        post.addHeader("Key", key);
        post.addHeader("Sign", sign);

        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("command", "returnBalances"));
        params.add(new BasicNameValuePair("nonce", nonce));
        post.setEntity(new UrlEncodedFormEntity(params));

        CloseableHttpResponse response = httpClient.execute(post);
        HttpEntity responseEntity = response.getEntity();
        System.out.println(response.getStatusLine());
        System.out.println(EntityUtils.toString(responseEntity));
    }







    public static String hmac512Digest(String msg, String keyString) {

        Mac shaMac;
        try {
            shaMac = Mac.getInstance("HmacSHA512");
            SecretKeySpec  keySpec = new SecretKeySpec(keyString.getBytes(), "HmacSHA512");

            shaMac.init(keySpec);
            final byte[] macData = shaMac.doFinal(msg.getBytes());
            return Hex.encodeHexString(macData); //again with try/catch for InvalidKeyException

        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        return null;
    }








    }