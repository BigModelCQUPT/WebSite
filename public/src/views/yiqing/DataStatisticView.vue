<template>
    <div class="zuiwaiceng" style="height: 645px">
        <!--        标题-->
        <div class="head"  style="display: inline-flex" >
            <div  >
                <div style="display: flex">
                    <div class="img1" style="width: 152px;">
                        <img style="width: 100%;" class="img1"  src="https://img.alicdn.com/tps/TB1Z8MHPFXXXXacaXXXXXXXXXXX-500-54.gif" alt="">
                    </div>
                    <div class="img1" style="width: 154px;">
                        <img style="width: 100%;" class="img1"  src="https://img.alicdn.com/tps/TB1Z8MHPFXXXXacaXXXXXXXXXXX-500-54.gif" alt="">
                    </div>
                    <div class="img1" style="width: 156px;">
                        <img style="width: 100%;" class="img1"  src="https://img.alicdn.com/tps/TB1Z8MHPFXXXXacaXXXXXXXXXXX-500-54.gif" alt="">
                    </div>
                </div>
                <div style="display: flex">
                    <div class="img1" style="width: 162px;">
                        <img style="width: 100%;" class="img1"  src="https://img.alicdn.com/tps/TB1Z8MHPFXXXXacaXXXXXXXXXXX-500-54.gif" alt="">
                    </div>
                    <div class="img1" style="width: 164px;">
                        <img style="width: 100%;" class="img1"  src="https://img.alicdn.com/tps/TB1Z8MHPFXXXXacaXXXXXXXXXXX-500-54.gif" alt="">
                    </div>
                    <div class="img1" style="width: 166px;">
                        <img style="width: 100%;" class="img1"  src="https://img.alicdn.com/tps/TB1Z8MHPFXXXXacaXXXXXXXXXXX-500-54.gif" alt="">
                    </div>
                </div>
            </div>
            <div class="title" style="width: 100%">防疫信息</div>
            <div  >
                <div style="display: flex; float: right">
                    <div class="img1" style="width: 152px;">
                        <img style="width: 100%;" class="img1"  src="https://img.alicdn.com/tps/TB1Z8MHPFXXXXacaXXXXXXXXXXX-500-54.gif" alt="">
                    </div>
                    <div class="img1" style="width: 154px;">
                        <img style="width: 100%;" class="img1"  src="https://img.alicdn.com/tps/TB1Z8MHPFXXXXacaXXXXXXXXXXX-500-54.gif" alt="">
                    </div>
                    <div class="img1" style="width: 156px;">
                        <img style="width: 100%;" class="img1"  src="https://img.alicdn.com/tps/TB1Z8MHPFXXXXacaXXXXXXXXXXX-500-54.gif" alt="">
                    </div>
                </div>
                <div style="display: flex; float: right">
                    <div class="img1" style="width: 162px;">
                        <img style="width: 100%;" class="img1"  src="https://img.alicdn.com/tps/TB1Z8MHPFXXXXacaXXXXXXXXXXX-500-54.gif" alt="">
                    </div>
                    <div class="img1" style="width: 164px;">
                        <img style="width: 100%;" class="img1"  src="https://img.alicdn.com/tps/TB1Z8MHPFXXXXacaXXXXXXXXXXX-500-54.gif" alt="">
                    </div>
                    <div class="img1" style="width: 166px;">
                        <img style="width: 100%;" class="img1"  src="https://img.alicdn.com/tps/TB1Z8MHPFXXXXacaXXXXXXXXXXX-500-54.gif" alt="">
                    </div>
                </div>
            </div>

        </div>
        <!--        主题-->
        <div class="zhuti">
            <div class="col"  >
                <!--                几个社区居民打卡情况 条形图-->
                <div class="panel">
                    <div style="color: white; margin-top: 10px">社区居民打卡情况</div>
                    <div  ref="mychart_report" style="height: 85%; width: 100%;"></div>
                </div>
                <div class="panel" >
                    <div style="color: white;  margin-top: 10px">社区居民填报率</div>
                    <div  ref="report_rank" style="height: 85%; width: 100%;"></div>
                </div>
                <div class="panel" style="height: 90px">
                    <div style="display: inline-flex" >
                        <span style="color: white; font-size: 18px; margin-top: 10px; margin-left: -30px">当前填报人数</span>
                        <span class="number" style="margin-left: 20px; margin-top: 5px" >5563</span>
                    </div>
                    <br/>
                    <div style="margin-top: 0px;display: inline-flex" >
                        <span class="number" >95.4</span>
                        <span style="color: white; font-size: 18px; margin-left: 40px; margin-top: 6px">填报率(%)</span>
                    </div>
                </div>
            </div>
            <div class="col2" >
                <div class="middle-panel">
                    <div style="color: white; margin-top: 10px; ">进区状态</div>
                    <div style="color:#ffffff; display:flex">
                        <span style="flex: 2; margin-left: 10px">姓名</span>
                        <span style="flex: 1">进区时间</span>
                        <span style="flex: 1">所属区域</span>
                        <span style="flex: 2">入口</span>
                    </div>
                    <div style="color: white;display: flex; margin-top: 13px" v-for="item in into_infor" :key="item">
                        <div class="line row-content" style="margin-left: 10px;height: 20px;text-overflow: ellipsis;overflow: hidden;vertical-align: middle; display: flex;align-items: center;background-color: rgba(0, 59, 81, 0);">

                            <div class="index" style="display: inline-block;text-align: center;vertical-align: middle;width: 12.5%;color: #141313;font-size: 1px;">
                                <div class="index-bg" style="background-color: #f4ff00; font-weight: normal; width: 14.600000000000001px;height: 14.600000000000001px;line-height: 14.600000000000001px;vertical-align: middle;margin: auto;border-radius: 7.300000000000001px;text-align: center;">
                                </div>
                            </div>
                            <div class="column cell-content" style="width: 50%;display:inline-block;text-align: center;white-space: nowrap;overflow: hidden;vertical-align: middle;background-color: transparent;font-size: 12px;color: #ffffff;font-weight: normal;">
                                <div class="marquee-text" style="display: inline-block;">
                                    <span style="display: inline-block;" class="marquee-text-span"></span><i class="whiteSpace" style="display:inline-block;width:0;"></i>
                                    <span style="display: inline-block;" class="marquee-text-span"></span><i class="whiteSpace" style="display:inline-block;width:0;"></i>
                                </div>
                            </div><div class="column cell-content" style="width: 25%;display:inline-block;text-align: center;white-space: nowrap;overflow: hidden;vertical-align: middle;background-color: transparent;font-size: 12px;color: #ffffff;font-weight: normal;">
                            <div class="marquee-text" style="display: inline-block;">
                                <span style="display: inline-block;" class="marquee-text-span"></span><i class="whiteSpace" style="display:inline-block;width:0;"></i>
                                <span style="display: inline-block;" class="marquee-text-span"></span><i class="whiteSpace" style="display:inline-block;width:0;"></i>
                            </div>
                        </div><div class="column cell-content" style="width: 25%;display:inline-block;text-align: center;white-space: nowrap;overflow: hidden;vertical-align: middle;background-color: transparent;font-size: 12px;color: #ffffff;font-weight: normal;">
                            <div class="marquee-text" style="display: inline-block;">
                                <span style="display: inline-block;" class="marquee-text-span"></span><i class="whiteSpace" style="display:inline-block;width:0;"></i>
                                <span style="display: inline-block;" class="marquee-text-span"></span><i class="whiteSpace" style="display:inline-block;width:0;"></i>
                            </div>
                        </div><div class="column cell-content" style="width: 15%;display:inline-block;text-align: center;white-space: nowrap;overflow: hidden;vertical-align: middle;background-color: transparent;font-size: 12px;color: #ffffff;font-weight: normal;">
                            <div class="marquee-text" style="display: inline-block;">
                                <span style="display: inline-block;" class="marquee-text-span"></span><i class="whiteSpace" style="display:inline-block;width:0;"></i>
                                <span style="display: inline-block;" class="marquee-text-span"></span><i class="whiteSpace" style="display:inline-block;width:0;"></i>
                            </div>
                        </div></div>
                        <span style="flex: 2; margin-left: -15px">{{item.name}}</span>
                        <span style="flex: 1">{{item.time}}</span>
                        <span style="flex: 1">{{item.community}}</span>
                        <span style="flex: 2">{{item.status}}</span>
                    </div>
                </div>
                <div class="middle-panel" style="margin-top: 5px">
                    <div style="color: white; margin-top: 10px">实时填报情况</div>
                    <div ref="time_chart" style="height:90%; width: 100%;"  ></div>
                </div>
                <div class="middle-panel" style="height: 90px; margin-top: 5px;">
                    <div style="display: flex; margin-top: 10px; margin-left: 40px">
                        <div style="float: left">
                            <span style="color: white">集中隔离 &nbsp;</span>
                            <span class="nnnumber"> 1</span>
                            <span  style="color: white; font-size: 5px" >人</span>
                        </div>
                        <div style="float: left; margin-left: 50px" >
                            <span style="color: white">确诊病例 &nbsp;</span>
                            <span class="nnnumber"> 0</span>
                            <span  style="color: white; font-size: 5px" >人</span>
                        </div>
                        <div style="float: left;margin-left: 50px">
                            <span style="color: white">密切接触 &nbsp;</span>
                            <span class="nnnumber"> 0</span>
                            <span  style="color: white; font-size: 5px" >人</span>
                        </div>
                    </div>
                    <div style="display: flex; margin-top: 20px; margin-left: 40px">
                        <div style="float: left">
                            <span style="color: white">居家隔离 &nbsp;</span>
                            <span class="nnnumber" style="padding-top: 12px"> 10</span>
                            <span  style="color: white; font-size: 5px" >人</span>
                        </div>
                        <div style="float: left; margin-left: 50px" >
                            <span style="color: white">疑似病例 &nbsp;</span>
                            <span class="nnnumber"> 0</span>
                            <span  style="color: white; font-size: 5px" >人</span>
                        </div>
                        <div style="float: left;margin-left: 50px">
                            <span style="color: white">重点关注 &nbsp;</span>
                            <span class="nnnumber"> 0</span>
                            <span  style="color: white; font-size: 5px" >人</span>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col">
                <div class="panel">
                    <div style="color:#ffffff; margin-top: 10px">实时填报状况</div>
                    <div style="color:#ffffff; display:flex">
                        <span style="flex: 2; margin-left: 10px">填报时间</span>
                        <span style="flex: 1">姓名</span>
                        <span style="flex: 1">社区</span>
                        <span style="flex: 2">健康状况</span>
                    </div>
                    <div style="color: white;display: flex; margin-top: 13px" v-for="item in shishitianbao" :key="item">
                        <div class="line row-content" style="margin-left: 10px;height: 20px;text-overflow: ellipsis;overflow: hidden;vertical-align: middle; display: flex;align-items: center;background-color: rgba(0, 59, 81, 0);">

                            <div class="index" style="display: inline-block;text-align: center;vertical-align: middle;width: 12.5%;color: #141313;font-size: 1px;">
                                <div class="index-bg" style="background-color: #f4ff00; font-weight: normal; width: 14.600000000000001px;height: 14.600000000000001px;line-height: 14.600000000000001px;vertical-align: middle;margin: auto;border-radius: 7.300000000000001px;text-align: center;">
                                </div>
                            </div>

                            <div class="column cell-content" style="width: 50%;display:inline-block;text-align: center;white-space: nowrap;overflow: hidden;vertical-align: middle;background-color: transparent;font-size: 12px;color: #ffffff;font-weight: normal;">



                                <div class="marquee-text" style="display: inline-block;">
                                    <span style="display: inline-block;" class="marquee-text-span"></span><i class="whiteSpace" style="display:inline-block;width:0;"></i>

                                    <span style="display: inline-block;" class="marquee-text-span"></span><i class="whiteSpace" style="display:inline-block;width:0;"></i>

                                </div>

                            </div><div class="column cell-content" style="width: 25%;display:inline-block;text-align: center;white-space: nowrap;overflow: hidden;vertical-align: middle;background-color: transparent;font-size: 12px;color: #ffffff;font-weight: normal;">



                            <div class="marquee-text" style="display: inline-block;">
                                <span style="display: inline-block;" class="marquee-text-span"></span><i class="whiteSpace" style="display:inline-block;width:0;"></i>

                                <span style="display: inline-block;" class="marquee-text-span"></span><i class="whiteSpace" style="display:inline-block;width:0;"></i>

                            </div>

                        </div><div class="column cell-content" style="width: 25%;display:inline-block;text-align: center;white-space: nowrap;overflow: hidden;vertical-align: middle;background-color: transparent;font-size: 12px;color: #ffffff;font-weight: normal;">



                            <div class="marquee-text" style="display: inline-block;">
                                <span style="display: inline-block;" class="marquee-text-span"></span><i class="whiteSpace" style="display:inline-block;width:0;"></i>

                                <span style="display: inline-block;" class="marquee-text-span"></span><i class="whiteSpace" style="display:inline-block;width:0;"></i>

                            </div>

                        </div><div class="column cell-content" style="width: 15%;display:inline-block;text-align: center;white-space: nowrap;overflow: hidden;vertical-align: middle;background-color: transparent;font-size: 12px;color: #ffffff;font-weight: normal;">



                            <div class="marquee-text" style="display: inline-block;">
                                <span style="display: inline-block;" class="marquee-text-span"></span><i class="whiteSpace" style="display:inline-block;width:0;"></i>

                                <span style="display: inline-block;" class="marquee-text-span"></span><i class="whiteSpace" style="display:inline-block;width:0;"></i>

                            </div>

                        </div></div>
                        <span style="flex: 2; margin-left: -15px">{{item.time}}</span>
                        <span style="flex: 1">{{item.name}}</span>
                        <span style="flex: 1">{{item.community}}</span>
                        <span style="flex: 2">{{item.status}}</span>
                    </div>
                </div>
                <div class="panel">
                        <div style="color: white">社区居民疫苗接种情况</div>
                        <div  ref="mychart_yimiao" style="height: 85%; width: 100%;"></div>
                </div>
            </div>
        </div>

    </div>
