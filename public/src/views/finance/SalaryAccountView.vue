<!--工资账套-->
<template>
    <div style="margin-top: -30px">
<<<<<<< HEAD
        <h2 style="margin-left: -120px">用户管理</h2>
       
        <el-table :data="tableData" height="350" border style="width: 100%; margin: auto;" align="center" >
            <el-table-column prop="" label="序号" width="60" fixed="left"  align="center" />
            <el-table-column prop="tel" label="电话号码" width="130" fixed="left"  align="center" />
            <el-table-column prop="username" label="用户名" width="120"  align="center" />
            <el-table-column prop="exptime" label="到期时间" width="120"  align="center" />
            <!-- <el-table-column prop="launch_subsidy" label="午餐补助" width="90" />
            <el-table-column prop="award" label="奖金" width="90" />
            <el-table-column prop="start_date" label="启用时间" width="100" /> -->
            <!-- <el-table-column  label="养老金">
=======
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
>>>>>>> ffc14b02a948452209c8504655d41786bdd0c07f
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
<<<<<<< HEAD
            </el-table-column> -->
            <el-table-column
                    fixed="right"
                    label="操作"
                    width="250"
                    align="center" >
                <template v-slot="scope">
                    <el-button  @click="editClick(scope.row)" type="primary" size="small">编辑</el-button>
                    <!-- <el-button @click="showDialog" style="float: right; margin: auto" type="primary">添加用户</el-button> -->
                    <el-button @click="showDialog" type="primary" size="small">添加用户</el-button>
=======
            </el-table-column>
            <el-table-column
                    fixed="right"
                    label="操作"
                    width="135"
            >
                <template v-slot="scope">
                    <el-button  @click="editClick(scope.row)" type="primary" size="small">编辑</el-button>

>>>>>>> ffc14b02a948452209c8504655d41786bdd0c07f
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
<<<<<<< HEAD
            
        </el-table>

        <div style="display: flex;justify-content: flex-end; margin-top: 10px">
            <el-pagination background layout="sizes, prev, pager, next, jumper, ->, total, slot" :total="total"
                :page-sizes="[10, 20, 30, 50]" @size-change="sizeChange" @current-change="currentChange"
                v-model:currentPage="currentPage" v-model:page-size="size" />
        </div>
        <el-dialog
                title="添加用户信息"
=======
        </el-table>
        <el-dialog
                title="添加工资账套"
>>>>>>> ffc14b02a948452209c8504655d41786bdd0c07f
                width="50%"
                v-model="dialogVisible"
                style="display: flex; justify-content: space-around; align-items: center"
                >
            <div style="height: 300px">
                <el-steps direction="vertical" :active="activeindex">
                    <el-step :title="item"
<<<<<<< HEAD
                             v-for="(item,index) in adminItem"
                             :key="index"
                    />
                </el-steps>
                <el-input :placeholder=" '请输入' + adminItem[index]" v-for="(val, key, index) in admin"
                          :key="index"
                          v-show="activeindex == index"
                          style="float: right; width: 400px; margin-top: -150px"
                          v-model="admin[key]"
=======
                             v-for="(item,index) in salaryItem"
                             :key="index"
                    />
                </el-steps>
                <el-input :placeholder=" '请输入' + salaryItem[index]" v-for="(val, key, index) in salary"
                          :key="index"
                          v-show="activeindex == index"
                          style="float: right; width: 400px; margin-top: -150px"
                          v-model="salary[key]"
>>>>>>> ffc14b02a948452209c8504655d41786bdd0c07f
                ></el-input>
            </div>
            <div style="margin-top: 20px">
                <el-button type="primary" @click="preStep">{{activeindex == 0 ? '取消' : '上一步'}}</el-button>
                <el-button type="primary" @click="nextStep">{{activeindex == 12 ? '完成' : '下一个'}}</el-button>
            </div>
        </el-dialog>
        <el-dialog
<<<<<<< HEAD
                title="编辑用户信息"
=======
                title="编辑工资账套"
>>>>>>> ffc14b02a948452209c8504655d41786bdd0c07f
                width="50%"
                v-model="editdialogVisible"
                style="display: flex; justify-content: space-around; align-items: center"
        >
            <div style="height: 300px">
                <el-steps direction="vertical" :active="activeindex">
                    <el-step :title="item"
