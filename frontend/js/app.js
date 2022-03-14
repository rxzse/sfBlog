const mEngine = window.markdownit("commonmark");
var asideVue = new Vue({
    el: '#op',

    data() {
        return {
            isComponentModalActive: false,
            markdown_src: "",
            postTitle: "",
            name: "rxz",
            isPublic: true,
            formProps: {
                id: -1,
                typeName: "Create",
                name: 'evan@you.com',
                sequence: 10001,
                alias: 'rxzksksk'
            }
        }
    },
    computed: {
        html_src() {
            return mEngine.render(this.markdown_src);
        },
        post_alias() {
            return this.toAlias(this.postTitle);
        }
    },
    methods: {
        toAlias(text) {
            return text.toLowerCase()
                .replace(/[^\w ]+/g, '')
                .replace(/ +/g, '_');
        },
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
            const formBody = Object.keys(this.formProps).map(key => encodeURIComponent(key) + '=' + encodeURIComponent(this.formProps[key])).join('&');

            var request = new XMLHttpRequest();
            request.open("POST", "/SFBlog/admin?action=" + action, true);
            request.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');

            request.send(formBody);

            request.onreadystatechange = function () {
                if (this.readyState == 4 && this.status == 200) {
                    console.log(this.responseText);
                }
            };

        }
    }
})