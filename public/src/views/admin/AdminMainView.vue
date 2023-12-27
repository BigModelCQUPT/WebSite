<!--管理端主界面-->
<template>
    <div class="common-layout">
        <el-header style=" font-size: 12px">
            <div class="toolbar">
                <h1 style="padding-top: 9px; font-size: 30px; text-align: left;">
                    大模型管理员系统
                </h1>
                <el-dropdown class="user">
                    <el-icon style="padding-right: 5px">
                        <avatar style="padding-top: 10px" />
                    </el-icon>
                    <template #dropdown>
                        <el-dropdown-menu>
                            <el-dropdown-item @click="loginOut">退出登录</el-dropdown-item>
                        </el-dropdown-menu>
                    </template>
                    <span>{{ username }}</span>
                </el-dropdown>

            </div>
        </el-header>
        <el-container style="height: 500px; border: 1px solid #eee">
            <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
                <el-scrollbar>
                    <el-menu :default-active="activeIndex" router>
                        <!--                    :default-openeds="['1', '3']" 打开对应的menu-->
                        <el-sub-menu index="3">
                            <template #title>
                                <el-icon>
                                    <setting />
                                </el-icon>员工工资管理
                            </template>
                            <el-menu-item-group>
                                <el-menu-item index="salaryAccount">
                                    <el-icon>
                                        <svg t="1650628080599" class="icon" viewBox="0 0 1024 1024" version="1.1"
                                            xmlns="http://www.w3.org/2000/svg" p-id="2850" width="200" height="200">
                                            <path
                                                d="M800 736H448a32 32 0 0 0 0 64h352a32 32 0 0 0 0-64zM800 480H448a32 32 0 0 0 0 64h352a32 32 0 0 0 0-64zM800 224H448a32 32 0 0 0 0 64h352a32 32 0 0 0 0-64zM304 736h-64a32 32 0 0 0 0 64h64a32 32 0 0 0 0-64zM304 480h-64a32 32 0 0 0 0 64h64a32 32 0 0 0 0-64zM304 224h-64a32 32 0 0 0 0 64h64a32 32 0 0 0 0-64z"
                                                p-id="2851"></path>
                                            <path
                                                d="M864 96H160a96 96 0 0 0-96 96v640a96 96 0 0 0 96 96h704a96 96 0 0 0 96-96V192a96 96 0 0 0-96-96z m32 736a32 32 0 0 1-32 32H160a32 32 0 0 1-32-32v-160h672a32 32 0 0 0 0-64H128v-192h672a32 32 0 0 0 0-64H128V192a32 32 0 0 1 32-32h704a32 32 0 0 1 32 32v640z"
                                                p-id="2852"></path>
                                        </svg>
                                    </el-icon>工资账套管理
                                </el-menu-item>
                            </el-menu-item-group>
                        </el-sub-menu>

                        <el-sub-menu index="6">
                            <template #title>
                                <el-icon>
                                    <list />
                                </el-icon>每日一报信息
                            </template>
                            <el-menu-item-group>
                                <el-menu-item index="dailyAdmin">
                                    <el-icon><svg t="1650628429102" class="icon" viewBox="0 0 1024 1024" version="1.1"
                                            xmlns="http://www.w3.org/2000/svg" p-id="6373" width="200" height="200">
                                            <path
                                                d="M862.048 926.048c0 19.2-15.136 34.336-34.304 34.336H92.352c-19.2 0-34.368-15.168-34.368-34.336V94.752c0-19.232 15.168-34.4 34.368-34.4h507.552v187.872c0 53.568 43.968 96.704 97.568 96.704h27.936l63.168-63.136h-91.104a34.144 34.144 0 0 1-34.4-34.4v-135.04l143.104 151.872 43.936-43.968L643.904 0.448H92.352C40.384 0.448 0.448 41.216 0.448 94.784V926.08c0 55.168 39.968 97.504 91.904 97.504h735.392c52.704 0 94.272-42.336 94.272-97.504V550.336l-60.704 64 0.736 311.712z"
                                                p-id="6374"></path>
                                            <path
                                                d="M1001.984 301.792l-21.6-21.6a79.84 79.84 0 0 0-48.736-23.2 71.68 71.68 0 0 0-56.704 21.568l-255.808 256.608-52.768 137.44c-9.6 24.8 14.4 48.8 39.232 39.968l139.872-50.336 256.576-256.576a73.344 73.344 0 0 0-0.064-103.872z m-144.704 61.536l59.968 59.168-163.872 164.672-59.904-60 163.808-163.84z m111.936 8l-18.4 18.4-59.936-59.168 18.4-18.4a25.184 25.184 0 0 1 18.336-7.2c7.232 0 13.6 2.4 18.368 7.2l23.232 23.2c4.768 4.8 7.136 11.168 7.136 18.4a23.456 23.456 0 0 1-7.136 17.568z m-309.376 189.408l60 59.936-95.904 35.2 35.904-95.136zM171.488 878.88h588.288c19.2 0 35.2-15.968 35.2-35.168s-16-35.168-35.2-35.168H171.488c-19.168 0-35.168 15.968-35.168 35.168s16 35.168 35.168 35.168z m0-187.04h278.176c19.168 0 35.136-16 35.136-35.168 0-19.2-15.968-35.2-35.136-35.2H171.488c-19.168 0-35.168 16-35.168 35.2 0 19.168 16 35.168 35.168 35.168z m278.176-266.176H171.488c-19.168 0-35.168 16-35.168 35.168 0 19.2 16 35.2 35.168 35.2h278.176c19.168 0 35.136-16 35.136-35.2 0-19.168-15.968-35.168-35.136-35.168zM171.488 293.792h278.176c19.168 0 35.136-16 35.136-35.2s-15.968-35.168-35.136-35.168H171.488c-19.168 0-35.168 16-35.168 35.168s16 35.2 35.168 35.2z"
                                                p-id="6375"></path>
                                        </svg></el-icon>
                                    查看每日一报
                                </el-menu-item>
                            </el-menu-item-group>
                        </el-sub-menu>
                        <el-sub-menu index="7">
                            <template #title>
                                <el-icon><office-building /></el-icon>社区信息编辑
                            </template>
                            <el-menu-item-group>
                                <el-menu-item index="editIntroduction">
                                    <el-icon>
                                        <tickets />
                                    </el-icon>社区简介编辑
                                </el-menu-item>
                                <el-menu-item index="editStruct">
                                    <el-icon>
                                        <svg t="1650628282139" class="icon" viewBox="0 0 1024 1024" version="1.1"
                                            xmlns="http://www.w3.org/2000/svg" p-id="5487" width="200" height="200">
                                            <path
                                                d="M130.093 351.89h766c17.673 0 32-14.327 32-32v-128c0-17.673-14.327-32-32-32h-766c-17.673 0-32 14.327-32 32v128c0 17.673 14.327 32 32 32z m32-128h702v64h-702v-64zM128.025 527.448H480.1v33.598c0 17.673 14.327 32 32 32 17.673 0 32-14.327 32-32v-33.598h346.399c17.673 0 32-14.327 32-32s-14.327-32-32-32H544.1v-30.24c0-17.673-14.327-32-32-32-17.673 0-32 14.327-32 32v30.24H128.025c-17.673 0-32 14.327-32 32s14.327 32 32 32zM287.55 639.802h-158c-17.673 0-32 14.327-32 32v160c0 17.673 14.327 32 32 32h158c17.673 0 32-14.327 32-32v-160c0-17.673-14.327-32-32-32z m-32 160h-94v-96h94v96zM592.093 639.802h-158c-17.673 0-32 14.327-32 32v160c0 17.673 14.327 32 32 32h158c17.673 0 32-14.327 32-32v-160c0-17.673-14.327-32-32-32z m-32 160h-94v-96h94v96zM896.637 639.802h-158c-17.673 0-32 14.327-32 32v160c0 17.673 14.327 32 32 32h158c17.673 0 32-14.327 32-32v-160c0-17.673-14.327-32-32-32z m-32 160h-94v-96h94v96z"
                                                fill="" p-id="5488"></path>
                                        </svg>
                                    </el-icon>党群结构编辑
                                </el-menu-item>
                                <el-menu-item index="editEmployee">
                                    <el-icon>
                                        <help />
                                    </el-icon>领导集体编辑
                                </el-menu-item>
                            </el-menu-item-group>
                        </el-sub-menu>
                    </el-menu>
                </el-scrollbar>
            </el-aside>
            <el-main>
                <el-breadcrumb separator=">" class="bread" v-if="this.$router.currentRoute._rawValue.fullPath != '/main'">
                    <el-breadcrumb-item :to="{ path: item.path }" v-for="item in list" :key="item">{{ item.name
                    }}</el-breadcrumb-item>
                    <!--                    <el-breadcrumb-item>{{this.$router.currentRoute._rawValue.name}}</el-breadcrumb-item>-->
                    <!--                    <el-breadcrumb-item>promotion list</el-breadcrumb-item>-->
                    <!--                    <el-breadcrumb-item>promotion detail</el-breadcrumb-item>-->
                    <el-divider border-style="double" />
                </el-breadcrumb>

                <div class="welcome" v-if="this.$route.matched.length == 1">
                    欢迎使用社区工作辅助系统
                </div>
                <router-view />
            </el-main>
        </el-container>
    </div>
