<template>
  <div>
  <h2 class="title"># Các bài viết của Blog này</h2>
  <itable />
  </div>
</template>

<script>
import iTable from "~/components/itable.vue";

export default {
  name: "IndexPage",
  components: {
    iTable,
    // Card
  },
};
</script>
