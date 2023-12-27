<!-- 封控区隔离登记  暖心台账记录-->
<template>
    <div style="margin-left: 10px; margin-top: -25px;margin-right: 10px">
        <h2>隔离人员信息</h2>
        <!--        工具栏-->
        <div class="toolbar">
            <div>
                <el-input clearable @clear="init_page" @keydown.enter="findPeople" class="el-input-resident" v-model="search_name" placeholder="请输入隔离人员名称进行搜索...">
                </el-input>
                <el-button type="primary"   @click="findPeople"  >
                    <el-icon style="vertical-align: middle;"><search /></el-icon>
                    <span style="vertical-align: middle;"> 搜索 </span>
                </el-button>
            </div>
            <div >
                <el-button type="primary" @click="showDialog" style="margin-top: -6px">
                    <el-icon style="vertical-align: middle;"><plus /></el-icon>
                    <span style="vertical-align: middle;">新增隔离人员</span>
                </el-button>
            </div>
        </div>
        <!--        数据展示-->
        <div style="margin-top: 15px">
            <el-table :data="tableData" border style="width: 100%">
                <el-table-column type="selection" width="55" align="center" fixed="left"/>
                <el-table-column prop="community" label="社区" width="90"  align="center"  />
                <el-table-column prop="name" label="姓名" width="75"  align="center" fixed="left" />
                <el-table-column prop="house_id" label="门牌号" width="100"  align="center"/>
                <el-table-column prop="gender" label="性别" width="55"  align="center" />
                <el-table-column prop="age" label="年龄" width="55"  align="center" />
                <el-table-column prop="id_no" label="身份证号码" width="210"  align="center" />
                <el-table-column prop="tele" label="电话号码" width="120"  align="center" />
                <el-table-column prop="note" label="备注" width="320"  align="center" />
                <el-table-column
                        fixed="right"
                        label="操作"
                        width="135"
                >
                    <template v-slot="scope">
                        <el-button  @click="editClick(scope.row)" type="primary" size="small">编辑</el-button>
                        <!--                        <el-button @click="deleteClick(scope.row)" type="danger" size="small">删除</el-button>-->
                        <el-popconfirm
                                confirm-button-text="确定"
                                cancel-button-text="取消"
                                icon-color="red"
                                title="确定删除该条信息吗"
                                @confirm="deleteItem(scope.row)"
                                @cancel="cancleDelete()"
                        >
                            <template #reference>
                                <el-button type="danger"  size="small">删除</el-button>
                            </template>
                        </el-popconfirm>


                    </template >
                </el-table-column>
            </el-table>
            <div style="display: flex;justify-content: flex-end; margin-top: 10px">
                <el-pagination
                        background
                        layout="sizes, prev, pager, next, jumper, ->, total, slot"
                        :total="total"
                        :page-sizes="[10, 20,30, 50]"
                        @size-change="sizeChange"
                        @current-change="currentChange"
                        v-model:currentPage="currentPage"
                        v-model:page-size="size"
                />
            </div>
        </div>
        <!--        添加框  编辑框-->
        <div>
            <el-dialog
                    title="添加居民信息"
                    width="50%"
                    v-model="dialogVisible"
                    style="display: flex; justify-content: space-around; align-items: center"
            >
                <div style="height: 300px">
                    <el-steps direction="vertical" :active="activeindex">
                        <el-step :title="item"
                                 v-for="(item,index) in informationItem"
                                 :key="index"
                        />
                    </el-steps>
                    <el-input :placeholder=" '请输入' + informationItem[index]" v-for="(val, key, index) in information"
                              :key="index"
                              v-show="activeindex == index"
                              style="float: right; width: 400px; margin-top: -150px"
                              v-model="information[key]"
                              @keydown.enter="nextStep"

                    ></el-input>
                </div>
                <div style="margin-top: 60px">
                    <el-button type="primary" @click="preStep">{{activeindex == 0 ? '取消' : '上一步'}}</el-button>
                    <el-button type="primary" @click="nextStep">{{activeindex == 13 ? '完成' : '下一个'}}</el-button>
                </div>
            </el-dialog>
            <el-dialog
                    title="编辑隔离人员信息"
                    width="50%"
                    v-model="editdialogVisible"
                    style="display: flex; justify-content: space-around; align-items: center"
            >
                <div style="height: 300px">
                    <el-steps direction="vertical" :active="activeindex">
                        <el-step :title="item"
                                 v-for="(item,index) in informationItem"
                                 :key="index"
                        />
                    </el-steps>
                    <el-input :placeholder=" '请输入' + informationItem[index]" v-for="(val, key, index) in information"
                              :key="index"
                              v-show="activeindex == index"
                              style="float: right; width: 400px; margin-top: -150px"
                              v-model="information[key]"
                              @keydown.enter="nextStep"
                    ></el-input>
                </div>
                <div style="margin-top: 60px">
                    <el-button type="primary" @click="preStep">{{activeindex == 0 ? '取消' : '上一步'}}</el-button>
                    <el-button type="primary" @click="nextStep">{{activeindex == 8 ? '完成' : '下一个'}}</el-button>
                </div>
            </el-dialog>
        </div>
    </div>
