
async function fetchData() {
    const response = await fetch('http://localhost:8080/adm/allAguardando');
    return await response.json();
}

async function getQtdeProduto(id) {
    const response = await fetch("http://localhost:8080/adm/qtdeProdDoacao?id=" + id);
    const text = await response.text();
    return parseFloat(text); // Supondo que a quantidade seja um número
}

async function getDoador(id) {
    const response = await fetch("http://localhost:8080/adm/pessoaDoacao?id=" + id);
    const text = await response.text();
    return text; // Supondo que a quantidade seja um número
}


async function populateTable(data) {
    const tableBody = document.querySelector('#doacoes tbody');
    tableBody.innerHTML = '';

    for (const item of data) {
        const row = document.createElement('tr');

        const idCell = document.createElement('td');
        idCell.textContent = item.id;
        row.appendChild(idCell);

        const descricaoCell = document.createElement('td');
        descricaoCell.textContent = item.descricao;
        row.appendChild(descricaoCell);

        const dataCell = document.createElement('td');
        dataCell.textContent = formatDateToBrazilian(item.dataAgendamento);
        row.appendChild(dataCell);

        const situacaoCell = document.createElement('td');
        situacaoCell.textContent = item.situacao;
        row.appendChild(situacaoCell);

        const quantidadeCell = document.createElement('td');
        const quantidade = await getQtdeProduto(item.id);
        quantidadeCell.textContent = quantidade;
        row.appendChild(quantidadeCell);

        const doadorCell = document.createElement('td');
        const doador = await getDoador(item.id);
        doadorCell.textContent = doador;
        row.appendChild(doadorCell);

        const aproveCell = document.createElement('td');
        const aproveButton = document.createElement('button');
        aproveButton.textContent = 'Aprovar';
        aproveButton.style = "background-color: rgb(16, 36, 75); border-color: rgb(16, 36, 75); color: white;";
        aproveButton.className = 'btn btn-warning';
        aproveButton.onclick = function() {
            aprovaDoacao(item);
        };
        aproveCell.appendChild(aproveButton);
        row.appendChild(aproveCell);

        const reproveCell = document.createElement('td');
        const reproveButton = document.createElement('button');
        reproveButton.textContent = 'Reprovar';
        reproveButton.className = 'btn btn-danger';
        reproveButton.onclick = function() {
            reprovaDoacao(item);
        };
        reproveCell.appendChild(reproveButton);
        row.appendChild(reproveCell);

        const visualizarCell = document.createElement('td');
        const visualizarButton = document.createElement('button');
        visualizarButton.textContent = 'Visualizar';
        visualizarButton.style = 'background-color: green; border-color: green; color: white;'
        visualizarButton.className = 'btn btn-warning';
        visualizarButton.onclick = function() {
            openModal(item.id);
        };
        visualizarCell.appendChild(visualizarButton);
        row.appendChild(visualizarCell);

        tableBody.appendChild(row);
    }
}

document.addEventListener('DOMContentLoaded', async function() {
    try {
        const data = await fetchData();

        document.getElementById('sortAsc').addEventListener('click', async () => {
            const sortedData = await sortData(data, 'asc');
            populateTable(sortedData);
        });

        document.getElementById('sortDesc').addEventListener('click', async () => {
            const sortedData = await sortData(data, 'desc');
            populateTable(sortedData);
        });


        populateTable(data);
    } catch (error) {
        console.error('Erro ao obter os dados:', error);
    }
});


function aprovaDoacao(item)
{
    const myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");

    const raw = JSON.stringify({
    "id": item.id,
    "descricao": item.descricao,
    "dataAgendamento": item.dataAgendamento,
    "pessoaId": item.pessoaId,
    "colaboradorId": item.colaboradorId,
    "situacao": "aprovado"
    });

    const requestOptions = {
    method: "PATCH",
    headers: myHeaders,
    body: raw,
    redirect: "follow"
    };

    if(confirm("Deseja aprovar esta Doação?"))
    {
        fetch("http://localhost:8080/adm/alterarSituacao?id="+item.id, requestOptions)
        .then((response) => response.text())
        setTimeout(function() {
            location.reload(true);
        }, 1000);
    }
    
}

