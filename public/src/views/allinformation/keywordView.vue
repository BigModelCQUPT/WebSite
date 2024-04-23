<!--获取关键词信息展示-->
<template>
    <!-- <div style="margin-left: 10px; margin-top: 15px;margin-right: 10px">

        <div style="margin-top: 30px">
            <el-card class="box-card" style="width: 100%">
                <template #header>
                    <div class="card-header">
                        <span>关键词</span>
                    </div>
                </template>
<el-tag :key="tag" v-for="tag in keywordData" closable :disable-transitions="false" @close="handleDeleteKeyword(tag.id)"
    type="danger">
    {{ tag.keyword }}

</el-tag>
<el-input v-if="keyworddialogVisible" ref="InputRef" v-model="keywordInputValue" class="input-new-tag" size="small"
    @keyup.enter="handleAddKeyword" @blur="blurKeyWord" type="info" />

<el-button v-else class="button-new-tag" size="large" @click="showInput">
    + 新增关键词
</el-button>
</el-card>



</div>


</div> -->
    <div style="margin-left: 10px; margin-top: 15px;margin-right: 10px">
        <!--        工具栏-->
        <div class="toolbar">
            <div>

                <el-button type="primary" @click="add" style="margin-top: -6px">
                    <el-icon style="vertical-align: middle;">
                        <plus />
                    </el-icon>
                    <span style="vertical-align: middle;">手动添加</span>
                </el-button>
            </div>
        </div>

        <div class="demo">
            <!-- // 表单必须嵌套在表格外面，表单必须绑定 rules 、ref属性 -->
            <el-form :model="ruleForm" :rules="rules" ref="keywordForm">
                <el-table :data="ruleForm.suppContactInfoList" style="width: 100%" ref="suppContactInfoList">
                    <el-table-column prop="index" label="序号" width="50" type="index" align="center"
                        :index="(index) => index + 1" />
                    <el-table-column prop="forwardKeyword" label="正向关键词" align="center">
                        <template #default="scope">
                            <span v-show="!scope.row.editFlag">{{
                    scope.row.forwardKeyword
                }}</span>
                            <!-- // 每个字段必须要动态的绑定表单的 prop、rules属性 -->
                            <el-form-item :prop="'suppContactInfoList.' + scope.$index + '.forwardKeyword'
                    " :rules="rules['forwardKeyword']">
                                <el-input v-show="scope.row.editFlag" v-model="scope.row.forwardKeyword"
                                    placeholder="若有多个关键词，请使用中文逗号分隔">
                                </el-input>
                            </el-form-item>
                        </template>
                    </el-table-column>
                    <el-table-column prop="backwardKeyword" label="反向关键词" align="center">
                        <template #default="scope">
                            <span v-show="!scope.row.editFlag">{{
                    scope.row.backwardKeyword
                }}</span>
                            <el-form-item :prop="'suppContactInfoList.' + scope.$index + '.backwardKeyword'
                    " :rules="rules['backwardKeyword']">
                                <el-input v-show="scope.row.editFlag" v-model="scope.row.backwardKeyword"
                                    placeholder="若有多个关键词，请使用中文逗号分隔">
                                </el-input>
                            </el-form-item>
                        </template>
                    </el-table-column>

                    <el-table-column label="操作" width="120" align="center">
                        <template #default="scope">
                            <div style="display: flex">
                                <el-button size="small" type="primary" icon="CircleCheck" style="width: 90px"
                                    v-show="scope.row.editFlag" @click="submit(scope.row)">保存
                                </el-button>
                                <!-- <el-button size="small" icon="Edit" type="text" v-show="!scope.row.editFlag"
                                    @click="edit(scope.row)">
                                    修改
                                </el-button> -->
                                <el-button size="small" icon="Delete" type="text" v-show="!scope.row.editFlag"
                                    @click="handleDeleteKeyword(scope.row.id)">删除
                                </el-button>
                            </div>
                        </template>
                    </el-table-column>
                </el-table>
            </el-form>
            <!-- <div style="width: 100%">
                <el-button @click="add" size="small"
                    style="width: 100px; margin-top: 20px; background-color: #fff">添加</el-button>
            </div> -->
        </div>
    </div>

</template>

<script>
import request from '@/utils/http'
import { Plus } from '@element-plus/icons-vue'

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

            ruleForm: {
                suppContactInfoList: []
            },
            rules: {
                forwardKeyword: [
                    { required: true, message: '请输入文本', trigger: 'blur' },
                    { validator: this.validateText, trigger: 'blur' }
                ],
                backwardKeyword: [
                    // { required: true, message: '请输入文本', trigger: 'blur' },
                    { validator: this.validateText, trigger: 'blur' }
                ],
            },

        }
    },
    created() {
        this.fetchKeywordData()
    },
    methods: {
        fetchKeywordData() {
            const _this = this
            let data = []
            request({
                url: 'http://localhost:8181/keyword/listAll',
                method: 'get',
            }).then(function (resp) {
                if (resp.status == "200") {
                    data = resp.data.data

                    _this.ruleForm.suppContactInfoList = data.map(item => ({
                        ...item,
                        editFlag: false,
                        isSubmit: true
                    }));
                    // 因为可能导致 整个分页表是否包含 该关键词发生变化
                    this.fetchData()
                    console.log(_this.ruleForm.suppContactInfoList)
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
        },




        add() {
            this.ruleForm.suppContactInfoList.push({
                forwardKeyword: "",
                backwardKeyword: "",

                editFlag: true, // 可编辑标识
                isSubmit: false, // 是否点击确定标识
            });
        },


        validateText(rule, value, callback) {
            // const _this = this
            // 使用正则表达式检查是否使用中文逗号分隔每个词
            if (!/^([\u4e00-\u9fa5\w\d]+|[\u4e00-\u9fa5\w\d]+(，[\u4e00-\u9fa5\w\d]+)+)?$/.test(value)) {
                alert('温馨提示：请输入使用中文逗号分隔的文本');
                callback(new Error('请输入使用中文逗号分隔的文本'));
            } else {
                callback();
            }
        },
        // 保存新增的联系人
        submit(row) {
            // 检查文本是否为空
            if (!row.forwardKeyword) {
                // 弹出提示
                alert('正向关键词不能为空');
                return;
            }
            this.$refs["keywordForm"].validate((v) => {
                if (v) {
                    row.editFlag = false;
                    row.isSubmit = true;

                    request({
                        url: 'http://localhost:8181/keyword/add',
                        method: 'post',
                        data: row
                    }).then(function (resp) {
                        if (resp.status == "200") {
                            this.$message.success('添加成功');
                        }
                        else {
                            this.$message.error('出错了');
                            return false;
                        }
                    })
                }
            });
        },
        // 编辑联系人
        edit(row) {
            row.editFlag = true;
        },
        // 删除联系人
        del(index, id) {
            const _this = this;
            this.ruleForm.suppContactInfoList.splice(index, 1);

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
        },


    },

    components: {
        Plus,
    }
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

.demo {
    .el-form-item {
        margin-bottom: 0;
    }
}

.el-tag+.el-tag {
    margin-left: 10px;
}
</style>