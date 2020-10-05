<template>
    <v-layout row>
        <v-text-field label="New message"
                      placeholder="What's new?"
                      v-model="text"
                      @keyup.enter="save" />

        <v-btn @click="save" large style="margin-left: 10px; margin-top: 5px;">
            Save
        </v-btn>
    </v-layout>
</template>

<script>
import { mapActions } from 'vuex'

export default {
    props: ['messageAttr'],
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
        ...mapActions(['addMessageAction', 'updateMessageAction']),
        save() {
            const message = {
                id: this.id,
                text: this.text
            }

            if (this.id) {
                this.updateMessageAction(message)
            } else {
                this.addMessageAction(message)
            }

            this.text = ''
            this.id = ''
        }
    }
}
</script>

<style>

</style>