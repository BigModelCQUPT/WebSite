<template>
    <div class="dashboard-container">

        <el-card shadow="never">
            <el-row justify="space-between">
                <el-col :span="18" :xs="24">
                    <div>
                        <!-- <p>{{ greetings }}</p> -->
                        <p class="text-sm text-gray">
                            早上好 CQUPT
                        </p>
                    </div>
                </el-col>
            </el-row>
        </el-card>

        <el-row :gutter="10" class="mt-3">
            <el-col :xs="24" :sm="12" :lg="12">
                <el-card shadow="never">
                    <template #header>
                        <div class="flex items-center justify-between">
                            <span class="text-[var(--el-text-color-secondary)]">总推特用户数</span>
                            <!-- <el-tag type="success">日</el-tag> -->
                        </div>
                    </template>

                    <div class="flex items-center justify-between mt-5">
                        <div class="text-lg text-right">
                            {{ Math.round(twitterUserCount) }}
                        </div>
                        <svg-icon icon-class="visit" size="2em" />
                    </div>

                    <!-- <div class="flex items-center justify-between mt-5 text-sm text-[var(--el-text-color-secondary)]">
                        <span> </span>
                        <span> {{ Math.round(twitterUserCount * 15) }} </span>
                    </div> -->
                </el-card>
            </el-col>

            <el-col :xs="24" :sm="12" :lg="12">
                <el-card shadow="never">
                    <template #header>
                        <div class="flex items-center justify-between">
                            <span class="text-[var(--el-text-color-secondary)]">总推文数</span>
                            <!-- <el-tag type="success">日</el-tag> -->
                        </div>
                    </template>

                    <div class="flex items-center justify-between mt-5">
                        <div class="text-lg text-right">
                            {{ Math.round(totalTweet) }}
                        </div>
                        <svg-icon icon-class="ip" size="2em" />
                    </div>

                    <!-- <div class="flex items-center justify-between mt-5 text-sm text-[var(--el-text-color-secondary)]">
                        <span> 总推文数 </span>
                        <span> {{ Math.round(tweetCount) }} </span>
                    </div> -->
                </el-card>
            </el-col>
        </el-row>
    </div>

    <div>
        <div ref="mychart" style="height: 560px; width: 100%; margin-top: 0px">
        </div>
    </div>
</template>
<script>
import * as echarts from "echarts";
// import { ref } from 'vue'
// import axios from 'axios'
import request from '@/utils/http'

export default {
    data() {
        return {
            twitterUserCount: 0,
            totalTweet: 0,

            option: {
                title: {
                    text: 'Referer of a Website',
                    subtext: 'Fake Data',
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
                        name: 'Access From',
                        type: 'pie',
                        radius: '50%',
                        data: [
                            { value: 1048, name: 'Search Engine' },
                            { value: 735, name: 'Direct' },
                            { value: 580, name: 'Email' },
                            { value: 484, name: 'Union Ads' },
                            { value: 300, name: 'Video Ads' }
                        ],
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
        }
    },
    created() {
        this.getNumberUser()
        this.getNumberTweet()
    },

    mounted() {
        let myEcharts = echarts.init(this.$refs.mychart)
        myEcharts.setOption(this.option)
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
        }
    }
}
</script>
<style
>
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
}
</style>
