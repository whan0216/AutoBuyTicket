package com.autobuyticket.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.Consts;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.RequestLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;

import com.autobuyticket.core.HttpClientFactory.ClientType;

public class HttpUtil
{
    
    public static HttpResponse sendPost(String url,
            Map<String, String> dataMap, Header... headers) throws Exception
    {
        HttpPost post = new HttpPost(url);
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        if (dataMap != null && dataMap.size() > 0)
        {
            for (Map.Entry<String, String> entry : dataMap.entrySet())
            {
                String key = entry.getKey();
                String value = entry.getValue();
                nvps.add(new BasicNameValuePair(key, value));
            }
        }
        return sendRequest(post, nvps, headers);
    }
    
    public static HttpResponse sendPost(String url, List<NameValuePair> nvps,
            Header... headers) throws Exception
    {
        HttpPost post = new HttpPost(url);
        return sendRequest(post, nvps, headers);
    }
    
    public static HttpResponse sendGet(String url) throws Exception
    {
        HttpGet get = new HttpGet(url);
        return sendRequest(get, null);
    }
    
    public static HttpResponse sendGet(String url, Header... headers)
            throws Exception
    {
        HttpGet get = new HttpGet(url);
        return sendRequest(get, null, headers);
    }
    
    private static HttpResponse sendRequest(HttpRequestBase request,
            List<NameValuePair> nvps) throws Exception
    {
        return sendRequest(request, nvps, null);
    }
    
    private static HttpResponse sendRequest(HttpRequestBase request,
            List<NameValuePair> nvps, Header... headers) throws Exception
    {
        RequestLine requestLine = request.getRequestLine();
        HttpClient httpclient = null;
        if (requestLine.getUri().toLowerCase().startsWith("https"))
        {
            httpclient = HttpClientFactory.getHttpClient(ClientType.HTTPS);
        }
        else
        {
            httpclient = HttpClientFactory.getHttpClient(ClientType.HTTP);
        }
        
        if (nvps != null && !nvps.isEmpty())
        {
            ((HttpPost)request).setEntity(new UrlEncodedFormEntity(nvps,
                    Consts.UTF_8));
            request.setHeader("Content-Length", ""
                    + ((HttpPost)request).getEntity().getContentLength());
            request.setHeader("Content-Type",
                    "application/x-www-form-urlencoded");
            
        }
        
        if (headers != null && headers.length > 0)
        {
            request.setHeaders(headers);
        }
        return httpclient.execute(request);
    }
    
    public static Header[] getRequestCookieHeader(HttpResponse response)
    {
        Header[] responseHeaders = response.getHeaders("Set-Cookie");
        if (responseHeaders == null || responseHeaders.length <= 0)
        {
            return null;
        }
        Header[] requestCookies = new BasicHeader[responseHeaders.length];
        for (int i = 0; i < responseHeaders.length; i++)
        {
            requestCookies[i] = new BasicHeader("Cookie",
                    responseHeaders[i].getValue());
        }
        return requestCookies;
    }
    
    /**
     * 
    * 拼装http请求的header 
    * <功能详细描述> 
    * @param referer
    * @param cookieHeaders
    * @return [参数说明] 
    * 
    * @return Header[] [返回类型说明] 
    * @exception throws [违例类型] [违例说明] 
    * @see [类、类#方法、类#成员]
     */
    public static Header[] getRequestHeader(String referer,
            Header[] cookieHeaders)
    {
        if (!StringUtil.isEmpty(referer))
        {
            Header referHeader = new BasicHeader("Referer", referer);
            
            Header[] headers = new Header[1 + cookieHeaders.length];
            headers[0] = referHeader;
            for (int i = 0; i < cookieHeaders.length; i++)
            {
                headers[i + 1] = cookieHeaders[i];
            }
            
            return headers;
        }
        else
        {
            return cookieHeaders;
        }
    }
}
