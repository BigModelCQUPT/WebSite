<!--获取居民信息展示-->
<template>
    <div style="margin-left: 10px; margin-top: 10px;margin-right: 10px">
        <!--        工具栏-->
        <div class="toolbar">
            <div>
                <el-input clearable @clear="init_page" @keydown.enter="findPeople" class="el-input-resident"
                    v-model="search_name" placeholder="请输入人员名称进行搜索...">
                </el-input>
                <el-button type="primary" @click="findPeople">
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
                <el-table-column type="selection" width="55" align="center" fixed="left" />
                <el-table-column prop="community" label="社区" width="90" align="center" />
                <el-table-column prop="name" label="姓名" width="75" align="center" fixed="left" />
                <el-table-column prop="house_id" label="房屋地址" width="100" align="center" />
                <el-table-column prop="landlord_name" label="户主" width="75" align="center" />
                <el-table-column prop="relationship" label="与户主关系" width="100" align="center" />
                <el-table-column prop="gender" label="性别" width="55" align="center" />
                <el-table-column prop="age" label="年龄" width="55" align="center" />
                <el-table-column prop="political_role" label="政治面貌" width="110" align="center" />
                <el-table-column prop="nation" label="民族" width="55" align="center" />
                <el-table-column prop="native_place" label="籍贯" width="100" align="center" />
                <el-table-column prop="id_no" label="身份证号码" width="210" align="center" />
                <el-table-column prop="tele" label="电话号码" width="120" align="center" />
                <el-table-column prop="note" label="备注" width="220" align="center" />
                <el-table-column fixed="right" label="操作" width="200">
                    <template v-slot="scope">
                        <el-button @click="getDetail(scope.row)" type="success" size="small">详情</el-button>
                        <el-button @click="editClick(scope.row)" type="primary" size="small">编辑</el-button>
                        <el-popconfirm confirm-button-text="确定" cancel-button-text="取消" icon-color="red" title="确定删除该条信息吗"
                            @confirm="deleteItem(scope.row)" @cancel="cancleDelete()">
                            <template #reference>
                                <el-button type="danger" size="small">删除</el-button>
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
        <!--        添加框  编辑框-->
        <div>
            <el-dialog title="添加居民信息" width="50%" v-model="dialogVisible"
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
                    <el-button type="primary" @click="nextStep">{{ activeindex == 13 ? '完成' : '下一个' }}</el-button>
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
                    <el-button type="primary" @click="nextStep">{{ activeindex == 13 ? '完成' : '下一个' }}</el-button>
                </div>
            </el-dialog>
            <el-dialog v-model="detaildialogVisible" title="居民信息" width="50%">
                <el-form :model="form">
                    <div style="display:inline-flex;" class="margin-topa">
                        <el-form-item style="width: 20%; margin-left: 20px">
                            <label style="font-weight: bolder; font-family: 仿宋">姓名</label>
                            <el-input v-model="form.name" disabled></el-input>
                        </el-form-item>
                        <el-form-item style="width: 20%; margin-left: 20px">
                            <label style="font-weight: bolder; font-family: 仿宋">性别</label>
                            <el-input v-model="form.gender" disabled></el-input>
                        </el-form-item>
                        <el-form-item style="width: 20%; margin-left: 20px">
                            <label style="font-weight: bolder; font-family: 仿宋">户主</label>
                            <el-input v-model="form.landlord_name" disabled></el-input>
                        </el-form-item>
                        <el-form-item style="width: 20%; margin-left: 20px">
                            <label style="font-weight: bolder; font-family: 仿宋">与户主关系</label>
                            <el-input v-model="form.relationship" disabled></el-input>
                        </el-form-item>
                    </div>
                    <div style="display:inline-flex;" class="margin-topa">
                        <el-form-item style="width: 20%; margin-left: 20px">
                            <label style="font-weight: bolder; font-family: 仿宋">年龄</label>
                            <el-input v-model="form.age" disabled></el-input>
                        </el-form-item>
                        <el-form-item style="width: 20%; margin-left: 20px">
                            <label style="font-weight: bolder; font-family: 仿宋">民族</label>
                            <el-input v-model="form.nation" disabled></el-input>
                        </el-form-item>
                        <el-form-item style="width: 20%; margin-left: 20px">
                            <label style="font-weight: bolder; font-family: 仿宋">籍贯</label>
                            <el-input v-model="form.native_place" disabled></el-input>
                        </el-form-item>
                        <el-form-item style="width: 20%; margin-left: 20px">
                            <label style="font-weight: bolder; font-family: 仿宋">政治面貌</label>
                            <el-input v-model="form.political_role" disabled></el-input>
                        </el-form-item>
                    </div>
                    <div style="display:inline-flex;" class="margin-topa">
                        <el-form-item style="width: 20%; margin-left: 20px">
                            <label style="font-weight: bolder; font-family: 仿宋">所在社区</label>
                            <el-input v-model="form.house_id" disabled></el-input>
                        </el-form-item>
                        <el-form-item style="width: 43%; margin-left: 20px">
                            <label style="font-weight: bolder; font-family: 仿宋">身份证号码</label>
                            <el-input v-model="form.id_no" disabled></el-input>
                        </el-form-item>
                        <el-form-item style="width: 20%; margin-left: 20px">
                            <label style="font-weight: bolder; font-family: 仿宋">联系方式</label>
                            <el-input v-model="form.tele" disabled></el-input>
                        </el-form-item>
                    </div>
                    <div class="margin-topa">
                        <el-form-item style="width: 90%; margin-left: 20px">
                            <label style="font-weight: bolder; font-family: 仿宋">备注</label>
                            <el-input type="textarea" :rows="3" v-model="form.note" disabled></el-input>
                        </el-form-item>
                    </div>
                </el-form>
                <template #footer>
                    <span class="dialog-footer">
                        <el-button round @click="returnMain">返回</el-button>
                    </span>
                </template>
            </el-dialog>
        </div>
    </div>
