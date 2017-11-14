package cn.bingoogolapple.refreshlayout.demo.engine;


import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by leo on 16/10/22.
 * httpclietn
 */

public class WBHttpClient {

    private static Gson mGson = new GsonBuilder().disableHtmlEscaping().create();
    public static OkHttpClient getHttpClient() {
        // 添加证书
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        try {
            client
                    .addInterceptor(new LogsInterceptor())
                    .retryOnConnectionFailure(true)
                    .connectTimeout(10, TimeUnit.SECONDS);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return client.build();
    }

    /**
     * LOG
     */
    public static class LogInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Response response = chain.proceed(chain.request());
            okhttp3.MediaType mediaType = response.body().contentType();
            String content = response.body().string();
            //            Buffer buffer = new Buffer();
            //            response.request().body().writeTo(buffer);
            //            String oldParamsJson = buffer.readUtf8();
            //            buffer.close();
            //      Log.i("TAG", "请求参数：" + oldParamsJson);
            Log.i("TAG", "请求参数：" +  response.request().body().toString());
            Log.i("TAG", "请求结果：" + content);
            return response.newBuilder()
                    .body(okhttp3.ResponseBody.create(mediaType, content))
                    .build();
        }
    }

    public static class LogsInterceptor implements Interceptor {

        public  String TAG = "LogInterceptor";

        @Override
        public okhttp3.Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            long startTime = System.currentTimeMillis();
            okhttp3.Response response = chain.proceed(chain.request());
            long endTime = System.currentTimeMillis();
            long duration=endTime-startTime;
            okhttp3.MediaType mediaType = response.body().contentType();
            String content = response.body().string();
            Log.i(TAG,"\n");
            Log.i(TAG,"----------Start----------------");
            Log.i(TAG, "| "+request.toString());
            String method=request.method();
            if("POST".equals(method)){
                StringBuilder sb = new StringBuilder();
                if (request.body() instanceof FormBody) {
                    FormBody body = (FormBody) request.body();
                    for (int i = 0; i < body.size(); i++) {
                        sb.append(body.encodedName(i) + "=" + body.encodedValue(i) + ",");
                    }
                    sb.delete(sb.length() - 1, sb.length());
                    Log.i(TAG, "| RequestParams:{"+sb.toString()+"}");
                }
            }
            Log.i(TAG, "| Response:" + content);
            Log.i(TAG,"----------End:"+duration+"毫秒----------");
            return response.newBuilder()
                    .body(okhttp3.ResponseBody.create(mediaType, content))
                    .build();
        }
    }




}


