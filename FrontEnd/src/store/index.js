// store/index.js
import { createStore } from "vuex";
import bootstrap from "bootstrap/dist/js/bootstrap.min.js";

export default createStore({
  state: {
    hideConfigButton: false,
    isPinned: true,
    showConfig: false,
    isTransparent: "",
    isRTL: false,
    color: "",
    isNavFixed: false,
    isAbsolute: false,
    showNavs: true,
    showSidenav: true,
    showNavbar: true,
    showFooter: true,
    showMain: true,
    navbarFixed:
      "position-sticky blur shadow-blur left-auto top-1 z-index-sticky px-0 mx-4",
    absolute: "position-absolute px-4 mx-0 w-100 z-index-2",
    bootstrap,
    isAuthenticated: !!localStorage.getItem('token'),
    token: localStorage.getItem('token') || '',
    userName: localStorage.getItem('userName') || '', // Adicionado o nome do usuário ao estado
    error: ''
  },
  mutations: {
    toggleConfigurator(state) {
      state.showConfig = !state.showConfig;
    },
    navbarMinimize(state) {
      const sidenav_show = document.querySelector(".g-sidenav-show");
      if (sidenav_show.classList.contains("g-sidenav-hidden")) {
        sidenav_show.classList.remove("g-sidenav-hidden");
        sidenav_show.classList.add("g-sidenav-pinned");
        state.isPinned = true;
      } else {
        sidenav_show.classList.add("g-sidenav-hidden");
        sidenav_show.classList.remove("g-sidenav-pinned");
        state.isPinned = false;
      }
    },
    sidebarType(state, payload) {
      state.isTransparent = payload;
    },
    cardBackground(state, payload) {
      state.color = payload;
    },
    navbarFixed(state) {
      state.isNavFixed = !state.isNavFixed;
    },
    toggleEveryDisplay(state) {
      state.showNavbar = !state.showNavbar;
      state.showSidenav = !state.showSidenav;
      state.showFooter = !state.showFooter;
    },
    toggleHideConfig(state) {
      state.hideConfigButton = !state.hideConfigButton;
    },
    setAuthentication(state, status) {
      state.isAuthenticated = status;
    },
    setToken(state, token) {
      state.token = token;
      state.isAuthenticated = !!token;
    },
    setUserName(state, userName) {
      state.userName = userName;
    },
    setError(state, error) {
      state.error = error;
    }
  },
  actions: {
    toggleSidebarColor({ commit }, payload) {
      commit("sidebarType", payload);
    },
    setCardBackground({ commit }, payload) {
      commit("cardBackground", payload);
    },
    async login({ commit }, credentials) {
      try {
        const response = await fetch('http://localhost:8081/auth/login', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify(credentials)
        });

        if (!response.ok) {
          throw new Error('Invalid login or password');
        }

        const data = await response.json();
        const token = data.token;
        const userName = data.username;

        localStorage.setItem('token', token);
        localStorage.setItem('userName', userName);

        commit('setToken', token);
        commit('setUserName', userName);
        commit('setError', '');
        return true;
      } catch (error) {
        commit('setError', error.message);
        return false;
      }
    },
    logout({ commit }) {
      localStorage.removeItem('token');
      localStorage.removeItem('userName');
      commit('setToken', null);
      commit('setUserName', '');
    }
  },
  getters: {
    isAuthenticated: (state) => state.isAuthenticated,
    userName: (state) => state.userName, // Getter para o nome do usuário
  },
});
