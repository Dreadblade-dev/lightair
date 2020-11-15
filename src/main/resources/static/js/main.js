import Vue from 'vue'
import Vuetify from "vuetify"
import '@babel/polyfill'
import 'api/resource'
import router from 'router/router'
import App from 'pages/App.vue'
import store from "store/store"
import { connect } from "./util/ws"
import profile from "./api/profile";
import * as Sentry from "@sentry/browser";
import { Vue as VueIntegration } from "@sentry/integrations";

Sentry.init({
    dsn: "https://907ba32efa2544a1b738cc4f97c259dc@o476946.ingest.sentry.io/5517215",
    integrations: [
        new VueIntegration({
            Vue,
            tracing: true,
            attachProps: true
        }),
    ],
});

Sentry.configureScope(scope =>
    scope.setUser({
        id: profile && profile.id,
        username: profile && profile.name
    })
)

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