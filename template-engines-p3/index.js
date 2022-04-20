const express = require ('express' )
const app = express ()
const port = 3000

app.use(express.json())
app.use(express.urlencoded({ extended: true }))
app.set('view engine', 'ejs');
app.set('views', './views');

const bdCategoria = require('./repositorio/BDCategorias');
const bdProduto = require('./repositorio/BDProdutos');

app.get('/', (req, res) => {
  res.render('home', {title: 'Lab MPA', message:'Bem vindo ao Lab MPA'});
});

app.get('/categorias', (req, res) => {
    res.render('categoria', {title: 'Lab MPA', message:'Bem vindo ao Lab MPA', listaCategoria : bdCategoria.getListaCategoria()});
});
  
app.post('/categoria-salvar', (req, res) => {
  
    var categoria = { chave: req.body.chaveCategoria , valor:req.body.valor};
    bdCategoria.addCategoria(categoria);
  
    res.redirect('/categorias');
});
  
app.get('/categoria-deletar', (req, res) => {

    var categoria = { chave: req.query.chaveCategoria };
    bdCategoria.dropCategoria(categoria);
  
    res.redirect('/categorias');
});

app.get('/produtos', (req, res) => {
    res.render('produto', {title: 'Lab MPA', message:'Bem vindo ao Lab MPA', listaProduto : bdProduto.getListaProduto()});
});
  
app.get('/cadastrarProduto', (req, res) => {
    res.render('cadastrarProduto', {title: 'Lab MPA', message:'Bem vindo ao Lab MPA', listaCategoria : bdCategoria.getListaCategoria()});
});
  
app.post('/produto-salvar', (req, res) => {
    
    var produto = { id: 0 , 
                    name:req.body.name, 
                    categoria:req.body.categoria, 
                    preco:req.body.preco, 
                    descricao:req.body.descricao};
                    
    bdProduto.addProduto(produto);
    
    res.redirect('/produtos');
  
});
  
app.post('/produto-editar', (req, res) => {
  
    var produto = { id: req.body.id , 
                    name:req.body.name, 
                    categoria:req.body.categoria, 
                    preco:req.body.preco, 
                    descricao:req.body.descricao};
                  
    bdProduto.editarProduto(produto);
  
    res.redirect('/produtos');
});
  
app.get('/produto-editar', (req, res) => {
  
    var produtoGET = {id:req.query.id};
  
    bdProduto.getProduto(produtoGET);
  
    res.render('editarProduto', {title: 'Lab MPA', message:'Bem vindo ao Lab MPA', produto: produtoGET , listaCategoria : bdCategoria.getListaCategoria()});
});

app.listen(port, () => {
    console.log(`Example app listening on port ${port}`)
});