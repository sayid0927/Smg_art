package com.smg.art.db.entity;

import android.net.Uri;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Lenovo on 2018/7/26.
 */

@Entity
public class RongUserInfoEntity {

    @Id(autoincrement = true) //自增
    private Long id; //主键
    private String userId;
    private String userName;
    private String  userPortraitUri;
    @Generated(hash = 669699934)
    public RongUserInfoEntity(Long id, String userId, String userName,
            String userPortraitUri) {
        this.id = id;
        this.userId = userId;
        this.userName = userName;
        this.userPortraitUri = userPortraitUri;
    }
    @Generated(hash = 1969482173)
    public RongUserInfoEntity() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUserId() {
        return this.userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getUserName() {
        return this.userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getUserPortraitUri() {
        return this.userPortraitUri;
    }
    public void setUserPortraitUri(String userPortraitUri) {
        this.userPortraitUri = userPortraitUri;
    }



}
