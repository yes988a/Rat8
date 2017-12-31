
chatsingle
    ---- typ_chat_single



chatgroup
    ---- 暂无


fri_add
    ---- typ_add_fri


fri_del
    ---- typ_del_fri



请求内容描述：
文本 图片 附件 链接   还有其他内容。不确定是什么类型时统一为0标示文本

    public final static int typ_des_txt = 725;//文本 
    public final static int typ_des_img = 185;//图片
    public final static int typ_des_file = 326;//附件
    public final static int typ_des_url = 486;//链接









/**
 * 公用的存放。。。如果，有专属某个模块，就当如到相应的模块下面。
 * <p>
 * 各参数前加固定前缀。
 * <p>
 * 网络访问时间：tim_web_
 * <p>
 * redis存储时间：tim_redis_
 * <p>
 * redis存储前缀： redis_
 * <p>
 * 路径前缀： file_
 * <p>
 * url业务标识前缀：url_login  作为para_url的value存在。    ，，，URL是A发送给B的路上(线)上的标识（线标识。）
 * <p>
 * 参数前缀： para_
 * <p>
 * 固定值标识：val_
 * <p>
 * type类型区分：typ_
 * <p>
 * <p>
 * <p>
 * 即时通讯，如果，没有及时通知到对方，发送方应该有知情权，知道对方没有收到，否则还在等待对方回复。。。  故以对方服务器实际收到信息为发送成功。
 * <p>
 * 因为好多操作是异步，没办法及时返回结果，所以，当接收者服务收到信息后，返回成功信号。
 */
