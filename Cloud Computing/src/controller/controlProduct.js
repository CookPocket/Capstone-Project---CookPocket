const productModel = require('../models/modelProduct');
const db = require('../config/database');

const getProducts = async (req, res) => {
    const { search, page = 1, limit = 10 } = req.query; 
    if (!search) {
        return res.status(400).json({ message: 'pencarian tidak boleh kosong!' });
    }

    const offset = (page -1) * limit;

    try {
        const products = await productModel.searchProducts(search, parseInt(offset), parseInt(limit));
        res.status(200).json({
            status: 'success',
            message: 'Products found',
            data: products
        });
    } catch (error) {
        console.error(error);
        res.status(500).json({ message: 'Terjadi kesalahan pada server' });
    }
};

const getProductById = async (req, res) => {
    const {id_category, page=1, limit=10, sort_by='price', order='DESC'} = req.query;

    if(!id_category) {
        return res.status(400).json({
            status: 'Failed',
            message: 'Masukan id_category terlebih dahulu!'
        });
    }

    const offset = (page - 1) * limit;

    try {
        const products = await productModel.getProductByCategory(id_category, offset, limit, sort_by, order);

        const [totalCountResult] = await db.query(
            `SELECT COUNT(*) AS total FROM product WHERE id_category= ?`,
            [id_category]
        );
        const total = totalCountResult[0].total;
        res.status(200).json({
            status: 'success',
            message: 'Product found',
            data: products,
            meta: {
                total,
                page: parseInt(page),
                limit: parseInt(limit),
            }
        });
    } catch (error) {
        console.error(error);
        res.status(500).json({
            status: 'Failed',
            message: 'Internal server error',
            serverMessage: error,
        })
        
    }
}

module.exports = {
    getProducts,
    getProductById
}

