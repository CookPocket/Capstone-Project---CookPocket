const express = require('express');
const userController = require('../controller/users');
const userMiddleware = require('../middleware/users.js');
const router = express.Router();

//menambahkan data pengguna atau user baru
router.post('/auth/register', userController.registerUsers);

//for login user
router.post('/auth/login', userController.loginUser);

//authenticate user
router.get('/auth', userMiddleware.ensureAuthenticated, userController.getCurrentUser);

//Mendapatkan semua pengguna/user
router.get('/user', userController.getAllUser);

//Mendapatkan & mencari pengguna atau user berdasarkan (id)
router.get('/user/:userId', userController.findUserById);


//Mengupdate sebagian data tanpa me-replace kesuluruhan data
router.patch('/user/:userId', userController.updateUserById);

//Delete user berdasarkan Id
router.delete('/user/:userId', userController.deletedUser);

module.exports = router;