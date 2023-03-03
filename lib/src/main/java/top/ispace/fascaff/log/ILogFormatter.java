package top.ispace.fascaff.log;

/**
 * @author: jinglong
 * @date: 2023/3/3
 */
public interface ILogFormatter {
    String format(Object... contents);
}
