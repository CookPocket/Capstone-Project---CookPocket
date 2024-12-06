const logsRequest = (req, res, next) => {
    console.log('Mengakses Ke Request Path: ', req.path);
    next();
}

module.exports= logsRequest;