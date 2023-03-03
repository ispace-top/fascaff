package top.ispace.fascaff.sys;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.text.format.Formatter;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.text.DecimalFormat;
import java.util.Enumeration;
import java.util.UUID;
import java.util.regex.Pattern;

/**
 * 获取设备硬件相关信息
 * Created by Kerwin on 2016/9/9.
 */
public class DevieUtil {
    /**********************************************************************************/
    /* ------------------------------关于网络部分------------------------------------- */
    /**********************************************************************************/
    /**
     * 用来获取手机拨号上网（包括CTWAP和CTNET）时由PDSN分配给手机终端的源IP地址。
     *
     * @return
     * @author
     */
    public static String getPhoneIp() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
                        return inetAddress.getHostAddress().toString();
                    }
                }
            }
        } catch (Exception e) {
        }
        return "";
    }


    /**
     * 检查网络是否可用
     *
     * @param context
     * @return
     */
    public static boolean checkNetState(Context context) {
        boolean netstate = false;
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        netstate = true;
                        break;
                    }
                }
            }
        }
        return netstate;
    }

    /**********************************************************************************/
    /* ----------------------------设备硬件信息部分----------------------------------- */
    /**********************************************************************************/

    /**
     * 获取设备id
     *
     * @param tm TelephoneManager
     * @return
     */
    public static String getDeviceId(TelephonyManager tm) {
        String id = tm.getSubscriberId();
        if (TextUtils.isEmpty(id)) {
            id = tm.getDeviceId();
        }
        if (TextUtils.isEmpty(id)) {
            id = UUID.randomUUID().toString();
        }
        return id;
    }

    /**
     * 获取设备CPU名称
     *
     * @return
     */
    public static String getCpuName() {
        try {
            FileReader fr = new FileReader("/proc/cpuinfo");
            BufferedReader br = new BufferedReader(fr);
            String text = br.readLine();
            String[] array = text.split(":\\s+", 2);
            for (int i = 0; i < array.length; i++) {
            }
            return array[1];
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取CPU核心数
     *
     * @return
     */
    public static int getNumCores() {
        class CpuFilter implements FileFilter {
            @Override
            public boolean accept(File pathname) {
                if (Pattern.matches("cpu[0-9]", pathname.getName())) {
                    return true;
                }
                return false;
            }
        }
        try {
            File dir = new File("/sys/devices/system/cpu/");
            File[] files = dir.listFiles(new CpuFilter());
            return files.length;
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }

    /**
     * 获取机器型号
     *
     * @return
     */
    public static String getPhoneModel() {
        return Build.MODEL;
    }

    /**
     * 获取机器品牌
     *
     * @return
     */
    public static String getPhoneBrand() {
        return Build.BRAND;
    }

    /**
     * 获取操作系统版本
     *
     * @return
     */
    public static String getOS() {
        return android.os.Build.VERSION.RELEASE;
    }

    /**
     * 获取设备总内存大小
     *
     * @param mContext
     * @return
     */
    public static String getMemorySize(Context mContext) {
        String str1 = "/proc/meminfo";// 系统内存信息文件
        String str2;
        String[] arrayOfString;
        long initial_memory = 0;
        try {
            FileReader localFileReader = new FileReader(str1);
            BufferedReader localBufferedReader = new BufferedReader(localFileReader, 8192);
            str2 = localBufferedReader.readLine();// 读取meminfo第一行，系统总内存大小
            arrayOfString = str2.split("\\s+");
            initial_memory = Integer.valueOf(arrayOfString[1]).intValue() * 1024;// 获得系统总内存，单位是KB，乘以1024转换为Byte
            localBufferedReader.close();
        } catch (IOException e) {
        }
        return Formatter.formatFileSize(mContext, initial_memory);// Byte转换为KB或者MB，内存大小规格化
    }

    /**
     * @return 获取设备总存储容量
     * 需在manifest.xml文件中添加
     * <uses-permission
     * android:name="android.permission.WRITE_EXTERNAL_STORAGE">
     * </uses-permission>
     */
    public static String getRomSize(Context mContext) {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            String path = Environment.getExternalStorageDirectory().getPath();
            StatFs statfs = new StatFs(path);
            // 获取block的SIZE
            long blocSize = statfs.getBlockSize();
            // 获取BLOCK数量
            long totalBlocks = statfs.getBlockCount();
            // 空闲的Block的数量
            long availaBlock = statfs.getAvailableBlocks();
            // 计算总空间大小和空闲的空间大小
            // 存储空间大小跟空闲的存储空间大小就被计算出来了。
            long availableSize = blocSize * availaBlock;
            long allSize = blocSize * totalBlocks;
            return Formatter.formatFileSize(mContext, allSize);// Byte转换为KB或者MB，内存大小规格化
        }
        return "0MB";
    }

    /**
     * 获取空闲存储空间
     *
     * @param context
     * @return
     */
    public static String getAvailaRomSize(Context context) {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            String path = Environment.getExternalStorageDirectory().getPath();
            StatFs statfs = new StatFs(path);
            // 获取block的SIZE
            long blocSize = statfs.getBlockSize();
            long availaBlock = statfs.getAvailableBlocks();
            long availableSize = blocSize * availaBlock;
            return Formatter.formatFileSize(context, availableSize);// Byte转换为KB或者MB，内存大小规格化
        }
        return "0MB";
    }
    /**********************************************************************************/
    /* ----------------------------关于显示器部分------------------------------------- */
    /**********************************************************************************/
    /**
     * 获取屏幕宽度
     *
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context) {
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        return display.getWidth();
    }

    /**
     * 获取屏幕高度
     *
     * @param context
     * @return
     */
    public static int getScreenHeight(Context context) {
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        return display.getHeight();
    }

    /**
     * 获取屏幕分辨率
     *
     * @return
     */
    public static String getDensity(Context context) {
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        display.getMetrics(displayMetrics);
        if (null != display) {
            return displayMetrics.density + "";
        }
        return "";
    }

    /**
     * @return 获取屏幕尺寸
     */
    public static String getScreenSize(Context context) {
        DecimalFormat fnum = new DecimalFormat("##0.0");
        String dd = fnum.format(Math.sqrt(getScreenHeight(context) * getScreenHeight(context)
                + getScreenWidth(context) * getScreenWidth(context))
                / (Float.parseFloat(getDensity(context)) * 160));
        return dd + "英寸";
    }

    /**
     * @return 获取屏幕比例
     */
    public static String getScreenRatio(Context context) {
        int width = getScreenWidth(context);
        int height = getScreenHeight(context);
        int big = getGreatestCommonDivisor(width, height);
        return width / big + ":" + height / big;
    }

    // 欧几里德算法  计算最大公约数
    static int getGreatestCommonDivisor(int m, int n) {
        int r = m % n;
        while (r != 0) {
            m = n;
            n = r;
            r = m % n;
        }
        return n;
    }
}
