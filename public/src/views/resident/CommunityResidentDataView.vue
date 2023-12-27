<template>
    <div class="zuiwaiceng" style="height: 660px;  ">
        <!--        标题-->
        <div class="head">
            <h1 class="title">居民信息统计</h1>
            <div class="showTime">当前时间：{{datetime}}</div>
        </div>
        <!--        主题-->
        <div class="zhuti">
            <div class="col"  >
                <!--                社区居民年龄分布 柱状图-->
                <div class="panel">
                    <div style="color: white">社区人员年龄分布</div>
                    <div  ref="mychart_age" style="height: 85%; width: 100%;"></div>
                </div>
                <!--                社区居民疫苗接种情况 饼状图-->
                <div class="panel">
                    <div style="color: white">男女比例</div>
                            <div style="display: flex">
                                <img   :src="require('../../assets/male.png')" style="height: 100px; ">
                                <span class="number" style="margin-top: 20px; margin-left: 30px">3774</span>
                            </div>
                            <div style="display: flex">
                                <img   :src="require('../../assets/female.png')" style="height: 100px; margin-left: 50px">
                                <span class="number" style="margin-top: 20px; margin-left: -20px" >2842</span>
                            </div>
                </div>
            </div>
            <div class="col2" >
                <div class="no">
                    <div class="number">
                        <span  >6616</span>
                    </div>
                    <div class="no-title">
                        <span >社区总人数</span>
                    </div>
                </div>
                <div  ref="mychart_map" style="background: rgba(101, 132, 226, 0.1);height: 78%; width: 100%;"></div>
            </div>
            <div class="col">
                <!--                每个社区人数占比 饼状图-->
                <div class="panel2" style="margin-top: 120px">
                    <div style="color: white">各单元人数占比</div>
                    <div  ref="mychart_community" style="height: 90%; width: 90%;"></div>
                </div>
            </div>
        </div>

    </div>
</template>

