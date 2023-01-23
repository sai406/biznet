package com.mstech.gamesnatcherz.utils;

import android.content.Context;
import android.content.SharedPreferences;

/** HARISH GADDAM
 *  Created on 29/01/2021
 * */

public class SharePref {

    private SharedPreferences sharedPreferences;

    public SharePref(Context mContext) {
        sharedPreferences = mContext.getSharedPreferences("gamesnatcherz", Context.MODE_PRIVATE);
    }

    public boolean isLogin() {
        return sharedPreferences.getBoolean("isLogin", false);
    }

    public void setisLogin(boolean islogin) {
        sharedPreferences.edit().putBoolean("isLogin", islogin).apply();
    }

    public int getMemberID() {
        return sharedPreferences.getInt("MemberID", 0);
    }

    public void setMemberID(int MemberID) {
        sharedPreferences.edit().putInt("MemberID", MemberID).apply();
    }

    public void setFirstName(String firstName) {
        sharedPreferences.edit().putString("firstName", firstName).apply();
    }

    public String getFirstName() {
        return sharedPreferences.getString("firstName", "");
    }

    public void setLastName(String lastName) {
        sharedPreferences.edit().putString("lastName", lastName).apply();
    }

    public String getLastName() {
        return sharedPreferences.getString("lastName", "");
    }

    public String getProfileImagePath() {
        return sharedPreferences.getString("strProfileImagePath", "");
    }

    public void setProfileImagePath(String strProfileImagePath) {
        sharedPreferences.edit().putString("strProfileImagePath", strProfileImagePath).apply();
    }

    public void setUserName(String userName) {
        sharedPreferences.edit().putString("userName", userName).apply();
    }

    public String getUserName() {
        return sharedPreferences.getString("userName", "");
    }

    public void setEmailID(String emailID) {
        sharedPreferences.edit().putString("emailID", emailID).apply();
    }

    public String getEmailID() {
        return sharedPreferences.getString("emailID", "");
    }

    public int getOrgID() {
        return sharedPreferences.getInt("OrgID", 0);
    }

    public void setOrgID(int OrgID) {
        sharedPreferences.edit().putInt("OrgID", OrgID).apply();
    }

    public void clearSharedPreferences() {
        sharedPreferences.edit().clear().apply();
        sharedPreferences.edit().putBoolean("isLogin", false).apply();
    }

}


































