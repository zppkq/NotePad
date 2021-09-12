package com.hngy.zp.notepad.entity;

public class Province {
    int province_id_zp;
    String province_name_zp;

    public Province() {
    }

    public Province(int province_id_zp, String province_name_zp) {
        this.province_id_zp = province_id_zp;
        this.province_name_zp = province_name_zp;
    }

    public int getProvince_id_zp() {
        return province_id_zp;
    }

    public void setProvince_id_zp(int province_id_zp) {
        this.province_id_zp = province_id_zp;
    }

    public String getProvince_name_zp() {
        return province_name_zp;
    }

    public void setProvince_name_zp(String province_name_zp) {
        this.province_name_zp = province_name_zp;
    }

    @Override
    public String toString() {
        return "Province{" +
                "province_id_zp=" + province_id_zp +
                ", province_name_zp='" + province_name_zp + '\'' +
                '}';
    }
}
