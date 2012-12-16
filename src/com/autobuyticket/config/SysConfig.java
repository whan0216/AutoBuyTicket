package com.autobuyticket.config;

import org.apache.commons.lang.SystemUtils;

public class SysConfig
{
    private String cache_path = SystemUtils.getUserDir().getPath();
    
    private static SysConfig instance = null;
    
    public static SysConfig getDefConfig()
    {
        if (null == instance)
        {
            instance = new SysConfig();
        }
        return instance;
    }
    
    public String getCache_path()
    {
        return cache_path;
    }
    
    public void setCache_path(String cache_path)
    {
        this.cache_path = cache_path;
    }
}