<script>
    import * as echarts from "echarts";
    import axios from "axios";
    import chinamap from '../../utils/china.json'

    export default {
        name: 'CommunityResidentDataView',
        data() {
            return{
                datetime: '2022年1月1日',
                age_option : {
                    tooltip: {
                        trigger: 'item',
                        formatter: '{a} <br/>{b} : {d}%'
                    },
                    legend: {
                        bottom: "0%",
                        textStyle: {
                            color: "write"
                        }

                    },
                    color:['#45C2E0', '#C1EBDD', '#FFC851','#5A5476','#1869A0','#FF9393'],
                    series: [
                        {
                            name: '具体占比',
                            type: 'pie',
                            radius: ['30%', '50%'],
                            avoidLabelOverlap: false,
                            label: {
                                show: false,
                                position: 'center'
                            },
                            center: [ '50%', '35%'],
                            emphasis: {
                                label: {
                                    show: true,
                                    fontSize: '20',
                                    fontWeight: 'bold'
                                }
                            },
                            labelLine: {
                                show: false
                            },
                            data: [
                                { value: 1048, name: '20岁以下' },
                                { value: 735, name: '20-30岁' },
                                { value: 580, name: '30-40岁' },
                                { value: 484, name: '40-50岁' },
                                { value: 300, name: '50岁以上' }
                            ]
                        }
                    ]
                },
                community_option:{
                    tooltip: {
                        trigger: 'item',
                        formatter: '{a} <br/>{b} : {c} 人 ({d}%)'
                    },
                    legend: {
                        bottom: "-3%",
                        textStyle: {
                            color: "write"
                        }

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
                            radius: [5, 60],
                            center: ['50%', '40%'],
                            roseType: 'area',
                            itemStyle: {
                                borderRadius: 5
                            },
                            data: [
                                { value: 40, name: '锦溪' },
                                { value: 38, name: '兰锦' },
                                { value: 32, name: '卦畈' },
                                { value: 30, name: '怡情苑' },
                                { value: 28, name: '九龙' },
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
                    if(resp.data.code == "200"){//返回成功
                        _this.setEcharts()
                    }else if(resp.data.code == "101"){
                        _this.$message.error('请先登录');
                        return false;
                    }
                })
                this.datetime = new Date().getFullYear() + '年' + (new Date().getMonth() + 1) + '月'  + new Date().getDate() + '日'
            },
            setEcharts() {
                let myEcharts = echarts.init(this.$refs.mychart_age)
                myEcharts.setOption(this.age_option)
                myEcharts = echarts.init(this.$refs.mychart_community)
                myEcharts.setOption(this.community_option)

            },
            async map_init() {
                const ret = await chinamap
                // console.log(ret)
                const option = {
                    title:{
                        text:'人口地域分布',
                        left:20,
                        top: 20,
                        textStyle: {
                            color: '#ffffff'
                        }
                    },
                    geo : {
                        type:'map',
                        map:'china',
                        top:'5%',
                        bottom:'5%',
                        left:'1%',
                        right :'1%',
                        itemStyle: {
                            itemColor: '#2E72BF',
                            borderColor:'#333'
                        },

                    },
                    legend: {
                        left: '3%',
                        bottom: '5%',
                        orient:'vertical',
                        textStyle: {
                            color: "#ffffff",
                        }
                    },

                }
                let myEcharts = echarts.init(this.$refs.mychart_map)
                echarts.registerMap('china', ret)
                myEcharts.setOption(option)

                var mmap = [{
                    name:'九龙',
                    children:[
                        {
                            name:'武汉',
                            value:[114.31, 30.52]
                        },{
                            name:'北京',
                            value:[116.20, 39.56]
                        },{
                            name:'绍兴',
                            value:[120.49, 30.08]
                        },


                    ]
                },{
                    name :'锦溪',
                    children: [
                        {
                            name:'广州',
                            value:[113.28, 23.12]
                        },
                        {
                            name:'义乌',
                            value:[120.07, 29.3]
                        },
                    ]
                },{
                    name :'兰锦',
                    children: [
                        {
                            name:'宁波',
                            value:[121.84, 29.90]
                        },
                        {
                            name:'嘉兴',
                            value:[120.78483,30.74744]
                        },
                        {
                            name:'温州',
                            value:[121.15,27.83]
                        }

                    ]
                },{
                    name :'卦畈',
                    children: [
                        {
                            name:'台州',
                            value:[121.26, 28.64]
                        },
                        {
                            name:'湖州',
                            value:[120.41, 30.84]
                        }
                    ]
                },{
                    name :'怡情苑',
                    children: [
                        {
                            name:'苏州',
                            value:[120.63, 31.30]
                        },{
                            name:'衢州',
                            value:[118.87, 28.96]
                        },
                    ]
                },{
                    name :'锦溪',
                    children: [
                        {
                            name:'广州',
                            value:[113.28, 23.12]
                        }
                    ]
                }]
                const data = mmap.map(item => {
                    return {
                        type: 'effectScatter',
                        rippleEffect:{
                            scale: 5,
                            brushType:'stroke'
                        },
                        name: item.name,
                        data: item.children,
                        coordinateSystem:'geo'
                    }
                })
                const aaa = mmap.map(item => {
                    return {
                        name : item.name
                    }
                })
                const dataoption = {
                    legend: {
                        data: aaa,
                    },
                    series: data
                }
                myEcharts.setOption(dataoption)
            }
        },
        created() {
            this.init()
            this.map_init()
        }

    }
</script>
<style scoped>
    .zuiwaiceng {
        background: url("../../assets/img/bg.jpg") no-repeat top center;
    }
    .head {
        background: url(../../assets/img/head_bg.png) no-repeat top center;
        background-size: 100% 100%;
        font-size:14px;
        margin-top: -2px;
    }
    .showTime {
        float: right;
        margin-top: -50px;
        color: rgba(255, 255, 255, 0.7);
    }
    .title {
        color: #fff;
        text-align: center;
    }
    .zhuti {
        display: flex;
    }
    .col {
        flex: 4;
    }
    .col2 {
        flex: 6;
        margin-left: 10px;
        margin-right: 10px;
        margin-bottom: 5px;
        border: 1px solid rgba(2, 235, 181, 0.7);
    }
    .panel {
        width: 100%;
        height: 255px;
        border: 1px solid rgba(2, 235, 181, 0.7);
        background: rgba(255, 255, 255, 0.04) url(../../assets/img/line.png);
        margin-bottom: 5px;
    }
    .panel2 {
        width: 100%;
        height: 300px;
        border: 1px solid rgba(2, 235, 181, 0.7);
        background: rgba(255, 255, 255, 0.04) url(../../assets/img/line.png);
        margin-bottom: 5px;
    }
    .no {
        background: rgba(101, 132, 226, 0.1);
        padding: 15px;
    }
    .number  {
        float: left;
        margin-left: 50px;
        margin-top: -6px;
        color:#ffeb7b ;
        text-align: center;
        line-height: 50px;
        font-size: 40px;
        font-family: electronicFont;
        font-weight: bold;

    }
    .no-title {
        margin-left: 240px;
        margin-top: 50px;
        font-size: 25px;
        color: white;
        font-family: 华文楷体;

    }
</style>