package top.ispace.fascaff.log.printer;

import top.ispace.fascaff.log.LogLevel;
import top.ispace.fascaff.log.config.LogConfig;

/**
 * @author: jinglong
 * @date: 2023/3/3
 */
public interface ILogPrinter {
    void print(LogConfig conf, @LogLevel.Level int level, String tag, String content);
}
