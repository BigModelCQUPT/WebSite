<!--每日一报-->
<template>
    <div style="text-align: left; margin-top: -25px; margin-left: 9px">
        <el-space wrap>
            <el-card  style="width: 750px;">
                <div class="card-header">
                    <el-icon class="title"><tickets /></el-icon>
                    <span class="title-span">申报人信息</span>
                </div>
                <el-divider />
                <div class="el-input">
                    <label class="el-form-item__label">姓名:</label>
                    <label style="color: rgb(192, 192, 192);"> {{name}}</label>
                </div>
                <div class="el-input">
                    <label class="el-form-item__label">身份证号码:</label>
                    <label style="color: rgb(192, 192, 192);"> {{id_no}}</label>
                </div>
                <div class="el-input">
                    <label class="el-form-item__label">填报日期:</label>
                    <label style="color: rgb(192, 192, 192);"> {{date}}</label>
                </div>
                <div class="el-input">
                    <label class="el-form-item__label">地址:</label>
                    <label style="color: rgb(192, 192, 192);"> {{address}}</label>
                </div>
                <div class="el-input">
                    <label class="el-form-item__label">详细地址:</label>
                    <el-input style="margin-top: 0px; width: 400px" v-model="input" placeholder="请输入所在位置的详细地址" />
                    <!--                    <label style="color: rgb(192, 192, 192);"> {{}}</label>-->
                </div>
                <el-divider />
                <div style="margin-top: 30px">
                    <el-icon class="title"><tickets /></el-icon>
                    <span class="title-span">健康状况</span>
                </div>
                <el-divider />
                <div style="margin-left: 20px;">
                    <label style=" font-size: 20px">个人体温状况</label>
                    <br>
                    <el-radio border class="check2-content" v-model="radio1" label="正常37.3℃以下" size="large" />
                    <el-radio border class="check2-content" v-model="radio1" label="低温37.3~37.9℃" size="large " />
                    <el-radio border class="check2-content" v-model="radio1" label="中热38~39℃" size="large" />
                    <el-radio border class="check2-content" v-model="radio1" label="高热39℃以上" size="large" />
                </div>
                <div style="margin-left: 20px;">
                    <label style=" font-size: 20px">是否有下列情况</label>
                    <br>
                    <el-checkbox border class="check1-content" v-model="checked1" label="14天内曾居住或前往疫情高发地" size="large" />
                    <el-checkbox border class="check1-content" v-model="checked2" label="两周内有与确诊患者接触" size="large" />
                    <el-checkbox border class="check1-content" v-model="checked4" label="喘憋、呼吸急促" size="large" />
                    <el-checkbox border class="check1-content" v-model="checked5" label="恶心呕吐、腹泻" size="large" />
                    <el-checkbox border class="check1-content" v-model="checked6" label="心慌、胸闷" size="large" />
                    <el-checkbox border class="check1-content" v-model="checked3" label="感冒样症状:乏力、精神差、咳嗽、发烧、肌肉痛、头痛" size="large" />
                </div>
                <el-divider />
                <label  class="radio-label" >本人承诺以上提供的信息真实准确，没有瞒报漏报。如有不实，本人愿承担由此引起的一切后果及法律责任。</label>
                <br>
                <el-radio  v-model="radio" label="true" size="large">我已知晓</el-radio>
                <div style="margin-top: 10px; text-align: center;" >
                    <el-button style="width: 300px" type="primary" @click="sub">提交</el-button>
                </div>
            </el-card>
        </el-space>
    </div>
