<template>
  <!-- Navbar -->
  <nav
    class="top-0 navbar navbar-expand-lg position-absolute z-index-3"
    :class="isBlur ? isBlur : 'shadow-none my-2 navbar-transparent w-50'"
  >
    <div class="container d-flex justify-content-center">
      <router-link
        class="navbar-brand font-weight-bolder"
        to="/"
        v-bind="$attrs">
      Sociedade Bernadense de Apoio Aos Portadores de Câncer
      </router-link>
    </div>
  </nav>
  <!-- End Navbar -->
</template>

<script>
export default {
  name: "navbar",
  props: {
    btnBackground: String,
    isBlur: String,
    darkMode: {
      type: Boolean,
      default: false,
    },
  },
};
</script>
