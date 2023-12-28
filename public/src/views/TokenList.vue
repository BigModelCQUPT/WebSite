<!-- 陆婉莹 token 2 -->
<template>
  <div style="margin-left: 10px; margin-top: 15px;margin-right: 10px">

    <h2 style="margin-left: -120px">token管理</h2>
    <div style="margin-top: 30px">
      <el-card class="box-card" style="width: 100%" shadow="never">

        <el-form :model="formData" label-width="180px" style="margin-top: 40px;margin-bottom : 40px">

          <el-form-item label="token1" prop="token1">
            <el-input v-model="formData.token1" style="width: 80%" type="text" placeholder='请输入token1' />
            <el-button type="primary" style="margin-left: 25px" @click="submitForm1">提交</el-button>
          </el-form-item>

          <el-form-item label="token2" prop="token2">
            <el-input v-model="formData.token2" style="width: 80%" type="text" placeholder='请输入token2' />

            <el-button type="primary" style="margin-left: 25px" @click="submitForm2">提交</el-button>
          </el-form-item>

          <el-form-item label="token3" prop="token3">
            <el-input v-model="formData.token3" style="width: 80%" type="text" placeholder='请输入token3' />
            <el-button type="primary" style="margin-left: 25px" @click="submitForm3">提交</el-button>
          </el-form-item>
        </el-form>
      </el-card>
    </div>


  </div>
</template>

<script>

  import request from '@/utils/http'
  export default {
    name: "TokenList",
    data() {
      return {
        formData: {
          token1: '',
          token2: '',
          token3: '',
        }
      }
    },
    methods: {
      submitForm1() {
        const data = {
          mailtoken: this.formData.token1,
        }
        const _this = this
        request({
          url: '/token/add/mailtoken',
          method: 'post',
          data: data
        }).then(function (resp) {
          if (resp.status == "200") {
            _this.$message.success('提交成功');
          }
          else {
            _this.$message.error('出错了');
            return false;
          }
        })

      },
      submitForm2() {
        const data = {
          token1: this.formData.token1,
        }
        const _this = this
        request({
          url: '/token/addtoken2',
          method: 'post',
          data: data
        }).then(function (resp) {
          if (resp.status == "200") {
            _this.$message.success('提交成功');
          }
          else {
            _this.$message.error('出错了');
            return false;
          }
        })

      },
      submitForm3() {
        const data = {
          token1: this.formData.token1,
        }
        const _this = this
        request({
          url: '/token/addtoken3',
          method: 'post',
          data: data
        }).then(function (resp) {
          if (resp.status == "200") {
            _this.$message.success('提交成功');
          }
          else {
            _this.$message.error('出错了');
            return false;
          }
        })

      },
    }
  }

</script>