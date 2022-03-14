var asideVue = new Vue({
    el: '#op',

    data() {
        return {
            isComponentModalActive: false,
            formProps: {
                id: -1,
                typeName: null,
                name: null,
                sequence: null,
                alias: null
            }
        }
    },
    methods: {
        create(dict) {
            this.formProps = {
                id: -1,
                typeName: "Create new category",
                action: "create_category",
                name: null,
                sequence: null,
                alias: null
            }
            this.isComponentModalActive = true
        },
        edit(dict) {

            this.formProps = {
                id: dict.id,
                typeName: "Edit",
                action: "edit_category&cate_id=" + dict.id,
                name: dict.name,
                sequence: dict.seq,
                alias: dict.alias
            }
            this.isComponentModalActive = true
        },
        del(dict) {
            this.formProps = {
                id: dict.id,
                typeName: "Delete",
                action: "delete_category",
                name: dict.name,
                sequence: dict.seq,
                alias: dict.alias
            }
            this.$buefy.dialog.confirm({
                title: 'Deleting category - ' + dict.name + ' - ' + dict.id,
                message: 'Bạn có chắc chắn muốn xoá Category này không?',
                confirmText: 'Delete',
                type: 'is-danger',
                hasIcon: false,
                onConfirm: () => this.submit()
            })
        },
        submit() {
            var par = this;
            const formBody = Object.keys(this.formProps).map(key => encodeURIComponent(key) + '=' + encodeURIComponent(this.formProps[key])).join('&');

            var request = new XMLHttpRequest();
            request.open("POST", "/SFBlog/admin?action=" + this.formProps.action, true);
            request.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');

            request.send(formBody);

            request.onreadystatechange = function () {
                if (this.readyState == 4 && this.status == 200) {
                    par.toast(this.responseText);
                }
            };

        },
        toast(text) {
            this.$buefy.toast.open({
                duration: 5000,
                message: text,
                position: 'is-bottom',
                type: text.indexOf("success") != -1 ? 'is-success' : 'is-danger'
            })
        }
    }
})