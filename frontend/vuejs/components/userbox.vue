<template>
  <article class="media">
    <div class="media-content">
      <div class="content">
        <b-button size="is-small">Tài khoản</b-button>
        <b-button size="is-small">Số bài viết</b-button>
        <hr />
        <b-tag type="is-primary" size="is-medium">Số dư: </b-tag>
        <b-tag type="is-primary" size="is-medium"
          >{{ currentBalance }} VNĐ</b-tag
        >
      </div>
    </div>
  </article>
</template>

<script lang="ts">
import numeral from "numeral";
import Vue from "vue";
export default Vue.extend({
  computed: {
    currentBalance(): string {
      return numeral(this.$store.state.wallet_info.balance).format();
    },
  },
  methods: {},
  mounted() {
    this.$store.dispatch("getWalletInfo");
  },
});
</script>