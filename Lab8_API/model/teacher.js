const e = require('express');
const mongoose = require('mongoose');
const Scheme = mongoose.Schema;

const Teacher = new Scheme({
    hoTen_PH36760 : {type: String, required: true},
    queQuan_PH36760: {type: String, required: true},
    luong_PH36760: {type: Number, required: true, default: 0},
    hinhAnh_PH36760: {type: String},
    chuyenNganh_PH36760: {type: String}
});

module.exports = mongoose.model("teacher", Teacher);