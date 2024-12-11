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

const addTokenToBlacklist = async (token) => {
    const query = 'INSERT INTO token_blacklist (token) VALUES (?)';
    await configDB.execute(query, [token]);
};

const isTokenBlacklisted = async (token) => {
    const query = 'SELECT COUNT(*) AS count FROM token_blacklist WHERE token = ?';
    const [rows] = await configDB.execute(query, [token]);
    return rows[0].count > 0;
};

// Update user name
const updateUserName = async (userId, name) => {
    const query = `UPDATE user SET name = ? WHERE id_user = ?`;
    await configDB.execute(query, [name, userId]);
};

// Update user email
const updateUserEmail = async (userId, email) => {
    const query = `UPDATE user SET email = ? WHERE id_user = ?`;
    await configDB.execute(query, [email, userId]);
};

// Update user phone number
const updateUserPhone = async (userId, noTelp) => {
    const query = `UPDATE user SET noTelp = ? WHERE id_user = ?`;
    await configDB.execute(query, [noTelp, userId]);
};

// Update user password
const updateUserPassword = async (userId, hashedPassword) => {
    const query = `UPDATE user SET password = ? WHERE id_user = ?`;
    await configDB.execute(query, [hashedPassword, userId]);
};

// Update user address
const updateUserAddress = async (userId, address) => {
    const query = `UPDATE user SET address = ? WHERE id_user = ?`;
    await configDB.execute(query, [address, userId]);
};

// Update user city
const updateUserCity = async (userId, city) => {
    const query = `UPDATE user SET city = ? WHERE id_user = ?`;
    await configDB.execute(query, [city, userId]);
};

module.exports = { 
    getAllUser, 
    getUserById,
    updateUserById,
    deleteUser,
    createUser,
    findUserByEmail,
    addTokenToBlacklist,
    isTokenBlacklisted,
    updateUserName,
    updateUserEmail,
    updateUserPhone,
    updateUserPassword,
    updateUserAddress,
    updateUserCity,
}