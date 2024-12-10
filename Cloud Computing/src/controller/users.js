const modelTableUser = require('../models/tableUser');
const bcrypt = require('bcryptjs')
const jwt = require('jsonwebtoken')

const getAllUser = async (req, res) => {

    try {
        const [data] = await modelTableUser.getAllUser();

        res.status(200).json({
            status: 'success',
            message: 'GET all users succes',
            data: data
        });
        
    } catch (error) {
        res.status(500).json({
            status: 'Failed',
            message: 'Internal server error',
            serverMessage: error,
        })

    }

}

const findUserById = async (req, res) => {
    try {
        const { userId } = req.params;
        if (isNaN(userId)) {
            return res.status(400).json({
                status: 'error',
                message: 'Invalid user ID format.',
            });
        }
        const [results] = await modelTableUser.getUserById(userId);
        if (results.length === 0) {
            return res.status(404).json({
                status: 'error',
                message: 'User not found.',
        });
    }
        res.status(200).json({
            status: 'success',
            message: 'User found.',
            data: results[0],
        });
    } catch (error) {
        res.status(500).json({
            status: 'error',
            message: 'Internal server error.',
            data: error,
        });
    }
};


const updateUserById = async (req, res) => {
    const {userId} = req.params;
    const {body} = req;

    try {
        await modelTableUser.updateUserById(body, userId)
        res.status(200).json({
            status: 'Success',
            message: 'UPDATE Success',
            data: {
                id: userId,
                ...body
            },
        })
    } catch (error) {
        res.status(400).json({
            status: 'Failed',
            message: 'Update failed',
            serverMessage: error
        });
    }
}

const deletedUser = async (req, res) => {
    const {userId} = req.params;
    
    try {
        await modelTableUser.deleteUser(userId)
        res.status(200).json({
            status: 'success',
            message: 'DELETE Success',
            data: []
        })
    } catch (error) {
        res.status(400).json({
            status: 'Failed',
            message: 'Invalid',
            serverMessage: error
        })
    }
    
}

const registerUsers = async (req, res) => {
    try {
        const { name, email, password } = req.body;

        // Validasi input
        if (!name || !email || !password) {
            return res.status(422).json({ message: 'Please fill in all fields' });
        }

        // Cek apakah email sudah terdaftar
        const existingUser = await modelTableUser.findUserByEmail(email);
        if (existingUser) {
            return res.status(409).json({ message: 'Email already taken' });
        }

        // Hash password
        const hashedPassword = await bcrypt.hash(password, 10);

        // Simpan pengguna baru ke database
        await modelTableUser.createUser({ name, email, password: hashedPassword });

        return res.status(201).json({ message: 'User registered successfully' });
    } catch (error) {
        return res.status(500).json({ message: error.message });
    }
};

const loginUser = async (req, res) => {
    try {
        const { email, password } = req.body;

        if (!email || !password) {
            return res.status(422).json({ message: 'Please fill in all fields' });
        }

        const user = await modelTableUser.findUserByEmail(email); // Mencari user berdasarkan email

        if (!user) {
            return res.status(401).json({ message: 'Email or password is invalid' });
        }

        const passwordMatch = await bcrypt.compare(password, user.password); // Verifikasi password

        if (!passwordMatch) {
            return res.status(401).json({ message: 'Email or password is invalid' });
        }

        // Jika password benar, buat JWT token
        const accessToken = jwt.sign({ userId: user.id_user }, process.env.ACCESS_TOKEN_SECRET, { expiresIn: '1h' });

        return res.status(200).json({
            name: user.name,
            email: user.email,
            accessToken
        });
    } catch (error) {
        return res.status(500).json({ message: error.message });
    }
};

const getCurrentUser = async (req, res) => {
    try {
        // Mencari user berdasarkan ID yang didapat dari token
        const user = await modelTableUser.getUserById(req.user.id_user); // Sesuaikan ke id_user

        // Jika user tidak ditemukan
        if (!user) {
            return res.status(404).json({ message: 'User not found' });
        }

        return res.status(200).json({
            name: user.name,
            email: user.email,
        });
    } catch (error) {
        return res.status(500).json({ message: error.message });
    }
};


module.exports = {
    getAllUser,
    findUserById,
    updateUserById,
    deletedUser,
    registerUsers,
    loginUser,
    getCurrentUser
}