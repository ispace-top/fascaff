/**
 *
 */
package top.itjl.util.sysutil;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Environment;

/**
 * TypeName： sysUtil
 *
 * @author Kerwin
 *         <p>
 *         说明：
 *         </p>
 */
public class SysUtil {
    /**
     * 在新Task中打开Activity
     *
     * @param context
     * @param packageName  要打开的activity包名
     * @param activityName 要打开的activity名
     */
    public static void startActivityOnNewTask(Context context, String packageName, String activityName) {
        ComponentName componetName = new ComponentName(packageName, activityName);
        Intent intent = new Intent();
        intent.setComponent(componetName);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    /**
     * 在新Task中打开Activity
     *
     * @param context
     * @param clazz   要打开的Activity类名.class
     */
    public static <T> void startActivityOnNewTask(Context context, Class<T> clazz) {
        Intent intent = new Intent(context, clazz);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    /**
     * 在新Task中打开Activity，并传递一个Boolean值
     *
     * @param context
     * @param clazz   要打开的Activity类名.class
     * @param tag     存取值的的键
     * @param value   要传递的Boolean值
     */
    public static <T> void startActivityOnNewTask(Context context, Class<T> clazz, String tag, boolean value) {
        Intent intent = new Intent(context, clazz);
        intent.putExtra(tag, value);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    /**
     * 打开Activity
     *
     * @param context
     * @param packageName  包名
     * @param activityName 类名
     */
    public static void startActivity(Context context, String packageName, String activityName) {
        ComponentName componetName = new ComponentName(packageName, activityName);
        Intent intent1 = new Intent();
        intent1.setComponent(componetName);
        context.startActivity(intent1);
    }

    /**
     * 打开Activity
     *
     * @param context
     * @param clazz   Activity的.class
     */
    public static <T> void startActivity(Context context, Class<T> clazz) {
        Intent intent = new Intent(context, clazz);
        context.startActivity(intent);
    }

    /**
     * 打开Activity，并传递一个Boolean值
     *
     * @param context
     * @param clazz   要打开的Activity类名.class
     * @param tag     存取值的的键
     * @param value   要传递的Boolean值
     */
    public static <T> void startActivity(Context context, Class<T> clazz, String tag, boolean value) {
        Intent intent = new Intent(context, clazz);
        intent.putExtra(tag, value);
        context.startActivity(intent);
    }

    /**
     * 启动Service
     *
     * @param context
     * @param clazz
     * @param <T>
     */
    public static <T> void startService(Context context, Class<T> clazz) {
        Intent intent = new Intent(context, clazz);
        context.startService(intent);
    }

    /**
     * 发送广播
     *
     * @param context
     * @param action  发送的Action
     */
    public static void sendBroadcast(Context context, String action) {
        Intent intent = new Intent();
        intent.setAction(action);
        context.sendBroadcast(intent);
    }

    /**
     * 重新打开桌面，等同于执行Home按键
     *
     * @param context
     */
    public static void starHome(Context context) {
        Intent home = new Intent(Intent.ACTION_MAIN);
        home.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        home.addCategory(Intent.CATEGORY_HOME);
        context.startActivity(home);
    }

    /**
     * 获取设备sdcard路径
     *
     * @return
     */
    public static String sdCardPath() {
        String sdPath = "";
        if (Environment.isExternalStorageEmulated()) {
            sdPath = Environment.getExternalStorageDirectory().getAbsolutePath();
        }
        return sdPath;
    }

    /**
     * 获取当前软件版本号
     *
     * @return
     */
    public static String getSystemVersion(Context mContext) {
        PackageManager packageManager = mContext.getPackageManager();
        PackageInfo packInfo;
        String version;
        try {
            packInfo = packageManager.getPackageInfo(mContext.getPackageName(), 0);
            version = packInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            version = "1.0";
        }
        return version;
    }

    /**
     * 返回当前程序版本名
     */
    public static String getAppVersionName(Context context) throws Exception {
        String versionName = "";
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            versionName = pi.versionName;
            int versioncode = pi.versionCode;
            if (versionName == null || versionName.length() <= 0) {
                return "";
            }
        } catch (Exception e) {
            throw e;
        }
        return versionName;
    }
}
