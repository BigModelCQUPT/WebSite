const { defineConfig } = require('@vue/cli-service')

let proxyObj = {}
proxyObj['/ws'] = {
  ws: true,
  target: 'ws://10.16.104.183:8181'
};
proxyObj['/'] = {
//websocket
  ws: false,
//目标地址
  target: 'http://10.16.104.183:8081',
//发送请求头中host会设置成target
  changeOrigin: true,
//不重写请求地址
  pathRewrite: {
    '^/': '/'
  }
}
module.exports = {
  devServer: {
    host: '10.16.104.183',
    port: 8080,
    proxy: proxyObj
  }
}

module.exports = defineConfig({
  transpileDependencies: true
})