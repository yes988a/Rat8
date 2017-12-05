package wx.common.generator.active;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VoteTaskMapper {
    long countByExample(VoteTaskExample example);

    int deleteByExample(VoteTaskExample example);

    int deleteByPrimaryKey(String uuid);

    int insert(VoteTask record);

    int insertSelective(VoteTask record);

    List<VoteTask> selectByExample(VoteTaskExample example);

    VoteTask selectByPrimaryKey(String uuid);

    int updateByExampleSelective(@Param("record") VoteTask record, @Param("example") VoteTaskExample example);

    int updateByExample(@Param("record") VoteTask record, @Param("example") VoteTaskExample example);

    int updateByPrimaryKeySelective(VoteTask record);

    int updateByPrimaryKey(VoteTask record);
}