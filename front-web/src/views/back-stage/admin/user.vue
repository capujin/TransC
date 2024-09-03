<template>
  <div class="container">
    <div class="head"></div>
    <transc-table
      class="table"
      :data="user.all"
    ></transc-table>
    <el-pagination
      v-model:current-page="currentPage"
      v-model:page-size="pageSize"
      :page-sizes="[100, 200, 300, 400]"
      layout="total, sizes, prev, pager, next, jumper"
      :total="400"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
    />
  </div>
</template>

<script setup lang="ts" name="UserManage">
import transcTable from '@/components/base/transc-table.vue';
import { ref, onMounted, type Ref, reactive } from 'vue';
import { type User } from '@/lin/interface/type'

const currentPage = ref(4);
const pageSize = ref(100);
const user:{all:User[] | []} = reactive({all:[]});
const handleSizeChange = (val: number) => {
  console.log(`${val} items per page`)
}
const handleCurrentChange = (val: number) => {
  console.log(`current page: ${val}`)
}
onMounted(() => {
  user.all = [
    {
      id:'1',
      name:'asdsa',
      create_time:'2020-10-10',
      status:'ACTIVE'
    }
  ]
});
</script>

<style lang="scss" scoped>
.container {
  .head {
    background-color: greenyellow;
    height: 50px;
  }
  .table {
    height: calc(100vh - 160px);
  }

  .el-pagination {
    height: 50px;
  }
}
</style>
