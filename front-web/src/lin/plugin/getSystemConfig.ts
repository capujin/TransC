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
    // menusTitleList:字符串数组['首页...'], pathPrefix:系统前缀字符串'\fs'
    //     const menus = []
    //     for (const item of backRouter) {
    // 这里是router文件夹导出的back-rouer，目前导出为格式化后的config文件夹back-stage文件夹的index文件
    //         if (isIncludesTitle(menusTitleList, item)) {
    // isIncludesTitle 返回一个确定存在的title
    //             const oldName = typeof item.name === 'symbol' ? item.name.description : item.name
    //             const routeObj = {
    //                 ...item,
    //                 path: `${pathPrefix}${item.path}`,
    //                 name: Symbol(`${pathPrefix}-${oldName}`) // 确保name一定是唯一的
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
    // 返回值：res的data[]
    //   "extra": {
    //     "isHiddenSidebarLogo": "true"
    //   },
    //   "fullname": "事理图谱",
    //   "id": 7,
    //   "is_default": false,
    //   "is_enable": true,
    //   "menus": [
    //     {
    //       "children": [],
    //       "label": "首页"
    //     },
    //     {
    //       "children": [
    //         {
    //           "label": "用户"
    //         },
    //         {
    //           "label": "角色"
    //         }
    //       ],
    //       "label": "用户管理"
    //     }
    //   ],
    //   "name": "事理图谱",
    //   "permissions": [
    //     {
    //       "children": [
    //         {
    //           "id": 63,
    //           "label": "创建事理图谱新闻",
    //           "module": "事理图谱新闻"
    //         },
    //         {
    //           "id": 64,
    //           "label": "更新事理图谱新闻",
    //           "module": "事理图谱新闻"
    //         }
    //       ],
    //       "id": "事理图谱新闻",
    //       "label": "事理图谱新闻"
    //     }
    //   ],
    //   "route": "/sl"
    // }
    //     if (res.code !== 0) {
    //         Vue.prototype.$message.error('系统配置项加载错误，请重试')
    //         return
    //     }
    // 所有系统里启用路由的data项
    //     const systemList = res.data.filter(i => i.is_enable)

    //     setSystemConfig(systemList)
    //     let newBackStageConfig = []
    //     systemList.forEach(system => {
    //     切换默认路由，向vue写入全局配置
    //         if (window.location.pathname.includes(system.route) || system.is_default) {
    //             setSystem(system.route.slice(1))
    //             Vue.prototype['$CONFIG'] = Utils.deepClone(system.extra)
    //             document.title = system.fullname
    //         }

    //         const menusTitleList = getMenusList(system.menus)
    // //system.menus: [{"children": [],"label": "首页"}]
    // getMenusList计算过后（抽取所有menusLabel）：["首页","目标管理(金字塔目标管理)","遥感数据(金字塔遥感数据)","开源数据(金字塔开源数据)","态势要素抽取与关联","态势金字塔查询","典型突发事件的态势表达","用户","角色"]
    //         newBackStageConfig = newBackStageConfig.concat(updateBackStageConfig(stageConfig, system.route))
    //         // stageConfig：是导出的config->BackStageConfig->index后端路由
    // const menus = getMenus(menusTitleList, system.route)

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
    const componentMap: { [key: string]: string } = {
        '/main': 'MainView',
        '/pathEditor': 'PathEditorView',
        '/another': 'AnotherView', // 为新的组件添加映射
    };
    const portalRoutes: RouteRecordRaw[] = []
    Array.isArray(FRONT_CONFIG['/transc']) && FRONT_CONFIG['/transc'].forEach((item: any) => {
        const path = `${'/transc'}${item === '/main' ? '' : item}`
        let componentName = 'MainView'; // 使用映射获取组件名
        if (item in componentMap) {
            componentName = componentMap[item];
        }
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
export const getSystemConfigPlugin = async function () {
    console.log("初始化getSystemConfigPlugin");

    await getsystemConfigFunc()
    return router
}