package wx.common.job;

import wx.common.utils_server.SerUtil;

/**
 * 定时任务
 * <p>
 * 白天执行.
 */
public class DayTaskJob {


    // 39秒一次
    //失败任务定时发送
    public void execute1() {
        if (SerUtil.succStart) {
            System.out.printf("39miao失败任务定时发送");
        }
    }

    // 30分钟一次
    //错误定时检查总结
    public void execute2() {
        if (SerUtil.succStart) {
            System.out.printf("30分钟,错误定时检查总结");
        }
    }

}
