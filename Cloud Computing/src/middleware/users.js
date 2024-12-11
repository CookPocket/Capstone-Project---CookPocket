const jwt = require('jsonwebtoken');
const modelTableUser = require('../models/tableUser');

/*untuk menggunakan ini pada setiap protected route,
harus disertakan ensureAuthenticated pada route*/

const ensureAuthenticated = async (req, res, next) => {
    try {
        const authorizationHeader = req.headers.authorization;
        if (!authorizationHeader || !authorizationHeader.startsWith('Bearer ')) {
            return res.status(401).json({ message: 'Access token not found or expired' });
        }

        const accessToken = authorizationHeader.split(' ')[1];
        if (!accessToken) {
            return res.status(401).json({ message: 'Access token not found or expired' });
        }

        const isBlacklisted = await modelTableUser.isTokenBlacklisted(accessToken);
        if (isBlacklisted) {
            return res.status(401).json({ message: 'You have logged out' });
        }

        const decodedAccessToken = jwt.verify(accessToken, process.env.ACCESS_TOKEN_SECRET);
        req.user = { id_user: decodedAccessToken.userId };

        next();
    } catch (error) {
        console.error('Authentication error:', error.message);
        return res.status(401).json({
            message: error.name === 'TokenExpiredError'
                ? 'Access token expired'
                : 'Access token invalid or malformed',
        });
    }
};


module.exports = { ensureAuthenticated };
