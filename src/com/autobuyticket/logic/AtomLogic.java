package com.autobuyticket.logic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;

import com.autobuyticket.config.SysConfig;
import com.autobuyticket.constant.Constant;
import com.autobuyticket.core.CommonUtil;
import com.autobuyticket.core.HttpUtil;
import com.autobuyticket.core.StringUtil;
import com.autobuyticket.domain.LoginInfo;
import com.autobuyticket.domain.RemainTicketInfo;
import com.autobuyticket.domain.StationNameInfo;
import com.autobuyticket.domain.TicketQryCondition;
import com.autobuyticket.domain.TrainNoInfo;
import com.autobuyticket.domain.TrainSelectedInfo;
import com.autobuyticket.log.LogFactory;

public class AtomLogic
{
    public static Header[] getHeaderCookie()
    {
        Header[] httpsCookieHeaders = null;
        try
        {
            HttpResponse response = HttpUtil.sendGet(Constant.URL_4_COOKIE);
            
            httpsCookieHeaders = HttpUtil.getRequestCookieHeader(response);
            
            LogFactory.info("获取https cookie:"
                    + java.util.Arrays.toString(httpsCookieHeaders));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        return httpsCookieHeaders;
    }
    
    /**
     * 
    * 获取车站名称信息集 
    * <功能详细描述> 
    * @return [参数说明] 
    * 
    * @return Map<String,StationNameInfo> [返回类型说明] 
    * @exception throws [违例类型] [违例说明] 
    * @see [类、类#方法、类#成员]
     */
    public static Map<String, StationNameInfo> getStationNames()
    {
        Map<String, StationNameInfo> ret = new HashMap<String, StationNameInfo>();
        
        try
        {
            HttpResponse response = HttpUtil.sendGet(Constant.URL_4_STATION_NAMES);
            
            String stationNames = CommonUtil.getContent(response.getEntity()
                    .getContent());
            stationNames = stationNames.substring("var station_names ='".length());
            stationNames = stationNames.substring(0, stationNames.length());
            
            //以下开始解析stationNames
            String[] stations = stationNames.split("[@]");
            for (int i = 0; i < stations.length; i++)
            {
                String station = stations[i];
                if (!StringUtil.isEmpty(station))
                {
                    String[] stationSttrs = station.split("[|]");
                    
                    StationNameInfo stationInfo = new StationNameInfo();
                    stationInfo.setId(stationSttrs[5]);
                    stationInfo.setJianma(stationSttrs[4]);
                    stationInfo.setName(stationSttrs[1]);
                    stationInfo.setPinyin(stationSttrs[3]);
                    stationInfo.setShortname(stationSttrs[0]);
                    stationInfo.setTelecode(stationSttrs[2]);
                    
                    ret.put(stationInfo.getName(), stationInfo);
                }
            }
            
            //缓存此次得到的文件
            CommonUtil.writeCacheFile(Constant.CACHE_STATION_NAMES,
                    stationNames);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        return ret;
    }
    
    /**
     * 
    * 查询车次信息<p> 
    * 需要传入始发站，目的车站，开始日期，开始时间
    * @param qryCondition
    * @param cookieHeaders
    * 
    * @return Map<String,TrainNoInfo> [返回类型说明] 
    * @exception throws [违例类型] [违例说明] 
    * @see [类、类#方法、类#成员]
     */
    public static Map<String, TrainNoInfo> getTrainNoInfo(
            TicketQryCondition qryCondition, Header[] cookieHeaders)
    {
        Map<String, TrainNoInfo> retMap = new HashMap<String, TrainNoInfo>();
        
        List<NameValuePair> nvps = CommonUtil.getParamList(qryCondition,
                "queryststrainall");
        
        Header[] headers = HttpUtil.getRequestHeader(Constant.URL_4_REFERER_REMANENT_TICKET,
                cookieHeaders);
        
        for (int idx = 0; idx < 5; idx++)
        {
            try
            {
                HttpResponse response = HttpUtil.sendPost(Constant.URL_4_STS_TRAIN_ALL,
                        nvps,
                        headers);
                String content = CommonUtil.getContent(response.getEntity()
                        .getContent());
                
                JSONArray ja = JSONArray.fromObject(content);
                for (int i = 0; i < ja.size(); i++)
                {
                    JSONObject jo = (JSONObject)ja.get(i);
                    TrainNoInfo trainNoInfo = TrainNoInfo.getNewInstance(jo);
                    retMap.put(trainNoInfo.getTrain_no(), trainNoInfo);
                }
                break;
            }
            catch (Exception e)
            {
            }
        }
        return retMap;
    }
    
    /**
     * 
    * 余票查询接口取cookie 
    * <功能详细描述> 
    * @return [参数说明] 
    * 
    * @return Header[] [返回类型说明] 
    * @exception throws [违例类型] [违例说明] 
    * @see [类、类#方法、类#成员]
     */
    public static Header[] getQueryHeaderCookie()
    {
        Header[] httpsCookieHeaders = null;
        try
        {
            HttpResponse response = HttpUtil.sendGet(Constant.URL_4_QUERY_COOKIE);
            
            httpsCookieHeaders = HttpUtil.getRequestCookieHeader(response);
            
            LogFactory.info("获取https cookie:"
                    + java.util.Arrays.toString(httpsCookieHeaders));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        return httpsCookieHeaders;
    }
    
    /**
     * 
    * 获得余票信息 
    * <功能详细描述> 
    * @param headers
    * @return [参数说明] 
    * 
    * @return List [返回类型说明] 
    * @exception throws [违例类型] [违例说明] 
    * @see [类、类#方法、类#成员]
     */
    public static Map<String, RemainTicketInfo> getRemanentTicket(
            String refererUrl, TicketQryCondition qryCondition,
            Header[] cookieHeaders)
    {
        Map<String, RemainTicketInfo> retmap = new HashMap<String, RemainTicketInfo>();
        
        String params = CommonUtil.getParamsString(qryCondition,
                "queryLeftTicket");
        
        String url = refererUrl.replace("init", "queryLeftTicket") + "&"
                + params;
        Header[] headers = HttpUtil.getRequestHeader(refererUrl, cookieHeaders);
        
        try
        {
            HttpResponse response = HttpUtil.sendGet(url, headers);
            
            String content = CommonUtil.getContent(response.getEntity()
                    .getContent());
            
            String datas = null;
            //是否是来自订单接口的查询
            boolean isOrder = false;
            
            if (content.startsWith("{"))
            {
                //非订单接口的响应返回值是json值
                JSONObject jo = JSONObject.fromObject(content);
                datas = jo.getString("datas");
            }
            else
            {
                datas = content;
                isOrder = true;
            }
            
            System.out.println(datas);
            
            if (!StringUtil.isEmpty(datas) && datas.trim().startsWith("0"))
            {
                String dataArray[] = datas.split(",");
                
                int num = 0;//计数器
                RemainTicketInfo currInfo = null;
                for (String data : dataArray)
                {
                    if (data.startsWith("<span"))
                    {
                        if (null != currInfo)
                        {
                            retmap.put(currInfo.getTrain_code(), currInfo);
                        }
                        
                        //计数器清零，重新开始记录下一个列车的信息
                        num = 0;
                        currInfo = new RemainTicketInfo();
                        List<String> vals = CommonUtil.splitValues(data);
                        String str = vals.get(2);
                        if (!StringUtil.isEmpty(str))
                        {
                            String[] strs = str.split("#");
                            currInfo.setId(strs[0]);
                            currInfo.setFrom_station_code(strs[1]);
                            currInfo.setTo_station_code(strs[2]);
                            currInfo.setTrain_code(CommonUtil.getXMLValue(data));
                        }
                    }
                    //发站的名称
                    else if (num == 1 && null != currInfo)
                    {
                        data = data.replaceAll("&nbsp;", "").trim();
                        currInfo.setFrom_station_name(data.substring(0,
                                data.length() - 5));
                        currInfo.setFrom_station_time(data.substring(data.length() - 5));
                    }
                    //到站的名称
                    else if (num == 2 && null != currInfo)
                    {
                        data = data.replaceAll("&nbsp;", "").trim();
                        currInfo.setTo_station_name(data.substring(0,
                                data.length() - 5));
                        currInfo.setTo_station_time(data.substring(data.length() - 5));
                    }
                    //历时
                    else if (num == 3 && null != currInfo)
                    {
                        currInfo.setLishi(data.trim());
                    }
                    //商务座
                    else if (num == 4 && null != currInfo)
                    {
                        currInfo.setBusiness_ticket(CommonUtil.getXMLValue(data));
                    }
                    //特等座
                    else if (num == 5 && null != currInfo)
                    {
                        currInfo.setVip_ticket(CommonUtil.getXMLValue(data));
                    }
                    //一等座
                    else if (num == 6 && null != currInfo)
                    {
                        currInfo.setOne_level_ticket(CommonUtil.getXMLValue(data));
                    }
                    //二等座
                    else if (num == 7 && null != currInfo)
                    {
                        currInfo.setTwo_level_ticket(CommonUtil.getXMLValue(data));
                    }
                    //高级软卧座
                    else if (num == 8 && null != currInfo)
                    {
                        currInfo.setHigh_soft_berth_ticket(CommonUtil.getXMLValue(data));
                    }
                    //软卧
                    else if (num == 9 && null != currInfo)
                    {
                        currInfo.setSoft_berth_ticket(CommonUtil.getXMLValue(data));
                    }
                    //硬卧
                    else if (num == 10 && null != currInfo)
                    {
                        currInfo.setHard_berth_ticket(CommonUtil.getXMLValue(data));
                    }
                    //软座
                    else if (num == 11 && null != currInfo)
                    {
                        currInfo.setSoft_seat_ticket(CommonUtil.getXMLValue(data));
                    }
                    //硬座
                    else if (num == 12 && null != currInfo)
                    {
                        currInfo.setHard_seat_ticket(CommonUtil.getXMLValue(data));
                    }
                    //无座
                    else if (num == 13 && null != currInfo)
                    {
                        currInfo.setNo_seat_ticket(CommonUtil.getXMLValue(data));
                    }
                    //其他座位
                    else if (num == 14 && null != currInfo)
                    {
                        currInfo.setOther_seat_ticket(CommonUtil.getXMLValue(data));
                    }
                    //其他信息 selected的js参数
                    else if (num == 15 && null != currInfo)
                    {
                        List<String> vals = CommonUtil.splitValues(data);
                        if (isOrder && data.contains("getSelected"))
                        {
                            currInfo.setTagInfo(vals.get(3));
                            currInfo.setSelectedInfo(new TrainSelectedInfo(
                                    currInfo.getTagInfo()));
                        }
                        else if (!isOrder && data.contains("getSelected"))
                        {
                            currInfo.setTagInfo(vals.get(2));
                        }
                    }
                    num++;
                }
            }
            else if (StringUtil.isEmpty(datas))
            {
                //查询无票
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        return retmap;
    }
    
    /**
     * 
    * 获取验证码 
    * <功能详细描述> 
    * @param url
    * @param cookieHeaders
    * 
    * @return File [返回类型说明] 
    * @exception throws [违例类型] [违例说明] 
    * @see [类、类#方法、类#成员]
     */
    public static File getVerifyCode(String url, Header[] cookieHeaders)
    {
        File ret = null;
        if (null != cookieHeaders)
        {
            try
            {
                //获取登陆验证码
                Header[] headers = HttpUtil.getRequestHeader(Constant.URL_4_REFERER_LOGIN_IN,
                        cookieHeaders);
                
                HttpResponse response = HttpUtil.sendGet(url, headers);
                InputStream in = response.getEntity().getContent();
                
                ret = new File(SysConfig.getDefConfig().getCache_path(),
                        Constant.CACHE_VERIFY_CODE_IMG);
                
                CommonUtil.streamPipeline(in, new FileOutputStream(ret));
            }
            catch (Exception e)
            {
                
            }
        }
        return ret;
    }
    
    /**
     * 
    * 登录车票订票页面 
    * <功能详细描述> 
    * @param loginInfo
    * @param cookieHeaders
    * @return [参数说明] 
    * 
    * @return boolean [返回类型说明] 
    * @exception throws [违例类型] [违例说明] 
    * @see [类、类#方法、类#成员]
     */
    public static boolean login(LoginInfo loginInfo, Header[] cookieHeaders)
    {
        try
        {
            //获取登陆的随机数
            Header headers[] = HttpUtil.getRequestHeader(Constant.URL_4_REFERER_LOGIN_IN,
                    cookieHeaders);
            
            HttpResponse response = HttpUtil.sendPost(Constant.URL_4_RAND_CODE,
                    (Map<String, String>)null,
                    headers);
            String content = CommonUtil.getContent(response.getEntity()
                    .getContent());
            JSONObject jo = JSONObject.fromObject(content);
            String loginRand = jo.getString("loginRand");
            loginInfo.setLoginRand(loginRand);
            //构造登录参数,登陆
            List<NameValuePair> nvps = CommonUtil.getParamList(loginInfo);
            
            //登陆
            response = HttpUtil.sendPost(Constant.URL_4_LOGIN, nvps, headers);
            String loginResult = CommonUtil.getContent(response.getEntity()
                    .getContent());
            
            if (loginResult.indexOf("欢迎") > -1)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return false;
        }
    }
    
    /**
     * 
    * 预定票 
    * <功能详细描述> 
    * @param ticket
    * @param remain
    * @param cookieHeaders
    * @return [参数说明] 
    * 
    * @return boolean [返回类型说明] 
    * @exception throws [违例类型] [违例说明] 
    * @see [类、类#方法、类#成员]
     */
    public static boolean doPreBookTicket(TicketQryCondition ticket,
            RemainTicketInfo remain, Header[] cookieHeaders)
    {
        boolean ret = false;
        Header[] headers = HttpUtil.getRequestHeader(Constant.URL_4_REFERER_ORDER,
                cookieHeaders);
        
        //填充单返程信息
        if (StringUtil.isEmpty(ticket.getSingle_round_type()))
        {
            ticket.setSingle_round_type("1");
        }
        if ("1".equals(ticket.getSingle_round_type()))
        {
            ticket.setRound_start_time_str(ticket.getStart_time());
            ticket.setRound_train_date(ticket.getStart_date());
        }
        
        List<NameValuePair> nvps = CommonUtil.getParamList(ticket,
                "submutOrderRequest");
        nvps.addAll(CommonUtil.getParamList(remain.getSelectedInfo(),
                "submutOrderRequest"));
        
        try
        {
            HttpResponse response = HttpUtil.sendPost(Constant.URL_4_PRE_BOOK_TICKET,
                    nvps,
                    headers);
            String beforeOrderResult = CommonUtil.getContent(response.getEntity()
                    .getContent());
            
            if (beforeOrderResult.indexOf("系统忙") > -1)
            {
            }
            else
            {
                ret = true;
            }
        }
        catch (Exception ex)
        {
            
        }
        return ret;
    }
    
    public static boolean doSubmitOrder(Header[] cookieHeaders)
    {
        boolean ret = false;
        try
        {
            //获取页面token,余票token
            String confirmOrderUrl = "https://dynamic.12306.cn/otsweb/order/confirmPassengerAction.do?method=init";
            Header[] headers = HttpUtil.getRequestHeader(Constant.URL_4_REFERER_ORDER,
                    cookieHeaders);
            HttpResponse response = HttpUtil.sendGet(confirmOrderUrl, headers);
            String content = CommonUtil.getContent(response.getEntity()
                    .getContent());
            System.out.println("content:" + content);
            String strutsToken = CommonUtil.getStrutsToken(content);
            System.out.println("strutsToken:" + strutsToken);
        }
        catch (Exception e)
        {
            
        }
        return ret;
    }
    
    public static void main(String[] args) throws IOException
    {
        Header[] cookieHeaders = AtomLogic.getHeaderCookie();
        
        AtomLogic.getVerifyCode(Constant.URL_4_VERIFY_CODE, cookieHeaders);
        
        System.out.println("please input:");
        
        InputStreamReader isr = new InputStreamReader(System.in);
        
        BufferedReader br = new BufferedReader(isr);
        String s = null;
        try
        {
            s = br.readLine();
            
            LoginInfo loginInfo = LoginInfo.getTestObject();
            loginInfo.setRandCode(s);
            
            boolean ret = login(loginInfo, cookieHeaders);
            System.out.println(ret);
            
            Map<String, RemainTicketInfo> map = AtomLogic.getRemanentTicket(Constant.URL_4_REFERER_ORDER,
                    TicketQryCondition.getTestObject(),
                    cookieHeaders);
            System.out.println(map);
            
            ret = doPreBookTicket(TicketQryCondition.getTestObject(),
                    map.get("K1512"),
                    cookieHeaders);
            
            System.out.println("pre book ticket :" + ret);
            
            doSubmitOrder(cookieHeaders);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            br.close();
            isr.close();
        }
    }
}
