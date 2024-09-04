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
            option.value = tipo.idTipoProduto;
            option.textContent = tipo.nomeTipoProduto;
            select.appendChild(option);
        });
    } catch (error) {
        console.error('Erro ao obter tipos', error);
        alert('Erro ao obter tipos de produtos. Por favor, tente novamente mais tarde.');
    }
}

const loadTipoProdAlterar = async () => {
    try {
        const response = await fetch('http://localhost:8080/adm/allprodtipo', {
            method: 'GET',
        });

        const data = await response.json();
        console.log('Dados recebidos:', data); // Adicione este log

        const select = document.querySelector('#tipoprodalterar');

        data.forEach(tipo => {
            console.log('Adicionando tipo:', tipo); // Adicione este log
            const option = document.createElement('option');
            option.value = tipo.idTipoProduto;
            option.textContent = tipo.nomeTipoProduto;
            select.appendChild(option);
        });
    } catch (error) {
        console.error('Erro ao obter tipos', error);
        alert('Erro ao obter tipos de produtos. Por favor, tente novamente mais tarde.');
    }
}


document.getElementById('produtoForm').addEventListener('submit', function (event) {
    event.preventDefault(); // Impede o envio padrão do formulário

    const myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");

    const nomeProd = document.getElementById('nomeProd').value;
    const descricaoProd = document.getElementById('descricaoProd').value;
    const valorProd = document.getElementById('valorProd').value;
    const quantidadeProd = document.getElementById('quantidadeProd').value;
    const idTipoProduto = document.getElementById('tipoprod').value;
    console.log(valorProd)

    const raw = JSON.stringify({
        "nomeProd": nomeProd,
        "descricaoProd": descricaoProd,
        "idTipoProd": {
            "idTipoProduto": idTipoProduto,
            "nometipo": ""
        },
        "valorProd": valorProd,
        "quantidadeProd": quantidadeProd
    });

    const requestOptions = {
        method: "POST",
        headers: myHeaders,
        body: raw,
        redirect: "follow"
    };

    if(confirm("Deseja inserir este produto?"))
    {
        fetch("http://localhost:8080/adm/produto", requestOptions)
        .then((response) => {response.text()
            location.reload()
        })
        .then((result) => console.log(result))
        .catch((error) => console.error(error));
    }
});









