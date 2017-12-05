package wx.common.generator.base;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserUniqueMapper {
    long countByExample(UserUniqueExample example);

    int deleteByExample(UserUniqueExample example);

    int deleteByPrimaryKey(String uid);

    int insert(UserUnique record);

    int insertSelective(UserUnique record);

    List<UserUnique> selectByExample(UserUniqueExample example);

    UserUnique selectByPrimaryKey(String uid);

    int updateByExampleSelective(@Param("record") UserUnique record, @Param("example") UserUniqueExample example);

    int updateByExample(@Param("record") UserUnique record, @Param("example") UserUniqueExample example);

    int updateByPrimaryKeySelective(UserUnique record);

    int updateByPrimaryKey(UserUnique record);
}