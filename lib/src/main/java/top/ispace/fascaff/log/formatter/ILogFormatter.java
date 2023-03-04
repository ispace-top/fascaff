package top.ispace.fascaff.log.formatter;

/**
 * @author: jinglong
 * @date: 2023/3/4
 */
public interface ILogFormatter <T>{
    String format(T t);
}
