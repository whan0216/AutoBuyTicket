package com.autobuyticket.constant;

import java.io.File;

public class Constant
{
    // ------------------ 请求的URL常量 -----------------
    public static final String URL_4_COOKIE = "https://dynamic.12306.cn/otsweb/";
    
    public static final String URL_4_STATION_NAMES = "http://dynamic.12306.cn/otsquery/js/common/station_name.js";
    
    /**
     * 确认订单的接口的URL
     */
    public static final String URL_4_PASS_CODE = "https://dynamic.12306.cn/otsweb/passCodeAction.do?rand=randp";
    
    /**
     * 查询余票接口的取cookie的URL
     */
    public static final String URL_4_QUERY_COOKIE = "http://dynamic.12306.cn/otsquery/query/queryRemanentTicketAction.do?method=init";
    
    /**
     * 查询余票接口的cookie的URL
     */
    public static final String URL_4_REMANENT_TICKET = "http://dynamic.12306.cn/otsquery/query/queryRemanentTicketAction.do?method=queryLeftTicket";
    
    /**
     * 查询车次信息的URL
     */
    public static final String URL_4_STS_TRAIN_ALL = "http://dynamic.12306.cn/otsquery/query/queryRemanentTicketAction.do?method=queryststrainall";
    
    /**
     * 获取校验码的URL
     */
    public static final String URL_4_VERIFY_CODE = "https://dynamic.12306.cn/otsweb/passCodeAction.do?rand=sjrand";
    
    /**
     * 获取登陆随机码的URL
     */
    public static final String URL_4_RAND_CODE = "https://dynamic.12306.cn/otsweb/loginAction.do?method=loginAysnSuggest";
    
    /**
     * 登陆的URL
     */
    public static final String URL_4_LOGIN = "https://dynamic.12306.cn/otsweb/loginAction.do?method=login";
    
    /**
     * 预定车票的URL
     */
    public static final String URL_4_PRE_BOOK_TICKET = "https://dynamic.12306.cn/otsweb/order/querySingleAction.do?method=submutOrderRequest";
    
    /**
     * 车票数量
     */
    public static final String URL_4_QUEUECOUNT = "https://dynamic.12306.cn/otsweb/order/confirmPassengerAction.do?method=getQueueCount";
    
    /**
     * 确认订单
     */
    public static final String URL_4_CONFIRM_ORDER = "https://dynamic.12306.cn/otsweb/order/confirmPassengerAction.do?method=confirmSingleForQueueOrder";
    
    /**
     * 查询常用联系人信息
     */
    public static final String URL_4_GET_PASSENGER = "https://dynamic.12306.cn/otsweb/order/confirmPassengerAction.do?method=getpassengerJson";
    
    // ---------------------Referer URL ------------------------
    /**
     * 查询余票接口的Referer URL
     */
    public static final String URL_4_REFERER_REMANENT_TICKET = "http://dynamic.12306.cn/otsquery/query/queryRemanentTicketAction.do?method=init";
    
    /**
     * 登陆接口的Referer URL
     */
    public static final String URL_4_REFERER_LOGIN_IN = "https://dynamic.12306.cn/otsweb/loginAction.do?method=init";
    
    /**
     * 订单接口的Referer URL
     */
    public static final String URL_4_REFERER_ORDER = "https://dynamic.12306.cn/otsweb/order/querySingleAction.do?method=init";
    
    /**
     * 乘客相关接口的Referer URL
     */
    public static final String URL_4_REFERER_PASSENGER = "https://dynamic.12306.cn/otsweb/order/confirmPassengerAction.do?method=init";
    
    // ------------------ 缓存的文件名称常量 ----------------
    public static final String CACHE_STATION_NAMES = "station_name.txt";
    
    public static final String CACHE_VERIFY_CODE_IMG = "verifyCode.jpg";
    
    public static final String CACHE_PASS_CODE_IMG = "passCode.jpg";
    
    // --------------------- 通用常量 --------------------
    public static final String PATH_SEPARATOR = File.pathSeparator;
}
