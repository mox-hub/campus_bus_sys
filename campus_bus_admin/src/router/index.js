import {createRouter, createWebHashHistory} from "vue-router";
import Home from "../views/Home.vue";

const routes = [
    {
        path: '/',
        redirect: '/dashboard'
    }, {
        path: "/",
        name: "Home",
        component: Home,
        children: [
            {
                path: "/dashboard",
                name: "dashboard",
                meta: {
                    title: '系统首页'
                },
                component: () => import ( /* webpackChunkName: "dashboard" */ "../views/Dashboard.vue")
            }, 
            {
                path: "/tabs",
                name: "tabs",
                meta: {
                    title: 'tab标签'
                },
                component: () => import ( /* webpackChunkName: "tabs" */ "../views/Tabs.vue")
            }, 
            {
                path: '/my',
                name: 'my',
                meta: {
                    title: '个人中心'
                },
                component: () => import (/* webpackChunkName: "user" */ '../views/User.vue')
            }, 
            {
                path: '/bus',
                name: 'bus',
                meta: {
                    title: '车辆管理'
                },
                component: () => import (/* webpackChunkName: "editor" */ '../views/admin/BusTable.vue')
            },
            {
                path: '/campus',
                name: 'campus',
                meta: {
                    title: '校区管理'
                },
                component: () => import (/* webpackChunkName: "editor" */ '../views/admin/CampusTable.vue')
            },
            {
                path: '/order',
                name: 'order',
                meta: {
                    title: '订单管理'
                },
                component: () => import (/* webpackChunkName: "editor" */ '../views/admin/OrderTable.vue')
            },
            {
                path: '/route',
                name: 'route',
                meta: {
                    title: '路线管理'
                },
                component: () => import (/* webpackChunkName: "editor" */ '../views/admin/RouteTable.vue')
            },
            {
                path: '/schedule',
                name: 'schedule',
                meta: {
                    title: '排班管理'
                },
                component: () => import (/* webpackChunkName: "editor" */ '../views/admin/ScheduleTable.vue')
            },
            {
                path: '/user',
                name: 'user',
                meta: {
                    title: '用户管理'
                },
                component: () => import (/* webpackChunkName: "editor" */ '../views/admin/UserTable.vue')
            },
        ]
    }, {
        path: "/login",
        name: "Login",
        meta: {
            title: '登录'
        },
        component: () => import ( /* webpackChunkName: "login" */ "../views/Login.vue")
    }
];

const router = createRouter({
    history: createWebHashHistory(),
    routes
});

router.beforeEach((to, from, next) => {
    document.title = `${to.meta.title} | campus-bus-system`;
    const role = localStorage.getItem('ms_username');
    if (!role && to.path !== '/login') {
        next('/login');
    } else if (to.meta.permission) {
        // 如果是管理员权限则可进入，这里只是简单的模拟管理员权限而已
        role === 'admin'
            ? next()
            : next('/403');
    } else {
        next();
    }
});

export default router;