<!--获取电报信息展示-->
<template>
    <div style="margin-left: 10px; margin-top: 15px;margin-right: 10px">
        <!--        工具栏-->
        <div class="toolbar">
            <div>
                <el-select v-model="findName" multiple placeholder="请选择">
                    <el-option v-for="item in tabAllUser" :key="item.key" :label="item.label" :value="item.key">
                    </el-option>
                </el-select>
                <el-button type="primary" @click="fetchData" style="margin-left:10px;">
                    <el-icon style="vertical-align: middle;">
                        <search />
                    </el-icon>
                    <span style="vertical-align: middle;"> 搜索 </span>
                </el-button>
            </div>
            <div>
                <!-- <el-upload style="  display: inline-flex; margin-right: 25px;margin-top: -5px;"
                    action="http://localhost:8181/telegram/upload" :headers="header"> 
                    <el-button type="primary">上传数据</el-button>
                </el-upload> -->
                <el-upload style="  display: inline-flex; margin-right: 12px;"
                    action="http://localhost:8181/telegram/upload" :headers="header" :show-file-list="false"
                    :on-change="fileChange" :on-error="fileSuccess" :on-success="fileSuccess">
                    <el-button type="success">
                        <el-icon><folder-add /></el-icon>
                        <span style="vertical-align: middle;">导入数据</span>
                    </el-button>
                </el-upload>
                <el-button type="success" @click="exportData"
                    style=" display: inline-flex; margin-left: 12px;margin-top: -5px;">
                    <el-icon>
                        <upload />
                    </el-icon>
                    <span style="vertical-align: middle;">导出数据</span>

                </el-button>
            </div>

        </div>
    </div>

    <!--        数据展示-->

    <div style="margin-top: 15px">
        <!-- <el-table :data="tableData" border style="width: 100%"> -->
        <div class="table-alert table-alert-info table-alert-with-icon" align="left" style="margin-left: 10px">
            <span class="table-alert-icon">
                <i class="el-alert__icon el-icon-info" />
            </span>
            <span class="table-alert-message">
                已选择 <a style="font-weight: 600">{{ selectUserList.length }}</a> 条信息&nbsp;&nbsp;
            </span>
            <el-button @click="handleClearSelection" type="text">清空
            </el-button>
            <el-button @click="handleReadTelegram" type="text">
                已读所选
            </el-button>
        </div>
        <el-table :data="tableData" border style="width: 98%;margin-left: 15px"
            @selection-change="handleSelectionChange" ref="table" row-key="id" fit :row-class-name="tableRowClassName">

            <el-table-column type="selection" align="center" width="38" :selectable="checkSelectable"
                :reserve-selection="true" />
            <el-table-column prop="id" label="序号" width="90" align="center" />
            <el-table-column prop="nickname" label="用户名" width="120" align="center" />
            <el-table-column prop="userId" label="用户id" width="90" align="center"></el-table-column>
            <el-table-column prop="groupName" label="群组名" width="90" align="center"></el-table-column>
            <el-table-column prop="postalTime" label="发送时间" width="180" align="center" />
            <el-table-column prop="message" label="聊天内容" align="center" />
            <!-- <el-table-column prop="postalTime" label="发送时间" width="180" align="center" /> -->
            <!-- <el-table-column label="查看状态" width="90" align="center">
                    <template #default="tableData">
                        <el-tag v-if="tableData.row.flag === 0" type=" success">未读</el-tag>
                        <el-tag v-else type="success">已读</el-tag>
                    </template>
</el-table-column> -->

            <!-- <el-table-column prop="" label="是否返回" width="60" align="center" /> -->
            <!-- <el-table-column fixed="right" label="操作" width="120" align="center">
                    <template v-slot="tableData">
                        <el-popconfirm confirm-button-text="确定" cancel-button-text="取消" icon-color="red"
                            title="确定删除该条信息吗">

                            <el-button @click="classify(scope.row)" type="success" size="small">大模型分析</el-button>
                        </el-popconfirm>
                        <el-popconfirm confirm-button-text="确定" cancel-button-text="取消" icon-color="red"
                            title="确认使用大模型分析该信息吗" @confirm="analysis(tableData.row.id)" @cancel="cancleAnalysis()">
                            <template #reference>
                                <el-button type="danger" size="small">大模型分析</el-button>
                            </template>
                        </el-popconfirm>

                    </template>
                </el-table-column> -->
            <el-table-column prop="keyword" label="关键词" width="90" align="center">
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
</template>

