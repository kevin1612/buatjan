const server = require('express')()
const bodyParser=require('body-parser')
const mongoose=require('mongoose')
const port = 5000
const cors=require('cors')
const mongoURI = 'mongodb://localhost:port:27017/dbbarang'

server.use(cors())

mongoose.connect(mongoURI,{
useNewUrlParser:true,
useCreateIndex:true,
useUnifiedTopology: true
}).then(()=>{
console.log('Connect to db Success')
}).catch(err=>{
console.log('Error:' +err)
})


server.use(bodyParser.json({
    extended:'true',
    limit:'50mb'
}))

server.use(bodyParser.urlencoded({
    extended:'true',
    limit:'50mb'
}))


server.use('/namabarang',require('./Routers/namabarang'))


server.listen(port, function() {
console.log('Server Started on Port' + port)
})