<!--忘记密码进行修改-->
<template>
    <div>
        <el-form :model="ruleForm" ref="ruleForm" :rules="rules" class="loginContainer">
            <h3 class="loginTitle">修改密码</h3>
            <el-form-item prop="username" style="margin-top: -5px">
                <label>用 户 名</label>
                <el-input style="margin-top: -5px" type="text" v-model="ruleForm.username" autocomplete="off"
                    placeholder="请输入用户名"></el-input>
            </el-form-item>
            <el-form-item prop="id_num" style="margin-top: -5px">
                <label>身份证号码</label>
                <el-input style="margin-top: -5px" type="text" v-model="ruleForm.id_num" autocomplete="off"
                    placeholder="请输入身份证号码"></el-input>
            </el-form-item>
            <el-form-item prop="new_password" style="margin-top: -5px">
                <label>新 密 码</label>
                <el-input style="margin-top: -5px" type="password" v-model="ruleForm.new_password" autocomplete="off"
                    placeholder="请输入新密码"></el-input>
            </el-form-item>
            <el-button type="primary" style="width: 100%" @click="submitUpdate">提交修改</el-button>
        </el-form>
    </div>
</template>

<script>
    import axios from 'axios'
    export default {
        name: "ForgetAccountView",
        data() {
            return {
                ruleForm: {
                    username: 'lisa',
                    new_password: '',
                    id_num: '411522'
                },
                checked: true,
                rules: {
                    username: [{
                        required: true, message: '请输入用户名', trigger:
                            'blur'
                    }],
                    id_num: [{
                        required: true, message: '请输入身份证号码', trigger: 'blur'

                    }],
                    new_password: [{
                        required: true, message: '请输入新密码', trigger:
                            'blur'
                    }]
                }
            }
        },
        methods: {
            //提交修改
            submitUpdate() {
                const _this = this
                _this.$refs.ruleForm.validate((valid) => {
                    console.log(_this.ruleForm)
                    if (valid) {
                        axios.post('http://10.16.104.183:8181/forgetAccount', this.ruleForm).then(function (resp) {
                            if (resp.data.code == "200") {
                                _this.$message({
                                    message: '修改成功',
                                    type: 'success'
                                });
                                _this.$router.replace('/')
                            } else if (resp.data.code == "101") {
                                console.log(resp)
                                _this.$message.error('信息输入有误');
                            }
                        })
                    } else {
                        this.$message.error('请输入所有字段');
                        return false;
                    }
                })
            }
        }
    }
</script>

<style scoped>
    .loginContainer {
        border-radius: 15px;
        background-clip: padding-box;
        margin: 100px auto;
        width: 250px;
        padding: 15px 35px 15px 35px;
        background: #fff;
        border: 1px solid #eaeaea;
        box-shadow: 0 0 25px #cac6c6;
    }

    .loginTitle {
        margin: 0 auto 30px auto;
        text-align: center;
        color: #505458;
    }

    .input {}
</style>