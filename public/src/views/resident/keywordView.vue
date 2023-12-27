<!--获取租户信息展示-->
<template>
    <div style="margin-left: 10px; margin-top: 15px;margin-right: 10px">

        <div style="margin-top: 30px">
            <el-card class="box-card" style="width: 100%">
                <template #header>
                    <div class="card-header">
                        <span>关键词</span>
                    </div>
                </template>
                <el-tag :key="tag" v-for="tag in keywordData" closable :disable-transitions="false"
                    @close="handleDeleteKeyword(tag.id)" type="danger">
                    {{ tag.keyword }}
                </el-tag>
                <el-input v-if="keyworddialogVisible" ref="InputRef" v-model="keywordInputValue" class="input-new-tag"
                    size="small" @keyup.enter="handleAddKeyword" @blur="blurKeyWord" type="info" />

                <el-button v-else class="button-new-tag" size="large" @click="showInput">
                    + 新增关键词
                </el-button>
            </el-card>
        </div>

    </div>
</template>

<script>
import request from '@/utils/http'

export default {
    name: "GetResidentInformation",
    data() {
        return {
            keywordData: [],
            search_text: '',
            total: 0,//总条数
            currentPage: 1,//第几页
            size: 10,//每页条数
            dialogVisible: false,
            editdialogVisible: false,
            activeindex: 0,
            detaildialogVisible: false,
            keyworddialogVisible: false,
            keywordInputValue: '',
            search_keyword: '',
        }
    },
    created() {
        this.fetchKeywordData()
    },
    methods: {
        fetchKeywordData() {
            const _this = this
            request({
                url: 'http://localhost:8181/keyword/listAll',
                method: 'get',
            }).then(function (resp) {
                if (resp.status == "200") {
                    _this.keywordData = resp.data.data
                    // 因为可能导致 整个分页表是否包含 该关键词发生变化
                    this.fetchData()
                }
                else {
                    _this.$message.error('出错了');
                    return false;
                }
            })
        },
        handleDeleteKeyword(id) {
            console.log(id);
            const _this = this
            request({
                url: 'http://localhost:8181/keyword/delete/' + id,
                method: 'get',
            }).then(function (resp) {
                if (resp.status == "200") {
                    _this.$message({
                        message: '删除成功',
                        type: 'success'
                    });
                    this.fetchData()
                }
                else {
                    _this.$message.error('出错了');
                    return false;
                }
            })
            // this.fetchKeywordData()
            this.$router.go(0)
        },
        handleAddKeyword() {
            const _this = this
            const data = {
                keyword: this.keywordInputValue
            }
            request({
                url: 'http://localhost:8181/keyword/add',
                method: 'post',
                data: data
            }).then(function (resp) {
                if (resp.status == "200") {
                    _this.$message.success('添加成功');
                }
                else {
                    _this.$message.error('出错了');
                    return false;
                }
            })
            this.keyworddialogVisible = false
            this.keywordInputValue = ''
            this.fetchKeywordData()
            this.$router.go(0)
        },
        blurKeyWord() {
            this.keyworddialogVisible = false
            this.keywordInputValue = ''
        },

        showInput() {
            this.keyworddialogVisible = true;
        },


        handleInputConfirm() {
            // let keywordInputValue = this.keywordInputValue;
            // if (keywordInputValue) {
            //     this.dynamicTags.push(keywordInputValue);
            // }
        }
    },
}
</script>

<style scoped>
.toolbar {
    text-align: left;
    display: flex;
    justify-content: space-between;
}

.el-input-resident {
    width: 300px;
    margin-right: 10px;
}

.margin-topa {
    margin-top: -15px;
}


.el-tag+.el-tag {
    margin-left: 10px;
}

.button-new-tag {
    margin-left: 10px;
    height: 32px;
    line-height: 30px;
    padding-top: 0;
    padding-bottom: 0;
}

.input-new-tag {
    width: 90px;
    margin-left: 10px;
    vertical-align: bottom;
}

.card-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    background-color: lightblue;
}

.text {
    font-size: 14px;
}

.item {
    margin-bottom: 18px;
}

.box-card {
    width: 480px;
}
</style>