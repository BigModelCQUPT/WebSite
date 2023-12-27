<!--修改密码-->
<template>
    <div>
        <div style="width: 60%; margin: auto">
            <el-form
                    :model="resetForm"
                    :rules="rules"
                    ref="resetForm"
                    status-icon
                    label-width="100px"
            >
                <el-form-item label="旧密码：" prop="password">
                    <el-input type="password" v-model="resetForm.password" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="新密码：" prop="new_password">
                    <el-input type="password" v-model="resetForm.new_password" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="确认密码：" prop="confirm_password">
                    <el-input type="password" v-model="resetForm.confirm_password" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item style="margin-left: 150px">
                    <el-button style="width: 160px" type="primary" @click="updatePassword">确认修改</el-button>
                </el-form-item>
            </el-form>
        </div>
    </div>
</template>

<script>
    import axios from 'axios'
    export default {
        name: "UpdatePasswordView",
        data() {
            return {
                resetForm: {
                        new_password: "",
                        password: "",
                        username: "",
                        confirm_password:''
                    },
                checked: true,
                rules: {
                    password: [{required: true, message: "请输旧密码", trigger: "blur"}],
                    new_password: [{required: true, message: "请输入新密码", trigger: "blur"}],
                    confirm_password: [{required: true, message: "请输入确认密码", trigger: "blur"}]
                }
            }
        },
        methods: {
            updatePassword( ){
                const _this = this
                this.resetForm.username = window.sessionStorage.username
                this.$refs.resetForm.validate((valid) => {
                    if (valid) {
                        if(_this.resetForm.new_password != _this.resetForm.confirm_password)
                        {
                            _this.$message.error('新密码和确认密码不相同');
                            return
                        }
<<<<<<< HEAD
                        axios.post('http://10.16.104.183:8181/updatePassword', _this.resetForm).then(function (resp) {
=======
                        axios.post('http://localhost:8181/updatePassword', _this.resetForm).then(function (resp) {
>>>>>>> ffc14b02a948452209c8504655d41786bdd0c07f
                                // console.log(resp)
                                if(resp.data.code == "200"){//返回成功
                                    _this.$message({
                                        message: '修改成功',
                                        type: 'success'
                                    });
                                    _this.$router.replace('/my')
                                }else if(resp.data.code == "101"){
                                    _this.$message.error('密码错误');
                                    return false;
                                }
                            })
                    } else {
                        _this.$message.error('请输入所有字段');
                        return false;
                    }
                });
            }
        }
    }
</script>

<style scoped>

</style>