<<<<<<< HEAD
                             v-for="(item,index) in adminItem"
                             :key="index"
                    />
                </el-steps>
                <el-input :placeholder=" '请输入' + adminItem[index]" v-for="(val, key, index) in admin"
                          :key="index"
                          v-show="activeindex == index"
                          style="float: right; width: 400px; margin-top: -150px"
                          v-model="admin[key]"
=======
                             v-for="(item,index) in salaryItem"
                             :key="index"
                    />
                </el-steps>
                <el-input :placeholder=" '请输入' + salaryItem[index]" v-for="(val, key, index) in salary"
                          :key="index"
                          v-show="activeindex == index"
                          style="float: right; width: 400px; margin-top: -150px"
                          v-model="salary[key]"
>>>>>>> ffc14b02a948452209c8504655d41786bdd0c07f
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
<<<<<<< HEAD
        name: "adminAccountView",
        methods:{
            init(){
                const _this = this;
                axios.get('http://10.16.104.183:8181/getadminAccount').then(function (resp) {
=======
        name: "SalaryAccountView",
        methods:{
            init(){
                const _this = this;
                axios.get('http://localhost:8181/getSalaryAccount').then(function (resp) {
>>>>>>> ffc14b02a948452209c8504655d41786bdd0c07f
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
<<<<<<< HEAD
                this.admin["tel"] = row.tel
                this.admin["username"] = row.username
                this.admin["exptime"] = row.exptime
                // this.admin["launch_subsidy"] = row.launch_subsidy
                // this.admin["award"] = row.award
                // this.admin["start_date"] = row.start_date
                // this.admin["base1"] = row.base1
                // this.admin["ratio1"] = row.ratio1
                // this.admin["base2"] = row.base2
                // this.admin["ratio2"] = row.ratio2
                // this.admin["base3"] = row.base3
                // this.admin["ratio3"] = row.ratio3
=======
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
>>>>>>> ffc14b02a948452209c8504655d41786bdd0c07f
            },
            deleteClick(row){
                const _this = this
                var data = {
<<<<<<< HEAD
                   "username":row.username
                }
                axios.post('http://10.16.104.183:8181/deleteadminAccount', JSON.parse(JSON.stringify(data ))).then(function (resp) {
=======
                   "name":row.name
                }
                axios.post('http://localhost:8181/deleteSalaryAccount', JSON.parse(JSON.stringify(data ))).then(function (resp) {
>>>>>>> ffc14b02a948452209c8504655d41786bdd0c07f
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
<<<<<<< HEAD
                        // console.log(this.admin)
                        const _this = this
                        axios.post('http://10.16.104.183:8181/addadminAccount', this.admin).then(function (resp) {
=======
                        // console.log(this.salary)
                        const _this = this
                        axios.post('http://localhost:8181/addSalaryAccount', this.salary).then(function (resp) {
>>>>>>> ffc14b02a948452209c8504655d41786bdd0c07f
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
<<<<<<< HEAD
                        axios.post('http://10.16.104.183:8181/editadminAccount', this.admin).then(function (resp) {
=======
                        axios.post('http://localhost:8181/editSalaryAccount', this.salary).then(function (resp) {
>>>>>>> ffc14b02a948452209c8504655d41786bdd0c07f
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
<<<<<<< HEAD
                    this.admin = null
=======
                    this.salary = null
>>>>>>> ffc14b02a948452209c8504655d41786bdd0c07f
                    return ;
                }
                // else if (this.activeindex == 12){
                //     // this.dialogVisible = false
                //     this.$router.go(0)
<<<<<<< HEAD
                //     this.admin = null
=======
                //     this.salary = null
>>>>>>> ffc14b02a948452209c8504655d41786bdd0c07f
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
<<<<<<< HEAD
                        tel:'17768120920',
                        username:'啊啊啊啊啊',
                        exptime:'20231231'

=======
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
>>>>>>> ffc14b02a948452209c8504655d41786bdd0c07f
                    }
                ],
                dialogVisible:false,
                editdialogVisible:false,
                activeindex: 0,
<<<<<<< HEAD
                adminItem:[
                    '电话号码',
                    '用户名',
                    '到期时间'
                ],
                admin:{
                    tel:'',
                    username:'',
                    exptime:'',
                   
=======
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
>>>>>>> ffc14b02a948452209c8504655d41786bdd0c07f
                },
                content:''
            }
        }
    }
</script>

<style scoped>

</style>