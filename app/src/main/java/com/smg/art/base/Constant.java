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
package com.smg.art.base;

public class Constant {
    public static final int STATE_UNKNOWN = 0;
    public static final int STATE_LOADING = 1;
    public static final int STATE_ERROR = 2;
    public static final int STATE_EMPTY = 3;
    public static final int STATE_SUCCESS = 4;

    public static final String BaseImgUrl = "http://192.168.1.56:8080/art-world";
    public static final String API_BASE_URL = "http://192.168.1.56:8080/art-world/";



    /**
     * 会员注册
     */
    public static final String MEMBER_REGISTER = "member/register";
    /***
     * 获取短信验证码
     */
    public static final String MEMBER_GETPHONEVERIFYCODE = "member/getPhoneVerifyCode";

    /**
     * 会员登录
     */
    public static final String MEMBER_LOGIN = "member/login";
    //忘记密码
    public static final String FINDPASSWORDBYVALIDTECODE = "member/findPasswordByValidteCode";
    //获取保证金
    public static final String QUERYAUCTIONBUYERDEPOSITS = "auctionBuyerDeposit/queryAuctionBuyerDeposits";
    //我的收藏
    public static final String QUERYMEMBERCOLLECTS = "memberCollectsPage/queryMemberCollects";
    /**
     * 首页广告图片列表
     */
    public static final String HOMEPAGE_GETHOMEPAGEIMG = "homePage/getHomePageList";
    /**
     * 拍卖品详情
     */
    public static final String HOMEPAGE_GETAUCTIONDETAIL = "homePage/getAuctionDetail";
    /**
     * 新增收藏商品
     */
    public static final String MEMBERCOLLECTSPAGE_SAVE = "memberCollectsPage/save";
    /**
     * 保证金支付
     */
    public static final String AUCTIONBUYERDEPOSIT_INSERT = "auctionBuyerDeposit/insert";
    /**
     * 获取搜索列表
     */
    public static final String HOMEPAGE_GETHOTWORDSLIST = "homePage/getHotWordsList";
    /**
     * 搜索平台会员
     */
    public static final String  ADDRESSBOOK_SEARCHMEMBER = "AddressBook/searchMember";
    /**
     * 新增通讯录好友
     */
    public static final String  ADDRESSBOOK_ADDFRIEND = "AddressBook/addFriend";
    /**
     * 查询通讯录好友列表
     */
    public static final String  ADDRESSBOOK_GETADDRESSBOOKFRIENDS = "AddressBook/getAddressBookFriends";
    /**
     * 删除通讯录好友
     */
    public static final String  ADDRESSBOOK_UPDATEFRIENDRELATION = "AddressBook/updateFriendRelation";





    //参拍记录
    public static final String QUERYAUCTIONINFOLIST = "auctionpage/queryAuctionInfoList";

    //上传头像
    public static final String USERHEADIMG = "upload/userHeadImg";

    //我的钱包查询余额
    public static final String QUERYWALLETINFO = "wallet/queryWalletInfo";

    //我的钱包收支
    public static final String QUERYWALLETRECORDS = "wallet/queryWalletRecords";



    public static String LOCAL_APP_CONFIG_FILE_NAME = "smgconfig";//本地应用配置文件名，存储些全局变量


}
