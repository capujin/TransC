export interface Response {
    code: number;
    message: string;
    data: any;
    total?: number;
    currentPage?: number;
}
