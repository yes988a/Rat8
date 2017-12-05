package wx.common.generator.base;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserFullMapper {
    long countByExample(UserFullExample example);

    int deleteByExample(UserFullExample example);

    int deleteByPrimaryKey(String uid);

    int insert(UserFull record);

    int insertSelective(UserFull record);

    List<UserFull> selectByExample(UserFullExample example);

    UserFull selectByPrimaryKey(String uid);

    int updateByExampleSelective(@Param("record") UserFull record, @Param("example") UserFullExample example);

    int updateByExample(@Param("record") UserFull record, @Param("example") UserFullExample example);

    int updateByPrimaryKeySelective(UserFull record);

    int updateByPrimaryKey(UserFull record);

    default void aa(){

    }
}