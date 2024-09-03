<template>
    <el-menu default-active="1-1" @open="handleOpen" @close="handleClose">
        <template v-for="menu in menuList" :key="menu.order">
            <el-menu-item v-if="!menu.children" :index="'' + menu.order" @click="open(menu.route)">
                <span>{{ menu.title }}</span>
            </el-menu-item>
            <el-sub-menu v-else :index="'' + menu.order">
                <template #title>
                    <span>{{ menu.title }}</span>
                </template>
                <el-menu-item v-for="(menu_item, index) in menu.children" :key="menu_item.order"
                    :index="'' + menu.order + index" @click="open(menu_item.route)">
                    <template #title>{{ menu_item.title }}</template>
                </el-menu-item>
            </el-sub-menu>
        </template>
    </el-menu>
</template>

<script setup lang="ts" name="asideLayout">
import { ref, onMounted, type Ref } from 'vue'
import { useMenuStore } from '@/stores/menu';
import homeRouter from '@/config/back_stage/index'
import { useRoute,useRouter } from 'vue-router'
console.log(homeRouter);

// 菜单
const menuList = homeRouter;
const system = useMenuStore();
// 路由
const router = useRouter();
const location = useRoute();

const handleOpen = (key: string, keyPath: string[]) => {
    console.log(key, keyPath)
}
const handleClose = (key: string, keyPath: string[]) => {
    console.log(key, keyPath)
}

const open = (route: string | undefined) => {
    if (!route) {
        route = location.path;
        return;
    }
    router.push({ path: `${system.route}${route}`});
}
onMounted(() => {

});
</script>

<style lang="scss" scoped>
.el-menu {
    height: calc(100vh - 50px);
    width: min(200px, 100%);
}
</style>