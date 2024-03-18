package com.book.manager.util.http;

/**
 * @Description 响应状态码枚举类
 */
public enum CodeEnum {
    /** 请求成功 */
    SUCCESS(200,"成功!"),
    /** 找不到资源 */
    NOT_FOUND(404,"找不到资源!"),
    /** 请求参数错误 */
    PARAM_ERROR(444,"请求参数错误!"),
    USER_NOT_FOUND(446,"找不到用户!"),
    BOOK_NAME_NOT_EXIST_ERROR(-1,"请填写图书名称"),
    BOOK_ISBN_NOT_EXIST_ERROR(-1,"请填写编号"),
    BOOK_AUTHOR_NOT_EXIST_ERROR(-1,"请填写作者"),
    BOOK_ADD_ERROR(-1,"图书添加失败"),
    BOOK_EDIT_ERROR(-1,"图书编辑失败"),
    USER_HEAD_PIC_ERROR(-1,"请上传头像"),
    USERNAME_NOT_EXIST_ERROR(-1,"用户名不能为空"),
    NICKNAME_NOT_EXIST_ERROR(-1,"昵称不能为空"),
    USER_MOBILE_NOT_EXIST_ERROR(-1,"请填写手机号"),
    USER_EMAIL_NOT_EXIST_ERROR(-1,"请填写邮箱"),
    USER_ADD_ERROR(-1,"读者添加失败"),
    USER_EDIT_ERROR(-1,"读者修改失败"),
    USER_NAME_IS_EXIST_ERROR(-1,"用户名已存在！");
    CodeEnum(int code, String data) {
        this.code = code;
        this.data = data;
    }

    private int code;
    private String data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
