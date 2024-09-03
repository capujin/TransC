import { router, type Route, type Routes } from '@/router'
import Utils from "@/lin/util/utils";
import stageConfig from '@/config/back_stage/'
import backRouter from '@/router/back_route'
import FRONT_CONFIG from "@/views/front-stage/FRONT_CONFIG";
import type { RouteRecordInfo, RouteRecordRaw } from 'vue-router';
import * as Views from '@/views/front-stage'

const getsystemConfigFunc = async function () {
    // 格式化router路由
    const updateBackStageConfig: (stageConfig: Routes | undefined, pathPrefix: string) => Routes = (stageConfig, pathPrefix) => {
        if (Array.isArray(stageConfig)) {
            // 当 stageConfig 是数组时，使用 map 进行迭代
            return stageConfig.map(item => {
                const oldName = item.name;
                // console.log(item);
                
                const newConfig: Route = {
                    ...item,
                    route: `${pathPrefix}${item.route}`,
                    name: `${pathPrefix}-${oldName}`
                };
                if (item.children && item.children.length) {
                    newConfig.children = updateBackStageConfig(item.children, pathPrefix);
                }
                return newConfig;
            });
        } else {
            // 当 stageConfig 为 undefined 时，返回空数组
            return [];
        }
    };
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
    const getMenus = function (menusTitleList: string[], pathPrefix: string) {
        // console.log("pathPrefix",pathPrefix);
        
        // menusTitleList:字符串数组['首页...'], pathPrefix:系统前缀字符串'\fs'
        // console.log("menusTitleList:", menusTitleList);
        const menus = []       
        for (const item of backRouter) {
            const oldName = typeof item.name === 'symbol' ? item.name.description : item.name
            const routeObj = {
                ...item,
                path: `${pathPrefix}${item.path}`,
                name: `${oldName}` // 确保name一定是唯一的
            }
            menus.push(routeObj)
        }
        return menus
    }
    const system = {
        "fullname": "TransC",
        //     {
        //         "children": [],
        //         "label": "首页"
        //     },
        //     {
        //         "children": [
        //             {
        //                 "label": "用户"
        //             },
        //             {
        //                 "label": "角色"
        //             }
        //         ],
        //         "label": "用户管理"
        //     }
        // ],
        "name": "TransC",
        "permissions": [
            {
                "children": [
                    {
                        "id": 63,
                        "label": "创建事理图谱新闻",
                        "module": "事理图谱新闻"
                    },
                    {
                        "id": 64,
                        "label": "更新事理图谱新闻",
                        "module": "事理图谱新闻"
                    }
                ],
                "id": "事理图谱新闻",
                "label": "事理图谱新闻"
            }
        ],
        "route": "/transc"
    }
    let newBackStageConfig: Routes = []
    if (window.location.pathname.includes(system.route)) {
        document.title = system.fullname
    }

    newBackStageConfig = newBackStageConfig.concat(updateBackStageConfig(stageConfig, system.route))
    // console.log("newBackStageConfig:", newBackStageConfig);

    const menus = getMenus(['模拟菜单'], system.route.slice(1))
    // console.log("menus----------------:", menus);
    router.addRoute({
        path: `${system.route}/admin/login`,
        name: 'adminLogin',
        component: () => import('@/views/Login.vue')
    })
    router.addRoute({
        // path: `${system.route}/admin`,
        path:'',
        name: 'adminPage',
        component: () => import('@/views/home/back-home.vue'),
        children: [...menus]
        // children: back_children
    })
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
    // console.log("后台管理路由：", backRouter);
    // console.log("后台管理路由2：", stageConfig);
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
    // console.log("初始化getSystemConfigPlugin");

    await getsystemConfigFunc()
    return router
}