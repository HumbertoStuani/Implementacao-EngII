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
                editButton.onclick = function() {
                    // Preenche o formulário com os dados do produto ao clicar no botão "Alterar"
                    document.getElementById('produtoId').value = item.idProd;
                    document.getElementById('nomeProd').value = item.nomeProd;
                    document.getElementById('descricaoProd').value = item.descricaoProd;
                    document.getElementById('valorProd').value = item.valorProd;
                    document.getElementById('quantidadeProd').value = item.quantidadeProd;
                    document.getElementById('tipoprod').value = item.idTipoProd.idTipoProduto;
                };
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