const db = require('../config/database');

const searchProducts = async (query, offset, limit) => {
    const [rows] = await db.query(
      `SELECT * FROM product WHERE name LIKE ? ORDER BY id_category DESC LIMIT 10`,
        [`%${query}%`, limit, offset]
    );
    return rows;
};

const getProductByCategory = async (id_category, offset, limit, sortBy, order) => {
    const sortColumn = ['price', 'servings', 'name'].includes(sortBy) ? sortBy : 'price';
    const sortOrder = ['ASC', 'DESC'].includes(order.toUpperCase()) ? order : 'DESC';

    const [data] = await db.query(
        `SELECT * FROM product WHERE id_category= ? ORDER BY ${sortColumn} ${sortOrder} LIMIT ? OFFSET ?`,
        [id_category, limit, offset]
    );
    return data;
}

const createRecipe = async (recipeData) => {
    const { name, ingredients, steps, image_url } = recipeData;

    const query = 'INSERT INTO recipes (name, ingredients, steps, image_url) VALUES (?, ?, ?, ?)';
    try {
        const [result] = await configDB.execute(query, [name, ingredients, steps, image_url]);
        return result;
    } catch (error) {
        console.error('Database query failed:', error.message);
        throw new Error('Database query failed');
    }
};

module.exports = {
    searchProducts,
    getProductByCategory,
    createRecipe

}