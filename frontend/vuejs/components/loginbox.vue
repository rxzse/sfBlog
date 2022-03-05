<template>
  
</template>

<script>
export default {
  name: "LoginBox",
  data() {
    return {
      isLoading: false,
      form: {
        email: "adminstrator@email",
        password: "yourblogidpassword",
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