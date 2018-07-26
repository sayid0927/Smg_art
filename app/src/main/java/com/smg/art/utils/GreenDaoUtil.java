package com.smg.art.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.smg.art.db.database.CollectionEntityDao;
import com.smg.art.db.database.DaoMaster;
import com.smg.art.db.database.DaoSession;
import com.smg.art.db.entity.CollectionEntity;


/**
 * Description:
 * author         xulei
 * Date           2017/7/5
 */

public class GreenDaoUtil {
    private static DaoSession daoSession;
    private static SQLiteDatabase database;

    /**
     * 初始化数据库
     * 建议放在Application中执行
     */
    public static void initDataBase(Context context) {
        //通过DaoMaster的内部类DevOpenHelper，可得到一个SQLiteOpenHelper对象。
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(context, "com.smg.art.db", null); //数据库名称
        database = devOpenHelper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(database);
        daoSession = daoMaster.newSession();
    }

    public static DaoSession getDaoSession() {
        return daoSession;
    }

    public static SQLiteDatabase getDatabase() {
        return database;
    }

    public  static CollectionEntityDao getCollectionEntityDao(){
        return  daoSession.getCollectionEntityDao();
    }

}
