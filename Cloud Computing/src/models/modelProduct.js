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
    const { name, ingredient, steps, image_url, price } = recipeData;

    // Menyusun query untuk insert data
    const query = 'INSERT INTO product (name, ingredient, steps, image_url, price) VALUES (?, ?, ?, ?, ?)';
    
    try {
        const [result] = await db.execute(query, [
            name,
            ingredient,
            steps,
            image_url && image_url.trim() !== "" ? image_url : null, // Set image_url ke null jika kosong
            price !== undefined ? price : 0,  // Set price ke 0 jika tidak diberikan
        ]);
        
        // Mengembalikan hasil berupa id_product jika berhasil
        return result;
    } catch (error) {
        console.error('Database query failed:', error.message);
        throw new Error('Database query failed');
    }
};


const getProductById = async (productId) => {
    try {
        const sqlQuery = 'SELECT * FROM product WHERE id_product = ?';
        const [data] = await db.execute(sqlQuery, [productId]);
        return data.length ? data[0] : null;
    } catch (error) {
        throw error;
    }
}

module.exports = {
    searchProducts,
    getProductByCategory,
    createRecipe,
    getProductById

}