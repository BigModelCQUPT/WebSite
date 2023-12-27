const { defineConfig } = require('@vue/cli-service')

let proxyObj = {}
proxyObj['/ws'] = {
  ws: true,
<<<<<<< HEAD
  target: 'ws://10.16.104.183:8181'
=======
  target: 'ws://localhost:8181'
>>>>>>> ffc14b02a948452209c8504655d41786bdd0c07f
};
proxyObj['/'] = {
//websocket
  ws: false,
//目标地址
<<<<<<< HEAD
  target: 'http://10.16.104.183:8081',
=======
  target: 'http://localhost:8181',
>>>>>>> ffc14b02a948452209c8504655d41786bdd0c07f
//发送请求头中host会设置成target
  changeOrigin: true,
//不重写请求地址
  pathRewrite: {
    '^/': '/'
  }
}
module.exports = {
  devServer: {
<<<<<<< HEAD
    host: '10.16.104.183',
=======
    host: 'localhost',
>>>>>>> ffc14b02a948452209c8504655d41786bdd0c07f
    port: 8080,
    proxy: proxyObj
  }
}

module.exports = defineConfig({
  transpileDependencies: true
})