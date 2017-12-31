package wx.service.manager;

/**
 * 图片管理服务器。
 */
public class ImageManager {

/*
    //插入数据库。  jo中，uid即接收者ID，fid即发送者ID
    //发送给好友.
    private boolean insertChat() {
        //校验，user权限。
        //同一个服务器，直接插入。
        //不同服务器，转发。

        boolean filesucc = false;// 防止图片上传失败

        if (ChatUtilA.typ_des_img == des_typ) {
            if (into.get(ChatUtilA.para_f_str) == null) {
                filesucc = false;
            } else {
                Calendar instance = Calendar.getInstance();
                String yearday = instance.get(Calendar.YEAR)
                        + ""
                        + instance.get(Calendar.DAY_OF_YEAR);
                String path = File.separator
                        + FilesUtilC.file_tempimg + res
                        + File.separator + yearday
                        + File.separator + txt;
                File file = new File(path);
                byte[] filebyte = Base64.decodeBase64(into.get(ChatUtilA.para_f_str).getAsString());
                try {
                    FileUtils.writeByteArrayToFile(file,
                            filebyte);
                    chat.setDes(path);
                    filesucc = true;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {// 不是图片和附件上传，直接跳过啦文件操作
            filesucc = true;
        }
        if (filesucc) {
            //发送给friend
            chatMapper.insert(chat);
        }
        return filesucc;
    }*/
}
