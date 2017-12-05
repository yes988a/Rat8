package wx.common.generator.base;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UrelationMapper {
    long countByExample(UrelationExample example);

    int deleteByExample(UrelationExample example);

    int deleteByPrimaryKey(UrelationKey key);

    int insert(Urelation record);

    int insertSelective(Urelation record);

    List<Urelation> selectByExample(UrelationExample example);

    Urelation selectByPrimaryKey(UrelationKey key);

    int updateByExampleSelective(@Param("record") Urelation record, @Param("example") UrelationExample example);

    int updateByExample(@Param("record") Urelation record, @Param("example") UrelationExample example);

    int updateByPrimaryKeySelective(Urelation record);

    int updateByPrimaryKey(Urelation record);
}