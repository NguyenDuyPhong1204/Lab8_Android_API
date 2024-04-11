var express = require('express');
const router = express.Router();
const SinhViens = require("../model/sinhvien");
const Teacher = require('../model/teacher');
//thêm 
const Upload = require('../config/common/upload');
router.post('/add-sinhvien', Upload.single('hinhAnh_PH36760'), async (req, res) => {
    try {
        const data = req.body;
        const { file } = req;
        const newSinhVien = new SinhViens({
            hoTen_PH36760: data.hoTen_PH36760,
            queQuan_PH36760: data.queQuan_PH36760,
            diem_PH36760: data.diem_PH36760,
            hinhAnh_PH36760: `${req.protocol}://${req.get('host')}/uploads/${file.filename}`
        })

        const result = await newSinhVien.save();
        if (result) {
            res.json({
                "status": 200,
                "message": "Thêm mới thành công",
                "data": result
            });
        } else {
            res.json({
                "status": 400,
                "message": "Thêm mới thất bại",
                "data": []
            })
        }
    } catch (error) {
        console.log(error);
    }
});

//getlist
router.get('/get-list-sinhvien', async function (req, res) {
    try {
        const result = await SinhViens.find();
        if (result) {
            res.json({
                "status": 200,
                "message": "Lấy dữ liệu thành công",
                "data": result
            })
        } else {
            res.json({
                "status": 400,
                "message": "Lấy dữ liệu thất bại",
                "data": []
            })
        }
    } catch (error) {
        console.log(error);
    }
});



// update
router.put("/update-sinhvien/:id", Upload.single('hinhAnh_PH36760'),async function (req, res) {
    try {
        const { id } = req.params;
        const {file} = req;
        const data = req.body;
        const updateSinhVien = await SinhViens.findById(id);
        let result = null;
        if (updateSinhVien) {
            updateSinhVien.hoTen_PH36760 = data.hoTen_PH36760 ?? updateSinhVien.hoTen_PH36760;
            updateSinhVien.queQuan_PH36760 = data.queQuan_PH36760 ?? updateSinhVien.queQuan_PH36760;
            updateSinhVien.diem_PH36760 = data.diem_PH36760 ?? updateSinhVien.diem_PH36760;
            updateSinhVien.hinhAnh_PH36760 = data.hinhAnh_PH36760 ?? `${req.protocol}://${req.get('host')}/uploads/${file.filename}`;
            result = await updateSinhVien.save();
            if (result) {
                res.json({
                    "status": 200,
                    "message": "Cập nhật thành công",
                    "data": result
                })
            } else {
                res.json({
                    "status": 400,
                    "message": "Cập nhật thất bại",
                    "data": []
                })
            }
        }
    } catch (error) {
        console.log(error);
    }
});


//update
// router.put("/update-sinhvien/:id", async function (req, res) {
//     try {
//         const { id } = req.params;
//         const data = req.body;
//         const updateSinhVien = await SinhViens.findById(id);
//         let result = null;
//         if (updateSinhVien) {
//             updateSinhVien.hoTen_PH36760 = data.hoTen_PH36760 ?? updateSinhVien.hoTen_PH36760;
//             updateSinhVien.queQuan_PH36760 = data.queQuan_PH36760 ?? updateSinhVien.queQuan_PH36760;
//             updateSinhVien.diem_PH36760 = data.diem_PH36760 ?? updateSinhVien.diem_PH36760;
//             updateSinhVien.hinhAnh_PH36760 = data.hinhAnh_PH36760 ?? updateSinhVien.hinhAnh_PH36760;
//             result = await updateSinhVien.save();
//             if (result) {
//                 res.json({
//                     "status": 200,
//                     "message": "Cập nhật thành công",
//                     "data": result
//                 })
//             } else {
//                 res.json({
//                     "status": 400,
//                     "message": "Cập nhật thất bại",
//                     "data": []
//                 })
//             }
//         }
//     } catch (error) {
//         console.log(error);
//     }
// });

