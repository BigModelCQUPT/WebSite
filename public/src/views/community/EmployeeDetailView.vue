<template>
    <div style="margin-top: 30px">
        <table width="70%"  cellspacing="0" border style="margin: auto;" >
            <tbody>
            <tr>
                <td rowspan="3" align="center" style="padding:5px 10px;"><el-image :src="require('../../assets/'+img)" width="116" height="153" title="" class=" _681a23b0649e61ca572cb5bb8db206ec" alt="图片"/></td>
                <td  width="17%" style="padding:5px 10px;"><strong>领导姓名：</strong></td>
                <td width="60%" style="padding:5px 10px;">{{name}}</td>
            </tr>
            <tr>
                <td style="padding:5px 10px;"><strong>职    务：</strong></td>
                <td style="padding:5px 10px;">{{position}}</td>
            </tr>
            <tr>
                <td style="padding:5px 10px;"><strong>领导简历：</strong></td>
                <td style="padding:5px 10px;"><p style="text-align: left; text-indent: 2em;">{{introduction}}</p><p style="text-align: left; text-indent: 2em;"></p><p></p></td>
            </tr>

            <tr>
                <td align="center">&nbsp;</td>
                <td style="padding:5px 10px;"><strong>工作分工：</strong></td>
                <td style="padding:5px 10px; text-align: left; text-indent: 2em;">{{work}}</td>
            </tr>


            <tr>
                <td align="center">&nbsp;</td>
                <td style="padding:5px 10px;"><strong>联系电话：</strong></td>
                <td style="padding:5px 10px;">{{tele}}</td>
            </tr>
            </tbody>
        </table>

    </div>
</template>

<script>
    import axios from "axios";

    export default {
        name: "EmployeeDetailView",
        data(){
            return{
                img:"person1.png",
                name:'',
                position:'',
                introduction:'',
                work:'',
                tele:'',
                grow:'',
            }
        },
        created() {
            const _this = this
            console.log(window.sessionStorage.employee_id)
<<<<<<< HEAD
            axios.post('http://10.16.104.183:8181/employee/getById/'  + window.sessionStorage.employee_id).then(function (resp) {
=======
            axios.post('http://localhost:8181/employee/getById/'  + window.sessionStorage.employee_id).then(function (resp) {
>>>>>>> ffc14b02a948452209c8504655d41786bdd0c07f
                if(resp.data.code == "200"){//返回成功
                    console.log(resp.data.data)
                    _this.img = resp.data.data.img
                    _this.name = resp.data.data.name
                    _this.position = resp.data.data.position
                    _this.introduction = resp.data.data.introduce
                    _this.tele = resp.data.data.tele
                    _this.work = resp.data.data.work
                }else if(resp.data.code == "101"){
                    _this.$message.error('出现错误');
                    return false;
                }
            })

        }

    }
</script>

<style scoped>

</style>