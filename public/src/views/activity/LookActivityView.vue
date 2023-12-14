<!--
&lt;!&ndash;查看历史活动&ndash;&gt;
<template>
    <div style="margin-left: 10px; margin-right: 10px">
        &lt;!&ndash;        工具栏&ndash;&gt;
        <div class="toolbar">
            <div>
                <el-input clearable @clear="init_page" @keydown.enter="findPeople" class="el-input-resident" v-model="search_name" placeholder="请输入活动主题进行搜索...">
                </el-input>
                <el-button type="primary"   @click="findPeople"  >
                    <el-icon style="vertical-align: middle;"><search /></el-icon>
                    <span style="vertical-align: middle;"> 搜索 </span>
                </el-button>
            </div>
            <div >
                <el-button type="primary" @click="addActivity">
                    <el-icon style="vertical-align: middle;"><plus /></el-icon>
                    <span style="vertical-align: middle;">添加活动</span>
                </el-button>
            </div>
        </div>
        &lt;!&ndash;        数据展示&ndash;&gt;
        <div style="margin-top: 15px">
            <el-table :data="tableData" border style="width: 100%">
                <el-table-column type="selection" width="55" align="center"/>
                <el-table-column prop="theme" label="活动主题" width="260"  align="center" fixed="left" />
                <el-table-column prop="address" label="活动地点" width="220"  align="center"/>
                <el-table-column prop="name" label="记录人" width="80"  align="center"  />
                <el-table-column prop="date" label="活动时间" width="125"  align="center" />
                <el-table-column prop="number" label="参与人数" width="105"  align="center" />
                <el-table-column
                        fixed="right"
                        label="操作"
                        width="225"
                >
                    <template v-slot="scope">
                        <el-button @click="getDetail(scope.row)" type="success" size="small">查看详情</el-button>
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
        &lt;!&ndash;     编辑框&ndash;&gt;
        <div>
            <el-dialog
                    title="编辑活动信息"
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
                    <el-button type="primary" @click="nextStep">{{activeindex == 5 ? '完成' : '下一个'}}</el-button>
                </div>
            </el-dialog>
            <el-dialog
                    title="活动详情"
                    width="50%"
                    v-model="detaildialogVisible"
                    style="display: flex; justify-content: space-around; align-items: center"
            >dasdasd
            </el-dialog>
        </div>
    </div>
</template>

<script>
    import axios from 'axios'
    import {Search, Plus,} from '@element-plus/icons-vue'
    export default {
        name: "GetResidentInformation",
        data(){
            return {
                tableData:[{
                    theme:'主题主题主题主题',
                    date: '2022-01-01',
                    name: '彭于晏',
                    address:'重庆市南岸区茶园大道',
                    number:12,
                }, {
                }],
                search_name:'',
                total:0,//总条数
                currentPage:1,//第几页
                size:10,//每页条数
                activeindex:0,
                information:{
                    theme:'',
                    date:'',
                    address:'',
                    number:'',
                    name:''//记录人
                },
                informationItem:[
                    '主题',
                    '登记日期',
                    '活动地址',
                    '参与人数',
                    '记录人',
                ],
                editdialogVisible:false,
                detaildialogVisible:false
            }
        },
        created() {
            this.init_page()
        },
        methods:{
            getDetail(row) {
                console.log(row)
                this.detaildialogVisible = true
            },
            editClick(row){
                console.log(row.theme)
                this.editdialogVisible = true
                this.information["theme"] = row.theme
                this.information["name"] = row.name
                this.information["address"] = row.address
                this.information["date"] = row.date
                this.information["number"] = row.number
            },
            addActivity() {
                this.$router.push('/addActivity');
            },
            deleteItem(row) {
                const _this = this
                console.log(row.theme)
                axios.get('http://localhost:8181/activity/delete/' + row.theme).then(function (resp) {
                    // console.log(resp)
                    if (resp.data.code == "200") {//返回成功
                        _this.$message({
                            message: '删除成功',
                            type: 'success'
                        });
                    } else if (resp.data.code == "101") {
                        _this.$message.error('出现错误');
                        return false;
                    }
                })
                this.$router.go(0)

            },
            cancleDelete(){
                return
            },
            nextStep() {
                if(this.activeindex == 5) {
                    const _this = this
                    if(this.editdialogVisible == true) {//编辑
                        if(this.information.theme == '')
                        {
                            this.$message.error('请输入活动主题');
                            return
                        }
                        // console.log(this.information)
                        axios.post('http://localhost:8181/activity/edit', this.information).then(function (resp) {
                            console.log(resp)
                            if (resp.data.code == "200") {//返回成功
                                _this.$message({
                                    message: '编辑成功',
                                    type: 'success'
                                });
                            } else if (resp.data.code == "101") {
                                _this.$message.error('出现错误');
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
                this.activeindex &#45;&#45;;
            },
            currentChange() {
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
                axios.get('http://localhost:8181/residentInformation/find/'  + this.search_name).then(function (resp) {
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
                axios.get('http://localhost:8181/activity/get/' + this.currentPage + '/' + this.size).then(function (resp) {
                    if(resp.data.code == "200"){//返回成功
                        // console.log(resp)
                        _this.tableData = resp.data.data.content
                        _this.total = resp.data.data.totalElements
                    }else if(resp.data.code == "101"){
                        _this.$message.error('请先登录');
                        return false;
                    }
                })
            }

        },
        components:{
            Search, Plus,
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
</style>-->

<template>
    <div style="margin-left: 5px; margin-right:5px; margin-top: -20px">
        <div v-for="index in tableData" :key="index" >
            <el-card class="box-card" style="width: 100%" @click="getDetail(index)">
                <template #header>
                    <div class="card-header">
                        <span><b>{{index.theme}}</b></span>
                    </div>
                </template>
                <div class="content-item"  > {{index.content}} </div>
            </el-card>
        </div>
    </div>
</template>
<script>

    import axios from 'axios'
    export default {
        name:'lookActivity',
        data(){
            return {
                tableData:[
                    {
                        theme: '“八·一”建军节慰问',
                        content: '',
                        address:''
                    }]
            }
        },
        methods: {
            init() {
                const _this = this
                axios.get('http://localhost:8181/activity/get').then(function (resp) {
                    // console.log(resp.data)
                    if(resp.data.code == "200"){//返回成功
                        _this.tableData = resp.data.data
                    }else if(resp.data.code == "101"){
                        this.$message.error('出现错误');
                        return false;
                    }
                })
            },
            getDetail(index) {
                // console.log(index)
                this.$router.push({
                    path:'/activityDetail',
                    query:{
                        theme:index.theme,
                        address:index.address
                    },
                })
            }
        },
        created() {
            this.init()
        }
    }
</script>
<style scoped>
    .card-header {
        display: flex;
        justify-content: space-between;
        align-items: center;
        height: 0px;
        color: #3399ff;
        font-family: 华文楷体;

    }


    .box-card {
        width: 480px;
    }
    .content-item {
        text-align: left;
        overflow: hidden;
        text-overflow: ellipsis;
        display: -webkit-box;
        -webkit-line-clamp: 2;
        -webkit-box-orient: vertical;
        margin-top: -10px;
    }
</style>
