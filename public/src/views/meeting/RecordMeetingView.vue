<template>
    <div style="margin-top: -10px">
<!--        <div><h3>会议记录</h3></div>-->
        <el-form
                ref="meetingForm"
                :model="meetingForm"
                :rules="rules"
                label-width="120px"
                class="demo-ruleForm"
                style="width: 70%; margin: auto"
        >
            <el-form-item label="会议主题" prop="theme">
                <el-input v-model="meetingForm.theme"  placeholder="请输入会议主题"/>
            </el-form-item>
            <el-form-item label="会议地点" prop="address">
                <el-select v-model="meetingForm.address" placeholder="请选择会议地点">
                    <el-option label="1号会议室" value="1号会议室" />
                    <el-option label="党员之家" value="党员之家" />
                    <el-option label="2楼大报告厅" value="2楼大报告厅" />
                    <el-option label="大讲堂" value="大讲堂" />
                    <el-option label="小办公室" value="小办公室" />
                </el-select>
            </el-form-item>
            <el-form-item label="会议日期" required>
                <el-col >
                    <el-form-item prop="date">
                        <el-date-picker
                                v-model="meetingForm.date"
                                value-format="YYYY-MM-DD"
                                type="date"
                                placeholder="请选择会议时间"
                                style="width: 220px; text-align: center"
                        />
                    </el-form-item>
                </el-col>
            </el-form-item>
            <el-form-item label="会议主持人" prop="host">
                <!--                <el-switch v-model="meetingForm.host" />-->
                <el-input v-model="meetingForm.host" style="width: 220px" />
            </el-form-item>
            <el-form-item label="会议记录员" prop="edit">
                <el-input v-model="meetingForm.edit" style="width: 220px" />
            </el-form-item>
            <el-form-item label="到场人数" >
                <el-col :span="9" style="margin-left: -55px">
                    <el-input-number
                            v-model="meetingForm.number_of_arrive"
                            style="width: 50%"
                            prop="number_of_arrive"
                    />
                </el-col>
                <el-col   :span="3"  style="margin-left: -45px" >缺席人数</el-col>
                <el-col :span="9" style="margin-left: -50px">
                    <el-input-number
                            v-model="meetingForm.number_of_not_arrive"
                            style="width: 50%"
                            prop="number_of_not_arrive"
                    />
                </el-col>
            </el-form-item>
            <el-form-item label="参会名单" prop="member">
                <el-input v-model="meetingForm.member" type="textarea" rows="4" />
            </el-form-item>
            <el-form-item label="会议内容" prop="content">
                <el-input v-model="meetingForm.content" type="textarea" rows="6" />
            </el-form-item>
            <el-form-item label="备注" prop="note">
                <el-input v-model="meetingForm.note" type="textarea" rows="2" />
            </el-form-item>
            <el-form-item >
                <el-button style="width: 200px; margin: auto" type="primary" @click="submitForm"
                >提交</el-button
                >
            </el-form-item>
        </el-form>
    </div>
</template>




<script>
    import {ref} from 'vue'
    import axios from "axios";

    export default {
        name:"RecordMeetingView",
        data(){
            return {
                meetingForm:{
                    theme: '',
                    address: '',
                    date: ref(),
                    host: '',
                    content: '',
                    note: '无',
                    number_of_arrive: '10',
                    number_of_not_arrive: '0',
                    member:'',
                    edit:''
                },
                checked:true,
                rules: {
                    theme: [{ required: true, message: '请输入会议主题', trigger: 'blur' },],
                    address: [{required: true, message: '请选择会议地点', trigger: 'blur',},],
                    date: [{type: 'date', required: true, message: '请选择日期', trigger: 'change',},],
                    host: [{required: true, message: '请输入会议主持人', trigger: 'blur',},],
                    content: [{required: true, message: '请输入会议主要内容', trigger: 'blur',},],
                    member: [{ required: true, message: '请输入参会人员名单', trigger: 'blur' },],
                    edit: [{required: true, message: '请输入会议记录员', trigger: 'blur',},],
                }
            }
        },
        methods:{
            submitForm() {
                    const _this = this
                    // console.log(this.meetingForm.address)
                    this.$refs.meetingForm.validate((valid) => {
                        if (valid) {
                            _this.meetingForm.address =
<<<<<<< HEAD
                            axios.post('http://10.16.104.183:8181/recordMeeting', _this.meetingForm).then(function (resp) {
=======
                            axios.post('http://localhost:8181/recordMeeting', _this.meetingForm).then(function (resp) {
>>>>>>> ffc14b02a948452209c8504655d41786bdd0c07f
                                console.log(resp)
                                if(resp.data.code == "200"){//返回成功
                                    _this.$message({
                                        message: '提交成功',
                                        type: 'success'
                                    });
                                    _this.$router.replace('/historyMeeting')
                                }else if(resp.data.code == "101"){
                                    _this.$message.error('出现未知错误');
                                    return false;
                                }
                            })
                        } else {
                            _this.$message.error('请输入所有字段');
                            return false;
                        }
                    });
                },
            init() {
                // console.log(this.$route.query.id)
                if (this.$route.query.id) {
                    var id = this.$route.query.id
                    const _this = this
<<<<<<< HEAD
                    axios.get('http://10.16.104.183:8181/getMeetingById/' + id).then(function (resp) {
=======
                    axios.get('http://localhost:8181/getMeetingById/' + id).then(function (resp) {
>>>>>>> ffc14b02a948452209c8504655d41786bdd0c07f
                        if (resp.data.code == "200") {//返回成功
                            // console.log(resp.data.data)
                            _this.meetingForm = resp.data.data
                        } else {
                            console.log("error")
                        }
                    })
                }
            }
        },
        created() {
            this.init()
        }
    }

</script>

<style >

</style>

