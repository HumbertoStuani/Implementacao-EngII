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
                valorCell.textContent = "R$ "+item.valorProd;
                row.appendChild(valorCell);

                const nomeTipoCell = document.createElement('td');
                nomeTipoCell.textContent = item.idTipoProd.nomeTipoProduto;
                row.appendChild(nomeTipoCell);

                // Adiciona um botão de "Alterar" na tabela
                const editCell = document.createElement('td');
                const editButton = document.createElement('button');
                editButton.textContent = 'Alterar';
                editButton.style = "background-color: rgb(16, 36, 75); border-color: rgb(16, 36, 75); color: white;"
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

const alterarProduto = (produto) => {
  const resp = document.getElementById("respostaAlterar");

  resp.innerHTML = `
  <div class="col-lg-6 gradient shadow p-3 text-center">
    <h4 class="mb-3">Alterar Produto</h4>
    <form class="needs-validation" id="alterarProdForm" onsubmit="submitAlteracaoProduto(); return false;" novalidate>
      <div class="row g-3">
        <div class="col-sm-12">
          <input type="number" id="idProd" name="idProd" value="${produto.idProd}" hidden>
          <label for="nomeProd" class="form-label">Nome do Produto</label>
          <input type="text" class="form-control" id="nomeProdAlterar" name="nomeProdAlterar" placeholder="" value="${produto.nomeProd}" oninput="validarCampo()" required>
          <div class="invalid-feedback">
            Nome válido é necessário.
          </div>
        </div>

        <div class="col-12">
          <label for="descricaoProd" class="form-label">Descrição</label>
          <br>
          <textarea name="descricaoProdAlterar" id="descricaoProdAlterar" class="form-control" placeholder="" oninput="validarCampoDescr()">${produto.descricaoProd}</textarea>
          <div class="invalid-feedback">
            Por favor, informe a descrição do produto.
          </div>
        </div>

        <div class="col-md-5">
          <label for="tipoprodalterar" class="form-label">Tipo do Produto</label>
          <select class="form-select" id="tipoprodalterar" name="tipoprodalterar" required>
          <option value="" selected></option>
            <!-- Opções para o tipo do produto -->
          </select>
          <div class="invalid-feedback">
            Selecione um tipo.
          </div>
        </div>

        <div class="col-md-4">
          <label for="valorProd" class="form-label">Preço</label>
          <input type="text" class="form-control" id="valorProdAlterar" name="valorProdAlterar" placeholder="" value="${produto.valorProd}" required oninput="validarPreco(this)">
          <div class="invalid-feedback">
            Preço válido é necessário.
          </div>
        </div>

        <div class="col-md-3">
          <label for="quantidadeProd" class="form-label">Quantidade</label>
          <input type="number" class="form-control" id="quantidadeProdAlterar" name="quantidadeProdAlterar" placeholder="" required value="${produto.quantidadeProd}" min="1" max="999">
          <div class="invalid-feedback">
            Necessário informar uma quantidade.
          </div>
        </div>
      </div>

      <hr class="my-4">

      <button class="w-100 btn btn-primary btn-lg" type="submit id="submitBtnAlterar" style="background-color: rgb(16, 36, 75); border-color: rgb(16, 36, 75); color: white;" disable>Alterar Produto!</button>
      </br></br>
      <button class="btn-primary" type="button" onclick="cancelarForm()" style="background-color: red; border-color: red; color: white;">Cancelar</button>
    </form>
  </div>`;

  // Chama a função para carregar os tipos de produto
  loadTipoProdAlterar();
}

const submitAlteracaoProduto = () => {
  const form = document.getElementById("alterarProdForm");

  console.log("cheguei aqui")
  // Verifica se o formulário é válido antes de enviar os dados
  if (!form.checkValidity()) {
    form.classList.add('was-validated');
    return;
  }

  // Extrai os valores do formulário
  const idProd = document.getElementById("idProd").value;
  const nomeProd = document.getElementById("nomeProdAlterar").value;
  const descricaoProd = document.getElementById("descricaoProdAlterar").value;
  const tipoProd = document.getElementById("tipoprodalterar").value;
  const valorProd = parseFloat(document.getElementById("valorProdAlterar").value);
  const quantidadeProd = parseInt(document.getElementById("quantidadeProdAlterar").value);

  console.log(idProd,nomeProd,descricaoProd,tipoProd,valorProd,quantidadeProd);

  const raw = JSON.stringify({
    "nomeProd": nomeProd,
    "descricaoProd": descricaoProd,
    "quantidadeProd": quantidadeProd,
    "valorProd": valorProd,
    "idTipoProd": {
      "idTipoProduto": tipoProd,
      "nometipo": ""
    }
  });

  // Configuração da requisição
  const myHeaders = new Headers();
  myHeaders.append("Content-Type", "application/json");

  const requestOptions = {
    method: "PATCH",
    headers: myHeaders,
    body: raw,
    redirect: "follow"
  };
  
  if(confirm("Deseja alterar este produto?"))
  {
    fetch("http://localhost:8080/adm/produto?id="+idProd, requestOptions)
    .then((response) => {response.text()
      location.reload()
    })
    .then((result) => console.log(result))
    .catch((error) => console.error(error));
  }
}


function cancelarForm()
{
    const resp = document.getElementById("respostaAlterar");

    resp.innerHTML = "";
}

function validarCampo() {
  var nomeProduto = document.getElementById('nomeProdAlterar').value.trim();

  if (nomeProduto === '') {
      // Se o campo estiver vazio, exiba uma mensagem de erro
      document.getElementById('nomeProdAlterar').classList.add('is-invalid');
      return false; // Retorna false para indicar que o campo não é válido
  } else {
      // Se o campo não estiver vazio, remova qualquer mensagem de erro existente
      document.getElementById('nomeProdAlterar').classList.remove('is-invalid');
      return true; // Retorna true para indicar que o campo é válido
  }
}

function validarCampoDescr() {
  var nomeProduto = document.getElementById('descricaoProdAlterar').value.trim();

  if (nomeProduto === '') {
      // Se o campo estiver vazio, exiba uma mensagem de erro
      document.getElementById('descricaoProdAlterar').classList.add('is-invalid');
      return false; // Retorna false para indicar que o campo não é válido
  } else {
      // Se o campo não estiver vazio, remova qualquer mensagem de erro existente
      document.getElementById('descricaoProdAlterar').classList.remove('is-invalid');
      return true; // Retorna true para indicar que o campo é válido
  }
}

