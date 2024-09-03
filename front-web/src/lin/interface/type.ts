export interface Response {
    code: number;
    message: string;
    data: any;
    total?: number;
    currentPage?: number;
}
export interface User {
    id:string
    create_time: string
    name: string
    status: string
}