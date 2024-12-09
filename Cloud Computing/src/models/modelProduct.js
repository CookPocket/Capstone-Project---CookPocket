const db = require('../config/database');

const searchProducts = async (query, offset, limit) => {
    const [rows] = await db.query(
      `SELECT * FROM product WHERE name LIKE ? ORDER BY id_category DESC LIMIT 10`,
        [`%${query}%`, limit, offset]
    );
    return rows;
};

const searchProductByCategory = async() => {

}

module.exports = {
    searchProducts,
}