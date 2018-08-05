
package com.smg.art.module;


import android.annotation.SuppressLint;

import com.blankj.utilcode.utils.NetworkUtils;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.orhanobut.logger.Logger;
import com.smg.art.api.Api;
import com.smg.art.base.BaseApplication;
import com.smg.art.base.Constant;
import com.smg.art.bean.LoginBean;
import com.smg.art.module.persistentcookiejar.ClearableCookieJar;
import com.smg.art.module.persistentcookiejar.PersistentCookieJar;
import com.smg.art.module.persistentcookiejar.cache.SetCookieCache;
import com.smg.art.module.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.smg.art.utils.L;
import com.smg.art.utils.LocalAppConfigUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;

@Module
public class ApiModule {


    @Provides
    public OkHttpClient provideOkHttpClient() {

        //cookie
        ClearableCookieJar cookieJar =
                new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(BaseApplication.baseApplication));

        File httpCacheDir = new File(BaseApplication.getBaseApplication().getCacheDir(), "response");
        int cacheSize = 10 * 1024 * 1024;// 10 MiB
        Cache cache = new Cache(httpCacheDir, cacheSize);

        OkHttpClient.Builder builder = new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(20 * 1000, TimeUnit.MILLISECONDS)
                .readTimeout(20 * 1000, TimeUnit.MILLISECONDS)
                .addInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR)
                .retryOnConnectionFailure(true) // 失败重发
                .cache(cache)
                .cookieJar(cookieJar)
                .addInterceptor(new TokenInterceptor())
                .addInterceptor(new LoggingInterceptor());
        return builder.build();

    }

    Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            CacheControl.Builder cacheBuilder = new CacheControl.Builder();
            cacheBuilder.maxAge(0, TimeUnit.SECONDS);
            cacheBuilder.maxStale(365, TimeUnit.DAYS);
            CacheControl cacheControl = cacheBuilder.build();
            Request request = chain.request();
            if (!NetworkUtils.isAvailableByPing()) {
                request = request.newBuilder()
                        .cacheControl(cacheControl)
                        .build();
            }
            Response originalResponse = chain.proceed(request);
            if (NetworkUtils.isAvailableByPing()) {
                int maxAge = 0;//read from cache
                return originalResponse.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public ,max-age=" + maxAge)
                        .build();
            } else {
                int maxStale = 60 * 60 * 24 * 28;//tolerate 4-weeks stale
                return originalResponse.newBuilder()
                        .removeHeader("Prama")
                        .header("Cache-Control", "poublic, only-if-cached, max-stale=" + maxStale)
                        .build();
            }
        }
    };

    class LoggingInterceptor implements Interceptor {
        @SuppressLint("DefaultLocale")
        @Override
        public Response intercept(Interceptor.Chain chain) throws IOException {
            Request request = chain.request();
            long t1 = System.nanoTime();//请求发起的时间
            Map<String,String> map = new HashMap<>();
            for(int i=0;i<request.url().queryParameterNames().size();i++){
                map.put(request.url().queryParameterName(i),request.url().queryParameterValue(i));
            }

            Response response = chain.proceed(request);
            long t2 = System.nanoTime();//收到响应的时间
            ResponseBody responseBody = response.peekBody(1024 * 1024);
            Logger.e(String.format("接收响应: [%s] %n返回json:【%s】%n请求参数: [%s] %n响应时间[%.1fms]" ,
                    response.request().url(),
                    responseBody.string(),
                    transMapToString(map),
                    (t2 - t1) / 1e6d
            ));
            return response;
        }
    }
     static String transMapToString(Map map){
        java.util.Map.Entry entry;
        StringBuffer sb = new StringBuffer();
        for(Iterator iterator = map.entrySet().iterator(); iterator.hasNext();)
        {
            entry = (java.util.Map.Entry)iterator.next();
            sb.append(entry.getKey().toString()).append( " == " ).append(null==entry.getValue()?"":
                    entry.getValue().toString()).append (iterator.hasNext() ? "\n" : "");
        }
        return sb.toString();
    }

    class TokenInterceptor implements Interceptor {

        private final Charset UTF8 = Charset.forName("UTF-8");

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            // try the request
            Response originalResponse = chain.proceed(request);
            ResponseBody responseBody = originalResponse.body();

            BufferedSource source = responseBody.source();
            source.request(Long.MAX_VALUE); // Buffer the entire body.
            Buffer buffer = source.buffer();
            Charset charset = UTF8;
            MediaType contentType = responseBody.contentType();
            if (contentType != null) {
                charset = contentType.charset(UTF8);
            }

            String bodyString = buffer.clone().readString(charset);
            JSONObject jsonObj = null;
            try {
                jsonObj = new JSONObject(bodyString);
                if (jsonObj.has("status")) {
                    int status = jsonObj.optInt("status");
                    if (status == 10000) {
                        OkHttpClient client = new OkHttpClient();
                        FormBody.Builder builder = new FormBody.Builder();
                        builder.add("account", LocalAppConfigUtil.getInstance().getUserTelephone());
                        builder.add("password", LocalAppConfigUtil.getInstance().getPassword());
                        RequestBody     requestBody = builder.build();

                        Request tokRequest = new Request.Builder()
                                .url(Constant.API_BASE_URL + Constant.MEMBER_LOGIN)
                                .post(requestBody)
                                .build();

                        Call call = client.newCall(tokRequest);
                        Response response = call.execute();
                        String responseStr = response.body().string();
                        LoginBean newToken = new Gson().fromJson(responseStr, LoginBean.class);

                        if (newToken.getData() != null && newToken.getData().getRCToken() != null) {
//                           LocalAppConfigUtil.getInstance().setRCToken(newToken.getData().getRCToken());
                            Logger.t("TAG").e(newToken.getMsg());
                           Logger.t("TAG").e("lod  Token>>>>>   "+LocalAppConfigUtil.getInstance().getAccessToken());
                            Logger.t("TAG").e("new  Token>>>>>   "+newToken.getData().getRCToken());
                            HttpUrl originalHttpUrl = request.url();
                            Logger.t("TAG").e("lod  Url>>>>>   "+originalHttpUrl);
                            HttpUrl url = originalHttpUrl.newBuilder()
                                    .setQueryParameter("access_token",newToken.getData().getRCToken())
                                    .build();

                            Logger.t("TAG").e("new Url>>>>>   "+url);

                            Request newRequest = request.newBuilder()
                                    .url(url)
                                    .build();

                            return chain.proceed(newRequest);
                        }
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return originalResponse;
        }
    }

    @Provides
    protected Api provideService(OkHttpClient okHttpClient) {
        return Api.getInstance(okHttpClient);
    }

}