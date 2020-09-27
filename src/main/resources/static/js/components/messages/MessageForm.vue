<template>
    <v-layout row>
        <v-text-field label="New message" placeholder="What's new?" v-model="text" />
        <v-btn @click="save" large style="margin-left: 10px; margin-top: 5px;">Save</v-btn>
    </v-layout>
</template>

<script>
import { sendMessage } from "util/ws";

export default {
    props: ['messages', 'messageAttr'],
    data() {
        return {
            id: '',
            text: ''
        }
    },
    watch: {
        messageAttr(newValue, oldValue) {
            this.id = newValue.id
            this.text = newValue.text
        }
    },
    methods: {
        save() {
            sendMessage({id: this.id, text: this.text})
            this.text = ''
            this.id = ''
        }
    }
}
</script>

<style>

</style>