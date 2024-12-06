const directMessage =require('../models/tableChat');

const getMessage = async (req, res) => {
    const { senderId, receiverId } = req.params;

    try {
        const messages = await directMessage.getMessage(senderId, receiverId);
        res.status(200).json(messages);
    } catch (error) {
        res.status(500).json({
            status: 'Failed',
            message: 'Internal server error',
            serverMessage: error.message
        })
    }
}

const sendMessage = async (req, res) => {
    const { senderId, receiverId, message } = req.body;

    try {
        const messageId = await directMessage.createMessage(senderId, receiverId, message);
        res.status(201).json({
            id: messageId,
            senderId,
            receiverId,
            message
        })
    } catch (error) {
        res.status(500).json({
            status: 'Failed',
            message: 'Internal server error',
            serverMessage: error.message
        })
    }
}


const updateMessage = async (req, res) => {
    const {id} = req.params;
    const {status} = req.body;

    try {
        const updated = await directMessage.updateMessage(id, status)
        if(updated) {
            res.status(200).json({
                status: 'Success',
                message: 'Status update succesfull'
            })
        } else {
            res.status(404).json({
                status: 'Failed',
                message: 'Not found'
            })
        }
    } catch (error) {
        res.status(500).json({
            status: 'Failed',
            message: 'Internal server error',
            serverMessage: error
        })
    }
}


module.exports = { getMessage, sendMessage, updateMessage };