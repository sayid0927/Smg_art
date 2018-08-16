package com.smg.art.bean;

import com.chad.library.adapter.base.entity.SectionEntity;

/**
 * Created by Lenovo on 2018/8/15.
 */

public class MySection extends SectionEntity<AddressBookFriendsBean.DataBean> {




    public MySection(boolean isHeader, String header) {
        super(isHeader, header);
    }
    public MySection(AddressBookFriendsBean.DataBean t) {
        super(t);
    }

}