</template>
<script  >
import { Help, Tickets, OfficeBuilding, List, Setting, Avatar, } from '@element-plus/icons-vue'
import { ref } from 'vue'
export default {
    methods: {
        loginOut() {
            localStorage.removeItem("jwt_token");
            this.$router.replace('/')
        },
        getNew() {
            this.router = this.$router.currentRoute._rawValue.name
        },
        init() {
        }
    },
    data() {
        return {
            username: "",
            router: '',
            value: ''
        }
    },
    created() {
        // if (localStorage.getItem("jwt_token") === "" || localStorage.getItem("jwt_token") === null) {
        //     this.$message.error('请先登录')
        //     this.$router.replace('/')
        //     return false;
        // }
        // this.username = window.sessionStorage.username
        // console.log(this.$route.matched.length)
        this.value = ref(new Date())
        this.init()
    },
    components: {
        Help,/* Fold, Expand,*/    Tickets, OfficeBuilding, List, Setting, Avatar,

    },
    computed: {
        list() {
            return this.$route.matched
        }
    }
}
</script>
<style scoped>
.el-header {
    position: relative;
    background-color: #b3c0d1;
    color: var(--el-text-color-primary);
}

.el-aside {
    width: 240px;
    color: var(--el-text-color-primary);
    background: #fff !important;
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
</style>