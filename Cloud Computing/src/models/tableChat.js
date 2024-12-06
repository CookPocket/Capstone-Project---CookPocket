const db = require('../config/database');

const createMessage = async (senderId, receiverId, message) => {
    const query = `
        INSERT INTO chat (sender_id, receiver_id, message) 
        VALUES (?, ?, ?)`;
        
    const [result] = await db.execute(query, [senderId, receiverId, message]);
    return result.insertId;
};


const getChatHistory = async (senderId, receiverId) => {
    const query = `
        SELECT * FROM chat
        WHERE (sender_id = ? AND receiver_id = ?)
        OR (sender_id = ? AND receiver_id = ?)
        ORDER BY created_at ASC
    `;
    const [data] = await db.execute(query, [senderId, receiverId, receiverId, senderId]);
    return data;
};

module.exports = {
    createMessage,
    getChatHistory

}
