package top.ispace.fascaff.log;

import top.ispace.fascaff.log.config.LogConfig;
import top.ispace.fascaff.log.printer.ILogPrinter;

/**
 * @author: jinglong
 * @date: 2023/3/3
 */
public class Log {

    public static void v(Object... object) {
        vt(null, object);
    }


    public static void d(Object... object) {
        dt(null, object);
    }


    public static void i(Object... object) {
        it(null, object);
    }


    public static void w(Object... object) {
        wt(null, object);
    }


    public static void e(Object... object) {
        et(null, object);
    }


    public static void a(Object... object) {
        at(null, object);
    }


    public static void vt(String tag, Object... contents) {
        log(LogManager.me().conf(), LogLevel.VERBOSE, tag, contents);
    }


    public static void dt(String tag, Object... contents) {
        log(LogManager.me().conf(), LogLevel.DEBUG, tag, contents);
    }


    public static void it(String tag, Object... contents) {
        log(LogManager.me().conf(), LogLevel.INFO, tag, contents);
    }


    public static void wt(String tag, Object... contents) {
        log(LogManager.me().conf(), LogLevel.WARN, tag, contents);
    }


    public static void et(String tag, Object... contents) {
        log(LogManager.me().conf(), LogLevel.ERROR, tag, contents);
    }


    public static void at(String tag, Object... contents) {
        log(LogManager.me().conf(), LogLevel.ASSERT, tag, contents);
    }

    public static void log(LogConfig config, @LogLevel.Level int level, String tag, Object... contents) {
        if (!LogManager.me().enabled()) return;
        if (tag == null) {
            tag = parserTag();
        }
        String content = parserLogContent(contents);
        for (ILogPrinter printer : LogManager.me().printers()) {
            printer.print(config, level, tag, content);
        }
    }

    private static String parserTag() {
        return LogManager.DEFAULT_TAG_FORMATTER.format(new Throwable().getStackTrace());
    }

    private static String parserLogContent(Object[] contents) {
        StringBuilder content = new StringBuilder();
        content.append(LogManager.THREAD_INFO_FORMATTER.format(Thread.currentThread()));
        if (LogManager.me().formatter() != null) {
            content.append("\t");
            content.append(LogManager.me().formatter().format(contents));
        }
        content.append("\t");
        if (LogManager.me().parser() != null) {
            if (contents.length == 1) {
                if (isBaseClass(contents[0])) {
                    content.append(contents[0]);
                } else {
                    content.append(LogManager.me().parser().parser(contents[0]));
                }
            } else {
                content.append(LogManager.me().parser().parser(contents));
            }
        } else {
            content.append(contents.toString());
        }
        return content.toString();
    }

    /**
     * 判断是否是基础数据类型
     *
     * @param content 入参
     * @return 是否基础类型
     */
    private static boolean isBaseClass(Object content) {
        return content instanceof Integer
                || content instanceof Byte
                || content instanceof Long
                || content instanceof Double
                || content instanceof Float
                || content instanceof Character
                || content instanceof String
                || content instanceof Short
                || content instanceof Boolean;
    }
}
