package com.power.enums;

public enum AppHttpCodeEnum {
    // 成功
    SUCCESS(200,"操作成功"),
    // 登录
    NEED_LOGIN(401,"需要登录后操作"),
    NO_OPERATOR_AUTH(403,"无权限操作"),
    SYSTEM_ERROR(500,"出现错误"),
    USERNAME_EXIST(501,"用户名已存在"),
    PHONENUMBER_EXIST(502,"手机号已存在"),
    EMAIL_EXIST(503, "邮箱已存在"),
    REQUIRE_USERNAME(504, "必需填写用户名"),
    LOGIN_ERROR(505,"用户名或密码错误"),
    COMMENT_NOT_NULL(506, "评论不能为空"),
    COMMENT_CONTENT_NOT_NULL(507, "评论内容不能为空"),
    FILE_TYPE_ERROR(508, "文件类型错误，请上传png/jpg/jpeg文件"),
    USER_NOT_NULL(509, "用户不能为空"),
    USERNAME_NOT_NULL(510, "用户名不能为空"),
    NICKNAME_NOT_NULL(511, "昵称不能为空"),
    PASSWORD_NOT_NULL(512, "密码不能为空"),
    EMAIL_NOT_NULL(513, "邮箱不能为空"),
    NICKNAME_EXIST(514, "昵称已存在"),
    PARAMETER_NOT_NULL(515, "参数不能为空"),
    TAG_EXIST(516, "标签已存在"),
    UPDATE_MENU_PARENT_ERROR(519, "修改菜单'写博文'失败，上级菜单不能选择自己"),
    UPDATE_MENU_CHILDREN_ERROR(520, "存在子菜单不允许删除"),
    ID_NOT_NULL(521, "id参数不能为空"),
    STATUS_NOT_NULL(522, "status不能为空"),
    REDIS_KEY_NOT_EXIST(523, "key不存在"),
    ADD_ERROR(530, "添加失败"),
    SELECT_ERROR(531, "查询失败"),
    UPDATE_ERROR(532, "更新失败"),
    DELETE_ERROR(533, "删除失败");
    int code;
    String msg;

    AppHttpCodeEnum(int code, String errorMessage){
        this.code = code;
        this.msg = errorMessage;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
