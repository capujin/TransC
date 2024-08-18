<template>
    <div class="container">
        <div class="form-x">
            <div class="left"></div>
            <div class="content" v-loading="loading" element-loading-background="rgba(255, 255, 255, 0.2)">
                <h1 class="title">
                    <span>Trans</span>
                    <svg width="288" height="288" viewBox="-16 0 288 288" xmlns="http://www.w3.org/2000/svg"
                        preserveAspectRatio="xMidYMid">
                        <path
                            d="M255.987 85.672c-.002-4.843-1.037-9.122-3.129-12.794-2.055-3.612-5.134-6.638-9.262-9.032-34.081-19.67-68.195-39.28-102.264-58.97-9.185-5.307-18.091-5.114-27.208.27-13.565 8.008-81.481 46.956-101.719 58.689C4.071 68.665.015 76.056.013 85.663 0 125.221.013 164.777 0 204.336c.002 4.736.993 8.932 2.993 12.55 2.056 3.72 5.177 6.83 9.401 9.278 20.239 11.733 88.164 50.678 101.726 58.688 9.121 5.387 18.027 5.579 27.215.27 34.07-19.691 68.186-39.3 102.272-58.97 4.224-2.447 7.345-5.559 9.401-9.276 1.997-3.618 2.99-7.814 2.992-12.551 0 0 0-79.094-.013-118.653"
                            fill="#A9B9CB" />
                        <path
                            d="M141.101 5.134c-9.17-5.294-18.061-5.101-27.163.269C100.395 13.39 32.59 52.237 12.385 63.94 4.064 68.757.015 76.129.013 85.711 0 125.166.013 164.62 0 204.076c.002 4.724.991 8.909 2.988 12.517 2.053 3.711 5.169 6.813 9.386 9.254a9009 9009 0 0 0 20.159 11.62L219.625 50.375c-26.178-15.074-52.363-30.136-78.524-45.241"
                            fill="#7F8B99" />
                        <path
                            d="m154.456 126.968 39.839.281c0-16.599-16.802-57.249-64.973-57.249-30.691 0-71.951 19.512-71.951 75.61S97.818 220 129.322 220c51.017 0 63.21-35.302 63.21-55.252l-38.007-2.173s1.017 23.075-25.406 23.075c-24.39 0-28.46-29.878-28.46-40.04 0-15.447 5.493-40.244 28.46-40.244 22.968 0 25.337 21.602 25.337 21.602"
                            fill="#FFF" />
                    </svg>
                </h1>
                <el-form ref="loginForm" :model="form" label-width="auto" label-position="top">
                    <el-form-item label="账号：" prop="username"
                        :rules="[{ required: true, message: '请输入账户名', trigger: 'submit' }]">
                        <el-input ref="autoFocus" v-model="form.username" autocomplete="off" />
                    </el-form-item>
                    <el-form-item label="密码：" prop="password"
                        :rules="[{ required: true, message: '密码不能为空', trigger: 'submit' }]">
                        <el-input v-model="form.password" type="password" autocomplete="off" show-password
                            @keyup.enter="submitForm(loginForm)" />
                    </el-form-item>
                    <!-- <el-form-item>
                        <el-button @click="resetForm(loginForm)">Reset</el-button>
                    </el-form-item> -->
                </el-form>
                <div class="options">
                    <a href="javascript:;" class="button button--piyo" @click="submitForm(loginForm)">
                        <div class="button__wrapper">
                            <span class="button__text">登录</span>
                        </div>
                        <div class="characterBox">
                            <div class="character wakeup">
                                <div class="character__face"></div>
                            </div>
                            <div class="character wakeup">
                                <div class="character__face"></div>
                            </div>
                            <div class="character">
                                <div class="character__face"></div>
                            </div>
                        </div>
                    </a>
                    <!-- <el-button type="primary" @click="submitForm(loginForm)">
                        登录
                    </el-button> -->
                </div>
            </div>
        </div>
    </div>
</template>

