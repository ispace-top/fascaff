package top.ispace.fascaff.log.formatter;

/**
 * @author: jinglong
 * @date: 2023/3/4
 */
public class StackTraceInfoFormatter implements ILogFormatter<StackTraceElement[]> {
    @Override
    public String format(StackTraceElement[] elements) {
        if (elements == null || elements.length == 0) return null;
        StringBuilder builder = new StringBuilder();
        for (StackTraceElement element:elements){
            if(element==elements[0]){
                builder.append("\n \t Trace:");
            }
            builder.append("\t "+element.toString());
            if(element==elements[elements.length-1]){
                builder.append("\n");
            }
        }
        return builder.toString();
    }

}
