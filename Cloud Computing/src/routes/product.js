const express = require('express');
const productController = require('../controller/controlProduct')
const router = express.Router();

router.get('/search', productController.getProducts);

module.exports = router;