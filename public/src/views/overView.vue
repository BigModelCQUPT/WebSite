<template>
    <div class="dashboard-container">
        <el-card shadow="never">
            <el-col>
                <el-row :gutter="20" class="mgb20">
                    <el-col :span="12">
                        <el-card shadow="hover" :body-style="{ padding: '0px' }" class="usercount">
                            <div class="grid-content grid-con-1">
                                <el-icon class="grid-con-icon">
                                    <User />
                                </el-icon>
                                <div class="grid-cont-right">
                                    <div class="grid-num">{{ Math.round(twitterUserCount) }}</div>
                                    <div>用户推特总数</div>
                                </div>
                            </div>
                        </el-card>
                    </el-col>
                    <el-col :span="12">
                        <el-card shadow="hover" :body-style="{ padding: '0px' }" class="tweetnum">
                            <div class="grid-content grid-con-2">
                                <el-icon class="grid-con-icon">
                                    <ChatDotRound />
                                </el-icon>
                                <div class="grid-cont-right">
                                    <div class="grid-num">{{ Math.round(totalTweet) }}</div>
                                    <div>推文总数</div>
                                </div>
                            </div>
                        </el-card>
                    </el-col>
                </el-row>
            </el-col></el-card>

    </div>


    <div class="container">
        <el-row :gutter="20">
            <el-col :span="12">
                <el-card shadow="hover" class="chart-card1">
                    <div ref="getTopicKeywordChart" style="height: 280px; width: 100%; margin-top: 0px">
                    </div>
                </el-card>
            </el-col>
            <el-col :span="12">
                <el-card shadow="hover" style="height: 320px" class="chart-card2">
                    <h3 style="margin-top: 0px ">词云统计</h3>
                    <div ref="getWordCloudChart" style="height: 300px; width: 100%; margin-top: -35px"></div>

                </el-card>

            </el-col>
        </el-row>


    </div>

    <div class="container">
        <el-row :gutter="20">
            <el-col :span="12">
                <el-card shadow="hover" style="height: 360px">
                    <!-- <h3 style="margin-top: 0px">热门话题趋势统计</h3> -->
                    <h3 style="margin-top: 0px color=#505050 ">热门话题趋势统计</h3>
                    <div ref="getTrendChart" style="height: 280px; width:90%; margin-top: -20px">
                    </div>
                </el-card>
            </el-col>
            <el-col :span="12">
                <el-card shadow="hover" style="height: 360px">
                    <h3 style="margin-top: 0px color #505050">活跃账户</h3>
                    <div ref="getActivateUserChart" style="height: 300px;  margin-top: -40px;margin-left: -55px"></div>
                </el-card>

            </el-col>
        </el-row>


    </div>
