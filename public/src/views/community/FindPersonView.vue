<template>
    <div style="width: 70%; margin: auto">
        <el-input
                v-model="input3"
                placeholder="请输入要查找的工作人员的姓名"
                class="input-with-select">
            <template  #append>
                <el-icon @click="sub" style="color: #3399ff; width: 30px; margin: -10px -10px -10px -10px; height: 30px"> <Search/></el-icon>
            </template>
        </el-input>
    </div>
</template>

<script>
    import {Search} from '@element-plus/icons-vue'
    export default {
        name: "FindPersonView",
        components: {
            Search
        }

    }
</script>

<style scoped>
    .input-with-select{
        margin-top: 10px;
    }
</style>