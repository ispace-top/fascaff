package top.ispace.fascaff.log.printer;

import android.util.Log;

import top.ispace.fascaff.log.config.LogConfig;

/**
 * @author: jinglong
 * @date: 2023/3/4
 */
public class ConsoleLogPrinter implements ILogPrinter {
    @Override
    public void print(LogConfig conf, int level, String tag, String content) {
        Log.println(level,tag,content);
    }
}
