package wx.common.utils_ser_comm;

public class RegisterUtilC {

    //同步uni。到全部服务器。
    public final static int url_ser_userUni_syn = 95602;

    //负载最小的compter。（没有后缀）：好处，当注册时，某次请求重复时，获取到的compter是同一个。减少错误。
    public final static String redis_lessComputer = "w@l!" + SerUtilC.curCid;

    //把userFull同步到所在服务器。（必然同时插入userunique）
    public final static int url_ser_userfull_syn = 92402;

}
