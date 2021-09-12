package com.hngy.zp.notepad.tabel;

import org.litepal.crud.LitePalSupport;

public class MyLog extends LitePalSupport {
    int id;
    String tittle_zp;
    String date_zp;
    String operation;
    String operationTime;
    String account_zp;
    private User user;
    private NotePad notePad;

    public String getAccount_zp() {
        return account_zp;
    }

    public void setAccount_zp(String account_zp) {
        this.account_zp = account_zp;
    }

    public String getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(String operationTime) {
        this.operationTime = operationTime;
    }

    public MyLog(int id, String tittle_zp, String date_zp, String operation, String operationTime) {
        this.id = id;
        this.tittle_zp = tittle_zp;
        this.date_zp = date_zp;
        this.operation = operation;
        this.operationTime = operationTime;
    }

    public MyLog(int id, String tittle_zp, String date_zp, String operation) {
        this.id = id;
        this.tittle_zp = tittle_zp;
        this.date_zp = date_zp;
        this.operation = operation;
    }

    public MyLog() {
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

    public String getDate_zp() {
        return date_zp;
    }

    public void setDate_zp(String date_zp) {
        this.date_zp = date_zp;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
}
