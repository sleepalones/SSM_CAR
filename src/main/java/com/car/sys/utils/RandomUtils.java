package com.car.sys.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * @author 铭鸽鸽
 * @create 2020/6/25 12:46
 * 随机工具类
 */
public class RandomUtils {

    private static SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
    private static SimpleDateFormat format2 = new SimpleDateFormat("yyyyMMddHHmmssSSS");
    private static Random random = new Random();

    /**
     * 得到当前日期
     */
    public static String getCurrentDateForString(){
        return format1.format(new Date());
    }

    /**
     * 生成文件名使用时间+4位随机数
     */
    public static String createFileNameUseTime (String fileName){
        String fileSuffix = fileName.substring(fileName.lastIndexOf("."), fileName.length());
        String time = format2.format(new Date());
        Integer num = random.nextInt(9000) + 1000;
        return time + num + fileSuffix;
    }

    /**
     * 生成文件名使用UUID
     */
    public static String createFileNameUseUUID (String fileName){
        String fileSuffix = fileName.substring(fileName.lastIndexOf("."),fileName.length());
        return UUID.randomUUID().toString().replace("-","").toUpperCase() + fileSuffix;
    }

}
