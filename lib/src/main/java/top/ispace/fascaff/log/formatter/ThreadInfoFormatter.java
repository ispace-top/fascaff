package top.ispace.fascaff.log.formatter;

/**
 * @author: jinglong
 * @date: 2023/3/4
 */
public class ThreadInfoFormatter implements ILogFormatter<Thread> {
    @Override
    public String format(Thread thread) {
        StringBuilder builder = new StringBuilder();
        builder.append("[Thread:")
                .append(thread.getName())
                .append("-")
                .append(thread.getId())
                .append("]");
        return builder.toString();
    }
}
