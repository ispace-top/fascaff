package top.itjl.util.log.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import top.itjl.util.log.ILogFormatter;
import top.itjl.util.log.ILogPrinter;

/**
 * @author: jinglong
 * @date: 2023/3/3
 */
public class LogConfig {
    boolean enabled;
    int traceDeep;
    ILogFormatter formatter;
    List<ILogPrinter> printers;

    public LogConfig() {
    }

    public LogConfig enabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    public LogConfig traceDeep(int deep) {
        this.traceDeep = deep;
        return this;
    }

    public LogConfig addPrinter(ILogPrinter... printers) {
        if (printers == null) {
            this.printers = new ArrayList();
        }
        this.printers.addAll(Arrays.asList(printers));
        return this;
    }

    public LogConfig setFormatter(ILogFormatter formatter) {
        this.formatter = formatter;
        return this;
    }

}