package top.ispace.fascaff.log;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author: jinglong
 * @date: 2023/3/3
 */
public class LogLevel {
    @IntDef({VERBOSE, DEBUG, INFO, WARN, ERROR, ASSERT})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Level {
    }

    public final static int VERBOSE = 2;
    public final static int DEBUG = 3;
    public final static int INFO = 4;
    public final static int WARN = 5;
    public final static int ERROR = 6;
    public final static int ASSERT = 7;
}
