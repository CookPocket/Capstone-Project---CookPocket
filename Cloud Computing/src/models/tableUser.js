const configDB = require('../config/database');

const getAllUser = (req, res) => {
    const sqlQuery = 'SELECT * FROM user';
    return configDB.execute(sqlQuery);
}

const getUserById = async (userId) => {
    if (!userId) {
        throw new Error('User ID is required');
    }

    const query = 'SELECT * FROM user WHERE id_user = ?'; // Ganti id dengan id_user
    try {
        const [rows] = await configDB.execute(query, [userId]);

        if (rows.length === 0) {
            return null;  // Jika user tidak ditemukan, kembalikan null
        }

        return rows[0];  // Kembalikan data user pertama
    } catch (error) {
        console.error('Database query failed:', error.message);
        throw new Error('Database query failed');
    }
};


const updateUserById = (body, userId) => {
    const sqlQuery = `UPDATE user SET
    name= ?,
    email= ?,
    password= ?,
    gender= ?,
    address= ?,
    city= ?,
    postal_code= ?
    WHERE id_user= ?`;

    const values = [
        body.name,
        body.email,
        body.password,
        body.gender,
        body.address,
        body.city,
        body.postal_code,
        userId
    ]
    return configDB.execute(sqlQuery, values);
}


const deleteUser = (userId) => {
    const sqlQuery = `DELETE FROM user WHERE id_user=${userId}`;
    return configDB.execute(sqlQuery);
}

// Fungsi untuk insert data pengguna baru
const createUser = async (userData) => {
    const { name, email, password, noTelp } = userData;
    const query = 'INSERT INTO user (name, email, password, noTelp) VALUES (?, ?, ?, ?)';
    const [result] = await configDB.execute(query, [name, email, password, noTelp]);
    return result;
};

// Fungsi untuk mencari pengguna berdasarkan email
const findUserByEmail = async (email) => {
    const query = 'SELECT * FROM user WHERE email = ?';
    const [rows] = await configDB.execute(query, [email]);
    return rows[0];
};

const updateUserAccount = async (userId, updateData) => {
    const { name, email, noTelp, password, address, city } = updateData;

    const query = `
        UPDATE user
        SET name = ?, email = ?, noTelp = ?, password = ?, address = ?, city = ?
        WHERE id_user = ?`;

    // Ganti undefined dengan null
    const params = [
        name !== undefined ? name : null,
        email !== undefined ? email : null,
        noTelp !== undefined ? noTelp : null,
        password !== undefined ? password : null,
        address !== undefined ? address : null,
        city !== undefined ? city : null,
        userId
    ];

    const [result] = await configDB.execute(query, params);

    return result;
};

const addTokenToBlacklist = async (token) => {
    const query = 'INSERT INTO token_blacklist (token) VALUES (?)';
    await configDB.execute(query, [token]);
};

const isTokenBlacklisted = async (token) => {
    const query = 'SELECT COUNT(*) AS count FROM token_blacklist WHERE token = ?';
    const [rows] = await configDB.execute(query, [token]);
    return rows[0].count > 0;
};

module.exports = { 
    getAllUser, 
    getUserById,
    updateUserById,
    deleteUser,
    createUser,
    findUserByEmail,
    updateUserAccount,
    addTokenToBlacklist,
    isTokenBlacklisted
}