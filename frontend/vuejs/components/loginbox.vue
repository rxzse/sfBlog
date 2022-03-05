<template>
  
</template>

<script>
export default {
  name: "LoginBox",
  data() {
    return {
      isLoading: false,
      form: {
        email: "john.doe@example.com",
        password: "my-secret-password-9e9w",
        remember: false,
      },
    };
  },
  methods: {
    submit() {
      this.isLoading = true;
      setTimeout(() => {
        this.isLoading = false;
        this.$router.push("/");
      }, 750);
    },
  },
};
</script>