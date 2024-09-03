<template>
  <div class="back-main">
    <base-head></base-head>
    <main>
      <base-aside></base-aside>
      <article>
        <router-view v-slot="{ Component }">
          <transition name="back-fade" mode="out-in">
            <keep-alive>
              <component :is="Component" />
            </keep-alive>
          </transition>
        </router-view>
      </article>
    </main>
  </div>
</template>

<script setup lang="ts" name="BackHomeIndex">
import baseHead from '@/components/layout/base-head.vue';
import baseAside from '@/components/layout/base-aside.vue';
import 'element-plus/theme-chalk/display.css';
console.log("后台页面加载加载加载");

</script>

<style lang="scss" scoped>
.back-main {
  display: grid;
  width: 100%;
  height: 100%;
}

main {
  width: 100%;
  display: flex;

  article{
    position: relative;
    overflow: hidden;
    border-radius: 3px;
    margin: 5px;
    flex: 1;
    box-shadow: 1px 1px 3px 0px #0000000d;
  }
}

.back-fade-enter-active {
  position: absolute;
  width: 100%;
  transition: all .3s ease-out;
}

.back-fade-leave-active {
  position: absolute;
  width: 100%;
  transition: all .3s cubic-bezier(1, 0.5, 0.8, 1);
}

.back-fade-enter-from {
  transform: translateX(-5px);
  opacity: 0;
}

.back-fade-leave-to {
  transform: translateX(5px);
  opacity: .5;
}
</style>