</template>
<script>
    import * as echarts from "echarts";
    // import { ref } from 'vue'
    // import axios from 'axios'
    import request from '@/utils/http'
    // eslint-disable-next-line no-unused-vars
    import {  /*Fold, Expand,*/  User, ChatDotRound } from '@element-plus/icons-vue'
    //  import wordcloud from 'vue-wordcloud'

    import 'echarts-wordcloud'

    // eslint - disable - next - line no - undef

    export default {


        data() {
            return {
                chart: null,
                twitterUserCount: 0,
                totalTweet: 0,
                activateUserData: [],
                topicKeywordData: [],
                wordCloudData: [],
                trendOption: {
                    title: {
                        text: ''
                    },
                    tooltip: {
                        trigger: 'axis'
                    },
                    legend: {
                        data: ['Email', 'Union Ads', 'Video Ads', 'Direct', 'Search Engine']
                    },
                    grid: {
                        left: '3%',
                        right: '4%',
                        bottom: '3%',
                        containLabel: true
                    },
                    toolbox: {
                        feature: {
                            saveAsImage: {}
                        }
                    },
                    xAxis: {
                        type: 'category',
                        boundaryGap: false,
                        data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
                    },
                    yAxis: {
                        type: 'value'
                    },
                    series: [
                        {
                            name: 'Email',
                            type: 'line',
                            stack: 'Total',
                            data: [120, 132, 101, 134, 90, 230, 210]
                        },
                        {
                            name: 'Union Ads',
                            type: 'line',
                            stack: 'Total',
                            data: [220, 182, 191, 234, 290, 310, 11]
                        },
                        {
                            name: 'Video Ads',
                            type: 'line',
                            stack: 'Total',
                            data: [150, 232, 201, 154, 190, 330, 410]
                        },
                        {
                            name: 'Direct',
                            type: 'line',
                            stack: 'Total',
                            data: [320, 332, 301, 334, 390, 330, 320]
                        },
                        {
                            name: 'Search Engine',
                            type: 'line',
                            stack: 'Total',
                            data: [820, 932, 901, 934, 1290, 1330, 1320]
                        }
                    ]
                }


            }
        },

        created() {
            this.getNumberUser()
            this.getNumberTweet()
        },
        components: {
    /* Fold, Expand,*/  User, ChatDotRound,
        },
        mounted() {
            // let myEcharts = echarts.init(this.$refs.mychart)
            // myEcharts.setOption(this.option)
            // this.initEcharts();
            // let myEchart = echarts.init(this.$refs.mychart1)
            // myEchart.setOption(this.chartoption)
            // let myEcharts3 = echarts.init(this.$refs.mychart3)
            // myEcharts3.setOption(this.option2)
            this.getActivateUser()
            this.getTopicKeyword()
            this.KeywordTrend()
        },


        methods: {
            getNumberTweet() {
                const _this = this
                request({
                    url: '/tweet/getNumberTweet',
                    method: 'get',
                }).then(function (resp) {
                    if (resp.status == "200") {
                        _this.totalTweet = resp.data.data.totalTweet
                    }
                    else {
                        _this.$message.error('出错了');
                        return false;
                    }
                })
            },
            getNumberUser() {
                const _this = this
                request({
                    url: '/twitterUser/getNumberUser',
                    method: 'get',
                }).then(function (resp) {
                    if (resp.status == "200") {
                        _this.twitterUserCount = resp.data.data.totalUser
                    }
                    else {
                        _this.$message.error('出错了');
                        return false;
                    }
                })
            },

            getActivateUser() {
                const _this = this
                request({
                    url: '/overview/account',
                    method: 'get',
                }).then(function (resp) {
                    if (resp.status == "200") {
                        for (var i = 0; i < 5; i++) {
                            _this.activateUserData[i] = resp.data.data[i]
                        }
                        // _this.tableOption2 = resp.data.data;
                        _this.activateUserChart()
                    }
                    else {
                        _this.$message.error('出错了');
                        return false;
                    }
                })
            },
            activateUserChart() {
                const _this = this
                this.userChart = echarts.init(this.$refs.getActivateUserChart)

                const optionActivateUser = {
                    dataset: {
                        source: _this.activateUserData
                    },
                    grid: { containLabel: true },
                    xAxis: { name: '推文数', type: 'value' },
                    yAxis: { name: '用户名', type: 'category', inverse: true },
                    visualMap: {
                        orient: 'horizontal',
                        left: 'center',
                        min: _this.activateUserData[_this.activateUserData.length - 1].tweetCnt,
                        max: _this.activateUserData[0].tweetCnt,
                        text: ['High', 'Low'],
                        // Map the score column to color
                        dimension: 0,
                        inRange: {
                            color: ['#65B581', '#FFCE34', '#FD665F']
                        }
                    },
                    series: [
                        {
                            type: 'bar',
                            encode: {
                                // Map the "amount" column to X axis.
                                x: 'amount',
                                // Map the "product" column to Y axis
                                y: 'product'
                            }
                        }
                    ]
                }
                this.userChart.setOption(optionActivateUser)
            },



            // 主要话题
            getTopicKeyword() {
                const _this = this
                request({
                    url: '/overview/topickeyword',
                    method: 'get',
                }).then(function (resp) {
                    if (resp.status == "200") {
                        for (var i = 0; i < 5; i++) {
                            _this.topicKeywordData[i] = resp.data.data[i]

                        }
                        for (var j = 0; j < 15; j++) {
                            _this.wordCloudData[j] = resp.data.data[j]
                        }
                        // _this.tableOption2 = resp.data.data;
                        _this.TopicKeywordChart()
                        _this.WordCloudChart()

                    }
                    else {
                        _this.$message.error('出错了');
                        return false;
                    }
                })
            },

            TopicKeywordChart() {
                // const _this = this
                this.userChart = echarts.init(this.$refs.getTopicKeywordChart)

                const optionTopicKeyword = {
                    title: {
                        text: '热门话题',
                        subtext: '(关键词)',
                        left: 'center'
                    },
                    tooltip: {
                        trigger: 'item'
                    },
                    legend: {
                        orient: 'vertical',
                        left: 'left'
                    },
                    series: [
                        {
                            name: '热门话题',
                            type: 'pie',
                            radius: '50%',
                            data: this.topicKeywordData,
                            emphasis: {
                                itemStyle: {
                                    shadowBlur: 10,
                                    shadowOffsetX: 0,
                                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                                }
                            }

                        }
                    ]
                }
                this.userChart.setOption(optionTopicKeyword)
            },


            WordCloudChart() {
                // const _this = this
                this.wordCloudChart = echarts.init(this.$refs.getWordCloudChart)

                const echartsData = {
                    series: [{
                        type: 'wordCloud',
                        // shape这个属性虽然可配置，但是在词的数量不太多的时候，效果不明显，它会趋向于画一个椭圆
                        shape: 'circle',
                        // 这个功能没用过
                        keepAspect: false,
                        // maskImage这个是可以自定义背景图片的，词云会按照图片的形状排布，所以有形状限制的时候，最好用背景图来实现，而且，这个背景图一定要放base64的，不然词云画不出来
                        // maskImage: '',
                        // 下面就是位置的配置
                        left: 'center',
                        top: 'center',
                        width: '70%',
                        height: '80%',
                        right: null,
                        bottom: null,
                        // 词的大小，最小12px，最大60px，可以在这个范围调整词的大小
                        sizeRange: [12, 60],
                        // 每个词旋转的角度范围
                        rotationRange: [-90, 90],
                        rotationStep: 45,
                        // 词间距，数值越小，间距越小，这里间距太小的话，会出现大词把小词套住的情况，比如一个大的口字，中间会有比较大的空隙，这时候他会把一些很小的字放在口字里面，这样的话，鼠标就无法选中里面的那个小字
                        gridSize: 8,
                        // 允许词太大的时候，超出画布的范围
                        drawOutOfBound: false,
                        // 布局的时候是否有动画
                        layoutAnimation: true,
                        // 这是全局的文字样式，相对应的还可以对每个词设置字体样式
                        textStyle: {
                            fontFamily: 'sans-serif',
                            fontWeight: 'bold',
                            // 颜色可以用一个函数来返回字符串
                            color: function () {
                                // Random color
                                return 'rgb(' + [
                                    Math.round(Math.random() * 160),
                                    Math.round(Math.random() * 160),
                                    Math.round(Math.random() * 160)
                                ].join(',') + ')';
                            }
                        },
                        emphasis: {
                            focus: 'self',
                            textStyle: {
                                textShadowBlur: 3,
                                textShadowColor: '#333'
                            }
                        },
                        // 数据必须是一个数组，数组是对象，对象必须有name和value属性
                        data: this.wordCloudData,
                    }]
                }
                this.wordCloudChart.setOption(echartsData)
            },

            TrendChart() {
                // const _this = this
                this.trendChart = echarts.init(this.$refs.getTrendChart)
                this.trendChart.setOption(this.trendOption)
            },

            KeywordTrend() {
                const _this = this
                request({
                    url: '/keyword/trend',
                    method: 'get',
                }).then(function (resp) {
                    if (resp.status == "200") {
                        _this.trendOption.legend.data = resp.data.data.keywords

                        for (var i = 0; i < _this.trendOption.legend.data.length; i++) {
                            var trend = resp.data.data.trend[_this.trendOption.legend.data[i]]
                            // console.log(trend)
                            _this.trendOption.series[i].name = _this.trendOption.legend.data[i]
                            for (var j = 0; j < trend.length; j++) {
                                _this.trendOption.series[i].data[j] = trend[j].cnt
                                _this.trendOption.xAxis.data[j] = trend[j].nowDate
                            }
                        }
                        _this.TrendChart()

                    }
                })
            },

        },
    }


