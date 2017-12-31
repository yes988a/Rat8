package wx.common.utils_ser_comm;

import com.google.gson.JsonObject;
import wx.common.generator.base.Computer;
import wx.common.utils_app.LoginUtilA;
import wx.common.utils_app.MineUtilA;
import wx.common.utils_app.RetNumUtilA;

public class LoginUtilC {

    //获取服务器成功发回给app
    public final static void returnApp(JsonObject jout, Computer computer) {
        if (computer != null) {
            jout.addProperty(LoginUtilA.para_login_ip, computer.getIp() + ":" + computer.getPor());
            jout.addProperty(MineUtilA.para_r, RetNumUtilA.n_0);
        } else {
            jout.addProperty(MineUtilA.para_r, RetNumUtilA.n_b1);
            System.out.println("return App 服务器错误");
        }
    }

}
