package wx.common.utils_ser_netty;

import wx.common.generator.base.Computer;
import wx.common.utils_ser_comm.RegisterUtilC;
import wx.common.utils_server.RedisUtil;
import wx.common.utils_server.SerUtil;
import wx.service.dao.ExtComputerMapper;

import java.util.ArrayList;
import java.util.List;

public class RegisterUtilN {


    /**
     * （可用的computer。客户端）获取负载最小的computer（可以是自己）
     * 当前服务器，可以联系上的服务器。
     */
    public final static Computer getLessComputer(long tim, List<String> noIds) {//count为零或者null即可

        if (noIds == null) {
            noIds = new ArrayList<String>();
        }
        int count = noIds.size();
        if (count < 6) {//防止死循环
            Computer computer = null;
            if (count == 0) {
                computer = SerUtil.getComputer(RedisUtil.getR(RegisterUtilC.redis_lessComputer, null));
            }
            if (computer == null) {
                ExtComputerMapper extComputerMapper = SerUtil.SPRING.getBean(ExtComputerMapper.class);
                computer = extComputerMapper.selectLessOne(tim, noIds);
            }
            if (computer == null) {
                return null;
                //日志，不存在
            } else {
                boolean bb = SerUtil.ctxCli.containsKey(computer.getCid());
                if (SerUtil.curCid.equals(computer.getCid()) || bb) {
                    if (count != 0) {
                        RedisUtil.setR(RegisterUtilC.redis_lessComputer, computer.getCid(), RedisUtil.tim_r_20m);
                    }
                    return computer;
                } else {
                    //尝试建立连接，，，，
                    SerUtil.connectServer(computer, null);
                    noIds.add(computer.getCid());
                    return getLessComputer(tim, noIds);
                }
            }
        } else {
            return null;
        }
    }

}
