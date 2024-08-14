import { createRouter, createWebHistory, type Router } from 'vue-router'
// import routes from './routes'
export const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path: '/about',
            name: 'about',
            component: () => import('../views/AboutView.vue')
        },
        {
            path: '/404',
            name: '404',
            component: () => import('@/views/error-stage/404.vue')
        },
        {
            path: '/:pathMatch(.*)*',
            name: 'NotFound',
            redirect: '/404'
        },
        {
            path:'/game',
            name:'RunOfTheRabbit',
            component: () => import('@/views/others/RunOfTheRabbit.vue')
        }
    ]
})
