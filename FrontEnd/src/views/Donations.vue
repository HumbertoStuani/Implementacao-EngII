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
              <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">Data de Agendamento</th>
              <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">Situação</th>
              <th class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">Ações</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="doacao in filteredDoacoes" :key="doacao.id">
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
                <b-button @click="expandDetails(doacao)" variant="secondary">Ver mais</b-button>
              </td>
            </tr>
            <tr v-if="expandedDoacao && expandedDoacao.id === doacao.id">
              <td colspan="5">
                <ul>
                  <li v-for="produto in doacao.produtos" :key="produto.produtoId">
                    {{ produto.nome }} - {{ produto.quantidade }}
                  </li>
                </ul>
                <div v-if="doacao.situacao === 'aguardando'">
                  <b-button @click="confirmDonationFromTable(doacao)" variant="success">Confirmar</b-button>
                  <b-button @click="cancelDonationFromTable(doacao)" variant="danger">Cancelar</b-button>
                </div>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
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
    };
  },
  computed: {
    filteredDoacoes() {
      return this.doacoes.filter(doacao => 
        doacao.descricao.toLowerCase().includes(this.searchQuery.toLowerCase()) || 
        doacao.id.toString().includes(this.searchQuery)
      );
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
    expandDetails(doacao) {
      // Verifica se a doação já está expandida ou não
      if (this.expandedDoacao && this.expandedDoacao.id === doacao.id) {
        this.expandedDoacao = null; // Recolhe a doação se já estiver expandida
      } else {
        // Chama a API de produtos para a doação específica
        apiClientHost.get(`/produtosDoacao/id?id=${doacao.id}`)
          .then(response => {
            // Define a lista de produtos para a doação expandida
            this.expandedDoacao = {
              ...doacao,
              produtos: response.data
            };
          })
          .catch(error => {
            console.error("Erro ao buscar produtos da doação:", error);
          });
      }
    },
    confirmDonationFromTable(doacao) {
      apiClientHost.post(`/doacoes/aprovar/${doacao.id}`)
        .then(() => {
          alert(`Doação ${doacao.id} confirmada!`);
          this.fetchDoacoesByColaborador(); // Atualiza a lista de doações
        })
        .catch(error => {
          console.error("Erro ao confirmar doação:", error);
        });
    },
    cancelDonationFromTable(doacao) {
      apiClientHost.post(`/doacoes/cancelar/${doacao.id}`)
        .then(() => {
          alert(`Doação ${doacao.id} cancelada!`);
          this.fetchDoacoesByColaborador(); // Atualiza a lista de doações
        })
        .catch(error => {
          console.error("Erro ao cancelar doação:", error);
        });
    },
    formatDate(date) {
      return new Date(date).toLocaleDateString();
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
