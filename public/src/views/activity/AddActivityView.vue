<!--社区活动登记-->
<template>
    <div>
        <div style="margin-top: -32px; height: 30px;display: flex; margin-left: 10px">
            <div @click="submit" style="padding-top: 3px;display: flex">
                <el-icon  >
                    <svg t="1650978743591" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="1412" width="200" height="200"><path d="M447.67 483h-128a32 32 0 0 0 0 64h128a32 32 0 1 0 0-64z" p-id="1413"></path><path d="M863 66.16H161a64.19 64.19 0 0 0-64 64v767.68a64.19 64.19 0 0 0 64 64h702a64.19 64.19 0 0 0 64-64V130.16a64.19 64.19 0 0 0-64-64z m0 831.58l-0.11 0.11H161.12l-0.11-0.11V130.27l0.11-0.11h701.76l0.11 0.11z" p-id="1414"></path><path d="M319 290.58h383.25a32 32 0 0 0 0-64H319a32 32 0 1 0 0 64zM319 418.41h383.25a32 32 0 0 0 0-64H319a32 32 0 0 0 0 64zM681.06 497.87l-0.19-0.18-0.69-0.65-0.42-0.36-0.51-0.43-0.54-0.42-0.41-0.32-0.62-0.44-0.37-0.26-0.66-0.42-0.37-0.23-0.67-0.39-0.39-0.22-0.22-0.12-0.44-0.22-0.45-0.22-0.62-0.28-0.52-0.22-0.2-0.09-0.37-0.14-0.59-0.22-0.52-0.18-0.65-0.21-0.16-0.05-0.33-0.09-0.7-0.19-0.48-0.11-0.71-0.15h-0.13l-0.37-0.06-0.69-0.11-0.58-0.07-0.63-0.07-0.71-0.05h-3.38l-0.86 0.07-0.64 0.08-0.57 0.07-0.72 0.12-0.48 0.09-0.73 0.16-0.46 0.11-0.7 0.19-0.48 0.14-0.64 0.21-0.53 0.18-0.56 0.21-0.6 0.24-0.47 0.21-0.66 0.3-0.39 0.2-0.53 0.26-0.18 0.1-0.32 0.18-0.74 0.43-0.27 0.17-0.75 0.49-0.26 0.18-0.73 0.52-0.28 0.22-0.67 0.53-0.38 0.33-0.54 0.47q-0.45 0.41-0.88 0.84l-85.85 86.29a25 25 0 0 0 35.48 35.23l43.15-43.45 0.54 241.75a25 25 0 0 0 25 24.94h0.06a25 25 0 0 0 24.94-25.06l-0.54-242 44.19 43.89A25 25 0 1 0 768 584.17z" p-id="1415"></path></svg>
                </el-icon>
                <span style="margin-left: 5px; margin-top: -3px">提交</span>
            </div>
            <el-divider direction="vertical" style="margin-top: 3px" />
            <div @click="exportData" style="margin-top: 3px; display:flex;">
                <el-icon style="">
                    <svg t="1650978715708" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="1963" width="200" height="200"><path d="M949.65726 253.775217L776.574163 11.498877C771.474661 4.29958 763.075481 0 754.27634 0H96.740553C81.542037 0 69.243238 12.298799 69.243238 27.497315v969.00537c0 15.198516 12.298799 27.497315 27.497315 27.497315h830.518894c15.198516 0 27.497315-12.298799 27.497315-27.497315V269.673665c0-5.699443-1.799824-11.298897-5.099502-15.898448zM313.619373 54.894639h373.7635c-0.599941 2.299775-0.899912 4.699541-0.899912 7.099307v249.375647h-371.963676V62.093936c0-2.499756-0.299971-4.899522-0.899912-7.199297z m396.761254 914.110732H313.619373c0.599941-2.299775 0.899912-4.699541 0.899912-7.099307V747.027048h394.96143v214.779026c0 2.499756 0.299971 4.899522 0.899912 7.199297z m189.381506 0H763.475442c0.599941-2.299775 0.899912-4.699541 0.899912-7.099307V719.629724c0-15.198516-12.298799-27.497315-27.497315-27.497315H287.021971c-15.198516 0-27.497315 12.298799-27.497315 27.497315V961.906064c0 2.499756 0.299971 4.899522 0.899912 7.099307H124.237867V54.894639h136.386681c-0.599941 2.299775-0.899912 4.699541-0.899912 7.099307v276.972952c0 15.198516 12.298799 27.497315 27.497315 27.497314H713.980275c15.198516 0 27.497315-12.298799 27.497315-27.497314V62.093936c0-2.199785-0.299971-4.39957-0.799922-6.499365l159.284445 222.978225v690.432575z" fill="" p-id="1964"></path><path d="M575.493799 200.480422m-35.996484 0a35.996485 35.996485 0 1 0 71.992969 0 35.996485 35.996485 0 1 0-71.992969 0Z" fill="" p-id="1965"></path></svg>
                </el-icon>
                <span style="margin-left: 5px;margin-top: -3px">导出文件</span>
            </div>
            <!--            下拉框选择word excel pdf 图片-->
            <el-divider direction="vertical" style="margin-top: 3px"/>
        </div>
        <el-divider border-style="double" style="margin-top: -5px"/>
        <div style="margin-left: 30px; font-size: 20px">
            <h3 style="margin-left: -330px; margin-top: -15px; font-family: 华文楷体">锦南社区活动记录表</h3>
            <el-form :model="form">
            <table class="out-table" style="margin-top: -15px; font-family: 华文楷体" >
            <tr style="height: 40px">
                <td class="in-td" style="width: 150px;">活动时间</td>
                <td class="in-td" style="width: 180px">
                    <input type="date" v-model="form.date" style=" height: 100%; width: 100%; border: 0px; text-align: center" />
                </td>
                <td class="in-td" style="width: 150px" >参加人数</td>
                <td class="in-td" style="width: 130px">
                    <input v-model="form.number" type="text" style="height: 40px; width: 100%; border: 0px; text-align: center">
                </td>
            </tr>
            <tr style="height: 40px" >
                <td class="in-td">活动地点</td>
                <td class="in-td">
                    <input v-model="form.address" name="name" type="text" style="height: 40px; width: 100%; border: 0px; text-align: center">
                </td>
                <td class="in-td">记录人</td>
                <td class="in-td" >
                    <input v-model="form.name" type="text" style="height: 40px; width: 100%; border: 0px; text-align: center">
                </td>
            </tr>
            <tr style="height: 40px">
                <td class="in-td"><b>活动主题</b></td>
                <td class="in-td" colspan="3">
                    <input v-model="form.theme" type="text" style="height: 40px; width: 100%; border: 0px; text-align: center">
                </td>
            </tr>
            <tr style="height: 540px">
                <td class="in-td"><b>活动内容</b></td>
                <td class="in-td" colspan="3">
                    <textarea type="text" v-model="form.content" style=" height: 330px; width: 99%; border: 0px;margin-top: -80px" ></textarea>
                    <div  style="margin-bottom:-40px">
                        <el-upload
                                list-type="picture-card"
                                :auto-upload="false"
                                :limit="1"
                                :on-exceed="tip"
                        >
                            <el-icon ><Plus /></el-icon>
                            <template #file="{ file }" >
                                <div>
                                    <img  class="el-upload-list__item-thumbnail" :src="file.url" alt="" />
                                    <span class="el-upload-list__item-actions"><el-button @click="submit_img(file)">上传</el-button></span>
                                </div>
                            </template>
                        </el-upload>
                    </div>

                </td>
            </tr>
        </table>
        </el-form>

        </div>
    </div>
