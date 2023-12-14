<template>
    <div style="margin-top: -30px">
        <div><h3>会议记录表</h3></div>
        <el-descriptions
                :column="1"
                border
                style="width: 600px; margin: auto"

        >
            <el-descriptions-item
                    label="会议名称"
                    label-class-name="aaa"
                    label-align="center"
                    align="center"
                    min-width="83px"
            >
                {{theme}}
            </el-descriptions-item>
        </el-descriptions>
        <el-descriptions
                :column="2"
                border
                style="width: 600px;margin: auto"

        >
            <el-descriptions-item  label="会议时间" label-class-name="aaa"  align="center" label-align="center">
                {{time}}
            </el-descriptions-item>
            <el-descriptions-item  label="会议地点" label-class-name="aaa" align="center" label-align="center">
                {{address}}
            </el-descriptions-item>
            <el-descriptions-item  label="会议主持人"  align="center" label-class-name="aaa" label-align="center">
                {{host}}
            </el-descriptions-item>
            <el-descriptions-item  label="会议记录人"   align="center" label-align="center" label-class-name="aaa">
                {{edit}}
            </el-descriptions-item>
            <el-descriptions-item  label="到场人数"    align="center" label-align="center" label-class-name="aaa">
                {{number_of_arrive}}
            </el-descriptions-item>
            <el-descriptions-item  label="缺席人数"   align="center" label-align="center" label-class-name="aaa">
                {{number_of_not_arrive}}
            </el-descriptions-item>
        </el-descriptions>
        <el-descriptions
                :column="1"
                border
                style="width: 600px;margin: auto"
        >
            <el-descriptions-item
                    label="到场人员"
                    label-align="center"
                    label-class-name="aaa"
                    min-width="150px">
                {{member}}
            </el-descriptions-item>
        </el-descriptions>
        <el-descriptions
                :column="1"
                border
                style="width: 600px;margin: auto"
        >
            <el-descriptions-item
                    label="会议内容"
                    label-class-name="aaa"
                    min-width="150px"
                    label-align="center">
                {{content}}
            </el-descriptions-item>
        </el-descriptions>
        <el-descriptions
                :column="1"
                border
                style="width: 600px;margin: auto"
        >
            <el-descriptions-item
                    label="备注"
                    label-class-name="aaa"
                    label-align="center"
                    min-width="150px"
            >
                {{note}}
            </el-descriptions-item>
        </el-descriptions>
    </div>
</template>
<script>
    import axios from 'axios'
    export default {

        name:"MeetingDetailView",
        data(){
            return {
                theme:'',
                time:'2022-04-10',
                address:'三楼大讲堂',
                host:'张三书记',
                member:'张三、刘志云、王 五、赵五  、赵倩张三、刘志云、王 五、赵五  、赵倩张三、刘志云、王 五、赵五  、赵倩张三、刘志云、王 五、赵五  、赵倩',
                number_of_arrive:'111',
                number_of_not_arrive:'0',
                content:'嘻嘻学嘻嘻学嘻嘻学嘻嘻学嘻嘻学嘻嘻学嘻嘻学嘻嘻学嘻嘻学嘻嘻学嘻嘻学嘻嘻学嘻嘻学嘻嘻学嘻嘻学嘻嘻学嘻嘻学嘻嘻学嘻嘻学嘻嘻学嘻嘻学嘻嘻学嘻嘻学嘻嘻学嘻嘻学嘻嘻学嘻嘻学嘻嘻学嘻嘻学嘻嘻学嘻嘻学嘻嘻学嘻嘻学嘻嘻学嘻嘻学嘻嘻学嘻嘻学嘻嘻学嘻嘻学嘻嘻学嘻嘻学嘻嘻学嘻嘻学嘻嘻学嘻嘻学嘻嘻学嘻嘻学嘻嘻学嘻嘻学嘻嘻学嘻嘻学嘻嘻学嘻嘻学嘻嘻学嘻嘻学嘻嘻学嘻嘻学嘻嘻学嘻嘻学嘻嘻学嘻嘻学嘻嘻学嘻嘻学嘻嘻学嘻嘻学嘻嘻学嘻嘻学嘻嘻学嘻嘻学嘻嘻学嘻嘻学嘻嘻学',
                note:'啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊',
                edit:'刘志云'//会议记录人
            }
        },
        created() {
            const _this = this
            axios.post('http://localhost:8181/getMeetingDetail', window.sessionStorage).then(function (resp) {
                console.log(resp.data)
                if (resp.data.code == "200") {//返回成功

                    _this.theme = resp.data.data.theme
                    _this.time = resp.data.data.date
                    _this.address = resp.data.data.address
                    _this.edit= resp.data.data.edit
                    _this.host = resp.data.data.host
                    _this.member = resp.data.data.member
                    _this.number_of_arrive = resp.data.data.number_of_arrive
                    _this.number_of_not_arrive = resp.data.data.number_of_not_arrive
                    _this.content = resp.data.data.content
                    _this.note = resp.data.data.note




                }
            })
        }
    }

</script>

<style scoped>
    .aaa{
        width: 150px;
    }
</style>