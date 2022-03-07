var mdHtml = window.markdownit();
var appEditor = new Vue({
    el: "#editor",
    data: {
        content: 'alt'
    },
    computed: {
        // a computed getter
        resultHtml: function () {
            // `this` points to the vm instance
            console.log(mdHtml.render(this.content));
            return mdHtml.render(this.content)
        }
    }
}) 