import { removeToken } from '@/lin/util/token';
import { defineStore } from 'pinia'
import { ref, reactive, type Ref, type Reactive } from 'vue'

export const usePathEditorStore = defineStore('pathEditor', () => {

    // 默认展开
    const expand: Ref<boolean> = ref(false);
    const defaultWidth: Ref<Number> = ref(250);

    return {
        // state
        expand,
        defaultWidth
        // mutation


        // action


    }
})