package com.autobuyticket.domain;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

public class PassengerInfo
{
    private String first_letter;

    private String isUserSelf;

    private String mobile_no;

    private String old_passenger_id_no;

    private String old_passenger_id_type_code;

    private String old_passenger_name;

    private String passenger_flag = "0";

    private String passenger_id_no;

    private String passenger_id_type_code = "1";

    private String passenger_id_type_name;

    private String passenger_name;

    private String passenger_type = "1";

    private String passenger_type_name;

    private String recordCount;

    private String seat_type;

    private String seat_detail_select = "0";

    private String seat_detail = "0";

    private String add_passenger = "N";

    public static PassengerInfo getTestObject()
    {
        PassengerInfo ret = new PassengerInfo();
        ret.setFirst_letter("");
        return ret;
    }

    public static PassengerInfo getNewPassengerInfo(JSONObject jObj)
    {
        PassengerInfo ret = new PassengerInfo();
        ret.setFirst_letter(jObj.getString("first_letter"));
        ret.setIsUserSelf(jObj.getString("isUserSelf"));
        ret.setMobile_no(jObj.getString("mobile_no"));
        ret.setOld_passenger_id_no(jObj.getString("old_passenger_id_no"));
        ret.setOld_passenger_id_type_code(jObj.getString("old_passenger_id_type_code"));
        ret.setOld_passenger_name(jObj.getString("old_passenger_name"));
        ret.setPassenger_flag(jObj.getString("passenger_flag"));
        ret.setPassenger_id_no(jObj.getString("passenger_id_no"));
        ret.setPassenger_id_type_code(jObj.getString("passenger_id_type_code"));
        ret.setPassenger_id_type_name(jObj.getString("passenger_id_type_name"));
        ret.setPassenger_name(jObj.getString("passenger_name"));
        ret.setPassenger_type(jObj.getString("passenger_type"));
        ret.setPassenger_type_name(jObj.getString("passenger_type_name"));
        ret.setRecordCount(jObj.getString("recordCount"));

        return ret;
    }

    public List<NameValuePair> getNameValuePairs(int index)
    {
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("passengerTickets", seat_type + "," + seat_detail_select + "," + passenger_type
                + "," + passenger_name + "," + passenger_id_type_code + "," + passenger_id_no + "," + mobile_no + ","
                + add_passenger));

        nvps.add(new BasicNameValuePair("passenger_" + index + "_seat", seat_type));
        nvps.add(new BasicNameValuePair("passenger_" + index + "_seat_detail_select", seat_detail_select));
        nvps.add(new BasicNameValuePair("passenger_" + index + "_seat_detail", seat_detail));
        nvps.add(new BasicNameValuePair("passenger_" + index + "_ticket", passenger_type));
        nvps.add(new BasicNameValuePair("passenger_" + index + "_name", passenger_name));
        nvps.add(new BasicNameValuePair("passenger_" + index + "_cardtype", passenger_id_type_code));
        nvps.add(new BasicNameValuePair("passenger_" + index + "_cardno", passenger_id_no));
        nvps.add(new BasicNameValuePair("passenger_" + index + "_mobileno", mobile_no));
        if ("Y".equals(add_passenger))
        {
            nvps.add(new BasicNameValuePair("oldPassengers", passenger_name + "," + passenger_id_type_code + ","
                    + passenger_id_no));
            nvps.add(new BasicNameValuePair("checkbox9", "Y"));
        }
        else
        {
            nvps.add(new BasicNameValuePair("oldPassengers", ""));
        }
        return nvps;
    }

    public String getSeat_detail_select()
    {
        return seat_detail_select;
    }

    public void setSeat_detail_select(String seat_detail_select)
    {
        this.seat_detail_select = seat_detail_select;
    }

    public String getSeat_detail()
    {
        return seat_detail;
    }

    public void setSeat_detail(String seat_detail)
    {
        this.seat_detail = seat_detail;
    }

    public String getFirst_letter()
    {
        return first_letter;
    }

    public void setFirst_letter(String first_letter)
    {
        this.first_letter = first_letter;
    }

    public String getIsUserSelf()
    {
        return isUserSelf;
    }

    public void setIsUserSelf(String isUserSelf)
    {
        this.isUserSelf = isUserSelf;
    }

    public String getMobile_no()
    {
        return mobile_no;
    }

    public void setMobile_no(String mobile_no)
    {
        this.mobile_no = mobile_no;
    }

    public String getOld_passenger_id_no()
    {
        return old_passenger_id_no;
    }

    public void setOld_passenger_id_no(String old_passenger_id_no)
    {
        this.old_passenger_id_no = old_passenger_id_no;
    }

    public String getOld_passenger_id_type_code()
    {
        return old_passenger_id_type_code;
    }

    public void setOld_passenger_id_type_code(String old_passenger_id_type_code)
    {
        this.old_passenger_id_type_code = old_passenger_id_type_code;
    }

    public String getOld_passenger_name()
    {
        return old_passenger_name;
    }

    public void setOld_passenger_name(String old_passenger_name)
    {
        this.old_passenger_name = old_passenger_name;
    }

    public String getPassenger_flag()
    {
        return passenger_flag;
    }

    public void setPassenger_flag(String passenger_flag)
    {
        this.passenger_flag = passenger_flag;
    }

    public String getPassenger_id_no()
    {
        return passenger_id_no;
    }

    public void setPassenger_id_no(String passenger_id_no)
    {
        this.passenger_id_no = passenger_id_no;
    }

    public String getPassenger_id_type_code()
    {
        return passenger_id_type_code;
    }

    public void setPassenger_id_type_code(String passenger_id_type_code)
    {
        this.passenger_id_type_code = passenger_id_type_code;
    }

    public String getPassenger_id_type_name()
    {
        return passenger_id_type_name;
    }

    public void setPassenger_id_type_name(String passenger_id_type_name)
    {
        this.passenger_id_type_name = passenger_id_type_name;
    }

    public String getPassenger_name()
    {
        return passenger_name;
    }

    public void setPassenger_name(String passenger_name)
    {
        this.passenger_name = passenger_name;
    }

    public String getPassenger_type()
    {
        return passenger_type;
    }

    public void setPassenger_type(String passenger_type)
    {
        this.passenger_type = passenger_type;
    }

    public String getPassenger_type_name()
    {
        return passenger_type_name;
    }

    public void setPassenger_type_name(String passenger_type_name)
    {
        this.passenger_type_name = passenger_type_name;
    }

    public String getRecordCount()
    {
        return recordCount;
    }

    public void setRecordCount(String recordCount)
    {
        this.recordCount = recordCount;
    }

    public String getSeat_type()
    {
        return seat_type;
    }

    public void setSeat_type(String seat_type)
    {
        this.seat_type = seat_type;
    }

}
