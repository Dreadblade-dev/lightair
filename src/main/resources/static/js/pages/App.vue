<template>
    <v-app>
        <v-container>
        <v-app-bar app>
            <v-toolbar-title>LightAir</v-toolbar-title>
            <v-btn v-if="profile" :disabled="$route.path === '/'" text @click="showMessages" class="ml-3">
                Messages
            </v-btn>
            <v-spacer></v-spacer>

            <v-btn v-if="profile" :disabled="$route.path === '/user'" text @click="showProfile">
                {{profile.name}}
            </v-btn>
            <v-btn icon href="/logout" v-if="profile">
                <v-icon>mdi-exit-to-app</v-icon>
            </v-btn>

        </v-app-bar>
        <v-main app>
            <router-view></router-view>
        </v-main>
        </v-container>
    </v-app>
</template>

<script>
import { mapState, mapMutations } from 'vuex'
import { addHandler } from "util/ws";
export default {
    computed: mapState(['profile']),
    methods: {
        ...mapMutations([
                'addMessageMutation',
                'updateMessageMutation',
                'removeMessageMutation',
                'addCommentMutation'
        ]),
        showMessages() {
            this.$router.push('/')
        },
        showProfile() {
            this.$router.push('/user')
        }
    },
    created() {
        addHandler(data => {
            if (data.objectType === 'MESSAGE') {
                switch (data.eventType) {
                    case 'CREATE':
                        this.addMessageMutation(data.body)
                        break
                    case 'UPDATE':
                        this.updateMessageMutation(data.body)
                        break
                    case 'REMOVE':
                        this.removeMessageMutation(data.body)
                        break
                    default:
                        console.error(`Looks like eventType is unknown "${data.eventType}"`)
                }
            } else if (data.objectType === 'COMMENT') {
                switch (data.eventType) {
                    case 'CREATE':
                        this.addCommentMutation(data.body)
                        break
                    default:
                        console.error(`Looks like eventType is unknown "${data.eventType}"`)
                }
            } else {
                console.error(`Looks like objectType is unknown "${data.objectType}"`)
            }
        })
    },
    beforeMount() {
        if (!this.profile) {
            this.$router.replace("/auth")
        }
    }
}
</script>

<style>

</style>