package com.dongze.mvpandroid.common;


public class GlobalVar {

    /**
     * 服务器地址
     */
    public static String SERVER = "http://apis.baidu.com/";

    /**
     * 屏幕宽
     */
    public static int SCREEN_WIDTH = 0;

    /**
     * 屏幕高
     */
    public static int SCREEN_HEIGHT = 0;

    /**
     * 读取时间
     */
    public static int READ_TIMEOUT = 30;

    /**
     * 连接时间
     */
    public static int CONNECT_TIMEOUT = 30;

    /**
     * 写入时间
     */
    public static int WRITE_TIMEOUT = 30;

    public static int RESULT_OK = 0;
    public static int RESULT_UNLOGIN = -1;

    /**
     * 网络接口
     */
    public interface NetPorts {
        String WEATHER ="apistore/mobilenumber/mobilenumber";
    }
}
