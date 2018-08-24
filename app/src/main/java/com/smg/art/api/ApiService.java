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


import com.smg.art.base.AnnouncementAuctionListBean;
import com.smg.art.base.AuctionBuyerDepositBean;
import com.smg.art.base.AuctionDetailBean;
import com.smg.art.bean.CardUrlBean;
import com.smg.art.base.Constant;
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

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
import rx.Observable;


public interface ApiService {

    /**
     * 下载 图片
     */

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
     * 查询客服信息
     */
    @GET(Constant.PICTURECODE)
    Observable<ResponseBody> FetchPictureCode(@QueryMap Map<String, String> map);


    /**
     * 获取用户协议
     */
    @GET(Constant.TOREGISTERRULEPAGE)
    Observable<UserRulaBean> FetchUserRulaCode(@QueryMap Map<String, String> map);


    /**
     * 会员登录
     */
    @POST(Constant.MEMBER_LOGIN)
    Observable<LoginBean> FetchLogin(@QueryMap Map<String, String> map);

    /**
     * 重新获取Token
     */
    @POST(Constant.MEMBER_LOGIN)
    Call<LoginBean> FetchreFreshToken(@QueryMap Map<String, String> map);


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
    @GET(Constant.HOMEPAGE_GETHOTWORDSLIST)
    Observable<HotWordsListBean> FetchHotWordsList(@QueryMap Map<String, String> map);

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
    @Multipart
    @POST(Constant.USERHEADIMG)
    Observable<UpLoadBean> FetchUploadHead(@Part List<MultipartBody.Part> partLis);

    /**
     * 上传图片
     */
    @Multipart
    @POST(Constant.UPLOAD_UPLOADFILE)
    Observable<CardUrlBean> FetchUploadFile(@Part List<MultipartBody.Part> partLis);
    /**
     * 首页公告期分页查询
     */
    @GET(Constant.HOMEPAGE_GETANNOUNCEMENTAUCTIONLIST)
    Observable<AnnouncementAuctionListBean> FetchAnnouncementAuctionList(@QueryMap Map<String, String> map);

    /**
     * 查询余额
     */
    @GET(Constant.QUERYWALLETINFO)
    Observable<WalletBalanceBean> FetchMyWalletBalance(@QueryMap Map<String, String> map);

    /**
     * 查询钱包收支
     */
    @GET(Constant.QUERYWALLETRECORDS)
    Observable<BalanceOfPayBean> FetchBalanceOfPay(@QueryMap Map<String, String> map);

    /**
     * 首页搜索框查询
     */
    @GET(Constant.HOMEPAGE_GETAUCTIONLISTBYNAME)
    Observable<AnnouncementAuctionListBean> FetchAuctionListByName(@QueryMap Map<String, String> map);

    /**
     * 添加银行卡
     */
    @POST(Constant.INSERTORUPDATEBANKNOINFO)
    Observable<AddBankCardBean> FetchAddBankCard(@QueryMap Map<String, String> map);

    /**
     * 新增热门搜索字段
     */
    @POST(Constant.HOMEPAGE_CREATWORDS)
    Observable<CreatWordsBean> FetchCreatWordsBean(@QueryMap Map<String, String> map);
    /**
     * 搜索字段删除接口
     */
    @GET(Constant.HOMEPAGE_DELETEWORDBYID)
    Observable<CreatWordsBean> FetchDeleteWordById(@QueryMap Map<String, String> map);

    /**
     * 新增实名认证
     */
    @POST(Constant.MEMBERAUTH_SAVE)
    Observable<CreatWordsBean> FetchMemberAuthSave(@QueryMap Map<String, String> map);

    /**
     * 查看银行卡
     */
    @GET(Constant.GETUSERBANKCARD)
    Observable<CheckBankCardBean> FetchCheckBankCard(@QueryMap Map<String, String> map);

    /**
     * 查看银行卡
     */
    @POST(Constant.BANKWITHCASH)
    Observable<WithDrawBean> FetchWithDraw(@QueryMap Map<String, String> map);


    /**
     * 获取拍卖汇率
     */
    @GET(Constant.GETCURRENCYEXCHANGERATE)
    Observable<CurrencyExchangeRateBean> FetchCurrencyExchangeRate(@QueryMap Map<String, String> map);

    /**
     * 充值
     */
    @POST(Constant.RECHARGEBANKCARD)
    Observable<ReChargeBean> FetchReCharge(@QueryMap Map<String, String> map);

    /**
     * 个人中心
     */
    @POST(Constant.QUERYSETTINGPARAM)
    Observable<PersonalCenterBean> FetchPersonalCenter(@QueryMap Map<String, String> map);

    /**
     * 修改昵称
     */
    @POST(Constant.UPDATEMEMBERNAME)
    Observable<ChangeNickBean> FetchChangeNick(@QueryMap Map<String, String> map);

    /**
     * 修改手机
     */
    @POST(Constant.UPDATEPHONE)
    Observable<ChangeMobilePhoneBean> FetchChangeMobilePhone(@QueryMap Map<String, String> map);

    /**
     * 修改手机
     */
    @POST(Constant.VALIDATECODE)
    Observable<ProofreadCodeBean> FetchProofreadCode(@QueryMap Map<String, String> map);

