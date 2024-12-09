const productModel = require('../models/modelProduct');

const getProducts = async (req, res) => {
    const { search, page = 1, limit = 10 } = req.query; 
    if (!search) {
        return res.status(400).json({ message: 'Query pencarian tidak boleh kosong!' });
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

}

module.exports = {
    getProducts,
}

