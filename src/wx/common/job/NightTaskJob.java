package wx.common.job;

import wx.common.utils_server.SerUtil;

/**
 * 定时任务
 * <p>
 * 晚上执行。
 */
public class NightTaskJob {

    //晚上 三点执行一次
    //闲暇时间允许
    //数据统计
    public void execute1() {
        if (SerUtil.succStart) {
            System.out.printf("晚上3dian定时任务执行");
        }
    }

    //晚上 四点执行一次
    //过时数据定时清理了
    public void execute2() {
        if (SerUtil.succStart) {
            System.out.printf("4dian晚上定时任务执行");
        }
    }

}
