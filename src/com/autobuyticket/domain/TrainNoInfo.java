package com.autobuyticket.domain;

import com.autobuyticket.core.StringUtil;

import net.sf.json.JSONObject;

public class TrainNoInfo
{
    private String end_station_name;
    
    private String end_time;
    
    private String id;
    
    private String start_station_name;
    
    private String start_time;
    
    private String train_no;
    
    public static TrainNoInfo getNewInstance(JSONObject jo)
    {
        TrainNoInfo ret = null;
        if (null != jo)
        {
            ret = new TrainNoInfo();
            ret.setEnd_station_name(jo.getString("end_station_name"));
            ret.setEnd_time(jo.getString("end_time"));
            ret.setId(jo.getString("id"));
            ret.setStart_station_name(jo.getString("start_station_name"));
            ret.setStart_time(jo.getString("start_time"));
            ret.setTrain_no(jo.getString("value"));
        }
        return ret;
    }
    
    public String getEnd_station_name()
    {
        return end_station_name;
    }
    
    public void setEnd_station_name(String end_station_name)
    {
        this.end_station_name = end_station_name;
    }
    
    public String getEnd_time()
    {
        return end_time;
    }
    
    public void setEnd_time(String end_time)
    {
        this.end_time = end_time;
    }
    
    public String getId()
    {
        return id;
    }
    
    public void setId(String id)
    {
        this.id = id;
    }
    
    public String getStart_station_name()
    {
        return start_station_name;
    }
    
    public void setStart_station_name(String start_station_name)
    {
        this.start_station_name = start_station_name;
    }
    
    public String getStart_time()
    {
        return start_time;
    }
    
    public void setStart_time(String start_time)
    {
        this.start_time = start_time;
    }
    
    public String getTrain_no()
    {
        return train_no;
    }
    
    public void setTrain_no(String train_no)
    {
        this.train_no = train_no;
    }
    
    @Override
    public String toString()
    {
        return "{\"end_station_name\":\""
                + StringUtil.getString(end_station_name) + "\",\"end_time\":\""
                + StringUtil.getString(end_time) + "\",\"id\":\""
                + StringUtil.getString(id) + "\",\"start_station_name\":\""
                + StringUtil.getString(start_station_name)
                + "\",\"start_time\":\"" + StringUtil.getString(start_time)
                + "\",\"value\":\"" + StringUtil.getString(train_no) + "\"}";
    }
    
}
