import stageConfig from '@/config/back_stage'
// 后台管理路由
import { type Routes, type Route } from '@/router/index'
import { type RouteRecordRaw } from 'vue-router'

import * as ViewsBack from '@/views/back-stage/index'
import Utils from '@/lin/util/utils'
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
  let viewName = 'MainView' as keyof typeof ViewsBack;
  try {
    viewName = `${formatCompoentName(viewConfig.filePath)}` as keyof typeof ViewsBack;
  } catch (e) {
    viewName = 'MainView' as keyof typeof ViewsBack;
  }
  // 构造实际能用的view路由
  // console.log("`@/${viewConfig.filePath}`:", formatCompoentName(viewConfig.filePath));
  const viewRouter = {
    path: viewConfig.route,
    name: viewConfig.name,
    component: ViewsBack[viewName],
    // component: () => import(/* @vite-ignore */`@/${viewConfig.filePath}`),
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
function formatCompoentName(com: string) {
  const match = com.match(/\/([^/]+)\.vue$/);
  return match ? match[1].charAt(0).toUpperCase() + match[1].slice(1) + 'View' : null;
}
export default homeRouter
