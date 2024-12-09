const jwt = require('jsonwebtoken');

const ensureAuthenticated = async (req, res, next) => {
    // Mengambil token dari header Authorization
    const accessToken = req.headers.authorization?.split(' ')[1]; // Mengambil token setelah 'Bearer'

    // Jika token tidak ditemukan
    if (!accessToken) {
        return res.status(401).json({ message: 'Access token not found' });
    }

    try {
        // Verifikasi token
        const decodedAccessToken = jwt.verify(accessToken, process.env.ACCESS_TOKEN_SECRET);

        // Menyimpan ID pengguna yang terverifikasi di req.user
        req.user = { id_user: decodedAccessToken.userId }; // Ganti id menjadi id_user jika perlu

        // Lanjutkan ke handler berikutnya
        next();
    } catch (error) {
        return res.status(401).json({ message: 'Access token invalid or expired' });
    }
};

module.exports = { ensureAuthenticated };
