package wx.service.dao;

import org.apache.ibatis.annotations.Param;
import wx.common.entity.GroupSimple;
import wx.common.entity.GuserSimple;

import java.util.List;

public interface ExtGuserMapper {

    //查询我的群列表简单信息.。
    public List<GroupSimple> findGroupSimples(@Param("uid") String uid);

    //查询群成员简单信息。
    public List<GuserSimple> findGroupUsersSimple(@Param("gid") String gid);
}
