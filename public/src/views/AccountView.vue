<template>
    <div>
        <table>
            <tr>
                <td>编号</td>
                <td>账户</td>
                <td>密码</td>
            </tr>
            <tr v-for="item in accounts" :key="item">
                <td>{{item.num}}</td>
                <td>{{item.id}}</td>
                <td>{{item.pw}}</td>
            </tr>
        </table>
        <router-link to="/login">
            <h2>增删改查</h2>
        </router-link>

    </div>
</template>
<script  >
    import axios from 'axios'
    export default {
        name: "AccountView",
        data(){
            return {
                msg: "tips",
                accounts:[
                    {
                        num:1,
                        id:'admin',
                        pw:'123'
                    },
                    {
                        num:2,
                        id:'admin',
                        pw:'123'
                    }
                ]
            }

        },
        created() {
            const _this = this
<<<<<<< HEAD
            axios.get('http://10.16.104.183:8181/account/findAll').then(function (resp) {
=======
            axios.get('http://localhost:8181/account/findAll').then(function (resp) {
>>>>>>> ffc14b02a948452209c8504655d41786bdd0c07f
                //console.log(resp)
                _this.accounts = resp.data
            })
            //     // alert(43)
        }
    }
</script>

<style scoped>

</style>