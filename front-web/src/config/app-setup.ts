import { setupRouter } from "@/router/router-setup";
import type { App } from 'vue';
// import { createPinia } from 'pinia'
// import { createPinia } from 'pinia'
// app.use(createPinia())

export function setupApp(app: App) {
    // setupStore(app); // 设置全局仓库
    setupRouter(app);   // 设置路由
    // 设置ElementUi
    // 设置Echarts
    // ......
    // 增加 setup 步骤
}
