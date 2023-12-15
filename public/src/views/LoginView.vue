<!--登录界面-->
<template>
    <div class="outside">
        <div class="mod-new-reg-text">
            <h3 class="max-text">大模型分析</h3>
            <!--            <p class="small-text">让社区工作更简单</p>-->
        </div>
        <div class="mylogin">
            <h4 class="loginTitle">系统登录</h4>
            <el-form :model="loginForm" :rules="loginRules" ref="loginForm" label-width="0px">
                <el-form-item prop="username" style="margin-top:10px; margin-left: 50px ">
                    <el-row>
                        <el-col :span='22'>
                            <el-input class="inps" placeholder='账号' v-model="loginForm.username"
                                @keydown.enter="submitForm">
                            </el-input>
                        </el-col>
                    </el-row>
                </el-form-item>
                <el-form-item prop="password" style="margin-left: 50px">
                    <el-row>
                        <el-col :span='22'>
                            <el-input class="inps" type="password" placeholder='密码' v-model="loginForm.password"></el-input>
                        </el-col>
                    </el-row>
                </el-form-item>
                <el-form-item style="margin-top:30px;">
                    <el-button type="primary" round class="submitBtn" @click="submitForm" style="margin-left: 40px">登录
                    </el-button>
                </el-form-item>
                <div class="unlogin">
                    <router-link :to="{ path: '/forgetAccount' }">
                        <span style="color: #3399ff">忘记密码?</span>
                    </router-link>
                    |
                    <router-link :to="{ path: '/register' }">
                        <a href="register.vue" target="_blank" align="right">
                            <span style="color: #3399ff">注册新账号</span>
                        </a>
                    </router-link>
                </div>
            </el-form>
        </div>
    </div>
</template>

<script>

// import {postRequest} from "@/utils/api";
import axios from 'axios'
export default {
    name: "LoginView",
    data() {
        return {
            loginForm: {
                username: '',
                password: ''
            },
            checked: true,
            loginRules: {
                username: [{ required: true, message: "请输入账号", trigger: "blur" }],
                password: [{ required: true, message: "请输入密码", trigger: "blur" }]
            }
        }
    },
    methods: {
        submitForm() {
            const _this = this
            // console.log(this.loginForm)
            this.$refs.loginForm.validate((valid) => {
                if (valid) {
                    axios.post('http://localhost:8181/user/account/login', this.loginForm).then(function (resp) {
                        // console.log(resp)
                        if (resp.data.error_message === "success") {//返回成功
                            _this.$message({
                                message: '登录成功',
                                type: 'success'
                            });
                            localStorage.setItem("jwt_token", resp.data.jwt_token);
                            // window.sessionStorage.setItem('username', resp.data.data.username); //将传回来的信息存放在session里面
                            // var tmp = {
                            //     "username": resp.data.data.name,
                            //     "head": resp.data.data.img
                            // }
                            // sessionStorage.setItem("head_img", resp.data.data.img)
                            // window.sessionStorage.setItem('user', JSON.stringify(tmp))
                            // console.log( JSON.parse( sessionStorage.user))
                            _this.$router.replace('/main')
                        } else if (resp.data.code == "101") {
                            _this.$message.error('账号或者密码错误');
                            // return false;
                        }
                    })
                } else {
                    this.$message.error('请输入所有字段');
                    return false;
                }
            });
        },
    }
}
</script>

<style scoped>
.outside {
    /*width: 100vw;*/
    height: 100vh;
    font-size: 16px;
    background-position: left top;
    background-color: #242645;
    color: #fff;
    font-family: "Source Sans Pro";
    position: relative;
    background-image: url('../assets/background.jpg');
    background-repeat: repeat;
    background-size: 100% 100%;
}

.mylogin {
    width: 280px;
    height: 320px;
    position: absolute;
    top: 0;
    left: 650px;
    right: 0;
    bottom: 0;
    margin: auto;
    padding: 50px 40px 40px 40px;
    box-shadow: -15px 15px 15px rgba(6, 17, 47, 0.7);
    opacity: 1;
    background: linear-gradient(230deg,
            rgba(53, 57, 74, 0) 0%,
            rgb(0, 0, 0) 100%);

}

.loginTitle {
    margin: 10px auto 20px auto;
    text-align: center;
    color: #fff;
    font-size: 25px;
}

.inps input {
    vertical-align: middle;
    border: none;
    color: #fff;
    background-color: transparent;
    font-size: 12px;
}

.submitBtn {
    background-color: transparent;
    color: #39f;
    width: 200px;
}

.mod-new-reg-text {

    position: fixed;
    left: 155px;
    top: 214px;
    color: #fff;
    font-size: 25px;
}

.max-text {
    line-height: 10px;
    text-align: left;
    font-size: 45px;
    margin-bottom: 20px;
}

.small-text {
    line-height: 20px;
    text-align: left;
}

.unlogin {
    /*color:;*/
    color: aqua;
}
</style>