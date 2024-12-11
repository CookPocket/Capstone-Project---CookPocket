const orderModel = require('../models/modelOrder');
const orderItemModel = require('../models/orderItem');
const productModel = require('../models/modelProduct');

const checkOut = async (req, res) => {
    const { user_id, cart_items } = req.body;

    if(!user_id || !Array.isArray(cart_items) || cart_items.length === 0) {
        return res.status(400).json({
            status: 'Failed',
            message: 'Invalid input',

        });
    }

    let total_price = 0;

    try {
        for(const {product_id, quantity} of cart_items) {
            console.log(`Checking product Id: ${product_id}`);
            const product = await productModel.getProductById(product_id);

            if(!product) {
                console.log(`Product Id ${product_id} not found`);
                return res.status(404).json({
                    status: 'Failed',
                    message: `Product with ID ${product_id} not found`
                });
                
            }
            total_price += product.price * quantity;
            
        }
        const orderId = await orderModel.createOrder(user_id, 'Paid');

        await Promise.all(cart_items.map(({ product_id, quantity }) => 
            orderItemModel.addOrderItem(orderId, product_id, quantity)
        ));

        
        res.status(201).json({
            status: 'Success',
            message: 'Checkout successful',
            order_id: orderId,
            total_price
        });
    } catch (error) {
        console.error('Error during checkout process:', error);
        res.status(500).json({
            status: 'Failed',
            message: 'Internal server error',
            serverMessage: error.message
        });
        
    };
};


module.exports = { checkOut }

