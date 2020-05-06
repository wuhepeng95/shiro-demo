<template>
  <div style="background-color: thistle;width: 300px;margin: 0 auto;">
    <el-input v-model="username" placeholder="用户名" size="medium" class="my_input"></el-input>
    <br>
    <el-input v-model="password" placeholder="密码" size="medium" class="my_input" show-password></el-input>
    <br>
    <br>
    <el-checkbox style="color: black;margin-right: 20px">记住我</el-checkbox>
    <el-button type="primary" @click="doRegister">Register</el-button>
    <el-button type="primary" @click="doLogin"> Log In </el-button>
  </div>
</template>

<script>
export default {
  // name: 'Login',
  data () {
    return {
      'username': '',
      'password': ''
    }
  },
  methods: {
    doLogin () {
      let requestParam = {}
      requestParam.username = this.username
      requestParam.password = this.password
      this.$axios.post('/login', requestParam)
        .then(response => {
          if (response.status === 200 && response.data.code === 200) {
            localStorage.setItem('token', response.data)
            localStorage.setItem('username', this.username)
            this.$router.replace('/index')
            // this.$router.push('/index')
          } else {
            this.$message.error(response.data.msg)
          }
        })
    },
    doRegister () {
      let requestParam = {}
      requestParam.username = this.username
      requestParam.password = this.password
      this.$axios.post('/register', requestParam)
        .then(response => {
          if (response.status === 200 && response.data.code === 200) {
            // error注册成功再登录
            this.doLogin()
          } else {
            this.$message.error(response.data.msg)
          }
        })
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  .my_input {
    width: 200px;
  }
</style>
