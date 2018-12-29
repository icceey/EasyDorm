package com.easydorm.easydorm.Utils;

import com.easydorm.easydorm.http.GetRequestInterface;
import com.easydorm.easydorm.http.PostRequestInterface;
import com.easydorm.easydorm.http.TokenInterceptor;
import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpUtil {

    private static Retrofit getRetrofit() {
        return getRetrofit(Constants.Url.baseUrl);
    }

    public static Retrofit getRetrofit(String url) {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new TokenInterceptor())
                .build();
        return getRetrofit(url, client);
    }

    public static Retrofit getRetrofit(String url, OkHttpClient client) {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create());
        if(client != null) {
            builder.client(client);
        }
        return builder.build();
    }

    public static PostRequestInterface getPostRequestInterface() {
        return getRetrofit().create(PostRequestInterface.class);
    }

    public static GetRequestInterface getGetRequestInterface() {
        return getRetrofit().create(GetRequestInterface.class);
    }


}
