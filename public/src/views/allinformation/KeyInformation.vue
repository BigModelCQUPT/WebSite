<!--获取关键信息展示-->
<template>
    <div style="margin-left: 10px; margin-top: 15px;margin-right: 10px">
        <!--        工具栏-->
        <div class="toolbar">
            <div>
                <el-input clearable @clear="fetchData" @keydown.enter="fetchData" class="el-input-resident"
                    v-model="search_keyword" placeholder="请输入进行搜索...">
                </el-input>
                <el-button type="primary" @click="fetchData">
                    <el-icon style="vertical-align: middle;">
                        <search />
                    </el-icon>
                    <span style="vertical-align: middle;"> 搜索 </span>
                </el-button>
            </div>
            <!-- <div>
                <el-button type="primary" @click="showDialog" style="margin-top: 0px">
                    <el-icon style="vertical-align: middle;">
                        <plus />
                    </el-icon>
                    <span style="vertical-align: middle;">手动添加</span>
                </el-button>
            </div> -->
        </div>

        <!--        数据展示-->
        <div style="margin-top: 15px">
            <!-- <el-table :data="tableData" border style="width: 100%"> -->


            <el-table :data="tableData" border style="width: 100%" :row-class-name="tableRowClassName">
                <el-table-column prop="id" label="序号" width="90" align="center" />
                <el-table-column prop="username" label="用户名" width="120" align="center" />
                <el-table-column prop="text" label="推文内容" align="center" />
                <el-table-column label="推文类型" width="70" align="center">
                    <template #default="tableData">
                        <el-tag v-if="tableData.row.type === 'replied_to'" type="success">回复</el-tag>
                        <el-tag v-else-if="tableData.row.type === 'tweet'" type=" success">原创</el-tag>
                        <el-tag v-else-if="tableData.row.type === 'retweeted'" type=”success”>转发</el-tag>
                        <el-tag v-else type="info">引用</el-tag>
                    </template>
                </el-table-column>
                <!-- <el-table-column prop="keyword" label="关键词" width="90" align="center" /> -->
                <el-table-column prop="keyword" label="关键词" width="90" align="center">
                    <template #default="scope">
                        <div v-for="item in companyCut(scope.row.keyword)" :key='item'>
                            <!-- <el-tag type="success">{{ item }}</el-tag> -->
                            {{ item }}
                        </div>
                    </template>
                </el-table-column>
                <el-table-column label="已读状态" width="90" align="center">
                    <template #default="scope">
                        <el-button v-if="scope.row.flag == 0" type="success"
                            @click="updateFlag(scope.row.id)">未读</el-button>
                        <el-button v-if="scope.row.flag == 1" type="success" plain disabled>已读</el-button>
                    </template>
                </el-table-column>

                <el-table-column label="钉钉返回" width="60" align="center">
                    <template #default="scope">
                        <el-tag v-if="scope.row.needReturn === 0" type="success">否</el-tag>
                        <el-tag v-else-if="scope.row.needReturn === 1" type=" success">是</el-tag>
                    </template>
                </el-table-column>
                <el-table-column prop="category" label="GPT分类类别" width="80" align="center" />
            </el-table>
            <div style="display: flex;justify-content: flex-end; margin-top: 10px">
                <el-pagination background layout="sizes, prev, pager, next, jumper, ->, total, slot" :total="total"
                    :page-sizes="[10, 20, 30, 50]" @size-change="sizeChange" @current-change="currentChange"
                    v-model:currentPage="currentPage" v-model:page-size="size" />
            </div>
        </div>




        <!-- chatgpt 分析结果 弹窗 -->
        <!-- <div>
            <el-dialog title="ChatGPT分析结果" width="30%" v-model="dialogVisible"
                style="display: flex; justify-content: space-around; align-items: center">
                <div style="height: 20%">

                </div>
                <div style="margin-top: 60px">
                    <el-button type="primary" @click="completeAdd">确认</el-button>
                    <el-button type="primary" @click="cancelAdd">取消</el-button>
                </div>
            </el-dialog>
        </div> -->



    </div>
