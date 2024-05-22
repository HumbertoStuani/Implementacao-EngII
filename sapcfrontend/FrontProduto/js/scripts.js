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

// document.getElementById('produtoForm').addEventListener('submit', function(event) {
//     event.preventDefault(); // Impede o envio padrão do formulário

//     // Captura os valores do formulário
//     const nomeProd = document.getElementById('nomeProd').value;
//     const descricaoProd = document.getElementById('descricaoProd').value;
//     const valorProd = document.getElementById('valorProd').value;
//     const quantidadeProd = document.getElementById('quantidadeProd').value;
//     const idTipoProduto = document.getElementById('tipoprod').value;

//     console.log({id: idTipoProduto});

//     // Monta o objeto com os dados do formulário
//     const produto = {
//         nomeProd,
//         descricaoProd,
//         idTipoProd: {
//             idTipoProduto: parseInt(idTipoProduto),
//             nometipo: "aa",
//         },
//         valorProd: parseFloat(valorProd),
//         quantidadeProd: parseInt(quantidadeProd),
//     };

//     console.log(JSON.stringify(produto));

//     // Envia o JSON via fetch API
//     fetch('http://localhost:8080/adm/produto', {
//         method: 'POST',
//         headers: {
//             'Content-Type': 'application/json'
//         },
//         body: produto,
//     })
//     .then(response => response.json())
//     .then(data => {
//         console.log('Success:', data);
//         // Adicione aqui qualquer lógica adicional após o sucesso do envio
//     })
//     .catch((error) => {
//         console.error('Error:', error);
//         // Adicione aqui qualquer lógica adicional em caso de erro
//     });


// });


document.getElementById('produtoForm').addEventListener('submit', function (event) {
    event.preventDefault(); // Impede o envio padrão do formulário

    const myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");

    const nomeProd = document.getElementById('nomeProd').value;
    const descricaoProd = document.getElementById('descricaoProd').value;
    const valorProd = document.getElementById('valorProd').value;
    const quantidadeProd = document.getElementById('quantidadeProd').value;
    const idTipoProduto = document.getElementById('tipoprod').value;

    const raw = JSON.stringify({
        "nomeProd": nomeProd,
        "descricaoProd": descricaoProd,
        "idTipoProd": {
            "idTipoProduto": idTipoProduto,
            "nometipo": "aa"
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

    fetch("http://localhost:8080/adm/produto", requestOptions)
        .then((response) => response.text())
        .then((result) => console.log(result))
        .catch((error) => console.error(error));
});

