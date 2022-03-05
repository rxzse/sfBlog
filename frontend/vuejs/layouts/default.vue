<template>
  <div>
    <NavBar />
    <main>
      <section class="section" v-if="$auth.$state.loggedIn">
        <div class="container">
          <user-box /> 
        </div>
      </section>

      <section class="section">
        <div class="container">
          <nuxt />
        </div>
      </section>
    </main>
  </div>
</template>

<script lang="ts">
import Vue from "vue";
import get from "lodash.get";

import NavBar from "~/components/navbar.vue";
import UserBox from "~/components/userbox.vue";

export default Vue.extend({
  components: {
    NavBar,
    UserBox
  },
  computed: {
    picture() {
      return (
        get(this.$auth.user, "picture") || // OpenID
        get(this.$auth.user, "picture.data.url") || // Facebook graph API
        get(this.$auth.user, "avatar_url")
      ); // GitHub
    },
  },
});
</script>