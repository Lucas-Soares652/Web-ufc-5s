var listaProduto = [];
let idProduto = 1;

function addProduto(produto) {

    produto.id = idProduto;
    listaProduto.push(produto);
    idProduto++;
}

function getProduto(produto){

    for (let index = 0; index < listaProduto.length; index++) {

        if(listaProduto[index].id == produto.id){
            produto.id = produto.id
            produto.name = listaProduto[index].name;
            produto.preco = listaProduto[index].preco;
            produto.descricao = listaProduto[index].descricao;
            produto.categoria = listaProduto[index].categoria;
        }
    }
}

function editarProduto(produto){

    for (let index = 0; index < listaProduto.length; index++) {

        if(listaProduto[index].id == produto.id){

            listaProduto[index].name = produto.name;
            listaProduto[index].preco = produto.preco;
            listaProduto[index].descricao = produto.descricao;
            listaProduto[index].categoria = produto.categoria;
        }
    }
}

function getListaProduto(){
    return listaProduto;
}

exports.addProduto = addProduto;
exports.getListaProduto = getListaProduto;
exports.getProduto = getProduto;
exports.editarProduto = editarProduto;