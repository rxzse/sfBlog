const mEngine = window.markdownit("commonmark");

var asideVue = new Vue({
    el: '#app',

    data() {
        return {
            isComponentModalActive: false,
            isComponentPostActive: false,
            formProps: {
                id: -1,
                typeName: null,
                name: null,
                sequence: null,
                alias: null
            },
            formPostProps: {
                id: -1,
                typeName: null,
                title: null,
                alias: null,
                html: null,
                markdown: "",
                isDraft: false,
                isActive: true,
                category: 1
            }
        }
    },
    computed: {
        html_src() {
            return mEngine.render(this.formPostProps.markdown);
        },
    },
    methods: {
        genCateAlias() {
            this.formProps.alias = this.toAlias(this.formProps.name);
        },
        genPostAlias() {
            this.formPostProps.alias = this.toAlias(this.formPostProps.title);
        },
        toAlias(text) {
            if (text == null)
                return "";
            return text.toLowerCase()
                    .replace(/[^\w ]+/g, '')
                    .replace(/ +/g, '_');
        },
        create_post(dict) {
            this.formPostProps = {
                id: -1,
                typeName: "Create new post",
                action: "create_post",
                title: null,
                alias: null,
                html: null,
                markdown: "",
                isActive: true,
                category: 1
            }
            this.isComponentPostActive = true
        },
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
        edit_post(dict) {
            this.formPostProps = {
                id: dict.id,
                typeName: "Edit",
                action: "edit_post&post_id=" + dict.id,
                title: dict.title,
                alias: dict.alias,
                html: "",
                markdown: "Fetching from server...",
                isActive: dict.isActive,
                category: dict.category
            }
            this.isComponentPostActive = true;
            this.fetchPostMarkdown();
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
        
        del_post(dict) {
            this.formPostProps = {
                id: dict.id,
                typeName: "Delete",
                action: "delete_post",
                title: dict.title,
                alias: "",
                html: "",
                markdown: "",
                isActive: "",
                category: ""
            }
            this.$buefy.dialog.confirm({
                title: 'Deleting post - ' + dict.title + ' - ' + dict.id,
                message: 'Bạn có chắc chắn muốn xoá Post này không?',
                confirmText: 'Delete',
                type: 'is-danger',
                hasIcon: false,
                onConfirm: () => this.submit_post()
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
        submit_post() {
            var par = this;
            this.formPostProps.html = mEngine.render(this.formPostProps.markdown);
            const formBody = Object.keys(this.formPostProps).map(key => encodeURIComponent(key) + '=' + encodeURIComponent(this.formPostProps[key])).join('&');

            var request = new XMLHttpRequest();
            request.open("POST", "/SFBlog/admin?action=" + this.formPostProps.action, true);
            request.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');

            request.send(formBody);

            request.onreadystatechange = function () {
                if (this.readyState == 4 && this.status == 200) {
                    par.toast(this.responseText);
                }
            };

        },
        
        fetchPostMarkdown() {
            var par = this;
            this.formPostProps.html = mEngine.render(this.formPostProps.markdown);
            const formBody = Object.keys(this.formPostProps).map(key => encodeURIComponent(key) + '=' + encodeURIComponent(this.formPostProps[key])).join('&');

            var request = new XMLHttpRequest();
            request.open("POST", "/SFBlog/admin?action=post_markdown&id=" + this.formPostProps.id, true);
            request.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');

            request.send(formBody);

            request.onreadystatechange = function () {
                if (this.readyState == 4 && this.status == 200) {
                    par.formPostProps.markdown = this.responseText;
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