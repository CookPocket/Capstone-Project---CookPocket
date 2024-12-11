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
    const { name, email, password, noTelp } = req.body;

    try {
        // Validasi input
        if (!name || !email || !password || !noTelp) {
            return res.status(422).json({
                status: 'Failed',
                message: 'Please fill in all fields',
            });
        }

        // Cek apakah email sudah terdaftar
        const existingUser = await modelTableUser.findUserByEmail(email);
        if (existingUser) {
            return res.status(409).json({
                status: 'Failed',
                message: 'Email already taken',
            });
        }

        // Hash password
        const hashedPassword = await bcrypt.hash(password, 10);

        // Simpan pengguna baru ke database
        await modelTableUser.createUser({
            name,
            email,
            password: hashedPassword,
            noTelp,
        });

        return res.status(201).json({
            status: 'Success',
            message: 'User registered successfully',
            data: {
                name,
                email,
                noTelp,
            },
        });
    } catch (error) {
        return res.status(500).json({
            status: 'Failed',
            message: 'Registration failed',
            serverMessage: error.message,
        });
    }
};


const loginUser = async (req, res) => {
    const { email, password } = req.body;

    try {
        if (!email || !password) {
            return res.status(422).json({
                error: true,
                message: 'Please fill in all fields',
            });
        }

        const user = await modelTableUser.findUserByEmail(email); // Mencari user berdasarkan email

        if (!user) {
            return res.status(401).json({
                error: true,
                message: 'Email or password is invalid',
            });
        }

        const passwordMatch = await bcrypt.compare(password, user.password); // Verifikasi password

        if (!passwordMatch) {
            return res.status(401).json({
                error: true,
                message: 'Email or password is invalid',
            });
        }

        // Jika password benar, buat JWT token
        const accessToken = jwt.sign(
            { userId: user.id_user }, // Menyesuaikan `id_user` dari database Anda
            process.env.ACCESS_TOKEN_SECRET,
            { expiresIn: '1h' }
        );

        return res.status(200).json({
            error: false,
            message: 'Login successful',
            loginResult: {
                userId: user.id_user, // Menggunakan `id_user` sesuai struktur database
                name: user.name,
                token: accessToken,
            },
        });
    } catch (error) {
        return res.status(500).json({
            error: true,
            message: 'Login failed',
            serverMessage: error.message,
        });
    }
};



const getCurrentUser = async (req, res) => {
    try {
        // Mencari user berdasarkan ID yang didapat dari token
        const user = await modelTableUser.getUserById(req.user.id_user); // Sesuaikan ke id_user

        // Jika user tidak ditemukan
        if (!user) {
            return res.status(404).json({
                error: true,
                message: 'User not found',
            });
        }

        return res.status(200).json({
            error: false,
            message: 'User found',
            data: {
                name: user.name,
                email: user.email,
            },
        });
    } catch (error) {
        return res.status(500).json({
            error: true,
            message: 'Failed to retrieve user data',
            serverMessage: error.message,
        });
    }
};

const logoutUser = async (req, res) => {
    const authorization = req.headers.authorization;

    try {
        if (!authorization || !authorization.startsWith('Bearer ')) {
            return res.status(400).json({ 
                error: true,
                message: 'Invalid or missing Authorization header'
            });
        }

        const accessToken = authorization.split(' ')[1];
        if (!accessToken) {
            return res.status(400).json({ 
                error: true,
                message: 'Access token not found'
            });
        }

        // Cek blacklist
        const isBlacklisted = await modelTableUser.isTokenBlacklisted(accessToken);
        if (isBlacklisted) {
            return res.status(400).json({ 
                error: true,
                message: 'You have logged out' 
            });
        }

        // Tambahkan ke blacklist
        await modelTableUser.addTokenToBlacklist(accessToken);

        return res.status(200).json({
            error: false,
            message: 'Logout successful',
        });
    } catch (error) {
        console.error('Error during logout:', error.message);
        return res.status(500).json({
            error: true,
            message: 'Internal server error',
        });
    }
};

// Update name
const updateUserName = async (req, res) => {
    const { name } = req.body;
    const userId = req.user.id_user;

    try {
        await modelTableUser.updateUserName(userId, name);
        res.status(200).json({ message: 'Name updated successfully' });
    } catch (error) {
        res.status(500).json({ error: true, message: error.message });
    }
};

// Update email
const updateUserEmail = async (req, res) => {
    const { email } = req.body;
    const userId = req.user.id_user;

    try {
        await modelTableUser.updateUserEmail(userId, email);
        res.status(200).json({ message: 'Email updated successfully' });
    } catch (error) {
        res.status(500).json({ error: true, message: error.message });
    }
};

// Update phone number
const updateUserPhone = async (req, res) => {
    const { noTelp } = req.body;
    const userId = req.user.id_user;

    try {
        await modelTableUser.updateUserPhone(userId, noTelp);
        res.status(200).json({ message: 'Phone number updated successfully' });
    } catch (error) {
        res.status(500).json({ error: true, message: error.message });
    }
};

// Update password
const updateUserPassword = async (req, res) => {
    const { password } = req.body;
    const userId = req.user.id_user;

    try {
        const hashedPassword = await bcrypt.hash(password, 10);
        await modelTableUser.updateUserPassword(userId, hashedPassword);
        res.status(200).json({ message: 'Password updated successfully' });
    } catch (error) {
        res.status(500).json({ error: true, message: error.message });
    }
};

// Update address
const updateUserAddress = async (req, res) => {
    const { address } = req.body;
    const userId = req.user.id_user;

    try {
        await modelTableUser.updateUserAddress(userId, address);
        res.status(200).json({ message: 'Address updated successfully' });
    } catch (error) {
        res.status(500).json({ error: true, message: error.message });
    }
};

// Update city
const updateUserCity = async (req, res) => {
    const { city } = req.body;
    const userId = req.user.id_user;

    try {
        await modelTableUser.updateUserCity(userId, city);
        res.status(200).json({ message: 'City updated successfully' });
    } catch (error) {
        res.status(500).json({ error: true, message: error.message });
    }
};

module.exports = {
    getAllUser,
    findUserById,
    updateUserById,
    deletedUser,
    registerUsers,
    loginUser,
    getCurrentUser,
    logoutUser,
    updateUserName,
    updateUserPassword,
    updateUserEmail,
    updateUserPhone,
    updateUserAddress,
    updateUserCity
}