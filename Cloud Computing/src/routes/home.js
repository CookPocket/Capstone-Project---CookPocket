const express = require('express');
const homePage= require('../controller/home');
const router = express.Router();


router.post('/', homePage.sendMessage);

router.get('/', homePage.getMessage);

router.patch('/', homePage.updateMessage);


module.exports= router;
