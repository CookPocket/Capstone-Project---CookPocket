const mysql = require('mysql2');

const pool = mysql.createPool({
    host: process.env.DB_HOST,
    user: process.env.DB_USER,
    password: process.env.DB_PASSWORD,
    database: process.env.DB_NAME,
    waitForConnections: true,
    connectionLimit: 10,
    queueLimit: 0,
});

// Cek koneksi awal
pool.getConnection((err, connection) => {
    if (err) {
        console.error('Koneksi ke database gagal:', err.message);
    } else {
        console.log('Berhasil terkoneksi ke database!');
        connection.release(); // Pastikan koneksi dilepas
    }
});

module.exports = pool.promise();
