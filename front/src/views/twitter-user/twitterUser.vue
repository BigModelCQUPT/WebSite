<template>
  <ContentField>
    <div>

    用户信息
      <div class="">
        <button type="button" class="btn btn-primary " style="">新增</button>
      </div>
    <table class="table table-striped table-hover" style="text-align: center;">
      <thead>
      <tr>
        <th>序号</th>
        <th>推特账号</th>
        <th>推特用户名</th>
        <th>真实姓名</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="twitter_user in twitter_users" :key="twitter_user.id">
        <td>{{ twitter_user.id }}</td>
        <!-- <td>
            <img :src="user.photo" alt="" class="record-user-photo">
            &nbsp;
            <span class="record-user-username">{{ twitter.keyWord }}</span>
        </td> -->
        <td>{{ twitter_user.twitterId }}</td>
        <td>{{ twitter_user.username }}</td>
        <td>{{ twitter_user.name }}</td>
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

    </div>

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
    let twitter_users = ref([]);
    let current_page = 1;
    let total_users = 0;
    let pages = ref([]);

    const click_page = page => {
      if (page === -2) page = current_page - 1;
      else if (page === -1) page = current_page + 1;
      let max_pages = parseInt(Math.ceil(total_users / 10));

      if (page >= 1 && page <= max_pages) {
        pull_page(page);
      }
    }

    const update_pages = () => {
      let max_pages = parseInt(Math.ceil(total_users / 10));
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
        url: "http://127.0.0.1:3000/twitterUser/getUsers",
        data: {
          page,
        },
        type: "get",
        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        success(resp) {
          twitter_users.value = resp.data.records;
          console.log(resp.data);
          total_users = resp.data.total;
          update_pages();
        },
        error(resp) {
          console.log(resp);
        }
      })
    }


    pull_page(current_page);

    return {
      twitter_users,
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
