package com.autobuyticket.core;

public class StringUtil
{
    public static boolean isEmpty(String str)
    {
        return null == str || str.trim().equals("");
    }
    
    /**
     * 
    * 返回一个字符串，对非String类型的值自动转成String 
    * <功能详细描述> 
    * @param obj
    * @return [参数说明] 
    * 
    * @return String [返回类型说明] 
    * @exception throws [违例类型] [违例说明] 
    * @see [类、类#方法、类#成员]
     */
    public static String getString(Object obj)
    {
        return getString(obj, "");
    }
    
    /**
     * 
    * 返回一个字符串，对非String类型的值自动转成String 
    * <功能详细描述> 
    * @param obj
    * @param def
    * 
    * @return String [返回类型说明] 
    * @exception throws [违例类型] [违例说明] 
    * @see [类、类#方法、类#成员]
     */
    public static String getString(Object obj, String def)
    {
        String ret = def;
        if (null != obj)
        {
            if (obj instanceof String)
            {
                ret = getString((String)obj);
            }
            else if (obj instanceof Integer)
            {
                ret = ((Integer)obj).toString();
            }
        }
        return ret;
    }
    
    /**
     * 
    * 返回一个非null的字符串 
    * <功能详细描述> 
    * @param str
    * @return [参数说明] 
    * 
    * @return String [返回类型说明] 
    * @exception throws [违例类型] [违例说明] 
    * @see [类、类#方法、类#成员]
     */
    public static String getString(String str)
    {
        return getString(str, "");
    }
    
    /**
     * 
    * 返回一个字符串,当传入字符串为null的时候返回def，否则返回str 
    * <功能详细描述> 
    * @param str
    * @param def 如果str为空的时候的返回值
    * @return [参数说明] 
    * 
    * @return String [返回类型说明] 
    * @exception throws [违例类型] [违例说明] 
    * @see [类、类#方法、类#成员]
     */
    public static String getString(String str, String def)
    {
        String ret = def;
        if (!isEmpty(str))
        {
            ret = str;
        }
        return ret;
    }
}
