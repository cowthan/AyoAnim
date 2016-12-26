package org.ayo.core.log;

import org.ayo.core.log.constant.LogLevel;
import org.ayo.core.log.constant.LogSegment;
import org.ayo.core.log.constant.ZoneOffset;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by codeest on 2016/8/3.
 */
public class log {

    public static boolean isDebug = true;
    private static String TAG = "ayo";

    public static void init(boolean debug, String defaultTag){
        isDebug = debug;
        TAG = defaultTag;
        //Logger.init(defaultTag).hideThreadInfo();

        List<LogLevel> logLevels = new ArrayList<>();
        logLevels.add(LogLevel.ERROR);
        logLevels.add(LogLevel.JSON);
        JLog.init()
                .setDebug(debug)
                .setPackagedLevel(1)
                .writeToFile(false)
                .setLogLevelsForFile(logLevels)
                .setLogDir("jlog")
                //.setLogPrefix("JiongBull")   //If you don't want use sub directory for logs, you may try prefix for log file.
                .setLogSegment(LogSegment.ONE_HOUR)
                .setCharset("utf-8")
                .setTimeFormat("yyyy年MM月dd日 HH时mm分ss秒")
                .setZoneOffset(ZoneOffset.P0800);
    }

    public static void e(String tag, Object o) {
        if(isDebug) {
            if(o instanceof Throwable){
                JLog.e(tag, (Throwable)o);
            }else{
                JLog.e(tag, o + "");
            }
        }
    }

    public static void e(Object o) {
        e(TAG, o);
    }

    public static void w(String tag, Object o) {
        if(isDebug) {
            JLog.w(tag, o + "");
        }
    }

    public static void w(Object o) {
        if(isDebug) {
            JLog.w(TAG, o+"");
        }

    }

    public static void d(String msg) {
        if(isDebug) {
            JLog.d(msg);
        }
    }

    public static void i(String msg) {
        if(isDebug) {
            JLog.i(msg);
        }
    }

    public static void i(String tag, String msg) {
        if(isDebug) {
            JLog.i(tag, msg);
        }
    }

    public static void json(String json){
        if(isDebug){
            JLog.json(json);
        }
    }


}
