<!--年度支出分析-->
<template>
    <div>
        <el-card class="box-card">
            <template #header>
                <div class="card-header">
                    <span><b>社区年度支出分析</b></span>
                </div>
            </template>
            <div ref="mychart" style="height: 560px; width: 100%; margin-top: -15px">
            </div>
        </el-card>
    </div>
</template>

<script>
    import axios from "axios";
    import * as echarts from "echarts";

    export default {
        name: "OutputAnalyseView",
        data(){
            return {
                option :{
                    tooltip: {
                        trigger: 'item',
                        formatter: '{a} <br/>{b}: {c} ({d}%)'
                    },
                    legend: {
                        data: [
                            '购买社区公共设备',
                            '公共区域设备能耗费用',
                            '社区公共植物栽培及养护',
                            '社区日常活动支出',
                            '公共设备维修、保养费用',
                            '其他支出',
                            '疫情期间防疫活动支出',
                            '居委会办公支出',

                        ]
                    },
                    series: [
                        // {
                        //     name: 'Access From',
                        //     type: 'pie',
                        //     selectedMode: 'single',
                        //     radius: [0, '30%'],
                        //     label: {
                        //         position: 'inner',
                        //         fontSize: 14
                        //     },
                        //     labelLine: {
                        //         show: false
                        //     },
                        //     data: [
                        //         { value: 1548, name: 'Search Engine' },
                        //         { value: 775, name: 'Direct' },
                        //         { value: 679, name: 'Marketing', selected: true }
                        //     ]
                        // },
                        {
                            name: '具体占比',
                            type: 'pie',
                            radius: ['25%', '40%'],
                            labelLine: {
                                length: 30
                            },
                            label: {
                                formatter: '{a|{a}}{abg|}\n{hr|}\n  {b|{b}：}{c}元 {per|{d}%}  ',
                                backgroundColor: '#F6F8FC',
                                borderColor: '#8C8D8E',
                                borderWidth: 1,
                                borderRadius: 4,
                                rich: {
                                    a: {
                                        color: '#6E7079',
                                        lineHeight: 22,
                                        align: 'center'
                                    },
                                    hr: {
                                        borderColor: '#8C8D8E',
                                        width: '100%',
                                        borderWidth: 1,
                                        height: 0
                                    },
                                    b: {
                                        color: '#4C5058',
                                        fontSize: 14,
                                        fontWeight: 'bold',
                                        lineHeight: 33
                                    },
                                    per: {
                                        color: '#fff',
                                        backgroundColor: '#4C5058',
                                        padding: [3, 4],
                                        borderRadius: 4
                                    }
                                }
                            },
                            data: [
                                { value: 1048, name: '购买社区公共设备' },
                                { value: 335, name: '公共区域设备能耗费用' },
                                { value: 310, name: '社区公共植物栽培及养护' },
                                { value: 251, name: '社区日常活动支出' },
                                { value: 234, name: '公共设备维修、保养费用' },
                                { value: 147, name: '其他支出' },
                                { value: 135, name: '疫情期间防疫活动支出' },
                                { value: 102, name: '居委会办公支出' }
                            ]
                        }
                    ]
                }
            }
        },
        methods:{
            init() {
                const _this = this
<<<<<<< HEAD
                axios.get('http://10.16.104.183:8181/communityFinance/getStatistic/year').then(function (resp) {
=======
                axios.get('http://localhost:8181/communityFinance/getStatistic/year').then(function (resp) {
>>>>>>> ffc14b02a948452209c8504655d41786bdd0c07f
                    if(resp.data.code == "200"){//返回成功
                        console.log(resp.data.data.output)
                        _this.option.series[0].data[0].value =resp.data.data.output["购买社区公共设备"]
                        _this.option.series[0].data[1].value =resp.data.data.output["公共区域设备能耗费用"]
                        _this.option.series[0].data[2].value =resp.data.data.output["社区公共植物栽培以及养护"]
                        _this.option.series[0].data[3].value =resp.data.data.output["日常活动支出"]
                        _this.option.series[0].data[4].value =resp.data.data.output["公共设备维修、保养费用"]
                        _this.option.series[0].data[5].value =resp.data.data.output["其他支出"]
                        _this.option.series[0].data[6].value =resp.data.data.output["疫情期间防疫活动支出"]
                        _this.option.series[0].data[7].value =resp.data.data.output["居委会办公支出"]
                        _this.setEcharts()
                    }else if(resp.data.code == "101"){
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