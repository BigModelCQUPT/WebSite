<!--工资账套-->
<template>
    <div style="margin-top: -30px">
        <h2 style="margin-left: -120px">员工工资管理</h2>
        <el-button @click="showDialog" style="float: right; margin: auto" type="primary">添加工资账套</el-button>
        <el-table :data="tableData" height="350" style="width: 70%; margin: auto;" >
            <el-table-column prop="name" label="账套名称" width="130" fixed="left" />
            <el-table-column prop="basic_salary" label="基本工资" width="90" />
            <el-table-column prop="traffic_subsidy" label="交通补助" width="90" />
            <el-table-column prop="launch_subsidy" label="午餐补助" width="90" />
            <el-table-column prop="award" label="奖金" width="90" />
            <el-table-column prop="start_date" label="启用时间" width="100" />
            <el-table-column  label="养老金">
                <el-table-column prop="ratio1" label="比率" width="60" />
                <el-table-column prop="base1" label="基数" width="60" />
            </el-table-column>
            <el-table-column label="医疗保险">
                <el-table-column prop="ratio2" label="比率" width="60" />
                <el-table-column prop="base2" label="基数" width="60" />
            </el-table-column>
            <el-table-column label="公积金">
                <el-table-column prop="ratio3" label="比率" width="60" />
                <el-table-column prop="base3" label="基数" width="60" />
            </el-table-column>
            <el-table-column
                    fixed="right"
                    label="操作"
                    width="135"
            >
                <template v-slot="scope">
                    <el-button  @click="editClick(scope.row)" type="primary" size="small">编辑</el-button>

                    <el-popconfirm
                            confirm-button-text="确定"
                            cancel-button-text="取消"
                            icon-color="red"
                            title="确定删除该条信息吗"
                            @confirm="deleteClick(scope.row)"
                            @cancel="cancleDelete()"
                    >
                        <template #reference>
                            <el-button type="danger"  size="small">删除</el-button>
                        </template>
                    </el-popconfirm>

                </template>

            </el-table-column>
        </el-table>
        <el-dialog
                title="添加工资账套"
                width="50%"
                v-model="dialogVisible"
                style="display: flex; justify-content: space-around; align-items: center"
                >
            <div style="height: 300px">
                <el-steps direction="vertical" :active="activeindex">
                    <el-step :title="item"
                             v-for="(item,index) in salaryItem"
                             :key="index"
                    />
                </el-steps>
                <el-input :placeholder=" '请输入' + salaryItem[index]" v-for="(val, key, index) in salary"
                          :key="index"
                          v-show="activeindex == index"
                          style="float: right; width: 400px; margin-top: -150px"
                          v-model="salary[key]"
                ></el-input>
            </div>
            <div style="margin-top: 20px">
                <el-button type="primary" @click="preStep">{{activeindex == 0 ? '取消' : '上一步'}}</el-button>
                <el-button type="primary" @click="nextStep">{{activeindex == 12 ? '完成' : '下一个'}}</el-button>
            </div>
        </el-dialog>
        <el-dialog
                title="编辑工资账套"
                width="50%"
                v-model="editdialogVisible"
                style="display: flex; justify-content: space-around; align-items: center"
        >
            <div style="height: 300px">
                <el-steps direction="vertical" :active="activeindex">
                    <el-step :title="item"
                             v-for="(item,index) in salaryItem"
                             :key="index"
                    />
                </el-steps>
                <el-input :placeholder=" '请输入' + salaryItem[index]" v-for="(val, key, index) in salary"
                          :key="index"
                          v-show="activeindex == index"
                          style="float: right; width: 400px; margin-top: -150px"
                          v-model="salary[key]"
                ></el-input>
            </div>

            <div style="margin-top: 20px">
                <el-button type="primary" @click="preStep">{{activeindex == 0 ? '取消' : '上一步'}}</el-button>
                <el-button type="primary" @click="nextStep">{{activeindex == 12 ? '完成' : '下一个'}}</el-button>
            </div>
        </el-dialog>

    </div>