</script>



<style>
    .card-header {
        display: flex;
        justify-content: space-between;
        align-items: center;
        height: 0px;
        color: #3399ff;
        font-family: 华文楷体;

    }


    .box-card {
        width: 480px;
    }

    .content-item {
        text-align: left;
        overflow: hidden;
        text-overflow: ellipsis;
        display: -webkit-box;
        -webkit-line-clamp: 2;
        -webkit-box-orient: vertical;
        margin-top: -10px;
    }

    .dashboard-container {
        position: relative;
        padding: 24px;

        .user-avatar {
            width: 40px;
            height: 40px;
            border-radius: 50%;
        }

        .data-box {
            display: flex;
            justify-content: space-between;
            padding: 20px;
            font-weight: bold;
            color: var(--el-text-color-regular);
            background: var(--el-bg-color-overlay);
            border-color: var(--el-border-color);
            box-shadow: var(--el-box-shadow-dark);
        }

        .svg-icon {
            fill: currentcolor !important;
        }

        .chart-card1 {
            width: 100px;
        }


        .grid-content {
            display: flex;
            align-items: center;
            height: 100px;

        }

        .grid-cont-right {
            flex: 1;
            text-align: center;
            font-size: 14px;
            color: #999;
        }

        .grid-num {
            font-size: 30px;
            font-weight: bold;
        }

        .grid-con-icon {
            font-size: 50px;
            width: 100px;
            height: 100px;
            text-align: center;
            line-height: 100px;
            color: #fff;
        }

        .grid-con-1 .grid-con-icon {
            background: rgb(45, 140, 240);
        }

        .grid-con-1 .grid-num {
            color: rgb(45, 140, 240);
        }

        .grid-con-2 .grid-con-icon {
            background: rgb(100, 213, 114);
        }

        .grid-con-2 .grid-num {
            color: rgb(100, 213, 114);
        }

        .usercount {
            width: 500px;
        }

        .tweetnum {
            width: 500px;
        }

    }
</style>