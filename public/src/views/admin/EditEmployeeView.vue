<!--领导轮播图-->
<template>
    <div style="width: 70%; margin: auto;">
        <el-carousel :interval="2000" type="card" height="500px" >
            <el-carousel-item
                    v-for="(item, index) in userInfo"
                    :key="item"
                    style="background-color: white; "
                    @click="getDetail(userInfo[index].id)"
            >
                <span style="font-size: 25px">{{item.position}}</span>
                <div style="display:inline-flex; color: #3399ff">
                    <div style="width: 30%; margin-top: 130px; font-size: 15px">
                        <p style="margin-top: -80px; font-size: 20px">{{item.name[0]}}</p>
                        <p style="margin-top: 20px; font-size: 20px">{{item.name[1]}}</p>
                        <p style="margin-top: 20px; font-size: 20px">{{item.name[2]}}</p>
                        <p style="margin-top:45px">性别:{{item.gender}}</p>
                        <p >年龄:{{item.age}}</p>
                    </div>
                    <div style="width: 70%">
                        <el-image class="img" :src="require('../../assets/'+userInfo[index].img)" alt="找不到" :fit="cover"/>
                    </div>
                </div>
                <div align="left" style="margin-left: 5px; margin-right: 5px">
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    {{item.introduce}}
                </div>
            </el-carousel-item>
        </el-carousel>
    </div>
</template>
<script>

    import axios from 'axios'

    export default {
        name: "EditEmployeeView",
        data(){
            return {
                userInfo:[
                    {
                        id:'',
                        name:'吴彦祖',
                        position:'党委书记',
                        gender:'男',
                        age:'45',
                        img:'person1.png',
                        introduce:'吴彦祖，男，汉族，1953年6月生，陕西富平人，1969年1月参加工作，1974年1月加入中国共产党，清华大学人文社会学院马克思主义理论与思想政治教育专业毕业，在职研究生学历，法学博士学位。'
                    }, {
                        id:'',
                        name:'张三丰',
                        position:'党委副书记',
                        gender:'女',
                        age:'35',
                        img:'person2.png',
                        introduce:'张三丰，女，汉族，1953年6月生，陕西富平人，1969年1月参加工作，1974年1月加入中国共产党，清华大学人文社会学院马克思主义理论与思想政治教育专业毕业，在职研究生学历，法学博士学位。'
                    }, {
                        id:'',
                        name:'马虹波',
                        position:'社区专员',
                        gender:'男',
                        age:'25',
                        img: 'person3.png',
                        introduce:'马虹波，男，汉族，1953年6月生，陕西富平人，1969年1月参加工作，1974年1月加入中国共产党，清华大学人文社会学院马克思主义理论与思想政治教育专业毕业，在职研究生学历，法学博士学位。'
                    }
                ]
            }
        },
        methods:{
            getDetail(index){
                window.sessionStorage.setItem("employee_id", index)
                this.$router.push('/editLeader');
            }
        },
        created() {
            const _this = this
            axios.get('http://localhost:8181/employee/getInformation').then(function (resp) {
                console.log(resp.data.data)
                if(resp.data.code == "200"){//返回成功
                    _this.userInfo = resp.data.data
                }else if(resp.data.code == "101"){
                    return false;
                }
            })
        }
    }
</script>

<style scoped>
    .el-carousel__item h3 {
        color: #475669;
        font-size: 14px;
        opacity: 0.75;
        line-height: 200px;
        margin: 0;
        text-align: center;
    }
    .img{
        margin-top: 10px;
        background: rgba(0, 0, 0, 0.3);
        border-radius: 20px;
    }
</style>