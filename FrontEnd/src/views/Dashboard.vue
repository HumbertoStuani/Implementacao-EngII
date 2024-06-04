<template>
  <div class="py-4 container-fluid">
    <div class="row">
      <div class="col-lg-3 col-md-6 mb-4" v-for="section in sections" :key="section.name">
        <button class="btn btn-modern w-100" @click="navigateTo(section.route)">
          <i :class="section.icon"></i>
          <span class="btn-text">{{ section.name }}</span>
        </button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "dashboard-default",
  data() {
    return {
      sections: [
        { name: 'Doações', route: 'doacoes', icon: 'fas fa-donate' },
        { name: 'Pacientes', route: 'pacientes', icon: 'fas fa-user-injured' },
        { name: 'Caixa', route: 'caixa', icon: 'fas fa-cash-register' },
        { name: 'Eventos', route: 'eventos', icon: 'fas fa-calendar-alt' },
        { name: 'Relatórios', route: 'relatorios', icon: 'fas fa-chart-line' },
        { name: 'Configurações', route: 'configuracoes', icon: 'fas fa-cog' },
        { name: 'Perfil', route: 'perfil', icon: 'fas fa-user-circle' },
        { name: 'Ajuda', route: 'ajuda', icon: 'fas fa-question-circle' }
      ]
    };
  },
  methods: {
    navigateTo(section) {
      console.log(`Navegar para ${section}`);
      this.$router.push({ name: section });
    },
  },
};
</script>

<style scoped>
.btn-modern {
  font-size: 1rem;
  padding: 0.75rem 1.5rem;
  border-radius: 10px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  transition: all 0.3s ease;
  background-color: #ffffff;
  border: 1px solid #2c8ef7;
  color: #2c8ef7;
  display: flex;
  align-items: center;
  justify-content: center;
  margin-right: 10px; /* Espaço entre botões */
}

.btn-modern i {
  margin-right: 8px;
  font-size: 1.25rem;
}

.btn-modern .btn-text {
  margin-left: 4px;
}

.btn-modern:hover {
  background-color: #2c8ef7;
  color: white;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.15);
  transform: translateY(-1px);
}

.col-lg-3, .col-md-6 {
  padding-right: 15px; 
}

.container-fluid {
  padding-left: 15px; 
  padding-right: 80px; 
}
</style>

