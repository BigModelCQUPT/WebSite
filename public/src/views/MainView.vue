<!--主界面、-->
<template>
    <div class="common-layout" style="min-height:100%">
        <el-header style=" font-size: 12px">
            <div class="toolbar">
                <h1 style="  padding-top: 9px; font-size: 30px; text-align: left; color:#bfcbd9  ">
                    CQUPT大模型分析
                </h1>
                <el-dropdown class="user">

                    <el-icon style="padding-right: 5px">
                        <avatar style="padding-top: 10px" />
                    </el-icon>
                    <template #dropdown>
                        <el-dropdown-menu>
                            <el-dropdown-item @click="getInformation">个人信息</el-dropdown-item>
                            <el-dropdown-item @click="updatePassword">修改密码</el-dropdown-item>
                            <el-dropdown-item @click="loginOut">退出登录</el-dropdown-item>
                        </el-dropdown-menu>
                    </template>
                    <span>{{ username }}</span>
                </el-dropdown>

            </div>
        </el-header>
        <el-container style="border: 1px solid #eeeeee">
            <el-aside width="200px" style="background-color: rgb(6, 50, 126)">
                <el-scrollbar>
                    <el-menu :default-active="activeIndex" router>

                        <el-menu-item index="overView">
                            <template #title>
                                <el-icon>
                                    <Menu />
                                </el-icon>总览
                            </template>
                        </el-menu-item>
                        <el-menu-item index="getResidentInformation">
                            <el-icon>
                                <UserFilled />
                            </el-icon>账号信息
                        </el-menu-item>
<<<<<<< HEAD
                        <el-sub-menu index="">
                            <template #title>
                                <el-icon>
                                    <DataBoard />
                                </el-icon>全部信息
                            </template>
                            <el-menu-item index="getTenantInformation">
                                <el-icon>
                                    <DataAnalysis />
                                </el-icon>推特信息
                            </el-menu-item>
                            <el-menu-item index="getTelegramInformation">
                                <el-icon>
                                    <DataAnalysis />
                                </el-icon>Telegram信息
                            </el-menu-item>
                        </el-sub-menu>

                        <el-menu-item index="TokenList">
                            <el-icon>
                                <Key />
                            </el-icon>token管理
=======
                        <el-menu-item index="keyword">
                            <el-icon>
                                <DataBoard />
                            </el-icon>关键词管理
                        </el-menu-item>
                        <el-menu-item index="getTenantInformation">
                            <el-icon>
                                <DataBoard />
                            </el-icon>Twitter全部信息
                        </el-menu-item>
                        <el-menu-item index="getYoutubeInformation">
                            <el-icon>
                                <DataBoard />
                            </el-icon>Youtube全部信息
                        </el-menu-item>
                        <el-menu-item index="keyInformation">
                            <el-icon>
                                <DataBoard />
                            </el-icon>关键信息
>>>>>>> ffc14b02a948452209c8504655d41786bdd0c07f
                        </el-menu-item>
                    </el-menu>
                </el-scrollbar>
            </el-aside>
            <el-main>
<<<<<<< HEAD

=======
                <!-- <el-breadcrumb separator=">" class="bread" v-if="this.$router.currentRoute._rawValue.fullPath != '/main'">
                    <el-breadcrumb-item :to="{ path: item.path }" v-for="item in list" :key="item">{{ item.name
                    }}</el-breadcrumb-item>
                                       <el-breadcrumb-item>{{this.$router.currentRoute._rawValue.name}}</el-breadcrumb-item>
                                       <el-breadcrumb-item>promotion list</el-breadcrumb-item>
                                       <el-breadcrumb-item>promotion detail</el-breadcrumb-item>
                    <el-divider border-style="double" />
                </el-breadcrumb> -->

                <!-- <div>
                    <div ref="mychart" style="height: 560px; width: 100%; margin-top: 0px">
                    </div>
                </div> -->
>>>>>>> ffc14b02a948452209c8504655d41786bdd0c07f
                <router-view />
            </el-main>

        </el-container>

    </div>
