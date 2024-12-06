const express = require('express');
const htpp = require('http')
require('dotenv').config();

const PORT = process.env.PORT || 4000;
const initSocket = require('./socket');
const routesChat = require('./routes/chat.js');

const routesUsers = require('./routes/users');
const logsRequest = require('./middleware/logs.js');

const app = express();
const server = htpp.createServer(app)

app.use(express.json());
app.use(logsRequest);

//path login/register
app.use('/api', routesUsers);

//path fitur chat
app.use('/api/chat', routesChat);

initSocket(server);

server.listen(PORT, () => {
    console.log(`Server berjalan pada http://localhost:${PORT}`);
});
