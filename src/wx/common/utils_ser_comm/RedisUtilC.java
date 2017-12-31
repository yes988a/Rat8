package wx.common.utils_ser_comm;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;


/**
 * 缓存，公用方法的封装。不涉及到业务逻辑。
 */
public class RedisUtilC {

    //前缀：redis 中取出来的userId。避免app自己传id(安全校验使用。)
    public final static String para_login_uid = "ueMs";

    /**
     * redis错误标识
     */
    public final static String val_error = "error";

    /**
     * 5分钟，临时存储，如：，短信验证吗发送吃次数限定，等等
     */
    public final static int tim_r_5m = 301;

    /**
     * 15分钟，验证有效时间。
     */
    public final static int tim_r_15m = 901;

    /**
     * 1小时
     */
    public final static int tim_r_1h = 3601;

    /**
     * 2小时，验证有效时间。
     */
    public final static int tim_r_2h = 7201;

    /**
     * 11小时，如我的token，加密解密秘钥（这个应该一旦报错应：数据库取秘钥，还是如果不报错更新redis，如果报错， 加密有问题）
     */
    public final static int tim_r_11h = 39601;

    /**
     * redis 3天，群数目，群人员ID集合，人员所有的群等
     */
    public final static int tim_r_3d = 259203;

    /**
     * redis 13天，群数目，群人员ID集合，人员所有的群等
     */
    public final static int tim_r_13d = 950002;

    /**
     * redis 一个月，IP列表。二维码
     */
    public final static int tim_r_30d = 950003;

    /**
     * redis 三个月，接近永远。,IP列表。二维码
     */
    public final static int tim_r_90d = 950001;

    /**
     * 黑名单，时间。 单位秒
     */
    public final static int tim_r_6m = 361;
    public final static int tim_r_8m = 481;
    public final static int tim_r_10m = 601;
    public final static int tim_r_20m = 1201;

    /**---------------好像没有启动redis就会报错，集群时可能不一样，应该是jedis返回的结果不一样。？-----------
     *  redis方法      ------------------------    redis方法  --------------------*/
    /**
     * 插入 string jedis
     *
     * @param key
     * @param value
     * @param seconds
     * @return true为成功，false失败
     */
    public final static Boolean setR(String key, String value, Integer seconds) {

        Jedis je = SerUtilC.SPRING.getBean("redis", JedisPool.class).getResource();
        boolean bb = false;
        try {
            je.set(key, value);
            je.expire(key, seconds);
            bb = true;
            je.close();
        } catch (Exception e) {
            je.close();
        }

        return bb;
    }

    /**
     * 获取 string jedis
     *
     * @param key
     * @param seconds 如果不为null，更新销毁时间。
     * @return
     */
    public final static String getR(String key, Integer seconds) {
        Jedis je = SerUtilC.SPRING.getBean("redis", JedisPool.class).getResource();

        String get = val_error;
        try {
            get = je.get(key);
            if (seconds != null) {
                je.expire(key, seconds);
            }
        } catch (Exception e) {
        }
        return get;
    }

    /**
     * @param key
     * @return true存在，false不存在，null是redis错误
     */
    public final static Boolean existR(String key) {
        Jedis je = SerUtilC.SPRING.getBean("redis", JedisPool.class).getResource();

        Boolean b = null;
        try {
            b = je.exists(key);
        } catch (Exception e) {
        }
        return b;
    }

    /**
     * 删除 key jedis
     */
    public final static void delR(String key) {
        JedisPool redisTemplate = SerUtilC.SPRING.getBean("redis", JedisPool.class);
        Jedis je = redisTemplate.getResource();
        try {
            je.del(key);
        } catch (Exception e) {
        }
    }
}
