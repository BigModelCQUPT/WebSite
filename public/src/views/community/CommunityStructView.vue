<template>
    <div style="margin-top: -20px">
        <h2 style="margin-bottom: 40px">社区党组结构</h2>
        <el-image  style="width: 80%; margin-top: -12px"
                   :src="require('../../assets/'+struct_img)"  lazy
        />
    </div>
</template>

<script>
    import axios from "axios";

    export default {
        name: "CommunityStructView",
        data(){
            return {

                struct_img:'struct.jpg'
            }
        },
        created() {
            this.init()
        },
        methods:{
            init() {
                const _this = this
                axios.get('http://10.16.104.183:8181/community/get').then(function (resp) {
                    if (resp.data.code == "200") {//返回成功
                         // console.log(resp.data.data.struct_img)
                        _this.struct_img = resp.data.data.struct_img
                    }
                })
            }
        }
    }
</script>

<style scoped>

</style>