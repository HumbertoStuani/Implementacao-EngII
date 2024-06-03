<template>
  <div
    class="w-auto h-auto collapse navbar-collapse max-height-vh-100 h-100"
    id="sidenav-collapse-main"
  >
    <ul class="navbar-nav">
      <li class="nav-item">
        <sidenav-collapse navText="Inicio" :to="{ name: 'Inicio' }">
          <template #icon>
            <shop />
          </template>
        </sidenav-collapse>
      </li>
      <li class="nav-item">
        <sidenav-collapse navText="Funcionarios" :to="{ name: 'Funcionarios' }">
          <template #icon>
            <office />
          </template>
        </sidenav-collapse>
      </li>
      <li class="nav-item">
        <sidenav-collapse navText="Relatorios" :to="{ name: 'Relatorios' }">
          <template #icon>
            <credit-card />
          </template>
        </sidenav-collapse>
      </li>
    </ul>
  </div>
</template>

<script>
import SidenavCollapse from "./SidenavCollapse.vue";
import Shop from "../../components/Icon/Shop.vue";
import Office from "../../components/Icon/Office.vue";
import CreditCard from "../../components/Icon/CreditCard.vue";

export default {
  name: "SidenavList",
  props: {
    cardBg: String,
  },
  data() {
    return {
      title: "SAPC",
      controls: "dashboardsExamples",
      isActive: "active",
    };
  },
  components: {
    SidenavCollapse,
    Shop,
    Office,
    CreditCard,

  },
  methods: {
    getRoute() {
      const routeArr = this.$route.path.split("/");
      return routeArr[1];
    },
  },
};
</script>

<style>
.navbar-vertical .navbar-nav > .nav-item .nav-link.active .icon {
    background-image: linear-gradient(310deg, #2158f0 0%, #0d67ee 100%) !important;
}</style>