<script name="Login" lang="ts" setup>
// import { Base64 } from 'js-base64'
import { type Response } from '@/lin/interface/type'
import Util from '@/lin/util/utils'
import User from '@/lin/model/admin'
import { proxy } from '@/main'
import { saveTokens } from '@/lin/util/token'
import { ref, reactive, onMounted } from 'vue';
import type { FormInstance, InputInstance } from 'element-plus'
import { useUserStore } from '@/stores'
const store = useUserStore();
const loading = ref(false);
const loginForm = ref<FormInstance>();
const autoFocus = ref<InputInstance>()
const form = reactive({
    username: '',
    password: ''
})
// async function login({ username, password }: { username: string, password: string }) {
//     loading.value = true;
//     const data = {
//         username,
//         password
//         // password: Base64.encode(password)
//     }
//     const tokens = await User.getToken(data) as Response;
//     loading.value = false;
//     if (tokens.code === 0) {
//         saveTokens(tokens.data.token)
//     } else {
//         proxy.$message.error(tokens.message)
//     }
// }
const submitForm = Util.debounce((formEl: FormInstance | undefined) => {
    if (!formEl) return
    console.log("存储：", store.logined);
    let { username, password } = { ...form };
    let data = {
        username,
        password    //:Base64.encode(password)

    }
    loading.value = true;
    formEl.validate(async (valid) => {
        if (valid) {
            const tokens: Response = await User.getToken(data);
            if (tokens.code === 0) {
                saveTokens(tokens.data.token);
            } else {
                proxy.$message.error(tokens.message);
            }
            console.log(data);
            return;
        }
    })
    loading.value = false;
}, 500)
async function getInformation() {
    try {
        // 尝试获取当前用户信息
        const authsRes = await User.getPermissions()  // 获取用户权限列表
        // const user = await User.getInformation()  // 获取用户信息
        // this.setUserAndState(user.data)
        // this.setUserPermissions(authsRes.data.permissions)
        // this.setUserMenus(authsRes.data.menus)
    } catch (e) {
        console.log(e)
    }
};
const resetForm = (formEl: FormInstance | undefined) => {
    if (!formEl) return
    formEl.resetFields()
}

onMounted(() => {
    autoFocus.value?.focus();
})

</script>

