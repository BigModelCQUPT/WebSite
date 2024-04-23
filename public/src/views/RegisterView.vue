<!--注册界面-->
<template>
    <div class="zuiwaiceng">
        <div class="mod-new-reg-text">
            <h3 class="max-text">大模型分析</h3>
        </div>
        <div class="mylogin">
            <h3>欢迎注册</h3>
            <p class="login">
                已有账号?
                <span @click="login" style="color: #3399ff">登录</span>
            </p>

            <el-form :model="registerForm" :rules="registerRules" ref="registerForm" label-width="0px">
                <el-form-item prop="username" style="margin-top:10px; margin-left: 50px ">
                    <label style="margin-left: -50px; margin-right: 15px">用户名 &nbsp; &nbsp;</label>
                    <el-row>
                        <el-col :span='25'>
                            <el-input class="inps" placeholder='请设置用户名' v-model="registerForm.username">
                            </el-input>
                        </el-col>
                    </el-row>
                </el-form-item>
                <el-form-item prop="password" style="margin-left: 50px">
                    <label style="margin-left: -50px; margin-right: 20px">密 &nbsp;&nbsp;码 &nbsp;&nbsp;</label>
                    <el-row>
                        <el-col :span='25'>
                            <el-input class="inps" type="password" placeholder='请设置登录密码'
                                v-model="registerForm.password"></el-input>
                        </el-col>
                    </el-row>
                </el-form-item>
                <el-form-item prop="password" style="margin-left: 50px">
                    <label style="margin-left: -50px; margin-right: 17px">重复密码</label>
                    <el-row>
                        <el-col :span='25'>
                            <el-input class="inps" type="password" placeholder='请再次输入密码'
                                v-model="registerForm.confirmedPassword"></el-input>
                        </el-col>
                    </el-row>
                </el-form-item>
                <el-form-item prop="useremail" style="margin-top:10px; margin-left: 50px ">
                    <label style="margin-left: -50px; margin-right: 28px">邮箱 &nbsp; &nbsp;</label>
                    <el-row>
                        <el-col :span='25'>
                            <el-input class="inps" placeholder='请输入邮箱' v-model="registerForm.useremail">
                            </el-input>
                        </el-col>
                    </el-row>
                </el-form-item>
                <el-form-item style="margin-top:25px;">
                    <el-button type="primary" round class="submitBtn" @click="submitForm">注册
                    </el-button>
                </el-form-item>
            </el-form>
        </div>
    </div>
</template>



<script>

// import {postRequest} from "@/utils/api";
import axios from 'axios'
export default {
    name: "RegisterView",
    data() {
        return {
            registerForm: {
                username: '',
                password: '',
                confirmedPassword: '',
                useremail: '',
            },
            checked: true,
            registerRules: {
                username: [{ required: true, message: "请输入账号", trigger: "blur" }],
                password: [{ required: true, message: "请输入密码", trigger: "blur" }],
                confirmedPassword: [{ required: true, message: "请输入密码", trigger: "blur" }]
            }
        }
    },
    methods: {
        submitForm() {
            const _this = this
            this.$refs.registerForm.validate((valid) => {
                if (valid) {
                    console.log(this.registerForm)
                    axios.post('http://localhost:8181/user/account/register', this.registerForm).then(function (resp) {
                        if (resp.data.data.error_message === "success") {//返回成功
                            _this.$message({
                                message: '注册成功',
                                type: 'success'
                            });
                            _this.$router.replace('/')
                        } else if (resp.data.code == "101") {
                            _this.$message.error('账号已存在');
                            // return false;
                        }
                        else {
                            const error = resp.data.data.error_message
                            _this.$message.error(error);
                        }
                    })
                } else {
                    this.$message.error('请输入所有字段');
                    return false;
                }
            });
        },
        login() {//跳转到登录
            this.$router.replace('/')
        }
    }
}
</script>


<style scoped>
.zuiwaiceng {
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

.mylogin h3 {
    margin: 10px 160px 0px 0px;
    color: #fff;
    font-size: 30px;
}

.login {
    margin: 5px 0px 30px -190px;
    /*上右下左*/
    font-size: 14px;
    color: #9B9B96;


}

.inps input {
    vertical-align: middle;
    border: none;
    color: #fff;
    background-color: transparent;
    font-size: 12px;
}

.submitBtn {
    margin-top: 15px;
    background-color: transparent;
    color: #39f;
    width: 260px;
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
</style>