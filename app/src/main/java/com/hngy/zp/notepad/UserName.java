package com.hngy.zp.notepad;

public class UserName {
    static String userName;

    public static String getUserName() {
        return userName;
    }

    public static void setUserName(String userName) {
        UserName.userName = userName;
    }
}
