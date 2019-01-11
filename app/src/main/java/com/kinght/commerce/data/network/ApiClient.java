package com.kinght.commerce.data.network;



import com.kinght.commerce.BuildConfig;
import com.kinght.commerce.data.pref.PrefHelper;
import com.kinght.commerce.utility.Configuration;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public Retrofit retrofit = null;
    private PrefHelper prefHelper;

    @Inject
    public ApiClient(PrefHelper prefHelper) {
        this.prefHelper = prefHelper;
    }

    public Retrofit getClient() {

        if (BuildConfig.DEBUG) {
            Configuration.baseUrl = Configuration.devBaseUrl;
        } else {
            Configuration.baseUrl = Configuration.prodBaseUrl;
        }

        if (retrofit == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .readTimeout(Configuration.readTimeOut, TimeUnit.SECONDS)
                    .connectTimeout(Configuration.connectTimeOut, TimeUnit.SECONDS)
                    .addInterceptor(interceptor)
                    .build();


            retrofit = new Retrofit.Builder()
                    .baseUrl(Configuration.baseUrl)
                    .client(okHttpClient)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }


}

