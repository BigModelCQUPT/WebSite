<!--编辑领导信息-->
<template>
    <div >
        <div style="margin-top: -32px; height: 30px;display: flex; margin-left: 10px">
            <div @click="submit" style="padding-top: 3px;display: flex">
                <el-icon  >
                    <svg t="1650978743591" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="1412" width="200" height="200"><path d="M447.67 483h-128a32 32 0 0 0 0 64h128a32 32 0 1 0 0-64z" p-id="1413"></path><path d="M863 66.16H161a64.19 64.19 0 0 0-64 64v767.68a64.19 64.19 0 0 0 64 64h702a64.19 64.19 0 0 0 64-64V130.16a64.19 64.19 0 0 0-64-64z m0 831.58l-0.11 0.11H161.12l-0.11-0.11V130.27l0.11-0.11h701.76l0.11 0.11z" p-id="1414"></path><path d="M319 290.58h383.25a32 32 0 0 0 0-64H319a32 32 0 1 0 0 64zM319 418.41h383.25a32 32 0 0 0 0-64H319a32 32 0 0 0 0 64zM681.06 497.87l-0.19-0.18-0.69-0.65-0.42-0.36-0.51-0.43-0.54-0.42-0.41-0.32-0.62-0.44-0.37-0.26-0.66-0.42-0.37-0.23-0.67-0.39-0.39-0.22-0.22-0.12-0.44-0.22-0.45-0.22-0.62-0.28-0.52-0.22-0.2-0.09-0.37-0.14-0.59-0.22-0.52-0.18-0.65-0.21-0.16-0.05-0.33-0.09-0.7-0.19-0.48-0.11-0.71-0.15h-0.13l-0.37-0.06-0.69-0.11-0.58-0.07-0.63-0.07-0.71-0.05h-3.38l-0.86 0.07-0.64 0.08-0.57 0.07-0.72 0.12-0.48 0.09-0.73 0.16-0.46 0.11-0.7 0.19-0.48 0.14-0.64 0.21-0.53 0.18-0.56 0.21-0.6 0.24-0.47 0.21-0.66 0.3-0.39 0.2-0.53 0.26-0.18 0.1-0.32 0.18-0.74 0.43-0.27 0.17-0.75 0.49-0.26 0.18-0.73 0.52-0.28 0.22-0.67 0.53-0.38 0.33-0.54 0.47q-0.45 0.41-0.88 0.84l-85.85 86.29a25 25 0 0 0 35.48 35.23l43.15-43.45 0.54 241.75a25 25 0 0 0 25 24.94h0.06a25 25 0 0 0 24.94-25.06l-0.54-242 44.19 43.89A25 25 0 1 0 768 584.17z" p-id="1415"></path></svg>
                </el-icon>
                <span style="margin-left: 5px; margin-top: -3px">提交</span>
            </div>
            <el-divider direction="vertical" style="margin-top: 3px" />
        </div>
        <el-divider border-style="double" style="margin-top: -5px"/>

        <el-form :model="form">
            <table width="70%"  cellspacing="0" border style="margin: auto;" >
            <tbody>
            <tr style="height: 35px">
                <td rowspan="3" align="center" style="padding:5px 10px;">
                    <el-image :src="require('../../assets/'+form.img)" width="116" height="153" title="" class=" _681a23b0649e61ca572cb5bb8db206ec" alt="图片"/>
                    <el-upload
                            v-if="form.img === null"
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
                </td>
                <td  width="17%" ><strong>领导姓名：</strong></td>
                <td width="60%" >
                    <input  v-model="form.name" style="height: 35px;width: 100%; border: 0px; text-align: center" />
                </td>
            </tr>
            <tr style="height: 35px">
                <td style="padding:5px 10px;"><strong>职    务：</strong></td>
                <td >
                    <input  v-model="form.position" style="height: 35px;  width: 100%; border: 0px; text-align: center" />
                </td>
            </tr>
            <tr style="height: 65px">
                <td style="padding:5px 10px;"><strong>领导简历：</strong></td>
                <td >
                    <p >
                        <textarea  type="text" v-model="form.introduce" style=" height: 65px; width: 98%; border: 0px; " />
                    </p>
                </td>
            </tr>

            <tr style="height: 65px">
                <td align="center">&nbsp;</td>
                <td style="padding:5px 10px;"><strong>工作分工：</strong></td>
                <td >
                    <textarea  type="text" v-model="form.work" style=" height: 65px; width: 98%; border: 0px; " />
                </td>
            </tr>
            <tr style="height: 35px">
                <td align="center">&nbsp;</td>
                <td style="padding:5px 10px;"><strong>联系电话：</strong></td>
                <td style="padding:5px 10px;">
                    <input  v-model="form.tele" style=" height: 100%; width: 100%; border: 0px; text-align: center" />
                </td>
            </tr>
            </tbody>
        </table>
        </el-form>
    </div>
</template>

<script>
    import axios from "axios";

    export default {
        name: "EditLeaderView",
        data(){
            return{
                form:{
                    img:"person1.png",
                    name:'',
                    position:'',
                    introduce:'',
                    work:'',
                    tele:'',
                    grow:'',
                }
            }
        },
        created() {
            const _this = this
            // console.log(window.sessionStorage.employee_id)
<<<<<<< HEAD
            axios.post('http://10.16.104.183:8181/employee/getById/'  + window.sessionStorage.employee_id).then(function (resp) {
=======
            axios.post('http://localhost:8181/employee/getById/'  + window.sessionStorage.employee_id).then(function (resp) {
>>>>>>> ffc14b02a948452209c8504655d41786bdd0c07f
                if(resp.data.code == "200"){//返回成功
                    // console.log(resp.data.data)
                    _this.form = resp.data.data
                }else if(resp.data.code == "101"){
                    _this.$message.error('出现错误');
                    return false;
                }
            })

        },
        methods:{
            submit()  {
                const _this = this
<<<<<<< HEAD
                axios.post('http://10.16.104.183:8181/employee/edit', this.form).then(function (resp) {
=======
                axios.post('http://localhost:8181/employee/edit', this.form).then(function (resp) {
>>>>>>> ffc14b02a948452209c8504655d41786bdd0c07f
                    if(resp.data.code == "200"){//返回成功
                        _this.$message({
                            message: '编辑成功',
                            type: 'success'
                        });
                    }
                })
            }
        }
    }
</script>

<style scoped>

</style>