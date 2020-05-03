<template>
  <div style="background-color: thistle">
    <el-input v-model="username" placeholder="用户名" size="medium" class="my_input"></el-input>
    <br>
    <el-input v-model="password" placeholder="密码" size="medium" class="my_input"></el-input>
    <br>
    <br>
    <el-checkbox style="color: black;margin-right: 20px">记住我</el-checkbox>
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
      this.$router.push('/index')
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
