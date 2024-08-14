import stageConfig from '@/config/back_stage'
// 后台管理路由
import { type Routes, type Route } from '@/router/index'
import { type RouteRecordRaw } from 'vue-router'
// 深度遍历配置树, 摘取叶子节点作为路由部分
function deepTravel(config: Routes | Route, fuc: (viewConfig: any) => any) {
  if (Array.isArray(config)) {
    config.forEach((subConfig: Route) => {
      deepTravel(subConfig, fuc)
    })
  } else if (config.children) {
    config.children.forEach(subConfig => {
      deepTravel(subConfig, fuc)
    })
  } else {
    fuc(config)
  }
}

const homeRouter: Array<RouteRecordRaw> = []

deepTravel(stageConfig, viewConfig => {
  // 构造实际能用的view路由
  const viewRouter = {
    path: viewConfig.route,
    name: viewConfig.name,
    component: () => import(`@/${viewConfig.filePath}`),
    meta: {
      title: viewConfig.title,
      alias: viewConfig.alias,
      icon: viewConfig.icon,
      permission: viewConfig.permission,
      type: viewConfig.type,
      blueBaseColor: viewConfig.blueBaseColor ? 'viewConfig.blueBaseColor' : '',
    }
  }

  homeRouter.push(viewRouter)
})

export default homeRouter
