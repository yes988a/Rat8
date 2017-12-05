package wx.common.generator.base;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GuserMapper {
    long countByExample(GuserExample example);

    int deleteByExample(GuserExample example);

    int deleteByPrimaryKey(GuserKey key);

    int insert(Guser record);

    int insertSelective(Guser record);

    List<Guser> selectByExample(GuserExample example);

    Guser selectByPrimaryKey(GuserKey key);

    int updateByExampleSelective(@Param("record") Guser record, @Param("example") GuserExample example);

    int updateByExample(@Param("record") Guser record, @Param("example") GuserExample example);

    int updateByPrimaryKeySelective(Guser record);

    int updateByPrimaryKey(Guser record);
}