import axios from 'axios'
const request = axios.create({
    baseURL: 'http://10.16.104.183:8181',
    timeout: 5000,
})
request.interceptors.request.use(function (config){
        const token = localStorage.getItem('jwt_token')
        config.headers.Authorization = "Bearer " + token
        return config
    }, function (error) {Promise.reject(error)}
)

export default request