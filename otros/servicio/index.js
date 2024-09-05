const express = require('express');
const app = express();

// Servicio 1
app.get('/service/worldsys/:dni', (req, res) => {
   const dni =  req.params.dni

   let data = [
    {dni:12345678,isTerrotist:true},
    {dni:12345675,isTerrotist:false},
    {dni:12345674,isTerrotist:true},
    {dni:12345673,isTerrotist:false},
    {dni:12345672,isTerrotist:true},
    {dni:66666666,isTerrotist:false}
]
    res.status(200).json(data.filter(x=>x.dni==dni)[0]);
});

// Servicio 2
app.get('/service/veraz/:dni', (req, res) => {
    const dni =  req.params.dni
    let data = [
        {dni:12345678,score:0.1},
        {dni:12345675,score:0.4},
        {dni:12345674,score:4},
        {dni:12345673,score:1.5},
        {dni:12345672,score:0.8},
        {dni:66666666,score:10}
    ]
        
    res.status(200).json(data.filter(x=>x.dni==dni)[0])


});

// Servicio 3
app.get('/service/renaper/:dni', (req, res) => {
    const dni =  req.params.dni

    let data = [
        {dni:12345678,isAuthorize:true},
        {dni:12345675,isAuthorize:false},
        {dni:12345674,isAuthorize:false},
        {dni:12345673,isAuthorize:false},
        {dni:12345672,isAuthorize:true},
        {dni:66666666,isAuthorize:true}
    ]

    res.status(200).json(data.filter(x=>x.dni==dni)[0]);
});

// Puerto
const PORT = process.env.PORT || 3000;
app.listen(PORT, () => {
    console.log(`Servidor escuchando en el puerto ${PORT}`);
});
