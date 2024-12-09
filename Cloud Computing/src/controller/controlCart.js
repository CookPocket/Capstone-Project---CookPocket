const { addToCart } = require('../models/modelCart')

const addItemToCart = (req, res) => {
    const { userId, productId, quantity } = req.body;

    if(!userId || !productId || !quantity) {
        return res.status(400).json({
            status: 'Failed',
            message: 'Incomplete data',
        })
    }
    addToCart(userId, productId, quantity, (err, result) => {
        if(err) {
            return req.status(500).json({
                status: 'Failed',
                message: 'Error adding to cart',
                serverMessage: err
            })
        }
        res.status(200).json({
            status: 'success',
            message: 'Item added to cart',
            result
        })
    })
}

module.exports = { addItemToCart };