package top.itjl.util.log;

import top.itjl.util.log.config.LogConfig;

/**
 * @author: jinglong
 * @date: 2023/3/3
 */
public class LogManager {
    private static LogManager INSTANCE;

    private LogManager(LogConfig config) {
    }

    public static LogManager getInstance() {
        return INSTANCE;
    }

    public static void init(LogConfig config) {
        if (INSTANCE == null) {
            INSTANCE = new LogManager(config);
        }
    }

}
