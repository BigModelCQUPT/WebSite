<!--获取推特信息展示-->
<template>
    <div style="margin-left: 10px; margin-top: 15px;margin-right: 10px">
        <!--        工具栏-->
        <div class="toolbar">
            <div style=" display: inline-flex;">
                <el-input clearable @clear="fetchData" @keydown.enter="fetchData" class="el-input-resident"
                    v-model="search_keyword" placeholder="请输入进行搜索...">
                </el-input>
                <el-select v-model="findName" multiple placeholder="请选择用户">
                    <el-option v-for="item in tabAllUser" :key="item.key" :label="item.label" :value="item.key">
                        <span style="float: left">{{ item.key }}</span>
                        <span style="
                        float: right;
                        color: var(--el-text-color-secondary);
                        font-size: 13px;
                        ">{{ item.label }}</span>
                    </el-option>
                </el-select>
                <el-button type="primary" @click="fetchData" style="margin-left:10px;">
                    <el-icon style=" vertical-align: middle;">
                        <search />
                    </el-icon>
                    <span style="vertical-align: middle;"> 搜索 </span>
                </el-button>
            </div>
            <div align="right" style=" display: inline-flex;">
                <el-upload style="  display: inline-flex;margin-right:8px;"
                    action="http://localhost:8181/twitter/upload" :headers="header" :show-file-list="false"
                    :on-change="fileChange" :on-error="fileSuccess" :on-success="fileSuccess">
                    <el-button type="success" style="margin-right:10px;">
                        <el-icon><folder-add /></el-icon>
                        <span style="vertical-align: middle;">导入数据</span>
                    </el-button>
                </el-upload>

                <el-button type="success" @click="exportData" style=" display: inline-flex;margin-right:5px;">
                    <el-icon>
                        <upload />
                    </el-icon>
                    <span style="vertical-align: middle;">导出数据</span></el-button>
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
            <div class="table-alert table-alert-info table-alert-with-icon" align="left">
                <span class="table-alert-icon">
                    <i class="el-alert__icon el-icon-info" />
                </span>
                <span class="table-alert-message">
                    已选择 <a style="font-weight: 600">{{ selectUserList.length }}</a> 条信息&nbsp;&nbsp;
                </span>
                <el-button @click="handleClearSelection" type="text">清空
                </el-button>
                <el-button @click="handleReadTweet" type="text">
                    已读所选
                </el-button>
            </div>
            <!-- <el-table :data="tableData" border style="width: 100%"> -->


            <el-table :data="tableData" border style="width: 98%;margin-left: 15px"
                @selection-change="handleSelectionChange" ref="table" row-key="id" fit
                :row-class-name="tableRowClassName">

                <el-table-column type="selection" align="center" width="38" :selectable="checkSelectable"
                    :reserve-selection="true" />
                <el-table-column prop="id" label="序号" width="90" align="center" />
                <el-table-column prop="username" label="用户名" width="100" align="center" />
                <el-table-column prop="time" label="发布时间" width="100" align="center" />
                <el-table-column prop="text" label="推文内容" align="center" />
                <el-table-column label="推文类型" width="85" align="center">
                    <template #default="tableData">
                        <el-tag v-if="tableData.row.type === 'reply'" type="success">评论</el-tag>
                        <el-tag v-else-if="tableData.row.type === 'tweet'" type=" success">推文</el-tag>
                        <el-tag v-else-if="tableData.row.type === 'reposted'" type=”success”>转发</el-tag>
                    </template>
                </el-table-column>
                <!-- <el-table-column prop="keyword" label="关键词" width="90" align="center" /> -->
                <el-table-column prop="keyword" label="关键词" width="80" align="center">
                    <template #default="scope">
                        <div v-for="item in companyCut(scope.row.keyword)" :key='item'>
                            <!-- <el-tag type="success">{{ item }}</el-tag> -->
                            {{ item }}
                        </div>
                    </template>
                </el-table-column>


                <el-table-column label="钉钉返回" width="60" align="center">
                    <template #default="scope">
                        <el-tag v-if="scope.row.needReturn === 0" type="success">否</el-tag>
                        <el-tag v-else-if="scope.row.needReturn === 1" type=" success">是</el-tag>
                    </template>
                </el-table-column>

                <!-- <el-table-column label="已读状态" width="90" align="center">
                    <template #default="scope">
                        <el-button v-if="scope.row.flag == 0" type="success"
                            @click="updateFlag(scope.row.id)">未读</el-button>
                        <el-button v-if="scope.row.flag == 1" type="success" plain disabled>已读</el-button>
                    </template>
                </el-table-column> -->
                <!-- <el-table-column prop="category" label="GPT分类类别" width="80" align="center" /> -->
                <!-- <el-table-column fixed="right" label="操作" width="110" align="center">
                    <template v-slot="tableData"> -->
                <!-- <el-popconfirm confirm-button-text="确定" cancel-button-text="取消" icon-color="red" title="确定删除该条信息吗">
                            
                            <el-button @click="classify(scope.row)" type="success" size="small">大模型分析</el-button>
                        </el-popconfirm> -->

                <!-- <el-button  @click="editClick(scope.row)" type="primary" size="small">不使用</el-button> -->
                <!-- <el-popconfirm confirm-button-text="确定" cancel-button-text="取消" icon-color="red"
                            title="确认使用大模型分析该信息吗" @confirm="analysis(tableData.row.id)" @cancel="cancleAnalysis()">
                            <template #reference>
                                <el-button type="danger" size="small">大模型分析</el-button>
                            </template>
                        </el-popconfirm>

                    </template> -->
                <!-- </el-table-column> -->
            </el-table>
            <div style="display: flex;justify-content: flex-end; margin-top: 10px">
                <el-pagination background layout="sizes, prev, pager, next, jumper, ->, total, slot" :total="total"
                    :page-sizes="[10, 20, 30, 50]" @size-change="sizeChange" @current-change="currentChange"
                    v-model:currentPage="currentPage" v-model:page-size="size" />
            </div>
        </div>
    </div>
