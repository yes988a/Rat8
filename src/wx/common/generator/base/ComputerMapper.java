package wx.common.generator.base;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ComputerMapper {
    long countByExample(ComputerExample example);

    int deleteByExample(ComputerExample example);

    int deleteByPrimaryKey(String cid);

    int insert(Computer record);

    int insertSelective(Computer record);

    List<Computer> selectByExample(ComputerExample example);

    Computer selectByPrimaryKey(String cid);

    int updateByExampleSelective(@Param("record") Computer record, @Param("example") ComputerExample example);

    int updateByExample(@Param("record") Computer record, @Param("example") ComputerExample example);

    int updateByPrimaryKeySelective(Computer record);

    int updateByPrimaryKey(Computer record);
}