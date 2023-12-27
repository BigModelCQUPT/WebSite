<!--每日一报后端-->
<template>
    <div style="margin-left: 10px; margin-right: 10px; margin-top: -20px">
        <el-button @click="lookDanger" type="danger" style="float: right; margin-bottom: 10px">异常人员</el-button>
        <div>
            <el-table :data="tableData" border style="width: 100%">
                <el-table-column type="selection" width="55" align="center" fixed="left" />
                <el-table-column prop="name" label="姓名" width="75" align="center" fixed="left" />
                <el-table-column prop="id_no" label="身份证号码" width="210" align="center" />
                <el-table-column prop="temperature" label="体温情况" width="120" align="center" />
                <el-table-column prop="status" label="身体状况" width="220" align="center" />
                <el-table-column prop="address" label="详细地址" width="220" align="center" />
                <el-table-column prop="datetime" label="填报时间" width="150" align="center" />
                <el-table-column fixed="right" label="操作" width="80" align="center">
                    <template v-slot="scope">
                        <el-button @click="getDetail(scope.row)" type="success">详情</el-button>
                    </template>
                </el-table-column>
            </el-table>
            <div style="display: flex;justify-content: flex-end; margin-top: 10px">
                <el-pagination background layout="sizes, prev, pager, next, jumper, ->, total, slot" :total="total"
                    :page-sizes="[10, 20,30, 50]" @size-change="sizeChange" @current-change="currentChange"
                    v-model:currentPage="currentPage" v-model:page-size="size" />
            </div>
        </div>
        <div>
            <el-dialog v-model="detaildialogVisible" title="居民信息" width="40%">
                <el-form :model="form">
                    <div style="display:inline-flex;" class="margin-topa">
                        <el-form-item style="width: 20%; margin-left: 20px">
                            <label style="font-weight: bolder; font-family: 仿宋">姓名</label>
                            <el-input v-model="form.name" disabled></el-input>
                        </el-form-item>
                        <el-form-item style="width: 60%; margin-left: 20px">
                            <label style="font-weight: bolder; font-family: 仿宋">身份证号码</label>
                            <el-input v-model="form.id_no" disabled></el-input>
                        </el-form-item>
                    </div>
                    <div style="display:inline-flex;" class="margin-topa">
                        <el-form-item style="width: 25%; margin-left: 20px">
                            <label style="font-weight: bolder; font-family: 仿宋">体温</label>
                            <el-input v-model="form.temperature" disabled></el-input>
                        </el-form-item>
                        <el-form-item style="width: 55%; margin-left: 20px">
                            <label style="font-weight: bolder; font-family: 仿宋">地址</label>
                            <el-input v-model="form.address" disabled></el-input>
                        </el-form-item>
                    </div>
                    <div class="margin-topa">
                        <el-form-item style="width: 84%; margin-left: 20px">
                            <label style="font-weight: bolder; font-family: 仿宋">健康状况</label>
                            <el-input type="textarea" :rows="3" v-model="form.status" disabled></el-input>
                        </el-form-item>
                    </div>
                    <div class="margin-topa">
                        <el-form-item style="width: 85%; margin-left: 20px">
                            <label style="font-weight: bolder; font-family: 仿宋">填报时间</label>
                            <el-input v-model="form.datetime" disabled></el-input>
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

    export default {
        name: "GetResidentInformation",
        data() {
            return {
                tableData: [{
                    name: '彭于晏',
                    id_no: '311400112301372347',
                    address: '',
                    status: '',
                    temperature: '',
                    datetime: ''
                }],
                total: 0,//总条数
                currentPage: 1,//第几页
                size: 10,//每页条数
                activeindex: 0,
                detaildialogVisible: false,
                form: {
                    name: '',
                    id_no: '',
                    address: '',
                    status: '',
                    temperature: '',
                    datetime: ''
                }

            }
        },
        created() {
            this.init_page()
        },
        methods: {
            lookDanger() {
                const _this = this
                axios.get('http://10.16.104.183:8181/dailyAdmin/getDanger').then(function (resp) {
                    console.log(resp.data.data)
                    if (resp.data.code == "200") {//返回成功
                        _this.tableData = resp.data.data.infor
                        _this.total = resp.data.data.size
                    }
                })
            },
            getDetail(row) {
                this.form = row
                this.detaildialogVisible = true

            },
            showDialog() {
                this.activeindex = 0
            },
            currentChange() {
                // console.log(this.currentPage)
                this.init_page()
            },
            sizeChange() {
                // console.log(this.size)
                this.init_page()

            },
            init_page() {
                const _this = this
                axios.get('http://10.16.104.183:8181/dailyAdmin/get/' + this.currentPage + '/' +
                    this.size).then(function (resp) {
                        if (resp.data.code == "200") {//返回成
                            // console.log(resp)
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