</template>

<script>
    import axios from 'axios'
    import {Search, Plus } from '@element-plus/icons-vue'

    export default {
        name: "ReportWarmView",
        data(){
            return {
                tableData:[{
                    community:'卦畈社区',
                    house_id: '1-1-101',
                    landlord_name: '张三啊',
                    name: '彭于晏',
                    gender  : '男',
                    age: '30',
                    id_no: '311400112301372347',
                    tele: '18766389207',
                    note: '高龄产妇，正在准备生二胎'
                }, {

                }],
                search_name:'',
                total:0,//总条数
                currentPage:1,//第几页
                size:10,//每页条数
                dialogVisible:false,
                editdialogVisible:false,
                activeindex:0,
                informationItem:[
                    '社区名称',
                    '门牌号码',
                    '姓名',
                    '性别',
                    '年龄',
                    '身份证号码',
                    '手机号',
                    '备注'
                ],
                information:{
                    community:'',
                    house_id: '',
                    name: '',
                    gender  : '',
                    age: '',
                    id_no: '',
                    tele: '',
                    note: ''
                },
            }
        },
        created() {
            this.init_page()
        },
        methods:{
            showDialog() {
                this.dialogVisible = true
                this.activeindex = 0
            },
            deleteItem(row) {
                const _this = this
<<<<<<< HEAD
                axios.get('http://10.16.104.183:8181/abnormalWarm/delete/' + row.id_no).then(function (resp) {
=======
                axios.get('http://localhost:8181/abnormalWarm/delete/' + row.id_no).then(function (resp) {
>>>>>>> ffc14b02a948452209c8504655d41786bdd0c07f
                    if (resp.data.code == "200") {//返回成功
                        _this.$message({
                            message: '删除成功',
                            type: 'success'
                        });
                        _this.$router.go(0)
                    } else if (resp.data.code == "101") {
                        _this.$message.error('出现未知错误');
                        return false;
                    }
                })
            },
            cancleDelete(){
                return
            },
            editClick(row){
                // console.log(row.id_no)
                this.editdialogVisible = true
                this.information["community"] = row.community
                this.information["house_id"] = row.house_id
                this.information["name"] = row.name
                this.information["gender"] = row.gender
                this.information["age"] = row.age
                this.information["id_no"] = row.id_no
                this.information["tele"] = row.tele
                this.information["note"] = row.note
            },
            nextStep() {
                if(this.activeindex == 8) {

                    const _this = this
                    if(this.dialogVisible == true) {//添加
                        if(this.information.id_no == '')
                        {
                            this.$message.error('请输入身份证号码');
                            return
                        }
                        if(this.information.name == '')
                        {
                            this.$message.error('请输入姓名');
                            return
                        }
<<<<<<< HEAD
                        axios.post('http://10.16.104.183:8181/abnormalWarm/add', this.information).then(function (resp) {
=======
                        axios.post('http://localhost:8181/abnormalWarm/add', this.information).then(function (resp) {
>>>>>>> ffc14b02a948452209c8504655d41786bdd0c07f
                            // console.log(resp)
                            if (resp.data.code == "200") {//返回成功
                                _this.$message({
                                    message: '添加成功',
                                    type: 'success'
                                });
                                //     _this.$router.replace('/main')
                            } else if (resp.data.code == "101") {
                                _this.$message.error('出现未知错误');
                                return false;
                            }
                        })
                        this.dialogVisible = false;
                    }
                    else if(this.editdialogVisible == true) {//编辑
                        if(this.information.id_no == '')
                        {
                            this.$message.error('请输入身份证号码');
                            return
                        }
                        if(this.information.name == '')
                        {
                            this.$message.error('请输入姓名');
                            return
                        }
                        // console.log(this.information)
<<<<<<< HEAD
                        axios.post('http://10.16.104.183:8181/abnormalWarm/edit', this.information).then(function (resp) {
=======
                        axios.post('http://localhost:8181/abnormalWarm/edit', this.information).then(function (resp) {
>>>>>>> ffc14b02a948452209c8504655d41786bdd0c07f
                            console.log(resp)
                            if (resp.data.code == "200") {//返回成功
                                _this.$message({
                                    message: '编辑成功',
                                    type: 'success'
                                });
                            } else if (resp.data.code == "101") {
                                _this.$message.error('出现未知错误');
                                return false;
                            }
                        })
                        this.editdialogVisible = false;
                    }
                    this.$router.go(0)
                    return ;
                }
                this.activeindex ++;
            },
            preStep() {
                if(this.activeindex == 0) {
                    this.dialogVisible = false
                    // this.information = ''
                    this.$router.go(0)
                    return ;
                }
                // else if (this.activeindex == 13){
                //     this.dialogVisible = false
                //     // this.$router.go(0)
                //     this.information = null
                //     return ;
                // }
                this.activeindex --;
            },
            currentChange() {
                // console.log(this.currentPage)
                this.init_page()
            },
            sizeChange() {
                // console.log(this.size)
                this.init_page()

            },
            findPeople() {
                if(this.search_name == '')
                {
                    this.$message.error('请先输入有效值');
                    return ;
                }
                const _this= this
<<<<<<< HEAD
                axios.get('http://10.16.104.183:8181/abnormalWarm/find/'  + this.search_name).then(function (resp) {
=======
                axios.get('http://localhost:8181/abnormalWarm/find/'  + this.search_name).then(function (resp) {
>>>>>>> ffc14b02a948452209c8504655d41786bdd0c07f
                    if(resp.data.code == "200"){//返回成功
                        console.log(resp)
                        _this.tableData = resp.data.data.content
                        _this.total =  resp.data.data.length
                    }else if(resp.data.code == "101"){
                        _this.$message.error('出现错误');
                        return false;
                    }else{
                        console.log("error")
                    }
                })
            },
            init_page() {
                const _this = this
<<<<<<< HEAD
                axios.get('http://10.16.104.183:8181/abnormalWarm/get/' + this.currentPage + '/' + this.size).then(function (resp) {
=======
                axios.get('http://localhost:8181/abnormalWarm/get/' + this.currentPage + '/' + this.size).then(function (resp) {
>>>>>>> ffc14b02a948452209c8504655d41786bdd0c07f
                    if(resp.data.code == "200"){//返回成功
                         console.log(resp)
                        _this.tableData = resp.data.data.content
                        _this.total = resp.data.data.totalElements
                    }else if(resp.data.code == "101"){
                        _this.$message.error('请先登录');
                        return false;
                    }else{
                        console.log("error")
                    }
                })
            }

        },
        components:{
            Search, Plus
        }
    }
</script>

<style scoped>
    .toolbar {
        text-align: left;
        display: flex;
        justify-content: space-between;
    }
    .el-input-resident{
        width: 300px;
        margin-right: 10px;
    }
</style>