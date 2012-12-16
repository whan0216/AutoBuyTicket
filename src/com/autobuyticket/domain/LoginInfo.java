package com.autobuyticket.domain;

import com.autobuyticket.annotation.ParamMapping;

public class LoginInfo
{
    /**
     * 随机码
     */
    @ParamMapping(paramcode = "loginRand")
    private String loginRand;
    
    /**
     * 是否退款登陆
     */
    @ParamMapping(paramcode = "refundLogin")
    private String refundLogin = "N";
    
    /**
     * 是否可退款，猜测的，界面没有输入的地方
     */
    @ParamMapping(paramcode = "refundFlag")
    private String refundFlag = "Y";
    
    /**
     * 用户名
     */
    @ParamMapping(paramcode = "loginUser.user_name")
    private String user_name;
    
    @ParamMapping(paramcode = "nameErrorFocus")
    private String nameErrorFocus;
    
    /**
     * 密码
     */
    @ParamMapping(paramcode = "user.password")
    private String password;
    
    @ParamMapping(paramcode = "passwordErrorFocus")
    private String passwordErrorFocus;
    
    /**
     * 验证码
     */
    @ParamMapping(paramcode = "randCode")
    private String randCode;
    
    @ParamMapping(paramcode = "randErrorFocus")
    private String randErrorFocus;
    
    public static LoginInfo getTestObject()
    {
        LoginInfo ret = new LoginInfo();
        ret.setUser_name("richard0216");
        ret.setPassword("68060227");
        return ret;
    }
    
    public String getLoginRand()
    {
        return loginRand;
    }
    
    public void setLoginRand(String loginRand)
    {
        this.loginRand = loginRand;
    }
    
    public String getRefundLogin()
    {
        return refundLogin;
    }
    
    public void setRefundLogin(String refundLogin)
    {
        this.refundLogin = refundLogin;
    }
    
    public String getRefundFlag()
    {
        return refundFlag;
    }
    
    public void setRefundFlag(String refundFlag)
    {
        this.refundFlag = refundFlag;
    }
    
    public String getUser_name()
    {
        return user_name;
    }
    
    public void setUser_name(String user_name)
    {
        this.user_name = user_name;
    }
    
    public String getNameErrorFocus()
    {
        return nameErrorFocus;
    }
    
    public void setNameErrorFocus(String nameErrorFocus)
    {
        this.nameErrorFocus = nameErrorFocus;
    }
    
    public String getPassword()
    {
        return password;
    }
    
    public void setPassword(String password)
    {
        this.password = password;
    }
    
    public String getPasswordErrorFocus()
    {
        return passwordErrorFocus;
    }
    
    public void setPasswordErrorFocus(String passwordErrorFocus)
    {
        this.passwordErrorFocus = passwordErrorFocus;
    }
    
    public String getRandCode()
    {
        return randCode;
    }
    
    public void setRandCode(String randCode)
    {
        this.randCode = randCode;
    }
    
    public String getRandErrorFocus()
    {
        return randErrorFocus;
    }
    
    public void setRandErrorFocus(String randErrorFocus)
    {
        this.randErrorFocus = randErrorFocus;
    }
    
}
