package com.power.constants;

public class SystemConstants
{
    /**
     *  文章是草稿
     */
    public static final String ARTICLE_STATUS_DRAFT = "1";
    /**
     *  文章是正常分布状态
     */
    public static final String ARTICLE_STATUS_NORMAL = "0";

    /**
     *  分类不可显示
     */
    public static final String CATEGORY_STATUS_DRAFT = "1";
    /**
     *  分类可显示
     */
    public static final String CATEGORY_STATUS_NORMAL = "0";

    /**
     *  友链审核未通过
     */
    public static final String LINK_STATUS_DRAFT = "0";
    /**
     *  友链审核未通过
     */
    public static final String LINK_STATUS_NORMAL = "1";
    /**
     *  友链未审核
     */
    public static final String LINK_STATUS_NOT = "2";

    /**
     *  根评论Id
     */
    public static final long COMMENT_ROOT_ID = -1;

    /**
     *  管理员Id
     */
    public static final Long USER_ADMIN_ID = 1L;

    /**
     *  注册时默认UserId(注册时自己还是一个游客)
     */
    public static final Long DEFAULT_REGISTER_USER_ID = 1L;

    /**
     *  评论类型：文章评论
     */
    public static final String ARTICLE_COMMENT_TYPE = "0";

    /**
     *  评论类型：友链评论
     */
    public static final String LINK_COMMENT_TYPE = "1";

    /**
     *  redis中viewCount的key
     */
    public static final String ARTICLE_VIEW_COUNT_KEY = "article:viewCount";



    /**
     *  redis中博客前台的Jwt的key
     */
    public static final String JWT_BLOG_KEY_PREFIX = "bloglogin:";


    /**
     *  redis中博客后台的Jwt的key
     */
    public static final String JWT_ADMIN_KEY_PREFIX = "login:";



    public static final String MENU = "C";
    public static final String BUTTON = "F";

    public static final String STATUS_NORMAL = "0";



    public static final Long MENU_PARENT_ID = 0L;

    /** 正常状态 */
    public static final String NORMAL = "0";


    public static final String ADMIN = "1";
}