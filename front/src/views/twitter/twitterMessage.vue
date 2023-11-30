<template>
    <ContentField>
        Twitter 内容
        <table class="table table-striped table-hover" style="text-align: center;">
            <thead>
                <tr>
                    <th>序号</th>
                    <th>推文序号</th>
                    <th>关键词</th>
                    <th>推文内容</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="tweet in tweets" :key="tweet.id">
                    <td>{{ tweet.id }}</td>
                    <!-- <td>
                        <img :src="user.photo" alt="" class="record-user-photo">
                        &nbsp;
                        <span class="record-user-username">{{ twitter.keyWord }}</span>
                    </td> -->
                    <td>{{ tweet.tweetid }}</td>
                    <td>{{ tweet.keyword }}</td>
                    <td>{{ tweet.text }}</td>
                </tr>
            </tbody>
        </table>
        <nav aria-label="...">
            <ul class="pagination" style="float: right;">
                <li class="page-item" @click="click_page(-2)">
                    <a class="page-link" href="#">前一页</a>
                </li>
                <li :class="'page-item ' + page.is_active" v-for="page in pages" :key="page.number"
                    @click="click_page(page.number)">
                    <a class="page-link" href="#">{{ page.number }}</a>
                </li>
                <li class="page-item" @click="click_page(-1)">
                    <a class="page-link" href="#">后一页</a>
                </li>
            </ul>
        </nav>
    </ContentField>
</template>

<script>
import ContentField from '../../components/ContentField.vue'
import { useStore } from 'vuex';
import { ref } from 'vue';
import $ from 'jquery';

export default {
    components: {
        ContentField
    },
    setup() {
        const store = useStore();
        let tweets = ref([]);
        let current_page = 1;
        let total_tweets = 0;
        let pages = ref([]);

        const click_page = page => {
            if (page === -2) page = current_page - 1;
            else if (page === -1) page = current_page + 1;
            let max_pages = parseInt(Math.ceil(total_tweets / 10));

            if (page >= 1 && page <= max_pages) {
                pull_page(page);
            }
        }

        const update_pages = () => {
            let max_pages = parseInt(Math.ceil(total_tweets / 10));
            let new_pages = [];
            for (let i = current_page - 2; i <= current_page + 2; i++) {
                if (i >= 1 && i <= max_pages) {
                    new_pages.push({
                        number: i,
                        is_active: i === current_page ? "active" : "",
                    });
                }
            }
            pages.value = new_pages;
        }

        const pull_page = (page) => {
            current_page = page;
            $.ajax({
                url: "http://127.0.0.1:3000/tweet/listAll/",
                data: {
                    page,
                },
                type: "get",
                headers: {
                    Authorization: "Bearer " + store.state.user.token,
                },
                success(resp) {
                    tweets.value = resp.data;
                    console.log(tweets.value);
                    total_tweets = resp.total_tweets;
                    update_pages();
                },
                error(resp) {
                    console.log(resp);
                }
            })
        }


        pull_page(current_page);

        return {
            tweets,
            pages,
            click_page
        }
    }
}



</script>

<style scoped>
img.record-user-photo {
    width: 4vh;
    border-radius: 50%;
}
</style>
