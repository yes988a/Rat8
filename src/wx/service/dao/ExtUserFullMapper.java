package wx.service.dao;

import org.apache.ibatis.annotations.Param;
import wx.common.entity.*;
import wx.common.generator.base.UserFull;

import java.util.List;


public interface ExtUserFullMapper {

    /**
     * 用户登录成功，后信息。rat服务器使用
     *
     * @param acc
     * @param pas
     * @return
     */
    public LoginCompeletePojo loginCompelete(@Param("acc") String acc,
                                             @Param("pas") String pas);

    /**
     * 根据二维码查询用户可对外公开的基本信息
     */
    public UsimpleTostrange findSimpleByQrcode(@Param("qrcode") String qrcode);

    public UsimpleTostrange findSimpleByacc(@Param("acc") String acc);

    public UsimpleTostrange findSimpleByphone(@Param("phone") String phone);

    public UsimpleTostrange findSimpleByUid(@Param("uid") String uid);

    /**
     * 查询我的个人设置
     *
     * @param uid
     * @return
     */
    public MySimple findMySimple(@Param("uid") String uid);

}
