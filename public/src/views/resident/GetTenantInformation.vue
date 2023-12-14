<!--获取租户信息展示-->
<template>
    <div style="margin-left: 10px; margin-top: -15px;margin-right: 10px">
        <!--        工具栏-->
        <div class="toolbar">
            <div>
                <el-input clearable @clear="init_page" @keydown.enter="findPeople" class="el-input-resident" v-model="search_name" placeholder="请输入租户名称进行搜索...">
                </el-input>
                <el-button type="primary"   @click="findPeople"  >
                    <el-icon style="vertical-align: middle;"><search /></el-icon>
                    <span style="vertical-align: middle;"> 搜索 </span>
                </el-button>
            </div>
            <div >
                <el-button type="primary" @click="showDialog" style="margin-top: 0px">
                    <el-icon style="vertical-align: middle;"><plus /></el-icon>
                    <span style="vertical-align: middle;">手动添加</span>
                </el-button>
            </div>
        </div>
        <!--        数据展示-->
        <div style="margin-top: 15px">
            <el-table :data="tableData" border style="width: 100%">
                <el-table-column prop="community" label="社区" width="60"  align="center"  />
                <el-table-column prop="owner_name" label="房东姓名" width="90"  align="center" />
                <el-table-column prop="owner_tele" label="房东电话" width="130"  align="center" />
                <el-table-column prop="house_id" label="房屋地址" width="100"  align="center"/>
                <el-table-column prop="tenant_name" label="租户姓名" width="90"  align="center" fixed="left"  />
                <el-table-column prop="tenant_tele" label="租户电话" width="130"  align="center" />
                <el-table-column prop="tenant_id_no" label="租户身份证号码" width="200"  align="center" />
                <el-table-column prop="number" label="租客人数" width="120"  align="center" />
                <el-table-column prop="note" label="备注" width="220"  align="center" />
                <el-table-column
                        fixed="right"
                        label="操作"
                        width="200"
                        align="center"
                >
                    <template v-slot="scope">
                        <el-button @click="getDetail(scope.row)" type="success" size="small">详情</el-button>
                        <el-button  @click="editClick(scope.row)" type="primary" size="small">编辑</el-button>
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
                    title="添加租户信息"
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
                    <el-button type="primary" @click="nextStep">{{activeindex == 9 ? '完成' : '下一个'}}</el-button>
                </div>
            </el-dialog>
            <el-dialog
                    title="编辑居民信息"
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
                    <el-button type="primary" @click="nextStep">{{activeindex == 9 ? '完成' : '下一个'}}</el-button>
                </div>
            </el-dialog>
            <el-dialog
                    v-model="detaildialogVisible"
                    title="租户信息"
                    width="50%">
                <el-form :model="form" >
                    <div style="display: inline-flex">
                        <div>
                            <el-form-item style="width: 80%">
                                <label style="font-weight: bolder; font-family: 仿宋">租户姓名</label>
                                <el-input v-model="form.tenant_name" disabled ></el-input>
                            </el-form-item>
                            <el-form-item style="width: 80%" class="margin-topa">
                                <label style="font-weight: bolder; font-family: 仿宋">租户电话</label>
                                <el-input v-model="form.tenant_tele" disabled ></el-input>
                            </el-form-item >

                            <el-form-item style="width: 80%" class="margin-topa">
                                <label style="font-weight: bolder; font-family: 仿宋">身份证号码</label>
                                <el-input v-model="form.tenant_id_no" disabled ></el-input>
                            </el-form-item>
                            <el-form-item style="width: 80%" class="margin-topa">
                                <label style="font-weight: bolder; font-family: 仿宋">居住人数</label>
                                <el-input v-model="form.number" disabled ></el-input>
                            </el-form-item>
                        </div>
                        <div>
                            <el-form-item style="width: 80%" >
                                <label style="font-weight: bolder; font-family: 仿宋">社区</label>
                                <el-input v-model="form.community" disabled ></el-input>
                            </el-form-item>
                            <el-form-item style="width: 80%" class="margin-topa" >
                                <label style="font-weight: bolder; font-family: 仿宋">房屋地址</label>
                                <el-input v-model="form.house_id" disabled ></el-input>
                            </el-form-item>
                            <el-form-item style="width: 80%" class="margin-topa">
                                <label style="font-weight: bolder; font-family: 仿宋">房东姓名</label>
                                <el-input v-model="form.owner_name" disabled ></el-input>
                            </el-form-item>
                            <el-form-item style="width: 80%" class="margin-topa">
                                <label style="font-weight: bolder; font-family: 仿宋">房东电话</label>
                                <el-input v-model="form.owner_tele" disabled ></el-input>
                            </el-form-item>

                        </div>
                    </div>
                    <div style="margin-left: 30px">
                        <el-form-item style="width: 85%" class="margin-topa">
                            <label style="font-weight: bolder; font-family: 仿宋">备注</label>
                            <el-input v-model="form.note" disabled ></el-input>
                        </el-form-item>
                    </div>
                </el-form>
                <template #footer>
                <span class="dialog-footer">
                    <el-button round style="margin-top: -30px"  @click="returnMain">返回</el-button>
                </span>
                </template>
            </el-dialog>
        </div>
    </div>
