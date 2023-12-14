<!--编辑社区信息-->
<template>
    <div style="width: 60%">
        <el-form ref="formData" :model="formData"  style="margin-left: 100px; margin-right:20px">
            <el-form-item  prop="member"  label="介绍1">
                <el-input
                        v-model="formData.p1"
                        :autosize="{ minRows: 1 }"
                        type="textarea"
                        placeholder="请输入参会人员"
                        style="margin-top: 8px"
                />
            </el-form-item>
            <el-form-item  prop="member"  label="介绍2">
                <el-input
                        v-model="formData.p2"
                        :autosize="{ minRows: 1 }"
                        type="textarea"
                        placeholder="请输入社区介绍"
                        style="margin-top: 8px"
                />
            </el-form-item>
            <el-form-item  prop="member"  label="介绍3">
                <el-input
                        v-model="formData.p3"
                        :autosize="{ minRows: 1 }"
                        type="textarea"
                        placeholder="请输入社区介绍"
                        style="margin-top: 8px"
                />
            </el-form-item>
            <el-form-item  prop="member"  label="介绍4">
                <el-input
                        v-model="formData.p4"
                        :autosize="{ minRows: 1 }"
                        type="textarea"
                        placeholder="请输入社区介绍"
                        style="margin-top: 8px"
                />
            </el-form-item>
            <el-form-item  prop="member"  label="介绍5">
                <el-input
                        v-model="formData.p5"
                        :autosize="{ minRows: 1 }"
                        type="textarea"
                        placeholder="请输入社区介绍"
                        style="margin-top: 8px"
                />
            </el-form-item>
            <el-form-item  prop="member"  label="介绍6">
                <el-input
                        v-model="formData.p6"
                        :autosize="{ minRows: 1 }"
                        type="textarea"
                        placeholder="请输入社区介绍"
                        style="margin-top: 8px"
                />
            </el-form-item>
            <el-upload
                    list-type="picture-card"
                    :auto-upload="false"
                    :limit="6"
                    :on-exceed="tip"
                    style="margin-bottom: 5px"
            >
                <el-icon ><Plus /></el-icon>
                <template #file="{ file }" >
                    <div>
                        <img  class="el-upload-list__item-thumbnail" :src="file.url" alt="" />
                        <span class="el-upload-list__item-actions"><el-button @click="submit_img(file)">上传</el-button></span>
                    </div>
                </template>
            </el-upload>

            <el-form-item >
                <el-button style="margin-left: 110px; width: 300px" type="primary" @click="submitForm">立即上传</el-button>
            </el-form-item>

        </el-form>

    </div>
</template>

<script>
    import axios from "axios";
    import {Plus} from '@element-plus/icons-vue'

    export default {
        name: "EditIntroductionView",
        data(){
            return {
                cnt:0,
                formData: {
                    p1:null,
                    p2:null,
                    p3:null,
                    p4:null,
                    p5:null,
                    p6:null,
                    img1:'1.jpg',
                    img2:'2.jpg',
                    img3:'3.jpg',
                    img4:'4.jpg',
                    img5:'5.jpg',
                    img6:'6.jpg',
                    struct_img:'struct.jpg'
                },

            }
        },
        methods:{
            submitForm(){
                if(this.cnt == 0) {
                    this.$message.error('请先点击图片上传按钮上传图片');
                    return
                }
                const _this = this
                axios.post('http://localhost:8181/community/edit', this.formData).then(function (resp) {
                    if(resp.data.code == "200"){//返回成功
                        _this.$message({
                            message: '编辑成功',
                            type: 'success'
                        });
                    }
                })
            },
            tip() {
                this.$message.error('最多上传六张图片');
            },
            submit_img(file) {
                this.cnt ++;
                if(this.cnt == 1)
                    this.formData.img1 = file.name
                else if(this.cnt == 2)
                    this.formData.img2 = file.name
                else if(this.cnt == 3)
                    this.formData.img3 = file.name
                else if(this.cnt == 4)
                    this.formData.img4 = file.name
                else if(this.cnt == 5)
                    this.formData.img5 = file.name
                else if(this.cnt == 6)
                    this.formData.img6 = file.name
            },
        },
        components:{
            Plus
        }
    }
</script>

<style scoped>

</style>