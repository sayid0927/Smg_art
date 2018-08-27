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
import com.smg.art.base.AnnouncementAuctionListBean;
import com.smg.art.base.AuctionBuyerDepositBean;
import com.smg.art.base.AuctionDetailBean;
import com.smg.art.bean.CardUrlBean;
import com.smg.art.base.Constant;
import com.smg.art.bean.ComfirmOrderBean;
import com.smg.art.bean.FindCustomerServiceBean;
import com.smg.art.bean.HomePageImgBean;
import com.smg.art.bean.LogisticInfo;
import com.smg.art.bean.PlayIntroductionBean;
import com.smg.art.bean.SearchAreaBean;
import com.smg.art.bean.AddBankCardBean;
import com.smg.art.bean.AddFriendBean;
import com.smg.art.bean.AddressBookFriendsBean;
import com.smg.art.bean.AddressListBean;
import com.smg.art.bean.AuctionCenterBean;
import com.smg.art.bean.AuctionGoodsBean;
import com.smg.art.bean.AuctionOrderBean;
import com.smg.art.bean.BalanceOfPayBean;
import com.smg.art.bean.CashDepositiBean;
import com.smg.art.bean.ChangeMobilePhoneBean;
import com.smg.art.bean.ChangeNickBean;
import com.smg.art.bean.ChangePassWordBean;
import com.smg.art.bean.ChangeTraderPasswordBean;
import com.smg.art.bean.CheckBankCardBean;
import com.smg.art.bean.CollectionBean;
import com.smg.art.bean.ComplaintBean;
import com.smg.art.bean.CreatWordsBean;
import com.smg.art.bean.CurrencyExchangeRateBean;
import com.smg.art.bean.DeleteCollectionBean;
import com.smg.art.bean.ForgetPasswordBean;
import com.smg.art.bean.HotWordsListBean;
import com.smg.art.bean.LoginBean;
import com.smg.art.bean.OderDetailBean;
import com.smg.art.bean.OrderMessageBean;
import com.smg.art.bean.PersonalCenterBean;
import com.smg.art.bean.PhoneVerifyCodeBean;
import com.smg.art.bean.ProofreadCodeBean;
import com.smg.art.bean.ReChargeBean;
import com.smg.art.bean.RefundBean;
import com.smg.art.bean.RegisterBean;
import com.smg.art.bean.SaveCollectsBean;
import com.smg.art.bean.SearchMemberBean;
import com.smg.art.bean.ServiceBean;
import com.smg.art.bean.SystemMessageBean;
import com.smg.art.bean.UpLoadBean;
import com.smg.art.bean.UserRulaBean;
import com.smg.art.bean.WalletBalanceBean;
import com.smg.art.bean.WithDrawBean;
import com.smg.art.utils.LocalAppConfigUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
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
        if (!TextUtils.isEmpty(LocalAppConfigUtil.getInstance().getAccessToken())) {
            map.put("access_token", LocalAppConfigUtil.getInstance().getJsessionId());
        }
        return map;
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
     * 获取图形验证码
     *
     * @param
     */
    public Observable<ResponseBody> FetchPictureCode(String... s) {
        return service.FetchPictureCode(getMap(s));
    }

    /**
     * 获取用户协议
     */
    public Observable<UserRulaBean> FetchUserRulaCode(String... s) {
        return service.FetchUserRulaCode(getMap(s));
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
     * 重新获取Token
     */
    public Call<LoginBean> FetchreFreshToken(String... s) {
        return service.FetchreFreshToken(getMap(s));
    }


    /**
     * 忘记密码
     */
    public Observable<ForgetPasswordBean> FetchForgetPassword(String... s) {
        return service.FetchForgetPassWord(getMap(s));
    }

    /**
     * 首页广告图片列表
     */
    public Observable<HomePageImgBean> FetchHomePageImgBean() {
        return service.FetchHomePageImgBean();
    }


    /**
     * 拍卖品详情
     */
    public Observable<AuctionDetailBean> FetchHomepageGetauctiondetail(String... s) {
        return service.FetchHomepageGetauctiondetail(getMap(s));
    }

    /**
     * 新增收藏商品
     */
    public Observable<SaveCollectsBean> FetchMembercollectspageSave(String... s) {
        return service.FetchMembercollectspageSave(getMap(s));
    }

    /**
     * 保证金支付
     */
    public Observable<AuctionBuyerDepositBean> FetchAuctionBuyerDeposit(String... s) {
        return service.FetchAuctionBuyerDeposit(getMap(s));
    }
    /**
     * 获取搜索列表
     */
    public Observable<HotWordsListBean> FetchHotWordsList(String... s) {
        return service.FetchHotWordsList(getMap(s));
    }


    /**
     * 保证金
     *
     * @param s
     * @return
     */
    public Observable<CashDepositiBean> FetchCashDeposit(String... s) {
        return service.FetchCashDeposit(getMap(s));
    }


    /**
     * 我的收藏
     *
     * @param s
     * @return
     */
    public Observable<CollectionBean> FetchCollection(String... s) {
        return service.FetchCollection(getMap(s));
    }
    /**
     * 搜索平台会员
     *
     * @param s
     * @return
     */
    public Observable<SearchMemberBean> FetchSearchMember(String... s) {
        return service.FetchSearchMember(getMap(s));
    }
    /**
     * 新增通讯录好友
     *
     * @param s
     * @return
     */
    public Observable<AddFriendBean> FetchAddFriend(String... s) {
        return service.FetchAddFriend(getMap(s));
    }
    /**
     * 删除通讯录好友
     *
     * @param s
     * @return
     */
    public Observable<AddFriendBean> FetchUpdateFriendRelation(String... s) {
        return service.FetchUpdateFriendRelation(getMap(s));
    }

    /**
     * 查询通讯录好友列表
     *
     * @param s
     * @return
     */
    public Observable<AddressBookFriendsBean> FetchAddressBookFriends(String... s) {
        return service.FetchAddressBookFriends(getMap(s));
    }

    /**
     * 参拍记录
     */
    public Observable<AuctionOrderBean> FetchAuctionOrder(String... s) {
        return service.FetchAuctionOrder(getMap(s));
    }

    /**
     * 上传头像
     */
    public Observable<UpLoadBean> FetchUploadHead(List<MultipartBody.Part> parts) {
        return service.FetchUploadHead(parts);
    }

    /**
     * 上传图片
     */
    public Observable<CardUrlBean> FetchUploadFile(List<MultipartBody.Part> parts) {
        return service.FetchUploadFile(parts);
    }

    /**
     * 首页公告期分页查询
     */
    public Observable<AnnouncementAuctionListBean> FetchAnnouncementAuctionList(String... s) {
        return service.FetchAnnouncementAuctionList(getMap(s));
    }

    /**
     * 上传头像
     */
    public Observable<WalletBalanceBean> FetchMyWalletBalance(String... s) {
        return service.FetchMyWalletBalance(getMap(s));
    }

    /**
     * 查询钱包收支
     */
    public Observable<BalanceOfPayBean> FetchBalanceOfPay(String... s) {
        return service.FetchBalanceOfPay(getMap(s));
    }

    /**
     * 首页搜索框查询
     */
    public Observable<AnnouncementAuctionListBean> FetchAuctionListByName(String... s) {
        return service.FetchAuctionListByName(getMap(s));
    }



    /**
     * 添加银行卡
     */
    public Observable<AddBankCardBean> FetchAddBankCard(String... s) {
        return service.FetchAddBankCard(getMap(s));
    }


    /**
     * 新增热门搜索字段
     */
    public Observable<CreatWordsBean> FetchCreatWordsBean(String... s) {
        return service.FetchCreatWordsBean(getMap(s));
    }

    /**
     * 搜索字段删除接口
     */
    public Observable<CreatWordsBean> FetchDeleteWordById(String... s) {
        return service.FetchDeleteWordById(getMap(s));
    }

    /**
     * 新增实名认证
     */
    public Observable<CreatWordsBean> FetchMemberAuthSave(String... s) {
        return service.FetchMemberAuthSave(getMap(s));
    }



    /**
     * 查看银行卡
     */
    public Observable<CheckBankCardBean> FetchCheckBankCard(String... s) {
        return service.FetchCheckBankCard(getMap(s));
    }

    /**
     * 提现
     */
    public Observable<WithDrawBean> FetchWithDraw(String... s) {
        return service.FetchWithDraw(getMap(s));
    }

    /**
     * 拍卖汇率
     */
    public Observable<CurrencyExchangeRateBean> FetchCurrencyExchangeRate(String... s) {
        return service.FetchCurrencyExchangeRate(getMap(s));
    }

    /**
     * 充值
     */
    public Observable<ReChargeBean> FetchReCharge(String... s) {
        return service.FetchReCharge(getMap(s));
    }

    /**
     * 个人中心
     */
    public Observable<PersonalCenterBean> FetchPersonalCenter(String... s) {
        return service.FetchPersonalCenter(getMap(s));
    }

    /**
     * 修改昵称
     */
    public Observable<ChangeNickBean> FetchChangeNick(String... s) {
        return service.FetchChangeNick(getMap(s));
    }

    /**
     * 修改手机号
     */
    public Observable<ChangeMobilePhoneBean> FetchChangeMobilePhone(String... s) {
        return service.FetchChangeMobilePhone(getMap(s));
    }

    /**
     * 校对验证码
     */
    public Observable<ProofreadCodeBean> FetchProofreadCode(String... s) {
        return service.FetchProofreadCode(getMap(s));
    }

    /**
     * 修改登录密码
     */
    public Observable<ChangePassWordBean> FetchChangePassWord(String... s) {
        return service.FetchChangePassWord(getMap(s));
    }

    /**
     * 修改交易密码
     */
    public Observable<ChangeTraderPasswordBean> FetchChangeTraderPassword(String... s) {
        return service.FetchChangeTraderPassword(getMap(s));
    }

    /**
     * 修改交易密码
     */
    public Observable<AuctionGoodsBean> FetchAuctionList(String... s) {
        return service.FetchAuctionList(getMap(s));
    }
    /**
     * 获取系统消息列表
     */
    public Observable<SystemMessageBean> FetchGetListFront(String... s) {
        return service.FetchGetListFront(getMap(s));
    }

    /**
     * 获取订单消息列表
     */
    public Observable<OrderMessageBean> FetchOrderLidtFront(String... s) {
        return service.FetchOrderLidtFront(getMap(s));
    }
    /**
     * 竞价列表以及最高价
     */
    public Observable<AuctionCenterBean> FetchAuctionCenterList(String... s) {
        return service.FetchAuctionCenterList(getMap(s));
    }
    /**
     * 竞价列表以及最高价
     */
    public Observable<AuctionCenterBean> FetchCreatBidding(String... s) {
        return service.FetchCreatBidding(getMap(s));
    }
    /**
     * 查询客服信息
     */
    public Observable<FindCustomerServiceBean> FetchFindCustomerService(String... s) {
        return service.FetchFindCustomerService(getMap(s));
    }

    /**
     * 查询单个拍卖记录
     */
    public Observable<OderDetailBean> FetchQueryAuctionInfoService(String... s) {
        return service.FetchQueryAuctionInfoService(getMap(s));
    }

    /**
     * 查看单个投诉记录
     */
    public Observable<RefundBean> FetchRefundService(String... s) {
        return service.FetchRefundService(getMap(s));
    }
    /**
     * 验证交易密码(Gumq)
     */
    public Observable<RefundBean> FetchvalidteTradePwd(String... s) {
        return service.FetchvalidteTradePwd(getMap(s));
    }

    /**
     * 投诉
     */
    public Observable<ComplaintBean> FetchComplaintService(String... s) {
        return service.FetchComplaintService(getMap(s));
    }

    /**
     * 删除商品
     */
    public Observable<DeleteCollectionBean> FetchDeleteCollectionService(String... s) {
        return service.FetchDeleteCollectionService(getMap(s));
    }
    /**
     * 获取收货地址
     */
    public Observable<AddressListBean> FetchAddressList(String... s) {
        return service.FetchAddressList(getMap(s));
    }
    /**
     * 收货地址删除
     */
    public Observable<AddressListBean> FetchDeleteAddress(String... s) {
        return service.FetchDeleteAddress(getMap(s));
    }

    /**
     * 新增收货地址
     */
    public Observable<AddressListBean> FetchCreateAddress(String... s) {
        return service.FetchCreateAddress(getMap(s));
    }
    /**
     * 修改收货地址
     */
    public Observable<AddressListBean> FetchUpdateAddress(String... s) {
        return service.FetchUpdateAddress(getMap(s));
    }
    /**
     * 获取省市区信息
     */
    public Observable<SearchAreaBean> FetchRegionInfo(String... s) {
        return service.FetchRegionInfo(getMap(s));
    }
    /**
     * 获取竞拍支付说明
     */
    public Observable<PlayIntroductionBean> FetchPayIntroduction(String... s) {
        return service.FetchPayIntroduction(getMap(s));
    }
    /**
     * 查询投诉记录
     */
    public Observable<AuctionOrderBean> FetchComplainAuctionInfoList(String... s) {
        return service.FetchComplainAuctionInfoList(getMap(s));
    }


    /**
     * 查看物流
     */
    public Observable<LogisticInfo> FetchLogisticInfoService(String... s) {
        return service.FetchLogisticInfoService(getMap(s));
    }

    /**
     * 查看客服信息
     */
    public Observable<ServiceBean> FetchServiceService(String... s) {
        return service.FetchServiceService(getMap(s));
    }
    /**
     * 确认收货
     */
    public Observable<ServiceBean> FetchConfirmBuyerGoods(String... s) {
        return service.FetchConfirmBuyerGoods(getMap(s));
    }

    /**
     * 确认支付
     */
    public Observable<ComfirmOrderBean> FetchComfirmOrderService(String... s) {
        return service.FetchComfirmOrderService(getMap(s));
    }
    /**
     * 商品订单支付
     */
    public Observable<ComfirmOrderBean> FetchPayBuyerGoods(String... s) {
        return service.FetchPayBuyerGoods(getMap(s));
    }
    /**
     * 跳转到保证金规则页面
     */
    public Observable<ComfirmOrderBean> FetchTomarginrulespage(String... s) {
        return service.FetchTomarginrulespage(getMap(s));
    }
    /**
     * 跳转到用户竞拍服务协议页面
     */
    public Observable<ComfirmOrderBean> FetchToAuctionAgreementPage(String... s) {
        return service.FetchToAuctionAgreementPage(getMap(s));
    }

}
