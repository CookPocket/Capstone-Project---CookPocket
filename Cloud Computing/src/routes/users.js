const express = require('express');
const userController = require('../controller/users');
const userMiddleware = require('../middleware/users.js');
const router = express.Router();

//menambahkan data pengguna atau user baru
router.post('/auth/register', userController.registerUsers);

//for login user
router.post('/auth/login', userController.loginUser);

/*testing authenticate user
contoh menggunakan protected route*/
router.get('/auth', userMiddleware.ensureAuthenticated, userController.getCurrentUser);

//Mendapatkan semua pengguna/user
router.get('/user', userController.getAllUser);

//Mendapatkan & mencari pengguna atau user berdasarkan (id)
router.get('/user/:userId', userController.findUserById);


//Mengupdate sebagian data tanpa me-replace kesuluruhan data
router.patch('/user/:userId', userController.updateUserById);

//Delete user berdasarkan Id
router.delete('/user/:userId', userController.deletedUser);

//Update Account by User
router.put('/user/update-name', userMiddleware.ensureAuthenticated, userController.updateUserName);
router.put('/user/update-email', userMiddleware.ensureAuthenticated, userController.updateUserEmail);
router.put('/user/update-phone', userMiddleware.ensureAuthenticated, userController.updateUserPhone);
router.put('/user/update-password', userMiddleware.ensureAuthenticated, userController.updateUserPassword);
router.put('/user/update-address', userMiddleware.ensureAuthenticated, userController.updateUserAddress);
router.put('/user/update-city', userMiddleware.ensureAuthenticated, userController.updateUserCity);

//Logout Account by User
router.post('/auth/logout', userMiddleware.ensureAuthenticated, userController.logoutUser);
module.exports = router;