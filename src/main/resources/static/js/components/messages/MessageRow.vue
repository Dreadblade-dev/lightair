<template>
    <v-card class="my-2">
        <v-card-text primary-title>
        <i>({{ message.id }})</i>
            &nbsp;
            {{ message.text }}
        </v-card-text>
        <v-card-actions>
            <v-btn icon @click="edit">
                <v-icon dark>
                    mdi-square-edit-outline
                </v-icon>
            </v-btn>
            <v-btn icon @click="del">
                <v-icon dark style="mdi-dark">
                    mdi-delete
                </v-icon>
            </v-btn>
        </v-card-actions>
    </v-card>
</template>

<script>
import {mapActions} from 'vuex'
export default {
    props: ['message', 'editMessage'],
    methods: {
        ...mapActions(['removeMessageAction']),
        edit() {
            this.editMessage(this.message)
        },
        del() {
            this.removeMessageAction(this.message)
        }
    }
}
</script>

<style>

</style>