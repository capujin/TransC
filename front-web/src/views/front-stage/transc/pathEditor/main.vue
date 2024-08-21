<template>
    <div class="container">
        <!--  style="width: 100vw;height: 100vh;" -->
        <svg id="svgEl" ref="svgEl" @mousedown="mouseDownForDrag($event)"style="width: 100vw;height: 100vh;" :viewBox="`0 0 ${svgParam.width+scaling} ${svgParam.height+scaling}`">
            <!-- <rect width="100" height="100" x="0" fill="#008d46" /> -->
            <line x1="-146.379" y1="0" x2="174.72100000000003" y2="0" stroke-width="1.6006978575778446" style=""></line>
            <line x1="0" y1="-137.236" x2="0" y2="145.464" stroke-width="1.6006978575778446" style=""></line>
            <line v-for="i in svgParam.num_x" x1="0" :y1="i*10" :x2="svgParam.width" :y2="i*10" stroke="#ccc" stroke-width="0.1px"/>
            <line v-for="i in svgParam.num_y" :x1="i*10" :y1="0" :x2="i*10" :y2="svgParam.height" stroke="#ccc" stroke-width="0.1px"/>
        </svg>
        <div class="menu-bar-x">
            <div class="bar-item mobile-menu" @click="fold = !fold">
                <svg viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                    <path
                        d="M3.297 5.234a2.6 2.6 0 0 1 1.937-1.937 5.54 5.54 0 0 1 2.532 0 2.6 2.6 0 0 1 1.937 1.937c.195.833.195 1.7 0 2.532a2.6 2.6 0 0 1-1.937 1.937c-.833.195-1.7.195-2.532 0a2.6 2.6 0 0 1-1.937-1.937 5.55 5.55 0 0 1 0-2.532Z"
                        stroke="#dbddde" stroke-width="1.5" />
                    <path
                        d="M3.297 16.234a2.6 2.6 0 0 1 1.937-1.937 5.55 5.55 0 0 1 2.532 0 2.6 2.6 0 0 1 1.937 1.937c.195.833.195 1.7 0 2.532a2.6 2.6 0 0 1-1.937 1.937c-.833.195-1.7.195-2.532 0a2.6 2.6 0 0 1-1.937-1.937 5.55 5.55 0 0 1 0-2.532Z"
                        stroke="#0095FF" stroke-width="1.5" />
                    <path
                        d="M14.297 5.234a2.6 2.6 0 0 1 1.937-1.937 5.54 5.54 0 0 1 2.532 0 2.6 2.6 0 0 1 1.937 1.937c.195.833.195 1.7 0 2.532a2.6 2.6 0 0 1-1.937 1.937c-.833.195-1.7.195-2.532 0a2.6 2.6 0 0 1-1.937-1.937 5.55 5.55 0 0 1 0-2.532Zm0 11a2.6 2.6 0 0 1 1.937-1.937 5.55 5.55 0 0 1 2.532 0 2.6 2.6 0 0 1 1.937 1.937c.195.833.195 1.7 0 2.532a2.6 2.6 0 0 1-1.937 1.937c-.833.195-1.7.195-2.532 0a2.6 2.6 0 0 1-1.937-1.937 5.55 5.55 0 0 1 0-2.532Z"
                        stroke="#dbddde" stroke-width="1.5" />
                </svg>
            </div>
            <div class="bar-item">
                <div class="label">文件</div>
                <div class="more">
                    <div class="label">文件1</div>
                    <div class="label">文件2</div>
                </div>
            </div>
        </div>
        <div ref="resizePannelEl" class="menu-pannel-x">
            <div :class="{ 'collapse-btn': true, 'fold': fold }" @click="fold = !fold">
                <svg>
                    <path d="M 0 5 L 4 10 L 4 8 L 1 5 L 4 2 L 4 0" fill="#fff"></path>
                </svg>
            </div>
            <div ref="resizeBarEl" @mousedown="mouseDowForResize($event)" @dblclick="resetSize"
                class="resize-controler"></div>
        </div>
    </div>
</template>

<script setup lang="ts" name="PathEditor">
import { ref, onMounted, onBeforeUnmount, watch, reactive } from 'vue'
import { usePathEditorStore } from '@/stores/path-editor'

