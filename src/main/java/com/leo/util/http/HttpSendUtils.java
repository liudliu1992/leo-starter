package com.leo.util.http;

import com.leo.util.exception.HttpProcessException;
import com.leo.util.http.builder.HttpClientBuild;
import com.leo.util.http.common.HttpMethods;
import com.leo.util.http.common.Utils;
import com.leo.util.lang.HttpConsts;
import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * http请求工具类
 * @author 刘绍林
 * @create 2017-10-15 23:27
 **/
public class HttpSendUtils {

    /**
     * 默认采用的http协议的HttpClient对象
     */
    private static HttpClient client4HTTP;
    /**
     * 默认采用的https协议的HttpClient对象
     */
    private static HttpClient client4HTTPS;

    static {
        try {
            client4HTTP = HttpClientBuild.custom().build();
            client4HTTPS = HttpClientBuild.custom().ssl().build();
        } catch (HttpProcessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 请求资源或服务，自定义client对象，传入请求参数，设置内容类型，并指定参数和返回数据的编码
     *
     * @param client     client对象
     * @param url        资源地址
     * @param httpMethod 请求方法
     * @param parasMap   请求参数
     * @param headers    请求头信息
     * @param encoding   编码
     * @return 返回处理结果
     * @throws HttpProcessException 自定义http连接异常
     */
    public static String send(HttpClient client, String url, HttpMethods httpMethod, Map<String, String> parasMap,
                              Header[] headers, String encoding) throws HttpProcessException {
        String body = "";
        HttpResponse response = null;
        try {
            if (httpMethod == HttpMethods.GET && parasMap != null && !parasMap.isEmpty()) {
                url = Utils.buildParas(url, parasMap);
            }
            //创建请求对象
            HttpRequestBase request = getRequest(url, httpMethod);
            //设置header信息
            request.setHeaders(headers);

            //判断是否支持设置entity(仅HttpPost、HttpPut、HttpPatch支持)
            if (HttpEntityEnclosingRequestBase.class.isAssignableFrom(request.getClass())) {
                List<NameValuePair> nvps = new ArrayList<>();

                //检测url中是否存在参数
                url = Utils.checkHasParas(url, nvps);

                //装填参数
                Utils.map2List(nvps, parasMap);

                //设置参数到请求对象中
                ((HttpEntityEnclosingRequestBase) request).setEntity(new UrlEncodedFormEntity(nvps, encoding));
            }
            //调用发送请求
            response = execute(client, request, url, encoding);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                //按指定编码转换结果实体为String类型
                body = EntityUtils.toString(entity, encoding);
            }
            EntityUtils.consume(entity);
        } catch (UnsupportedEncodingException e) {
            throw new HttpProcessException(e);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close(response);
        }
        return body;
    }

    public static byte[] getImage(HttpClient client, HttpMethods httpMethod, String url) {
        HttpResponse response = null;
        byte[] body = null;
        //创建请求对象
        HttpRequestBase request = getRequest(url, httpMethod);
        //调用发送请求
        try {
            response = execute(client, request, url, Charset.defaultCharset().name());
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                //按指定编码转换结果实体为String类型
                body = EntityUtils.toByteArray(entity);
            }
            EntityUtils.consume(entity);
        } catch (Exception e) {
            e.printStackTrace();
        }  finally {
            close(response);
        }
        return body;
        //response = client.execute(request);
        //  return
    }

    /**
     * 请求资源或服务
     *
     * @param client   client对象
     * @param request  请求对象
     * @param url      资源地址
     * @param encoding 编码
     * @return 返回处理结果
     * @throws HttpProcessException
     */
    private static HttpResponse execute(HttpClient client, HttpRequestBase request, String url, String encoding) throws HttpProcessException {
        HttpResponse response;
        try {
            //执行请求操作，并拿到结果（同步阻塞）
            response = client.execute(request);

        } catch (ParseException | IOException e) {
            throw new HttpProcessException(e);
        }

        return response;
    }

    private static HttpRequestBase getRequest(String url, HttpMethods method) {
        HttpRequestBase request;
        switch (method) {
            // HttpGet
            case GET:
                request = new HttpGet(url);
                break;
            case POST:
                request = new HttpPost(url);
                break;
            case HEAD:
                request = new HttpHead(url);
                break;
            case PUT:
                request = new HttpPut(url);
                break;
            case DELETE:
                request = new HttpDelete(url);
                break;
            case TRACE:
                request = new HttpTrace(url);
                break;
            case PATCH:
                request = new HttpPatch(url);
                break;
            case OPTIONS:
                request = new HttpOptions(url);
                break;
            default:
                request = new HttpPost(url);
                break;
        }
        return request;
    }

