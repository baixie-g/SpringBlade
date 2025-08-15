package org.springblade.common.constant;

/**
 * 通用常量
 * @author SpringBlade
 */
public class Constants {
    
    /**
     * UTF-8 字符集
     */
    public static final String UTF8 = "UTF-8";
    
    /**
     * GBK 字符集
     */
    public static final String GBK = "GBK";
    
    /**
     * http请求
     */
    public static final String HTTP = "http://";
    
    /**
     * https请求
     */
    public static final String HTTPS = "https://";
    
    /**
     * 成功标记
     */
    public static final Integer SUCCESS = 200;
    
    /**
     * 失败标记
     */
    public static final Integer FAIL = 500;
    
    /**
     * 登录成功状态
     */
    public static final String LOGIN_SUCCESS_STATUS = "0";
    
    /**
     * 登录失败状态
     */
    public static final String LOGIN_FAIL_STATUS = "1";
    
    /**
     * 登录成功
     */
    public static final String LOGIN_SUCCESS = "Success";
    
    /**
     * 注销
     */
    public static final String LOGOUT = "Logout";
    
    /**
     * 注册
     */
    public static final String REGISTER = "Register";
    
    /**
     * 登录失败
     */
    public static final String LOGIN_FAIL = "Error";
    
    /**
     * 验证码有效期（分钟）
     */
    public static final Integer CAPTCHA_EXPIRATION = 2;
    
    /**
     * 令牌有效期（分钟）
     */
    public static final Integer TOKEN_EXPIRE = 720;
    
    /**
     * 令牌刷新时间（分钟）
     */
    public static final Integer TOKEN_REFRESH_TIME = 480;
    
    /**
     * 令牌刷新时间（分钟）
     */
    public static final Integer TOKEN_REFRESH_TIME_OFFSET = 240;
    
    /**
     * 令牌刷新时间（分钟）
     */
    public static final Integer TOKEN_REFRESH_TIME_OFFSET_DAY = 1;
    
    /**
     * 令牌刷新时间（分钟）
     */
    public static final Integer TOKEN_REFRESH_TIME_OFFSET_HOUR = 24;
    
    /**
     * 令牌刷新时间（分钟）
     */
    public static final Integer TOKEN_REFRESH_TIME_OFFSET_MINUTE = 60;
    
    /**
     * 令牌刷新时间（分钟）
     */
    public static final Integer TOKEN_REFRESH_TIME_OFFSET_SECOND = 3600;
    
    /**
     * 令牌刷新时间（分钟）
     */
    public static final Integer TOKEN_REFRESH_TIME_OFFSET_MILLISECOND = 1000;
    
    /**
     * 令牌刷新时间（分钟）
     */
    public static final Integer TOKEN_REFRESH_TIME_OFFSET_MICROSECOND = 1000000;
    
    /**
     * 令牌刷新时间（分钟）
     */
    public static final Integer TOKEN_REFRESH_TIME_OFFSET_NANOSECOND = 1000000000;
    
    /**
     * 资源映射路径 前缀
     */
    public static final String RESOURCE_PREFIX = "/profile";
}
