const express = require('express');
const userController = require('../controller/users');
const router = express.Router();

//menambahkan data pengguna atau user baru
router.post('/auth/register', userController.createNewUsers);

//for login user
router.post('/auth/login', userController.createNewUsers);

//Mendapatkan semua pengguna/user
router.get('/user', userController.getAllUser);

//Mendapatkan & mencari pengguna atau user berdasarkan (id)
router.get('/user/:userId', userController.findUserById);


//Mengupdate sebagian data tanpa me-replace kesuluruhan data
router.patch('/user/:userId', userController.updateUserById);

//Delete user berdasarkan Id
router.delete('/user/:userId', userController.deletedUser);

module.exports = router;