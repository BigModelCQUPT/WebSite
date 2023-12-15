import router from './router'
import { createApp } from 'vue'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import App from './App.vue'

// const app = createApp(App)
// app.use(ElementPlus)
// // app.component(
// //     'MyComponent',
// //     {
// //     }
// // )
// // app.mount('#app')

// app.use(router).mount('#app')


createApp(App).use(router).use(ElementPlus).mount('#app')


