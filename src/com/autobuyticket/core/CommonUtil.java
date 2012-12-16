package com.autobuyticket.core;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.SystemUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.autobuyticket.annotation.ParamMapping;

public class CommonUtil
{
    /**
     * 
    * 从输入流中获取数据 
    * <功能详细描述> 
    * @param is
    * @return
    * @throws IOException [参数说明] 
    * 
    * @return String [返回类型说明] 
    * @exception throws [违例类型] [违例说明] 
    * @see [类、类#方法、类#成员]
     */
    public static String getContent(InputStream is) throws IOException
    {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line = "";
        while ((line = br.readLine()) != null)
        {
            sb.append(line);
            sb.append("\n");
        }
        close(is, br);
        return sb.toString();
    }
    
    /**
     * 
    * 将字符串写入缓存文件 
    * <功能详细描述> 
    * @param file
    * @param str [参数说明] 
    * 
    * @return void [返回类型说明] 
    * @exception throws [违例类型] [违例说明] 
    * @see [类、类#方法、类#成员]
     */
    public static void writeCacheFile(String file, String str)
    {
        writeCacheFile(new File(SystemUtils.getUserDir(), file), str);
    }
    
    /**
     * 
    * 将字符串写入缓存文件  
    * <功能详细描述> 
    * @param file
    * @param str [参数说明] 
    * 
    * @return void [返回类型说明] 
    * @exception throws [违例类型] [违例说明] 
    * @see [类、类#方法、类#成员]
     */
    public static void writeCacheFile(File file, String str)
    {
        FileWriter fw = null;
        try
        {
            fw = new FileWriter(file);
            fw.write(str);
            fw.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (null != fw)
            {
                try
                {
                    fw.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
        
    }
    
    private static void close(Closeable... closeables)
    {
        if (closeables != null)
        {
            for (Closeable closeable : closeables)
            {
                try
                {
                    closeable.close();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
    
    /**
     * 
    * 从对象中抽取http请求的参数 
    * <功能详细描述> 
    * @param obj
    * 
    * @return List<NameValuePair> [返回类型说明] 
    * @exception throws [违例类型] [违例说明] 
    * @see [类、类#方法、类#成员]
     */
    public static List<NameValuePair> getParamList(Object obj)
    {
        return getParamList(obj, null);
    }
    
    /**
     * 
    * 从对象中抽取http请求的参数 
    * <功能详细描述> 
    * @param obj
    * @param method
    * 
    * @return List<NameValuePair> [返回类型说明] 
    * @exception throws [违例类型] [违例说明] 
    * @see [类、类#方法、类#成员]
     */
    public static List<NameValuePair> getParamList(Object obj, String method)
    {
        List<NameValuePair> ret = new ArrayList<NameValuePair>();
        if (null != obj)
        {
            Field[] fields = obj.getClass().getDeclaredFields();
            if (null != fields)
            {
                try
                {
                    for (Field field : fields)
                    {
                        ParamMapping param = field.getAnnotation(ParamMapping.class);
                        if (null != param)
                        {
                            String key = param.paramcode();
                            String mapping = param.mapping();
                            key = getParamCode(key, method, mapping);
                            
                            if (!StringUtil.isEmpty(key))
                            {
                                field.setAccessible(true);
                                String value = StringUtil.getString(field.get(obj));
                                
                                ret.add(new BasicNameValuePair(key, value));
                            }
                        }
                    }
                }
                catch (IllegalArgumentException e)
                {
                    e.printStackTrace();
                }
                catch (IllegalAccessException e)
                {
                    e.printStackTrace();
                }
            }
        }
        return ret;
    }
    
    /**
     * 
    * 计算字段对应的http请求的参数编码 
    * <功能详细描述> 
    * @param defCode
    * @param method
    * @param mapping
    * @return [参数说明] 
    * 
    * @return String [返回类型说明] 
    * @exception throws [违例类型] [违例说明] 
    * @see [类、类#方法、类#成员]
     */
    private static String getParamCode(String defCode, String method,
            String mapping)
    {
        String ret = null;
        //传入的Method和配置的Mapping都是空的时候表示要使用默认的paramcode
        if (StringUtil.isEmpty(method))
        {
            ret = defCode;
        }
        else if (!StringUtil.isEmpty(mapping) && !StringUtil.isEmpty(method))
        {
            String[] strs = mapping.split(";");
            for (String str : strs)
            {
                if (!StringUtil.isEmpty(str) && str.trim().startsWith(method))
                {
                    String[] ss = str.split("=");
                    if (ss.length < 2 || StringUtil.isEmpty(ss[1]))
                    {
                        ret = defCode;
                    }
                    else
                    {
                        ret = ss[1];
                    }
                    break;
                }
            }
        }
        return ret;
    }
    
    /**
     * 
    * 从对象中抽取http请求的参数字符串
    * <功能详细描述> 
    * @param obj
    * 
    * @return String [返回类型说明] 
    * @exception throws [违例类型] [违例说明] 
    * @see [类、类#方法、类#成员]
     */
    public static String getParamsString(Object obj)
    {
        return getParamsString(obj, null);
    }
    
    /**
     * 
    * 从对象中抽取http请求的参数字符串
    * <功能详细描述> 
    * @param obj
    * @param method
    * @return [参数说明] 
    * 
    * @return String [返回类型说明] 
    * @exception throws [违例类型] [违例说明] 
    * @see [类、类#方法、类#成员]
     */
    public static String getParamsString(Object obj, String method)
    {
        String ret = null;
        
        if (null != obj)
        {
            Field[] fields = obj.getClass().getDeclaredFields();
            if (null != fields)
            {
                try
                {
                    StringBuffer retBuf = new StringBuffer();
                    for (Field field : fields)
                    {
                        ParamMapping param = field.getAnnotation(ParamMapping.class);
                        if (null != param)
                        {
                            String key = param.paramcode();
                            String mapping = param.mapping();
                            key = getParamCode(key, method, mapping);
                            
                            if (!StringUtil.isEmpty(key))
                            {
                                field.setAccessible(true);
                                String value = StringUtil.getString(field.get(obj));
                                
                                retBuf.append("&" + key + "=" + value);
                            }
                        }
                    }
                    
                    if (retBuf.length() > 0)
                    {
                        ret = retBuf.substring(1);
                        ret = ret.replaceAll(":", "%3A");
                        ret = ret.replaceAll("#", "%23");
                    }
                }
                catch (IllegalArgumentException e)
                {
                    e.printStackTrace();
                }
                catch (IllegalAccessException e)
                {
                    e.printStackTrace();
                }
            }
        }
        return ret;
    }
    
    /**
     * 
    * 分解字符穿中用单引号包裹住的Value 
    * <功能详细描述> 
    * @param str
    * @return [参数说明] 
    * 
    * @return List<String> [返回类型说明] 
    * @exception throws [违例类型] [违例说明] 
    * @see [类、类#方法、类#成员]
     */
    public static List<String> splitValues(String str)
    {
        List<String> strlist = new ArrayList<String>();
        if (!StringUtil.isEmpty(str))
        {
            while (true)
            {
                int indx = str.indexOf("'");
                int indy = str.indexOf("'", indx + 1);
                if (indx > 0 && indy > 0)
                {
                    strlist.add(str.substring(indx + 1, indy));
                    str = str.substring(indy + 1);
                }
                else
                {
                    break;
                }
            }
        }
        
        return strlist;
    }
    
    /**
     * 
    * 获取 类似xml的尖括号之间包裹的值
    * <功能详细描述> 
    * @param str
    * @return [参数说明] 
    * 
    * @return String [返回类型说明] 
    * @exception throws [违例类型] [违例说明] 
    * @see [类、类#方法、类#成员]
     */
    public static String getXMLValue(String str)
    {
        String ret = null;
        if (!StringUtil.isEmpty(str))
        {
            ret = str.replaceAll("<[^>]*?>(.*?)", "");
        }
        return ret;
    }
    
    /**
     * 
    * 数据流管道，讲输入流数据导入到输出流中 
    * <功能详细描述> 
    * @param in
    * @param os
    * @throws IOException [参数说明] 
    * 
    * @return void [返回类型说明] 
    * @exception throws [违例类型] [违例说明] 
    * @see [类、类#方法、类#成员]
     */
    public static void streamPipeline(InputStream in, OutputStream os)
            throws IOException
    {
        byte[] buff = new byte[1024];
        int len = 0;
        while ((len = in.read(buff)) > -1)
        {
            os.write(buff, 0, len);
        }
        close(os, in);
    }
    
    /**
     * 从字符串中取出token
     */
    public static String getStrutsToken(String content)
    {
        if (content == null || content.equals(""))
        {
            return "";
        }
        Matcher m = Pattern.compile("(?is)<input .*?name=\"org.apache.struts.taglib.html.TOKEN\".*?value=\"(\\w+)\".*/?>")
                .matcher(content);
        if (m.find())
        {
            return m.group(1);
        }
        return "";
    }
    
    public static void main(String[] args)
    {
        List<String> strs = splitValues("<span id='id_550000K282A0' class='base_txtdiv' onmouseover=javascript:onStopHover('550000K282A0#NJH#XFN') onmouseout='onStopOut()'>K282<span>");
        
        System.out.println(strs);
    }
}
