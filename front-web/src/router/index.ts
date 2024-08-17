import { createRouter, createWebHistory, type Router } from 'vue-router'
import routes from './routes'
const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes,
    scrollBehavior: () => ({
        top: 0,
    }),
})
router.beforeEach((to, from, next) => {
    // 如果路由配置了 meta.title，就设置为该值
    if (to.meta.title) {
        document.title = to.meta.title as string
    }
    next()
})
// 定义一个类型来表示单个路由信息
type Route = {
    title: string;
    type: 'folder' | 'view';
    alias?: string;
    name: Symbol;
    filePath?: string;
    route?: string;
    inNav: boolean;
    order?: number;
    permission?: string[],
    children?: Route[];
};
type Routes = Array<Route>;
export { router }
export type { Route, Routes }