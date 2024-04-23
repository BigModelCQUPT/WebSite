<!--获取油管信息展示-->
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
            <div align="right">
                <el-upload style="  display: inline-flex;margin-right:8px;"
                    action="http://localhost:8181/youtube/upload" :headers="header" :show-file-list="false"
                    :on-change="fileChange" :on-error="fileSuccess" :on-success="fileSuccess">
                    <el-button type="success" style="margin-top:13px;margin-right:10px;margin-top: 8px">
                        <el-icon><folder-add /></el-icon>
                        <span style="vertical-align: middle;">导入数据</span>
                    </el-button>
                </el-upload>

                <el-button type="success" @click="exportData"
                    style=" display: inline-flex;margin-top: 0px;margin-right:5px;margin-top: -3px">
                    <el-icon>
                        <upload />
                    </el-icon>
                    <span style="vertical-align: middle;">导出数据</span></el-button>

                <el-button type="primary" @click="updateYoutube" style="margin-top: -3px">
                    <el-icon style="vertical-align: middle;">
                        <plus />
                    </el-icon>
                    <span style="vertical-align: middle;">手动刷新</span>
                </el-button>
            </div>
        </div>


        <!--        数据展示-->
        <div style="margin-top: 15px">
            <!-- <el-table :data="tableData" border style="width: 100%"> -->
            <div class="table-alert table-alert-info table-alert-with-icon" align="left">
                <span class="table-alert-icon">
                    <i class="el-alert__icon el-icon-info" />
                </span>
                <span class="table-alert-message">
                    已选择 <a style="font-weight: 600">{{ selectUserList.length }}</a> 条信息&nbsp;&nbsp;
                </span>
                <el-button @click="handleClearSelection" type="text">清空
                </el-button>
                <el-button @click="handleReadYoutube" type="text">
                    已读所选
                </el-button>
            </div>
            <el-table :data="tableData" border style="width: 98%;margin-left: 15px"
                @selection-change="handleSelectionChange" ref="table" row-key="id" fit
                :row-class-name="tableRowClassName">
                <el-table-column type="selection" align="center" width="38" :selectable="checkSelectable"
                    :reserve-selection="true" />
                <el-table-column prop="id" label="序号" align="center" width="50" />
                <el-table-column prop="releaseTime" label="发布时间" width="100" align="center" />
                <el-table-column prop="coverUrl" label="视频封面" width="150" align="center">
                    <template #default="scope">
                        <el-image :src="scope.row.coverUrl"></el-image>
                        <!-- <div style="text-align: center;width: 100px;">视频标题</div> -->
                    </template>
                </el-table-column>
                <!-- <template slot-scope="tableData">
                    <el-image @click="
                        bigImg(`https://xxx.com${tableData.row.ImageUrl}`)
                        " style="width: 50px; height: 50px; margin-bottom:-4px"
                        :src="`https://xxx.com${scope.row.ImageUrl}`" :preview-src-list="srcList">
                    </el-image>
                </template> -->

                <el-table-column prop="username" label="发布者" align="center" width="120" />
                <el-table-column prop="title" label="视频标题" align="center">
                    <template #default="scope">
                        <a :href="scope.row.videoUrl" target="_blank" style="text-decoration: none">
                            {{ scope.row.title }}
                        </a>
                    </template>
                </el-table-column>
                <el-table-column prop="summary" label="摘要" width="450" align="center"></el-table-column>
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
                <!-- </el-table-column> -->
                <!-- <el-table-column prop="videoUrl" label="视频链接" width="60" align="center" /> -->
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
import { Search, Plus, FolderAdd, Upload } from '@element-plus/icons-vue'
//  import { export_retailer } from "@/utils/api";
import request from '@/utils/http'


export default {
    name: "GetResidentInformation",
    data() {
        return {
            tableData: [{
                id: '123',
                coverUrl: 'abcd',
                username: '张三啊',
                channelId: 'null',
                videoUrl: 'null',
                title: '',
                keyword: '彭于晏',
                flag: '0',
                needReturn: '',
                releaseTime: '',
                summary: '',
            }, {
            }],
            search_text: '',
            total: 0,//总条数
            currentPage: 1,//第几页
            size: 10,//每页条数
            dialogVisible: false,
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
            axios.post('http://localhost:8181/upload/' + name).then(function (resp) {
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
                url: 'http://localhost:8181/youtube/analysisByGPT',
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
        updateFlag(id) {
            const _this = this
            const data = {
                data: id
            }
            request({
                url: 'http://localhost:8181/youtube/updateFlag/' + id,
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


        exportData() {
            let ids = []
            if (this.selectUserList.length > 0) {
                for (var i = 0; i < this.selectUserList.length; i++) {
                    ids[i] = this.selectUserList[i].id
                }
                this.selectUserIds = ids.join(',')
            }

            request({
                url: '/youtube/export',
                method: 'get',
                responseType: "blob",
                params: {
                    'ids': this.selectUserIds
                }
            }).then(function (res) {
                console.log(res)
                let data = res.data
                let filename = "油管信息.xlsx"
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

        currentChange() {
            // console.log(this.currentPage)
            this.fetchData()
        },
        sizeChange() {
            // console.log(this.size)
            this.fetchData()
        },

        fetchData() {
            const _this = this

            request({
                url: '/youtube/listAll/' + this.currentPage + '/' + this.size,
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

        updateYoutube() {
            const _this = this
            const data = {
                page: this.currentPage,
                size: this.size,
            }
            request({
                url: '/youtube/update',
                method: 'get',
                data: data
            }).then(function (resp) {
                if (resp.status == "200") {
                    _this.$message.success("刷新成功")
                }
                else {
                    _this.$message.error('出错了');
                    return false;
                }
            })
            this.$router.go(0)
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
        handleReadYoutube() {
            const _this = this
            request({
                url: 'http://localhost:8181/youtube/readYoutube',
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
                url: 'http://localhost:8181/youtube/findAllUser',
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
        Search, Plus, FolderAdd, Upload
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

.warning-row {
    background-color: #dde7ec !important;
}
</style>