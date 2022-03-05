<template>
  <nav
    class="navbar docs-navbar is-spaced has-shadow"
    :class="{ 'is-primary is-transparent': light }"
  >
    <div class="container">
      <div class="navbar-brand">
        <router-link
          to="/"
          class="navbar-item"
          title="Buefy: lightweight UI components for Vue.js based on Bulma"
          active-class=""
        >
          <img v-if="light" src="../assets/buefy.png" alt="Buefy" />
          <img v-else src="../assets/buefy.png" alt="Buefy" />
        </router-link>

        <a
          class="navbar-item"
          :class="{ 'has-text-dark': !light }"
          href="https://github.com/buefy/buefy"
          target="_blank"
          title="Github"
        >
          <b-icon icon="github-circle" />
        </a>

        <a
          class="navbar-item"
          :class="{ 'has-text-discord': !light }"
          href="https://discordapp.com/invite/ZkdFJMr"
          target="_blank"
          title="Discord"
        >
          <b-icon icon="discord" />
        </a>

        <a
          class="navbar-item"
          :class="{ 'has-text-twitter': !light }"
          href="https://twitter.com/walter_tommasi"
          target="_blank"
          title="Twitter"
        >
          <b-icon icon="twitter" />
        </a>

        <span
          class="navbar-burger burger"
          :class="{ 'is-active': isMenuActive }"
          @click="isMenuActive = !isMenuActive"
        >
          <span />
          <span />
          <span />
        </span>
      </div>

      <div class="navbar-menu" :class="{ 'is-active': isMenuActive }">
        <div class="navbar-end">
          <div v-if="$auth.$state.loggedIn">
            <a
              class="button is-primary is-success"
            >
            @{{ $auth.user.username }}
            </a>

            <a class="button is-primary is-danger" @click="$auth.logout()">
              <strong>Đăng xuất</strong>
            </a>
          </div>
          <router-link to="/auth/login" class="navbar-item" v-else>
            Đăng nhập
          </router-link>
        </div>
      </div>
    </div>
  </nav>
</template>

<script>
// import buefyPackage from "../../package";
// import bulmaPackage from "bulma/package";

export default {
  props: {
    light: Boolean,
  },
  data() {
    return {
      isMenuActive: false,
      version: " 0.1",
      bulmaVersion: " 0.1.2",
    };
  },
  methods: {
    closeMenu() {
      this.isMenuActive = false;
    },
    toggleHtmlClip() {
      document.documentElement.classList.toggle(
        "is-clipped-touch",
        this.isMenuActive
      );
    },
  },
  mounted() {
    // this.$eventHub.$on("navigate", this.closeMenu);
  },
};
</script>