<template>
  <div class="card mb-4">
    <div class="card-header pb-0 d-flex justify-content-between align-items-center">
      <h6>Funcionários</h6>
      <button class="btn btn-primary" @click="openModal(null)">Novo Cadastro</button>
    </div>
    <div class="card-body px-0 pt-0 pb-2">
      <div class="table-responsive p-0">
        <table class="table align-items-center mb-0">
          <thead>
            <tr>
              <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">Funcionário</th>
              <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">Função</th>
              <th class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">Status</th>
              <th class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">Admissão</th>
              <th class="text-secondary opacity-7"></th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="funcionario in funcionarios" :key="funcionario.id">
              <td>
                <div class="d-flex px-2 py-1">
                  <div>
                    <soft-avatar :img="funcionario.img" size="sm" border-radius="lg" class="me-3" :alt="funcionario.name" />
                  </div>
                  <div class="d-flex flex-column justify-content-center">
                    <h6 class="mb-0 text-sm">{{ funcionario.name }}</h6>
                    <p class="text-xs text-secondary mb-0">{{ funcionario.email }}</p>
                  </div>
                </div>
              </td>
              <td>
                <p class="text-xs font-weight-bold mb-0">{{ funcionario.role }}</p>
                <p class="text-xs text-secondary mb-0">{{ funcionario.department }}</p>
              </td>
              <td class="align-middle text-center text-sm">
                <soft-badge :color="funcionario.status === 'Ativo' ? 'success' : 'secondary'" variant="gradient" size="sm">
                  {{ funcionario.status }}
                </soft-badge>
              </td>
              <td class="align-middle text-center">
                <span class="text-secondary text-xs font-weight-bold">{{ funcionario.hireDate }}</span>
              </td>
              <td class="align-middle">
                <a href="javascript:;" @click="openModal(funcionario)" class="text-secondary font-weight-bold text-xs" data-toggle="tooltip" data-original-title="Edit user">Editar</a>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>

    <!-- Modal -->
    <b-modal v-model="showModal" title="Editar Funcionário" @hide="resetModal">
      <div>
        <b-form @submit.prevent="submitForm">
          <b-form-group label="Nome" label-for="input-name">
            <b-form-input id="input-name" v-model="form.name" required></b-form-input>
          </b-form-group>
          <b-form-group label="Email" label-for="input-email">
            <b-form-input type="email" id="input-email" v-model="form.email" required></b-form-input>
          </b-form-group>
          <b-form-group label="Função" label-for="input-role">
            <b-form-input id="input-role" v-model="form.role" required></b-form-input>
          </b-form-group>
          <b-form-group label="Departamento" label-for="input-department">
            <b-form-input id="input-department" v-model="form.department" required></b-form-input>
          </b-form-group>
          <b-form-group label="Status" label-for="input-status">
            <b-form-select id="input-status" v-model="form.status" :options="['Ativo', 'Inativo']" required></b-form-select>
          </b-form-group>
          <b-form-group label="Data de Admissão" label-for="input-hireDate">
            <b-form-input type="date" id="input-hireDate" v-model="form.hireDate" required></b-form-input>
          </b-form-group>
          <b-button type="submit" variant="primary">Salvar</b-button>
        </b-form>
      </div>
    </b-modal>
  </div>
</template>

<script>
import axios from 'axios';
import SoftAvatar from "@/components/SoftAvatar.vue";
import SoftBadge from "@/components/SoftBadge.vue";
import { BModal, BButton, BForm, BFormGroup, BFormInput, BFormSelect } from 'bootstrap-vue-3';

export default {
  name: "AuthorsTable",
  components: {
    SoftAvatar,
    SoftBadge,
    BModal,
    BButton,
    BForm,
    BFormGroup,
    BFormInput,
    BFormSelect
  },
  data() {
    return {
      funcionarios: [
        {
          id: 1,
          name: 'João Silva',
          email: 'joao@exemplo.com',
          role: 'Gerente',
          department: 'Administração',
          status: 'Ativo',
          hireDate: '2020-01-15',
          img: 'https://via.placeholder.com/150'
        }
      ],
      showModal: false,
      form: {
        id: null,
        name: '',
        email: '',
        role: '',
        department: '',
        status: 'Ativo',
        hireDate: '',
        img: ''
      }
    };
  },
  methods: {
    fetchFuncionarios() {
      axios.get('https://api.exemplo.com/funcionarios')
        .then(response => {
          this.funcionarios = response.data;
        })
        .catch(error => {
          console.error("Houve um erro ao buscar os funcionários!", error);
        });
    },
    openModal(funcionario) {
      if (funcionario) {
        this.form = { ...funcionario };
      } else {
        this.resetModal();
      }
      this.showModal = true;
    },
    resetModal() {
      this.form = {
        id: null,
        name: '',
        email: '',
        role: '',
        department: '',
        status: 'Ativo',
        hireDate: '',
        img: ''
      };
    },
    submitForm() {
      if (this.form.id) {
        axios.put(`https://api.exemplo.com/funcionarios/${this.form.id}`, this.form)
          .then(() => {
            this.fetchFuncionarios();
            this.showModal = false;
          })
          .catch(error => {
            console.error("Houve um erro ao atualizar o funcionário!", error);
          });
      } else {
        axios.post('https://api.exemplo.com/funcionarios', this.form)
          .then(() => {
            this.fetchFuncionarios();
            this.showModal = false;
          })
          .catch(error => {
            console.error("Houve um erro ao criar o funcionário!", error);
          });
      }
    }
  }
};
</script>

<style scoped>
.table-responsive {
  padding: 1rem;
}
</style>
