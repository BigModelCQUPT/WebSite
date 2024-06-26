const { defineConfig } = require('@vue/cli-service')

let proxyObj = {}
proxyObj['/ws'] = {
  ws: true,
  target: 'ws://localhost:8181'
};
proxyObj['/'] = {
  //websocket
  ws: false,
  //目标地址
  target: 'http://localhost:8181',
  //发送请求头中host会设置成target
  changeOrigin: true,
  //不重写请求地址
  pathRewrite: {
    '^/': '/'
  }
}
module.exports = {
  devServer: {
    host: 'localhost',
    port: 8080,
    proxy: proxyObj
  }
}

module.exports = defineConfig({
  transpileDependencies: true
})