</template>

<script>
    import * as echarts from "echarts";
    import axios from "axios";

    export default {
        name: 'DataStatisticView',
        data() {
            return{
                option : {
                    tooltip: {
                        trigger: 'axis',
                        axisPointer: {
                            type: 'shadow'
                        },
                        formatter: '{a} <br/>{b} : {c} 人 '

                    },
                    grid: {
                        left: '5%',
                        right: '4%',
                        bottom: '3%',
                        top:'10%',
                        containLabel: true
                    },
                    xAxis: [
                        {
                            type: 'category',
                            data: ['锦溪', '兰锦', '卦畈', '九龙', '怡情苑'],
                            axisTick: {
                                alignWithLabel: true
                            },
                            axisLabel: {
                                interval: 0,
                                rotate: 45, //文字逆时针旋转45°
                                textStyle: {
                                    color: "#ffffff",
                                }
                            }
                        }
                    ],
                    yAxis: [
                        {
                            type: 'value',
                        }
                    ],
                    series: [
                        {
                            name: '人数',
                            type: 'bar',
                            barWidth: '40%',
                            data: [  1334, 1390, 1330,  1300, 1420],
                            itemStyle: {
                                // 修改柱子圆角
                                borderRadius: 15,
                                color:function(params) {
                                    //颜色组
                                    var colorList =['#3E9DE7',  '#C38E32', '#65B581', '#FFCE34', '#FD665F']
                                    return colorList[params.dataIndex]
                                }
                            }
                        }
                    ]
                },
                rank_num : [91.2, 90.3, 93.6, 99.4, 100  ],
                rank_option :{
                    grid: {
                        left: '3%',
                        right: '10%',
                        bottom: '3%',
                        top:'10%',
                        containLabel: true
                    },
                    xAxis: {
                        max: 'dataMax',

                    },
                    yAxis: {
                        axisLabel: {
                            interval: 0,
                            rotate: 45, //文字逆时针旋转45°
                            textStyle: {
                                color: "#ffffff",
                            }
                        },
                        type: 'category',
                        data: ['锦溪', '兰锦', '九龙', '卦畈', '怡情苑'],
                        inverse: true,
                        animationDuration: 300,
                        animationDurationUpdate: 300,
                        max: 4 // only the largest 3 bars will be displayed
                    },
                    series: [
                        {
                            realtimeSort: true,
                            name: '百分比',
                            type: 'bar',
                            data: [91.2, 90.3, 93.6, 99.4, 100 ],
                            barWidth : 10,
                            label: {
                                show: true,
                                position: 'right',
                                valueAnimation: true
                            },
                            itemStyle: {
                                color : '#3E9DE7'
                            }
                        }
                    ],
                    animationDuration: 0,
                    animationDurationUpdate: 3000,
                    animationEasing: 'linear',
                    animationEasingUpdate: 'linear'
                },
                time_option:  {
                    grid: {
                        left: '3%',
                        right: '10%',
                        bottom: '3%',
                        top:'10%',
                        containLabel: true
                    },
                    legend: {
                        top:'3%',
                        left:'15%',
                        textStyle: {
                            color:'#ffffff'
                        }
                    },
                    xAxis: {
                        name :'时间',
                        type: 'category',
                        boundaryGap: false,
                        data: ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10'],
                        axisLabel: {
                            interval: 0,
                            textStyle: {
                                color: "#ffffff",
                            }
                        },
                        nameTextStyle: {
                            color:'#ffffff'
                        }
                    },
                    yAxis: {
                        name: '人',
                        type: 'value',
                        splitLine: {
                            show: false
                        },
                        axisLine: {
                            show: true,
                            interval: 0,
                            inside: true,    // 刻度标签是否朝内，默认朝外
                        },
                        axisLabel: {
                            formatter: '{value} ',
                            interval: 0,
                            textStyle: {
                                color: "#ffffff",
                            }
                        },
                        nameTextStyle: {
                            color:'#ffffff',
                            lineHeight:3
                        },
                        nameLocation:'end'
                    },
                    series: [
                        {
                            name: '今天',
                            type: 'line',
                            smooth: true,
                            data: [1011, 1211, 1311, 1511, 2012, 2552, 2900, 3000,3500, 4000],
                        },
                        {
                            name: '昨天',
                            type: 'line',
                            smooth: true,
                            data: [1311, 1411, 1611, 1911, 2012, 2452, 2500, 3100,3450, 4000],
                        }
                    ]
                },
                shishitianbao:[
                    {
                        time:'08:12:10',
                        name:'谢晗雨',
                        community:'九龙',
                        status:'无状况'
                    },{
                        time:'08:55:41',
                        name:'毛燕婉',
                        community:'锦溪',
                        status:'无状况'
                    },
                    {
                        time:'09:05:12',
                        name:'卢灿灿',
                        community:'锦溪',
                        status:'无状况'
                    },{
                        time:'09:33:01',
                        name:'叶雅丽',
                        community:'兰锦',
                        status:'无状况'
                    },{
                        time:'10:11:10',
                        name:'史咏德',
                        community:'卦畈',
                        status:'无状况'
                    }
                ],
                yimiao_option : {
                    tooltip: {
                        trigger: 'axis',
                        axisPointer: {
                            type: 'shadow'
                        },
                        formatter: '{a} <br/>{b} : {c} 人 '

                    },
                    grid: {
                        left: '3%',
                        right: '4%',
                        bottom: '3%',
                        top:'15%',
                        containLabel: true
                    },
                    xAxis: [
                        {
                            type: 'category',
                            data: ['完成接种', '接种一针', '接种两针', '没有接种'],
                            axisTick: {
                                alignWithLabel: true,

                            },
                            axisLabel: {
                                textStyle: {
                                    color: "#ffffff",
                                }
                            }

                        }
                    ],
                    yAxis: [
                        {
                            type: 'value',
                            axisLabel: {
                                textStyle: {
                                    color: "#ffffff",
                                }
                            }
                        }
                    ],
                    series: [
                        {
                            name: '占比',
                            type: 'bar',
                            barWidth: '40%',
                            data: [  1334, 1390, 1330, 1220],
                            itemStyle: {
                                // 修改柱子圆角
                                barBorderRadius: 10
                            }
                        }
                    ]
                },
                into_infor:[
                    {
                        time:'10:11:31',
                        name:'梁和暄',
                        community:'怡情苑',
                        status:'北大门'
                    },{
                        time:'10:55:41',
                        name:'杨知慧',
                        community:'锦溪',
                        status:'正门'
                    },
                    {
                        time:'12:10:12',
                        name:'马平霞',
                        community:'九龙',
                        status:'正门'
                    },{
                        time:'12:33:01',
                        name:'邹天天',
                        community:'兰锦',
                        status:'南门'
                    },{
                        time:'12:51:14',
                        name:'董思洁',
                        community:'卦畈',
                        status:'西门'
                    }
                ],


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

            },
            setEcharts() {
                let myEcharts = echarts.init(this.$refs.mychart_report)
                myEcharts.setOption(this.option)
                myEcharts = echarts.init(this.$refs.report_rank)
                myEcharts.setOption(this.rank_option)
                myEcharts = echarts.init(this.$refs.time_chart)
                myEcharts.setOption(this.time_option)
                myEcharts = echarts.init(this.$refs.mychart_yimiao)
                myEcharts.setOption(this.yimiao_option)

            }
        },
        created() {
            this.init()
        }

    }
