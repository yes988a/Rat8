package wx.service.dao;

import org.apache.ibatis.annotations.Param;
import wx.common.entity.UserrelationSimple;

import java.util.List;

/**
 * Created by hp on 2017/6/28.
 */
public interface ExtUrelationMapper {

    /**
     * 获取单个好友详情
     *
     * @param uid
     * @param fid
     * @return
     */
    public UserrelationSimple findUserrelationByfid(@Param("uid") String uid,
                                                    @Param("fid") String fid);

    /**
     * 获取我的好友cid
     *
     * @param uid
     * @param fid
     * @return
     */
    public String findUserCidByfid(@Param("uid") String uid,
                                   @Param("fid") String fid);

    /**
     * 获取我的所有好友
     *
     * @param uid
     * @return
     */
    public List<UserrelationSimple> findUserrelations(@Param("uid") String uid);

    /**
     * 获取多个好友详情
     *
     * @param uid
     * @param fids
     * @return
     */
    public List<UserrelationSimple> findUserrelationByfids(
            @Param("uid") String uid, @Param("fids") List<String> fids);
}
