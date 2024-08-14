import { cloneDeep, throttle, debounce, type DebouncedFunc } from 'lodash'
import { type Routes } from '@/router/'
type ThrottledFunc<T extends (...args: any[]) => any> = (...args: Parameters<T>) => ReturnType<T>;
interface Utils {
    cutString: (str: string, len: number) => string;
    debounce: <T extends (...args: any[]) => any>(func: T, wait?: number) => DebouncedFunc<T>;
    throttle: <T extends (...args: any[]) => any>(func: T, wait?: number) => ThrottledFunc<T>;
    getRandomStr: (n: number) => string;
    deepClone: (data: any) => any;
    came: (str: string) => string;
    hasPermission: (permissions: string[], route: any, user: any) => Boolean;
    sortByOrder: (source: Routes) => Routes;
}

const Utils: Utils = {
    /** 
     * 截取字符串，超长部分追加…
     * @param str ——对象字符串
     * @param len ——目标字节长度
     * return 返回值： 处理结果字符串
     */
    cutString: (str, len) => {
        if (str.length * 2 <= len) {
            return str
        }
        let strlen = 0
        let s = ''
        for (let i = 0; i < str.length; i++) {
            // eslint-disable-line
            s += str.charAt(i)
            if (str.charCodeAt(i) > 128) {
                strlen += 2
                if (strlen >= len) {
                    return `${s.substring(0, s.length - 1)}...`
                }
            } else {
                strlen += 1
                if (strlen >= len) {
                    return `${s.substring(0, s.length - 2)}...`
                }
            }
        }
        return s
    },
    /**
     * 防抖函数
     * @param func ——函数体
     * @param wait ——延时
     */
    debounce: (func, wait = 50) => debounce(func, wait),
    /**
     * 节流函数
     * @param {*} func 函数体
     * @param {*} wait 延时
     */
    throttle: (func, wait = 50) => throttle(func, wait),
    /**
     * 返回 n 位的随机字符串
     * @param {Number} n
     */
    getRandomStr: (n = 6) => {
        let str = ''
        const chars = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890'
        for (let i = 0; i < n; i += 1) {
            str += chars.charAt(Math.floor(Math.random() * 62))
        }
        return str
    },
    /**
    * 深度遍历，深拷贝
    * @param {*} data
    */
    deepClone: data => cloneDeep(data),
    /**
    * 中划线转驼峰
    */
    came: str => {
        return `${str}`.replace(/-\D/g, match => match.charAt(1).toUpperCase())
    },
    /**
    * 判断权限
    */
    hasPermission: (permissions, route, user) => {
        if (route.permission) {
            return permissions.some(permission => {
                const aliasList = permission.match(/(?<=\()(.+?)(?=\))/g)
                return aliasList ? route.permission.indexOf(aliasList[0]) > -1 : route.permission.indexOf(permission) > -1
            })
        }
        return true
    },
    /**
     * 根据数组的 order 字段排序
     * @param {Array} source
    */
    sortByOrder: (source: Routes) => {
        if (!Array.isArray(source)) {
            console.error('Lin->util：sortByOrder 函数传入参数不符合要求, 应为数组', source)
            return source
        }
        const tmp: any = []
        let target: Routes = []

        // 将带排序的子项添加进临时数组 tmp
        for (let i = 0; i < source.length; i += 1) {
            if (typeof source[i].order !== 'number') {
                continue
            }
            let { order } = source[i]
            // 支持设置倒数顺序
            if (!order) {
                order = 0
            }
            if (order < 0) {
                order = source.length + order
                if (order < 0) {
                    order = 0
                }
            }

            // 确保整数
            source[i].order = Math.floor(order)

            // 插入临时数组
            insertItem(source[i], tmp)
        }
        // 合并临时数组和原数组
        for (let i = 0, j = 0; i < source.length; i += 1) {
            if (typeof source[i].order === 'number') {
                continue
            }
            // 找需要填的坑
            while (tmp[j]) {
                j += 1
            }
            tmp[j] = source[i]
        }
        // 筛除空隙
        target = tmp.filter((item: any) => !!item)
        return target
    }

}
function insertItem(item: any, arr: any) {
    const { order } = item
    if (typeof arr[order] !== 'number') {
        arr[order] = item
        return
    }
    let moveBegin
    let moveEnd
    let pos
    let i = order + 1

    while (arr[i]) {
        if (arr[i].order > order) {
            if (!moveBegin) {
                moveBegin = i
                pos = i
            }
        }
        i += 1
    }

    if (moveBegin) {
        moveEnd = i
    } else {
        pos = i
    }

    if (!moveEnd) {
        arr[pos] = item
        return
    }

    // 需要移动
    for (let i = moveEnd; i >= moveBegin; i -= 1) {
        arr[i + 1] = arr[i]
    }
    arr[pos] = item
}
/**
 * 获取数据类型
 * @param obj 任意类型
 */
export function getTypeOf(obj: any): string {
    const { toString } = Object.prototype
    const map = {
        '[object Boolean]': 'boolean',
        '[object Number]': 'number',
        '[object String]': 'string',
        '[object Function]': 'function',
        '[object Array]': 'array',
        '[object Date]': 'date',
        '[object RegExp]': 'regExp',
        '[object Undefined]': 'undefined',
        '[object Null]': 'null',
        '[object Object]': 'object',
        '[object Symbol]': 'symbol',
    } as const;
    return map[toString.call(obj) as keyof typeof map] || 'unknown';
}

let cached: number;
/**
 * 获取窗口滚动条大小, From: https://github.com/react-component/util/blob/master/src/getScrollBarSize.js
 * @param {boolean} fresh 强制重新计算
 * @returns {number}
 */
export function getScrollBarSize(fresh: Boolean) {
    if (fresh || cached === undefined) {
        const inner = document.createElement('div')
        inner.style.width = '100%'
        inner.style.height = '200px'

        const outer = document.createElement('div')
        const outerStyle = outer.style

        outerStyle.position = 'absolute'
        outerStyle.top = 0 + 'px'
        outerStyle.left = 0 + 'px'
        outerStyle.pointerEvents = 'none'
        outerStyle.visibility = 'hidden'
        outerStyle.width = '200px'
        outerStyle.height = '150px'
        outerStyle.overflow = 'hidden'

        outer.appendChild(inner)

        document.body.appendChild(outer)

        const widthContained = inner.offsetWidth
        outer.style.overflow = 'scroll'
        let widthScroll = inner.offsetWidth

        if (widthContained === widthScroll) {
            widthScroll = outer.clientWidth
        }

        document.body.removeChild(outer)

        cached = widthContained - widthScroll
    }
    return cached
}

export default Utils
