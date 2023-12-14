<!--年度支出收入同一个折线图  饼状图  每月的支出折线图 收入折线图   根据鼠标移动展示每月的收入支出对比-->
<template>
    <div>
        <el-card class="box-card">
            <template #header>
            <div class="card-header">
                <span><b>年度月收入与支出对比</b></span>
            </div>
        </template>
            <div ref="mychart1" style="height: 448px; width: 100%; margin-top: -15px">
            </div>
            <div style="margin-top: -40px; display: inline-block; margin-left: -440px; font-size: 20px;">
                <div style="margin-bottom: 10px; color: #3399ff">年度总收入:{{sum_input}} 元</div>
                <div style="color: #42b983;"> 年度总支出:{{sum_output}} 元</div>
            </div>
        </el-card>
    </div>
</template>
<script>
    import  axios from 'axios'
    import * as echarts from 'echarts'
    export default {
        name: 'FinanceStatisticView',
        methods: {
            init() {
                const _this = this
                axios.get('http://localhost:8181/communityFinance/getStatistic/month').then(function (resp) {
                    if(resp.data.code == "200"){//返回成功
                        _this.option.series[0].data = resp.data.data.input
                        _this.option.series[1].data = resp.data.data.output
                        var sum_input = 0
                        var sum_output = 0
                        for(var i = 0; i < 12; i ++) {
                            sum_input += resp.data.data.input[i];
                            sum_output += resp.data.data.output[i];
                        }
                        _this.sum_input = sum_input;
                        _this.sum_output = sum_output
                        _this.setEcharts()
                    }else if(resp.data.code == "101"){
                        _this.$message.error('请先登录');
                        return false;
                    }
                })

            },
            setEcharts() {
                let myEcharts = echarts.init(this.$refs.mychart1)
                myEcharts.setOption(this.option)
            },
        },
        data() {
            return {
                option : {
                    title: {
                        text:''
                    },
                    tooltip: {
                        trigger: 'axis'
                    },
                    legend: {},
                    toolbox: {
                        show: true,
                        feature: {
                            dataZoom: {
                                yAxisIndex: 'none'
                            },
                            dataView: {readOnly: false},
                            magicType: {type: ['line', 'bar']},
                            restore: {},
                            saveAsImage: {}
                        }
                    },
                    xAxis: {
                        type: 'category',
                        boundaryGap: false,
                        data: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月']
                    },
                    yAxis: {
                        type: 'value',
                        axisLabel: {
                            formatter: '{value} 元'
                        }
                    },
                    series: [
                        {
                            name: '收入',
                            type: 'line',
                            data: [ 1, 2, 3, 4, 6],
                            markPoint: {
                                data: [
                                    {type: 'max', name: 'Max'},
                                    {type: 'min', name: 'Min'}
                                ]
                            },
                            markLine: {
                                data: [{type: 'average', name: 'Avg'}]
                            }
                        },
                        {
                            name: '支出',
                            type: 'line',
                            data:  [3, 1.2, 2, 4, 8, 5, 10],
                            markPoint: {
                                data: [
                                    {type: 'max', name: 'Max'},
                                    {type: 'min', name: 'Min'}
                                ]
                            },
                            markLine: {
                                data: [
                                    {type: 'average', name: 'Avg'},
                                    [
                                        {
                                            symbol: 'none',
                                            x: '90%',
                                            yAxis: 'max'
                                        },
                                        {
                                            symbol: 'circle',
                                            label: {
                                                position: 'start',
                                                formatter: 'Max'
                                            },
                                            type: 'max',
                                            name: '最高点'
                                        }
                                    ]
                                ]
                            }
                        }
                    ]
                },
                sum_input:0,
                sum_output:0
            }
        },

        created() {//创建data
            this.init()
            console.log(this.$route.matched)
        },
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
        width: 740px;
        height: 560px;
        display: inline-block;
        margin-right: 25px;
    }
</style>




