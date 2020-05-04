import axios from 'axios'
import Vue from 'vue'
import HttpStatus from 'http-status'

let instance = axios.create({
  baseURL: '/api'
})

instance.interceptors.request.use(config => {
  let token = localStorage.getItem('token')
  if (token) {
    config.headers.Authorization = token
  }
  return config
})

instance.interceptors.response.use((response) => {
  return response
}, (error) => {
  if (error.message.indexOf('Network Error') >= 0) {
    Vue.prototype.$message.error('Network error, please check your network settings!')
  } else if (error.response.status === HttpStatus.UNAUTHORIZED) {
    localStorage.removeItem('token')
    localStorage.removeItem('username')
    Vue.prototype.$message.error('Authorized failed,please login.')
  } else if (error.response.status >= HttpStatus.BAD_REQUEST) {
    Vue.prototype.$message.error(error.response.data.message)
  }
})

export const AXIOS = instance
