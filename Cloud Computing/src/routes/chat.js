const express = require('express');
const chatController= require('../controller/chat');
const router = express.Router();

//mengirim pesan baru
router.post('/message', chatController.sendMessage);

//mendapatkan pesan semua pesan antar pengguna
router.get('/:senderId/:receiverId', chatController.getChatHistory);



module.exports= router;
