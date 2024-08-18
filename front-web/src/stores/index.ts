import { defineStore } from 'pinia'
import { ref, reactive, type Ref, type Reactive } from 'vue'

// 你可以任意命名 `defineStore()` 的返回值，但最好使用 store 的名字，同时以 `use` 开头且以 `Store` 结尾。
// (比如 `useUserStore`，`useCartStore`，`useProductStore`)
// 第一个参数是你的应用中 Store 的唯一 ID。
export const useUserStore = defineStore('user', () => {

    // 登录状态
    const logined: Ref<boolean> = ref(false);

    // 用户信息
    const user: Reactive<any> = reactive({});

    // 权限列表
    const permissions: Ref<string[]> = ref([]);

    // 菜单列表
    const menus: Ref<string[]> = ref([]);

    // 推送消息
    const readedMessages: Ref<any[]> = ref([]);
    const unreadMessages: Ref<any[]> = ref([]);

    return {
        logined,
        user,
        readedMessages,
        unreadMessages,
        permissions,
        menus
    }
})