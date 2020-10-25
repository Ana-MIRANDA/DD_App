package com.ana.dd_app;

import android.util.Log;


import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class OkhttpUtils {

    public static String getRequest(String url) throws Exception {
        Log.w("tag", "url : " + url );
        OkHttpClient client = new OkHttpClient();
        Request pedido = new Request.Builder().url(url).build();
        Response resposta = client.newCall(pedido).execute();
        if(resposta.code() < 200 || resposta.code() >=300) {
            throw new Exception("Incorrect " + resposta.code());
        } else {
            return resposta.body().string();
        }
    }
}