let svgEl = ref<SVGSVGElement | null>(null);
let resizeBarEl = ref<HTMLElement | null>(null);
let resizePannelEl = ref<HTMLElement | null>(null);

const currentWidth = ref<Number>(0);
// 缩放比例
const scaling = ref<number>(0);

const svgParam = reactive<{
    width: number,
    height: number,
    num_x:number,
    num_y:number,
    view: {
        x: number,
        y: number,
        width: number,
        height: number
    }
}>({
    width: 100,
    height: 100,
    num_x:Math.floor(window.innerHeight/10),
    num_y:Math.floor(window.innerWidth/10),
    view: {
        x: 0,
        y: 0,
        width: 250,
        height: 300
    }
})
const handleResize = () =>{
    svgParam.width = window.innerWidth;
    svgParam.height = window.innerHeight;
}
const fold = ref(false);
watch(fold, (newV) => {
    if (newV) {
        if (resizePannelEl.value) {
            resizePannelEl.value.style.width = `${0}px`;
            resizePannelEl.value.classList.add('fold');
        }
    } else {
        if (resizePannelEl.value) {
            resizePannelEl.value.style.width = `${currentWidth.value}px`;
            resizePannelEl.value.classList.remove('fold');
        }
    }
})
const store = usePathEditorStore();

const resetSize = () => {
    let width = store.defaultWidth;
    if (resizePannelEl.value) {
        resizePannelEl.value.style.width = `${width ? width : 250}px`;
    }
}

const mouseDowForResize = (e: MouseEvent) => {
    document.body.style.cursor = 'e-resize';
    document.body.style.userSelect = 'none';
    // 获取鼠标位置
    const mouseX = e.clientX;
    const mouseY = e.clientY;
    // 可以在这里添加其他逻辑，比如开始监听 mousemove 事件
    document.addEventListener('mousemove', moveForResize);
    document.addEventListener('mouseup', mouseUp);
}
const moveForResize = (e: MouseEvent) => {
    // 在鼠标移动时获取实时位置
    if (resizePannelEl.value) {
        const mouseX = e.clientX;
        const minWidth = 200;
        const maxWidth = window.innerWidth - 300;
        if (mouseX <= 30) {
            console.log("折叠");
            fold.value = true;
        }
        if (mouseX >= minWidth && mouseX <= maxWidth) {
            fold.value = false;
            resizePannelEl.value.style.width = `${mouseX}px`;
        }
    }
}

const mouseDownForDrag = (e: MouseEvent) => {
    // 获取鼠标位置
    const mouseX = e.clientX;
    const mouseY = e.clientY;
    // 可以在这里添加其他逻辑，比如开始监听 mousemove 事件
    document.addEventListener('mousemove', moveForDrag);
    document.addEventListener('mouseup', mouseUp);
}
const moveForDrag = (e: MouseEvent) => {
    document.body.style.cursor = 'grabbing';
    // 在鼠标移动时获取实时位置
    // const mouseX = e.clientX;
    // const mouseY = e.clientY;
}

const mouseUp = () => {
    document.body.style.cursor = 'auto';
    document.body.style.userSelect = 'auto';

    // store.defaultWidth = resizePannelEl.value? resizePannelEl.value.offsetWidth:store.defaultWidth;
    // 释放鼠标时移除监听
    document.removeEventListener('mousemove', moveForResize);
    document.removeEventListener('mousemove', moveForDrag);
    document.removeEventListener("keydown", handleKeyDrag)
    document.removeEventListener("keyup", handleKeyDrag)
    document.removeEventListener('mouseup', mouseUp);
}

const handleKeyDrag = (e: KeyboardEvent) => {
    e.preventDefault();
    if (e.code === 'Space') {
        document.body.style.cursor = 'grab';
        console.log("触发");
        
        // document.addEventListener('mousemove', moving);
        // document.addEventListener('mouseup', mouseUp);
    }
    // if((e.code === 'ControlLeft' || e.code === 'ControlRight') && !e.repeat){
    //     console.log("触发");
    // }
    if (e.type === 'keyup') {
        console.log("键盘溢出");
        mouseUp()
    }
}
// const handleKeyWheel = (e: KeyboardEvent) => {
//     e.preventDefault();
//     console.log(e);
    
