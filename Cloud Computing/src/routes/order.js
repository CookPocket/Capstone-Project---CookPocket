const express = require('express');
const controllerOrder = require('../controller/orderController');
const router = express.Router();

router.post('/checkout', controllerOrder.checkOut);


module.exports = router;