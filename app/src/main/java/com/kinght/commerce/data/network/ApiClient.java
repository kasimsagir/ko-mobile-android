package com.kinght.commerce.data.network;



import android.content.Context;

import com.kinght.commerce.BuildConfig;
import com.kinght.commerce.data.pref.PrefHelper;
import com.kinght.commerce.utility.CommonUtils;
import com.kinght.commerce.utility.Configuration;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public Retrofit retrofit = null;
    private PrefHelper prefHelper;
    private Context context;

    @Inject
    public ApiClient(PrefHelper prefHelper,Context context) {
        this.prefHelper = prefHelper;
        this.context=context;
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

            int cacheSize = 10 * 1024 * 1024;
            Cache cache = new Cache(context.getCacheDir(), cacheSize);


            final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .readTimeout(Configuration.readTimeOut, TimeUnit.SECONDS)
                    .connectTimeout(Configuration.connectTimeOut, TimeUnit.SECONDS)
                    .addInterceptor(interceptor)
                    .cache(cache)
                    .addInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            Request request = chain.request().newBuilder()
                                    .addHeader("udid", CommonUtils.getUdid(context))
                                    .addHeader("AuthorizationKey",prefHelper.getAuthorizationKey())
                                    .build();
                            return chain.proceed(request);
                        }
                    })
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

