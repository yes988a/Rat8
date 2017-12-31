package wx.common.utils_ser_comm;

import wx.common.generator.base.UserUnique;
import wx.common.generator.base.UserUniqueExample;
import wx.common.generator.base.UserUniqueMapper;


import java.util.List;

public class PhoneUtilC {

    //废弃 某个手机号的归属，电话（发送给所有server）
    //手机号，解除(仅仅解绑，因为是注册，，不做其他绑定。)（服务器间通信使用）
    public final static int url_ser_del_phone = 4678;

    //更新某个手机号的归属，到某个人员.，电话（发送给所有server）
    public final static int url_ser_upda_phone = 8428;


    //，时间，删除手机号使用，服务器间传送。
    public final static String para_tim_ser_del = "X2dp";

    // 修改手机号，服务器间传送，时间
    public final static String para_tim_ser_update = "XM1ip";

    /**
     * 解除绑定。sql
     *
     * @param phone
     * @param tim
     */
    public final static void delPhone(String phone, long tim) {

        UserUniqueMapper userUniqueMapper = SerUtilC.SPRING.getBean(UserUniqueMapper.class);

        UserUniqueExample phoneExample = new UserUniqueExample();
        phoneExample.createCriteria().andPhoneEqualTo(phone);
        List<UserUnique> listPhone = userUniqueMapper.selectByExample(phoneExample);

        for (int i = listPhone.size() - 1; i >= 0; i--) {//理论只有一个。或者没有。
            UserUnique uu = listPhone.get(i);
            uu.setPhoneTim(tim);
            uu.setPhone(":" + uu.getPhone() + "&" + tim);//废弃（:手机号+:+时间戳）长度够用。
            userUniqueMapper.updateByPrimaryKeySelective(uu);
        }
    }

}
