let listaProdutos = [];

document.addEventListener('DOMContentLoaded', function () {
    fetch('http://localhost:8080/adm/allprodutos')
        .then(response => response.json())
        .then(data => {
            const tableBody = document.querySelector('#data-table tbody');
            tableBody.innerHTML = '';

            data.forEach(item => {
                const row = document.createElement('tr');

                const idCell = document.createElement('td');
                idCell.textContent = item.idProd;
                row.appendChild(idCell);

                const nameCell = document.createElement('td');
                nameCell.textContent = item.nomeProd;
                row.appendChild(nameCell);

                const descricaoCell = document.createElement('td');
                descricaoCell.textContent = item.descricaoProd;
                row.appendChild(descricaoCell);

                const quantidadeCell = document.createElement('td');
                quantidadeCell.textContent = item.quantidadeProd;
                row.appendChild(quantidadeCell);

                const valorCell = document.createElement('td');
                valorCell.textContent = item.valorProd;
                row.appendChild(valorCell);

                // Adiciona um botão de "Alterar" na tabela
                const editCell = document.createElement('td');
                const editButton = document.createElement('button');
                editButton.textContent = 'Alterar';
                editButton.className = 'btn btn-warning';
                editButton.onclick = function(){
                    alterarProduto(item);
                }
                editCell.appendChild(editButton);
                row.appendChild(editCell);

                const deleteCell = document.createElement('td');
                const deleteButton = document.createElement('button');
                deleteButton.textContent = 'Excluir';
                deleteButton.className = 'btn btn-danger';
                deleteButton.onclick = function() {
                    deleteProduto(item.idProd, row);
                };
                deleteCell.appendChild(deleteButton);
                row.appendChild(deleteCell);

                tableBody.appendChild(row);
            });
        })
        .catch(error => console.error('Erro ao obter os dados:', error));
});

function deleteProduto(id, row) {
    if (confirm('Você tem certeza que deseja excluir este produto?')) {
        fetch(`http://localhost:8080/adm/produto?id=${id}`, {
            method: 'DELETE',
        })
        .then(response => {
            if (response.ok) {
                row.remove();
                alert('Produto excluído com sucesso!');
            } else {
                return response.text().then(text => { throw new Error(text); });
            }
        })
        .catch(error => alert('Erro ao excluir o produto: ' + error.message));
    }
}

function alterarProduto(produto)
{
    const resp = document.getElementById("respostaAlterar");

    resp.innerHTML = `
    <div class=" col-lg-6 gradient shadow p-3 text-center">
                  <h4 class="mb-3">Alterar Produto</h4>
                  <form class="needs-validation" id="" novalidate>
                    <div class="row g-3">
                        <div class="col-sm-12">
                            <label for="nomeProd" class="form-label">Nome do Produto</label>
                            <input type="text" class="form-control" id="nomeProd" name="nomeProd" placeholder="${produto.nomeProd}" required>
                            <div class="invalid-feedback">
                                Nome válido é necessário.
                            </div>
                        </div>
                
                        <div class="col-12">
                            <label for="descricaoProd" class="form-label">Descrição</label>
                            <br>
                            <textarea name="descricaoProd" id="descricaoProd" class="form-control" placeholder="${produto.descricaoProd}"></textarea>
                            <div class="invalid-feedback">
                                Por favor, informe a descrição do produto.
                            </div>
                        </div>
                
                        <div class="col-md-5">
                            <label for="tipoprod" class="form-label">Tipo do Produto</label>
                            <select class="form-select" id="tipoprod" name="tipoprod" required>
                                <!-- Opções para o tipo do produto -->
                            </select>
                            <div class="invalid-feedback">
                                Selecione um tipo.
                            </div>
                        </div>
                
                        <div class="col-md-4">
                            <label for="valorProd" class="form-label">Preço</label>
                            <input type="text" class="form-control" id="valorProd" name="valorProd" placeholder="R$ 0.00" required>
                            <div class="invalid-feedback">
                                Preço válido é necessário.
                            </div>
                        </div>
                
                        <div class="col-md-3">
                            <label for="quantidadeProd" class="form-label">Quantidade</label>
                            <input type="number" class="form-control" id="quantidadeProd" name="quantidadeProd" placeholder="" required value="1" max="999">
                            <div class="invalid-feedback">
                                Necessário informar uma quantidade.
                            </div>
                        </div>
                    </div>
                
                    <hr class="my-4">
                
                    <button class="w-100 btn btn-primary btn-lg" type="submit">Alterar Produto!</button>
                    </br>
                    <button class="btn-primary" type="button" onclick="cancelarForm()">Cancelar </button>
                  </form>
                </div>`
}

function cancelarForm()
{
    const resp = document.getElementById("respostaAlterar");

    resp.innerHTML = "";
}