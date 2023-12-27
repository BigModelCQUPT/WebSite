<template>
    <div style="margin-top: -20px">
        <div><h3>历史会议</h3></div>
        <el-table :data="tableData" style="width: 80%; margin: auto;" max-height="450" border align="center">
            <el-table-column fixed prop="id" label="编号" width="60" align="center" />
            <el-table-column prop="theme" label="会议名称" width="220"  >
            </el-table-column>
            <el-table-column prop="date"  label="会议时间" width="250" align="center" />
            <el-table-column prop="address" label="会议地点" width="120" align="center" />
            <el-table-column prop="host" label="主持人" width="100" align="center" />

            <el-table-column
                    fixed="right"
                    label="操作"
                    width="90">
                <template v-slot="scope">
                    <el-button @click="handleClick(scope.row)" type="text" size="small">查看详情</el-button>
                </template >
            </el-table-column>
        </el-table>
    </div>
</template>




<script>
    import axios from "axios";

    export default {
        name:"HistoryMeetingView",
        data(){
            return {
                tableData:[{
                    id:'',
                    theme:'',
                    date:'',
                    address:'',
                    host:'',
                    time :''
                }
                ]
            }
        },
        methods:{
            handleClick(row){
                console.log(row.id)
                window.sessionStorage.setItem("id", row.id)
                this.$router.push('/meetingDetail')
            }
        },
        created() {
            const _this = this
            axios.get('http://10.16.104.183:8181/getMeeting').then(function (resp) {
                // console.log(resp.data)
                if(resp.data.code == "200"){//返回成功
                        _this.tableData = resp.data.data
                }else if(resp.data.code == "101"){
                    _this.$message.error('请先登录');
                    return false;
                }else{
                    console.log("error")
                }
            })
        },
    }

</script>

<style scoped>

</style>