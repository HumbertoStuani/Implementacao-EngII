<template>
  <div class="container mt-4">
    <h2>Receber Doação</h2>
    <b-form @submit.prevent="handleDonation">
      <!-- Informar Documentos -->
      <br>
      <b-row>
        <span>Informe qualquer um dos documentos para verificar o agendamento da doação</span>
        <br><br>
        <b-col md="6">
          <b-form-group label="RG">
            <b-form-input v-model="donation.rg" @input="handleInput" :required="!donation.cpf"></b-form-input>
          </b-form-group>
        </b-col>
        <b-col md="6">
          <b-form-group label="CPF">
            <b-form-input v-model="donation.cpf" @input="handleInput" :required="!donation.rg"></b-form-input>
          </b-form-group>
        </b-col>
      </b-row>

      <!-- Informar Doação -->
      <b-row v-if="step >= 2">
        <b-col md="12">
          <b-form-group label="Detalhes da Doação">
            <b-form-textarea v-model="donation.details" required></b-form-textarea>
          </b-form-group>
        </b-col>
      </b-row>

      <!-- Confirmar Doação -->
      <b-row v-if="step === 3">
        <b-col md="12">
          <p>Verifique os detalhes da doação abaixo e confirme:</p>
          <ul>
            <li><strong>RG:</strong> {{ donation.rg }}</li>
            <li><strong>CPF:</strong> {{ donation.cpf }}</li>
            <li><strong>Detalhes da Doação:</strong> {{ donation.details }}</li>
          </ul>
        </b-col>
      </b-row>

      <!-- Botões de Ação -->
      <b-row>
        <b-col md="12" class="text-right">
          <b-button v-if="step === 1" type="submit" variant="primary">Verificar Agendamento</b-button>
          <b-button v-if="step === 2" @click="confirmDonation" variant="success">Confirmar Doação</b-button>
          <b-button v-if="step === 3" @click="finalizeDonation" variant="success">Finalizar Doação</b-button>
        </b-col>
      </b-row>
    </b-form>

    <!-- Campo para teste de colaborador -->
    <b-row class="mt-4">
      <b-col md="6">
        <b-form-group label="Código do Colaborador">
          <b-form-input v-model="colaboradorId" required></b-form-input>
        </b-form-group>
      </b-col>
      <b-col md="6" class="d-flex align-items-end">
        <b-button @click="fetchDoacoesByColaborador" variant="secondary">Buscar Doações</b-button>
      </b-col>
    </b-row>

    <div v-if="colaboradorId">
      <h3 class="mt-4">Doações do Colaborador {{ colaboradorId }}</h3>
      <p class="text-muted">Aqui você pode visualizar e gerenciar as doações do colaborador identificado pelo código {{
        colaboradorId }}.</p>
    </div>

    <!-- Tabela de Doações -->
    <div class="card-body px-0 pt-0 pb-2" v-if="doacoes.length > 0">
      <div class="table-responsive p-0">
        <div class="p-3">
          <b-row>
            <b-col md="6" lg="4">
              <b-form-input v-model="searchQuery" placeholder="Pesquisar por descrição ou ID"></b-form-input>
            </b-col>
          </b-row>
        </div>
        <table class="table align-items-center mb-0">
          <thead>
            <tr>
              <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">ID</th>
              <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">Descrição</th>
              <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">Data de Agendamento
              </th>
              <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">Situação</th>
              <th class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">Ações</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="doacao in sortedFilteredDoacoes" :key="doacao.id">
              <td>
                <div class="d-flex px-2 py-1">
                  <div class="d-flex flex-column justify-content-center">
                    <h6 class="mb-0 text-sm">{{ doacao.id }}</h6>
                  </div>
                </div>
              </td>
              <td>
                <p class="text-xs text-secondary mb-0">{{ doacao.descricao }}</p>
              </td>
              <td>
                <p class="text-xs text-secondary mb-0">{{ formatDate(doacao.dataAgendamento) }}</p>
              </td>
              <td>
                <p class="text-xs font-weight-bold mb-0">{{ doacao.situacao }}</p>
              </td>
              <td class="align-middle text-center text-sm">
                <b-button v-if="doacao.situacao === 'aguardando'" @click="showModal(doacao)" variant="primary">
                  Concluir
                </b-button>
                <b-button v-else @click="showModal(doacao)" variant="secondary">
                  Ver mais
                </b-button>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>

    <!-- Modal de Detalhes da Doação -->
    <b-modal v-model="showDetailsModal" title="Detalhes da Doação" hide-footer size="lg">
      <div v-if="expandedDoacao">
        <h5>ID: {{ expandedDoacao.id }}</h5>
        <p><strong>Descrição:</strong> {{ expandedDoacao.descricao }}</p>
        <p><strong>Data de Agendamento:</strong> {{ formatDate(expandedDoacao.dataAgendamento) }}</p>
        <p><strong>Situação:</strong> {{ expandedDoacao.situacao }}</p>
        <table class="table">
          <thead>
            <tr>
              <th>Produto ID</th>
              <th>Nome</th>
              <th>Descrição</th>
              <th>Quantidade</th>
              <th v-if="expandedDoacao.situacao === 'aguardando'">Selecionar</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="produto in expandedDoacao.produtos" :key="produto.produtoId">
              <td>{{ produto.produtoId }}</td>
              <td>{{ produto.nome }}</td>
              <td>{{ produto.descricao }}</td>
              <td>{{ produto.quantidade }}</td>
              <td v-if="expandedDoacao.situacao === 'aguardando'">
                <b-form-checkbox v-model="produto.selected"></b-form-checkbox>
              </td>
            </tr>
          </tbody>
        </table>
        <br>
        <div v-if="expandedDoacao.situacao === 'aguardando'" class="d-flex justify-content-end">
          <b-button @click="confirmDonationFromTable(expandedDoacao)" style="margin: 5px;" variant="success"
            class="mr-2" :disabled="!allProductsSelected">Confirmar</b-button>
          <b-button @click="cancelDonationFromTable(expandedDoacao)" style="margin: 5px;" variant="danger">Reprovar</b-button>
        </div>
        <div class="text-right mt-3">
          <b-button @click="showDetailsModal = false" variant="secondary">Fechar</b-button>
        </div>
      </div>
    </b-modal>
  </div>
