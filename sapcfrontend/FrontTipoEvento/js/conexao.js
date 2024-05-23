function carregaTipoEventos() {
    const tag = document.getElementById("tipos-tabela");
    const URL = "http://localhost:8080/tp-evento/get-all-tipoevento";

    fetch(URL)
    .then(resp => resp.json())
    .then(json => {
        let lista = "";
        for (let item of json) {
            lista += `
                <tr id="row-${item.id}">
                    <th scope="row">${item.id}</th>
                    <td>${item.nomeTipo}</td>
                    <td>
                        <button style="background-color: red; color: white; border-radius: 5px; padding: 5px" type="button" onclick="excluirTipoEvento(${item.id})">Excluir</button>
                        <button style="background-color: blue; color: white; border-radius: 5px; padding: 5px" type="button" onclick="mostrarAlterarForm(${item.id}, '${item.nomeTipo}')">Alterar</button>
                    </td>
                </tr>
            `;
        }
        tag.innerHTML = lista;
    })
    .catch(Err => {
        tag.innerText = "Erro: " + Err;
    });
}

function excluirTipoEvento(id) {
    const URL = `http://localhost:8080/tp-evento/delete-tipoevento?id=${id}`;

    fetch(URL, {
        method: 'DELETE'
    })
    .then(resp => resp.text())
    .then(text => {
        alert("Exclusão realizada");
        carregaTipoEventos(); 
    })
    .catch(error => {
        console.error(error);
    });
}

function mostrarAlterarForm(id, nomeTipo) {
    document.getElementById('alterarId').value = id;
    document.getElementById('alterarTipo').value = nomeTipo;
    document.getElementById('alterarFormContainer').style.display = 'block';
}

function fecharAlterarForm() {
    document.getElementById('alterarFormContainer').style.display = 'none';
}

document.getElementById('alterarForm').addEventListener('submit', function(event) {
    event.preventDefault();
    alterarTipoEvento();
});

function alterarTipoEvento() {
    const id = document.getElementById('alterarId').value;
    const nomeTipo = document.getElementById('alterarTipo').value;
    const URL = `http://localhost:8080/tp-evento/update-tipoevento?id=${id}&tipo=${encodeURIComponent(nomeTipo)}`;

    fetch(URL, {
        method: 'PUT'
    })
    .then(resp => resp.text())
    .then(text => {
        alert("alteração realizada");
        fecharAlterarForm();
        carregaTipoEventos(); 
    })
    .catch(error => {
        console.error(error);
    });
}


function cadastrarTipoEvento(){
    const URL = "http://localhost:8080/tp-evento/add-tipoevento-envio";
    var fdados = document.getElementById("fdados");
    var formData = new FormData(fdados); 

    fetch(URL, {
        method: 'POST',
        body: formData 
    })
    .then(resp => {
        return resp.text();
    })
    .then(text => {
        fecharCadastroForm();
        carregaTipoEventos();
    })
    .catch(error => {
        console.error(error);
    });
}

function mostrarCadastroForm() {
    document.getElementById('cadastroFormContainer').style.display = 'block';
}

function fecharCadastroForm() {
    document.getElementById('cadastroFormContainer').style.display = 'none';
}