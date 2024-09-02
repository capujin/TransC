import adminConfig from './admin'
import Utils from '@/lin/util/utils'
import { type Routes, type Route } from '@/router/index'
let homeRouter: Routes = [
    adminConfig,
    {
        title: '首页',
        type: 'view',
        name: 'adminIndex',
        route: '/admin',
        filePath: 'views/back-stage/main.vue',
        inNav: false,
        order: 0,
        permission: ['首页']
    },
    // {
    //     title: '多源数据管理',
    //     type: 'folder',
    //     name:'jzt-data-manage',
    //     inNav: true,
    //     order: 1,
    //     children: [
    //         {
    //             title: '目标管理',
    //             alias: '金字塔目标管理',
    //             type: 'view',
    //             name: 'jzt-target',
    //             route: '/admin/target',
    //             filePath: 'view/back-stage/targetData/main.vue',
    //             inNav: true,
    //             permission: ['金字塔目标管理'],
    //         }
    //     ]
    // },
]

// 处理顺序
homeRouter = Utils.sortByOrder(homeRouter);
// 使用 Symbol 处理 name 字段, 保证唯一性
const deepReduceName = (target: Route | Routes) => {
    // children 遍历
    if (Array.isArray(target)) {
        target.forEach(item => {
            if (typeof item !== 'object') {
                return
            }
            deepReduceName(item)
        })
        return
    }
    // 正常遍历
    if (typeof target === 'object') {
        if (Array.isArray(target.children)) {
            target.children.forEach(item => {
                if (typeof item !== 'object') {
                    return
                }
                deepReduceName(item)
            })
        }
        //  else {
        //     console.warn("config->back_stage->idnex.ts：路由children未定义");
        // }
    }
}

deepReduceName(homeRouter)

export default homeRouter
