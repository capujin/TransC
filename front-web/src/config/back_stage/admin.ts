const adminRotes = {
    route: '/admin/user/list',
    name: null,
    title: '用户管理',
    inNav: true,
    icon: 'iconfont icon-huiyuanguanli',
    type: 'folder', // 取 route 为默认加载页
    order: 999,
    children: [
        {
            title: '用户',
            type: 'view',
            name: 'userList',
            route: '/admin/user/list',
            filePath: 'views/back-stage/admin/user/user-list.vue',
            inNav: true,
            icon: 'iconfont icon-huiyuanguanli',
            permission: ['用户']
        },
        {
            route: '/admin/group/list',
            type: 'view',
            name: 'groupList',
            inNav: true,
            filePath: 'views/back-stage/admin/group/group-list.vue',
            title: '角色',
            icon: 'iconfont icon-huiyuanguanli',
            permission: ['角色']
        }
    ]
}
export default adminRotes