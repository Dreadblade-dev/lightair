<template>
    <v-app>
        <v-container>
        <v-app-bar app>
            <v-toolbar-title> LightAir</v-toolbar-title>
            <v-spacer></v-spacer>

            <span v-if="profile">{{profile.name}}</span>
            <v-btn icon href="/logout" v-if="profile">
                <v-icon>mdi-exit-to-app</v-icon>
            </v-btn>

        </v-app-bar>
        <v-main app>
            <v-container v-if="!profile">
                You need to <a href="/login">Sign in with Google</a>
            </v-container>
            <v-container v-if="profile">
                <messages-list />
            </v-container>
        </v-main>
        </v-container>
    </v-app>
</template>

<script>
import { mapState, mapMutations } from 'vuex'
import MessagesList from "components/messages/MessageList.vue";
import { addHandler } from "../util/ws";
export default {
    components: {
        MessagesList,
    },
    computed: mapState(['profile']),
    methods: mapMutations(['addMessageMutation', 'updateMessageMutation', 'removeMessageMutation']),
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
                        console.error(`Looks like eventType is unknown ${data.eventType}`)
                }
            } else {
                console.error(`Looks like objectType is unknown ${data.objectType}`)
            }
        })
    }
}
</script>

<style>

</style>