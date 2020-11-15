import Vue from 'vue'
import Vuetify from "vuetify"
import '@babel/polyfill'
import 'api/resource'
import router from 'router/router'
import App from 'pages/App.vue'
import store from "store/store"
import { connect } from "./util/ws"
import profile from "./api/profile";

if (profile) {
    connect()
}

Vue.use(Vuetify)


new Vue({
    el: '#app',
    store: store,
    router: router,
    vuetify: new Vuetify({}),
    render: a => a(App)
})