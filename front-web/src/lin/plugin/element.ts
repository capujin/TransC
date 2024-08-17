import { type App } from 'vue';
import {
    ElButton,
    ElContainer,
    ElAside,
    ElMenu,
    ElSubMenu,
    ElMenuItemGroup,
    ElMenuItem,
    ElForm,
    ElFormItem,
    ElInput,
    ElCheckbox,
    ElPopover,
    ElTag,
    ElCard,
    ElTable,
    ElTableColumn,
    ElPopconfirm,
    ElUpload,
    ElDialog,
    ElPagination,
    ElCascader,
    ElRadioGroup,
    ElRadio,
    ElSelect,
    ElOption,
    ElMessage,
    ElLoading 
} from 'element-plus'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
export default (app: App) => {
    app.use(ElButton)
        .use(ElForm)
        .use(ElFormItem)
        .use(ElInput)
        .use(ElTable)
        .use(ElTableColumn)
        .use(ElDialog)
        .use(ElPagination)
        .use(ElCascader)
        .use(ElSelect)
        .use(ElLoading);
    for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
        app.component(key, component)
    }
    app.config.globalProperties.$message = {
        success: (msg: string) => {
            ElMessage({
                message: msg + '',
                type: 'success',
                plain: true,
            });
        },
        error: (msg: string) => {
            ElMessage({
                message: msg + '',
                type: 'error',
                plain: true,
            });
        },
        warning: (msg: string) => {
            ElMessage({
                message: msg + '',
                type: 'warning',
                plain: true,
            });
        },
        info: (msg: string) => {
            ElMessage({
                message: msg + '',
                type: 'info',
                plain: true,
            });
        }
    };
}