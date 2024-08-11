import { post, get, put, _delete } from '@/lin/plugin/axios'
export default class Admin {
    static async getToken(data: object, isAdmin: boolean = false): Promise<any> {
        const url = `${import.meta.env.VITE_APP_BASE_URL}/api${isAdmin ? '/admin' : '/user'}/login`
        const res = await post(url, data);
        return res;
    };
}