function reprovaDoacao(item)
{
    const myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");

    const raw = JSON.stringify({
    "id": item.id,
    "descricao": item.descricao,
    "dataAgendamento": item.dataAgendamento,
    "pessoaId": item.pessoaId,
    "colaboradorId": item.colaboradorId,
    "situacao": "reprovado"
    });

    const requestOptions = {
    method: "PATCH",
    headers: myHeaders,
    body: raw,
    redirect: "follow"
    };

    if(confirm("Deseja reprovar esta Doação?"))
    {
        fetch("http://localhost:8080/adm/alterarSituacao?id="+item.id, requestOptions)
        .then((response) => response.text())
        setTimeout(function() {
            location.reload(true);
        }, 1000);
    }
}

function formatDateToBrazilian(dateString) {
    try {
        const dateParts = dateString.split(' ');
        const [day, month, year] = dateParts[0].split('/');
        const [hour, minute, second] = dateParts[1].split(':');
        
        const formattedDate = new Date(`${year}-${month}-${day}T${hour}:${minute}:${second}`);
        if (isNaN(formattedDate)) {
            throw new Error("Invalid Date");
        }
        
        return new Intl.DateTimeFormat('pt-BR', {
            day: '2-digit',
            month: '2-digit',
            year: 'numeric',
            hour: '2-digit',
            minute: '2-digit',
            second: '2-digit'
        }).format(formattedDate);
    } catch (error) {
        console.error("Invalid time value:", dateString);
        return dateString; // Retorna a string original se houver um erro
    }
}

async function sortData(data, order) {
    const augmentedData = await Promise.all(data.map(async item => ({
        ...item,
        quantidade: await getQtdeProduto(item.id)
    })));

    if (order === 'asc') {
        return augmentedData.sort((a, b) => a.quantidade - b.quantidade);
    } else {
        return augmentedData.sort((a, b) => b.quantidade - a.quantidade);
    }
}

// Obtém o modal
var modal = document.getElementById("modal");

// Obtém o elemento <span> que fecha o modal
var closeButton = document.getElementsByClassName("btn-close")[0];

// Obtém o div para exibir os dados no modal
var modalContent = document.getElementById("modalContent");

// Função para abrir o modal e buscar dados
function openModal(id) {
    modal.style.display = "block";
    
    // Limpa o conteúdo anterior do modal
    modalContent.innerHTML = "Carregando...";

    // Busca dados da doação via fetch
    fetch(`http://localhost:8080/adm/todosProd?id=` + id)
        .then(response => response.json())
        .then(data => {
            // Cria a tabela com classes Bootstrap
            const table = document.createElement('table');
            table.className = "table table-striped";

            // Cria o cabeçalho da tabela
            const thead = document.createElement('thead');
            const headerRow = document.createElement('tr');

            const thNomeProd = document.createElement('th');
            thNomeProd.textContent = "Nome do Produto";
            headerRow.appendChild(thNomeProd);

            const thQtde = document.createElement('th');
            thQtde.textContent = "Quantidade";
            headerRow.appendChild(thQtde);


            thead.appendChild(headerRow);
            table.appendChild(thead);

            // Cria o corpo da tabela
            const tbody = document.createElement('tbody');

            // Preenche a tabela com os dados
            for (const item of data) {
                const row = document.createElement('tr');
                
                const nomeProd = document.createElement('td');
                nomeProd.textContent = item.produto.nomeProd;
                row.appendChild(nomeProd);

                const qtdeCell = document.createElement('td');
                qtdeCell.textContent = item.quantidade_produto;
                row.appendChild(qtdeCell);

                tbody.appendChild(row);
            }

            table.appendChild(tbody);

            // Limpa o conteúdo anterior e insere a tabela no modal
            modalContent.innerHTML = "";
            modalContent.appendChild(table);
        })
        .catch(error => {
            modalContent.innerHTML = "Erro ao carregar dados.";
            console.error('Erro:', error);
        });
}

// Quando o usuário clicar no botão de fechar, fecha o modal
closeButton.onclick = function() {
    modal.style.display = "none";
}

// Quando o usuário clicar fora do modal, fecha o modal
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}

// Adiciona evento de clique a todos os botões de abrir modal
document.querySelectorAll('.openModalBtn').forEach(button => {
    button.addEventListener('click', function() {
        var donationId = this.getAttribute('data-id');
        openModal(donationId);
    });
});















