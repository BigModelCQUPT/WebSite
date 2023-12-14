<template>
    <div style="text-align: left">
        <el-space wrap>
            <el-card  class="box-card" style="width: 750px">
                <template #header>
                    <div  class="security-right-title">
                        <span class="security-right-title-icon"></span>
                        <span class="security-right-title-text">我的信息</span>
                    </div>
                </template>
                <div class="input-information">
                    <div class="el-form-item__content"                    >
                        <div class="el-input">
                            <label class="el-form-item__label">姓名:</label>
                            <input v-model="name" style="width: 425px" autocomplete="off" placeholder="请输入你的真实姓名" type="text" rows="2" maxlength="16" validateevent="true" class="el-input__inner"  >
                        </div>
                        <div class="el-input">
                            <label class="el-form-item__label">用户名:</label>
<!--                            <input style="width: 425px" autocomplete="off" placeholder="请输入你的昵称" type="text" rows="2" maxlength="16" validateevent="true" class="el-input__inner">-->
                            <label style="color: rgb(192, 192, 192);"> {{username}}</label>
                        </div>
                        <div class="el-input">
                            <label class="el-form-item__label">职位:</label>
                            <input v-model="position" style="width: 425px" autocomplete="off" placeholder="请输入你的职位" type="text" rows="2" maxlength="16" validateevent="true" class="el-input__inner"  >
                        </div>
                        <div class="el-input">
                            <span class="el-form-item__label">出生日期:</span>
                            <el-date-picker
                                    v-model="birth"
                                    type="date"
                                    placeholder="请选择日期"
                                    style="width: 425px"
                                    :disabled-date="disabledDate"
                            />
                        </div>
                        <div class="el-input">
                            <span class="el-form-item__label" style="padding-top: 5px">性别:</span>
                            <div>
                                <el-radio  v-model="radio1" label="1" size="large" border>男</el-radio>
                                <el-radio  v-model="radio1" label="2" size="large" border>女</el-radio>
                            </div>
                        </div>
                        <div class="el-input">
                            <label class="el-form-item__label">地址:</label>
                            <input v-model="address" style="width: 425px" autocomplete="off" placeholder="请输入你的家庭地址" type="text" rows="2" maxlength="16" validateevent="true" class="el-input__inner">
                        </div>
                        <div class="el-input">
                            <label class="el-form-item__label">身份证号码:</label>
                            <input v-model="id_num" style="width: 425px" autocomplete="off" placeholder="请输入身份证号码" type="text" rows="2" maxlength="18" validateevent="true" class="el-input__inner">
                        </div>
                        <div class="el-input">
                            <label class="el-form-item__label">电话号码:</label>
                            <input v-model="tel" style="width: 425px" autocomplete="off" placeholder="请输入你的电话号码" type="text" rows="2" maxlength="11" validateevent="true" class="el-input__inner">
                        </div>
                        <div class="el-input">
                            <label class="el-form-item__label">邮箱:</label>
                            <input  v-model="email" style="width: 425px" autocomplete="off" placeholder="请输入你的邮箱" type="text" rows="2" maxlength="16" validateevent="true" class="el-input__inner">
                        </div>
                    </div>
                    <div style="margin-top: 20px;text-align: center; " >
                        <el-button type="primary" @click="updateInformation">&nbsp;&nbsp;&nbsp; 提交 &nbsp;&nbsp;&nbsp;</el-button>
                    </div>
                </div>
            </el-card>
        </el-space>
    </div>
</template>

<script>
    import {ref} from 'vue'
    import axios from "axios";
    // const radio1 = ref('1')
    export default {
        name: "UpdateInformationView",
        data() {
            return {
                    birth: Date.now(),
                    radio1:ref('2'), //1 男 2女
                    username:'lisa',
                    address:'中国重庆',
                    name:"卢贤斌",
                    tel:"11313324111",
                    email:'1031284718@qq.com',
                    id_num:'111111111111111',
                    position:'区长'
            }

        },
        methods:{
            updateInformation() {
                if(window.sessionStorage.username == "" || this.name == "" || this.address == "" || this.position == "")
                {
                    this.$message.error('请填写完整信息');
                    return ;
                }
                var str = {
                    "username": window.sessionStorage.username.toString(),
                    "name":this.name,
                    "gender":  this.radio1 == "1" ? "男":"女",
                    "address" : this.address,
                    "tel" : this.tel,
                    "email" : this.email,
                    "position" : this.position,
                    "id_num":this.id_num
                }
                // JSON.stringify 将字符串转化为json格式的字符串，JSON.parse将这种字符串转化为json对象，可以用来传给后端
                // console.log(JSON.stringify(str) )
                // console.log(JSON.parse( JSON.stringify(str) ))
                const _this = this
                axios.post('http://localhost:8181/updateInformation', JSON.parse(JSON.stringify(str)) ).then(function (resp) {
                    if(resp.data.code == "200"){//返回成功
                        // console.log(resp.data)
                        _this.$message({
                            message: '修改成功',
                            type: 'success'
                        });
                        _this.$router.replace('/my')

                    }else{
                        console.log("error")
                    }
                })
            }
            },

        created() {
            // console.log(window.sessionStorage)
            const _this = this
            axios.post('http://localhost:8181/getInformation', window.sessionStorage).then(function (resp) {
                console.log(resp.data)
                if(resp.data.code == "200"){//返回成功
                    _this.name = resp.data.data.name
                    _this.address = resp.data.data.address
                    _this.tel = resp.data.data.tel
                    _this.email = resp.data.data.email
                    _this.id_num = resp.data.data.id_num
                    if (resp.data.data.gender == "男")
                        _this.radio1 = '1'
                    else
                        _this.radio1 = '2'
                    _this.position = resp.data.data.position
                }else if(resp.data.code == "101"){
                    _this.$message.error('请先登录');
                    return false;
                }else{
                    console.log("error")
                }
            })
        },
    }
</script>

<style scoped>

    .el-form-item__label {
        width: 95px;
        text-align: right;
        margin-right: 20px;
        float: left;
        line-height: 30px;
        padding: 0;
    }
    .el-form-item__content {
        line-height: 36px;
        position: relative;
        font-size: 14px;
    }
    .el-input {
        margin-top: 30px;
    }
    .el-input__inner {
        float: left;
        width: 225px;
        height: 30px;
        margin-right: 40px;
    }
    .information .username span{
        font-size: 20px;
        font-weight: 600;
        padding-right: 3px;
        color: #2a2626;
    }
    .security-right-title-icon {
        float: left;
        width: 4px;
        height: 16px;
        margin-top: 3px;
        margin-right: 5px;
        margin-bottom: 20px;
        background-color: #00a1d6;
        border-radius: 5px;
    }
     .security-right-title-text {
         margin: 0px 0px 20px 5px;
         color: #00a1d6;
         font-size: 15px;
     }
    .block {
        text-align: left;
        margin-top: 30px;
        text-align: center;
    }

</style>