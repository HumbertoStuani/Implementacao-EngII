function carregaTipoEventos()
{   const tag=document.getElementById("tipos-tabela");
    const URL="http://localhost:8080/adm/get-all-tipoevento";
    fetch(URL)
    .then(resp=>{
        return resp.json()
        .then(json=>{
            let lista="";
            for (let item of json)
            {
                lista+=`
                <tr>
            <th scope="row">${item.id}</th>
            <td>${item.nomeTipo}</td>
            <td>Otto</td>
            <td>@mdo</td>
            </tr>
            `
            }
            tag.innerHTML=lista;
        })
    })
    .catch(Err=>{
        tag.innerText="Erro"+Err;
    })
}

const URL = "http://localhost:8080/adm/add-tipoevento-envio";
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
        //alert(text);
        //carregaFilmes();
    })
    .catch(error => {
        console.error(error);
    });