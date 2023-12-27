<!--个人信息界面-->
<template>
    <!--    <div class="zuiwaiceng" style="text-align: left">-->
    <!--        <div>-->
    <!--            <el-icon class="title"><tickets /></el-icon>-->
    <!--            <span class="title-span">个人资料</span>-->
    <!--        </div>-->
    <!--    </div>-->
    <div style="text-align: left">
        <el-space wrap>
            <el-card class="box-card" style="width: 550px">
                <template #header>
                    <div class="card-header">
                        <el-icon class="title">
                            <tickets />
                        </el-icon>
                        <span class="title-span">个人信息</span>
                    </div>
                </template>
                <div class="information">
                    <div class="username">
                        <span>{{username}}</span>
                        <el-icon v-if="isCollapse" class="gender" isCollapse="isCollapse">
                            <male />
                        </el-icon>
                        <el-icon v-else class="gender" :isCollapse="isCollapse">
                            <female />
                        </el-icon>
                        <img :src="require('../assets/'+img_url)" style="height: 70px; width: 70px; float: right"
                            class="img-head">
                    </div>
                    <div>
                        <div style="margin-bottom: 12px">
                            <span class="key">职 位</span> <span style="margin-right: 10px">{{position}}</span>
                        </div>
                        <div>
                            <span class="key">真实姓名</span> <span style="margin-right: 10px">{{name}}</span> |
                            <span class="key" style="margin-left: 10px">居住地</span> <span
                                style="margin-right: 10px;">{{address}}</span> |
                            <span class="key" style="margin-left: 10px">年龄</span> <span
                                style="margin-right: 10px;">{{age}}</span>
                        </div>
                        <div style="margin-top: 15px">
                            <el-icon style="padding-right: 10px; float: left">
                                <iphone />
                            </el-icon>
                            <span style="margin-right: 15px">{{tel}}</span> |
                            <el-icon style="padding-left: 10px;">
                                <message />
                            </el-icon>
                            <span style="margin-left: 10px">{{email}}</span>
                        </div>
                    </div>
                </div>
                <div style="margin-top: 10px; text-align: right">
                    <el-button type="primary" @click="updateInformation">编辑资料</el-button>
                </div>
            </el-card>
        </el-space>
    </div>
</template>
<script>
    import { Tickets, Male, Female, Iphone, Message } from '@element-plus/icons-vue'
    import axios from 'axios'
    export default {
        gender: '男',
        name: "MyView",
        methods: {
            updateInformation() {
                this.$router.replace('/updateInformation')
            }

        },
        created() {
            console.log(window.sessionStorage)
            const _this = this
            axios.post('http://10.16.104.183:8181/getInformation', window.sessionStorage).then(function (resp) {
                // console.log(resp.data)
                if (resp.data.code == "200") {//返回成功
                    _this.username = resp.data.data.username
                    _this.name = resp.data.data.name
                    _this.address = resp.data.data.address
                    _this.tel = resp.data.data.tel
                    _this.email = resp.data.data.email
                    _this.age = resp.data.data.age
                    _this.img_url = resp.data.data.img
                    _this.position = resp.data.data.position
                    _this.gender = resp.data.data.gender
                    _this.isCollapse = true
                    if (_this.gender == "男")
                        _this.isCollapse = true
                    else
                        _this.isCollapse = false

                } else if (resp.data.code == "101") {
                    _this.$message.error('请先登录');
                    return false;
                } else {
                    console.log("error")
                }
            })
        },
        data() {
            return {
                isCollapse: false,
                username: '',
                name: '',//真实姓名
                address: '',
                year: '',
                month: '',
                tel: '',
                email: '',
                position: '',
                img_url: 'head1.jpg',
            }
        },
        components: {
            Tickets, Male, Female, Iphone, Message
        }


    }
</script>

<style scoped>
    .title {
        float: left;
        color: #3399ff;
        padding-right: 3px;
        height: 20px;
        margin: 0px 3px 0px 0px;
    }

    .title-span {
        font-size: 16px;
        font-weight: 700;
    }

    .information .username {
        margin-bottom: 12px;
    }

    .information .username span {
        font-size: 20px;
        font-weight: 600;
        padding-right: 3px;
        color: #2a2626;
    }

    .gender {
        height: 20px;
        width: 20px;
        color: #3399ff;
        font-size: 20px;
    }

    .key {
        color: rgba(60, 60, 76, 0.6);
        margin: 0px 12px 0px 0px;
    }

    .img-head {
        left: 0px;
        right: 0px;
        top: 0px;
        bottom: 0px;
        background: rgba(0, 0, 0, 0.3);
        border-radius: 10px;
    }
</style>