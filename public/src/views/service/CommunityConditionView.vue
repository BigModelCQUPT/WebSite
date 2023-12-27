<!--民情走访展示-->
<template>
    <div style="margin-left: 10px; margin-top: -20px;margin-right: 10px">
        <!--        数据展示-->
        <h3>民情走访展示</h3>
        <div style="margin-top: 15px">
            <el-table :data="tableData" height="420" border style="width: 100%" :default-sort="{ prop: 'date', order: 'descending' }">
                <el-table-column prop="id" label="编号" width="60"  align="center" fixed="left" />
                <el-table-column prop="name" label="姓名" width="90"  align="center"  />
                <el-table-column prop="address" label="地点" width="100"  align="center"/>
                <el-table-column prop="date" sortable  label="帮扶日期" width="110"  align="center" />
                <el-table-column prop="number" label="走访人数" width="82"  align="center" />
                <el-table-column prop="problem" label="主要问题和诉求" width="300"  align="center" />
                <el-table-column prop="flag" label="是否解决" width="110"  align="center" @click="edit(scope.row)" >
                    <template #default="scope">
                        <el-button v-if="scope.row.flag == '1'" type="success" >已解决</el-button>
                        <el-button v-else-if="scope.row.flag == '2'" type="primary" @click="edit(scope.row)">处理中</el-button>
                        <el-button v-else type="danger" @click="edit(scope.row)">未解决</el-button>

                    </template>

                </el-table-column>
                <el-table-column prop="solve_condition" label="解决情况" width="200"  align="center" />
                <el-table-column prop="note" label="备注" width="220"  align="center" />
            </el-table>
        </div>
    </div>
</template>

<script>
    import axios from 'axios'
    import {  ElMessageBox } from 'element-plus'
    export default {
        name: "CommunityConditionView",
        data(){
            return {
                tableData:[{
                    id:'',
                    name:'',
                    address:'',
                    date:'',
                    number:'',
                    problem:'',
                    solve_condition:'',
                    note:'',
                    flag:'',
                    button_type:'error'
                }],
            }
        },
        created() {
            this.init_page()
        },
        methods:{
            edit(index) {
                const _this = this
                ElMessageBox.confirm(
                    '确认问题已经解决了吗?',
                    '提示',
                    {
                        confirmButtonText: '是的',
                        cancelButtonText: '否',
                        type: 'info',
                    }
                )
                    .then(() => {
                        // 确认
                        // console.log(index)
                        index.flag = '1'
<<<<<<< HEAD
                        axios.post('http://10.16.104.183:8181/communityCondition/edit', index).then(function (resp) {
=======
                        axios.post('http://localhost:8181/communityCondition/edit', index).then(function (resp) {
>>>>>>> ffc14b02a948452209c8504655d41786bdd0c07f
                            if(resp.data.code == "200"){//返回成功
                                _this.$message({
                                    message: '修改成功',
                                    type: 'success'
                                });
                            }else if(resp.data.code == "101"){
                                return false;
                            }
                        })
                    })
                    .catch(() => {
                        // 取消
                        return
                    })
            },
            init_page() {
                const _this = this
<<<<<<< HEAD
                axios.get('http://10.16.104.183:8181/communityCondition/get').then(function (resp) {
=======
                axios.get('http://localhost:8181/communityCondition/get').then(function (resp) {
>>>>>>> ffc14b02a948452209c8504655d41786bdd0c07f
                    if(resp.data.code == "200"){//返回成功
                        // console.log(resp)
                        _this.tableData = resp.data.data
                    }else if(resp.data.code == "101"){
                        return false;
                    }else{
                        console.log("error")
                    }
                })
            }

        },
        components:{
        }
    }
</script>

<style scoped>
</style>