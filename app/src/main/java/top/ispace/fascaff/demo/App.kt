package top.ispace.fascaff.demo

import android.app.Application
import com.alibaba.fastjson2.toJSONString
import top.ispace.fascaff.log.LogManager
import top.ispace.fascaff.log.config.LogConfig

/**
 *  @author: jinglong
 *  @date: 2023/3/3
 *
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        LogManager.init(
            LogConfig().enabled(BuildConfig.DEBUG)
                .traceDeep(5)
                .jsonParser { it.toJSONString() });
    }
}