</template>
<<<<<<< HEAD
<script>
    import {  /*Fold, Expand,*/  Menu, Avatar, DataBoard, UserFilled, DataAnalysis, Key } from '@element-plus/icons-vue'
    import { ref } from 'vue'
    import axios from 'axios'
    import { ElNotification } from 'element-plus'



    export default {
        name: 'MainView',
        methods: {
            //最底部显示最新消息
            loginOut() {
                localStorage.removeItem("jwt_token");
                this.$router.replace('/')
            },
            handleClick(click) {
                console.log(click)
            },
            getInformation() {
                this.$router.replace('/my')
            },
            getNew() {
                this.router = this.$router.currentRoute._rawValue.name
            },
            updatePassword() {
                this.$router.push('/updatePassword')
            },
            init() {
                // const _this = this
                axios.get('http://10.16.104.183:8181/getMeetingNotice').then(function (resp) {
                    // console.log(resp.data.data)
                    if (resp.data.code == "200") {//返回成功
                        if (resp.data.data[0] != '') {
                            var y = resp.data.data[0].date.substring(0, 4)
                            var m = resp.data.data[0].date.substring(5, 7)
                            var d = resp.data.data[0].date.substring(8, 10)
                            var tmp = new Date()
                            // console.log(tmp.getFullYear() + '-' + (tmp.getMonth() + 1) + '-' + tmp.getDate())
                            if (!(window.sessionStorage.notice)) {
                                {
                                    if (y == parseInt(tmp.getFullYear()) && m == parseInt(tmp.getMonth() + 1) && d == parseInt(tmp.getDate())) {
                                        ElNotification({
                                            title: '会议通知',
                                            dangerouslyUseHTMLString: true,
                                            message: '您在今天有会议要参加。<br/>会议开始时间: ' + resp.data.data[0].start_time + '。<br/>会议地点: ' + resp.data.data[0].address + '。',
                                        })
                                        window.sessionStorage.setItem("notice", "已添加")
                                    }
=======
<script  >
import {  /*Fold, Expand,*/  Menu, Avatar, DataBoard, UserFilled } from '@element-plus/icons-vue'
import { ref } from 'vue'
import axios from 'axios'
import { ElNotification } from 'element-plus'



export default {
    name: 'MainView',
    methods: {
        //最底部显示最新消息
        loginOut() {
            localStorage.removeItem("jwt_token");
            this.$router.replace('/')
        },
        handleClick(click) {
            console.log(click)
        },
        getInformation() {
            this.$router.replace('/my')
        },
        getNew() {
            this.router = this.$router.currentRoute._rawValue.name
        },
        updatePassword() {
            this.$router.push('/updatePassword')
        },
        init() {
            // const _this = this
            axios.get('http://10.16.104.183:8181/getMeetingNotice').then(function (resp) {
                // console.log(resp.data.data)
                if (resp.data.code == "200") {//返回成功
                    if (resp.data.data[0] != '') {
                        var y = resp.data.data[0].date.substring(0, 4)
                        var m = resp.data.data[0].date.substring(5, 7)
                        var d = resp.data.data[0].date.substring(8, 10)
                        var tmp = new Date()
                        // console.log(tmp.getFullYear() + '-' + (tmp.getMonth() + 1) + '-' + tmp.getDate())
                        if (!(window.sessionStorage.notice)) {
                            {
                                if (y == parseInt(tmp.getFullYear()) && m == parseInt(tmp.getMonth() + 1) && d == parseInt(tmp.getDate())) {
                                    ElNotification({
                                        title: '会议通知',
                                        dangerouslyUseHTMLString: true,
                                        message: '您在今天有会议要参加。<br/>会议开始时间: ' + resp.data.data[0].start_time + '。<br/>会议地点: ' + resp.data.data[0].address + '。',
                                    })
                                    window.sessionStorage.setItem("notice", "已添加")
>>>>>>> ffc14b02a948452209c8504655d41786bdd0c07f
                                }
                            }
                        }
                    }
<<<<<<< HEAD
                })

            },
        },
        data() {
            return {
                dialogVisible: false,
                username: "",
                router: '',
                value: '',
                user: {},
                isCollapse: false,
                users: [],
                chatUser: '',
                text: "",
                messages: [],
                content: '',
                img: '',
            }
        },
        created() {
            this.username = window.sessionStorage.username
            // console.log(this.$route.matched.length)
            this.value = ref(new Date())
            this.init()
        },
        components: {
        /* Fold, Expand,*/ Menu, Avatar, DataBoard, UserFilled, DataAnalysis, Key
        },
        computed: {
            list() {
                return this.$route.matched
            }
        },

    }
</script>
<style scoped>
    .el-header {
        position: relative;
        background-color: #324157;
        color: var(--el-text-color-primary);
    }

    .el-aside {
        height: 800px;
        width: 240px;
        color: var(--el-text-color-primary);
        background: #ffffff !important;
        border-right: solid 1px #e6e6e6;
        box-sizing: border-box;
    }

    .el-menu {
        border-right: none;
    }

    .el-main {
        padding: 0;
    }

    .toolbar .user {
        position: absolute;
        display: inline-flex;
        align-items: center;
        top: 50%;
        right: 20px;
        transform: translateY(-50%);
    }

    .welcome {
        text-align: center;
        font-size: 40px;
        color: #409eff;
        font-family: 华文楷体;
        padding-top: 150px;
    }

    .bread {
        margin-top: 10px;
        margin-left: 10px;
        font-size: 15px;
        margin-bottom: 10px;
    }

    .img-head {
        border-radius: 40px;
    }
=======
                }
            })

        },
    },
    data() {
        return {
            dialogVisible: false,
            username: "",
            router: '',
            value: '',
            user: {},
            isCollapse: false,
            users: [],
            chatUser: '',
            text: "",
            messages: [],
            content: '',
            img: '',
        }
    },
    created() {
        this.username = window.sessionStorage.username
        // console.log(this.$route.matched.length)
        this.value = ref(new Date())
        this.init()
    },
    components: {
        /* Fold, Expand,*/ Menu, Avatar, DataBoard, UserFilled
    },
    computed: {
        list() {
            return this.$route.matched
        }
    },

}
</script>
<style  scoped>
.el-header {
    position: relative;
    background-color: #324157;
    color: var(--el-text-color-primary);
}

.el-aside {
    height: 800px;
    width: 240px;
    color: var(--el-text-color-primary);
    background: #ffffff !important;
    border-right: solid 1px #e6e6e6;
    box-sizing: border-box;
}

.el-menu {
    border-right: none;
}

.el-main {
    padding: 0;
}

.toolbar .user {
    position: absolute;
    display: inline-flex;
    align-items: center;
    top: 50%;
    right: 20px;
    transform: translateY(-50%);
}

.welcome {
    text-align: center;
    font-size: 40px;
    color: #409eff;
    font-family: 华文楷体;
    padding-top: 150px;
}

.bread {
    margin-top: 10px;
    margin-left: 10px;
    font-size: 15px;
    margin-bottom: 10px;
}

.img-head {
    border-radius: 40px;
}
>>>>>>> ffc14b02a948452209c8504655d41786bdd0c07f
</style>