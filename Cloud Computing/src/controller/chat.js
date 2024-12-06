const modelTableChat = require('../models/tableChat');

const sendMessage = async (req, res) => {
    const { senderId, receiverId, message } = req.body;
    if (!senderId || !receiverId || !message) {
        return res.status(400).json({ error: 'Sender, Receiver, and Message are required' });
    }

    try {
        const newMessageId = await modelTableChat.createMessage(senderId, receiverId, message);
        res.status(201).json({
            success: true,
            message: 'Message sent successfully',
            messageId: newMessageId,
        });
    } catch (error) {
        console.error(error);
        res.status(500).json({ error: 'Failed to send message' });
    }
};

const getChatHistory = async (req, res) => {
    const { senderId, receiverId } = req.params;

    try {
        const chatHistory = await modelTableChat.getChatHistory(senderId, receiverId);
        res.status(200).json(chatHistory);
    } catch (error) {
        console.error(error);
        res.status(500).json({ error: 'Failed to retrieve chat history' });
    }
};

module.exports = {
    sendMessage,
    getChatHistory
}
