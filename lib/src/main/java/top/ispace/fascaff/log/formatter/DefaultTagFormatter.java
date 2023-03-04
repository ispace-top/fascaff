package top.ispace.fascaff.log.formatter;

/**
 * @author: jinglong
 * @date: 2023/3/4
 */
public class DefaultTagFormatter implements ILogFormatter<StackTraceElement[]> {
    private static final String DEFAULT_TAG = "Fascaff Log";
    private static final String PACKAGE_NAME = "top.ispace.fascaff.log";

    @Override
    public String format(StackTraceElement[] elements) {
        if (elements == null || elements.length == 0) return DEFAULT_TAG;
        for (StackTraceElement element : elements) {
            if (element.getClassName().startsWith(PACKAGE_NAME)) {
                continue;
            } else {
                return new StringBuilder()
                        .append(element.getFileName())
                        .append("(")
                        .append(element.getLineNumber())
                        .append(")")
                        .toString();
            }
        }
        return DEFAULT_TAG;
    }
}
