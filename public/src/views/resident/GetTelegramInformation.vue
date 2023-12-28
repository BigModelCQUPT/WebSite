<!--获取租户信息展示-->
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


            <el-table :data="tableData" border style="width: 100%">


                <el-table-column prop="id" label="id" width="90" align="center" />
                <el-table-column prop="username" label="用户名" width="120" align="center" />
                <el-table-column prop="user_id" label="用户id" width="90" align="center"></el-table-column>
                <el-table-column prop="message" label="聊天内容" align="center" />
                <el-table-column prop="postal_time" label="发送时间" width="90" align="center" />
                <el-table-column label="查看状态" width="90" align="center">
                    <template #default="tableData">
                        <el-tag v-if="tableData.row.flag === 0" type=" success">未读</el-tag>
                        <el-tag v-else type="success">已读</el-tag>
                    </template>
                </el-table-column>

                <el-table-column prop="" label="是否返回" width="60" align="center" />
                <el-table-column fixed="right" label="操作" width="120" align="center">
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
                </el-table-column>
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


        <!--        添加框  编辑框-->
        <!-- <div>
            <el-dialog title="添加租户信息" width="50%" v-model="dialogVisible"
                style="display: flex; justify-content: space-around; align-items: center">
                <div style="height: 300px">
                    <el-steps direction="vertical" :active="activeindex">
                        <el-step :title="item" v-for="(item, index) in informationItem" :key="index" />
                    </el-steps>
                    <el-input :placeholder="'请输入' + informationItem[index]" v-for="(val, key, index) in information"
                        :key="index" v-show="activeindex == index" style="float: right; width: 400px; margin-top: -150px"
                        v-model="information[key]" @keydown.enter="nextStep"></el-input>
                </div>
                <div style="margin-top: 60px">
                    <el-button type="primary" @click="preStep">{{ activeindex == 0 ? '取消' : '上一步' }}</el-button>
                    <el-button type="primary" @click="nextStep">{{ activeindex == 9 ? '完成' : '下一个' }}</el-button>
                </div>
            </el-dialog>
            <el-dialog title="编辑居民信息" width="50%" v-model="editdialogVisible"
                style="display: flex; justify-content: space-around; align-items: center">
                <div style="height: 300px">
                    <el-steps direction="vertical" :active="activeindex">
                        <el-step :title="item" v-for="(item, index) in informationItem" :key="index" />
                    </el-steps>
                    <el-input :placeholder="'请输入' + informationItem[index]" v-for="(val, key, index) in information"
                        :key="index" v-show="activeindex == index" style="float: right; width: 400px; margin-top: -150px"
                        v-model="information[key]" @keydown.enter="nextStep"></el-input>
                </div>
                <div style="margin-top: 60px">
                    <el-button type="primary" @click="preStep">{{ activeindex == 0 ? '取消' : '上一步' }}</el-button>
                    <el-button type="primary" @click="nextStep">{{ activeindex == 9 ? '完成' : '下一个' }}</el-button>
                </div>
            </el-dialog>
            <el-dialog v-model="detaildialogVisible" title="租户信息" width="50%">
                <el-form :model="form">
                    <div style="display: inline-flex">
                        <div>
                            <el-form-item style="width: 80%">
                                <label style="font-weight: bolder; font-family: 仿宋">租户姓名</label>
                                <el-input v-model="form.tenant_name" disabled></el-input>
                            </el-form-item>
                            <el-form-item style="width: 80%" class="margin-topa">
                                <label style="font-weight: bolder; font-family: 仿宋">租户电话</label>
                                <el-input v-model="form.tenant_tele" disabled></el-input>
                            </el-form-item>

                            <el-form-item style="width: 80%" class="margin-topa">
                                <label style="font-weight: bolder; font-family: 仿宋">身份证号码</label>
                                <el-input v-model="form.tenant_id_no" disabled></el-input>
                            </el-form-item>
                            <el-form-item style="width: 80%" class="margin-topa">
                                <label style="font-weight: bolder; font-family: 仿宋">居住人数</label>
                                <el-input v-model="form.number" disabled></el-input>
                            </el-form-item>
                        </div>
                        <div>
                            <el-form-item style="width: 80%">
                                <label style="font-weight: bolder; font-family: 仿宋">社区</label>
                                <el-input v-model="form.community" disabled></el-input>
                            </el-form-item>
                            <el-form-item style="width: 80%" class="margin-topa">
                                <label style="font-weight: bolder; font-family: 仿宋">房屋地址</label>
                                <el-input v-model="form.house_id" disabled></el-input>
                            </el-form-item>
                            <el-form-item style="width: 80%" class="margin-topa">
                                <label style="font-weight: bolder; font-family: 仿宋">房东姓名</label>
                                <el-input v-model="form.owner_name" disabled></el-input>
                            </el-form-item>
                            <el-form-item style="width: 80%" class="margin-topa">
                                <label style="font-weight: bolder; font-family: 仿宋">房东电话</label>
                                <el-input v-model="form.owner_tele" disabled></el-input>
                            </el-form-item>

                        </div>
                    </div>
                    <div style="margin-left: 30px">
                        <el-form-item style="width: 85%" class="margin-topa">
                            <label style="font-weight: bolder; font-family: 仿宋">备注</label>
                            <el-input v-model="form.note" disabled></el-input>
                        </el-form-item>
                    </div>
                </el-form>
                <template #footer>
                    <span class="dialog-footer">
                        <el-button round style="margin-top: -30px" @click="returnMain">返回</el-button>
                    </span>
                </template>
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

                keywordData: [],
                tableData: [{
                    id: '123',
                    username: 'abcd',
                    user_id: '1232342',
                    message: 'alsifgeuf',
                    postal_time: '1312434',
                    flag: '0',
                }, {
                }],
                search_text: '',
                total: 0,//总条数
                currentPage: 1,//第几页
                size: 10,//每页条数
                dialogVisible: false,
                editdialogVisible: false,
                activeindex: 0,
                informationItem: [
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
                information: {
                    community: '',
                    house_id: '',
                    owner_name: '',
                    owner_tele: '',
                    tenant_name: '',
                    tenant_tele: '',
                    number: '',
                    tenant_id_no: '',
                    note: ''
                },
                filename: '',
                detaildialogVisible: false,
                form: {
                    community: '孤帆',
                    tenant_name: 'lisa',
                    tenant_tele: '12344534332',
                    tenant_id_no: '411244323322222222',
                    owner_name: 'lisa',
                    owner_tele: '11111111111',
                    house_id: '2-10-123',
                    number: '12',
                    note: '外来务工人员，平时都在公司员工宿舍住'

                },

                keyworddialogVisible: false,
                keywordInputValue: '',
                search_keyword: '',
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
                axios.post('http://10.16.104.183:8181/telegram/upload/' + name).then(function (resp) {
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
            editClick(row) {
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
                if (this.activeindex == 9) {

                    const _this = this
                    if (this.dialogVisible == true) {//添加
                        if (this.information.id_no == '') {
                            this.$message.error('请输入身份证号码');
                            return
                        }
                        if (this.information.name == '') {
                            this.$message.error('请输入姓名');
                            return
                        }
                        axios.post('http://10.16.104.183:8181/telegramInformation/add', this.information).then(function (resp) {
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
                    else if (this.editdialogVisible == true) {//编辑
                        if (this.information.id_no == '') {
                            this.$message.error('请输入身份证号码');
                            return
                        }
                        if (this.information.name == '') {
                            this.$message.error('请输入姓名');
                            return
                        }
                        // console.log(this.information)
                        axios.post('http://10.16.104.183:8181/telegramInformation/edit', this.information).then(function (resp) {
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
                    return;
                }
                this.activeindex++;
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
                    url: '/telegram/findByPage/' + this.currentPage + '/' + this.size,
                    method: 'post',
                    data: data
                }).then(function (resp) {
                    if (resp.status == "200") {
                        _this.tableData = resp.data.records
                        _this.total = resp.data.total
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
                    url: 'http://10.16.104.183:8181/telegram/analysisByGPT',
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
                    url: 'http://10.16.104.183:8181/telegram/keyword/listAll',
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
                    url: 'http://10.16.104.183:8181/telegram/keyword/delete/' + id,
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
                    url: 'http://10.16.104.183:8181/telegram/keyword/add',
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
</style>