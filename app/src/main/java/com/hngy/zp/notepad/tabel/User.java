package com.hngy.zp.notepad.tabel;

import org.litepal.LitePal;
import org.litepal.crud.LitePalSupport;

import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.List;

public class User extends LitePalSupport {
    int id;
    String userName_zp;
    String userPassword_zp;
    String question_zp;
    String ans_zp;
    int isCheck_zp;
    private List<MyLog> myLogs=new ArrayList<>();
    private List<NotePad> list=new ArrayList<>();

    public int getIsCheck_zp() {
        return isCheck_zp;
    }

    public void setIsCheck_zp(int isCheck_zp) {
        this.isCheck_zp = isCheck_zp;
    }

    public User() {
    }

    public User(int id, String userName_zp, String userPassword_zp, String question_zp, String ans_zp) {
        this.id = id;
        this.userName_zp = userName_zp;
        this.userPassword_zp = userPassword_zp;
        this.question_zp = question_zp;
        this.ans_zp = ans_zp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName_zp() {
        return userName_zp;
    }

    public void setUserName_zp(String userName_zp) {
        this.userName_zp = userName_zp;
    }

    public String getUserPassword_zp() {
        return userPassword_zp;
    }

    public void setUserPassword_zp(String userPassword_zp) {
        this.userPassword_zp = userPassword_zp;
    }

    public String getQuestion_zp() {
        return question_zp;
    }

    public void setQuestion_zp(String question_zp) {
        this.question_zp = question_zp;
    }

    public String getAns_zp() {
        return ans_zp;
    }

    public void setAns_zp(String ans_zp) {
        this.ans_zp = ans_zp;
    }
}
