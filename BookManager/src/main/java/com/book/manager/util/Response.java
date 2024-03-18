package com.book.manager.util;

import com.book.manager.util.http.CodeEnum;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @Description 返回对象
 */
public class Response implements Serializable{

    @ApiModelProperty("响应码")
    private Integer code;

    @ApiModelProperty("响应信息")
    private String msg;

    @ApiModelProperty("响应数据")
    private Object data;

    public Response() {
    }

    public Response(String msg, String data) {
        this.msg = msg;
        this.data = data;
    }

    public Response(Integer code, String msg, String data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }


    public Response(CodeEnum codeEnum, Object data) {
        this.code = codeEnum.getCode();
        this.msg = codeEnum.getData();
        this.data = data;
    }

    public Response(CodeEnum codeEnum) {
        this.code = codeEnum.getCode();
        this.msg = codeEnum.getData();
    }


    public static Response success(CodeEnum codeEnum, Object data) {
        return new Response(codeEnum,data);
    }

    public static Response success(CodeEnum codeEnum) {
        return new Response(codeEnum);
    }

    public static Response fail(CodeEnum codeEnum) {
        return new Response(codeEnum);
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
