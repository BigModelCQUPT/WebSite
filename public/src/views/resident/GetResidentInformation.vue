<!--获取居民信息展示-->
<template>
    <div style="margin-left: 10px; margin-top: 10px;margin-right: 10px">

        <!--        工具栏-->
        <div class="toolbar">
            <div>
                <el-input clearable @clear="init_page" @keydown.enter="search_initpage" class="el-input-resident"
                    v-model="search_name" placeholder="请输入用户名进行搜索">
                </el-input>
                <el-button type="primary" @click="search_initpage">
                    <el-icon style="vertical-align: middle;">
                        <search />
                    </el-icon>
                    <span style="vertical-align: middle;"> 搜索 </span>
                </el-button>
            </div>
            <div>
                <el-upload style="  display: inline-flex; margin-right: 12px;" :show-file-list="false"
                    :on-change="fileChange" :on-error="fileSuccess">
                    <el-button type="success">
                        <el-icon><folder-add /></el-icon>
                        <span style="vertical-align: middle;">导入数据</span>
                    </el-button>
                </el-upload>
                <el-button type="success" @click="exportData" style="margin-top: -6px">
                    <el-icon>
                        <upload />
                    </el-icon>
                    <span style="vertical-align: middle;">导出数据</span>
                </el-button>
                <el-button type="primary" @click="showDialog" style="margin-top: -6px">
                    <el-icon style="vertical-align: middle;">
                        <plus />
                    </el-icon>
                    <span style="vertical-align: middle;">手动添加</span>
                </el-button>
            </div>
        </div>

        <!--        数据展示-->
        <div style="margin-top: 15px">
            <el-table :data="tableData" border style="width: 100%">
                <el-table-column prop="id" label="id" align="center" />
                <el-table-column prop="twitterId" label="推特id" align="center" />
                <el-table-column prop="username" label="用户名" align="center" />
                <el-table-column prop="name" label="真实姓名" align="center" />
            </el-table>
            <!--          分页-->
            <div style="display: flex;justify-content: flex-end; margin-top: 10px">
                <el-pagination background layout="sizes, prev, pager, next, jumper, ->, total, slot" :total="total"
                    :page-sizes="[10, 20, 30, 50]" @size-change="sizeChange" @current-change="currentChange"
                    v-model:currentPage="currentPage" v-model:page-size="size" />
            </div>
        </div>
        <!--        添加框 -->
        <div>
            <el-dialog title="添加用户" width="30%" v-model="dialogVisible"
                style="display: flex; justify-content: space-around; align-items: center">
                <div style="height: 20%">
                    <el-input :placeholder="'请输入用户名'" style="align:center; width: 60%;" @keydown.enter="completeAdd"
                        v-model="add_name" @clear="fetchData"></el-input>
                </div>
                <div style="margin-top: 60px">
                    <el-button type="primary" @click="completeAdd">确认</el-button>
                    <el-button type="primary" @click="cancelAdd">取消</el-button>
                </div>
            </el-dialog>
        </div>
        <!-- 
        <div>
            <el-dialog title="添加关键词" width="30%" v-model="dialogVisible"
                style="display: flex; justify-content: space-around; align-items: center">
                <div style="height: 20%">
                    <el-input :placeholder="'请输入关键词'" style="align:center; width: 60%;" @keydown.enter="completeAdd"
                        v-model="add_name" @clear="fetchData"></el-input>
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
import { Search, Plus, FolderAdd, Upload } from '@element-plus/icons-vue'
import { export_retailer } from "@/utils/api";
import request from '@/utils/http'

export default {
    name: "GetResidentInformation",
    data() {
        return {

            tableData: [{
                id: '1',
                twitterId: '121212',
                username: 'username',
                name: '姓名'
            }],
            search_name: '', // 搜索用户
            add_name: '', // 添加用户
            total: 0,//总条数
            currentPage: 1,//第几页
            size: 10,//每页条数
            dialogVisible: false,
            information: {
                id: '',
                twitterId: '',
                username: '',
                name: ''
            },
            filename: '',
            form: {
                id: '',
                twitterId: '',
                username: '',
                name: ''
            },
            search_flag: false
        }
    },
    created() {
        // this.init_page()
        this.fetchData()
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
            this.activeindex = 0
            this.add_name = ''
        },
        currentChange() {
            // console.log(this.currentPage)
            if (!this.search_flag) {  // 非搜索
                this.fetchData()
            } else {
                this.fetchData()
            }
        },
        sizeChange() {
            if (!this.search_flag) {  // 非搜索
                this.fetchData()
            } else {
                this.fetchData()
            }
        },

        findPeople() {
            if (this.search_name == '') {
                this.$message.error('请先输入有效值');
                return;
            }
            const _this = this
            const data = {
                username: this.search_name,
                page: this.currentPage,
                size: this.size
            }
            request({
                url: 'http://10.16.104.183:8181/twitterUser/findByUsername',
                method: 'post',
                data: data,
            }).then(function (resp) {
                if (resp.data.status == "200") {//返回成功
                    console.log(resp)
                    _this.tableData = resp.data.data.records
                    _this.total = resp.data.data.total
                    _this.search_flag = true
                } else {
                    _this.$message.error('出现错误');
                    return false;
                }
            })
        },

        init_page() { // 没有搜索的时候
            const _this = this
            this.search_flag = false
            const IPage = {
                "page": this.currentPage,
                "size": this.size
            }
            request({
                url: '/twitterUser/getUsers',
                method: 'post',
                data: IPage
            }).then(function (resp) {
                console.log(resp)
                if (resp.status == "200") {
                    console.log(resp)
                    _this.tableData = resp.data.data.records
                    _this.total = resp.data.data.total
                }
                else {
                    _this.$message.error('出错了');
                    return false;
                }
            })

        },
        search_initpage() { // 有搜索的时候
            if (this.search_name == '') {
                this.$message.error('请先输入有效值');
                return;
            }
            this.fetchData()
        },
        returnMain() {
        },
        cancelAdd() {
            this.dialogVisible = false
            return
        },
        completeAdd() {
            const _this = this
            // console.log(this.add_name)
            const data = {
                username: this.add_name
            }
            axios.post('http://10.16.104.183:8181/twitterUser/addUser', data).then(function (resp) {
                console.log(resp)
                if (resp.status == "200") {
                    console.log(resp)
                    _this.$message.success('添加成功');

                }
                else {
                    _this.$message.error('出错了');
                    return false;
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
                username: this.search_name,
                page: this.currentPage,
                size: this.size
            }
            request({
                url: 'http://10.16.104.183:8181/twitterUser/findByPage/' + this.currentPage + '/' + this.size,
                method: 'post',
                data: data
            }).then(function (resp) {
                console.log(resp)
                if (resp.status == "200") {
                    console.log(resp)
                    _this.tableData = resp.data.data.records
                    _this.total = resp.data.data.total
                }
                else {
                    _this.$message.error('出错了');
                    return false;
                }
            })
        },

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
</style>