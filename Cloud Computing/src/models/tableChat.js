const configDb = require('../config/database');

const getMessage = async (senderId, receiver_Id) => {
    const [data] = await configDb.execute(
        `SELECT c.*,
        sender.name AS sender_name,
        receiver.name AS receiver_name
        FROM chat c
        JOIN user sender ON c.sender_id =sender id_user
        WHERE (c.sender_id = ? AND c.receiver_id= ?)
        OR (c.sender_id = ? AND c.receiver_id= ?)
        ORDER BY c.created_at ASC`,
        [senderId, receiver_Id, receiver_Id, senderId]
    );
    return data;
}


const createMessage = async (senderId, receiverId, message) => {
    const [resultQuery] = await configDb.execute(
        `INSERT INTO chat(sender_id, receiver_id, message, status, created_at)
        VALUES(?, ?, ?, 'sent', NOW())`,
        [senderId, receiverId, message]
    );
    return resultQuery.insertId
}


const updateMessage = async (idChat, status) => {
    const [resultQuery] = await configDb.execute(
        `UPDATE chat SET status= ? WHERE id_chat= ?`,
        [status, idChat]
    );
    return resultQuery.affectedRows;
}


module.exports = { getMessage, createMessage, updateMessage };