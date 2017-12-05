package wx.common.generator.active;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StorageTaskMapper {
    long countByExample(StorageTaskExample example);

    int deleteByExample(StorageTaskExample example);

    int deleteByPrimaryKey(String uuid);

    int insert(StorageTask record);

    int insertSelective(StorageTask record);

    List<StorageTask> selectByExampleWithBLOBs(StorageTaskExample example);

    List<StorageTask> selectByExample(StorageTaskExample example);

    StorageTask selectByPrimaryKey(String uuid);

    int updateByExampleSelective(@Param("record") StorageTask record, @Param("example") StorageTaskExample example);

    int updateByExampleWithBLOBs(@Param("record") StorageTask record, @Param("example") StorageTaskExample example);

    int updateByExample(@Param("record") StorageTask record, @Param("example") StorageTaskExample example);

    int updateByPrimaryKeySelective(StorageTask record);

    int updateByPrimaryKeyWithBLOBs(StorageTask record);

    int updateByPrimaryKey(StorageTask record);
}