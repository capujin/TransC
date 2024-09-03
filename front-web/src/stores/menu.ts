import { defineStore } from 'pinia'
import portalPageConfig from '@/config/front_stage' // 引入舞台配置
// 你可以任意命名 `defineStore()` 的返回值，但最好使用 store 的名字，同时以 `use` 开头且以 `Store` 结尾。
// (比如 `useUserStore`，`useCartStore`，`useProductStore`)
// 第一个参数是你的应用中 Store 的唯一 ID。
export const useMenuStore = defineStore('menu', {
    state: () => ({ 
        route:'/transc',
        system: '',
        systemConfig: [],
        portalStageConfig: portalPageConfig,
        backStageConfig: null,
    }),
    getters: {
        // doubleCount: (state) => state.count * 2,
    },
    actions: {
        // increment() {
        //     this.count++
        // },
    },
})