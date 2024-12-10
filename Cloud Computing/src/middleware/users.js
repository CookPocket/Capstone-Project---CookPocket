const jwt = require('jsonwebtoken');

/*untuk menggunakan ini pada setiap protected route,
harus disertakan ensureAuthenticated pada route*/

const ensureAuthenticated = async (req, res, next) => {
    try {
        // Mengambil token dari header Authorization
        const authorizationHeader = req.headers.authorization;
        if (!authorizationHeader) {
            return res.status(401).json({ message: 'Access token not found' });
        }

        const accessToken = authorizationHeader.split(' ')[1]; // Mengambil token setelah 'Bearer'
        if (!accessToken) {
            return res.status(401).json({ message: 'Access token not found' });
        }

        // Verifikasi token
        const decodedAccessToken = jwt.verify(accessToken, process.env.ACCESS_TOKEN_SECRET);

        // Menyimpan ID pengguna yang terverifikasi di req.user
        req.user = { id_user: decodedAccessToken.userId }; // Pastikan sesuai dengan struktur token Anda

        next();
    } catch (error) {
        // Menangani error token
        const isExpired = error.name === 'TokenExpiredError';
        const errorMessage = isExpired
            ? 'Access token expired'
            : 'Access token invalid or malformed';
        return res.status(401).json({ message: errorMessage });
    }
};


module.exports = { ensureAuthenticated };
