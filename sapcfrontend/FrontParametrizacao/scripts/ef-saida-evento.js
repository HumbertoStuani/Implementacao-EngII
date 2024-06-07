let ativo = false

document.addEventListener("DOMContentLoaded", () => {
    fetchEventos()
})

async function fetchEventos() {
    fetch("http://localhost:8080/api/evento/get/all", {
        method: "GET",
        headers: {
            "Content-Type": "application/json"
        }
    })
        .then((response) => response.json())
        .then((result) => {
            const eventosContainer = document.getElementById("eventosContainer");
            result.forEach((evento) => {
                const card = document.createElement("div")
                card.classList.add("bg-white", "shadow-md", "rounded-lg", "p-6", "text-center", "grid", "grid-cols-1")
                card.setAttribute("id", "cardEvent" + evento.id)
                card.innerHTML = `
                    <p class="hidden" id="eventoId">${evento.id}</p>
                    <h3 class="text-xl font-semibold mb-2">${evento.nomeevento}</h3>
                    <p class="text-gray-600">Descrição: ${evento.descricao}</p>
                    <p class="text-gray-600">Data de Início: ${formatarData(evento.dataInicio)}</p>
                    <p class="text-gray-600">Data Final: ${formatarData(evento.dataFinal)}</p>
                    <p class="text-gray-600">Endereço: ${evento.endereco}, ${evento.bairro}, ${evento.cidade}</p>
                    <p class="text-gray-600">Tipo de Evento: ${evento.idTipoEvento.nomeTipo}</p>
                    <p class="text-gray-600">Colaborador Responsável: ${evento.idColaborador.cargo}</p>
                    <button onclick="listaProdutos(${evento.id})" class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded">Selecionar Evento</button>
                    <form id="form-produtos-${evento.id}" class="flex flex-col space-y-4 mt-4"></form>                    
                `
                eventosContainer.appendChild(card)
            })
        })
        .catch((error) => console.error("Erro ao carregar eventos:", error))
}

function listaProdutos(eventoId) {
    const formProdutos = document.getElementById(`form-produtos-${eventoId}`)
    if (!ativo) {
        ativo = true;
        formProdutos.innerHTML = `
        <h3 class='text-xl font-semibold mb-2'>Produtos ${eventoId}</h3>
    `

        fetch("http://localhost:8080/adm/allprodutos")
            .then(response => response.json())
            .then(result => {
                result.forEach((produto) => {
                    if (produto.quantidadeProd > 0) {
                        const card = document.createElement("div");
                        card.classList.add("bg-gray-200", "shadow-md", "rounded-lg", "p-4", "text-center", "grid", "grid-cols-4", "items-center", "gap-4");
                        card.innerHTML = `
                            <p class="text-gray-600">Nome: ${produto.nomeProd}</p>
                            <p class="text-gray-600">Quantidade Disponível: ${produto.quantidadeProd}</p>
                            <p class="text-gray-600">Valor: ${produto.valorProd}</p>
                            <input type="hidden" name="produtoId-${produto.idProd}" value="${produto.idProd}">
                            <input type="number" name="quantidade-${produto.idProd}" class="mt-2 p-2 border border-gray-300 rounded" placeholder="Quantidade" min="0">
                            <p id="erro-${produto.idProd}" class="text-red-500 hidden">Quantidade excede o disponível!</p>
                        `
                        formProdutos.appendChild(card)
                    }

                })

                const divButton = document.createElement("div")
                divButton.classList.add("flex", "justify-center", "gap-4", "mt-4");


                const cancelarButton = document.createElement("button")
                cancelarButton.type = "button"
                cancelarButton.classList.add("bg-red-500", "hover:bg-red-700", "text-white", "font-bold", "py-2", "px-4", "rounded", "mt-4")
                cancelarButton.textContent = "Cancelar"
                cancelarButton.addEventListener("click", () => {
                    ativo = false
                    formProdutos.innerHTML = " "
                })
                divButton.appendChild(cancelarButton)

                const coletarButton = document.createElement("button")
                coletarButton.type = "button"
                coletarButton.classList.add("bg-blue-500", "hover:bg-blue-700", "text-white", "font-bold", "py-2", "px-4", "rounded", "mt-4")
                coletarButton.textContent = "Coletar Informações"
                coletarButton.addEventListener("click", () => {
                    const formData = new FormData(formProdutos)
                    const produtosSelecionados = []

                    result.forEach((produto) => {
                        const quantidade = formData.get(`quantidade-${produto.idProd}`);
                        if (quantidade && parseInt(quantidade, 10) > 0) {
                            if (parseInt(quantidade, 10) > produto.quantidadeProd) {
                                document.getElementById(`erro-${produto.idProd}`).classList.remove('hidden')
                            } else {
                                produtosSelecionados.push({ IdProduto: produto.idProd, quantidade: parseInt(quantidade, 10) })
                            }
                        }
                    })

                    if (produtosSelecionados.length !== 0) {
                        const saidaEvento = JSON.stringify({
                            "idEvento": eventoId,
                            "produtosSaida": produtosSelecionados,
                        })

                        console.log(saidaEvento)

                        const requestOptions = {
                            method: "POST",
                            headers: {
                                "Content-Type": "application/json",
                            },
                            body: saidaEvento,
                            redirect: "follow"
                        }

                        fetch("http://localhost:8080/api/evento/saida", requestOptions)
                            .then((response) => response.text())
                            .then((result) => {
                                if (result) {
                                    alert("Saida efetuada com sucesso")
                                }
                                else {
                                    alert(result)
                                }
                            })
                    }

                })

                divButton.appendChild(coletarButton)
                formProdutos.appendChild(divButton)

            })
            .catch((error) => console.error("Erro ao carregar produtos:", error));
    } else {
        ativo = false
        formProdutos.innerHTML = " "
    }

}

function formatarData(data) {
    const dataObj = new Date(data)
    const dia = String(dataObj.getDate()).padStart(2, "0")
    const mes = String(dataObj.getMonth() + 1).padStart(2, "0")
    const ano = dataObj.getFullYear()
    return `${dia}/${mes}/${ano}`
}