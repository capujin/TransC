import { router, type Route, type Routes } from '@/router'
import Utils from "@/lin/util/utils";
import stageConfig from '@/config/back_stage/'
import backRouter from '@/router/back_route'
import FRONT_CONFIG from "@/views/front-stage/FRONT_CONFIG";
import type { RouteRecordInfo, RouteRecordRaw } from 'vue-router';
import * as Views from '@/views/front-stage'

// const rote = await getSystemConfig()
// console.log(rote);
const getsystemConfigFunc = async function () {
    // const setBackStageConfig = store._actions.setBackStageConfig[0]
    // const setSystemConfig = store._actions.setSystemConfig[0]
    // const setSystem = store._actions.setSystem[0]

    // 格式化router路由
    // const updateBackStageConfig = function (stageConfig: Routes, pathPrefix: string) {
    //     return stageConfig.map(item => {
    //         const oldName = item.name.description
    //         const newConfig = {
    //             ...item,
    //             route: `${pathPrefix}${item.route}`,
    //             name: Symbol(`${pathPrefix}-${oldName}`)
    //         }
    //         if (item.children && item.children.length) {
    //             newConfig.children = updateBackStageConfig(item.children, pathPrefix)
    //         }
    //         return newConfig
    //     })
    // }
    // 获取菜单列表，把数据格式化成二维的
    // const getMenusList = function (menus: any[]) {
    //     const menusList: any[] = []
    //     menus.forEach(menu => {
    //         if (menu.children && menu.children.length) {
    //             menusList.push(...getMenusList(menu.children))
    //         } else {
    //             menusList.push(menu.label)
    //         }
    //     })
    //     return menusList
    // }
    // 
    // const isIncludesTitle = function (list, routerItem) {
    //     return list.some(i => {
    //         const title = i.replace(/\(.*\)/g, '')
    //         const aliasList = i.match(/(?<=\()(.+?)(?=\))/g)
    //         if (aliasList) {
    //             return title === routerItem.meta.title && aliasList[0] === routerItem.meta.alias
    //         } else {
    //             return title === routerItem.meta.title
    //         }
    //     })
    // }
    // 获取全部菜单标题
    // const getMenus = function (menusTitleList, pathPrefix) {
    //     const menus = []
    //     for (const item of backRouter) {
    //         if (isIncludesTitle(menusTitleList, item)) {
    //             const oldName = typeof item.name === 'symbol' ? item.name.description : item.name
    //             const routeObj = {
    //                 ...item,
    //                 path: `${pathPrefix}${item.path}`,
    //                 name: Symbol(`${pathPrefix}-${oldName}`)
    //             }
    //             menus.push(routeObj)
    //         }
    //     }
    //     return menus
    // }
    // await fetch('/api/v1/system').then(res => {
    //     console.log(res);
    // })
    // .then(response => response.json())
    // .then(res => {
    //     if (res.code !== 0) {
    //         Vue.prototype.$message.error('系统配置项加载错误，请重试')
    //         return
    //     }
    //     const systemList = res.data.filter(i => i.is_enable)
    //     setSystemConfig(systemList)
    //     let newBackStageConfig = []
    //     systemList.forEach(system => {
    //         if (window.location.pathname.includes(system.route) || system.is_default) {
    //             setSystem(system.route.slice(1))
    //             Vue.prototype['$CONFIG'] = Utils.deepClone(system.extra)
    //             document.title = system.fullname
    //         }
    //         // const menusTitleList = system.menus.map(i => i.label)
    //         const menusTitleList = getMenusList(system.menus)
    //         newBackStageConfig = newBackStageConfig.concat(updateBackStageConfig(stageConfig, system.route))
    //         const menus = getMenus(menusTitleList, system.route)

    //         router.addRoutes([{
    //             path: `${system.route}/admin/login`,
    //             name: 'adminLogin',
    //             component: () => import('@/view/login/login')
    //         }, {
    //             path: `${system.route}/admin`,
    //             name: 'adminPage',
    //             component: () => import('@/view/home/back-home'),
    //             children: [...menus],
    //         }])

    //         const portalRoutes = []
    //         Array.isArray(PORTAL_CONFIG[system.route]) && PORTAL_CONFIG[system.route].forEach(item => {
    //             const path = `${system.route}${item === '/main' ? '' : item}`
    //             portalRoutes.push({
    //                 path,
    //                 name: 'portalIndex',
    //                 component: () => import('@/view/home/portal-home'),
    //                 children: [{
    //                     name: 'portalIndex',
    //                     path,
    //                     component: () => import(`@/view/portal-page${system.route}${item}/main.vue`),
    //                     meta: {
    //                         title: '首页'
    //                     }
    //                 }]
    //             })
    //         })
    //         PORTAL_CONFIG['/common'].forEach(item => {
    //             const path = `/common${item}`
    //             portalRoutes.push({
    //                 path,
    //                 name: 'portalIndex',
    //                 component: () => import('@/view/home/portal-home'),
    //                 children: [{
    //                     name: 'portalIndex',
    //                     path,
    //                     component: () => import(`@/view/portal-page/common${item}/main.vue`),
    //                     meta: {
    //                         title: '首页'
    //                     }
    //                 }]
    //             })
    //         })
    //         router.addRoutes(portalRoutes)

    //         if (system.is_default) {
    //             router.addRoutes([{
    //                 path: '/admin/login',
    //                 redirect: `${system.route}/admin/login`,
    //             }, {
    //                 path: '/admin',
    //                 redirect: `${system.route}/admin`,
    //             }, {
    //                 path: '/',
    //                 redirect: system.route
    //             }])
    //         }
    //     })
    //     setBackStageConfig(newBackStageConfig)
    // })
    const componentMap = {
        '/main': 'MainView',
        '/pathEditor': 'PathEditorView',
        '/another': 'AnotherView', // 为新的组件添加映射
    };
    const portalRoutes: RouteRecordRaw[] = []
    Array.isArray(FRONT_CONFIG['/transc']) && FRONT_CONFIG['/transc'].forEach((item:string) => {
        const path = `${'/transc'}${item === '/main' ? '' : item}`
        const componentName = isKey(item, componentMap) ? componentMap[item] : 'MainView'; // 使用映射获取组件名
        const _component = Views[componentName as keyof typeof Views];
        portalRoutes.push({
            path,
            name: 'front',
            component: () => import('@/views/home/front-home.vue'),
            children: [{
                name: 'frontIndex',
                path,
                component: _component,
                meta: {
                    title: '首页'
                }
            }]
        })
    })
    portalRoutes.forEach(route => {
        router.addRoute(route);
    })
    // FRONT_CONFIG['/common'].forEach(item => {
    //     const path = `/common${item}`
    //     portalRoutes.push({
    //         path,
    //         name: 'portalIndex',
    //         component: () => import('@/views/home/portal-home'),
    //         children: [{
    //             name: 'portalIndex',
    //             path,
    //             component: () => import(`@/views/portal-page/common${item}/main.vue`),
    //             meta: {
    //                 title: '首页'
    //             }
    //         }]
    //     })
    // })
    console.log("后台管理路由：", backRouter);
    console.log("后台管理路由2：", stageConfig);
    //     let map = [{
    //         path: '/',
    //         name: 'login',
    //         component: () => import('@/views/Login.vue')
    //     },
    //     {
    //         path: '/login',
    //         name: 'login',
    //         component: () => import('@/views/Login.vue')
    //     }
    // ];
    //     map.map(i => (router.addRoute(i)))
}
// 类型守卫函数，检查item是否是componentMap的有效键
function isKey<T>(key: string, obj: Record<string, T>): key is keyof typeof obj {
    return key in obj;
}
export const getSystemConfigPlugin = async function () {
    console.log("初始化getSystemConfigPlugin");

    await getsystemConfigFunc()
    return router
}