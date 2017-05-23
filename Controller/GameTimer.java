/**
 * 游戏计时器
 */

package Controller;

import java.text.SimpleDateFormat;
import java.util.Date;

class GameTimer {
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    private static Date time;

    // 初始化
    static void init() {
        time = new Date(0, 0, 0, 0, 0, 0);
    }

    // 获取游戏时间
    static String getTime() {
        return dateFormat.format(time.getTime());
    }

    // 下一秒
    static void updateTime() {
        time.setTime(time.getTime() + 1000);
    }
}
