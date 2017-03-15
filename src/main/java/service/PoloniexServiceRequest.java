package service;


import domain.Integration;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PoloniexServiceRequest<ResponsePoloniex> {

    private Retrofit retrofit;
    private PoloniexCallBack<ResponsePoloniex> poloniexCallBack;
    private Callback<ResponsePoloniex> callback;

    public PoloniexServiceRequest(PoloniexCallBack<ResponsePoloniex> poloniexCallBack) {
        this.poloniexCallBack = poloniexCallBack;
        callback =  new BaseCallBack();
        builderRetrofit();
    }

    private void builderRetrofit() {
        Retrofit.Builder retrofitBuilder = new Retrofit.Builder();
        retrofitBuilder
                .baseUrl(Integration.HOST)
                .addConverterFactory(GsonConverterFactory.create());
        retrofit = retrofitBuilder.build();
    }

    private class BaseCallBack implements Callback<ResponsePoloniex> {
        @Override
        public void onResponse(Call<ResponsePoloniex> call, Response<ResponsePoloniex> response) {
            if (response.isSuccessful()) {
                poloniexCallBack.success(response.body());
            } else {
                poloniexCallBack.fail(response.message());
            }
        }

        @Override
        public void onFailure(Call<ResponsePoloniex> call, Throwable t) {
            poloniexCallBack.fail(t.getMessage());
        }
    }

    public Retrofit getConfiguration() {
        return retrofit;
    }

    public Callback<ResponsePoloniex> getCallback() {
        return callback;
    }
}
