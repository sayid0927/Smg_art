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


import com.smg.art.base.Constant;
import com.smg.art.bean.Apk_UpdateBean;
import com.smg.art.bean.PhoneVerifyCodeBean;
import com.smg.art.bean.RegisterBean;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
import rx.Observable;


public interface ApiService {

    /**
     * 下载 图片
     */
    @GET
    Observable<Response<ResponseBody>> downloadPicFromNet(@Url String imgUrl);

    /**
     * 会员注册
     */
    @POST(Constant.MEMBER_REGISTER)
    Observable<RegisterBean> FetchREGISTER(@QueryMap Map<String, String> map);

    /**
     * 获取短信验证码
     */
    @POST(Constant.MEMBER_GETPHONEVERIFYCODE)
    Observable<PhoneVerifyCodeBean> FetchPhoneVerifyCode(@QueryMap Map<String, String> map);


}