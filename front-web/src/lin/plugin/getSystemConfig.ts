import { router } from '@/router'

// import { getSystemConfigPlugin as getSystemConfig } from '@/lin/plugin/getSystemConfig'

// const rote = await getSystemConfig()
// console.log(rote);
const getsystemConfigFunc = async function () {
    console.log("路由：", router);
    let map = [{
        path: '/',
        name: 'login',
        component: () => import('@/views/Login.vue')
    },
    {
        path: '/login',
        name: 'login',
        component: () => import('@/views/Login.vue')
    }];
    map.map(i => (router.addRoute(i)))
}

export const getSystemConfigPlugin = async function () {
    await getsystemConfigFunc()
    return router
}