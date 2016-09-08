package com.kerwin.k_utils.logutil;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

import android.os.Environment;

/**
 * 
 * TypeName： Log
 *
 * @author Kerwin
 * @Date 2016年8月16日
 * @Version 2.0
 *          <p>
 *          说明： <br>
 *          V2.0 <br>
 *          新增原生log(tag,msg);的使用方法，与原生log(tag,msg)使用和展示形式相同;<br>
 *          V1.0<br>
 *          1.该类是一个集成了日志本地存储和显示优化的日志工具类。
 *          2.使用默认配置时无需任何设置，按原生log正常使用即可，例：Log.e("sample");
 *          3.该工具类默认配置如下：默认输出路径为sdcar根目录下k-log文件夹，日志文档命名为：[当前时间].txt
 *          4.默认输出日志等级为WARM,如需配置建议在application中配置Log.uotLevel
 *          5.默认日志开关和输出开关均为开启状态，如需设置请在application中配置Log.isShow及Log.isOut属性
 *          </p>
 */
public class Log {
	public static final int VERBOSE = 0;
	public static final int DEBUG = 1;
	public static final int INFO = 2;
	public static final int WARN = 3;
	public static final int ERROR = 4;
	public static boolean isShow = true;// 是否打印日志
	public static boolean isOut = true;// 是否输出日志到文件
	public static int outLevel = DEBUG;// 输出日志最低等级
	public static String logPath;// 输出日志路径

	public static void v(String msg) {
		logPrint(msg, "V", VERBOSE);
	}

	public static void d(String msg) {
		logPrint(msg, "D", DEBUG);
	}

	public static void i(String msg) {
		logPrint(msg, "I", INFO);
	}

	public static void w(String msg) {
		logPrint(msg, "W", WARN);
	}

	public static void e(String msg) {
		logPrint(msg, "E", ERROR);
	}

	public static void v(String tag, String msg) {
		logPrint(tag, msg, "V", VERBOSE);
	}

	public static void d(String tag, String msg) {
		logPrint(tag, msg, "D", DEBUG);
	}

	public static void i(String tag, String msg) {
		logPrint(tag, msg, "I", INFO);
	}

	public static void w(String tag, String msg) {
		logPrint(tag, msg, "W", WARN);
	}

	public static void e(String tag, String msg) {
		logPrint(tag, msg, "E", ERROR);
	}

	/**
	 * 日志输出和打印方法
	 * 
	 * @param message
	 *            需输出的msg
	 * @param level
	 *            日志字符等级
	 * @param levelNum
	 *            输出日志最低数字等级
	 * 
	 */
	private static void logPrint(String message, String level, int levelNum) {
		if (!isShow && !isOut)
			return;
		StackTraceElement stackTrace = Thread.currentThread().getStackTrace()[4];
		String TAG = getTag(stackTrace);
		String msgStr = getMassage(stackTrace, message, level);
		if (isShow) {
			switch (levelNum) {
			case VERBOSE:
				android.util.Log.v(TAG, msgStr);
				break;
			case DEBUG:
				android.util.Log.d(TAG, msgStr);
				break;
			case INFO:
				android.util.Log.i(TAG, msgStr);
				break;
			case WARN:
				android.util.Log.w(TAG, msgStr);
				break;
			case ERROR:
				android.util.Log.e(TAG, msgStr);
				break;
			}
		}
		if (isOut && levelNum >= outLevel) {
			outPut("TAG:" + TAG + "  " + msgStr);
		}
	}

	/**
	 * 日志输出和打印方法
	 * 
	 * @param tag
	 *            日志TAG标记
	 * @param message
	 *            需要输出的massage
	 * @param level
	 *            日志字符等级标识
	 * @param levelNum
	 *            输出日志最低数字等级
	 */
	private static void logPrint(String tag, String message, String level, int levelNum) {
		if (!isShow && !isOut)
			return;
		StackTraceElement stackTrace = Thread.currentThread().getStackTrace()[4];
		String TAG = tag;
		String msgStr = getMassage(stackTrace, message, level);
		if (isShow) {
			switch (levelNum) {
			case VERBOSE:
				android.util.Log.v(TAG, msgStr);
				break;
			case DEBUG:
				android.util.Log.d(TAG, msgStr);
				break;
			case INFO:
				android.util.Log.i(TAG, msgStr);
				break;
			case WARN:
				android.util.Log.w(TAG, msgStr);
				break;
			case ERROR:
				android.util.Log.e(TAG, msgStr);
				break;
			}
		}
		if (isOut && levelNum >= outLevel) {
			outPut("TAG:" + TAG + "  " + msgStr);
		}
	}

	/**
	 * 获取TAG
	 * 
	 * @param stackTraceElement
	 * @return
	 */
	private static String getTag(StackTraceElement stackTraceElement) {
		String fileName = stackTraceElement.getFileName();
		String tag = fileName.substring(0, fileName.length() - 5);
		return tag;
	}

	/**
	 * 处理日志信息，添加时间和栈节点信息
	 * 
	 * @param stackTraceElement
	 * @param msg
	 * @param level
	 * @return
	 */
	private static String getMassage(StackTraceElement stackTraceElement, String msg, String level) {
		StringBuilder msgBuiler = new StringBuilder();
		String fileName = stackTraceElement.getFileName();
		// String className = stackTraceElement.getClassName();
		String time = getTime();
		String methodName = stackTraceElement.getMethodName();
		int lineNumber = stackTraceElement.getLineNumber();

		msgBuiler.append(level);
		msgBuiler.append("[");
		msgBuiler.append(time).append("] ");
		msgBuiler.append(fileName).append("(");
		msgBuiler.append(lineNumber).append(").");
		// msgBuiler.append(className).append(".");
		msgBuiler.append(methodName).append("() ");
		msgBuiler.append(msg);
		return msgBuiler.toString();
	}

	/**
	 * 在对应文件中写入字符串
	 * 
	 * @param msg
	 */
	private static void outPut(String msg) {
		FileWriter filerWriter = null;
		BufferedWriter bufWriter = null;

		if (logPath == null || "".equals(logPath)) {
			logPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/k-log/";
		}
		String filePath = logPath + getDate() + ".log";

		File f = new File(filePath);
		if (!f.getParentFile().exists()) {
			f.getParentFile().mkdirs();
		}
		try {
			filerWriter = new FileWriter(filePath, true);
			bufWriter = new BufferedWriter(filerWriter);
			bufWriter.write(msg);
			bufWriter.newLine();
			bufWriter.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bufWriter != null)
					bufWriter.close();
				if (filerWriter != null)
					filerWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 获取“13:25:44”格式的时间字符串
	 * 
	 * @return
	 */
	private static String getTime() {
		Date date = new Date();
		return String.format("%tT", date);
	}

	/**
	 * 获取“2016-8-15”格式的日期字符串
	 * 
	 * @return
	 */
	private static String getDate() {
		Date date = new Date();
		return String.format("%tF", date);
	}
}
