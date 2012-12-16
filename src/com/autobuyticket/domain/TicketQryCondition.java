package com.autobuyticket.domain;

import com.autobuyticket.annotation.ParamMapping;

public class TicketQryCondition
{
    /**
     * 出发地
     */
    @ParamMapping(paramcode = "from_station_telecode_name", mapping = "submutOrderRequest=")
    private String from_station;
    
    @ParamMapping(paramcode = "orderRequest.from_station_telecode", mapping = "queryLeftTicket=;queryststrainall=fromstation")
    private String from_station_code;
    
    /**
     * 目的地
     */
    @ParamMapping(paramcode = "to_station_telecode_name", mapping = "submutOrderRequest=")
    private String to_station;
    
    @ParamMapping(paramcode = "orderRequest.to_station_telecode", mapping = "queryLeftTicket=;queryststrainall=tostation")
    private String to_station_code;
    
    /**
     * 出发日期
     */
    @ParamMapping(paramcode = "orderRequest.train_date", mapping = "queryLeftTicket=;queryststrainall=date;submutOrderRequest=train_date")
    private String start_date;
    
    /**
     * 出发时间<br>
     * 00:00--24:00<br>
     * 00:00--06:00<br>
     * 06:00--12:00<br>
     * 12:00--18:00<br>
     * 18:00--24:00<br>
     */
    @ParamMapping(paramcode = "orderRequest.start_time_str", mapping = "queryLeftTicket=;queryststrainall=starttime;submutOrderRequest=start_time_str")
    private String start_time = "00:00--24:00";
    
    /**
     * 出发车次
     */
    @ParamMapping(paramcode = "orderRequest.train_no", mapping = "queryLeftTicket=;")
    private String train_number;
    
    /**
     * 列车类型 ：全部 动车 Z字头 T字头 K字头 其他 QB#D#Z#T#K#QT#
     */
    @ParamMapping(paramcode = "trainClass", mapping = "queryLeftTicket=;submutOrderRequest=train_class_arr")
    private String train_type = "QB#D#Z#T#K#QT#";
    
    /**
     * 列车过站类型 ： 全部 始发 过路 QB SF GL
     */
    @ParamMapping(paramcode = "trainPassType", mapping = "queryLeftTicket=;submutOrderRequest=")
    private String train_pass_type = "QB";
    
    /**
     * 查询方式：普通查询 学生票查询 民工团体查询  00 0X00 1F
     */
    @ParamMapping(paramcode = "includeStudent", mapping = "queryLeftTicket=;submutOrderRequest=")
    private String qry_type = "00";
    
    /**
     * 席别和数量：暂时没发现在哪里使用<br>
     * 获取席别、数量的集合，采用席别#数量@
     */
    @ParamMapping(paramcode = "seatTypeAndNum", mapping = "queryLeftTicket=;submutOrderRequest=seattype_num")
    private String seatTypeAndNum;
    
    //------------- 以下三个条件貌似查询的时候不会使用，但是选择 往返的时候需要填写
    /**
     * 返程日期
     */
    @ParamMapping(paramcode = "round_train_date", mapping = "submutOrderRequest=")
    private String round_train_date;
    
    /**
     * 返程时间
     */
    @ParamMapping(paramcode = "round_start_time_str", mapping = "submutOrderRequest=")
    private String round_start_time_str;
    
    /**
     * 单程、返程类型： 单程 1 往返 2
     */
    @ParamMapping(paramcode = "single_round_type", mapping = "submutOrderRequest=")
    private String single_round_type = "1";
    
    public String getRound_train_date()
    {
        return round_train_date;
    }
    
    public void setRound_train_date(String round_train_date)
    {
        this.round_train_date = round_train_date;
    }
    
    public String getRound_start_time_str()
    {
        return round_start_time_str;
    }
    
    public void setRound_start_time_str(String round_start_time_str)
    {
        this.round_start_time_str = round_start_time_str;
    }
    
    public String getSingle_round_type()
    {
        return single_round_type;
    }
    
    public void setSingle_round_type(String single_round_type)
    {
        this.single_round_type = single_round_type;
    }
    
    /**
     * 
     */
    public static TicketQryCondition getTestObject()
    {
        TicketQryCondition ret = new TicketQryCondition();
        ret.setFrom_station("南京");
        ret.setTo_station("襄阳");
        ret.setFrom_station_code("NJH");
        ret.setStart_date("2012-12-21");
        ret.setTo_station_code("XFN");
        ret.setTrain_number("");
        return ret;
    }
    
    public String getSeatTypeAndNum()
    {
        return seatTypeAndNum;
    }
    
    public void setSeatTypeAndNum(String seatTypeAndNum)
    {
        this.seatTypeAndNum = seatTypeAndNum;
    }
    
    public String getFrom_station()
    {
        return from_station;
    }
    
    public void setFrom_station(String from_station)
    {
        this.from_station = from_station;
    }
    
    public String getTo_station()
    {
        return to_station;
    }
    
    public void setTo_station(String to_station)
    {
        this.to_station = to_station;
    }
    
    public String getStart_date()
    {
        return start_date;
    }
    
    public void setStart_date(String start_date)
    {
        this.start_date = start_date;
    }
    
    public String getStart_time()
    {
        return start_time;
    }
    
    public void setStart_time(String start_time)
    {
        this.start_time = start_time;
    }
    
    public String getTrain_number()
    {
        return train_number;
    }
    
    public void setTrain_number(String train_number)
    {
        this.train_number = train_number;
    }
    
    public String getTrain_type()
    {
        return train_type;
    }
    
    public void setTrain_type(String train_type)
    {
        this.train_type = train_type;
    }
    
    public String getTrain_pass_type()
    {
        return train_pass_type;
    }
    
    public void setTrain_pass_type(String train_pass_type)
    {
        this.train_pass_type = train_pass_type;
    }
    
    public String getQry_type()
    {
        return qry_type;
    }
    
    public void setQry_type(String qry_type)
    {
        this.qry_type = qry_type;
    }
    
    public String getFrom_station_code()
    {
        return from_station_code;
    }
    
    public void setFrom_station_code(String from_station_code)
    {
        this.from_station_code = from_station_code;
    }
    
    public String getTo_station_code()
    {
        return to_station_code;
    }
    
    public void setTo_station_code(String to_station_code)
    {
        this.to_station_code = to_station_code;
    }
    
}
