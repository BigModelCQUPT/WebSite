<!--党群结构-->
<template>
    <div>
        <el-upload
                list-type="picture-card"
                :auto-upload="false"
                :limit="1"
                :on-exceed="tip"
                style="margin-bottom: 15px"
        >
            <el-icon ><Plus /></el-icon>
            <template #file="{ file }" >
                <div>
                    <img  class="el-upload-list__item-thumbnail" :src="file.url" alt="" />
                    <span class="el-upload-list__item-actions"><el-button @click="submit_img(file)">上传</el-button></span>
                </div>
            </template>
        </el-upload>
        <el-button style=" width: 300px" type="primary" @click="submit">立即上传</el-button>

    </div>
</template>

<script>
    import axios from "axios";
    import {Plus} from '@element-plus/icons-vue'
    export default {
        name: "EditStructView",
        data(){
            return {
                formData: {
                    struct_img:null
                },

            }
        },
        methods:{
            submit(){
                if(this.formData.struct_img == null) {
                    this.$message.error('请先点击图片上传按钮上传图片');
                    return
                }
                const _this = this
<<<<<<< HEAD
                axios.post('http://10.16.104.183:8181/community/edit_struct', this.formData).then(function (resp) {
=======
                axios.post('http://localhost:8181/community/edit_struct', this.formData).then(function (resp) {
>>>>>>> ffc14b02a948452209c8504655d41786bdd0c07f
                    if(resp.data.code == "200"){//返回成功
                        _this.$message({
                            message: '编辑成功',
                            type: 'success'
                        });
                    }
                })
            },
            tip() {
                this.$message.error('最多上传一张图片');
            },
            submit_img(file) {
                this.formData.struct_img = file.name
            }
        },
        components:{
            Plus
        }
    }
</script>

<style scoped>

</style>