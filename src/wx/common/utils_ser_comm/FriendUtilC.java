package wx.common.utils_ser_comm;


import wx.service.dao.ExtUrelationMapper;

public class FriendUtilC {

    //申请 ，发送到不同服务器。
    public final static int url_ser_ratToFriend = 1940;

    //同意 添加好友 ，回复方To请求方
    public final static int url_ser_resToreq = 2204;

    //删除好友，发送到相关服务器
    public final static int url_ser_delFriToRat2 = 5637;

    //判断是否我的好友，并获取他的cid
    public final static String getUsimpleCid(String uid, String fid) {
        if (fid == null || "".equals(fid)) {
            return null;
        } else {
            ExtUrelationMapper extUrelationDao = SerUtilC.SPRING.getBean(ExtUrelationMapper.class);
            String cid = extUrelationDao.findUserCidByfid(uid, fid);
            return cid;
        }
    }

}
