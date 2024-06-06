<template>
  <div class="container top-0 position-sticky z-index-sticky">
    <div class="row">
      <div class="col-12">
        <navbar
          is-blur="blur blur-rounded my-3 py-2 start-0 end-0 mx-4 shadow"
          btn-background="bg-gradient-success"
          :dark-mode="true"
        />
      </div>
    </div>
  </div>
  <main class="mt-0 main-content main-content-bg">
    <section>
      <div class="page-header min-vh-75">
        <div class="container">
          <div class="row">
            <div class="mx-auto col-xl-4 col-lg-5 col-md-6 d-flex flex-column">
              <div class="mt-8 card card-plain">
                <div class="pb-0 card-header text-start">
                  <h3 class="font-weight-bolder text-blue-gradient">
                    Bem-vindo de volta
                  </h3>
                  <p class="mb-0">Digite seu login e senha para entrar</p>
                  <br>
                </div>
                <div class="card-body">
                  <form @submit.prevent="handleLogin" class="text-start">
                    <label>Login</label>
                    <soft-input
                      id="userLogin"
                      type="text"
                      placeholder="Login"
                      v-model="userLogin"
                      name="login"
                      autocomplete="username"
                      required
                    />
                    <label>Senha</label>
                    <soft-input
                      id="password"
                      type="password"
                      placeholder="Senha"
                      v-model="password"
                      name="password"
                      autocomplete="current-password"
                      required
                    />
                    <div class="text-center">
                      <soft-button
                        class="my-4 mb-2"
                        :class="['blue-gradient-button']"
                        full-width
                        type="submit"
                        >Entrar
                      </soft-button>
                    </div>
                  </form>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
  </main>

  <!-- Modal de Cadastro de Funcionário -->
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
            <b-form-group label="Cargo" label-for="input-cargo">
              <b-form-input id="input-cargo" v-model="form.cargo" required></b-form-input>
            </b-form-group>
          </b-col>
          <b-col md="6">
            <b-form-group label="Sexo" label-for="input-sexo">
              <b-form-select id="input-sexo" v-model="form.sexo" :options="[{ text: 'Masculino', value: 'Masculino' }, { text: 'Feminino', value: 'Feminino' }]" required></b-form-select>
            </b-form-group>
          </b-col>
        </b-row>
        <b-row>
          <b-col md="6">
            <b-form-group label="Login" label-for="input-login">
              <b-form-input id="input-login" v-model="form.login" required></b-form-input>
            </b-form-group>
          </b-col>
          <b-col md="6">
            <b-form-group label="Ativo" label-for="input-active">
              <b-form-select id="input-active" v-model="form.active" :options="[{ text: 'Ativo', value: true }, { text: 'Inativo', value: false }]" required></b-form-select>
            </b-form-group>
          </b-col>
        </b-row>
        <b-row>
          <b-col md="6">
            <b-form-group label="Função" label-for="input-role">
              <b-form-select id="input-role" v-model="form.role" :options="[{ text: 'Usuário', value: 'USER' }, { text: 'Administrador', value: 'ADMIN' }]" required></b-form-select>
            </b-form-group>
          </b-col>
          <b-col md="6">
            <b-form-group label="Data de Admissão" label-for="input-hireDate">
              <b-form-input type="date" id="input-hireDate" v-model="form.dataAdmissao" :readonly="isEditing" required></b-form-input>
            </b-form-group>
          </b-col>
        </b-row>
        <b-row>
          <b-col md="6">
            <b-form-group label="RG" label-for="input-rg">
              <b-form-input id="input-rg" v-model="form.rg" :readonly="isEditing" required></b-form-input>
            </b-form-group>
          </b-col>
          <b-col md="6">
            <b-form-group label="CPF" label-for="input-cpf">
              <b-form-input id="input-cpf" v-model="form.cpf" :readonly="isEditing" required></b-form-input>
            </b-form-group>
          </b-col>
        </b-row>
        <b-row>
          <b-col md="6">
            <b-form-group label="Endereço" label-for="input-endereco">
              <b-form-input id="input-endereco" v-model="form.endereco" required></b-form-input>
            </b-form-group>
          </b-col>
          <b-col md="6">
            <b-form-group label="Cidade" label-for="input-cidade">
              <b-form-input id="input-cidade" v-model="form.cidade" required></b-form-input>
            </b-form-group>
          </b-col>
        </b-row>
        <b-row>
          <b-col md="6">
            <b-form-group label="Bairro" label-for="input-bairro">
              <b-form-input id="input-bairro" v-model="form.bairro" required></b-form-input>
            </b-form-group>
          </b-col>
          <b-col md="6">
            <b-form-group label="UF" label-for="input-uf">
              <b-form-input id="input-uf" v-model="form.uf" required></b-form-input>
            </b-form-group>
          </b-col>
        </b-row>
        <b-row>
          <b-col md="6">
            <b-form-group label="Data de Nascimento" label-for="input-dataNascimento">
              <b-form-input type="date" id="input-dataNascimento" v-model="form.dataNascimento" :readonly="isEditing" required></b-form-input>
            </b-form-group>
          </b-col>
          <b-col md="6">
            <b-form-group label="Senha" label-for="input-password">
              <b-form-input type="password" id="input-password" v-model="form.password"></b-form-input>
            </b-form-group>
          </b-col>
        </b-row>
        <b-row v-if="!form.id">
          <b-col md="6">
            <b-form-group label="Confirmar Senha" label-for="input-confirmPassword">
              <b-form-input type="password" id="input-confirmPassword" v-model="form.confirmPassword"></b-form-input>
            </b-form-group>
          </b-col>
        </b-row>
        <b-row>
          <b-col md="6">
            <b-form-group label="Salário" label-for="input-salario">
              <b-form-input type="number" id="input-salario" v-model="form.salario" required></b-form-input>
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
</template>

