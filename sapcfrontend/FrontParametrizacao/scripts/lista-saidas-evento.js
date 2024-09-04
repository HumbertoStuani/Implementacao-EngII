document.addEventListener("DOMContentLoaded", () => {
    fetchSaidaEvento()
})

async function fetchSaidaEvento() {
    fetch("http://localhost:8080/api/saida-evento/get/all", {
        method: "GET",
        headers: {
            "Content-Type": "application/json"
        }
    })
        .then(response => response.json())
        .then(data => {
            const saidasContainer = document.getElementById("saidasEventoContainer")
            saidasContainer.innerHTML = " "

            data.forEach(saida => {
                const saidaElement = document.createElement("div")
                saidaElement.className = "bg-white p-4 rounded-lg shadow-md"

                saidaElement.innerHTML = `
                    <h3 class="text-xl font-bold mb-2">Evento: ${saida.evento.nome}</h3>
                    <p><strong>Descrição:</strong> ${saida.descricao}</p>
                    <p><strong>Data de Saída:</strong> ${new Date(saida.dataSaida).toLocaleString()}</p>
                    <p><strong>Data de Retirada:</strong> ${saida.dataRetirada ? new Date(saida.dataRetirada).toLocaleString() : `<button class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-1 px-2 rounded text-sm" onclick="retirarSaida(${saida.id})">Retirar</button>`}</p>
                    <h4 class="text-lg font-semibold mt-4">Produtos:</h4>
                    <ul class="list-disc list-inside">
                        ${saida.produtosSaidaIds.map(produto => `
                            <li><strong>Produto:</strong> ${produto.nome} - <strong>Quantidade:</strong> ${produto.quantidade}</li>
                        `).join('')}
                    </ul>
                `

                saidasContainer.appendChild(saidaElement);
            })
        })
        .catch(error => {
            console.error("Erro ao buscar dados:", error)
        })
}

function retirarSaida(id) {
    fetch(`http://localhost:8080/api/saida-evento/update/${id}`, {
        method: "GET",
        headers: {
            "Content-Type": "application/json"
        }
    })
        .then(() => {
            const saidasContainer = document.getElementById("saidasEventoContainer")
            saidasContainer.innerHTML = " "
            fetchSaidaEvento()
        })
}

function filterSaidaEvento() {
    const searchInput = document.getElementById("searchInput").value.toLowerCase()
    fetch("http://localhost:8080/api/saida-evento/get/all", {
        method: "GET",
        headers: {
            "Content-Type": "application/json"
        }
    })
        .then(response => response.json())
        .then(data => {
            const filteredData = data.filter(saida => saida.evento.nome.toLowerCase().includes(searchInput))
            const saidasContainer = document.getElementById("saidasEventoContainer")
            saidasContainer.innerHTML = '' // Limpa o conteúdo atual
            filteredData.forEach(saida => {
                const saidaElement = document.createElement("div")
                saidaElement.className = "bg-white p-4 rounded-lg shadow-md"

                saidaElement.innerHTML = `
                    <h3 class="text-xl font-bold mb-2">Evento: ${saida.evento.nome}</h3>
                    <p><strong>Descrição:</strong> ${saida.descricao}</p>
                    <p><strong>Data de Saída:</strong> ${new Date(saida.dataSaida).toLocaleString()}</p>
                    <p><strong>Data de Retirada:</strong> ${saida.dataRetirada ? new Date(saida.dataRetirada).toLocaleString() : `<button class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-1 px-2 rounded text-sm" onclick="retirarSaida(${saida.id})">Retirar</button>`}</p>
                    <h4 class="text-lg font-semibold mt-4">Produtos:</h4>
                    <ul class="list-disc list-inside">
                        ${saida.produtosSaidaIds.map(produto => `
                            <li><strong>Produto:</strong> ${produto.nome} - <strong>Quantidade:</strong> ${produto.quantidade}</li>
                        `).join('')}
                    </ul>
                `

                saidasContainer.appendChild(saidaElement);
            })
        })
        .catch(error => {
            console.error("Erro ao buscar dados:", error)
        })
}