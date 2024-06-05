<template>
  <br>
  <div class="card mb-4">
    <div class="card-header pb-0 d-flex justify-content-between align-items-center">
      <h6>Clientes</h6>
      <button class="btn btn-primary" @click="openModal(null)">Novo Cadastro</button>
    </div>
    <div class="card-body px-0 pt-0 pb-2">
      <div class="table-responsive p-0">
        <div class="p-3">
          <b-row>
            <b-col md="6" lg="4">
              <b-form-input v-model="searchQuery" placeholder="Pesquisar por nome ou telefone"></b-form-input>
            </b-col>
          </b-row>
        </div>
        <table class="table align-items-center mb-0">
          <thead>
            <tr>
              <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">Nome</th>
              <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">Telefone</th>
              <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">Sexo</th>
              <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">Endereço</th>
              <th class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">Status</th>
              <th class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">Data de
                Cadastro</th>
              <th class="text-secondary opacity-7"></th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="cliente in filteredClientes" :key="cliente.id">
              <td>
                <div class="d-flex px-2 py-1">
                  <div class="d-flex flex-column justify-content-center">
                    <h6 class="mb-0 text-sm">{{ cliente.nome }}</h6>
                  </div>
                </div>
              </td>
              <td>
                <p class="text-xs text-secondary mb-0">{{ cliente.telefone }}</p>
              </td>
              <td>
                <p class="text-xs text-secondary mb-0">{{ cliente.sexo }}</p>
              </td>
              <td>
                <p class="text-xs text-secondary mb-0">{{ cliente.endereco }}</p>
              </td>
              <td class="align-middle text-center text-sm">
                <soft-badge :color="cliente.active ? 'success' : 'secondary'" variant="gradient" size="sm">
                  {{ cliente.active ? 'Ativo' : 'Inativo' }}
                </soft-badge>
              </td>
              <td class="align-middle text-center">
                <span class="text-secondary text-xs font-weight-bold">{{ formatDate(cliente.dataCadastro) }}</span>
              </td>
              <td class="align-middle">
                <a href="javascript:;" @click="openModal(cliente)" class="text-secondary font-weight-bold text-xs"
                  data-toggle="tooltip" data-original-title="Edit user">
                  <i class="fas fa-pencil-alt"></i>
                </a>
                <a href="javascript:;" @click="viewDetails(cliente)"
                  class="text-secondary font-weight-bold text-xs ms-2" data-toggle="tooltip"
                  data-original-title="View details">
                  <i class="fas fa-eye"></i>
                </a>
                <a href="javascript:;" @click="toggleActive(cliente)"
                  class="text-secondary font-weight-bold text-xs ms-2" data-toggle="tooltip"
                  :data-original-title="cliente.active ? 'Desativar' : 'Ativar'">
                  <i :class="cliente.active ? 'fas fa-toggle-off' : 'fas fa-toggle-on'"></i>
                </a>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>

    <!-- Modal -->
    <b-modal v-model="showModal" :title="modalTitle" @hide="resetModal" hide-footer>
      <div>
        <b-form @submit.prevent="submitForm">
          <b-row>
            <b-col md="6">
              <b-form-group label="Nome" label-for="input-name">
                <b-form-input id="input-name" v-model="form.nome" required></b-form-input>
              </b-form-group>
            </b-col>
            <b-col md="6">
              <b-form-group label="Telefone" label-for="input-telefone">
                <b-form-input id="input-telefone" v-model="form.telefone" required></b-form-input>
              </b-form-group>
            </b-col>
          </b-row>
          <b-row>
            <b-col md="6">
              <b-form-group label="Sexo" label-for="input-sexo">
                <b-form-select id="input-sexo" v-model="form.sexo"
                  :options="[{ text: 'Masculino', value: 'Masculino' }, { text: 'Feminino', value: 'Feminino' }]"
                  required></b-form-select>
              </b-form-group>
            </b-col>
            <b-col md="6">
              <b-form-group label="RG" label-for="input-rg">
                <b-form-input id="input-rg" v-model="form.rg" required></b-form-input>
              </b-form-group>
            </b-col>
          </b-row>
          <b-row>
            <b-col md="6">
              <b-form-group label="CPF" label-for="input-cpf">
                <b-form-input id="input-cpf" v-model="form.cpf" required></b-form-input>
              </b-form-group>
            </b-col>
            <b-col md="6">
              <b-form-group label="Endereço" label-for="input-endereco">
                <b-form-input id="input-endereco" v-model="form.endereco" required></b-form-input>
              </b-form-group>
            </b-col>
          </b-row>
          <b-row>
            <b-col md="6">
              <b-form-group label="Cidade" label-for="input-cidade">
                <b-form-input id="input-cidade" v-model="form.cidade" required></b-form-input>
              </b-form-group>
            </b-col>
            <b-col md="6">
              <b-form-group label="Bairro" label-for="input-bairro">
                <b-form-input id="input-bairro" v-model="form.bairro" required></b-form-input>
              </b-form-group>
            </b-col>
          </b-row>
          <b-row>
            <b-col md="6">
              <b-form-group label="UF" label-for="input-uf">
                <b-form-input id="input-uf" v-model="form.uf" required></b-form-input>
              </b-form-group>
            </b-col>
            <b-col md="6">
              <b-form-group label="Data de Nascimento" label-for="input-dataNascimento">
                <b-form-input type="date" id="input-dataNascimento" v-model="form.dataNascimento"
                  required></b-form-input>
              </b-form-group>
            </b-col>
          </b-row>
          <div class="d-flex justify-content-end">
            <b-button type="submit" variant="primary">Salvar</b-button>
            <b-button variant="secondary" @click="showModal = false" class="ms-2">Cancelar</b-button>
          </div>
        </b-form>
      </div>
    </b-modal>

    <!-- Modal de Visualização -->
    <b-modal v-model="showViewModal" :title="'Detalhes do Cliente'" hide-footer>
      <div>
        <b-row>
          <b-col md="6">
            <b-form-group label="Nome">
              <b-form-input v-model="viewForm.nome" readonly></b-form-input>
            </b-form-group>
          </b-col>
          <b-col md="6">
            <b-form-group label="Telefone">
              <b-form-input v-model="viewForm.telefone" readonly></b-form-input>
            </b-form-group>
          </b-col>
        </b-row>
        <b-row>
          <b-col md="6">
            <b-form-group label="Sexo">
              <b-form-input v-model="viewForm.sexo" readonly></b-form-input>
            </b-form-group>
          </b-col>
          <b-col md="6">
            <b-form-group label="RG">
              <b-form-input v-model="viewForm.rg" readonly></b-form-input>
            </b-form-group>
          </b-col>
        </b-row>
        <b-row>
          <b-col md="6">
            <b-form-group label="CPF">
              <b-form-input v-model="viewForm.cpf" readonly></b-form-input>
            </b-form-group>
          </b-col>
          <b-col md="6">
            <b-form-group label="Endereço">
              <b-form-input v-model="viewForm.endereco" readonly></b-form-input>
            </b-form-group>
          </b-col>
        </b-row>
        <b-row>
          <b-col md="6">
            <b-form-group label="Cidade">
              <b-form-input v-model="viewForm.cidade" readonly></b-form-input>
            </b-form-group>
          </b-col>
          <b-col md="6">
            <b-form-group label="Bairro">
              <b-form-input v-model="viewForm.bairro" readonly></b-form-input>
            </b-form-group>
          </b-col>
        </b-row>
        <b-row>
          <b-col md="6">
            <b-form-group label="UF">
              <b-form-input v-model="viewForm.uf" readonly></b-form-input>
            </b-form-group>
          </b-col>
          <b-col md="6">
            <b-form-group label="Data de Nascimento">
              <b-form-input v-model="viewForm.dataNascimento" readonly></b-form-input>
            </b-form-group>
          </b-col>
        </b-row>
        <div class="d-flex justify-content-end">
          <b-button variant="secondary" @click="showViewModal = false">Fechar</b-button>
        </div>
      </div>
    </b-modal>
  </div>