</template>

<script>
    import axios from 'axios'
    import {Plus} from '@element-plus/icons-vue'
    export default {
        name: "AddActivityView",
        data(){
            return {
                form:{
                    date:'',
                    number:'',
                    address:'',
                    name:'',
                    theme:'',
                    content:'',
                    img:null
                }
            }
        },
        components:{
            Plus
        },
        methods: {
            submit(){ //提交
                if(this.form.date == '' || this.form.number == '' || this.form.address == '' || this.form.name == '' || this.form.theme == '' || this.form.content == '') {
                    this.$message.error('请输入全部字段');
                    return ;
                }
                if(this.form.img == null) {
                    this.$message.error('请点击图片上的上传按钮上传图片');
                    return ;
                }
                const _this = this
<<<<<<< HEAD
                axios.post('http://10.16.104.183:8181/activity/add', _this.form).then(function (resp) {
=======
                axios.post('http://localhost:8181/activity/add', _this.form).then(function (resp) {
>>>>>>> ffc14b02a948452209c8504655d41786bdd0c07f
                    console.log(resp)
                    if(resp.data.code == "200"){//返回成功
                        window.sessionStorage.img = ''
                        _this.$message({
                            message: '提交成功',
                            type: 'success'
                        });
                        _this.$router.replace('/lookActivity')
                    }else if(resp.data.code == "101"){
                        _this.$message.error('出现错误');
                        return false;
                    }
                })
                this.form.img = null
            },
            exportData() {//导出文件

                if(this.form.date == '' || this.form.number == '' || this.form.address == '' || this.form.name == '' || this.form.theme == '' || this.form.content == '') {
                    this.$message.error('请输入全部字段');
                    return ;
                }
                if(this.form.img == null) {
                    this.$message.error('请点击图片上的上传按钮上传图片');
                    return ;
                }
                axios({
                    method:'post',
<<<<<<< HEAD
                    url: 'http://10.16.104.183:8181/activity/exportWord',
=======
                    url: 'http://localhost:8181/activity/exportWord',
>>>>>>> ffc14b02a948452209c8504655d41786bdd0c07f
                    data:this.form,
                    responseType: 'blob'
                }).then(res => {
                    let data = res.data
                    let filename = "活动登记表.docx"
                    if(res.headers.filename){
                        filename = decodeURI(res.headers.filename)
                    }
                    let url = window.URL.createObjectURL(new Blob([data]))
                    let link = document.createElement('a')
                    link.style.display = 'none'
                    link.href = url
                    link.setAttribute('download',filename)
                    document.body.appendChild(link)
                    link.click()
                    document.body.removeChild(link) // 下载完成移除元素
                    window.URL.revokeObjectURL(url) // 释放掉blob对象
                }).catch((error) => {
                    console.log(error)
                })
                this.form.img = null
            },
            tip() {
                this.$message.error('最多上传一张图片');
            },
            submit_img(file) {
                this.form.img =  file.name
            },
            up() {
            },
        },
        created() {
        }
    }
</script>

<style scoped>
    .out-table {
        border-collapse: collapse;
        border: none;
    }
    .in-td {
        border: solid #000 1px;
    }
</style>