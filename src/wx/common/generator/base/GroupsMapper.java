package wx.common.generator.base;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GroupsMapper {
    long countByExample(GroupsExample example);

    int deleteByExample(GroupsExample example);

    int deleteByPrimaryKey(String gid);

    int insert(Groups record);

    int insertSelective(Groups record);

    List<Groups> selectByExampleWithBLOBs(GroupsExample example);

    List<Groups> selectByExample(GroupsExample example);

    Groups selectByPrimaryKey(String gid);

    int updateByExampleSelective(@Param("record") Groups record, @Param("example") GroupsExample example);

    int updateByExampleWithBLOBs(@Param("record") Groups record, @Param("example") GroupsExample example);

    int updateByExample(@Param("record") Groups record, @Param("example") GroupsExample example);

    int updateByPrimaryKeySelective(Groups record);

    int updateByPrimaryKeyWithBLOBs(Groups record);

    int updateByPrimaryKey(Groups record);
}