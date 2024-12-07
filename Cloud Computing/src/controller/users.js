const modelTableUser = require('../models/tableUser');

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



const createNewUsers = async (req, res) => {
    const { body } = req;

    try {
        await modelTableUser.createNewUser(body);
        res.status(201).json({
            status:'success',
            message: 'users successfully created',
            data: body
        })
        
    } catch (error) {
        res.status(400).json({
            status: 'Failed',
            message: 'New user failed to be added',
            serverMessage: error,
        })
        
    }
    
}


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


module.exports = {
    getAllUser,
    findUserById,
    createNewUsers,
    updateUserById,
    deletedUser
}