</template>

<script>
import { apiClientClientes, apiClientHost } from "@/services/axios.js";

export default {
  name: "Donations",
  data() {
    return {
      step: 1, // Etapa do fluxo de doação
      donation: {
        rg: "",
        cpf: "",
        details: "",
      },
      colaboradorId: "",
      doacoes: [],
      searchQuery: "",
      expandedDoacao: null,
      showDetailsModal: false, // Para controlar a exibição do modal
    };
  },
  computed: {
    filteredDoacoes() {
      return this.doacoes.filter(doacao =>
        doacao.descricao.toLowerCase().includes(this.searchQuery.toLowerCase()) ||
        doacao.id.toString().includes(this.searchQuery)
      );
    },
    sortedFilteredDoacoes() {
      return this.sortDoacoes(this.filteredDoacoes);
    },
    allProductsSelected() {
      return this.expandedDoacao?.produtos.every(produto => produto.selected) || false;
    }
  },
  methods: {
    handleInput() {
      // Habilitar o botão de verificação se qualquer um dos campos (RG ou CPF) estiver preenchido
      if (this.donation.rg || this.donation.cpf) {
        this.step = 1;
      }
    },
    handleDonation() {
      if (this.step === 1) {
        this.verifyAppointment();
      }
    },
    verifyAppointment() {
      if (this.donation.cpf) {
        this.verifyByCPF();
      } else if (this.donation.rg) {
        this.verifyByRG();
      }
    },
    verifyByCPF() {
      apiClientClientes.post('/clientes/cpf', { cpf: this.donation.cpf })
        .then(response => {
          if (response.data.clienteFound) {
            this.step = 2;
          } else {
            alert("CPF não encontrado ou agendamento não feito.");
          }
        })
        .catch(error => {
          console.error("Erro ao verificar CPF:", error);
        });
    },
    verifyByRG() {
      apiClientClientes.post('/clientes/rg', { rg: this.donation.rg })
        .then(response => {
          if (response.data.clienteFound) {
            this.step = 2;
          } else {
            alert("RG não encontrado ou agendamento não feito.");
          }
        })
        .catch(error => {
          console.error("Erro ao verificar RG:", error);
        });
    },
    confirmDonation() {
      this.step = 3;
    },
    finalizeDonation() {
      apiClientClientes.post("/api/donations", this.donation)
        .then(() => {
          alert("Doação concluída com sucesso!");
          this.resetForm();
        })
        .catch(error => {
          console.error("Erro ao registrar doação:", error);
        });
    },
    resetForm() {
      this.step = 1;
      this.donation = {
        rg: "",
        cpf: "",
        details: "",
      };
    },
    fetchDoacoesByColaborador() {
      apiClientHost.get(`/doacoes/colaborador/${this.colaboradorId}`)
        .then(response => {
          this.doacoes = response.data;
        })
        .catch(error => {
          console.error("Erro ao buscar doações do colaborador:", error);
        });
    },
    showModal(doacao) {
      this.expandedDoacao = null;
      this.showDetailsModal = true;
      apiClientHost.get(`/produtosDoacao/id?id=${doacao.id}`)
        .then(response => {
          this.expandedDoacao = {
            ...doacao,
            produtos: response.data.map(produto => ({ ...produto, selected: false })) || []
          };
        })
        .catch(error => {
          console.error("Erro ao buscar produtos da doação:", error);
          this.expandedDoacao = {
            ...doacao,
            produtos: []
          };
        });
    },
    confirmDonationFromTable(doacao) {
      apiClientHost.post(`/doacoes/aprovar/${doacao.id}`)
        .then(() => {
          alert(`Doação ${doacao.id} confirmada!`);
          this.fetchDoacoesByColaborador(); // Atualiza a lista de doações
          this.showDetailsModal = false; // Fecha o modal
        })
        .catch(error => {
          console.error("Erro ao confirmar doação:", error);
        });
    },
    cancelDonationFromTable(doacao) {
      apiClientHost.post(`/doacoes/reprovar/${doacao.id}`)
        .then(() => {
          alert(`Doação ${doacao.id} reprovada!`);
          this.fetchDoacoesByColaborador(); // Atualiza a lista de doações
          this.showDetailsModal = false; // Fecha o modal
        })
        .catch(error => {
          console.error("Erro ao cancelar doação:", error);
        });
    },
    formatDate(date) {
      return new Date(date).toLocaleDateString();
    },
    sortDoacoes(doacoes) {
      return doacoes.sort((a, b) => {
        if (a.situacao === 'aguardando' && b.situacao !== 'aguardando') {
          return -1;
        }
        if (a.situacao !== 'aguardando' && b.situacao === 'aguardando') {
          return 1;
        }
        return 0;
      });
    }
  }
};
</script>

<style scoped>
.container {
  padding: 20px;
}

.text-right {
  text-align: right;
}

.row {
  margin-bottom: 1.5rem !important;
  margin-right: 60px !important;
  margin-left: 20px !important;
}
</style>
