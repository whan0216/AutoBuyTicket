package com.autobuyticket.domain;

public class RemainTicketInfo
{
    /**
     * 车次id
     */
    private String id;
    
    /**
     * 列车编号
     */
    private String train_code;
    
    /**
     * 发站名称
     */
    private String from_station_name;
    
    /**
     * 发站编码
     */
    private String from_station_code;
    
    /**
     * 发站时间
     */
    private String from_station_time;
    
    /**
     * 到站名称
     */
    private String to_station_name;
    
    /**
     *  到站编码
     */
    private String to_station_code;
    
    /**
     * 到站时间
     */
    private String to_station_time;
    
    /**
     * 历时
     */
    private String lishi;
    
    /**
     * 商务座
     */
    private String business_ticket;
    
    /**
     * 特等座
     */
    private String vip_ticket;
    
    /**
     * 一等座
     */
    private String one_level_ticket;
    
    /**
     * 二等座
     */
    private String two_level_ticket;
    
    /**
     * 高级软卧
     */
    private String high_soft_berth_ticket;
    
    /**
     * 软卧
     */
    private String soft_berth_ticket;
    
    /**
     * 硬卧
     */
    private String hard_berth_ticket;
    
    /**
     * 软座
     */
    private String soft_seat_ticket;
    
    /**
     * 硬座
     */
    private String hard_seat_ticket;
    
    /**
     * 无座位
     */
    private String no_seat_ticket;
    
    /**
     * 其他
     */
    private String other_seat_ticket;
    
    /**
     * 其他信息
     */
    private String tagInfo;
    
    private TrainSelectedInfo selectedInfo;
    
    public String getOther_seat_ticket()
    {
        return other_seat_ticket;
    }
    
    public void setOther_seat_ticket(String other_seat_ticket)
    {
        this.other_seat_ticket = other_seat_ticket;
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
    
    public String getId()
    {
        return id;
    }
    
    public void setId(String id)
    {
        this.id = id;
    }
    
    public String getTrain_code()
    {
        return train_code;
    }
    
    public void setTrain_code(String train_code)
    {
        this.train_code = train_code;
    }
    
    public String getFrom_station_name()
    {
        return from_station_name;
    }
    
    public void setFrom_station_name(String from_station_name)
    {
        this.from_station_name = from_station_name;
    }
    
    public String getFrom_station_time()
    {
        return from_station_time;
    }
    
    public void setFrom_station_time(String from_station_time)
    {
        this.from_station_time = from_station_time;
    }
    
    public String getTo_station_name()
    {
        return to_station_name;
    }
    
    public void setTo_station_name(String to_station_name)
    {
        this.to_station_name = to_station_name;
    }
    
    public String getTo_station_time()
    {
        return to_station_time;
    }
    
    public void setTo_station_time(String to_station_time)
    {
        this.to_station_time = to_station_time;
    }
    
    public String getLishi()
    {
        return lishi;
    }
    
    public void setLishi(String lishi)
    {
        this.lishi = lishi;
    }
    
    public String getBusiness_ticket()
    {
        return business_ticket;
    }
    
    public void setBusiness_ticket(String business_ticket)
    {
        this.business_ticket = business_ticket;
    }
    
    public String getVip_ticket()
    {
        return vip_ticket;
    }
    
    public void setVip_ticket(String vip_ticket)
    {
        this.vip_ticket = vip_ticket;
    }
    
    public String getOne_level_ticket()
    {
        return one_level_ticket;
    }
    
    public void setOne_level_ticket(String one_level_ticket)
    {
        this.one_level_ticket = one_level_ticket;
    }
    
    public String getTwo_level_ticket()
    {
        return two_level_ticket;
    }
    
    public void setTwo_level_ticket(String two_level_ticket)
    {
        this.two_level_ticket = two_level_ticket;
    }
    
    public String getHigh_soft_berth_ticket()
    {
        return high_soft_berth_ticket;
    }
    
    public void setHigh_soft_berth_ticket(String high_soft_berth_ticket)
    {
        this.high_soft_berth_ticket = high_soft_berth_ticket;
    }
    
    public String getSoft_berth_ticket()
    {
        return soft_berth_ticket;
    }
    
    public void setSoft_berth_ticket(String soft_berth_ticket)
    {
        this.soft_berth_ticket = soft_berth_ticket;
    }
    
    public String getHard_berth_ticket()
    {
        return hard_berth_ticket;
    }
    
    public void setHard_berth_ticket(String hard_berth_ticket)
    {
        this.hard_berth_ticket = hard_berth_ticket;
    }
    
    public String getSoft_seat_ticket()
    {
        return soft_seat_ticket;
    }
    
    public void setSoft_seat_ticket(String soft_seat_ticket)
    {
        this.soft_seat_ticket = soft_seat_ticket;
    }
    
    public String getHard_seat_ticket()
    {
        return hard_seat_ticket;
    }
    
    public void setHard_seat_ticket(String hard_seat_ticket)
    {
        this.hard_seat_ticket = hard_seat_ticket;
    }
    
    public String getNo_seat_ticket()
    {
        return no_seat_ticket;
    }
    
    public void setNo_seat_ticket(String no_seat_ticket)
    {
        this.no_seat_ticket = no_seat_ticket;
    }
    
    public String getTagInfo()
    {
        return tagInfo;
    }
    
    public void setTagInfo(String tagInfo)
    {
        this.tagInfo = tagInfo;
    }
    
    public void setSelectedInfo(TrainSelectedInfo selectedInfo)
    {
        this.selectedInfo = selectedInfo;
    }
    
    public TrainSelectedInfo getSelectedInfo()
    {
        return selectedInfo;
    }
    
}
