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

    <!-- Modal para mostrar as doações -->
    <b-modal v-model="showDoacoesModal" title="Doações do Colaborador" hide-footer size="xl">
      <b-table :items="filteredDoacoes" :fields="doacoesFields">
        <template #cell(actions)="row">
          <b-button @click="confirmDonationFromModal(row.item)" variant="success">Confirmar</b-button>
        </template>
      </b-table>
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
      showDoacoesModal: false,
      doacoes: [],
      doacoesFields: [
        { key: "id", label: "ID" },
        { key: "descricao", label: "Descrição" },
        { key: "dataAgendamento", label: "Data de Agendamento" },
        { key: "situacao", label: "Situação" },
        { key: "actions", label: "Ações" },
      ],
    };
  },
  computed: {
    filteredDoacoes() {
      return this.doacoes.filter(doacao => doacao.situacao === "aguardando");
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
          this.showDoacoesModal = true;
        })
        .catch(error => {
          console.error("Erro ao buscar doações do colaborador:", error);
        });
    },
    confirmDonationFromModal(doacao) {
      apiClientHost.post(`/doacoes/aprovar/${doacao.id}`)
        .then(() => {
          alert(`Doação ${doacao.id} confirmada!`);
          this.fetchDoacoesByColaborador(); // Atualiza a lista de doações no modal
        })
        .catch(error => {
          console.error("Erro ao confirmar doação:", error);
        });
    }
  },
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
