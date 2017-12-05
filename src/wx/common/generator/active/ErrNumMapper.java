package wx.common.generator.active;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ErrNumMapper {
    long countByExample(ErrNumExample example);

    int deleteByExample(ErrNumExample example);

    int deleteByPrimaryKey(String uniqueId);

    int insert(ErrNum record);

    int insertSelective(ErrNum record);

    List<ErrNum> selectByExample(ErrNumExample example);

    ErrNum selectByPrimaryKey(String uniqueId);

    int updateByExampleSelective(@Param("record") ErrNum record, @Param("example") ErrNumExample example);

    int updateByExample(@Param("record") ErrNum record, @Param("example") ErrNumExample example);

    int updateByPrimaryKeySelective(ErrNum record);

    int updateByPrimaryKey(ErrNum record);
}