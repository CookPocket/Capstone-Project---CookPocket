const db = require('../config/database');

const createOrder = async (user_id, status='Unpaid') => {
    try {
        const sqlQuery = 'INSERT INTO orders (user_id, status) VALUES (?, ?)';
        const [data] = await db.execute(sqlQuery, [user_id, status]);
        return data.insertId;
    } catch (error) {
        throw error;
    }
};

const getOrderByUser = async (userId) => {
    try {
        const sqlQuery = `
        SELECT o.id_orders, o.status, o.created_at,
                GROUP_CONCAT(p.name SEPARATOR ', ') AS products
            FROM orders o
            JOIN order_item oi ON o.id_orders = oi.order_id
            JOIN product p ON oi.product_id = p.id_product
            WHERE o.user_id = ?
            GROUP BY o.id_orders`;
            const [data] = await db.execute(sqlQuery, [userId]);
            return data;
    } catch (error) {
        throw error;
    }
}


module.exports = {
    createOrder,
    getOrderByUser
}