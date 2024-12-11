const db = require('../config/database');

const addOrderItem = async (orderId, productId, quantity) => {
    try {
        const sqlQuery = 'INSERT INTO order_item (order_id, product_id, quantity) VALUES (?, ?, ?)';
        await db.execute(sqlQuery, [orderId, productId, quantity]);
    } catch (error) {
        throw error;
    }
}

module.exports = {
    addOrderItem,
}