package top.ispace.fascaff.log;

/**
 * @author: jinglong
 * @date: 2023/3/3
 */
public interface ILog {
    void v(Object... object);

    void d(Object... object);

    void i(Object... object);

    void w(Object... object);

    void e(Object... object);

    void a(Object... object);

    void v(String tag, Object... content);

    void d(String tag, Object... content);

    void i(String tag, Object... content);

    void w(String tag, Object... content);

    void e(String tag, Object... content);

    void a(String tag, Object... content);

}
