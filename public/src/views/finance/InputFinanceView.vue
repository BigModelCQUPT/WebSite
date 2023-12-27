<!-- 财务支出和收入登记  每件事有类别 用于饼状图统计 -->

<template>
    <div style="width: 70%;  margin: auto; margin-top: 20px;">
        <el-form :model="formData"
                 label-width="120px"
                 :rules="rulesForm"
                 ref="formData"
        >
            <el-form-item label="收入项目" prop="input">
                <el-input v-model="formData.input" />
            </el-form-item>
            <el-form-item label="收入日期" prop="date">
                <el-col :span="10">
                    <el-date-picker
                            v-model="formData.date"
                            type="date"
                            placeholder="选择日期"
                            style="width: 100%"
                            value-format="YYYY-MM-DD"                     />
                </el-col>
                <el-col :span="14">
                    <el-form-item label="收入金额" prop="input_number">
                        <el-input v-model="formData.input_number" />
                    </el-form-item>
                </el-col>
            </el-form-item>
            <el-form-item label="收入类型" prop="radio">
                <el-radio-group v-model="formData.radio">
                    <el-radio label="利息结算收入" />
                    <el-radio label="快递柜租赁费收入" />
                    <el-radio label="房屋租赁收入" />
                    <el-radio label="房地产公司管理费收入" />
                    <el-radio label="墙壁、电梯等广告费收入" />
                    <el-radio label="其他收入" />
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
        name:'InputFinanceView',
        data(){
            return {
                formData:{
                    name: null,
                    date: null,
                    radio:null,
                    note: null,
                    input_number:0,
                    input:null,
                    input_type:null,
                    output:null,
                    output_type:null,
                    output_number:0
                },
                checked: true,
                rulesForm: {
                    input: [{required: true, message: '请输入收入项目内容', trigger: 'blur'},],
                    input_number: [{required: true, message: '请输入收入金额', trigger: 'blur'},],
                    date: [{type: 'date', required: true, message: '请选择登记日期', trigger: 'change',}],
                    radio: [{required: true, message: '请选择收入类型', trigger: 'change',},],
                }
            }
        },
        methods:{
            onSubmit() {
                const _this = this
                this.formData.input_type = this.formData.radio
                this.$refs.formData.validate((valid) => {
                    if (valid) {
                        axios.post('http://10.16.104.183:8181/communityFinance/addInput', this.formData).then(function (resp) {
                            // console.log(resp)
                            if(resp.data.code == "200"){//返回成功
                                _this.$message({
                                    message: '添加成功',
                                    type: 'success'
                                });
                                _this.$router.push('/communityFinance')
                            }else if(resp.data.code == "101"){
                                _this.$message.error('提交失败');
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
