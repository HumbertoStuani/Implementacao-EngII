
function loginRequ() {
    const login = document.getElementById("login").value;
    const senha = document.getElementById("senha").value;
    const URL = `http://localhost:8080/doacao/get-usuario?login=${login}&senha=${senha}`;

    fetch(URL, {
        method: 'GET'
    })
    .then(resp => {
        if (!resp.ok) {
            throw new Error('Não foi possível obter o usuário');
        }
        return resp.json(); // Retorna uma promessa que resolve os dados JSON
    })
    .then(data => {
        showTable();
        const id = data.id;
        const nome = data.nome;
        localStorage.setItem("id", id);
        localStorage.setItem("nome", nome);
        const nomeUsuario = document.getElementById("usuario");
        const novaLinha = document.createElement('h3');
        novaLinha.innerHTML =`
        Nome: ${nome} CPF: ${data.cpf}
        `;
        nomeUsuario.appendChild(novaLinha);
    })
    .catch(error => {
        console.error('Erro ao realizar a requisição:', error);
        var table = document.getElementById('productTable');
        table.classList.add('hidden');
    });
}

function showTable() {
    var table = document.getElementById('cadastroDoacao');
    table.classList.toggle('hidden');
}

document.addEventListener('DOMContentLoaded', function () {
    const templateDoacaoModal = document.getElementById('templateDoacaoModal').content;
    document.body.appendChild(document.importNode(templateDoacaoModal, true));
});


function abrirModalDoacao() {
    carregarProdutos();
    $('#doacaoModal').modal('show'); // Usando jQuery e Bootstrap para exibir o modal
}

function abrirModalValorDoacao() {
    $('#modalValorDoacao').modal('show'); // Usando jQuery e Bootstrap para exibir o modal
}

async function carregarProdutos() {
    try {
        const responseProdutos = await fetch('http://localhost:8080/doacao/all-produtos', {
            method: 'GET'
        });

        if (!responseProdutos.ok) {
            throw new Error('Erro na requisição dos produtos');
        }

        const produtos = await responseProdutos.json();
        const produtosSelect = document.getElementById('doacaoInput');
        let options = `<option value="">Selecione um Produto</option>`;
        produtos.forEach(produto => {
            options += `<option value="${produto.id}">${produto.nomeProd}</option>`;
        });
        produtosSelect.innerHTML = options;
    } catch (error) {
        console.error('Erro ao carregar Produtos:', error);
    }
}

function handleDoacaoSubmit(event) {
    event.preventDefault(); // Prevent form submission

    const produtoSelect = document.getElementById('doacaoInput');
    const produtoNome = produtoSelect.options[produtoSelect.selectedIndex].text;
    const produtoQtde = document.getElementById('doacaoQtde').value;

    // Crie uma nova linha para a tabela
    const tabela = document.getElementById('tabelaId');
    const novaLinha = document.createElement('tr');

    novaLinha.innerHTML = `
        <td>${produtoNome}</td>
        <td>${produtoQtde}</td>
    `;

    tabela.appendChild(novaLinha);

    // Feche o modal
    $('#doacaoModal').modal('hide');
}

function getCurrentDate() {
    const today = new Date();
    const year = today.getFullYear();
    const month = (today.getMonth() + 1).toString().padStart(2, '0');
    const day = today.getDate().toString().padStart(2, '0');
    return `${year}-${month}-${day}`;
}

function validateDate() {
    const inputDate = document.getElementById('myDate').value;
    const selectedDate = new Date(inputDate);
    const dayOfWeek = selectedDate.getDay();

    if (dayOfWeek === 0 || dayOfWeek === 6) {
        alert('Por favor escolha um dia futuro.');
        document.getElementById('myDate').value = getCurrentDate();
    }
}

function cadastrarDoacaoDinheiro() {
    // Obter dados adicionais
    const data = document.getElementById('myDate').value;
    const userId = localStorage.getItem("id");
    const valor = document.getElementById("valorDoacao").value; // Certifique-se de acessar o valor corretamente
    const descricao = "Dinheiro valor: " + valor;

    // Criar objeto DoacaoDTO
    const doacaodinehiroDTO = {
        descr: descricao,
        data: data,
        val: valor, // Valor da doação
        userId: userId
    };

    // Enviar a solicitação POST
    fetch('http://localhost:8080/doacao/fazer-doacao-dinheiro', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(doacaodinehiroDTO)
    })
    .then(resp => {
        if (!resp.ok) {
            throw new Error('Erro ao cadastrar a doação');
        }
        return resp.text();
    })
    .then(data => {
        console.log('Doação cadastrada com sucesso:', data);
        $('#modalValorDoacao').modal('hide');
        alert("Doação cadastrada com sucesso");
    })
    .catch(error => {
        console.error('Erro ao cadastrar a doação:', error);
    });
}



function validateField(field) {
    if (!field.value.trim()) {
        field.classList.add('invalid');
        return false;
    } else {
        field.classList.remove('invalid');
        return true;
    }
}

function validateForm() {
    const descricaoField = document.getElementById('descr');
    const dataField = document.getElementById('myDate');

    const isDescricaoValid = validateField(descricaoField);
    const isDataValid = validateField(dataField);

    return isDescricaoValid && isDataValid;
}

function cadastrarDoacao() {
    // Obter dados da tabela
    const tableRows = document.querySelectorAll('#tabelaId tr');
    const produtosDoacao = [];

    tableRows.forEach(row => {
        const cells = row.querySelectorAll('td');
        if (cells.length > 0) {
            const nome = cells[0].innerText;
            const quantidade = parseInt(cells[1].innerText);
            produtosDoacao.push({ nome, quantidade });
        }
    });

    // Validar formulário
    if (!validateForm()) {
        alert('Preencha todos os campos obrigatórios');
        return;
    }

    // Obter dados adicionais
    const descricao = document.getElementById('descr').value;
    const data = document.getElementById('myDate').value;
    const userId = localStorage.getItem("id");

    // Criar objeto DoacaoDTO
    const doacaoDTO = {
        descr: descricao,
        data: data,
        userId: userId
    };

    // Criar objeto de encapsulamento
    const doacaoRequestDTO = {
        produtosDoacao: produtosDoacao,
        doacaoDTO: doacaoDTO
    };

    // Enviar a solicitação POST
    fetch('http://localhost:8080/doacao/fazer-doacao', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(doacaoRequestDTO)
    })
    .then(resp => {
        if (!resp.ok) {
            throw new Error('Erro ao cadastrar a doação');
        }
        return resp.text(); // Mudança aqui para retornar como texto
    })
    .then(data => {
        console.log('Resposta da API:', data);
        alert("Doação cadastrada com sucesso");

        // Limpar os campos do formulário
        document.getElementById('descr').value = '';
        document.getElementById('myDate').value = '';

        // Remover todas as linhas da tabela
        const tabelaId = document.getElementById('tabelaId');
        while (tabelaId.firstChild) {
            tabelaId.removeChild(tabelaId.firstChild);
        }
    })
    .catch(error => {
        console.error('Erro ao cadastrar a doação:', error);
    });
}

// Remover a classe 'invalid' ao preencher corretamente os campos
document.getElementById('descr').addEventListener('input', function() {
    validateField(this);
});
document.getElementById('myDate').addEventListener('input', function() {
    validateField(this);
});




