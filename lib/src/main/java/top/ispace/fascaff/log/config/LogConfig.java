package top.ispace.fascaff.log.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import top.ispace.fascaff.log.formatter.ILogFormatter;
import top.ispace.fascaff.log.parser.JsonParser;
import top.ispace.fascaff.log.printer.ILogPrinter;

/**
 * @author: jinglong
 * @date: 2023/3/3
 */
public class LogConfig {
    public String defaultTag;
    public boolean enabled;
    public int traceDeep;
    public ILogFormatter formatter;
    public List<ILogPrinter> printers = new ArrayList<>();
    public JsonParser parser;

    public LogConfig() {
        defaultTag = "Fascaff";
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

    public LogConfig jsonParser(JsonParser parser) {
        this.parser = parser;
        return this;
    }

}