<script>
import Navbar from "@/examples/PageLayout/Navbar.vue";
import SoftInput from "@/components/SoftInput.vue";
import SoftButton from "@/components/SoftButton.vue";
import axios from 'axios';
import { mapActions, mapGetters } from "vuex";
import { BModal, BButton, BForm, BFormGroup, BFormInput, BFormSelect, BRow, BCol } from 'bootstrap-vue-3';

export default {
  name: "SignIn",
  components: {
    Navbar,
    SoftInput,
    SoftButton,
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
      userLogin: '',
      password: '',
      showModal: false,
      modalTitle: 'Primeiro cadastro',
      form: {
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
        dataAdmissao: '',
        login: '',
        active: true,
        role: 'USER',
        password: '',
        confirmPassword: '',
        cargo: '',
        salario: 0,
      },
      isEditing: false,
    };
  },
  created() {
    this.$store.commit('toggleEveryDisplay');
    this.$store.commit('toggleHideConfig');
    document.body.classList.remove("bg-gray-100");
    this.checkAdmin();
  },
  beforeUnmount() {
    this.$store.commit('toggleEveryDisplay');
    this.$store.commit('toggleHideConfig');
    document.body.classList.add("bg-gray-100");
  },
  methods: {
    ...mapActions(["login"]),
    async handleLogin() {
      const credentials = {
        login: this.userLogin,
        password: this.password,
      };

      const isAuthenticated = await this.login(credentials);
      if (isAuthenticated) {
        this.$router.push({ name: 'Inicio' });
      } else {
        alert('Login ou senha inválidos. Tente novamente.');
      }
    },
    async checkAdmin() {
      try {
        const response = await axios.get('http://localhost:8081/auth/checkAdmin');
        if (response.data) {
          this.showModal = true;
        }
      } catch (error) {
        console.error('Erro ao verificar o admin:', error);
      }
    },
    resetModal() {
      this.form = {
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
        dataAdmissao: '',
        login: '',
        active: true,
        role: 'USER',
        password: '',
        confirmPassword: '',
        cargo: '',
        salario: 0,
      };
    },
    async submitForm() {
      const payload = { ...this.form };
      try {
        await axios.post('http://localhost:8081/auth/initialRegister', payload);
        this.showModal = false;
        this.$router.push({ name: 'Sign In' });
      } catch (error) {
        console.error('Erro ao cadastrar o funcionário:', error);
      }
    }
  },
  computed: {
    ...mapGetters(['isAuthenticated']),
  },
};
</script>

<style scoped>
.main-content-bg {
  background-color: #f8f9fa;
}
.text-blue-gradient {
  background: linear-gradient(90deg, #1e90ff, #00bfff);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  background-clip: text;
}
.blue-gradient-button {
  background: linear-gradient(90deg, #1e90ff, #00bfff);
  border: none;
  color: white; /* Cor do texto do botão */
}
</style>
