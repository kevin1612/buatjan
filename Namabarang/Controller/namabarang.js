const namabarangModel = require('../Models/namabarang')

exports.insertnamabarang = (data) =>
  new Promise((resolve, reject) => {
      namabarangModel.find({
        kodebarang:data.kodebarang
      }).then(hasil => {
        if (hasil.length > 0) {
          reject ({
            error: true,
            pesan: 'Kode Sudah Digunakan'
          })
        } else {
            namabarangModel.create(data)
            .then(res => {
              resolve({
                error :false,
                pesan: 'Berhasil Input Data'
              })  
            })
            .catch(()=>{
              reject({
                error:true,
                pesan:'Gagal Input Data'
              })
            })
      }
    })
})

exports.getAllnamab = () =>
    new Promise((resolve, reject) => {
      MahasiswaModel.find()
        .then(res => {
          resolve({
            error: false,
            pesan: 'Berhasil Mengambil Data',
            data: res
          })
        })
        .catch (() => {
            reject({
              error: true,
              pesan: 'Npm Sudah Digunakan' 
            })
        })
    })

    exports.getAllnamabarang = () =>
    new Promise((resolve, reject) => {
      namabarangModel.find()
        .then(res => {
          resolve({
            error: false,
            pesan: 'Berhasil Mengambil Data',
            data: res
          })
        })
        .catch (() => {
            reject({
              error: true,
              pesan: 'Kode Barang Sudah Digunakan' 
            })
        })
    })