//     // if (e.code === 'Space' && !e.repeat) {
//     //     document.body.style.cursor = 'grab';
//     //     document.addEventListener('mousemove', moving);
//     //     // document.addEventListener('mouseup', mouseUp);
//     // }
//     if (e.type === 'keyup') {
//         mouseUp()
//     }
// }
const handleZoom = (e:WheelEvent) =>{
    // console.log(e);
    if(e.deltaY<0){
        console.log("放大");
        scaling.value -=20
    }else{
        console.log("缩小");
        scaling.value +=20
    }
    
}
onMounted(() => {
    // svgEl.value?.style.width = 100 +'vw';
    // svgEl.value?.height = 100 +'vh';
    currentWidth.value = store.defaultWidth;
    document.addEventListener("keydown", handleKeyDrag);
    document.addEventListener("keyup", handleKeyDrag);
    window.addEventListener("resize",handleResize);
    document.addEventListener("wheel", handleZoom);
    // document.addEventListener("keydown", handleKeyWheel)
    // document.addEventListener("keyup", handleKeyWheel)
});

onBeforeUnmount(() => {
    document.removeEventListener("keydown", handleKeyDrag);
    document.removeEventListener("keyup", handleKeyDrag);
    window.removeEventListener("resize",handleResize);
    document.addEventListener("wheel", handleZoom);
})
</script>

<style lang="scss" scoped>
.container {
    display: flex;
    flex-direction: column;
    font-size: 14px;
    background-color: #000000;
    position: relative;

    #svgEl {
        z-index: 1;
        inset: 0;
        position: absolute;
    }

    .menu-bar-x {
        z-index: 5;
        display: flex;
        align-items: center;
        color: #dbddde;
        padding: 0 5px;
        height: 28px;
        background-color: #24292E;
        border-bottom: 1px solid #1B1F23;

        .bar-item {
            background-color: inherit;
            position: relative;
            width: 60px;
            height: 100%;
            display: flex;
            justify-content: center;
            align-items: center;
            transition: 0.1s;

            svg {
                width: 70%;
                height: 70%;
                object-fit: cover;
            }

            &.mobile-menu {
                display: none;
            }

            .label {
                font-size: 12px;
                padding: 5px 10px;
                // display: flex;
                // justify-content: center;
                // align-items: center;
            }

            &:hover {
                cursor: pointer;
                background-color: #35393D;
            }

            &:hover .more {
                display: flex;
                opacity: 1;
            }

            .more {
                display: none;
                opacity: 0;
                flex-direction: column;
                align-items: center;
                background-color: inherit;
                position: absolute;
                top: 100%;
                width: 60px;
                transition: .1s;
            }
        }
    }

    .menu-pannel-x {
        z-index: 4;
        position: relative;
        color: #dbddde;
        box-sizing: border-box;
        padding: 10px 5px;
        width: min(100vw, 250px);
        height: calc(100vh - 29px);
        background-color: #1F2428;

        &.fold {
            padding: 0;
        }
        &:hover .collapse-btn{
            display: flex;
        }

        .collapse-btn {
            cursor: pointer;
            display: none;
            justify-content: center;
            align-items: center;
            user-select: none;
            position: absolute;
            width: 8px;
            height: 60px;
            border-radius: 0 10px 10px 0;
            background-color: inherit;
            right: -8px;
            z-index: 1;
            top: 50%;
            transform: translateY(-50%);
            transition: .1s;

            &:hover {
                background-color: #35393D;
            }

            &.fold {
                svg {
                    transform: rotate(180deg);
                    margin-right: 2px;
                }
            }

            svg {
                margin-right: -2px;
                width: 5px;
                height: 10px;
            }
        }

        &:has(.resize-controler:active) {
            background-color: #303539;
        }

        .resize-controler {
            position: absolute;
            width: 1px;
            height: 100%;
            right: -1px;
            top: 0;
            background-color: inherit;

            &:hover {
                z-index: 2;
                background-color: #005CC5;
                cursor: e-resize;
                transform: scaleX(2.5);
            }

        }
    }

}

/* 针对屏幕宽度小于 480px 的设备应用样式 */
@media (max-width: 480px) {
    .container {
        width: 100%;
        height: auto;
    }

    .mobile-menu {
        display: flex !important;
    }

    .collapse-btn {
        display: none !important;
    }

    /* 其他样式调整 */
    .some-element {
        font-size: 14px;
        /* 调整字体大小 */
    }
}
</style>