</template>
<script>
    import axios from 'axios'
    export default {
        name: "SalaryAccountView",
        methods:{
            init(){
                const _this = this;
                axios.get('http://localhost:8181/getSalaryAccount').then(function (resp) {
                    // console.log(resp.data)
                    // if(resp.data.code == "200"){//返回成功
                    _this.tableData = resp.data.data
                })
            },
            cancleDelete() {
              return
            },
            editClick(row){
                console.log(row.name)
                this.editdialogVisible = true
                this.salary["name"] = row.name
                this.salary["basic_salary"] = row.basic_salary
                this.salary["traffic_subsidy"] = row.traffic_subsidy
                this.salary["launch_subsidy"] = row.launch_subsidy
                this.salary["award"] = row.award
                this.salary["start_date"] = row.start_date
                this.salary["base1"] = row.base1
                this.salary["ratio1"] = row.ratio1
                this.salary["base2"] = row.base2
                this.salary["ratio2"] = row.ratio2
                this.salary["base3"] = row.base3
                this.salary["ratio3"] = row.ratio3
            },
            deleteClick(row){
                const _this = this
                var data = {
                   "name":row.name
                }
                axios.post('http://localhost:8181/deleteSalaryAccount', JSON.parse(JSON.stringify(data ))).then(function (resp) {
                    console.log(resp)
                    if(resp.data.code == "200"){//返回成功
                        _this.$message({
                            message: '删除成功',
                            type: 'success'
                        });
                        _this.init()
                    }else if(resp.data.code == "101"){
                        _this.$message.error('出现未知错误');
                        return false;
                    }
                })
            },
            showDialog() {
                this.dialogVisible = true
                this.activeindex = 0
            },
            nextStep() {
                if(this.activeindex == 12) {
                    if(this.dialogVisible == true)//添加
                    {
                        this.dialogVisible = false;
                        // console.log(this.salary)
                        const _this = this
                        axios.post('http://localhost:8181/addSalaryAccount', this.salary).then(function (resp) {
                            // console.log(resp)
                            if (resp.data.code == "200") {//返回成功
                                _this.$message({
                                    message: '添加成功',
                                    type: 'success'
                                });
                                _this.init()
                            } else if (resp.data.code == "101") {
                                _this.$message.error('出现未知错误');
                                return false;
                            }
                        })
                        return;
                    }
                    if(this.editdialogVisible == true)//编辑
                    {
                        this.editdialogVisible = false;
                        const _this = this
                        axios.post('http://localhost:8181/editSalaryAccount', this.salary).then(function (resp) {
                            // console.log(resp)
                            if (resp.data.code == "200") {//返回成功
                                this.$message({
                                    message: '添加成功',
                                    type: 'success'
                                });
                            } else if (resp.data.code == "101") {
                                _this.$message.error('出现错误');
                                return ;
                            }
                        })
                        this.$router.go(0)
                        return;
                    }
                }
                this.activeindex ++;
            },
            preStep() {
                if(this.activeindex == 0) {
                    this.dialogVisible = false
                    this.$router.go(0)
                    this.salary = null
                    return ;
                }
                // else if (this.activeindex == 12){
                //     // this.dialogVisible = false
                //     this.$router.go(0)
                //     this.salary = null
                //     return ;
                // }
                this.activeindex --;
            }
        },
        created() {
            this.init()
        },
        data() {
            return {
                tableData:[
                    {
                        name:'秘书处工资账套',
                        basic_salary:'5000',
                        traffic_subsidy:'500',
                        launch_subsidy:'500',
                        award:'500',
                        start_date:'2022-03-14',
                        base1:'800',
                        ratio1:'0.06',
                        base2:'1000',
                        ratio2:'0.08',
                        base3:'1200',
                        ratio3:'0.05'
                    }
                ],
                dialogVisible:false,
                editdialogVisible:false,
                activeindex: 0,
                salaryItem:[
                    '账套名称',
                    '基本工资',
                    '交通补助',
                    '午餐补助',
                    '奖金',
                    '启用时间',
                    '养老金比率',
                    '养老金基数',
                    '医疗保险比率',
                    '医疗保险基数',
                    '公积金比率',
                    '公积金基数'
                ],
                salary:{
                    name:'',
                    basic_salary:'',
                    traffic_subsidy:'',
                    launch_subsidy:'',
                    award:'',
                    start_date:'',
                    base1:'',
                    ratio1:'',
                    base2:'',
                    ratio2:'',
                    base3:'',
                    ratio3:''
                },
                content:''
            }
        }
    }
</script>

<style scoped>

</style>