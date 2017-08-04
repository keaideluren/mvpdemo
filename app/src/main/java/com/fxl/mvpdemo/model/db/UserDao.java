package com.fxl.mvpdemo.model.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.fxl.mvpdemo.LeplusApplication;
import com.fxl.mvpdemo.model.bean.UserInfo;

import java.util.List;

/**
 * Created by Administrator on 2017/2/15.
 * 用户信息持久化
 */

public class UserDao extends SQLiteOpenHelper {
    private static UserDao instance;

    private String ID = "Id";
    private String PHONE = "Phone";
    private String ACCOUNTNAME = "AccountName";
    private String SIG = "sig";
    private String HEADPICURL = "HeadPicUrl";
    private String AUTOKEY = "AutoKey";
    private String GENDER = "Gender";
    private String BIRTHDAY = "Birthday";
    private String SIGNATURE = "Signature";
    private String ADDRESS = "Address";
    private String EMOTIONAL = "Emotional";
    private String OCCUPATION = "Occupation";
    private String REGISTE_RPROGRESS = "RegisterProgress";
    private String WALLIMG = "WallImg";
    private String ROLE = "Role";
    private String PHOTOS = "Photos";
    private String ISFRIEND = "IsFriend";
    private String IS_THRID_LOGIN = "IsThirdLogin";
    private String TOKEN = "Token";

    private static final String TABLE_NAME = "UserInfo";
    private static final String DB_NAME = "xunlebao.db";

    private UserDao(Context context) {
        super(context, DB_NAME, null, 1);
    }

    private static final byte[] lock = new byte[0];

