<template>
    <div style="width: 70%; margin: auto">
        <el-form :model="formData" label-width="120px" :rules="rulesForm" ref="formData">
            <el-form-item label="支出项目" prop="output">
                <el-input v-model="formData.output" />
            </el-form-item>
            <el-form-item label="支出日期" prop="date">
                <el-col :span="10">
                    <el-date-picker v-model="formData.date" type="date" placeholder="选择日期" style="width: 100%"
                        value-format="YYYY-MM-DD" />
                </el-col>
                <el-col :span="14">
                    <el-form-item label="支出金额" prop="output_number">
                        <el-input v-model="formData.output_number" />
                    </el-form-item>
                </el-col>
            </el-form-item>
            <el-form-item label="支出类型" prop="radio">
                <el-radio-group v-model="formData.radio">
                    <el-radio label="居委会办公支出" />
                    <el-radio label="日常活动支出" />
                    <el-radio label="公共区域设备能耗费用" />
                    <el-radio label="购买社区公共设备" />
                    <el-radio label="疫情期间防疫活动支出" />
                    <el-radio label="社区公共植物栽培以及养护" />
                    <el-radio label="公共设备维修、保养费用" />
                    <el-radio label="其他支出" />
                </el-radio-group>
            </el-form-item>
            <el-form-item label="备注" prop="note">
                <el-input v-model="formData.note" type="textarea" placeholder="其他需要记录的内容" />
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="onSubmit">提交</el-button>
                <el-button>取消</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>
<script>
    import axios from "axios";
    export default {
        name: 'OutputFinanceView',
        data() {
            return {
                formData: {
                    name: null,
                    date: null,
                    radio: null,
                    note: null,
                    input_number: 0,
                    input: null,
                    input_type: null,
                    output: null,
                    output_type: null,
                    output_number: 0
                },
                checked: true,
                rulesForm: {
                    output: [{ required: true, message: '请输入支出项目内容', trigger: 'blur' },],
                    output_number: [{ required: true, message: '请输入支出金额', trigger: 'blur' },],
                    date: [{ type: 'date', required: true, message: '请选择日期', trigger: 'change', }],
                    radio: [{ required: true, message: '请选择支出类型', trigger: 'change', },],
                }
            }
        },
        methods: {
            onSubmit() {
                const _this = this
                this.formData.output_type = this.formData.radio
                this.$refs.formData.validate((valid) => {
                    if (valid) {
                        axios.post('http://10.16.104.183:8181/communityFinance/addOutput', this.formData).then(function (resp) {
                            // console.log(resp)
                            if (resp.data.code == "200") {//返回成功
                                _this.$message({
                                    message: '添加成功',
                                    type: 'success'
                                });
                                _this.$router.push('/communityFinance')
                            } else if (resp.data.code == "101") {
                                _this.$message.error('添加失败');
                                return false;
                            }
                        })
                    } else {
                        this.$message.error('请输入所有字段');
                        return false;
                    }
                });
            }
        },
        created() {
        }
    }
</script>
<style scoped>
</style>