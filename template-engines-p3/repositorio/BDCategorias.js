var listaCategoria = [];

function addCategoria(categoria) {

    listaCategoria.push(categoria);
}

function dropCategoria(categoria) {

    for (let index = 0; index < listaCategoria.length; index++) {

        if(listaCategoria[index].chave == categoria.chave){
            listaCategoria.splice(index, 1);
        }
    }
}

function getListaCategoria(){
    return listaCategoria;
}

exports.addCategoria = addCategoria;
exports.getListaCategoria = getListaCategoria;
exports.dropCategoria = dropCategoria;