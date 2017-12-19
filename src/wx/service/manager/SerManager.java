package wx.service.manager;

import com.google.gson.JsonObject;
import wx.common.generator.active.StorageTaskMapper;
import wx.common.utils_server.SerUtil;
import wx.common.utils_server.WxUtil;

import javax.annotation.Resource;


public class SerManager {

    @Resource
    private StorageTaskMapper storageTaskMapper;

    /**
     * 删除临时存储。
     */
    public void ser_del_storage(JsonObject into) {
        if (into.get(SerUtil.para_storage_need_return_id) != null) {
            storageTaskMapper.deleteByPrimaryKey(into.get(SerUtil.para_storage_need_return_id).getAsString());
        }
    }
}
