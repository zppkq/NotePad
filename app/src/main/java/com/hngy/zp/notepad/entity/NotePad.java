package com.hngy.zp.notepad.entity;

public class NotePad {
    int id;
    String tittle_zp;
    String content_zp;
    String date_zp;
    int isSelected;
    String account_zp;
    int user_id_zp;

    public NotePad(String tittle_zp, String content_zp, String date_zp, int isSelected, int user_id_zp) {
        this.tittle_zp = tittle_zp;
        this.content_zp = content_zp;
        this.date_zp = date_zp;
        this.isSelected = isSelected;
        this.user_id_zp = user_id_zp;
    }

    public int getUser_id_zp() {
        return user_id_zp;
    }

    public void setUser_id_zp(int user_id_zp) {
        this.user_id_zp = user_id_zp;
    }

    @Override
    public String toString() {
        return "NotePad{" +
                "id=" + id +
                ", tittle_zp='" + tittle_zp + '\'' +
                ", content_zp='" + content_zp + '\'' +
                ", date_zp='" + date_zp + '\'' +
                ", isSelected=" + isSelected +
                ", account_zp='" + account_zp + '\'' +
                '}';
    }

    public NotePad() {
    }

    public NotePad(int id, String tittle_zp, String content_zp, String date_zp, int isSelected, String account_zp) {
        this.id = id;
        this.tittle_zp = tittle_zp;
        this.content_zp = content_zp;
        this.date_zp = date_zp;
        this.isSelected = isSelected;
        this.account_zp = account_zp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTittle_zp() {
        return tittle_zp;
    }

    public void setTittle_zp(String tittle_zp) {
        this.tittle_zp = tittle_zp;
    }

    public String getContent_zp() {
        return content_zp;
    }

    public void setContent_zp(String content_zp) {
        this.content_zp = content_zp;
    }

    public String getDate_zp() {
        return date_zp;
    }

    public void setDate_zp(String date_zp) {
        this.date_zp = date_zp;
    }

    public int getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(int isSelected) {
        this.isSelected = isSelected;
    }

    public String getAccount_zp() {
        return account_zp;
    }

    public void setAccount_zp(String account_zp) {
        this.account_zp = account_zp;
    }
}
