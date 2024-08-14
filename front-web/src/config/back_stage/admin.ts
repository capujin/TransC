import { type Route } from '@/router/index'
const adminRotes: Route = {
    route: '/admin/user/list',
    name: Symbol('userManagement'),
    title: '用户管理',
    inNav: true,
    type: 'folder', // 取 route 为默认加载页
    order: 999,
    children: [
        {
            title: '用户',
            type: 'view',
            name: Symbol('userList'),
            route: '/admin/user/list',
            filePath: 'views/back-stage/admin/user/user-list.vue',
            inNav: true,
            permission: ['用户']
        },
        {
            route: '/admin/group/list',
            type: 'view',
            name: Symbol('groupList'),
            inNav: true,
            filePath: 'views/back-stage/admin/group/group-list.vue',
            title: '角色',
            permission: ['角色']
        }
    ]
}
export default adminRotes