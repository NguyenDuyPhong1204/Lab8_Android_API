package com.example.ph36760_thi.Model;

import com.google.gson.annotations.SerializedName;

public class Teacher {
    @SerializedName("_id")
    private String teacherID;
    private String hoTen_PH36760, queQuan_PH36760;
    @SerializedName("luong_PH36760")
    private double luong;
    private String hinhAnh_PH36760;
    private String chuyenNganh_PH36760;

    public Teacher(String teacherID, String hoTen_PH36760, String queQuan_PH36760, double luong, String hinhAnh_PH36760, String chuyenNganh_PH36760) {
        this.teacherID = teacherID;
        this.hoTen_PH36760 = hoTen_PH36760;
        this.queQuan_PH36760 = queQuan_PH36760;
        this.luong = luong;
        this.hinhAnh_PH36760 = hinhAnh_PH36760;
        this.chuyenNganh_PH36760 = chuyenNganh_PH36760;
    }

    public Teacher() {
    }

    public String getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(String teacherID) {
        this.teacherID = teacherID;
    }

    public String getHoTen_PH36760() {
        return hoTen_PH36760;
    }

    public void setHoTen_PH36760(String hoTen_PH36760) {
        this.hoTen_PH36760 = hoTen_PH36760;
    }

    public String getQueQuan_PH36760() {
        return queQuan_PH36760;
    }

    public void setQueQuan_PH36760(String queQuan_PH36760) {
        this.queQuan_PH36760 = queQuan_PH36760;
    }

    public double getLuong() {
        return luong;
    }

    public void setLuong(double luong) {
        this.luong = luong;
    }

    public String getHinhAnh_PH36760() {
        return hinhAnh_PH36760;
    }

    public void setHinhAnh_PH36760(String hinhAnh_PH36760) {
        this.hinhAnh_PH36760 = hinhAnh_PH36760;
    }

    public String getChuyenNganh_PH36760() {
        return chuyenNganh_PH36760;
    }

    public void setChuyenNganh_PH36760(String chuyenNganh_PH36760) {
        this.chuyenNganh_PH36760 = chuyenNganh_PH36760;
    }
}
