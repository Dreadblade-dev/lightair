<template>
    <v-card class="my-2">
        <v-card-text>
            <user-link
                :user="message.author"
                size="48"
            >
            </user-link>
            <div class="text--primary">
                {{ message.text }}
            </div>
        </v-card-text>
        <media v-if="message.link" :message="message" class="mb-3"></media>
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
        <comment-list class="ml-5 mr-5"
                      :comments="message.comments"
                      :message-id="message.id"
        ></comment-list>
    </v-card>
</template>

<script>
import { mapActions } from "vuex"
import Media from "components/media/Media.vue"
import CommentList from "../comment/CommentList.vue"
import UserLink from "components/UserLink.vue"
export default {
    props: ['message', 'editMessage'],
    components: {UserLink, CommentList, Media },
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