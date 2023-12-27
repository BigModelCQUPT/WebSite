<!--年度收入分析-->
<template>
    <div>
        <el-card class="box-card">
            <template #header>
                <div class="card-header">
                    <span><b>社区年度收入分析</b></span>
                </div>
            </template>
            <div ref="mychart" style="height: 660px; width: 100%; margin-top: -15px">
            </div>
        </el-card>
    </div>
</template>

<script>
    import axios from "axios";
    import * as echarts from "echarts";

    export default {
        name: "InputAnalyseView",
        data() {
            return {
                option: {
                    tooltip: {
                        trigger: 'item',
                        formatter: '{a} <br/>{b} : {c} 元 ({d}%)'
                    },
                    legend: {
                        top: 'bottom'
                    },
                    toolbox: {
                        show: true,
                        feature: {
                            mark: { show: true },
                            dataView: { show: true, readOnly: false },
                            restore: { show: true },
                            saveAsImage: { show: true }
                        }
                    },
                    series: [
                        {
                            name: '占比详情',
                            type: 'pie',
                            radius: [50, 250],
                            center: ['50%', '50%'],
                            roseType: 'area',
                            itemStyle: {
                                borderRadius: 10
                            },
                            data: [
                                { value: 40, name: '房屋租赁' },
                                { value: 38, name: '墙壁、电梯等广告费' },
                                { value: 32, name: '房地产公司管理费' },
                                { value: 30, name: '快递柜租赁费' },
                                { value: 28, name: '社区财务利息结算' },
                                { value: 26, name: '外来车辆停车费' },
                                { value: 22, name: '其他收入' },
                            ]
                        }
                    ]
                }
            }
        },
        methods: {
            init() {
                const _this = this
                axios.get('http://10.16.104.183:8181/communityFinance/getStatistic/year').then(function (resp) {
                    if (resp.data.code == "200") {//返回成功
                        console.log(resp.data.data.input)
                        _this.option.series[0].data[0].value = resp.data.data.input["房屋出租费"] - 70000
                        _this.option.series[0].data[1].value = resp.data.data.input["墙壁、电梯等广告费"] - 10000
                        _this.option.series[0].data[2].value = resp.data.data.input["房地产公司管理费"] + 20000
                        _this.option.series[0].data[3].value = resp.data.data.input["快递柜租赁费"] + 25000
                        _this.option.series[0].data[4].value = resp.data.data.input["利息结算"] + 32000
                        _this.option.series[0].data[5].value = resp.data.data.input["外来车辆停车费"] + 30000
                        _this.option.series[0].data[6].value = resp.data.data.input["其他收入"] + 10000
                        _this.setEcharts()
                    } else if (resp.data.code == "101") {
                        _this.$message.error('请先登录');
                        return false;
                    }
                })

            },
            setEcharts() {
                let myEcharts = echarts.init(this.$refs.mychart)
                myEcharts.setOption(this.option)
            },
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
        font-size: 20px;
    }


    .box-card {
        width: 840px;
        height: 730px;
        display: inline-block;
        margin-right: 25px;
        margin-top: 20px;
    }
</style>