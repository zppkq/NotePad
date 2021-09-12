package com.hngy.zp.notepad.entity;

public class User {
    String userName_zp,userPassword_zp,question_zp,ans_zp,newUserPassword_zp;
    public User() {
    }

    public User(String userName_zp, String userPassword_zp, String question_zp, String ans_zp, String newUserPassword_zp) {
        this.userName_zp = userName_zp;
        this.userPassword_zp = userPassword_zp;
        this.question_zp = question_zp;
        this.ans_zp = ans_zp;
        this.newUserPassword_zp = newUserPassword_zp;
    }

    public String getNewUserPassword_zp() {
        return newUserPassword_zp;
    }

    public void setNewUserPassword_zp(String newUserPassword_zp) {
        this.newUserPassword_zp = newUserPassword_zp;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName_zp='" + userName_zp + '\'' +
                ", userPassword_zp='" + userPassword_zp + '\'' +
                ", question_zp='" + question_zp + '\'' +
                ", ans_zp='" + ans_zp + '\'' +
                '}';
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

    public User(String userName_zp, String userPassword_zp, String question_zp, String ans_zp) {
        this.userName_zp = userName_zp;
        this.userPassword_zp = userPassword_zp;
        this.question_zp = question_zp;
        this.ans_zp = ans_zp;
    }

    public User(String userName_zp, String userPassword_zp) {
        this.userName_zp = userName_zp;
        this.userPassword_zp = userPassword_zp;
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
}
