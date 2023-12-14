<template>
    <div style="margin-top: -20px">
        <h3>兰锦社区{{title}}财务信息</h3>
    </div>
    <div style="text-align: right; margin-right: 10px; margin-top: -10px">
        <el-select v-model="now_select"
                   size="large"
                   style="margin-right: 10px"
                   @change="changeDate"
        >
            <el-option
                    v-for="item in options"
                    :key="item"
                    :value="item.date"
            >
            </el-option>

        </el-select>
    </div>
    <div style="margin: 10px 10px 10px 10px">
        <el-card class="box-card">
            <el-table :data="tableData" border
                      :summary-method="getSummaries01"
                      show-summary
                      style="width: 100%;"
                      height="410px"
            >
                <el-table-column prop="id" label="编号" width="60" align="center">
                </el-table-column>
                <el-table-column prop="date" sortable label="日期" width="120" align="center">
                </el-table-column>
                <el-table-column prop="input" label="支出项目" align="center">
                </el-table-column>
                <el-table-column prop="input_number" sortable label="支出金额(元)" align="center" width="100px">
                </el-table-column>
                <el-table-column prop="output" label="收入项目" align="center">
                </el-table-column>
                <el-table-column prop="output_number" sortable label="收入金额(元)" align="center" width="100px">
                </el-table-column>
            </el-table>
        </el-card>
    </div>
</template>

<script>
    import axios from 'axios'
    import { ref } from 'vue'
    export default {
        name:'CommunityFinanceView',
        data() {
            return {
                tableData: [{
                    id: '1',
                    date:'2022-01-01',
                    input:'',
                    input_number:'',
                    output:'报销购买灯线开关等费用',
                    output_number:280,
                }, {
                    id: '2',
                    date:'2022-01-02',
                    input:'',
                    input_number:'',
                    output:'报销小区广场舞音箱',
                    output_number:630.5,
                },
                ],
                title:'',
                date:2022,//当前年份
                now_select:ref('当前季度'),
                options:[{
                    date:'当前季度'
                }, {
                    date:'2022年'
                }, {
                    date: '2021年'
                }
                ]
            };
        },
        methods: {
            getSummaries01(param) {
                const {
                    columns,
                    data
                } = param;
                const sums = [];
                columns.forEach((column, index) => {
                    if (index === 1) {
                        sums[index] = '合 计';
                        return;
                    }
                    if(index == 0)
                    {
                        sums[index] = ''
                        return ;
                    }
                    if(index == 2)
                    {
                        sums[index] = '收入合计'
                        return ;
                    }
                    if(index == 4)
                    {
                        sums[index] = '支出合计'
                        return ;
                    }

                    const values = data.map(item => Number(item[column.property]));
                    if (!values.every(value => isNaN(value))) {
                        sums[index] = values.reduce((prev, curr) => {
                            const value = Number(curr);
                            if (!isNaN(value)) {
                                return prev + curr;
                            } else {
                                return prev;
                            }
                        }, 0);
                        // sums[index] += ' 元';
                    }
                });
                return sums;
            },
            init(){
                const _this = this
                axios.get('http://localhost:8181/communityFinance/get/' + this.now_select).then(function (resp) {
                    // console.log(resp.data)
                    if(resp.data.code == "200"){//返回成功
                        _this.tableData = resp.data.data
                    }else if(resp.data.code == "101"){
                        _this.$message.error('请先登录');
                        return false;
                    }
                })
                console.log(this.now_select)
                if(this.now_select == '当前季度') {
                    let m = new Date().getMonth() + 1;
                    this.date = new Date().getFullYear()
                    if (1 <= m && m <= 3)
                        this.title = '第一季度'
                    else if (4 <= m && m <= 6)
                        this.title = '第二季度'
                    else if (7 <= m && m <= 9)
                        this.title = '第三季度'
                    else if (10 <= m && m <= 12)
                        this.title = '第四季度'
                }else if (this.now_select == '2022年')
                    this.title = '2022年'
                else if(this.now_select == '2021年')
                    this.title = '2021年'
            },
            changeDate() {
                this.init()
            }

        },
        created() {
            this.init()
        },
        components:{
        }
    };
</script>