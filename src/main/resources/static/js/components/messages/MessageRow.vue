<template>
    <v-card class="my-2">
        <v-card-text>
            <div>
                <v-avatar
                        v-if="message.author && message.author.userpic"
                        size="48px">
                    <img
                            :src="message.author.userpic"
                            :alt="authorName"
                    >
                </v-avatar>

                <v-avatar
                        color="indigo"
                        v-else
                        size="36px">
                    <v-icon dark>
                        mdi-account-circle
                    </v-icon>
                </v-avatar>
                <span class="ml-2">
                    {{ authorName }}
                </span>
            </div>
            <div class="text--primary mt-3">
                {{ message.text }}
            </div>
        </v-card-text>
        <media v-if="message.link" :message="message"></media>
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
import Media from "components/media/Media.vue";
import CommentList from "../comment/CommentList.vue";
export default {
    props: ['message', 'editMessage'],
    components: { CommentList, Media },
    computed: {
        authorName() {
            return this.message.author ? this.message.author.name : "unknown"
        }
    },
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