<!--添加民情走访-->
<template>
    <div style="margin-top: -25px">
        <h3>民情走访</h3>
        <el-form ref="meetingForm" :model="meetingForm" :rules="rules" label-width="120px" class="demo-ruleForm"
            style="width: 70%; margin: auto">
            <el-form-item label="姓名" prop="name">
                <el-select v-model="meetingForm.name" placeholder="请选择工作人员姓名" style="width: 660px">
                    <el-option label="高允真" value="高允真" />
                    <el-option label="张无忌" value="张无忌" />
                    <el-option label="梁朝伟" value="梁朝伟" />
                    <el-option label="卢淑贤" value="卢淑贤" />
                    <el-option label="王正国" value="王正国" />
                    <el-option label="马晓丽" value="马晓丽" />
                </el-select>
            </el-form-item>
            <el-form-item label="走访时间" required>
                <el-col>
                    <el-form-item prop="date">
                        <el-date-picker v-model="meetingForm.date" value-format="YYYY-MM-DD" type="date"
                            placeholder="请选择走访时间" style="width: 620px; text-align: center" />
                    </el-form-item>
                </el-col>
            </el-form-item>
            <el-form-item label="走访地点" prop="address">
                <el-input v-model="meetingForm.address" style="width: 620px; " />
            </el-form-item>
            <el-form-item label="主要问题和诉求" prop="problem">
                <el-input v-model="meetingForm.problem" style="width: 620px" />
            </el-form-item>
            <el-form-item label="走访人数">
                <el-col>
                    <el-input-number v-model="meetingForm.number" style="width: 100%" prop="number" />
                </el-col>
            </el-form-item>
            <el-form-item label="是否解决" prop="radio">
                <el-radio style="margin-top: -5px" v-model="meetingForm.flag" label="1" size="large">已解决</el-radio>
                <el-radio style="margin-top: -5px" v-model="meetingForm.flag" label="2" size="large">未解决</el-radio>
                <el-radio style="margin-top: -5px" v-model="meetingForm.flag" label="3" size="large">解决中</el-radio>
            </el-form-item>
            <el-form-item label="解决情况" prop="solve_condition">
                <el-input v-model="meetingForm.solve_condition" placeholder="请简要输入问题解决情况" type="textarea" rows="4" />
            </el-form-item>
            <el-form-item label="备注" prop="note">
                <el-input v-model="meetingForm.note" type="textarea" rows="2" />
            </el-form-item>
            <el-form-item>
                <el-button style="width: 200px; margin: auto; border-radius: 20px" type="primary"
                    @click="submitForm">提交</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>




<script>
    // import {ref} from 'vue'
    import axios from "axios";

    export default {
        name: "AddCommunityConditionView",
        data() {
            return {
                meetingForm: {
                    name: '',
                    address: '',
                    date: '',
                    problem: '',
                    number: '5',
                    note: '',
                    solve_condition: '',
                    flag: ''
                },

                checked: true,
                rules: {
                    name: [{ required: true, message: '请选择工作人员姓名', trigger: 'blur' },],
                    address: [{ required: true, message: '请输入会议地点', trigger: 'blur', },],
                    date: [{ type: 'date', required: true, message: '请选择日期', trigger: 'change', },],
                    problem: [{ required: true, message: '请输入主要问题和诉求', trigger: 'blur', },],
                    content: [{ required: true, message: '请输入会议主要内容', trigger: 'blur', },],
                    solve_condition: [{ required: true, message: '请输入解决情况', trigger: 'blur' },],

                }
            }
        },
        methods: {
            submitForm() {
                const _this = this
                // console.log(this.meetingForm)
                this.$refs.meetingForm.validate((valid) => {
                    if (valid) {
                        axios.post('http://10.16.104.183:8181/communityCondition/add', _this.meetingForm).then(function (resp) {
                            // console.log(resp)
                            if (resp.data.code == "200") {//返回成功
                                _this.$message({
                                    message: '提交成功',
                                    type: 'success'
                                });
                                _this.$router.replace('/communityCondition')
                            } else if (resp.data.code == "101") {
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
        }
    }

</script>

<style>

</style>