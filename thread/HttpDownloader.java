package com.zggk.newiroad.thread;

/**
 * Created by 张成昆 on 2019-3-8.
 */


import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 下载工具类
 * Created by dongxiaoqing on 2018/10/22.
 */

public class HttpDownloader {
    private URL url = null;

    /**
     * 根据URL下载文件，前提是这个文件当中的内容是文本，函数的返回值就是文件当中的内容
     * 1.创建一个URL对象
     * 2.通过URL对象，创建一个HttpURLConnection对象
     * 3.得到InputStram
     * 4.从InputStream当中读取数据
     * @param urlStr
     * @return
     */
    public String download(String urlStr) {
        StringBuffer sb = new StringBuffer();
        String line = null;
        BufferedReader buffer = null;
        try {
            // 创建一个URL对象
            urlStr=urlStr.replace("\\","/");
            Log.i("===地址",urlStr);
            url = new URL(urlStr);

            // 创建一个Http连接
            HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
            urlConn.setRequestProperty("Charset", "utf-8");
            // 使用IO流读取数据
            buffer = new BufferedReader(new InputStreamReader(urlConn.getInputStream(),"gbk"));
            while ((line = buffer.readLine()) != null) {
                sb.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                buffer.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    public  String downloadByFile(Context context, String docUrl){                           /***加载正文***/
        //获取存储卡路径、构成保存文件的目标路径
        String dirName = "";
        //SD卡具有读写权限、指定附件存储路径为SD卡上指定的文件夹
        dirName = Environment.getExternalStorageDirectory()+"/Signature/";
        File f = new File(dirName);
        if(!f.exists()){      //判断文件夹是否存在
            f.mkdir();        //如果不存在、则创建一个新的文件夹
        }
        //准备拼接新的文件名
        String[] list = docUrl.split("/");
        String fileName = list[list.length-1];
        fileName = dirName + fileName;
        File file = new File(fileName);
        if(file.exists()){    //如果目标文件已经存在
            file.delete();    //则删除旧文件
        }
        //1K的数据缓冲
        byte[] bs = new byte[1024];
        //读取到的数据长度
        int len;
        try{
            //通过文件地址构建url对象
            URL url = new URL(docUrl);
            //获取链接
            //URLConnection conn = url.openConnection();
            //创建输入流
            InputStream is = url.openStream();
            //获取文件的长度
            //int contextLength = conn.getContentLength();
            //输出的文件流
            OutputStream os = new FileOutputStream(file);
            //开始读取
            while((len = is.read(bs)) != -1){
                os.write(bs,0,len);
            }
            //完毕关闭所有连接
            os.close();
            is.close();
        }catch(MalformedURLException e){
            fileName = null;
            System.out.println("创建URL对象失败");
        }catch(FileNotFoundException e){
            fileName = null;
            System.out.println("无法加载文件");
        }catch(IOException e){
            fileName = null;
            System.out.println("获取连接失败");
        }
        return fileName;
    }

}
