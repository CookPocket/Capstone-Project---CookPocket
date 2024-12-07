const { Server } = require('socket.io');
const modelTableChat = require('./models/tableChat');

const initSocket = (server) => {
    const io = new Server(server, {
        cors: { origin: '*' },
    });

    io.on('connection', (socket) => {
        console.log("User connected: ", socket.id);

        socket.onAny((event, args) =>{
            console.log(`Event received: ${event}`, args);
            
        });

        socket.on('joinRoom', ({ senderId, receiverId }) => {
            const room = `${Math.min(senderId, receiverId)}-${Math.max(senderId, receiverId)}`;
            socket.join(room);
            console.log(`User joined room: ${room}`);
            
        });

        
        socket.on('chatMessage', async ({ senderId,receiverId, message }) => {
            console.log('Message received', {senderId, receiverId, message});
            
            try {
                const newMessageId = await modelTableChat.createMessage(senderId, receiverId, message);
                const room = `${Math.min(senderId, receiverId)}-${Math.max(senderId, receiverId)}`;
                io.to(room).emit('receiveMessage', {
                    id: newMessageId,
                    senderId,
                    receiverId,
                    message,
                    createdAt: new Date().toISOString(),
                });
            } catch (error) {
                console.error('Error saving message:', error);
            }
        });

        socket.on('disconnect', () => {
            console.log(`User disconnected: ${socket.id}`);
        });
    });
};

module.exports = initSocket;