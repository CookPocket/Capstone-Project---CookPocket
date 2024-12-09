const express = require('express');
const productController = require('../controller/controlCart');
const router = express.Router();

router.post('/add', productController.addItemToCart);



module.exports = router;