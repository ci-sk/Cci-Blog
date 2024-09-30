package org.example.entity;

import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.JSONWriter;

/**
 * 表示一个通用的响应对象，包含状态码、数据和消息。
 * 这个类用于统一处理 API 的响应，提供成功和失败的不同状态。
 */
public record RestBean<T>(int code, T data, String message) {
    /**
     * 创建一个成功响应，包含数据。
     *
     * @param data 成功响应的数据。
     * @param <T>  数据的类型。
     * @return 一个成功的 RestBean 实例。
     */
    public static <T> RestBean<T> success(T data){
        return new RestBean<>(200,data,"请求成功");
    }

    /**
     * 创建一个成功响应，不包含数据。
     *
     * @param <T> 数据的类型。
     * @return 一个成功的 RestBean 实例。
     */
    public static <T> RestBean<T> success(){
        return success(null);
    }


    /**
     * 创建一个未授权的响应，包含自定义消息。
     *
     * @param message 自定义的未授权消息。
     * @param <T>     数据的类型。
     * @return 一个未授权的 RestBean 实例。
     */
    public static <T> RestBean<T> unauthorized(String message){
        return failure(401,message);
    }

    /**
     * 创建一个禁止访问的响应，包含自定义消息。
     *
     * @param message 自定义的禁止访问消息。
     * @param <T>     数据的类型。
     * @return 一个禁止访问的 RestBean 实例。
     */
    public static <T> RestBean<T> forbidden(String message){
        return failure(403,message);
    }

    /**
     * 创建一个失败响应，包含自定义状态码和消息。
     *
     * @param code    自定义的失败状态码。
     * @param message 自定义的失败消息。
     * @param <T>     数据的类型。
     * @return 一个失败的 RestBean 实例。
     */
    public static <T> RestBean<T> failure(int code, String message){
        return new RestBean<>(code,null,message);
    }

    /**
     * 创建一个数据库操作失败的响应，包含默认消息。
     *
     * @param <T> 数据的类型。
     * @return 一个失败的 RestBean 实例。
     */
    public static <T> RestBean<T> db_failure(){
        return failure(10001,"添加失败");
    }

    /**
     * 创建一个数据库操作成功的响应，包含数据和自定义消息。
     *
     * @param data    成功响应的数据。
     * @param message 自定义的成功消息。
     * @param <T>     数据的类型。
     * @return 一个成功的 RestBean 实例。
     */
    public static <T> RestBean<T> db_success(T data, String message){
        return new RestBean<>(200,data,message);
    }

    public static <T> RestBean<T> db_success(String message){
        return new RestBean<>(200,null,message);
    }

    /**
     * 创建一个数据库添加成功的响应，包含数据和自定义消息。
     *
     * @param data    成功响应的数据。
     * @param message 自定义的成功消息。
     * @param <T>     数据的类型。
     * @return 一个成功的 RestBean 实例。
     */
    public static <T> RestBean<T> db_add_success(T data, String message){
        return new RestBean<>(200,data,message);
    }

    /**
     * 创建一个数据库添加失败的响应，包含自定义状态码和消息。
     *
     * @param code    自定义的失败状态码。
     * @param message 自定义的失败消息。
     * @param <T>     数据的类型。
     * @return 一个失败的 RestBean 实例。
     */
    public static <T> RestBean<T> db_add_failure(int code, String message){
        return new RestBean<>(code,null,message);
    }

    /**
     * 创建一个数据库更改成功的响应，包含数据和自定义消息。
     *
     * @param data    成功响应的数据。
     * @param message 自定义的成功消息。
     * @param <T>     数据的类型。
     * @return 一个成功的 RestBean 实例。
     */
    public static <T> RestBean<T> db_update_success(T data, String message){
        return new RestBean<>(200,data,message);
    }

    /**
     * 创建一个数据库更改失败的响应，包含自定义状态码和消息。
     *
     * @param message 自定义的失败消息。
     * @param <T>     数据的类型。
     * @return 一个失败的 RestBean 实例。
     */
    public static <T> RestBean<T> db_un_failure(String message){
        return new RestBean<>(401,null,message);
    }

    /**
     * 将 RestBean 实例转换为 JSON 字符串。
     *
     * @return RestBean 实例的 JSON 字符串表示。
     */
    public String asJsonString(){
        return JSONObject.toJSONString(this, JSONWriter.Feature.WriteNulls);
    }
}