//delete
router.delete('/delete-sinhvien/:id', async function (req, res) {
    try {
        const { id } = req.params;
        const result = await SinhViens.findByIdAndDelete(id);
        if (result) {
            res.json({
                "status": 200,
                "message": "Xóa thành công",
                "data": result
            })
        } else {
            res.json({
                "status": 400,
                "message": "Xóa thất bại",
                "data": []
            })
        }
    } catch (error) {
        console.log(error);
    }
});

//teacher
router.post('/add-teacher', Upload.single('hinhAnh_PH36760'), async (req, res) => {
    try {
        const data = req.body;
        const { file } = req;
        const newTeacher = new Teacher({
            hoTen_PH36760: data.hoTen_PH36760,
            queQuan_PH36760: data.queQuan_PH36760,
            luong_PH36760: data.luong_PH36760,
            hinhAnh_PH36760: `${req.protocol}://${req.get('host')}/uploads/${file.filename}`,
            chuyenNganh_PH36760: data.chuyenNganh_PH36760
        })

        const result = await newTeacher.save();
        if (result) {
            res.json({
                "status": 200,
                "message": "Thêm mới thành công",
                "data": result
            });
        } else {
            res.json({
                "status": 400,
                "message": "Thêm mới thất bại",
                "data": []
            })
        }
    } catch (error) {
        console.log(error);
    }
});

//getlist
router.get('/get-list-teacher', async function (req, res) {
    try {
        const result = await Teacher.find();
        if (result) {
            res.json({
                "status": 200,
                "message": "Lấy dữ liệu thành công",
                "data": result
            })
        } else {
            res.json({
                "status": 400,
                "message": "Lấy dữ liệu thất bại",
                "data": []
            })
        }
    } catch (error) {
        console.log(error);
    }
});

router.put("/update-teacher/:id", Upload.single('hinhAnh_PH36760'),async function (req, res) {
    try {
        const { id } = req.params;
        const {file} = req;
        const data = req.body;
        const updateTeacher = await Teacher.findById(id);
        let result = null;
        if (updateTeacher) {
            updateTeacher.hoTen_PH36760 = data.hoTen_PH36760 ?? updateTeacher.hoTen_PH36760;
            updateTeacher.queQuan_PH36760 = data.queQuan_PH36760 ?? updateTeacher.queQuan_PH36760;
            updateTeacher.diem_PH36760 = data.diem_PH36760 ?? updateTeacher.diem_PH36760;
            updateTeacher.hinhAnh_PH36760 = data.hinhAnh_PH36760 ?? `${req.protocol}://${req.get('host')}/uploads/${file.filename}`,
            updateTeacher.chuyenNganh_PH36760 = data.chuyenNganh_PH36760 ?? updateTeacher.chuyenNganh_PH36760
            result = await updateTeacher.save();
            if (result) {
                res.json({
                    "status": 200,
                    "message": "Cập nhật thành công",
                    "data": result
                })
            } else {
                res.json({
                    "status": 400,
                    "message": "Cập nhật thất bại",
                    "data": []
                })
            }
        }
    } catch (error) {
        console.log(error);
    }
});

router.delete('/delete-teacher/:id', async function (req, res) {
    try {
        const { id } = req.params;
        const result = await SinhViens.findByIdAndDelete(id);
        if (result) {
            res.json({
                "status": 200,
                "message": "Xóa thành công",
                "data": result
            })
        } else {
            res.json({
                "status": 400,
                "message": "Xóa thất bại",
                "data": []
            })
        }
    } catch (error) {
        console.log(error);
    }
});


//tìm kiếm 
router.get('/search', async function (req, res) {
    try {
        const key = req.query.key;
        const data = await Teacher.find({ hoTen_PH36760: { "$regex": key, "$options": "i" } })
            .sort({ createdAt: -1 });
        if (data) {
            res.json({
                status: 200,
                messenger: "Tìm thành công",
                data: data,
            });
        } else {
            res.json({
                status: 400,
                messenger: "tìm thất bại",
                data: [],
            });
        }
    } catch (error) {
        console.log(error);
    }
})

module.exports = router;