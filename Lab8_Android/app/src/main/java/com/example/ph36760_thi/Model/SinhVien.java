package com.example.ph36760_thi.Model;

public class SinhVien {

    private String _id ,hoTen_PH36760 ,queQuan_PH36760;
    private double diem_PH36760;
    private String hinhAnh_PH36760;

    public SinhVien() {
    }

    public SinhVien(String _id, String hoTen_PH36760, String queQuan_PH36760, double diem_PH36760, String hinhAnh_PH36760) {
        this._id = _id;
        this.hoTen_PH36760 = hoTen_PH36760;
        this.queQuan_PH36760 = queQuan_PH36760;
        this.diem_PH36760 = diem_PH36760;
        this.hinhAnh_PH36760 = hinhAnh_PH36760;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
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

    public double getDiem_PH36760() {
        return diem_PH36760;
    }

    public void setDiem_PH36760(double diem_PH36760) {
        this.diem_PH36760 = diem_PH36760;
    }

    public String getHinhAnh_PH36760() {
        return hinhAnh_PH36760;
    }

    public void setHinhAnh_PH36760(String hinhAnh_PH36760) {
        this.hinhAnh_PH36760 = hinhAnh_PH36760;
    }
}
