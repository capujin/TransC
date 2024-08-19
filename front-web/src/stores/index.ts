import { removeToken } from '@/lin/util/token';
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
    function setUserPermissions(newPermissions: any[]) {
        const _permissions = []
        for (const i in newPermissions) {
            for (const key in newPermissions[i]) {
                _permissions.push(newPermissions[i][key].name)
            }
        }
        permissions.value = _permissions
    }
    function removeLogined() {
        logined.value = false;
        user.value = {};
    }

    // 菜单列表
    const menus: Ref<string[]> = ref([]);
    function setUserMenus(newMenus: any[]) {
        const _menus = [];
        for (const i of newMenus) {
            _menus.push(i);
        }
        permissions.value = permissions.value.concat(_menus);
    }

    // 推送消息
    const readedMessages: Ref<any[]> = ref([]);
    const unreadMessages: Ref<any[]> = ref([]);
    function addReadedMessage(msg: any) {
        readedMessages.value.push(msg);
    }
    function addUnReadedMessage(msg: any) {
        unreadMessages.value.push(msg);
    }
    function removeUnReadedMessage(msg_id: string) {
        const index = unreadMessages.value.findIndex(el => el.id === msg_id);
        unreadMessages.value.splice(index, 1);
    }

    const setUserAndState = (newUser: any) => {
        // 如果登陆成功，设置logined标志位
        logined.value = true;
        // 设置全局用户状态
        Object.assign(user, newUser);
    };
    const loginOut = () => {
        removeToken();
        removeLogined();
    }
    const readMessage = (message: any) => {
        removeUnReadedMessage(message?.id);
        addReadedMessage(message);
    }

    return {
        // state
        logined,
        user,
        readedMessages,
        unreadMessages,
        permissions,
        menus,

        // mutation
        addUnReadedMessage,
        setUserMenus,
        setUserPermissions,

        // action
        setUserAndState,
        loginOut,
        readMessage

    }
})