const express = require('express');
const http = require('http');
const cors = require('cors');

require('dotenv').config();
const PORT = process.env.PORT || 4000;

const initSocket = require('./socket');
const routesChat = require('./routes/chat.js');

const routesCart = require('./routes/cart.js');
const routesProduct = require('./routes/product.js')

const routesUsers = require('./routes/users');
const logsRequest = require('./middleware/logs.js');

const app = express();
const server = http.createServer(app)


app.use(logsRequest);

app.use(express.json());
app.use(cors());
app.use(express.urlencoded({ extended: true }));
//path login/register
app.use('/api', routesUsers);
app.use('/api/products', routesProduct);
app.use('/api/cart', routesCart);
//path fitur chat
app.use('/api/chat', routesChat);

initSocket(server);

server.listen(PORT, () => {
    console.log(`Server berjalan pada http://localhost:${PORT}`);
});
