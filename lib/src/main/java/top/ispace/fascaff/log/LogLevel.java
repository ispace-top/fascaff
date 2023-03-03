package top.ispace.fascaff.log;

/**
 * @author: jinglong
 * @date: 2023/3/3
 */
public @interface LogLevel {
    int VERBOSE = 0;
    int DEBUG = 1;
    int INFO = 2;
    int WARN = 3;
    int ERROR = 4;
    int ASSERT = 5;
}