</template>

<script>
    import axios from 'axios'
    import {Search, Plus } from '@element-plus/icons-vue'
    import {export_retailer} from "@/utils/api";

    export default {
        name: "GetResidentInformation",
        data(){
            return {
                tableData:[{
                    community:'卦畈',
                    house_id: '1-1-101',
                    owner_name: '张三啊',
                    owner_tele:'12378276544',
                    tenant_name: '彭于晏',
                    tenant_tele:'12378276544',
                    number: '',
                    tenant_id_no: '311400112301372347',
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
                    '房东姓名',
                    '房东电话',
                    '门牌号',
                    '租客姓名',
                    '租客电话',
                    '租客身份证号码',
                    '租客人数',
                    '备注'
                ],
                information:{
                    community:'',
                    house_id: '',
                    owner_name: '',
                    owner_tele:'',
                    tenant_name: '',
                    tenant_tele:'',
                    number: '',
                    tenant_id_no: '',
                    note: ''
                },
                filename: '',
                detaildialogVisible: false,
                form:{
                    community:'孤帆',
                    tenant_name: 'lisa',
                    tenant_tele: '12344534332',
                    tenant_id_no: '411244323322222222',
                    owner_name: 'lisa',
                    owner_tele: '11111111111',
                    house_id:'2-10-123',
                    number:'12',
                    note:'外来务工人员，平时都在公司员工宿舍住'

                }
            }
        },
        created() {
            this.init_page()
        },
        methods:{
            fileSuccess() {//上传失败
                this.importData(this.filename)
            },
            fileChange(file) {
                if(this.filename == '')
                    this.filename = file.name;
                // console.log(file.name + "111")
            },
            importData(name) {
                const _this = this
                axios.post('http://localhost:8181/upload/' + name).then(function (resp) {
                    if(resp.data.code == "200"){//返回成功
                        _this.$message({
                            message: '上传成功',
                            type: 'success'
                        });
                    }else if(resp.data.code == "101"){
                        _this.$message.error('上传失败');
                    }
                })
            },
            exportData() {
                // const _this = this
                // axios.get('http://localhost:8181/download/aaa').then(function () {
                //
                // })
                export_retailer()
            },
            showDialog() {
                this.dialogVisible = true
                this.activeindex = 0
            },
            deleteItem(row) {
                const _this = this
                axios.get('http://localhost:8181/tenantInformation/delete/' + row.tenant_id_no).then(function (resp) {
                    // console.log(resp)
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
                this.information["owner_name"] = row.owner_name
                this.information["owner_tele"] = row.owner_tele
                this.information["tenant_name"] = row.tenant_name
                this.information["tenant_tele"] = row.tenant_tele
                this.information["tenant_id_no"] = row.tenant_id_no
                this.information["note"] = row.note
                this.information["number"] = row.number
            },
            nextStep() {
                if(this.activeindex == 9) {

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
                        axios.post('http://localhost:8181/tenantInformation/add', this.information).then(function (resp) {
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
                        axios.post('http://localhost:8181/tenantInformation/edit', this.information).then(function (resp) {
                            // console.log(resp)
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
                axios.get('http://localhost:8181/tenantInformation/find/'  + this.search_name).then(function (resp) {
                    if(resp.data.code == "200"){//返回成功
                        // console.log(resp)
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
                axios.get('http://localhost:8181/tenantInformation/get/' + this.currentPage + '/' + this.size).then(function (resp) {
                    if(resp.data.code == "200"){//返回成功
                        // console.log(resp)
                        _this.tableData = resp.data.data.content
                        _this.total = resp.data.data.totalElements
                    }else if(resp.data.code == "101"){
                        _this.$message.error('请先登录');
                        return false;
                    }else{
                        console.log("error")
                    }
                })
            },
            getDetail(row) {
                // console.log(row)
                this.form = row
                this.detaildialogVisible = true

            },
            returnMain() {
                this.detaildialogVisible = false;
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
    .margin-topa {
        margin-top: -15px;
    }
</style>