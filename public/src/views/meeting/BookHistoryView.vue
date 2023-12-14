<!--会议预约记录-->
<template>
    <div style="margin-left: 5px; margin-right:5px;margin-top: -25px">
        <div v-for="index in tableData" :key="index"  style="display: inline-flex; margin-top: 15px">
            <el-card class="box-card" style="width: 320px; margin-left: 15px"  align="left">
                <template #header>
                    <div class="card-header">
                        <span><b>{{index.theme}}</b></span>
                    </div>
                </template>
                <div class="content-item"  >
                    <br/>
                    <span>开始时间: </span>{{index.date}}
                    <br/>
                    <span>会议地点: </span>{{index.address}}
                    <br/>
                    <span>会议主持人: </span>{{index.host}}
                </div>
                <div style="margin-top: 10px">
                    <el-popconfirm
                            confirm-button-text="确定"
                            cancel-button-text="取消"
                            icon-color="red"
                            title="确定取消该会议预约吗"
                            @confirm="cancelMeeting(index)"
                            @cancel="cancleDelete()"
                    >
                        <template #reference>
                            <el-button  plain type="primary" style="margin-bottom: 8px ;">取消会议</el-button>
                        </template>
                    </el-popconfirm>
                    <el-button @click="recordMeeting(index)" plain type="primary" style="float: right; margin-bottom: 8px;">记录会议</el-button>
                </div>
            </el-card>
        </div>
    </div>
</template>
<script>

    import axios from 'axios'
    export default {
        name:'BookHistoryView',
        data(){
            return {
                tableData:[
                    {
                        theme: '“八·一”建军节慰问',
                        date:'',
                        start_time:'',
                        address:'',
                        host:'',
                    },{

                    }]
            }
        },
        methods: {
            init() {
                const _this = this
                axios.get('http://localhost:8181/getBookHistory').then(function (resp) {
                    console.log(resp.data)
                    if(resp.data.code == "200"){//返回成功
                        _this.tableData = resp.data.data
                    }else if(resp.data.code == "101"){
                        this.$message.error('出现错误');
                        return false;
                    }
                })
            },
            recordMeeting(index) {
                console.log(index)
                this.$router.push({
                    path:'/recordMeeting',
                    query:{
                        id:index.id
                    },
                })
            },
            cancelMeeting(index) {
                // console.log(index)
                const _this = this
                axios.get('http://localhost:8181/cancelMeeting/' + index.id).then(function (resp) {
                    // console.log(resp.data)
                    if(resp.data.code == "200"){//返回成功
                        _this.$message({
                            message: '会议取消成功',
                            type: 'success'
                        });
                        _this.$router.go(0)
                    }
                })
            },
            cancleDelete() {
                return ;
            }

        },
        created() {
            this.init()
        }
    }
</script>
<style scoped>
    .card-header {
        display: flex;
        justify-content: space-between;
        align-items: center;
        height: 0px;
        color: #3399ff;
        font-family: 华文楷体;

    }


    .box-card {
    }
    .content-item {
        margin-top: -30px;
    }
</style>
