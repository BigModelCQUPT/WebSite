<template>
    <div style="margin-top: -25px">
        <el-row>
            <el-form ref="formData" :model="formData" :rules="rules" style="margin-left: 200px;">
                <el-form-item prop="theme" style="width: 500px; " label="会议主题" class="el-item">
                    <el-input placeholder='请输入会议主题' v-model="formData.theme"></el-input>
                </el-form-item>
                <el-form-item prop="address" style="width: 500px;" label="会议地址" class="el-item">
                    <el-select v-model="formData.address" placeholder="请选择会议地址" style="width: 500px;">
                        <el-option v-for="item in options" :key="item.value" :value="item.value" />
                    </el-select>
                </el-form-item>
                <el-form-item style="margin-top: 0px" prop="time" label=" ">
                    <div>
                        <span style="margin-left: -12px; margin-right: 12px;">会议时间</span>
                        <el-date-picker v-model="formData.date" placeholder="请选择日期" type="date"
                            value-format="YYYY-MM-DD" style="width: 135px" />
                    </div>
                    <div>
                        <el-time-picker v-model="formData.time" is-range range-separator="To" start-placeholder="开始时间"
                            end-placeholder="结束时间" style="width: 280px;margin-left: 10px; margin-top: 3px" />
                    </div>
                </el-form-item>
                <el-form-item prop="member" label="参会人员">
                    <el-input v-model="formData.member" :autosize="{ minRows: 4, maxRows: 8 }" type="textarea"
                        placeholder="请输入参会人员" style="margin-top: 8px" />
                </el-form-item>
                <el-form-item prop="content" label="主要议程">
                    <el-input v-model="formData.content" :autosize="{ minRows: 6, maxRows: 8 }" type="textarea"
                        placeholder="请输入会议主要议程" style="margin-top: 8px" />
                </el-form-item>
                <el-form-item>
                    <el-button style="margin-left: 110px; width: 300px" type="primary"
                        @click="submitForm">立即预订</el-button>
                </el-form-item>
            </el-form>
        </el-row>
    </div>
</template>

<script>
    import { ref } from 'vue'
    import axios from "axios";
    export default {
        name: "BookMeetingView",
        methods: {
            submitForm() {
                // if(this.formData.address == "")
                //     alert(1111)
                // console.log(this.formData.time)
                // console.log(this.formData.date)

                const _this = this
                this.$refs.formData.validate((valid) => {
                    if (valid) {
                        this.formData.start_time = this.formData.time[0].toString().substring(16, 21)
                        this.formData.end_time = this.formData.time[1].toString().substring(16, 21)
                        // this.formData.name =
                        axios.post('http://10.16.104.183:8181/bookMeeting', this.formData).then(function (resp) {
                            // console.log(resp)
                            if (resp.data.code == "200") {//返回成功
                                _this.$message({
                                    message: '会议预订成功',
                                    type: 'success'
                                });
                                _this.$router.replace('/bookHistory')
                            } else if (resp.data.code == "101") {
                                _this.$message.error('预订失败');
                                // return false;
                            }
                        })
                    } else {
                        this.$message.error('请输入所有字段');
                        return false;
                    }
                });
            },
            getNew() {

            }
        },
        data() {
            return {
                options: [
                    { value: '1楼会议室' },
                    { value: '2楼大讲堂' },
                    { value: '3楼报告厅' }
                ],
                formData: {
                    theme: window.sessionStorage.username + "预订的会议",
                    address: ref(''),
                    content: '',
                    member: '',
                    time: '',
                    start_time: '',
                    end_time: '',
                    date: ''
                },
                checked: true,
                rules: {
                    theme: [{ required: true, message: "请输入会议名称", trigger: "blur" }],
                    address: [{ required: true, message: "请输入会议地址", trigger: "blur" }],
                    time: [{ required: true, message: "", trigger: "blur" }],
                    member: [{ required: true, message: "请输入参会人员", trigger: "blur" }],
                    content: [{ required: true, message: "请输入会议主要议程", trigger: "blur" }]
                }
            }
        },
        created() {
            console.log(this.$route.matched)

        },

    }

</script>

<style scoped>
    .el-item {
        margin-top: 20px;
    }
</style>