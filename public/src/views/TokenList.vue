<!-- 陆婉莹 token 2 -->
<template>
  <div style="margin-left: 10px; margin-top: 15px;margin-right: 10px">

    <h2 style="margin-left: -120px">token管理</h2>
    <div style="margin-top: 30px">
      <el-card class="box-card" style="width: 100%" shadow="never">

        <el-form :model="formData" label-width="180px" style="margin-top: 40px;margin-bottom : 40px">

          <el-form-item label="Mail token" prop="token1">
            <el-input v-model="formData.token1" style="width: 80%" type="text" placeholder='请输入token1' />
            <el-button type="primary" style="margin-left: 25px" @click="submitForm1">提交</el-button>
          </el-form-item>

          <el-form-item label="YouTube token" prop="token2">
            <el-input v-model="formData.token2" style="width: 80%" type="text" placeholder='请输入token2' />

            <el-button type="primary" style="margin-left: 25px" @click="submitForm2">提交</el-button>
          </el-form-item>

          <el-form-item label="Twitter token" prop="token3">
            <el-input v-model="formData.token3" style="width: 80%" type="text" placeholder='请输入token3' />
            <el-button type="primary" style="margin-left: 25px" @click="submitForm3">提交</el-button>
          </el-form-item>
        </el-form>
      </el-card>
    </div>


    <h2 style="margin-left: -120px">修改邮箱 </h2>
    <div style="margin-top: 30px">
      <el-card class="box-card" style="width: 100%" shadow="never">

        <el-form :model="formData" :rules="emailRules" ref="email" label-width="180px"
          style="margin-top: 40px;margin-bottom : 40px">

          <el-form-item label="邮箱" prop="email">
            <el-input v-model="formData.email" style="width: 80%" type="text" placeholder='请输入修改邮箱' />
            <el-button type="primary" style="margin-left: 25px" @click="submitForm4">提交</el-button>
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
          email: '',
        },
        checked: true,
        emailRules: {
          email: [
            {
              required: true,
              message: "请输入邮箱",
              trigger: "blur"
            },
            {
              validator: function (rule, value, callback) {
                if (
                  // eslint-disable-next-line
                  /^\w{1,64}@[a-z0-9\-]{1,256}(\.[a-z]{2,6}){1,2}$/i.test(
                    value
                  ) == false
                ) {
                  callback(new Error("邮箱格式错误"));
                } else {
                  callback();
                }
              },
              trigger: "blur"
            }
          ]


        },

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
          youtubetoken: this.formData.token2,
        }
        const _this = this
        request({
          url: '/token/add/youtubetoken',
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
          twittertoken: this.formData.token3,
        }
        const _this = this
        request({
          url: '/token/add/twittertoken',
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

      submitForm4() {
        const data = {
          useremail: this.formData.email,
        }
        const _this = this
        this.$refs.email.validate((valid) => {
          if (valid) {
            request({
              url: '/token/add/useremail',
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
          } else {
            _this.$message.error('请输入正确邮箱');
            return false;
          }
        });

      },




    }
  }

</script>