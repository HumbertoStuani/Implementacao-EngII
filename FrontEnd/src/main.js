import { createApp } from "vue";
import App from "./App.vue";
import store from "./store";
import router from "./router";
import "./assets/css/nucleo-icons.css";
import "./assets/css/nucleo-svg.css";
import SoftUIDashboard from "./soft-ui-dashboard";
import { BootstrapVue3 } from 'bootstrap-vue-3';
import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap-vue-3/dist/bootstrap-vue-3.css';

// Cria a instância do aplicativo Vue
const appInstance = createApp(App);

// Usa os plugins necessários
appInstance.use(store);
appInstance.use(router);
appInstance.use(SoftUIDashboard);

// Usa BootstrapVue3
appInstance.use(BootstrapVue3);

// Monta o aplicativo na div com id="app"
appInstance.mount("#app");
