<template>
  <div class="container">
    <div class="title">1</div>
    <div class="quick-in">2</div>
    <div class="more">
      <el-badge :value="12" :offset="[10, 5]">
        <Bell style="width: 23px;cursor: pointer; vertical-align: middle;color: #454545;" />
      </el-badge>
    </div>
    <div class="avatar">
      <el-popover :width="280" :teleported="false"
        popper-style="box-shadow: rgb(14 18 22 / 35%) 0px 10px 38px -10px, rgb(14 18 22 / 20%) 0px 10px 20px -15px; padding: 20px;" >
        <template #reference>
          <el-avatar :size="35" :src="circleUrl" />
        </template>
        <template #default>
          <div class="top"></div>
          <div class="bottom">
            <span>个人中心</span>
            <span style="color: darkred;">退出登录</span>
          </div>
        </template>
      </el-popover>

    </div>
  </div>
</template>

<script setup lang="ts" name="headLayout">
import { useUserStore } from '@/stores';
import { ref,onMounted,type Ref } from 'vue';

const user = useUserStore();

const logined:Ref<boolean> = ref(false);
const circleUrl = 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png'

onMounted(()=>{
  logined.value = user.logined;
})
</script>

<style lang="scss" scoped>
::v-deep .el-popper{
  padding: 0 !important;
  .el-popper__arrow:before{
    background-color: skyblue;
  }
}
.container {
  align-items: center;
  display: flex;
  width: 100%;
  height: 50px;
  background-color: #a4ffe1;
  justify-content: space-between;

  .title {
    width: 200px;
  }
  
  .more{
    margin-left: auto;
    margin-right: 100px;
  }

  .avatar {
    width: 60px;

    .el-avatar {
      position: relative;
      cursor: pointer;
      vertical-align: middle;
    }

    .el-avatar:hover {
      box-shadow: 1px 1px 6px 0px rgba(0, 0, 0, 0.207);
    }
    
    .top{
      width: 100%;
      height: 160px;
      background:linear-gradient(to bottom,skyblue,transparent);
    }

    .bottom{
      padding: 0 10px;
      display: flex;
      justify-content: center;
      align-items: center;
      span{
        cursor: pointer;
        text-align: center;
        line-height: 32px;
        width: 50%;
        height: 32px;
        &:hover{
          color: darkblue;
        }
      }
    }
  }
}
</style>
