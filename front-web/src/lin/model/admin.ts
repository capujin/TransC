import { post, get, put, _delete } from '@/lin/plugin/axios'
export default class Admin {
    static async getToken(data: { username: string, password: string }, isAdmin: boolean = false): Promise<any> {
        const url = `${import.meta.env.VITE_APP_BASE_URL}/api${isAdmin ? '/admin' : '/user'}/login`
        const res = await post(url, data);
        return res;
    };

    static async getPermissions(): Promise<any> {
        const info = await get(`${import.meta.env.VITE_APP_BASE_URL}/user/permissions`)
        return info;
    };
    
}