</template>

<script>
import { apiClientClientes } from '@/services/axios'; // Corrigido para importar a instância correta
import SoftBadge from "@/components/SoftBadge.vue";
import { BModal, BButton, BForm, BFormGroup, BFormInput, BFormSelect, BRow, BCol } from 'bootstrap-vue-3';

export default {
  name: "Clientes",
  components: {
    SoftBadge,
    BModal,
    BButton,
    BForm,
    BFormGroup,
    BFormInput,
    BFormSelect,
    BRow,
    BCol
  },
  data() {
    return {
      clientes: [],
      showModal: false,
      showViewModal: false,
      modalTitle: 'Novo Cadastro',
      searchQuery: '',
      form: {
        id: null,
        nome: '',
        telefone: '',
        sexo: 'Masculino',
        rg: '',
        cpf: '',
        endereco: '',
        cidade: '',
        bairro: '',
        uf: '',
        dataNascimento: '',
        dataCadastro: '',
        active: true,
      },
      viewForm: {
        nome: '',
        telefone: '',
        sexo: '',
        rg: '',
        cpf: '',
        endereco: '',
        cidade: '',
        bairro: '',
        uf: '',
        dataNascimento: '',
        dataCadastro: '',
        active: true,
      }
    };
  },
  computed: {
    filteredClientes() {
      return this.clientes.filter(cliente => {
        const query = this.searchQuery.toLowerCase();
        return (
          cliente.nome.toLowerCase().includes(query) ||
          cliente.telefone.toLowerCase().includes(query)
        );
      });
    }
  },
  methods: {
    fetchClientes() {
      apiClientClientes.get('/all')
        .then(response => {
          this.clientes = response.data;
        })
        .catch(error => {
          console.error("Houve um erro ao buscar os clientes!", error);
        });
    },
    openModal(cliente) {
      if (cliente) {
        this.modalTitle = 'Editar Cliente';
        this.form = { ...cliente };
        this.form.dataNascimento = cliente.dataNascimento ? cliente.dataNascimento.split('T')[0] : '';
        this.isEditing = true;
      } else {
        this.modalTitle = 'Novo Cadastro';
        this.resetModal();
        this.isEditing = false;
      }
      this.showModal = true;
    },
    viewDetails(cliente) {
      this.viewForm = { ...cliente };
      this.viewForm.dataNascimento = cliente.dataNascimento ? cliente.dataNascimento.split('T')[0] : '';
      this.showViewModal = true;
    },
    resetModal() {
      this.form = {
        id: null,
        nome: '',
        telefone: '',
        sexo: 'Masculino',
        rg: '',
        cpf: '',
        endereco: '',
        cidade: '',
        bairro: '',
        uf: '',
        dataNascimento: '',
        dataCadastro: '',
        active: true,
      };
    },
    formatDateToPayload(date) {
      if (!date) return '';
      return new Date(date).toISOString().slice(0, 19).replace('T', 'T');
    },
    submitForm() {
      const payload = { ...this.form };
      payload.dataNascimento = this.formatDateToPayload(this.form.dataNascimento);
      payload.dataCadastro = this.formatDateToPayload(new Date());
      if (this.form.id) {
        apiClientClientes.put(`/alterar/${this.form.id}`, payload)
          .then(() => {
            this.fetchClientes();
            this.showModal = false;
          })
          .catch(error => {
            console.error("Houve um erro ao atualizar o cliente!", error);
          });
      } else {
        apiClientClientes.post('/cadastrar', payload)
          .then(() => {
            this.fetchClientes();
            this.showModal = false;
          })
          .catch(error => {
            console.error("Houve um erro ao criar o cliente!", error);
          });
      }
    },
    toggleActive(cliente) {
      const action = cliente.active ? 'desativar' : 'ativar';
      apiClientClientes.put(`/${action}/${cliente.id}`)
        .then(() => {
          this.fetchClientes();
        })
        .catch(error => {
          console.error(`Houve um erro ao ${action} o cliente!`, error);
        });
    },
    formatDate(date) {
      if (!date) return '';
      const options = { year: 'numeric', month: '2-digit', day: '2-digit', hour: '2-digit', minute: '2-digit', second: '2-digit' };
      return new Date(date).toLocaleDateString('pt-BR', options).replace(',', '');
    }
  },
  mounted() {
    this.fetchClientes();
  }
};
</script>

<style scoped>
.table-responsive {
  padding: 1rem;
}

.mb-4 {
  margin-left: 25px;
  margin-right: 80px;
}

@media (min-width: 576px) {
  .modal-dialog {
    max-width: 800px !important;
    margin: 1.75rem auto;
  }
}

.form-control {
  font-size: 14px;
}
</style>