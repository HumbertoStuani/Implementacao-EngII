<template>
  <nav
    class="shadow-none navbar navbar-main navbar-expand-lg border-radius-xl"
    v-bind="$attrs"
    id="navbarBlur"
    data-scroll="true"
  >
    <div class="px-3 py-1 container-fluid">
      <breadcrumbs :currentPage="currentRouteName" :textWhite="textWhite" />
      <div
        class="mt-2 collapse navbar-collapse mt-sm-0 me-md-0 me-sm-4"
        :class="this.$store.state.isRTL ? 'px-0' : 'me-sm-4'"
        id="navbar"
      >
        <ul class="navbar-nav ms-auto justify-content-end align-items-center">
          <li v-if="isAuthenticated" class="nav-item d-flex align-items-center">
            <i class="fa fa-user me-2"></i>
            <span class="nav-link font-weight-bold">{{ userName }}</span>
            <button @click="handleLogout" class="btn btn-outline-danger btn-sm ms-2">Sair</button>
          </li>
          <li v-else class="nav-item d-flex align-items-center">
            <router-link
              :to="{ name: 'Sign In' }"
              class="px-0 nav-link font-weight-bold"
              :class="textWhite ? textWhite : 'text-body'"
            >
              <i
                class="fa fa-user"
                :class="this.$store.state.isRTL ? 'ms-sm-2' : 'me-sm-1'"
              ></i>
              <span v-if="this.$store.state.isRTL" class="d-sm-inline d-none">يسجل دخول</span>
              <span v-else class="d-sm-inline d-none">Sign In</span>
            </router-link>
          </li>
          <li class="nav-item d-xl-none ps-3 d-flex align-items-center">
            <a
              href="#"
              @click="toggleSidebar"
              class="p-0 nav-link text-body"
              id="iconNavbarSidenav"
            >
              <div class="sidenav-toggler-inner">
                <i class="sidenav-toggler-line"></i>
                <i class="sidenav-toggler-line"></i>
                <i class="sidenav-toggler-line"></i>
              </div>
            </a>
          </li>
        </ul>
      </div>
    </div>
  </nav>
</template>

<script>
import Breadcrumbs from "../Breadcrumbs.vue";
import { mapMutations, mapActions, mapGetters } from "vuex";

export default {
  name: "navbar",
  data() {
    return {
      showMenu: false,
    };
  },
  props: ["minNav", "textWhite"],
  created() {
    this.minNav;
  },
  methods: {
    ...mapMutations(["navbarMinimize"]),
    ...mapActions(["toggleSidebarColor", "logout"]),
    toggleSidebar() {
      this.toggleSidebarColor("bg-white");
      this.navbarMinimize();
    },
    handleLogout() {
      this.logout();
      this.$router.push({ name: 'Sign In' });
    }
  },
  components: {
    Breadcrumbs,
  },
  computed: {
    ...mapGetters(['isAuthenticated', 'userName']),
    currentRouteName() {
      return this.$route.name;
    },
  },
  updated() {
    const navbar = document.getElementById("navbarBlur");
    window.addEventListener("scroll", () => {
      if (window.scrollY > 10 && this.$store.state.isNavFixed) {
        navbar.classList.add("blur");
        navbar.classList.add("position-sticky");
        navbar.classList.add("shadow-blur");
      } else {
        navbar.classList.remove("blur");
        navbar.classList.remove("position-sticky");
        navbar.classList.remove("shadow-blur");
      }
    });
  },
};
</script>

<style scoped>

.btn-outline-danger {
  border-color: #3711cd;
  color: #3549dc;
}

.btn-outline-danger:hover {
  background-color: #3711cd;
  color: white;
}

.navbar-nav {
  align-items: center;
}

.btn {
  margin-bottom: 0 !important;
}
</style>
