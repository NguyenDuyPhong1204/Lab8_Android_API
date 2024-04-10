const e = require('express');
const mongoose = require('mongoose');
const Scheme = mongoose.Schema;

const SinhVien = new Scheme({
    hoTen_PH36760 : {type: String, required: true},
    queQuan_PH36760: {type: String, required: true},
    diem_PH36760: {type: Number, required: true},
    hinhAnh_PH36760: {type: String}
});

module.exports = mongoose.model("sinhvien", SinhVien);