    /**
     * 修改昵称
     */
    @POST(Constant.UPDATELOGINPWD)
    Observable<ChangePassWordBean> FetchChangePassWord(@QueryMap Map<String, String> map);

    /**
     * 修改交易密码
     */
    @POST(Constant.UPDATETRADEPWD)
    Observable<ChangeTraderPasswordBean> FetchChangeTraderPassword(@QueryMap Map<String, String> map);

    /**
     * 拍卖列表
     */
    @GET(Constant.GETAUCTIONLISTBYNAME)
    Observable<AuctionGoodsBean> FetchAuctionList(@QueryMap Map<String, String> map);


    /**
     * 获取系统消息列表
     */
    @POST(Constant.NEWSFRONT_GETLISTFRONT)
    Observable<SystemMessageBean> FetchGetListFront(@QueryMap Map<String, String> map);


    /**
     * 获取订单消息列表
     */
    @POST(Constant.AUCTIONNEWSFRONT_GETLISTFRONT)
    Observable<OrderMessageBean> FetchOrderLidtFront(@QueryMap Map<String, String> map);


    /**
     * 竞价列表以及最高价
     */
    @GET(Constant.AUCTIONBIDDING_GETAUCTIONCENTERLIST)
    Observable<AuctionCenterBean> FetchAuctionCenterList(@QueryMap Map<String, String> map);
    /**
     * 竞价
     */
    @POST(Constant.AUCTIONBIDDING_CREATBIDDING)
    Observable<AuctionCenterBean> FetchCreatBidding(@QueryMap Map<String, String> map);

    /**
     * 查询客服信息
     */
    @GET(Constant.MEMBER_FINDCUSTOMERSERVICE)
    Observable<FindCustomerServiceBean> FetchFindCustomerService(@QueryMap Map<String, String> map);

    /**
     * 查询单个拍卖记录
     */
    @GET(Constant.QUERYAUCTIONINFO)
    Observable<OderDetailBean> FetchQueryAuctionInfoService(@QueryMap Map<String, String> map);

    /**
     * 查看单个投诉记录
     */
    @GET(Constant.QUERYCOMPLAINAUCTIONINFO)
    Observable<RefundBean> FetchRefundService(@QueryMap Map<String, String> map);

    /**
     * 验证交易密码(Gumq)
     */
    @POST(Constant.MEMBER_VALIDTETRADEPWD)
    Observable<RefundBean> FetchvalidteTradePwd(@QueryMap Map<String, String> map);
    /**
     * 投诉
     */
    @POST(Constant.SAVEAUCTIONCOMPLAIN)
    Observable<ComplaintBean> FetchComplaintService(@QueryMap Map<String, String> map);

    /**
     * 删除收藏
     */
    @POST(Constant.MEMBERCOLLECTSPAGEDELETE)
    Observable<DeleteCollectionBean> FetchDeleteCollectionService(@QueryMap Map<String, String> map);


    /**
     * 获取收货地址
     */
    @GET(Constant.DELIVERYADDRESS_GETDELIVERYADDRESS)
    Observable<AddressListBean> FetchAddressList(@QueryMap Map<String, String> map);

    /**
     * 收货地址删除
     */
    @GET(Constant.DELIVERYADDRESS_DELETEDELIVERYADDRESS)
    Observable<AddressListBean> FetchDeleteAddress(@QueryMap Map<String, String> map);

    /**
     * 新增收货地址
     */
    @POST(Constant.DELIVERYADDRESS_CREATEDELIVERYADDRESS)
    Observable<AddressListBean> FetchCreateAddress(@QueryMap Map<String, String> map);
    /**
     * 修改收货地址
     */
    @POST(Constant.DELIVERYADDRESS_UPDATEDELIVERYADDRESS)
    Observable<AddressListBean> FetchUpdateAddress(@QueryMap Map<String, String> map);
    /**
     * 获取省市区信息
     */
    @GET(Constant.DELIVERYADDRESS_GETREGIONINFO)
    Observable<SearchAreaBean> FetchRegionInfo(@QueryMap Map<String, String> map);

    /**
     * 获取竞拍支付说明
     */
    @POST(Constant.SYSCONFIGFRONT_GETPAYINTRODUCTION)
    Observable<PlayIntroductionBean> FetchPayIntroduction(@QueryMap Map<String, String> map);

    /**
     * 查询投诉记录
     */
    @GET(Constant.AUCTIONPAGE_QUERYCOMPLAINAUCTIONINFOLIST)
    Observable<AuctionOrderBean> FetchComplainAuctionInfoList(@QueryMap Map<String, String> map);

    /**
     * 查看物流
     */
    @GET(Constant.GETLOGISTICSINFOBYNO)
    Observable<LogisticInfo> FetchLogisticInfoService(@QueryMap Map<String, String> map);

    /**
     * 查看客服信息
     */
    @GET(Constant.FINDCUSTOMERSERVICE)
    Observable<ServiceBean> FetchServiceService(@QueryMap Map<String, String> map);

    /**
     * 确认收货
     */
    @POST(Constant.CONFIRMBUYERGOODS)
    Observable<ServiceBean> FetchConfirmBuyerGoods(@QueryMap Map<String, String> map);

}