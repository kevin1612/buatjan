const mongoose = require('mongoose')
const Schema = mongoose.Schema

const namabarangSchema = new Schema({
  kodebarang : {
    type: String,
    indexes:{
      unique:true
    }
  },
  namabarang : {
    type: String
  },
  harga : {
    type: String
  },
  jenis : {
    type: String
  }
})


module.exports = mongoose.model('namabarang',namabarangSchema)
