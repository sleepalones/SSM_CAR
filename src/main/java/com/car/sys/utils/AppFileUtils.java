package com.car.sys.utils;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.Properties;

/**
 * @author 铭鸽鸽
 * @create 2020/6/25 12:18
 */
public class AppFileUtils {
    /**
     * 获取文件上传路径
     */
    private static String PATH = "E:/upload/";

    static {
        InputStream inputStream = AppFileUtils.class.getClassLoader().getResourceAsStream("file.properties");
        Properties properties = new Properties();
        try {
            properties.load(inputStream);
            PATH = properties.getProperty("path");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 文件下载
     * @param resp
     * @param path
     * @param oldName
     * @return
     */
    public static ResponseEntity<Object> downloadFile(HttpServletResponse resp, String path, String oldName){
        //使用绝对路径+相对路径去找到文件对象
        File file = new File(AppFileUtils.PATH,path);
        //判断文件是否存在
        if (file.exists()){
            try {
                try {
                    oldName = URLEncoder.encode(oldName,"UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                byte[] bytes = FileUtils.readFileToByteArray(file);
                HttpHeaders header = new HttpHeaders();
                //封装响应内容类型（APPLICATION_OCTET_STREAM响应的内容不限定）
                header.setContentType(MediaType.APPLICATION_OCTET_STREAM);
                //设置下载的文件名称
                header.setContentDispositionFormData("attachment",oldName);
                //创建ResponseEntity对象
                return new ResponseEntity<>(bytes,header, HttpStatus.CREATED);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }else {
            PrintWriter out;
            try {
                out = resp.getWriter();
                out.write("文件不存在");
                out.flush();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return null;
    }

    /**
     * 根据相对路径删除硬盘上文件
     * @param path
     */
    public static void deleteFileUsePath(String path){
        String realPath = PATH + path;
        //根据文件
        File file = new File(realPath);
        if (file.exists()) {
            file.delete();
        }
    }

}
