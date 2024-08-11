import axios, { type AxiosInstance, type AxiosRequestConfig } from 'axios';
import { getToken, saveTokens } from '@/lin/util/token'
import qs from 'qs';
import vue from '@/main';

class CustomError extends Error {
    source: string;
    constructor({ source, message }: { source: string, message: string }) {
        super(message);
        this.source = source;
        this.name = 'CustomError'; // 设置错误的名字
    }
}

// 定义 Axios 配置的类型
const config: AxiosRequestConfig = {
    baseURL: import.meta.env.VITE_APP_BASE_URL || '',
    timeout: 10 * 60 * 1000, // 请求超时时间设置
    validateStatus(status) {
        return status >= 200 && status < 510;
    },
};
// 创建 Axios 实例
const _axios: AxiosInstance = axios.create(config);
// 请求拦截器
_axios.interceptors.request.use(
    originConfig => {
        // 有 API 请求重新计时
        // Vue.prototype.$_lin_jump()

        const reqConfig = { ...originConfig }

        // step1: 容错处理
        if (!reqConfig.url) {
            console.error('请求需要url')
            throw new CustomError({
                source: 'axiosInterceptors',
                message: '请求需要url',
            })
        }
        // 默认使用 get 请求
        reqConfig.method = reqConfig.method ? reqConfig.method : 'get'
        // 大小写容错-默认转为小写
        reqConfig.method = reqConfig.method.toLowerCase()

        // 参数容错
        if (reqConfig.method === 'get') {
            if (!reqConfig.params) {
                // 防止字段用错
                reqConfig.params = reqConfig.data || {}
            }
        }
        else if (reqConfig.method === 'post') {
            if (!reqConfig.data) {
                // 防止字段用错
                reqConfig.data = reqConfig.params || {}
            }

            // 检测是否包含文件类型, 若包含则进行 formData 封装
            let hasFile = false
            Object.keys(reqConfig.data).forEach(key => {
                if (typeof reqConfig.data[key] === 'object') {
                    const item = reqConfig.data[key]
                    if (item instanceof FileList || item instanceof File || item instanceof Blob) {
                        hasFile = true
                    }
                }
            })

            // 检测到存在文件使用 FormData 提交数据
            if (hasFile) {
                const formData = new FormData()
                Object.keys(reqConfig.data).forEach(key => {
                    formData.append(key, reqConfig.data[key])
                })
                reqConfig.data = formData
            }
        }
        else {
            // TODO0: 其他类型请求数据格式处理
            console.warn(`其他请求类型: ${reqConfig.method}, 暂无自动处理`)
        }
        // step2: permission 处理
        // if (reqConfig.url === `${import.meta.env.VITE_APP_BASE_URL}/api/v1/user/refresh`) {
        //     const refreshToken = getToken('refresh_token')
        //     if (refreshToken) {
        //         // eslint-disable-next-line no-param-reassign
        //         reqConfig.headers.Authorization = refreshToken
        //     }
        // } else {
        // 有access_token
        const accessToken = getToken('access_token')
        if (accessToken) {
            reqConfig.headers.Authorization = accessToken
        }
        // }
        return reqConfig
    },
    error => {
        Promise.reject(error)
    },
)

// 响应拦截器
_axios.interceptors.response.use(
    async res => {
        let { code, message } = res.data
        if (res.data.code === 0) {
            return res.data
        }
        return new Promise(async (resolve, reject) => {
            const { url } = res.config
            // refresh_token 异常，直接登出
            // 2006 令牌不合法
            // 2010 更新令牌失败
            // if (code === 10000 || code === 10100 || code === 2006 || code === 2010) {
            //     Vue.prototype.$message.error(res.data.message)
            //     setTimeout(() => {
            //         store.dispatch('loginOut')
            //         const { origin } = window.location
            //         window.location.href = origin
            //     }, 1500)
            //     return resolve(null)
            // }
            // // 令牌相关，刷新令牌
            // if (code === 10040 || code === 10041 || code === 10050 || code === 10051 || code === 2007) {
            //     const cache = {}
            //     if (cache.url !== url) {
            //         cache.url = url
            //         const refreshResult = await _axios(`${process.env.VUE_APP_BASE_URL}/api/v1/user/refresh`)
            //         saveAccessToken(refreshResult.access_token)
            //         // 将上次失败请求重发
            //         const result = await _axios(res.config)
            //         return resolve(result)
            //     }
            // }
            // 第一种情况：默认直接提示后端返回的异常信息；特殊情况：如果本次请求添加了 handleError: true，用户自己try catch，框架不做处理
            // if (res.config.handleError) {
            //     return reject(res)
            // }
            // 第二种情况：采用前端自己的一套异常提示信息；特殊情况：如果本次请求添加了 showBackend: true, 弹出后端返回错误信息。
            // if (Config.useFrontEndErrorMsg && !res.config.showBackend) {
            //     // 弹出前端自定义错误信息
            //     const errorArr = Object.entries(ErrorCode).filter(v => v[0] === code.toString())
            //     // 匹配到前端自定义的错误码
            //     if (errorArr.length > 0 && errorArr[0][1] !== '') {
            //         message = errorArr[0][1] // eslint-disable-line
            //     } else {
            //         message = ErrorCode['777']
            //     }
            // }

            vue.$message({
                message: '这是一个错误信息',
                type: 'error',
            });
            reject()
        })
    },
    error => {
        if (!error.response) {
            vue.$notify({
                title: 'Network Error',
                dangerouslyUseHTMLString: true,
                message: '<strong class="my-notify">请检查 API 是否异常</strong>',
            })
        }

        // 判断请求超时
        if (error.code === 'ECONNABORTED' && error.message.indexOf('timeout') !== -1) {
            vue.$message({
                type: 'warning',
                message: '请求超时',
            })
        }
        return Promise.reject(error)
    },
)
// 导出常用函数

/**
 * @param {string} url
 * @param {object} data
 * @param {object} params
 */
export function post(url: string, data = {}, params = {}) {
    return _axios({
        method: 'post',
        url,
        data,
        params,
    })
}

/**
 * @param {string} url
 * @param {object} params
 */
export function get(url: string, params = {}) {
    return _axios({
        method: 'get',
        url,
        params,
        paramsSerializer: (params) => qs.stringify(params, { arrayFormat: 'repeat' })
    })
}

/**
 * @param {string} url
 * @param {object} data
 * @param {object} params
 */
export function put(url: string, data = {}, params = {}) {
    return _axios({
        method: 'put',
        url,
        params,
        data,
    })
}

/**
 * @param {string} url
 * @param {object} params
 */
export function _delete(url: string, params = {}) {
    return _axios({
        method: 'delete',
        url,
        params,
    })
}

export default _axios;