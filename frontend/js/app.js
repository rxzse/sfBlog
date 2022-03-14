var asideVue = new Vue({
    el: '#op',


    data() {
        return {
            isComponentModalActive: false,
            name: "rxz",
            formProps: {
                id: -1,
                typeName: "Create",
                name: 'evan@you.com',
                sequence: 10001,
                alias: 'rxzksksk'
            }
        }
    },
    methods: {
        edit() {

            this.formProps = {
                id: -1,
                typeName: "Edit",
                name: 'evan@you.com',
                sequence: 10001,
                alias: 'rxzksksk'
            }
            this.isComponentModalActive = true
        },
        del(pp) {
            console.log(pp);
            this.$buefy.dialog.confirm({
                title: 'Deleting category',
                message: 'Bạn có chắc chắn muốn xoá Category này không?',
                confirmText: 'Delete',
                type: 'is-danger',
                hasIcon: false,
                onConfirm: () => this.$buefy.toast.open('Cate deleted!')
            })
        },
        submit(action) {
            var form_data = new FormData();

            for (var key in this.formProps) {
                form_data.append(key, this.formProps[key]);
            }

            var request = new XMLHttpRequest();
            request.open("POST", "http://foo.com/submitform.php");
            request.send(form_data);

            console.log(form_data)

        }
    }
})