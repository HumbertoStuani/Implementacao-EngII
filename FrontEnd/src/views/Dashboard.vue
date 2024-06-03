<template>
  <div class="py-4 container-fluid">
    <div class="row">
      <div class="col-lg-3 col-md-6 mb-4">
        <button class="btn  btn-modern w-100" @click="navigateTo('doacoes')">
          <i class="fas fa-donate"></i>
          <span class="btn-text">Doações</span>
        </button>
      </div>
      <div class="col-lg-3 col-md-6 mb-4">
        <button class="btn  btn-modern w-100" @click="navigateTo('pacientes')">
          <i class="fas fa-user-injured"></i>
          <span class="btn-text">Pacientes</span>
        </button>
      </div>
      <div class="col-lg-3 col-md-6 mb-4">
        <button class="btn  btn-modern w-100" @click="navigateTo('caixa')">
          <i class="fas fa-cash-register"></i>
          <span class="btn-text">Caixa</span>
        </button>
      </div>
      <div class="col-lg-3 col-md-6 mb-4">
        <button class="btn  btn-modern w-100" @click="navigateTo('eventos')">
          <i class="fas fa-calendar-alt"></i>
          <span class="btn-text">Eventos</span>
        </button>
      </div>
    </div>
    <div class="row">
      <div class="col-lg-3 col-md-6 mb-4">
        <button class="btn  btn-modern w-100" @click="navigateTo('doacoes')">
          <i class="fas fa-donate"></i>
          <span class="btn-text">Doações</span>
        </button>
      </div>
      <div class="col-lg-3 col-md-6 mb-4">
        <button class="btn  btn-modern w-100" @click="navigateTo('pacientes')">
          <i class="fas fa-user-injured"></i>
          <span class="btn-text">Pacientes</span>
        </button>
      </div>
      <div class="col-lg-3 col-md-6 mb-4">
        <button class="btn  btn-modern w-100" @click="navigateTo('caixa')">
          <i class="fas fa-cash-register"></i>
          <span class="btn-text">Caixa</span>
        </button>
      </div>
      <div class="col-lg-3 col-md-6 mb-4">
        <button class="btn  btn-modern w-100" @click="navigateTo('eventos')">
          <i class="fas fa-calendar-alt"></i>
          <span class="btn-text">Eventos</span>
        </button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "dashboard-default",
  methods: {
    navigateTo(section) {
      // Adicione a lógica de navegação para cada seção aqui
      // Por exemplo, redirecionar para uma rota específica ou carregar um componente
      console.log(`Navegar para ${section}`);
      // Exemplo de redirecionamento de rota:
      // this.$router.push({ name: section });
    },
  },
};
</script>

<style scoped>
.btn-modern {
  font-size: 1.25rem;
  padding: 1rem 2rem;
  border-radius: 25px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  transition: all 0.3s ease;
  background-color: transparent;
  border: 1px solid #2c8ef7fd !important;
  color: #2c8ef7fd;
  display: flex;
  align-items: center;
  justify-content: center;
}

.btn-modern i {
  margin-right: 8px;
  font-size: 1.5rem;
}

.btn-modern .btn-text {
  margin-left: 8px;
}

.btn-modern:hover {
  background-color: #2c8ef7fd;
  color: white;
  box-shadow: 0 6px 8px rgba(0, 0, 0, 0.15);
  transform: translateY(-2px);


}
</style>