    /**
     * 尝试关闭response
     *
     * @param resp HttpResponse对象
     */
    private static void close(HttpResponse resp) {
        try {
            if (resp == null){
                return;
            }
            //如果CloseableHttpResponse 是resp的父类，则支持关闭
            if (CloseableHttpResponse.class.isAssignableFrom(resp.getClass())) {
                ((CloseableHttpResponse) resp).close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String send(String url) throws HttpProcessException {
        return send(url, Charset.defaultCharset().name());
    }

    public static String send(String url, String encoding) throws HttpProcessException {
        return send(url, new Header[]{}, encoding);
    }

    public static String send(String url, Header[] headers) throws HttpProcessException {
        return send(url, headers, Charset.defaultCharset().name());
    }

    public static String send(String url, Header[] headers, String encoding) throws HttpProcessException {
        return send(url, new HashMap<>(0), headers, encoding);
    }

    public static String send(String url, Map<String, String> parasMap) throws HttpProcessException {
        return send(url, parasMap, Charset.defaultCharset().name());
    }

    public static String send(String url, Map<String, String> parasMap, String encoding) throws HttpProcessException {
        return send(url, parasMap, new Header[]{}, encoding);
    }

    public static String send(String url, Map<String, String> parasMap, Header[] headers) throws HttpProcessException {
        return send(url, parasMap, headers, Charset.defaultCharset().name());
    }

    public static String send(String url, Map<String, String> parasMap, Header[] headers, String encoding) throws HttpProcessException {
        return send(url, HttpMethods.POST, parasMap, headers, encoding);
    }

    public static String send(String url, HttpMethods httpMethod) throws HttpProcessException {
        return send(url, httpMethod, Charset.defaultCharset().name());
    }

    public static String send(String url, HttpMethods httpMethod, String encoding) throws HttpProcessException {
        return send(url, httpMethod, new Header[]{}, encoding);
    }

    public static String send(String url, HttpMethods httpMethod, Header[] headers) throws HttpProcessException {
        return send(url, httpMethod, headers, Charset.defaultCharset().name());
    }

    public static String send(String url, HttpMethods httpMethod, Header[] headers, String encoding) throws HttpProcessException {
        return send(url, httpMethod, new HashMap<>(), headers, encoding);
    }

    public static String send(String url, HttpMethods httpMethod, Map<String, String> parasMap) throws HttpProcessException {
        return send(url, httpMethod, parasMap, Charset.defaultCharset().name());
    }

    public static String send(String url, HttpMethods httpMethod, Map<String, String> parasMap, String encoding) throws HttpProcessException {
        return send(url, httpMethod, parasMap, new Header[]{}, encoding);
    }

    public static String send(String url, HttpMethods httpMethod, Map<String, String> parasMap, Header[] headers) throws HttpProcessException {
        return send(url, httpMethod, parasMap, headers, Charset.defaultCharset().name());
    }

    public static String send(String url, HttpMethods httpMethod, Map<String, String> parasMap, Header[] headers, String encoding) throws HttpProcessException {
        return send(create(url), url, httpMethod, parasMap, headers, encoding);
    }

    /**
     *
     * @param client httpClient
     * @param url 连接地址
     * @return String
     * @throws HttpProcessException 自定义http异常
     */
    public static String send(HttpClient client, String url) throws HttpProcessException {
        return send(client, url, Charset.defaultCharset().name());
    }

    public static String send(HttpClient client, String url, String encoding) throws HttpProcessException {
        return send(client, url, new Header[]{}, encoding);
    }

    public static String send(HttpClient client, String url, Header[] headers) throws HttpProcessException {
        return send(client, url, headers, Charset.defaultCharset().name());
    }

    public static String send(HttpClient client, String url, Header[] headers, String encoding) throws HttpProcessException {
        return send(client, url, new HashMap<>(), headers, encoding);
    }

    public static String send(HttpClient client, String url, Map<String, String> parasMap) throws HttpProcessException {
        return send(client, url, parasMap, Charset.defaultCharset().name());
    }

    public static String send(HttpClient client, String url, Map<String, String> parasMap, String encoding) throws HttpProcessException {
        return send(client, url, parasMap, new Header[]{}, encoding);
    }

    public static String send(HttpClient client, String url, Map<String, String> parasMap, Header[] headers) throws HttpProcessException {
        return send(client, url, parasMap, headers, Charset.defaultCharset().name());
    }

    public static String send(HttpClient client, String url, Map<String, String> parasMap, Header[] headers, String encoding) throws HttpProcessException {
        return send(client, url, HttpMethods.POST, parasMap, headers, encoding);
    }

    public static String send(HttpClient client, String url, HttpMethods httpMethod) throws HttpProcessException {
        return send(client, url, httpMethod, Charset.defaultCharset().name());
    }

    public static String send(HttpClient client, String url, HttpMethods httpMethod, String encoding) throws HttpProcessException {
        return send(client, url, httpMethod, new Header[]{}, encoding);
    }

    public static String send(HttpClient client, String url, HttpMethods httpMethod, Header[] headers) throws HttpProcessException {
        return send(client, url, httpMethod, headers, Charset.defaultCharset().name());
    }

    public static String send(HttpClient client, String url, HttpMethods httpMethod, Header[] headers, String encoding) throws HttpProcessException {
        return send(client, url, httpMethod, new HashMap<>(), headers, encoding);
    }

    public static String send(HttpClient client, String url, HttpMethods httpMethod, Map<String, String> parasMap) throws HttpProcessException {
        return send(client, url, httpMethod, parasMap, Charset.defaultCharset().name());
    }

    public static String send(HttpClient client, String url, HttpMethods httpMethod, Map<String, String> parasMap, String encoding) throws HttpProcessException {
        return send(client, url, httpMethod, parasMap, new Header[]{}, encoding);
    }

    public static String send(HttpClient client, String url, HttpMethods httpMethod, Map<String, String> parasMap, Header[] headers) throws HttpProcessException {
        return send(client, url, httpMethod, parasMap, headers, Charset.defaultCharset().name());
    }

    /**
     * get 等方法
     */

    public static String get(String url, Header[] headers, String encoding) throws HttpProcessException {
        return get(create(url), url, headers, encoding);
    }

    public static String get(HttpClient client, String url, Header[] headers, String encoding) throws HttpProcessException {
        return send(client, url, HttpMethods.GET, headers, encoding);
    }

    public static String post(String url, Map<String, String> parasMap, Header[] headers, String encoding) throws HttpProcessException {
        return post(create(url), url, parasMap, headers, encoding);
    }

    public static String post(HttpClient client, String url, Map<String, String> parasMap, Header[] headers, String encoding) throws HttpProcessException {
        return send(client, url, HttpMethods.POST, parasMap, headers, encoding);
    }

    public static String put(String url, Map<String, String> parasMap, Header[] headers, String encoding) throws HttpProcessException {
        return put(create(url), url, parasMap, headers, encoding);
    }

    public static String put(HttpClient client, String url, Map<String, String> parasMap, Header[] headers, String encoding) throws HttpProcessException {
        return send(client, url, HttpMethods.PUT, parasMap, headers, encoding);
    }

    public static String delete(String url, Header[] headers, String encoding) throws HttpProcessException {
        return delete(create(url), url, headers, encoding);
    }

    public static String delete(HttpClient client, String url, Header[] headers, String encoding) throws HttpProcessException {
        return send(client, url, HttpMethods.DELETE, headers, encoding);
    }

    public static String patch(String url, Map<String, String> parasMap, Header[] headers, String encoding) throws HttpProcessException {
        return patch(create(url), url, parasMap, headers, encoding);
    }

    public static String patch(HttpClient client, String url, Map<String, String> parasMap, Header[] headers, String encoding) throws HttpProcessException {
        return send(client, url, HttpMethods.PATCH, parasMap, headers, encoding);
    }

    public static String head(String url, Header[] headers, String encoding) throws HttpProcessException {
        return head(create(url), url, headers, encoding);
    }

    public static String head(HttpClient client, String url, Header[] headers, String encoding) throws HttpProcessException {
        return send(client, url, HttpMethods.HEAD, headers, encoding);
    }

    public static String options(String url, Header[] headers, String encoding) throws HttpProcessException {
        return options(create(url), url, headers, encoding);
    }

    public static String options(HttpClient client, String url, Header[] headers, String encoding) throws HttpProcessException {
        return send(client, url, HttpMethods.OPTIONS, headers, encoding);
    }

    public static String trace(String url, Header[] headers, String encoding) throws HttpProcessException {
        return trace(create(url), url, headers, encoding);
    }

    public static String trace(HttpClient client, String url, Header[] headers, String encoding) throws HttpProcessException {
        return send(client, url, HttpMethods.TRACE, headers, encoding);
    }


    /**
     * 判断url是http还是https，直接返回相应的默认client对象
     *
     * @return 返回对应默认的client对象
     * @throws HttpProcessException 自定义Exception
     */
    private static HttpClient create(String url) throws HttpProcessException {
        if (url.toLowerCase().startsWith(HttpConsts.HTTPS_HEADER)) {
            return client4HTTPS;
        } else {
            return client4HTTP;
        }
    }
}
