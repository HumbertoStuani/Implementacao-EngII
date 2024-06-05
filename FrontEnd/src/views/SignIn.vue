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
           <!-- <div class="col-md-6">
              <div
                class="top-0 oblique position-absolute h-100 d-md-block d-none me-n8"
              >
                <div
                  class="bg-cover oblique-image position-absolute fixed-top ms-auto h-100 z-index-0 ms-n6"
                  :style="{
                    backgroundImage:
                      'url(' +
                      require('@/assets/img/curved-images/curved9.jpg') +
                      ')',
                  }"
                ></div>
              </div>
            </div>-->
          </div>
        </div>
      </div>
    </section>
  </main>
</template>

<script>
import Navbar from "@/examples/PageLayout/Navbar.vue";
import SoftInput from "@/components/SoftInput.vue";
import SoftButton from "@/components/SoftButton.vue";
import { mapActions, mapGetters } from "vuex";

export default {
  name: "SignIn",
  components: {
    Navbar,
    SoftInput,
    SoftButton,
  },
  data() {
    return {
      userLogin: '',
      password: '',
    };
  },
  created() {
    this.$store.commit('toggleEveryDisplay');
    this.$store.commit('toggleHideConfig');
    document.body.classList.remove("bg-gray-100");
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
