const loadTipoProd = async () => {
    try {
        const response = await fetch('http://localhost:8080/adm/allprodtipo', {
            method: 'GET',
        });

        const data = await response.json();
        console.log('Dados recebidos:', data); // Adicione este log

        const select = document.querySelector('#tipoprod');

        data.forEach(tipo => {
            console.log('Adicionando tipo:', tipo); // Adicione este log
            const option = document.createElement('option');
            option.textContent = tipo.nomeTipoProduto;
            select.appendChild(option);
        });
    } catch (error) {
        console.error('Erro ao obter tipos', error);
        alert('Erro ao obter tipos de produtos. Por favor, tente novamente mais tarde.');
    }
}
