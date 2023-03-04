package top.ispace.fascaff.log;

/**
 * @author: jinglong
 * @date: 2023/3/4
 */
public class LogNotInitException extends RuntimeException{
    public LogNotInitException() {
        super("You must init LogManger before use it!");
    }
}
