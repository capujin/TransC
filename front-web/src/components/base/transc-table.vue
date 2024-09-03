<template>
  <el-table :data="props.data" style="width: 100%" :height="tableHeight">
    <el-table-column prop="id" label="id" width="80"></el-table-column>
    <el-table-column label="名字" width="180">
      <template #default="scope">
        <div style="display: flex; align-items: center">
          <span style="margin-left: 10px">{{ scope.row.name }}</span>
        </div>
      </template>
    </el-table-column>
    <el-table-column label="创建时间" width="180">
      <template #default="scope">
        <el-popover effect="light" trigger="hover" placement="top" width="auto">
          <template #default>
            <div>address: {{ scope.row.create_time }}</div>
          </template>
          <template #reference>
            <el-tag>{{ scope.row.create_time }}</el-tag>
          </template>
        </el-popover>
      </template>
    </el-table-column>
    <el-table-column label="操作">
      <template #default="scope">
        <el-button size="small" @click="handleEdit(scope.$index, scope.row)"> Edit </el-button>
        <el-button size="small" type="danger" @click="handleDelete(scope.$index, scope.row)">
          Delete
        </el-button>
      </template>
    </el-table-column>
  </el-table>
</template>

<script setup lang="ts" name="quickTable">
import { Timer } from '@element-plus/icons-vue'
import { type User } from '@/lin/interface/type'
import { ref, onMounted, type Ref } from 'vue'

const props = defineProps(['data'])

const tableHeight: Ref<number> = ref(300)

const handleEdit = (index: number, row: User) => {
  console.log(index, row)
}
const handleDelete = (index: number, row: User) => {
  console.log(index, row)
}

// const tableData: User[] =

onMounted(() => {
  tableHeight.value = window.innerHeight - 160
})
</script>

<style lang="scss" scoped></style>
