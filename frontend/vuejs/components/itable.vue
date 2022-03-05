<template>
  <div>
    <b-table
      :checked-rows.sync="checkedRows"
      :checkable="checkable"
      :paginated="paginated"
      :per-page="perPage"
      :data="clients"
      default-sort="name"
      striped
      narrowed
      hoverable
    >
      <b-table-column v-slot="props" label="Tên sản phẩm" field="name" sortable>
        {{ props.row.name }}
      </b-table-column>
      <b-table-column v-slot="props" label="Quốc gia" field="country" centered>
        <b-tag type="is-primary">{{ props.row.country }}</b-tag>
      </b-table-column>
      <b-table-column v-slot="props" label="Mô tả" field="desc" sortable>
        {{ props.row.desc }}
      </b-table-column>
      <b-table-column v-slot="props" label="Đơn giá" field="unitPrice" sortable>
        {{ formatNumber(props.row.unitPrice) }}
      </b-table-column>
      <b-table-column v-slot="props" label="Số lượng" field="inStock" sortable>
        {{ props.row.inStock }}
      </b-table-column>
      <b-table-column
        v-slot="props"
        custom-key="actions"
        cell-class="is-actions-cell"
      >
        <div class="buttons is-right no-wrap">
          <b-button
            type="is-primary"
            size="is-small"
            @click.prevent="formatNumber(props.row)"
          >
            Mua
          </b-button>
        </div>
      </b-table-column>

      <section slot="empty" class="section">
        <div class="content has-text-grey has-text-centered">
          <p>
            <b-icon icon="emoticon-sad" size="is-large" />
          </p>
          <p>Nothing's here&hellip;</p>
        </div>
      </section>
    </b-table>
  </div>
</template>

<script>
import numeral from "numeral";
export default {
  name: "iTable",
  props: {
    checkable: Boolean,
    isEmpty: Boolean,
    perPage: {
      type: Number,
      default: 10,
    },
  },
  data() {
    return {
      checkedRows: [],
      isModalActive: false,
      trashObject: null,
      clients: [
        {
          id: 1,
          name: "Gmail New",
          country: "VN",
          desc: "Mail new Live trâu (>3 ngày) Info Việt",
          unitPrice: 3500,
          inStock: -1,
        },
        {
          id: 2,
          name: "Gmail New",
          country: "US",
          desc: "Mail new Live trâu (>3 ngày) Info Us",
          unitPrice: 4000,
          inStock: -1,
        },
        {
          id: 3,
          name: "Gmail Ngâm",
          country: "US",
          desc: "Mail new Live trâu (>3 tháng) Info Us",
          unitPrice: 5000,
          inStock: -1,
        },
      ],
    };
  },
  computed: {
    paginated() {
      return this.clients.length > this.perPage;
    },
    // ...mapState(["clients"]),
  },
  methods: {
    formatNumber(num) {
      return numeral(num).format();
    },
    trashModalOpen(obj) {
      this.trashObject = obj;
      this.isModalActive = true;
      this.$buefy.dialog.prompt({
        message: `What's your name?`,
        inputAttrs: {
          placeholder: "e.g. Walter",
          maxlength: 10,
        },
        trapFocus: true,
        onConfirm: (value) => this.$buefy.toast.open(`Your name is: ${value}`),
      });
    },
  },
};
</script>