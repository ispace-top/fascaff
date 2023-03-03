package top.itjl.gift.log;

import top.itjl.gift.log.config.LogConfig;

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
