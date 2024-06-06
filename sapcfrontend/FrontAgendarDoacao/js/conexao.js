function loginRequ() {
    const login = document.getElementById("login").value;
    const senha = document.getElementById("senha").value;
    const URL = `http://localhost:8080/usuario/get-usuario?login=${login}&senha=${senha}`;

    fetch(URL, {
        method: 'GET'
    })
    .then(resp => {
        if (!resp.ok) {
            throw new Error('Não foi possível obter o usuário');
        }
        return resp.text();
    })
    .then(text => {
        // Aqui você pode fazer o que quiser com a resposta bem-sucedida
        // Por exemplo, mostrar a tabela
        showTable();
    })
    .catch(error => {
        // Se ocorrer um erro, como um BadRequest
        console.error('Erro ao realizar a requisição:', error);
        // Aqui você pode decidir o que fazer, por exemplo, não mostrar a tabela
        var table = document.getElementById('productTable');
        table.classList.add('hidden'); // Certifica-se de que a tabela esteja oculta
        // Ou você pode limpar os dados da tabela, se necessário
    });
}

function showTable() {
    var table = document.getElementById('productTable');
    table.classList.toggle('hidden');
}
