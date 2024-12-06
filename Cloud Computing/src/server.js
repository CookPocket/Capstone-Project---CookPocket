const express = require('express');
require('dotenv').config();
const PORT = process.env.PORT || 4000;
const routesHomePage = require('./routes/home.js');
const routesUsers = require('./routes/users');
const logsRequest = require('./middleware/logs.js');

const app = express();

app.use(express.json({ type: 'application/json' }));
app.use(logsRequest);

app.use('/v1', routesUsers);
app.use('/', routesHomePage);

app.listen(PORT, () => {
    console.log(`Server berjalan pada http://localhost:${PORT}`);
});