</template>

<script>
    import axios from 'axios'
    import { Search, FolderAdd, Upload } from '@element-plus/icons-vue'
    // import { export_retailer } from "@/utils/api";
    // import { export_retailer } from "@/utils/api";
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
                    time: '',
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
                selectUserList: [],
                selectUserIds: '',

                tabAllUser: [],
                findName: [],
            }
        },
        created() {
            this.fetchData()
            this.getAllUser()
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
                let ids = []
                if (this.selectUserList.length > 0) {
                    for (var i = 0; i < this.selectUserList.length; i++) {
                        ids[i] = this.selectUserList[i].id
                    }
                    this.selectUserIds = ids.join(',')
                }

                request({
                    url: '/tweet/export',
                    method: 'get',
                    responseType: "blob",
                    params: {
                        'ids': this.selectUserIds
                    }
                }).then(function (res) {
                    console.log(res)
                    let data = res.data
                    let filename = "推特聊天记录.xlsx"
                    let url = window.URL.createObjectURL(new Blob([data]))
                    let link = document.createElement('a')
                    link.style.display = 'none'
                    link.href = url
                    link.setAttribute('download', filename)
                    document.body.appendChild(link)
                    link.click()
                    document.body.removeChild(link) // 下载完成移除元素
                    window.URL.revokeObjectURL(url) // 释放掉blob对象
                }).catch((error) => {
                    console.log(error)
                })
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
                    keyword: this.search_keyword,
                    needName: _this.findName
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



            // 复选框
            handleSelectionChange(selection) {
                this.selectUserList = selection
            },
            handleClearSelection() {
                this.selectUserList = []
                this.selectUserIds = ''
                this.$refs.table.clearSelection()
            },
            checkSelectable(row) {
                return row.flag === 1 || row.flag === 0 // 状态为 2 禁用复选框（返回值为 true 启用，false 禁用）
            },
            handleReadTweet() {
                const _this = this
                request({
                    url: 'http://10.16.104.183:8181/tweet/readTweet',
                    method: 'post',
                    data: this.selectUserList
                }).then(function (resp) {
                    if (resp.status == "200") {
                        _this.$message.success('已读成功');
                    }
                    else {
                        _this.$message.error('出错了');
                        return false;
                    }
                })
                this.$router.go(0)
            },

            // 行数变灰
            tableRowClassName({ row, rowIndex }) {
                if (row.flag === 1) {
                    console.log(row, rowIndex);
                    return 'warning-row'
                } else {
                    return ''
                }
            },

            // 获取所有用户
            getAllUser() {
                const _this = this
                request({
                    url: 'http://10.16.104.183:8181/tweet/findAllUser',
                    method: 'get',
                }).then(function (resp) {
                    if (resp.status == "200") {
                        _this.tabAllUser = resp.data.data
                    }
                    else {
                        _this.$message.error('出错了');
                        return false;
                    }
                })
            }
        },
        components: {
            Search, FolderAdd, Upload
        }
    }

</script>

<style>
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

    .warning-row {
        background-color: #dde7ec !important;
    }
</style>