</template>

<script>
    import axios from 'axios'
    import { Search } from '@element-plus/icons-vue'
    import { export_retailer } from "@/utils/api";
    import request from '@/utils/http'

    export default {
        name: "GetResidentInformation",
        data() {
            return {
                tableData: [{
                    id: '123',
                    username: 'abcd',
                    text: '张三啊',
                    type: 'null',
                    keyword: '彭于晏',
                    flag: '0',
                    needReturn: '',
                }, {
                }],
                search_text: '',
                search_keyword: '',
                total: 0,//总条数
                currentPage: 1,//第几页
                size: 10,//每页条数
                dialogVisible: false,
                editdialogVisible: false,
                activeindex: 0,
                filename: '',
                detaildialogVisible: false,

            }
        },
        created() {
            this.fetchData()
            this.fetchKeywordData()
        },
        methods: {
            fileSuccess() {//上传失败
                this.importData(this.filename)
            },
            fileChange(file) {
                if (this.filename == '')
                    this.filename = file.name;
                // console.log(file.name + "111")
            },
            importData(name) {
                const _this = this
                axios.post('http://10.16.104.183:8181/upload/' + name).then(function (resp) {
                    if (resp.data.code == "200") {//返回成功
                        _this.$message({
                            message: '上传成功',
                            type: 'success'
                        });
                    } else if (resp.data.code == "101") {
                        _this.$message.error('上传失败');
                    }
                })
            },
            exportData() {
                // const _this = this
                // axios.get('http://10.16.104.183:8181/download/aaa').then(function () {
                //
                // })
                export_retailer()
            },
            showDialog() {
                this.dialogVisible = true
            },
            cancleAnalysis() {
                return
            },
            currentChange() {
                // console.log(this.currentPage)
                this.fetchData()
            },
            sizeChange() {
                // console.log(this.size)
                this.fetchData()
            },
            // find() {
            //     if (this.search_text == '') {
            //         this.$message.error('请先输入有效值');
            //         return;
            //     }
            //     const _this = this
            //     axios.get('http://10.16.104.183:8181/tenantInformation/find/' + this.search_name).then(function (resp) {
            //         if (resp.data.code == "200") {//返回成功
            //             // console.log(resp)
            //             _this.tableData = resp.data.data.content
            //             _this.total = resp.data.data.length
            //         } else if (resp.data.code == "101") {
            //             _this.$message.error('出现错误');
            //             return false;
            //         } else {
            //             console.log("error")
            //         }
            //     })
            // },
            init_page() {
                const _this = this
                const data = {
                    page: this.currentPage,
                    size: this.size
                }
                request({
                    url: '/tweet/listAll',
                    method: 'post',
                    data: data
                }).then(function (resp) {
                    if (resp.status == "200") {//返回成功
                        // console.log(resp)
                        _this.tableData = resp.data.data.records
                        _this.total = resp.data.data.total
                    } else if (resp.data.code == "101") {
                        _this.$message.error('请先登录');
                        return false;
                    } else {
                        console.log("error")
                    }
                })
            },

            fetchData() {
                // if (this.search_name == '') {
                //     this.$message.error('请先输入有效值');
                //     return;
                // }
                const _this = this
                const data = {
                    page: this.currentPage,
                    size: this.size,
                    keyword: this.search_keyword,
                }
                request({
                    url: '/tweet/findByPage/' + this.currentPage + '/' + this.size,
                    method: 'post',
                    data: data
                }).then(function (resp) {
                    if (resp.status == "200") {
                        _this.tableData = resp.data.data.records
                        _this.total = resp.data.data.total
                    }
                    else {
                        _this.$message.error('出错了');
                        return false;
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
            },

            analysis(id) {
                const _this = this
                // console.log(id)
                const data = {
                    id: id
                }
                request({
                    url: 'http://10.16.104.183:8181/tweet/analysisByGPT',
                    method: 'post',
                    data: data,
                }).then(function (resp) {
                    if (resp.status == "200") {
                        _this.$message.success("该文本的类别属于  " + resp.data.data.category)
                    }
                    else {
                        _this.$message.error('出错了');
                        return false;
                    }
                })
                // location.reload()
                this.$router.go(0)
            },

            fetchKeywordData() {
                const _this = this
                request({
                    url: 'http://10.16.104.183:8181/keyword/listAll',
                    method: 'get',
                }).then(function (resp) {
                    if (resp.status == "200") {
                        _this.keywordData = resp.data.data
                        // 因为可能导致 整个分页表是否包含 该关键词发生变化
                        this.fetchData()
                    }
                    else {
                        _this.$message.error('出错了');
                        return false;
                    }
                })
            },
            handleDeleteKeyword(id) {
                console.log(id);
                const _this = this
                request({
                    url: 'http://10.16.104.183:8181/keyword/delete/' + id,
                    method: 'get',
                }).then(function (resp) {
                    if (resp.status == "200") {
                        _this.$message({
                            message: '删除成功',
                            type: 'success'
                        });
                        this.fetchData()
                    }
                    else {
                        _this.$message.error('出错了');
                        return false;
                    }
                })
                // this.fetchKeywordData()
                this.$router.go(0)
            },
            handleAddKeyword() {
                const _this = this
                const data = {
                    keyword: this.keywordInputValue
                }
                request({
                    url: 'http://10.16.104.183:8181/keyword/add',
                    method: 'post',
                    data: data
                }).then(function (resp) {
                    if (resp.status == "200") {
                        _this.$message.success('添加成功');
                    }
                    else {
                        _this.$message.error('出错了');
                        return false;
                    }
                })
                this.keyworddialogVisible = false
                this.keywordInputValue = ''
                this.fetchKeywordData()
                this.$router.go(0)
            },
            blurKeyWord() {
                this.keyworddialogVisible = false
                this.keywordInputValue = ''
            },

            showInput() {
                this.keyworddialogVisible = true;
            },

            updateFlag(id) {
                const _this = this
                const data = {
                    data: id
                }
                request({
                    url: 'http://10.16.104.183:8181/tweet/updateFlag/' + id,
                    method: 'post',
                    data: data
                }).then(function (resp) {
                    if (resp.status == "200") {
                        _this.$message.success('已读该信息');
                    }
                    else {
                        _this.$message.error('出错了');
                        return false;
                    }
                })
                // this.fetchData()
                this.$router.go(0)
            },

            handleInputConfirm() {
                // let keywordInputValue = this.keywordInputValue;
                // if (keywordInputValue) {
                //     this.dynamicTags.push(keywordInputValue);
                // }
            },
            companyCut(name) {
                let company = (name || "").split(',')
                return company
            },

            tableRowClassName({ row }) {
                if (this.$isNotEmpty) {
                    if (row.needReturn === 1) {
                        return 'row-row';
                    }
                }
                return '';
            }


        },
        components: {
            Search,
        }
    }
</script>

<style scoped>
    .toolbar {
        text-align: left;
        display: flex;
        justify-content: space-between;
    }

    .el-input-resident {
        width: 300px;
        margin-right: 10px;
    }

    .margin-topa {
        margin-top: -15px;
    }


    .el-tag+.el-tag {
        margin-left: 10px;
    }

    .button-new-tag {
        margin-left: 10px;
        height: 32px;
        line-height: 30px;
        padding-top: 0;
        padding-bottom: 0;
    }

    .input-new-tag {
        width: 90px;
        margin-left: 10px;
        vertical-align: bottom;
    }

    .card-header {
        display: flex;
        justify-content: space-between;
        align-items: center;
        background-color: lightblue;
    }

    .text {
        font-size: 14px;
    }

    .item {
        margin-bottom: 18px;
    }

    .box-card {
        width: 480px;
    }

    .el-table .row-row {
        background-color: #FFFF00 !important;
    }
</style>