</template>

<script>
import axios from 'axios'
import { Search, Plus, FolderAdd, Upload } from '@element-plus/icons-vue'
import { export_retailer } from "@/utils/api";

export default {
    name: "GetResidentInformation",
    data() {
        return {
            tableData: [{
                community: '卦畈社区',
                house_id: '1-1-101',
                landlord_name: '张三啊',
                name: '彭于晏',
                gender: '男',
                age: '30',
                political_role: '中共预备党员',
                relationship: '父子',
                nation: '汉',
                native_place: '河南信阳',
                id_no: '311400112301372347',
                tele: '18766389207',
                note: '高龄产妇，正在准备生二胎'
            }],
            search_name: '',
            total: 0,//总条数
            currentPage: 1,//第几页
            size: 10,//每页条数
            dialogVisible: false,
            editdialogVisible: false,
            activeindex: 0,
            informationItem: [
                '社区名称',
                '家庭地址',
                '户主姓名',
                '姓名',
                '性别',
                '年龄',
                '政治面貌',
                '与户主关系',
                '民族',
                '籍贯',
                '身份证号码',
                '手机号',
                '备注'
            ],
            information: {
                community: '',
                house_id: '',
                landlord_name: '',
                name: '',
                gender: '',
                age: '',
                political_role: '',
                relationship: '',
                nation: '',
                native_place: '',
                id_no: '',
                tele: '',
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

            }
        }
    },
    created() {
        this.init_page()
    },
    methods: {
        getDetail(row) {
            // console.log(row)
            this.form = row
            this.detaildialogVisible = true

        },
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
            axios.get('http://localhost:8181/residentInformation/delete/' + row.id_no).then(function (resp) {
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
        cancleDelete() {
            return
        },
        editClick(row) {
            // console.log(row.id_no)
            this.editdialogVisible = true
            this.information["community"] = row.community
            this.information["house_id"] = row.house_id
            this.information["landlord_name"] = row.landlord_name
            this.information["name"] = row.name
            this.information["gender"] = row.gender
            this.information["age"] = row.age
            this.information["political_role"] = row.political_role
            this.information["relationship"] = row.relationship
            this.information["nation"] = row.nation
            this.information["native_place"] = row.native_place
            this.information["id_no"] = row.id_no
            this.information["tele"] = row.tele
            this.information["note"] = row.note
        },
        nextStep() {
            if (this.activeindex == 13) {

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
                    axios.post('http://localhost:8181/residentInformation/add', this.information).then(function (resp) {
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
                    axios.post('http://localhost:8181/residentInformation/edit', this.information).then(function (resp) {
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
            // else if (this.activeindex == 13){
            //     this.dialogVisible = false
            //     // this.$router.go(0)
            //     this.information = null
            //     return ;
            // }
            this.activeindex--;
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
            if (this.search_name == '') {
                this.$message.error('请先输入有效值');
                return;
            }
            const _this = this
            axios.get('http://localhost:8181/residentInformation/find/' + this.search_name).then(function (resp) {
                if (resp.data.code == "200") {//返回成功
                    console.log(resp)
                    _this.tableData = resp.data.data.content
                    _this.total = resp.data.data.length
                } else if (resp.data.code == "101") {
                    _this.$message.error('出现错误');
                    return false;
                } else {
                    console.log("error")
                }
            })
        },
        init_page() {
            const _this = this
            axios.get('http://localhost:8181/' +
                'getResidentInformation/'
                + this.currentPage + '/' +
                this.size).then(function (resp) {
                    if (resp.data.code == "200") {//返回成
                        _this.tableData = resp.data.data.content
                        _this.total = resp.data.data.totalElements
                    }
                    else if (resp.data.code == "101") {
                        _this.$message.error('请先登录');
                        return false;
                    }
                })
        },
        returnMain() {
            this.detaildialogVisible = false;
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
</style>