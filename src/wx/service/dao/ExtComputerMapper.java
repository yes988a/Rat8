package wx.service.dao;

import org.apache.ibatis.annotations.Param;
import wx.common.generator.base.Computer;

import java.util.List;

public interface ExtComputerMapper {

    /**
     * 查询负载最下的rat.computer。
     *
     * @param tim    时间
     * @param notIds 不可以为null  不包含ids
     * @return
     */
    public Computer selectLessOne(@Param("tim") Long tim, @Param("notIds") List<String> notIds);

    /**
     * 让stop增加 1
     *
     * @param cid
     */
    public void updateStop(@Param("cid") String cid);

    /**
     * 让用户数增加 1
     *
     * @param cid
     */
    public void updateUnum(@Param("cid") String cid);

}
