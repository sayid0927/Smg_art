package com.smg.art.component;



import com.smg.art.ui.activity.AuctionDeatilActivity;
import com.smg.art.ui.activity.ClassifyActivity;
import com.smg.art.ui.activity.GoodsDetailActivity;
import com.smg.art.ui.activity.GuideActivity;
import com.smg.art.ui.activity.MainActivity;
import com.smg.art.ui.activity.SearchActivity;
import com.smg.art.ui.activity.SearchContactsActivity;
import com.smg.art.ui.fragment.AuctionCentreFragment;
import com.smg.art.ui.fragment.AuctionDetailIntroductionFragment;
import com.smg.art.ui.fragment.AuctionFragment;
import com.smg.art.ui.fragment.ClassifyChildFragment;
import com.smg.art.ui.fragment.ContactsFragment;
import com.smg.art.ui.fragment.HomeFragment;
import com.smg.art.ui.fragment.MyFragment;
import com.smg.art.ui.fragment.RecentMessageFragment;
import com.smg.art.ui.login.ForgetPasswordActivity;
import com.smg.art.ui.login.LoginActivity;
import com.smg.art.ui.login.RegisterActivity;
import com.smg.art.ui.personalcenter.CashDepositActivity;
import com.smg.art.ui.personalcenter.MyCollectionActivity;
import com.smg.art.ui.personalcenter.fragemnt.AuctionOrderFragment;

import dagger.Component;


@Component(dependencies = AppComponent.class)
public interface MainComponent {

    MainActivity inject(MainActivity activity);

    HomeFragment inject(HomeFragment homeFragment);

    MyFragment inject(MyFragment myFragment);

    ClassifyActivity  inject (ClassifyActivity classifyActivity);

    SearchActivity inject(SearchActivity searchActivity);

    LoginActivity inject(LoginActivity loginActivity);

    GuideActivity inject(GuideActivity guideActivity);

    RegisterActivity inject(RegisterActivity registerActivity);

    ForgetPasswordActivity inject(ForgetPasswordActivity forgetPasswordActivity);
    ClassifyChildFragment inject (ClassifyChildFragment  classifyChildFragment);

    GoodsDetailActivity inject (GoodsDetailActivity goodsDetailActivity);

    AuctionFragment inject (AuctionFragment auctionFragment);

    AuctionDeatilActivity inject(AuctionDeatilActivity auctionDeatilActivity);

    AuctionDetailIntroductionFragment  inject(AuctionDetailIntroductionFragment auctionDetailIntroductionFragment);

    AuctionCentreFragment inject (AuctionCentreFragment auctionCentreFragment);

    RecentMessageFragment inject (RecentMessageFragment recentMessageFragment);

    AuctionOrderFragment inject(AuctionOrderFragment auctionOrderFragment);

    CashDepositActivity inject(CashDepositActivity cashDepositActivity);

    MyCollectionActivity inject(MyCollectionActivity myCollectionActivity);

    SearchContactsActivity inject (SearchContactsActivity searchContactsActivity);

    ContactsFragment inject(ContactsFragment contactsFragment);

}