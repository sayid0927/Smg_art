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


import com.smg.art.base.AuctionBuyerDepositBean;
import com.smg.art.base.AuctionDetailBean;
import com.smg.art.base.Constant;
import com.smg.art.base.HomePageImgBean;
import com.smg.art.bean.AddFriendBean;
import com.smg.art.bean.AddressBookFriendsBean;
import com.smg.art.bean.AuctionOrderBean;
import com.smg.art.bean.CashDepositiBean;
import com.smg.art.bean.CollectionBean;
import com.smg.art.bean.ForgetPasswordBean;
import com.smg.art.bean.LoginBean;
import com.smg.art.bean.PhoneVerifyCodeBean;
import com.smg.art.bean.RegisterBean;
import com.smg.art.bean.SaveCollectsBean;
import com.smg.art.bean.UpLoadBean;
import com.smg.art.bean.SearchMemberBean;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.GET;
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

    /**
     * 会员登录
     */
    @POST(Constant.MEMBER_LOGIN)
    Observable<LoginBean> FetchLogin(@QueryMap Map<String, String> map);

    /**
     * 忘记密码
     */
    @POST(Constant.FINDPASSWORDBYVALIDTECODE)
    Observable<ForgetPasswordBean> FetchForgetPassWord(@QueryMap Map<String, String> map);

    /**
     * 保证金
     */
    @GET(Constant.QUERYAUCTIONBUYERDEPOSITS)
    Observable<CashDepositiBean> FetchCashDeposit(@QueryMap Map<String, String> map);

    /**
     * 我的收藏
     */
    @GET(Constant.QUERYMEMBERCOLLECTS)
    Observable<CollectionBean> FetchCollection(@QueryMap Map<String, String> map);
    /**
     * 首页广告图片列表
     */
    @GET(Constant.HOMEPAGE_GETHOMEPAGEIMG)
    Observable<HomePageImgBean> FetchHomePageImgBean();

    /**
     * 拍卖品详情
     */
    @GET(Constant.HOMEPAGE_GETAUCTIONDETAIL)
    Observable<AuctionDetailBean> FetchHomepageGetauctiondetail(@QueryMap Map<String, String> map);

    /**
     * 新增收藏商品
     */
    @POST(Constant.MEMBERCOLLECTSPAGE_SAVE)
    Observable<SaveCollectsBean> FetchMembercollectspageSave(@QueryMap Map<String, String> map);


    /**
     * 保证金支付
     */
    @POST(Constant.AUCTIONBUYERDEPOSIT_INSERT)
    Observable<AuctionBuyerDepositBean> FetchAuctionBuyerDeposit(@QueryMap Map<String, String> map);

    /**
     * 获取搜索列表
     */
    @POST(Constant.HOMEPAGE_GETHOTWORDSLIST)
    Observable<Response<ResponseBody>> FetchHotWordsList(@QueryMap Map<String, String> map);


    /**
     * 搜索平台会员
     */
    @POST(Constant.ADDRESSBOOK_SEARCHMEMBER)
    Observable<SearchMemberBean> FetchSearchMember(@QueryMap Map<String, String> map);

    /**
     * 新增通讯录好友
     */
    @POST(Constant.ADDRESSBOOK_ADDFRIEND)
    Observable<AddFriendBean> FetchAddFriend(@QueryMap Map<String, String> map);

    /**
     * 删除通讯录好友
     */
    @POST(Constant.ADDRESSBOOK_UPDATEFRIENDRELATION)
    Observable<AddFriendBean> FetchUpdateFriendRelation(@QueryMap Map<String, String> map);

    /**
     * 查询通讯录好友列表
     */
    @POST(Constant.ADDRESSBOOK_GETADDRESSBOOKFRIENDS)
    Observable<AddressBookFriendsBean> FetchAddressBookFriends(@QueryMap Map<String, String> map);

    /**
     * 我的拍卖
     */
    @GET(Constant.QUERYAUCTIONINFOLIST)
    Observable<AuctionOrderBean> FetchAuctionOrder(@QueryMap Map<String, String> map);

    /**
     * 上传头像
     */
    @POST(Constant.USERHEADIMG)
    Observable<UpLoadBean> FetchUploadHead(@QueryMap Map<String, String> map);

}