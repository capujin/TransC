import { ref, reactive, type Ref, type Reactive } from 'vue'
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

export default {
    logined,
    user,
    readedMessages,
    unreadMessages,
    permissions,
    menus
}