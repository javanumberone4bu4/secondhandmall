package com.rimi.secondhandtradingmall.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 下载工具类
 *
 * @author Wang Xiaoping
 * @date 2019/11/4 10:29
 */
public final class DownloadUtils {
    private DownloadUtils(){}
    public static void download(String path, String fileName, HttpServletResponse response,HttpServletRequest request){
        //设置要下载文件的响应头
        response.setHeader("content-disposition","attachment;fileName="+fileName);
        //得到下载目录的真实路径
        String realPath = request.getServletContext().getRealPath(path);
        //判断要下载的文件是否存在
        File file=new File(realPath+"/"+fileName);
        if(file.exists()){
             try{
                //得到响应的输出流
                OutputStream out = response.getOutputStream();
                //得到文件的输入流
                FileInputStream in=new FileInputStream(file);
                //创建要写的字节数组
                byte[] bytes=new byte[1024];
                //写的长度
                int len=0;
                //当读的长度为-1时就说明写完了
                while((len=in.read(bytes))!=-1){
                    //写入
                    out.write(bytes,0,len);
                    //刷新
                    out.flush();
                }
                //关闭流
                out.close();
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
