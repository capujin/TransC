// eslint-disable-next-line import/no-mutable-exports
import Utils from '@/lin/util/utils'
import {type Routes,type Route} from '@/router'
let homeRouter:Routes = []

const plugins:Routes = []

// 筛除已经被添加的插件
function filterPlugin(data:Routes | Route) {
  if (plugins.length === 0) {
    return
  }
  if (Array.isArray(data)) {
    data.forEach((item:Route) => {
      filterPlugin(item)
    })
  } else {
    const findResult = plugins.findIndex(item => data === item)
    if (findResult >= 0) {
      plugins.splice(findResult, 1)
    }
    if (data.children) {
      filterPlugin(data.children)
    }
  }
}

filterPlugin(homeRouter)

homeRouter = homeRouter.concat(plugins)

// 处理顺序
homeRouter = Utils.sortByOrder(homeRouter)

// 使用 Symbol 处理 name 字段, 保证唯一性
const deepReduceName = (target:Routes | Route) => {
  if (Array.isArray(target)) {
    target.forEach((item:Route) => {
      if (typeof item !== 'object') {
        return
      }
      deepReduceName(item)
    })
    return
  }
  if (typeof target === 'object') {
    if (Array.isArray(target.children)) {
      target.children.forEach(item => {
        if (typeof item !== 'object') {
          return
        }
        deepReduceName(item)
      })
    }
  }
}

deepReduceName(homeRouter)

export default homeRouter

