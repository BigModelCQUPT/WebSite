import axios from 'axios'
const request = axios.create({
<<<<<<< HEAD
    baseURL: 'http://10.16.104.183:8181',
=======
    baseURL: 'http://localhost:8181',
>>>>>>> ffc14b02a948452209c8504655d41786bdd0c07f
    timeout: 5000,
})
request.interceptors.request.use(function (config){
        const token = localStorage.getItem('jwt_token')
        config.headers.Authorization = "Bearer " + token
        return config
    }, function (error) {Promise.reject(error)}
)

export default request