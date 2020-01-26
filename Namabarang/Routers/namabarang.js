const namabarang= require ('express')()
const namabarangController =require('../Controller/namabarang')

namabarang.post('/insert',(req,res) =>{
    namabarangController.insertnamabarang(req.body)
    .then(result=>{
      res.json(result)
    }).catch(err =>{
      res.json(err)
    })
})
namabarang.get('/get',(req,res) =>{
  namabarangController.getAllnamabarang()
  .then(result=>{
    res.json(result)
  }).catch(err =>{
    res.json(err)
  })
})


module.exports=namabarang