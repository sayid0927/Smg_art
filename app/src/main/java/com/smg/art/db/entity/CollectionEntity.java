package com.smg.art.db.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Lenovo on 2018/7/26.
 */

@Entity
public class CollectionEntity {

    @Id(autoincrement = true) //自增
    private Long id; //主键
    private String userName;
    private String userStart;
    private  String userDate;
    private  String userSiz;
    private  String userWeChat;
    private  String userUuid;

    @Generated(hash = 1922835025)
    public CollectionEntity(Long id, String userName, String userStart,
            String userDate, String userSiz, String userWeChat, String userUuid) {
        this.id = id;
        this.userName = userName;
        this.userStart = userStart;
        this.userDate = userDate;
        this.userSiz = userSiz;
        this.userWeChat = userWeChat;
        this.userUuid = userUuid;
    }
    @Generated(hash = 1951715304)
    public CollectionEntity() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUserName() {
        return this.userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getUserStart() {
        return this.userStart;
    }
    public void setUserStart(String userStart) {
        this.userStart = userStart;
    }
    public String getUserDate() {
        return this.userDate;
    }
    public void setUserDate(String userDate) {
        this.userDate = userDate;
    }
    public String getUserSiz() {
        return this.userSiz;
    }
    public void setUserSiz(String userSiz) {
        this.userSiz = userSiz;
    }
    public String getUserWeChat() {
        return this.userWeChat;
    }
    public void setUserWeChat(String userWeChat) {
        this.userWeChat = userWeChat;
    }
    public String getUserUuid() {
        return this.userUuid;
    }
    public void setUserUuid(String userUuid) {
        this.userUuid = userUuid;
    }



}
