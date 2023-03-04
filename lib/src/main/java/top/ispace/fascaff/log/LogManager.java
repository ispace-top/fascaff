package top.ispace.fascaff.log;

import androidx.annotation.NonNull;

import java.util.List;

import top.ispace.fascaff.log.config.LogConfig;
import top.ispace.fascaff.log.formatter.DefaultTagFormatter;
import top.ispace.fascaff.log.formatter.ILogFormatter;
import top.ispace.fascaff.log.formatter.StackTraceInfoFormatter;
import top.ispace.fascaff.log.formatter.ThreadInfoFormatter;
import top.ispace.fascaff.log.parser.JsonParser;
import top.ispace.fascaff.log.printer.ConsoleLogPrinter;
import top.ispace.fascaff.log.printer.ILogPrinter;

/**
 * @author: jinglong
 * @date: 2023/3/3
 */
public class LogManager {
    private static LogManager INSTANCE;
    private final LogConfig config;
    public final static DefaultTagFormatter DEFAULT_TAG_FORMATTER = new DefaultTagFormatter();
    public final static ThreadInfoFormatter THREAD_INFO_FORMATTER = new ThreadInfoFormatter();
    public final static StackTraceInfoFormatter STACK_TRACE_INFO_FORMATTER = new StackTraceInfoFormatter();

    public final static ConsoleLogPrinter CONSOLE_LOG_PRINTER = new ConsoleLogPrinter();

    private LogManager(LogConfig config) {
        this.config = config;
    }

    public static LogManager me() {
        if (INSTANCE == null) throw new LogNotInitException();
        return INSTANCE;
    }

    public static void init(@NonNull LogConfig config) {
        if (INSTANCE == null) {
            INSTANCE = new LogManager(config);
            config.addPrinter(CONSOLE_LOG_PRINTER);
        }
    }

    public boolean enabled() {
        return config.enabled;
    }

    public ILogFormatter formatter() {
        return config.formatter;
    }

    public List<ILogPrinter> printers() {
        return config.printers;
    }

    public JsonParser parser() {
        return config.parser;
    }

    public String tag() {
        return config.defaultTag;
    }

    public LogConfig conf() {
        return config;
    }

}
