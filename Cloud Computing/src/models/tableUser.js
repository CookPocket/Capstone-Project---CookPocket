const configDB = require('../config/database');

const getAllUser = (req, res) => {
    const sqlQuery = 'SELECT * FROM user';
    return configDB.execute(sqlQuery);
}

const getUserById = (userId) => {
  const sqlQuery = 'SELECT * FROM user WHERE id_user = ?';
    return configDB.execute(sqlQuery, [userId]);
};



const createNewUser= (body) => {
    const sqlQuery = `INSERT INTO user (name, email, password, gender, address, city, postal_code)
    VALUES ('${body.name}', '${body.email}', '${body.password}', '${body.gender}', '${body.address}', '${body.city}', '${body.postal_code}')`;

    return configDB.execute(sqlQuery);
}


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




module.exports = { 
    getAllUser, 
    getUserById, 
    createNewUser,
    updateUserById,
    deleteUser

}