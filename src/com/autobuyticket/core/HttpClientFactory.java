package com.autobuyticket.core;

import java.io.IOException;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HttpContext;

public class HttpClientFactory
{
    public static final String HTTP = "http";
    
    //联网方式
    public enum ClientType
    {
        HTTP, HTTPS
    }
    
    public static HttpClient getHttpClient(HttpClientFactory.ClientType type)
            throws Exception
    {
        if (ClientType.HTTP == type)
        {
            return getHttpClient();
        }
        else
        {
            return getHttpsClient();
        }
    }
    
    public static HttpClient getHttpClient() throws Exception
    {
        DefaultHttpClient httpclient = new DefaultHttpClient();
        httpclient.addRequestInterceptor(new HttpRequestInterceptor()
        {
            public void process(final HttpRequest request,
                    final HttpContext context) throws HttpException,
                    IOException
            {
                if (!request.containsHeader("Accept"))
                {
                    request.addHeader("Accept", "*/*");
                }
                if (request.containsHeader("User-Agent"))
                {
                    request.removeHeaders("User-Agent");
                }
                if (request.containsHeader("Connection"))
                {
                    request.removeHeaders("Connection");
                }
                if (request.containsHeader("Host"))
                {
                    request.removeHeaders("Host");
                }
                request.addHeader("Host", "www.12306.cn");
                request.addHeader("User-Agent",
                        "Mozilla/5.0 (Windows NT 5.1; rv:8.0) Gecko/20100101 Firefox/8.0");
                request.addHeader("Connection", "keep-alive");
            }
        });
        return httpclient;
    }
    
    public static HttpClient getHttpsClient() throws Exception
    {
        DefaultHttpClient httpclient = new DefaultHttpClient();
        TrustManager easyTrustManager = new X509TrustManager()
        {
            public void checkClientTrusted(
                    java.security.cert.X509Certificate[] x509Certificates,
                    String s) throws java.security.cert.CertificateException
            {
            }
            
            public void checkServerTrusted(
                    java.security.cert.X509Certificate[] x509Certificates,
                    String s) throws java.security.cert.CertificateException
            {
            }
            
            public java.security.cert.X509Certificate[] getAcceptedIssuers()
            {
                return null;
            }
        };
        SSLContext sslcontext = SSLContext.getInstance("TLS");
        sslcontext.init(null, new TrustManager[] {easyTrustManager}, null);
        SSLSocketFactory sf = new SSLSocketFactory(sslcontext);
        Scheme sch = new Scheme("https", 443, sf);
        httpclient.getConnectionManager().getSchemeRegistry().register(sch);
        
        httpclient.addRequestInterceptor(new HttpRequestInterceptor()
        {
            public void process(final HttpRequest request,
                    final HttpContext context) throws HttpException,
                    IOException
            {
                if (!request.containsHeader("Accept"))
                {
                    request.addHeader("Accept", "*/*");
                }
                if (request.containsHeader("User-Agent"))
                {
                    request.removeHeaders("User-Agent");
                }
                if (request.containsHeader("Connection"))
                {
                    request.removeHeaders("Connection");
                }
                if (request.containsHeader("Host"))
                {
                    request.removeHeaders("Host");
                }
                request.addHeader("Host", "dynamic.12306.cn");
                request.addHeader("User-Agent",
                        "Mozilla/5.0 (Windows NT 5.1; rv:8.0) Gecko/20100101 Firefox/8.0");
                request.addHeader("Connection", "keep-alive");
            }
        });
        return httpclient;
    }
}
