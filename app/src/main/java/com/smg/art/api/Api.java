/**
 * Copyright 2016 JustWayward Team
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.smg.art.api;


import android.text.TextUtils;

import com.google.gson.Gson;
import com.smg.art.base.Constant;
import com.smg.art.bean.Apk_UpdateBean;
import com.smg.art.bean.ForgetPasswordBean;
import com.smg.art.bean.LoginBean;
import com.smg.art.bean.PhoneVerifyCodeBean;
import com.smg.art.bean.RegisterBean;

import java.util.HashMap;
import java.util.Map;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

public class Api {

    public static Api instance;
    //用户token
    public static String JWTTOKEN;
    private ApiService service;

    public Api(OkHttpClient okHttpClient) {

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()) // 添加Rx适配器
                .client(okHttpClient)
                .baseUrl(Constant.API_BASE_URL)
                .build();
        service = retrofit.create(ApiService.class);

    }

    public static Api getInstance(OkHttpClient okHttpClient) {
        if (instance == null)
            instance = new Api(okHttpClient);
        return instance;
    }

    public static Map<String, String> getMap(String... s) {
        Map<String, String> map = new HashMap<>();
        if (s.length % 2 != 0) {
            return null;
        } else {
            for (int i = 0; i < s.length; i++) {
                map.put(s[i], s[++i]);
            }
        }
        if (!TextUtils.isEmpty(JWTTOKEN)) {
            map.put("access_token", JWTTOKEN);
        }
        return map;
    }

    public Observable<Apk_UpdateBean> Fetch_Apk_Update() {
        return service.Fetch_Apk_Update();
    }

    /**
     * 会员注册
     */
    public Observable<RegisterBean> FetchREGISTER(String... s) {
        return service.FetchREGISTER(getMap(s));
    }

    /**
     * 获取短信验证码
     */
    public Observable<PhoneVerifyCodeBean> FetchPhoneVerifyCode(String... s) {
        return service.FetchPhoneVerifyCode(getMap(s));
    }

    /**
     * 会员登录
     *
     * @param s
     * @return
     */
    public Observable<LoginBean> FetchLogin(String... s) {
        return service.FetchLogin(getMap(s));
    }

    /**
     * 忘记密码
     */
    public Observable<ForgetPasswordBean> FetchForgetPassword(String... s) {
        return service.FetchForgetPassWord(getMap(s));
    }
}
