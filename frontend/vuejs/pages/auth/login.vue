<template>
  <div>
    <h2 class="title"># Đăng nhập</h2>
    <div class="columns is-mobile is-centered">
      <div class="column is-half">
        <!-- <form method="POST" @submit.prevent="submit"> -->
        <b-field label="Username">
          <b-input v-model="username" name="username" required />
        </b-field>

        <b-field label="Password">
          <b-input
            v-model="password"
            type="password"
            name="password"
            required
          />
        </b-field>

        <hr />

        <b-field grouped>
          <div class="control">
            <b-button
              native-type="submit"
              type="is-black"
              @click.prevent="login"
            >
              Login
            </b-button>
          </div>
          <div class="control">
            <router-link to="/" class="button is-outlined is-black">
              Signup
            </router-link>
          </div>
        </b-field>
        <!-- </form> -->
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import md5 from "blueimp-md5";
import Vue from "vue";
export default Vue.extend({
  middleware: ["auth"],
  data() {
    return {
      username: "",
      password: "",
      error: null,
    };
  },
  computed: {
    redirect() {
      return (
        this.$route.query.redirect &&
        decodeURIComponent(this.$route.query.redirect)
      );
    },
    isCallback() {
      return Boolean(this.$route.query.callback);
    },
    errorMessage() {
      const { error } = this;
      if (!error || typeof error === "string") {
        return error;
      }
      let msg = "";
      if (error.message) {
        msg += error.message;
      }
      if (error.errors) {
        msg += `(${JSON.stringify(error.errors)
          .replace(/[{}"[\]]/g, "")
          .replace(/:/g, ": ")
          .replace(/,/g, " ")})`;
      }
      return msg;
    },
  },
  methods: {
    login() {
      this.error = null;
      return this.$auth
        .loginWith("local", {
          data: {
            // username: this.username,
            username: this.username.trim(),
            password: this.password,
          },
        })
        .catch((err) => {
          // eslint-disable-next-line no-console
          console.error(err);
          const responseData = err.response?.data;
          this.error = responseData?.error ?? responseData;
          this.$buefy.toast.open({
            // duration: 5000,
            message: responseData,
            position: "is-bottom",
            type: "is-danger",
          });
        });
    },
  },
});
</script>
