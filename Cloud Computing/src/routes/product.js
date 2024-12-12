const express = require('express');
const productController = require('../controller/controlProduct')
const userMiddleware = require('../middleware/users.js');
const router = express.Router();

router.get('/search', productController.getProducts);

router.get('/category/makanan-berat', productController.getProductById);

router.get('/category/makanan-tradisional', productController.getProductById);

router.get('/category/makanan-sehat', productController.getProductById);

router.post('/add-recipe', userMiddleware.ensureAuthenticated, productController.addRecipe);

module.exports = router;