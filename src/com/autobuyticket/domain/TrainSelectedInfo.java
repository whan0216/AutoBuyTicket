package com.autobuyticket.domain;

import com.autobuyticket.annotation.ParamMapping;

public class TrainSelectedInfo
{
    @ParamMapping(paramcode = "station_train_code", mapping = "submutOrderRequest=")
    private String station_train_code;
    
    @ParamMapping(paramcode = "lishi", mapping = "submutOrderRequest=")
    private String lishi;
    
    @ParamMapping(paramcode = "train_start_time", mapping = "submutOrderRequest=")
    private String starttime;
    
    @ParamMapping(paramcode = "trainno4", mapping = "submutOrderRequest=")
    private String trainno;
    
    @ParamMapping(paramcode = "from_station_telecode", mapping = "submutOrderRequest=")
    private String from_station_telecode;
    
    @ParamMapping(paramcode = "to_station_telecode", mapping = "submutOrderRequest=")
    private String to_station_telecode;
    
    @ParamMapping(paramcode = "arrive_time", mapping = "submutOrderRequest=")
    private String arrive_time;
    
    @ParamMapping(paramcode = "from_station_name", mapping = "submutOrderRequest=")
    private String from_station_name;
    
    @ParamMapping(paramcode = "to_station_name", mapping = "submutOrderRequest=")
    private String to_station_name;
    
    @ParamMapping(paramcode = "from_station_no", mapping = "submutOrderRequest=")
    private String from_station_no;
    
    @ParamMapping(paramcode = "to_station_no", mapping = "submutOrderRequest=")
    private String to_station_no;
    
    @ParamMapping(paramcode = "ypInfoDetail", mapping = "submutOrderRequest=")
    private String ypInfoDetail;
    
    @ParamMapping(paramcode = "mmStr", mapping = "submutOrderRequest=")
    private String mmStr;
    
    public TrainSelectedInfo(String selectStr)
    {
        String[] selectStr_arr = selectStr.split("#");
        station_train_code = selectStr_arr[0];
        lishi = selectStr_arr[1];
        starttime = selectStr_arr[2];
        trainno = selectStr_arr[3];
        from_station_telecode = selectStr_arr[4];
        to_station_telecode = selectStr_arr[5];
        arrive_time = selectStr_arr[6];
        from_station_name = selectStr_arr[7];
        to_station_name = selectStr_arr[8];
        from_station_no = selectStr_arr[9];
        to_station_no = selectStr_arr[10];
        ypInfoDetail = selectStr_arr[11];
        mmStr = selectStr_arr[12];
    }
    
    public String getStation_train_code()
    {
        return station_train_code;
    }
    
    public void setStation_train_code(String station_train_code)
    {
        this.station_train_code = station_train_code;
    }
    
    public String getLishi()
    {
        return lishi;
    }
    
    public void setLishi(String lishi)
    {
        this.lishi = lishi;
    }
    
    public String getStarttime()
    {
        return starttime;
    }
    
    public void setStarttime(String starttime)
    {
        this.starttime = starttime;
    }
    
    public String getTrainno()
    {
        return trainno;
    }
    
    public void setTrainno(String trainno)
    {
        this.trainno = trainno;
    }
    
    public String getFrom_station_telecode()
    {
        return from_station_telecode;
    }
    
    public void setFrom_station_telecode(String from_station_telecode)
    {
        this.from_station_telecode = from_station_telecode;
    }
    
    public String getTo_station_telecode()
    {
        return to_station_telecode;
    }
    
    public void setTo_station_telecode(String to_station_telecode)
    {
        this.to_station_telecode = to_station_telecode;
    }
    
    public String getArrive_time()
    {
        return arrive_time;
    }
    
    public void setArrive_time(String arrive_time)
    {
        this.arrive_time = arrive_time;
    }
    
    public String getFrom_station_name()
    {
        return from_station_name;
    }
    
    public void setFrom_station_name(String from_station_name)
    {
        this.from_station_name = from_station_name;
    }
    
    public String getTo_station_name()
    {
        return to_station_name;
    }
    
    public void setTo_station_name(String to_station_name)
    {
        this.to_station_name = to_station_name;
    }
    
    public String getFrom_station_no()
    {
        return from_station_no;
    }
    
    public void setFrom_station_no(String from_station_no)
    {
        this.from_station_no = from_station_no;
    }
    
    public String getTo_station_no()
    {
        return to_station_no;
    }
    
    public void setTo_station_no(String to_station_no)
    {
        this.to_station_no = to_station_no;
    }
    
    public String getYpInfoDetail()
    {
        return ypInfoDetail;
    }
    
    public void setYpInfoDetail(String ypInfoDetail)
    {
        this.ypInfoDetail = ypInfoDetail;
    }
    
    public String getMmStr()
    {
        return mmStr;
    }
    
    public void setMmStr(String mmStr)
    {
        this.mmStr = mmStr;
    }
}
