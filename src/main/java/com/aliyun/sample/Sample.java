// This file is auto-generated, don't edit it. Thanks.
package com.aliyun.sample;

import com.aliyun.alidns20150109.models.UpdateDomainRecordRequest;
import com.aliyun.alidns20150109.models.UpdateDomainRecordResponse;
import com.aliyun.tea.TeaModel;
import com.aliyun.teaopenapi.models.Config;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Sample {

    /**
     * 使用AK&SK初始化账号Client
     *
     * @param accessKeyId
     * @param accessKeySecret
     * @return Client
     * @throws Exception
     */
    public static com.aliyun.alidns20150109.Client createClient(String accessKeyId, String accessKeySecret) throws Exception {
        Config config = new Config()
                // 您的AccessKey ID
                .setAccessKeyId(accessKeyId)
                // 您的AccessKey Secret
                .setAccessKeySecret(accessKeySecret);
        // 访问的域名
        config.endpoint = "dns.aliyuncs.com";
        return new com.aliyun.alidns20150109.Client(config);
    }

    private static String get_ip() {
        String url = "http://www.3322.org/dyndns/getip";
        return doGet(url);
    }

    private static String doGet(String httpurl) {
        HttpURLConnection connection = null;
        String result = null;// 返回结果字符串
        try {
            // 创建远程url连接对象
            URL url = new URL(httpurl);
            // 通过远程url连接对象打开一个连接，强转成httpURLConnection类
            connection = (HttpURLConnection) url.openConnection();
            // 设置连接方式：get
            connection.setRequestMethod("GET");
            // 设置连接主机服务器的超时时间：15000毫秒
            connection.setConnectTimeout(15000);
            // 设置读取远程返回的数据时间：60000毫秒
            connection.setReadTimeout(60000);
            // 发送请求
            connection.connect();
            // 通过connection连接，获取输入流
            if (connection.getResponseCode() == 200) {
                try (InputStream is = connection.getInputStream();
                     // 封装输入流is，并指定字符集
                     BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"))) {
                    // 存放数据
                    StringBuffer sbf = new StringBuffer();
                    String temp;
                    while ((temp = br.readLine()) != null) {
                        sbf.append(temp);
                        //sbf.append("\r\n");
                    }
                    result = sbf.toString();
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();// 关闭远程连接
        }
        return result;
    }

    /**
     * accessKeyId
     * accessKeySecret
     * RecordId
     * RR
     * Type
     * @param args_
     * @throws Exception
     */
    public static void main(String[] args_) throws Exception {
        String ip = get_ip();
        System.out.println(ip);
        com.aliyun.alidns20150109.Client client = Sample.createClient(args_[0], args_[1]);
        UpdateDomainRecordRequest updateDomainRecordRequest = new UpdateDomainRecordRequest()
                .setRecordId(args_[2])
                .setRR(args_[3])
                .setType(args_[4])
                .setValue(ip);
        UpdateDomainRecordResponse resp = client.updateDomainRecord(updateDomainRecordRequest);
        com.aliyun.teaconsole.Client.log(com.aliyun.teautil.Common.toJSONString(TeaModel.buildMap(resp)));
    }
}
