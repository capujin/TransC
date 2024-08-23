package com.mvc.test.utils;

import lombok.Data;

/**
 * 通用的 API 响应结果类。
 * @param <T> 响应数据的类型
 */
@Data
public class Result<T> {

    private int code;
    private String message;
    private T data;
    private long total;

    public Result(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
    public Result(int code, String message, T data, long total) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.total = total;
    }

    // 成功返回带数据
    public static <T> Result<T> successLogin(T data) {
        return new Result<>(0, "登陆成功", data);
    }

    // 成功返回带数据
    public static <T> Result<T> success(T data) {
        return new Result<>(0, "操作成功", data);
    }

    // 成功返回带数据
    public static <T> Result<T> success(T data, long total) {
        return new Result<>(0, "操作成功", data, total);
    }

    // 成功返回不带数据
    public static <T> Result<T> success() {
        return success(null);
    }

    // 用户名或密码错误
    public static <T> Result<T> inputError() {
        return new Result<>(-1, "账户名或密码错误", null);
    }

    // 自定错误信息
    public static <T> Result<T> msgError(String msg) {
        return new Result<>(-1, msg, null);
    }

    // 网络错误
    public static <T> Result<T> netError() {
        return new Result<>(-1, "网络错误，请稍后重试", null);
    }

    // 权限不足
    public static <T> Result<T> permissionError() {
        return new Result<>(-1, "权限不足", null);
    }

    // 参数无效
    public static <T> Result<T> paramError(String message) {
        return new Result<>(-1, message, null);
    }

    // 操作失败
    public static <T> Result<T> failure(String message) {
        return new Result<>(-1, message, null);
    }

    // 资源未找到
    public static <T> Result<T> notFound() {
        return new Result<>(-1, "资源未找到", null);
    }

    // 业务逻辑错误
    public static <T> Result<T> businessError(String message) {
        return new Result<>(-1, message, null);
    }

    // 未认证或授权
    public static <T> Result<T> unauthorized() {
        return new Result<>(-1, "未认证或授权", null);
    }

    // 系统内部错误
    public static <T> Result<T> internalServerError() {
        return new Result<>(-1, "系统内部错误", null);
    }
}
