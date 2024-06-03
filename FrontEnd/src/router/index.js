import { createRouter, createWebHistory } from "vue-router";
import Dashboard from "@/views/Dashboard.vue";
import Tables from "@/views/Tables.vue";
import Billing from "@/views/Billing.vue";
import VirtualReality from "@/views/VirtualReality.vue";
import Profile from "@/views/Profile.vue";
import Rtl from "@/views/Rtl.vue";
import SignIn from "@/views/SignIn.vue";
import store from "@/store/index.js"; // Certifique-se de importar o store Vuex

const routes = [
  {
    path: "/",
    name: "/",
    redirect: "/Inicio",
  },
  {
    path: "/Inicio",
    name: "Inicio",
    component: Dashboard,
    meta: { requiresAuth: true },
  },
  {
    path: "/Funcionarios",
    name: "Funcionarios",
    component: Tables,
    meta: { requiresAuth: true },
  },
  {
    path: "/Relatorios",
    name: "Relatorios",
    component: Billing,
    meta: { requiresAuth: true },
  },
  {
    path: "/virtual-reality",
    name: "Virtual Reality",
    component: VirtualReality,
    meta: { requiresAuth: true },
  },
  {
    path: "/profile",
    name: "Profile",
    component: Profile,
    meta: { requiresAuth: true },
  },
  {
    path: "/rtl-page",
    name: "Rtl",
    component: Rtl,
    meta: { requiresAuth: true },
  },
  {
    path: "/sign-in",
    name: "Sign In",
    component: SignIn,
  },

];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
  linkActiveClass: "active",
});

// Navigation guard
router.beforeEach((to, from, next) => {
  if (to.matched.some(record => record.meta.requiresAuth)) {
    if (!store.getters.isAuthenticated) {
      next({
        path: '/sign-in',
        query: { redirect: to.fullPath }
      });
    } else {
      next();
    }
  } else {
    next();
  }
});

export default router;
