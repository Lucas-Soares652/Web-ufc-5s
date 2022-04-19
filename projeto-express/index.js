const express = require('express')
const app = express()
const port = 3000

app.use(express.json())
app.use(express.urlencoded({ extended: true }))

app.get('/', (req, res) => {
  res.send('Hello World!')
})

app.listen(port, () => {
  console.log(`Example app listening on port ${port}`)
})

app.post('/mirror', function (req, res, next) {
    console.log(req.body)
    res.send(req.body)
})
  
  app.get('/querytojson', (req, res) => {
    console.log(req.query);
    res.send(req.query);
})

app.get('/paramtojson/name/:name/role/:role', (req, res) => {
    console.log(req.params);
    res.send(req.params);
})
  
var animals = [{"animal":"DOG", "name":"Pluto"}
  ,{"animal":"CAT", "name":"Hercules"}
  ,{"animal":"BIRD", "name":"Tweety"}
  ,{"animal":"DOG", "name":"Spiff"}
  ,{"animal":"CAT", "name":"Tom"}
  ,{"animal":"BIRD", "name":"Road Runner"}
];
  
app.get('/animal', (req, res) => {
  var list = new Array();
  var contador = 0;

  if(req.query.animal){
    for (let i = 0; i < animals.length; i++){
      if(animals[i].animal.indexOf(req.query.animal) != -1){
        list[contador] = animals[i];
        contador++;
      }
    }
  }
  else{
      for (let j = 0; j < animals.length; j++){
        if(animals[j].name.indexOf(req.query.name) != -1){
          list[contador] = animals[j];
          contador++;
        }
      }
  }

  res.send(list);

  for(let x = 0; x < list.length; x++){
    list[x] = "";
  }
})