<style lang="scss" scoped>
.container {
    width: 100vw;
    height: 100vh;
    min-width: 800px;
    position: relative;
    margin: 0 auto;
    background-color: skyblue;
    background: url('@/assets/images/ScissorSeven-bg.jpg') center center;
    background-size: cover;
    overflow: hidden;

    .form-x {
        color: white;
        width: 800px;
        height: 500px;
        position: absolute;
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%);
        backdrop-filter: blur(3px);
        background-color: rgba(0, 0, 0, 0.2);
        border-radius: 5px;
        display: flex;
        overflow: hidden;
        box-shadow: 0px 0px 20px 20px rgba(255, 255, 255, 0.1);

        .left {
            width: 35%;
            height: 100%;
            background-color: white;
            background: url('@/assets/images/white-flag.jpg') center center;
            background-size: auto 170%;
            backdrop-filter: blur(1px);
            box-shadow: 1px 0px 9px 0px #00000021;
        }

        .content {
            box-sizing: border-box;
            width: 65%;
            height: 100%;
            padding: 0 100px;

            .title {
                user-select: none;
                font-family: 'Lucida Sans', Arial, sans-serif;
                height: 150px;
                font-size: 3rem;
                margin: 0;
                display: flex;
                justify-content: center;
                align-items: center;

                svg {
                    display: inline-block;
                    width: 60px;
                    height: 60px;
                }
            }

            .options {
                height: 150px;
                display: flex;
                justify-content: center;
                align-items: center;

                .button--piyo {
                    // --main_color: #f4cf47;
                    --main_color: #71E0F7;
                    --sub_color1: #f4e19c;
                    // --sub_color2: #ff8108;
                    --sub_color2: #EDD05B;
                    --base_color: #000;
                    --border_radius1: 60px 60px 40px 40px / 48px 48px 30px 30px;
                    --border_radius2: 70px 70px 40px 40px / 48px 48px 30px 30px;
                    --border_radius3: 40px 40px 40px 40px / 48px 48px 30px 30px;
                }

                .button {
                    position: relative;
                    display: flex;
                    justify-content: center;
                    align-items: center;
                    width: 250px;
                    height: 40px;
                    box-sizing: border-box;
                    text-decoration: none;
                    border: solid 3px #000;
                    border-radius: 20px;
                    background: var(--main_color);
                    font-family: "Microsoft YaHei", "微软雅黑", Arial, sans-serif;
                }

                .button::before {
                    content: '';
                    position: absolute;
                    z-index: 2;
                    top: 0;
                    right: 20px;
                    bottom: 0;
                    margin: auto 0;
                    width: 24px;
                    height: 24px;
                    background: var(--base_color);
                    transition: all ease .2s;
                }

                .button__wrapper {
                    display: flex;
                    justify-content: center;
                    align-items: center;
                    position: relative;
                    z-index: 1;
                    width: 100%;
                    height: 100%;
                    border-radius: 40px;
                    overflow: hidden;
                }

                .button__wrapper::before,
                .button__wrapper::after {
                    transition: all .5s ease;
                }

                .characterBox {
                    position: absolute;
                    top: -54px;
                    left: 0;
                    right: 0;
                    margin: 0 auto;
                    display: flex;
                    justify-content: space-between;
                    align-items: flex-end;
                    width: 170px;
                    height: 54px;
                }

                .button__text {
                    position: relative;
                    z-index: 3;
                    font-size: 18px;
                    letter-spacing: 4px;
                    color: var(--base_color);
                    transition: all .3s ease;
                }

                .character {
                    position: relative;
                    width: 56px;
                    height: 36px;
                    box-sizing: border-box;
                    border: solid 3px #000;
                    background: var(--main_color);
                    border-radius: var(--border_radius1);
                    animation: sleep 1s ease infinite alternate;
                }

                .character::before {
                    content: '';
                    position: absolute;
                    top: -12px;
                    left: 18px;
                    width: 20px;
                    height: 12px;
                    background: #000;
                    clip-path: path('M 17.23 4.32 C 13.69 4.95 11.51 6.83 10 9 C 9.263 6.43 10.047 5.679 10.732 3.688 C 10.863 3.035 10.928 2.186 10.634 1.631 C 10.24 0.81 9.1 1.239 8.708 1.631 S 6.717 5.026 5.938 8.061 Q 3.942 2.839 2.554 1.569 A 1 1 0 0 0 0.692 2.466 Q 2 5 5 13 Q 4 13 7 13 Q 8.32 12.995 8 13 C 8 13 8 13 8 13 C 8 13 8 13 8 13 C 8 13 8 13 8 13 C 10 13 10.49 13.04 10.52 13 C 10.62 12.86 12.06 8.18 17.11 7.29 C 17.93 7.15 18.48 6.37 18.33 5.55 S 17.39 4.19 16.58 4.34 Z');
                }

                .character__face {
                    position: absolute;
                    z-index: 2;
                    top: 15px;
                    left: 0;
                    right: 0;
                    margin: 0 auto;
                    width: 12px;
                    height: 6px;
                    background: var(--sub_color2);
                    border-radius: 50% 50% 50% 50% / 78% 78% 22% 22%;
                    transition: .2s;
                }

                .character__face::before,
                .character__face::after {
                    content: '';
                    position: absolute;
                    top: -4px;
                    width: 8px;
                    height: 2px;
                    border-radius: 4px;
                    background: #000;
                }

                .character__face::before {
                    left: -5px;
                }

                .character__face::after {
                    right: -5px;
                }

                .button--piyo::before {
                    clip-path: path('M 24 9.7362 c 0 -0.8829 -0.75 -1.3851 -0.81 -1.4337 L 11.17 0.3645 c -0.91 -0.5994 -2.21 -0.4536 -2.91 0.3402 c -0.69 0.7857 -0.52 1.9197 0.39 2.5191 l 7.12 4.7061 l -13.7 -0.0162 h 0 C 0.93 7.9137 0 8.7156 0 9.7119 c 0 0.9963 0.93 1.7982 2.07 1.7982 l 13.7 0.0162 l -7.13 4.6818 c -0.91 0.5994 -1.09 1.7253 -0.4 2.5191 c 0.41 0.4698 1.03 0.7128 1.65 0.7128 c 0.44 0 0.88 -0.1215 1.25 -0.3645 l 12.04 -7.9056 c 0.07 -0.0486 0.82 -0.5427 0.82 -1.4337 Z');
                }

                .button--piyo .button__wrapper::before,
                .button--piyo .button__wrapper::after {
                    content: '';
                    position: absolute;
                    bottom: 0;
                    width: 130px;
                    height: 38px;
                    background: var(--sub_color1);
                    clip-path: path('M13.77,37.35L.25,16.6c-.87-1.33,.69-2.91,2-2.02l12.67,8.59c.81,.55,1.91,.14,2.18-.81l2.62-9.33c.39-1.4,2.34-1.42,2.76-.02l3.6,11.99c.33,1.11,1.74,1.4,2.47,.52L49.38,.52c.87-1.04,2.53-.42,2.53,.95V23.7c0,1.13,1.2,1.83,2.16,1.26l12.75-7.51c.85-.5,1.94,0,2.13,.98l1.5,7.6c.2,1.03,1.37,1.51,2.22,.92l17.74-12.3c1.09-.75,2.52,.25,2.21,1.55l-2.44,10.2c-.26,1.09,.74,2.06,1.8,1.75l30.8-9.04c1.37-.4,2.42,1.26,1.49,2.36l-9.07,10.66c-.83,.98-.1,2.49,1.17,2.42l12.12-.68c1.6-.09,2.12,2.15,.65,2.8l-2.73,1.21c-.18,.08-.38,.12-.58,.12H14.97c-.48,0-.93-.25-1.2-.65Z');
                }

                .button--piyo .button__wrapper::before {
                    left: 0;
                }

                .button--piyo .button__wrapper::after {
                    right: 0;
                    transform: rotateY(180deg);
                }

                .button:hover .button__wrapper::before {
                    transform: translateX(-12px);
                }

                .button:hover .button__wrapper::after {
                    transform: rotateY(180deg) translateX(-12px);
                }

                .button:hover .button__text {
                    letter-spacing: 6px;
                }

                .button:hover::before {
                    right: 14px;
                }

                .button:hover .wakeup {
                    animation: wakeup .2s ease;
                    animation-fill-mode: forwards;
                }

                .button:hover .wakeup .character__face {
                    top: 20px;
                }

                .button:hover .wakeup .character__face::before,
                .button:hover .wakeup .character__face::after {
                    animation: eye 5s linear infinite;
                }

                .button:hover .wakeup:nth-child(2) .character__face::before,
                .button:hover .wakeup:nth-child(2) .character__face::after {
                    animation: eye_2 5s linear infinite;
                }

                @keyframes sleep {
                    0% {
                        height: 36px;
                        border-radius: var(--border_radius1);
                    }

                    100% {
                        height: 32px;
                        border-radius: var(--border_radius2);
                    }
                }

                @keyframes wakeup {
                    0% {
                        height: 32px;
                        border-radius: var(--border_radius2);
                    }

                    100% {
                        height: 56px;
                        border-radius: var(--border_radius3);
                    }
                }

                @keyframes eye {
                    0% {
                        top: -6px;
                        width: 6px;
                        height: 6px;
                    }

                    30% {
                        top: -6px;
                        width: 6px;
                        height: 6px;
                    }

                    32% {
                        top: -4px;
                        width: 8px;
                        height: 2px;
                    }

                    34% {
                        top: -6px;
                        width: 6px;
                        height: 6px;
                    }

                    70% {
                        top: -6px;
                        width: 6px;
                        height: 6px;
                    }

                    72% {
                        top: -4px;
                        width: 8px;
                        height: 2px;
                    }

                    74% {
                        top: -6px;
                        width: 6px;
                        height: 6px;
                    }

                    76% {
                        top: -4px;
                        width: 8px;
                        height: 2px;
                    }

                    78% {
                        top: -6px;
                        width: 6px;
                        height: 6px;
                    }

                    100% {
                        top: -6px;
                        width: 6px;
                        height: 6px;
                    }
                }

                @keyframes eye_2 {
                    0% {
                        top: -6px;
                        width: 6px;
                        height: 6px;
                    }

                    10% {
                        transform: translateX(0);
                    }

                    12% {
                        transform: translateX(3px);
                    }

                    20% {
                        top: -6px;
                        width: 6px;
                        height: 6px;
                    }

                    22% {
                        top: -4px;
                        width: 8px;
                        height: 2px;
                    }

                    24% {
                        top: -6px;
                        width: 6px;
                        height: 6px;
                    }

                    25% {
                        transform: translateX(3px);
                    }

                    27% {
                        transform: translateX(0);
                    }

                    74% {
                        top: -6px;
                        width: 6px;
                        height: 6px;
                        transform: translateX(0);
                    }

                    76% {
                        top: -4px;
                        width: 8px;
                        height: 2px;
                        transform: translateX(3px);
                    }

                    78% {
                        top: -6px;
                        width: 6px;
                        height: 6px;
                    }

                    80% {
                        top: -4px;
                        width: 8px;
                        height: 2px;
                    }

                    82% {
                        top: -6px;
                        width: 6px;
                        height: 6px;
                    }

                    85% {
                        transform: translateX(3px);
                    }

                    87% {
                        transform: translateX(0);
                    }

                    100% {
                        top: -6px;
                        width: 6px;
                        height: 6px;
                        transform: translateX(0);
                    }
                }

                @keyframes body_hoo {
                    0% {
                        bottom: 2px;
                    }

                    100% {
                        bottom: 0;
                    }
                }

                @keyframes body_hoo_wakeup {
                    0% {
                        bottom: 2px;
                    }

                    100% {
                        bottom: 6px;
                    }
                }

                @keyframes face_pen {
                    0% {
                        height: 14px;
                    }

                    100% {
                        height: 10px;
                    }
                }

                @keyframes face_pen_wakeup {
                    0% {
                        height: 14px;
                    }

                    100% {
                        height: 28px;
                    }
                }

            }
        }
    }

}

:deep(.el-form) {
    .el-form-item__label {
        font-size: inherit;
        color: rgb(255, 255, 255);
    }

    .el-input {
        height: 40px;

        .el-input__wrapper {
            background-color: rgba(255, 255, 255, 0);

            .el-input__inner {
                color: rgb(255, 255, 255);
            }
        }
    }
}
</style>