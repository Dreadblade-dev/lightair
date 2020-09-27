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
                <messages-list :messages="messages" />
            </v-container>
        </v-main>
        </v-container>
    </v-app>
</template>

<script>
import MessagesList from "components/messages/MessageList.vue";
import { addHandler } from "../util/ws";
import { getIndex } from "../util/collections";

export default {
    components: {
        MessagesList,
    },
    data() {
        return {
            messages: frontendData.messages,
            profile: frontendData.profile
        }
    },
    created() {
        addHandler(data => {
            let index = getIndex(this.messages, data.id)
            if (index > -1) {
                this.messages.splice(index, 1, data)
            } else {
                this.messages.push(data)
            }
        })
    }
}
</script>

<style>

</style>