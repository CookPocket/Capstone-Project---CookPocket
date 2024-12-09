const db = require('../config/database');

const addToCart = (userId, productId, quantity, callback) => {
    const sqlQuery = `INSERT INTO cart (user_id, product_id, quantity, )
    VALUES (?, ?, ?) ON DUPLICAT KEY UPDATE quantity= quantity + ?`;

    db.execute(query, [userId, productId, quantity, quantity], (err, result) => {
        if(err) return callback(err);
        callback(null, result);
    });
}

module.exports = { addToCart };