<script>
import axios from 'axios'
import { Search } from '@element-plus/icons-vue'
// import { export_retailer } from "@/utils/api";
import request from '@/utils/http'
import { FolderAdd, Upload } from '@element-plus/icons-vue'
// import { ro } from 'element-plus/lib/locale'


export default {
    name: "GetResidentInformation",
    data() {
        return {

            keywordData: [],
            tableData: [{
                id: '123',
                username: '',
                nickname: '',
                userId: '',
                message: '',
                postalTime: '',
                groupName: '',
                keyword: '',
                needReturn: '',
                flag: '0',
            }],
            search_text: '',
            total: 0,//总条数
            currentPage: 1,//第几页
            size: 10,//每页条数
            dialogVisible: false,
            editdialogVisible: false,
            activeindex: 0,

            filename: '',
            detaildialogVisible: false,
            keyworddialogVisible: false,
            keywordInputValue: '',
            search_keyword: '',
            selectUserList: [],
            selectUserIds: '',

            tabAllUser: [],
            findName: [],
        }
    },
    created() {
        this.fetchData()
        this.fetchKeywordData()
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
            axios.post('http://localhost:8181/telegram/upload/' + name).then(function (resp) {
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
                url: '/telegram/export',
                method: 'get',
                responseType: "blob",
                params: {
                    'ids': this.selectUserIds
                }
            }).then(function (res) {
                console.log(res)
                let data = res.data
                let filename = "电报聊天记录.xlsx"
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

        preStep() {
            if (this.activeindex == 0) {
                this.dialogVisible = false
                // this.information = ''
                this.$router.go(0)
                return;
            }
            this.activeindex--;
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
                url: '/telegram/listAll',
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
            const _this = this

            request({
                url: '/telegram/findByPage/' + this.currentPage + '/' + this.size,
                method: 'post',
                data: _this.findName
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
                url: 'http://localhost:8181/telegram/analysisByGPT',
                method: 'post',
                data: data,
            }).then(function (resp) {
                if (resp.status == "200") {

                    _this.$message.success("该文本的分析内容\n" + resp.data.data.answer)
                }
                else {
                    _this.$message.error('出错了');
                    return false;
                }
            })
        },

        fetchKeywordData() {
            const _this = this
            request({
                url: 'http://localhost:8181/telegram/keyword/listAll',
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
                url: 'http://localhost:8181/telegram/keyword/delete/' + id,
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
            this.fetchKeywordData()
        },
        handleAddKeyword() {
            const _this = this
            const data = {
                keyword: this.keywordInputValue
            }
            request({
                url: 'http://localhost:8181/telegram/keyword/add',
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
        },
        blurKeyWord() {
            this.keyworddialogVisible = false
            this.keywordInputValue = ''
        },

        showInput() {
            this.keyworddialogVisible = true;
        },

        handleInputConfirm() {
            // let keywordInputValue = this.keywordInputValue;
            // if (keywordInputValue) {
            //     this.dynamicTags.push(keywordInputValue);
            // }
        },
        updateFlag(id) {
            const _this = this
            const data = {
                data: id
            }
            request({
                url: 'http://localhost:8181/telegram/updateFlag/' + id,
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
            // this.$router.go(0)
        },
        // this: header = {
        //     Authorization: "Bearer " + localStorage.getItem('jwt_token')
        // },
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
            return row.flag === 1 || row.flag == 0
        },
        handleReadTelegram() {
            const _this = this
            request({
                url: 'http://localhost:8181/telegram/readTelegram',
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
                url: 'http://localhost:8181/telegram/findAllUser',
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
        Search, FolderAdd, Upload,
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