    public static UserDao getInstance() {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new UserDao(LeplusApplication.getInstance().getApplicationContext());
                }
            }
        }
        return instance;
    }

    /**
     * 创建表
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlCreateTable1 = "create table if not exists " + TABLE_NAME + "( " +
            ID + " int primary key, " +
            PHONE + " varchar, " +
            ACCOUNTNAME + " varchar, " +
            SIG + " varchar, " +
            HEADPICURL + " varchar, " +
            AUTOKEY + " varchar, " +
            GENDER + " varchar, " +
            BIRTHDAY + " varchar, " +
            SIGNATURE + " varchar, " +
            ADDRESS + " varchar, " +
            EMOTIONAL + " varchar, " +
            OCCUPATION + " varchar, " +
            REGISTE_RPROGRESS + " varchar, " +
            WALLIMG + " varchar, " +
            ROLE + " int default(0), " +
            PHOTOS + " varchar default('[]'), " +
            ISFRIEND + " int default(0), " +
            IS_THRID_LOGIN + " int default(0), " +
            TOKEN + " varchar" +
            ");";
        db.execSQL(sqlCreateTable1);
//        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /**
     * 插入数据
     */
    public boolean addUser(UserInfo user) {
        ContentValues values = new ContentValues();
        values.put(ID,user.getId());
        values.put(ACCOUNTNAME, user.getAccountName());
        values.put(ADDRESS, user.getAddress());
        values.put(AUTOKEY, user.getAutoKey());
        values.put(BIRTHDAY, user.getBirthday());
        values.put(EMOTIONAL, user.getEmotional());
        values.put(GENDER, user.getGender());
        values.put(HEADPICURL, user.getHeadPicUrl());
        values.put(IS_THRID_LOGIN, user.isThirdLogin());
        values.put(ISFRIEND, user.isFriend());
        values.put(OCCUPATION, user.getOccupation());
        values.put(PHONE, user.getPhone());
        values.put(PHOTOS,new Gson().toJson(user.getPhotos()));
        values.put(REGISTE_RPROGRESS, user.getRegisterProgress());
//        values.put(SIG, user.getSig());
        values.put(SIGNATURE, user.getSignature());
        values.put(TOKEN, user.getToken());
        values.put(WALLIMG, user.getWallImg());
        SQLiteDatabase db = getWritableDatabase();
        long insert = db.insert(TABLE_NAME, null, values);
        db.close();
        return insert > 0;
    }

    /**
     * 根据用户名查找用户
     */
    public UserInfo findUserByUserId(int id) {
        SQLiteDatabase db = getReadableDatabase();
        String sql = "select * from " + TABLE_NAME + " where " + ID + "= ?";
        Cursor cursor = db.rawQuery(sql, new String[]{"" + id});

        UserInfo user = null;
        if (cursor.moveToNext()) {
            user = new UserInfo();
            user.setId(id);
            user.setAccountName(cursor.getString(cursor.getColumnIndex(ACCOUNTNAME)));
            user.setAddress(cursor.getString(cursor.getColumnIndex(ADDRESS)));
            user.setAutoKey(cursor.getString(cursor.getColumnIndex(AUTOKEY)));
            user.setBirthday(cursor.getString(cursor.getColumnIndex(BIRTHDAY)));
            user.setEmotional(cursor.getString(cursor.getColumnIndex(EMOTIONAL)));
            user.setGender(cursor.getInt(cursor.getColumnIndex(GENDER)));
            user.setHeadPicUrl(cursor.getString(cursor.getColumnIndex(HEADPICURL)));
            user.setIsFriend(cursor.getInt(cursor.getColumnIndex(ISFRIEND)) == 1);
            user.setOccupation(cursor.getString(cursor.getColumnIndex(OCCUPATION)));
            user.setPhone(cursor.getString(cursor.getColumnIndex(PHONE)));
            List<String> photoList = new Gson().fromJson(cursor.getString(cursor.getColumnIndex(PHOTOS))
                , new TypeToken<List<String>>() {}.getType());
            user.setPhotos(photoList);
            user.setRegisterProgress(cursor.getInt(cursor.getColumnIndex(REGISTE_RPROGRESS)));
            user.setRole(cursor.getInt(cursor.getColumnIndex(ROLE)));
            user.setSig(cursor.getString(cursor.getColumnIndex(SIG)));
            user.setSignature(cursor.getString(cursor.getColumnIndex(SIGNATURE)));
            user.setThirdLogin(cursor.getInt(cursor.getColumnIndex(IS_THRID_LOGIN)) == 1);
            user.setToken(cursor.getString(cursor.getColumnIndex(TOKEN)));
            user.setWallImg(cursor.getString(cursor.getColumnIndex(WALLIMG)));
        }
        cursor.close();
        db.close();
        return user;
    }

    /**
     * 更新用户信息
     */
    public boolean updateUser(UserInfo user) {
        ContentValues values = new ContentValues();
        values.put(ID,user.getId());
        values.put(ACCOUNTNAME, user.getAccountName());
        values.put(ADDRESS, user.getAddress());
        values.put(AUTOKEY, user.getAutoKey());
        values.put(BIRTHDAY, user.getBirthday());
        values.put(EMOTIONAL, user.getEmotional());
        values.put(GENDER, user.getGender());
        values.put(HEADPICURL, user.getHeadPicUrl());
        values.put(IS_THRID_LOGIN, user.isThirdLogin());
        values.put(ISFRIEND, user.isFriend());
        values.put(OCCUPATION, user.getOccupation());
        values.put(PHONE, user.getPhone());
        values.put(PHOTOS,new Gson().toJson(user.getPhotos()));
        values.put(REGISTE_RPROGRESS, user.getRegisterProgress());
//        values.put(SIG, user.getSig());
        values.put(SIGNATURE, user.getSignature());
        values.put(TOKEN, user.getToken());
        values.put(WALLIMG, user.getWallImg());
        SQLiteDatabase db = getWritableDatabase();
        long update = db.update(TABLE_NAME, values, ID + "=?", new String[]{"" + user.getId()});
        db.close();
        return update > 0;
    }

    /**
     * 添加或更新用户
     */
    public boolean updateOrInsert(UserInfo user) {
        if (findUserByUserId(user.getId()) != null) {
            return updateUser(user);
        } else {
            return addUser(user);
        }
    }

    /**
     * 删除用户
     */
    public boolean deleteUser(UserInfo user) {
        SQLiteDatabase db = getWritableDatabase();
        int delete = db.delete(TABLE_NAME, ID + "=?", new String[]{"" + user.getId()});
        return delete > 0;
    }
}