</script>
<style scoped>
    .img1 {
        height: 15px;
        opacity: 1;
        background-size: contain;
        background-repeat: no-repeat;
        background-position: top left;
        background-image: url("https://img.alicdn.com/tps/TB1Z8MHPFXXXXacaXXXXXXXXXXX-500-54.gif");
    }

    .zuiwaiceng {
        background: url("../../assets/img/bg.jpg") no-repeat top center;
    }
    .head {
        background: url(../../assets/img/head_bg.png) no-repeat top center;
        background-size: 100% 100%;
        font-size:14px;
        margin-top: -2px;
    }
    .title {
        text-align: center;
        justify-content: center; color: rgb(35, 204, 251);
        font-weight: bolder;
        font-family: "Microsoft Yahei", Arial, sans-serif;
        font-size: 32px;
        margin-top: -5px;
        font-weight: normal;
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

    }
    .middle-panel {
        height: 250px;
        border: 1px solid rgba(2, 235, 181, 0.7);
    }
    .panel {
        width: 100%;
        height: 250px;
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
        color:#ffeb7b ;
        text-align: center;
        font-size: 28px;
        font-family: electronicFont;
        font-weight: bold;
    }
    .nnnumber {
        color:#ffeb7b ;
        font-size:20px;
        font-weight: bold;
        font-family: "Droid Serif";
    }
</style>