</template>
<script>
    import { Tickets  } from '@element-plus/icons-vue'
    import axios from "axios";
    export default {
        name: "ReportDailyView",
        methods:{
            sub(){
                if(this.radio == 'true')
                {
                    if(this.input == '')
                        this.$message.error('请输入详细地址');
                    else if(this.radio1 == '')
                        this.$message.error('请选择体温');
                    else {
                        var content = ''
                        if(this.checked1.toString() == 'true') {
                            content += '14天内曾居住或前往疫情高发地'
                        }
                        if(this.checked2.toString() == 'true') {
                            content += '、'
                            content += '两周内有与确诊患者接触'
                        }
                        if(this.checked4.toString() == 'true') {
                            content += '、'
                            content += '喘憋、呼吸急促'
                        }
                        if(this.checked5.toString() == 'true') {
                            content += '、'
                            content += '恶心呕吐、腹泻'
                        }
                        if(this.checked6.toString() == 'true') {
                            content += '、'
                            content += '心慌、胸闷'
                        }
                        if(this.checked3.toString() == 'true') {
                            content += '、'
                            content += '感冒样症状:乏力、精神差、咳嗽、发烧、肌肉痛、头痛'
                        }
                        var time = new Date()
                        var str = {
                            "name": this.name,
                            "id_no": this.id_no,
                            "temperature":this.radio1,
                            "status":content,
                            "address" : '浙江省杭州市临安区' + this.input,
                            "datetime" : this.date + '  ' + time.getHours() + ':' + time.getMinutes() + ':' + time.getSeconds()
                        }
                        console.log(str)

                        // JSON.stringify 将字符串转化为json格式的字符串，JSON.parse将这种字符串转化为json对象，可以用来传给后端
                        // console.log(JSON.stringify(str) )
                        // console.log(JSON.parse( JSON.stringify(str) ))
                        const _this = this
<<<<<<< HEAD
                        axios.post('http://10.16.104.183:8181/reportDaily', JSON.parse(JSON.stringify(str)) ).then(function (resp) {
=======
                        axios.post('http://localhost:8181/reportDaily', JSON.parse(JSON.stringify(str)) ).then(function (resp) {
>>>>>>> ffc14b02a948452209c8504655d41786bdd0c07f
                            if(resp.data.code == "200"){//返回成功
                                // console.log(resp.data)
                                _this.$message({
                                    message: '提交成功',
                                    type: 'success'
                                });
                                // _this.$router.replace('/main')
                            }else{
                                console.log("error")
                            }
                        })
                    }
                }else {
                    // console.log(this.radio)
                    this.$message.error('请勾选我已知晓');
                }
            },
            init() {
                const _this = this
<<<<<<< HEAD
                axios.get('http://10.16.104.183:8181/getInforByUsername/' + window.sessionStorage.username).then(function (resp) {
=======
                axios.get('http://localhost:8181/getInforByUsername/' + window.sessionStorage.username).then(function (resp) {
>>>>>>> ffc14b02a948452209c8504655d41786bdd0c07f
                    // console.log(resp.data)
                    if(resp.data.code == "200"){//返回成功
                        _this.name = resp.data.data.name
                        _this.id_no = resp.data.data.id_num
                    }else if(resp.data.code == "101"){
                        _this.$message.error('请先登录');
                        return false;
                    }
                })
            }
        },
        created() {
            var  date = new Date()
            var year =  date.getFullYear()
            var month =  date.getMonth() + 1 // 月份要加1
            var day = date.getDate()
            if(month < 10)
                month = '0' + month
            if (day < 10)
                day = '0' + day

            this.date = year + '-' + month+ '-' + day
            this.init()
        },
        data() {
            return{
                name:'',
                username:'',
                id_no:'',
                date : '',
                address:'浙江省杭州市临安区',
                input : '',
                radio:'',
                radio1:'',
                checked1:'',
                checked2:'',
                checked3:'',
                checked4:'',
                checked5:'',
                checked6:''
            }
        },
        components:{
            Tickets
        }


    }
</script>

<style scoped>
    .title{
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
    .information .username span {
        font-size: 20px;
        font-weight: 600;
        padding-right: 3px;
        color: #2a2626;
    }
    .el-input {
        font-size: 20px;
        margin-top: 10px;
    }
    .el-form-item__label {
        width: 125px;
        text-align: right;
        margin-right: 20px;
        float: left;
        line-height: 30px;
        padding: 0;
    }
    .check1-content {
        margin-top: 10px;
    }
    .check2-content {
        margin-top: 10px;
        width: 150px;
    }
    .radio-label {
        color: crimson;
    }
</style>