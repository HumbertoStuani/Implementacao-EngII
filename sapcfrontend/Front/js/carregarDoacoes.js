document.addEventListener('DOMContentLoaded', function () {
    const tableBody = document.querySelector('#alldoacoes tbody');
    const ButtonAprove = document.getElementById('aprovadas');
    const ButtonReprove = document.getElementById('reprovadas');

    // Event listener para o botão de doações aprovadas
    ButtonAprove.addEventListener('click', async function () {
        try {
            const response = await fetch('http://localhost:8080/adm/allAprovadas');
            const data = await response.json();
            await populateTableDoacoes(data);
        } catch (error) {
            console.error('Erro ao buscar doações aprovadas:', error);
        }
    });

    // Event listener para o botão de doações reprovadas
    ButtonReprove.addEventListener('click', async function () {
        try {
            const response = await fetch('http://localhost:8080/adm/allReprovadas');
            const data = await response.json();
            await populateTableDoacoes(data);
        } catch (error) {
            console.error('Erro ao buscar doações reprovadas:', error);
        }
    });

    // Função para preencher a tabela com os dados de doações
    async function populateTableDoacoes(data) {
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
});

