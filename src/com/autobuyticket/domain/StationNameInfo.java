package com.autobuyticket.domain;

import com.autobuyticket.core.StringUtil;

public class StationNameInfo
{
    private String shortname;
    
    private String name;
    
    private String telecode;
    
    private String pinyin;
    
    private String jianma;
    
    private String id;
    
    public String getShortname()
    {
        return shortname;
    }
    
    public void setShortname(String shortname)
    {
        this.shortname = shortname;
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public String getTelecode()
    {
        return telecode;
    }
    
    public void setTelecode(String telecode)
    {
        this.telecode = telecode;
    }
    
    public String getPinyin()
    {
        return pinyin;
    }
    
    public void setPinyin(String pinyin)
    {
        this.pinyin = pinyin;
    }
    
    public String getJianma()
    {
        return jianma;
    }
    
    public void setJianma(String jianma)
    {
        this.jianma = jianma;
    }
    
    public String getId()
    {
        return id;
    }
    
    public void setId(String id)
    {
        this.id = id;
    }
    
    @Override
    public String toString()
    {
        return StringUtil.getString(shortname) + "|"
                + StringUtil.getString(name) + "|"
                + StringUtil.getString(telecode) + "|"
                + StringUtil.getString(pinyin) + "|"
                + StringUtil.getString(jianma) + "|" + StringUtil.getString(id);
    }
    
}
