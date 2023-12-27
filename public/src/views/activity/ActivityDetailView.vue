<template>
    <div>
        <div style="margin-top: -32px; height: 30px;display: flex; margin-left: 10px">
            <div @click="exportData" style="margin-top: 3px; display:flex;">
                <el-icon style="">
                    <svg t="1650978715708" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="1963" width="200" height="200"><path d="M949.65726 253.775217L776.574163 11.498877C771.474661 4.29958 763.075481 0 754.27634 0H96.740553C81.542037 0 69.243238 12.298799 69.243238 27.497315v969.00537c0 15.198516 12.298799 27.497315 27.497315 27.497315h830.518894c15.198516 0 27.497315-12.298799 27.497315-27.497315V269.673665c0-5.699443-1.799824-11.298897-5.099502-15.898448zM313.619373 54.894639h373.7635c-0.599941 2.299775-0.899912 4.699541-0.899912 7.099307v249.375647h-371.963676V62.093936c0-2.499756-0.299971-4.899522-0.899912-7.199297z m396.761254 914.110732H313.619373c0.599941-2.299775 0.899912-4.699541 0.899912-7.099307V747.027048h394.96143v214.779026c0 2.499756 0.299971 4.899522 0.899912 7.199297z m189.381506 0H763.475442c0.599941-2.299775 0.899912-4.699541 0.899912-7.099307V719.629724c0-15.198516-12.298799-27.497315-27.497315-27.497315H287.021971c-15.198516 0-27.497315 12.298799-27.497315 27.497315V961.906064c0 2.499756 0.299971 4.899522 0.899912 7.099307H124.237867V54.894639h136.386681c-0.599941 2.299775-0.899912 4.699541-0.899912 7.099307v276.972952c0 15.198516 12.298799 27.497315 27.497315 27.497314H713.980275c15.198516 0 27.497315-12.298799 27.497315-27.497314V62.093936c0-2.199785-0.299971-4.39957-0.799922-6.499365l159.284445 222.978225v690.432575z" fill="" p-id="1964"></path><path d="M575.493799 200.480422m-35.996484 0a35.996485 35.996485 0 1 0 71.992969 0 35.996485 35.996485 0 1 0-71.992969 0Z" fill="" p-id="1965"></path></svg>
                </el-icon>
                <span style="margin-left: 5px;margin-top: -3px" @click="exportword">导出文件</span>
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
                            <input v-model="form.theme" type="text" style="font-family: 华文楷体;font-size: 20px;font-weight: bolder;height: 40px; width: 100%; border: 0px; text-align: center">
                        </td>
                    </tr>
                    <tr style="height: 540px">
                        <td class="in-td"><b>活动内容</b></td>
                        <td class="in-td" colspan="3">
                            <textarea type="text" v-model="form.content" style="font-size: 20px;height: 280px; width: 99%; border: 0px;margin-top: 0px" readonly="readonly" ></textarea>
                            <img style="margin-bottom: 0px; width: 300px; height: 300px; margin-right: 10px" :src="require('../../assets/'+form.img)" alt="">
                        </td>
                    </tr>
                </table>
            </el-form>
        </div>
    </div>
</template>

<script>
    import axios from 'axios'

    export default {
        name: "ActivityDetailView",
        data() {
            return {
                form:[
                    {
                        id:'',
                        date:'',
                        number:'',
                        name:'',
                        address:'',
                        content:'',
                        img1:'',
                        img2:''
                    }
                ]
            }
        },
        methods:{
            init() {
                var theme = this.$route.query.theme
                var address = this.$route.query.address
                const _this = this
                axios.get('http://10.16.104.183:8181/activity/getDetail/' + theme + '/' + address ).then(function (resp) {
                    if(resp.data.code == "200"){//返回成功
                        _this.form = resp.data.data
                    }else{
                        console.log("error")
                    }
                })
            },
            exportword(){
                // console.log(this.form.id )
                axios({
                    method:'get',
                    url: 'http://10.16.104.183:8181/activity/export/' + this.form.id,
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
            }
        },
        created() {
            this.init()
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