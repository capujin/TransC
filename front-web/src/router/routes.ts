import { type RouteRecordRaw } from 'vue-router'
const routes: RouteRecordRaw[] = [
    // {
    //     path: '/super/admin/login',
    //     name: 'superAdminLogin',
    //     component: () => import('@/views/Login.vue'),
    //     meta: {
    //         title: '超级管理员登录'
    //     }
    // },
    // {
    //     path: '/super/admin',
    //     name: 'superAdmin',
    //     component: () => import('@/view/super/main')
    // },
    {
        path: '/404',
        name: '404',
        component: () => import('@/views/error-stage/404.vue'),
        meta: {
            title: '页面未找到'
        }
    },
    {
        redirect: '/404',
        path: '/:pathMatch(.*)*',
    },
]
export default routes