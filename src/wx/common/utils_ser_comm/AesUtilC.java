package wx.common.utils_ser_comm;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

// 仅仅测试使用。。。。。 其他类里面已经包含。
public class AesUtilC {

    public static String key = "WZOoXwGv4OvxkeRu5op9sQ==";

    public static void main(String[] args) throws UnsupportedEncodingException {
        System.out.println(getRawKey("www.weixiao-world.com"));// 1UyM9SkO3/OumSOgwfXqgA==
        // ，每次都一样
        String mes = "中文也有jlsjfasjj2458498&^%$#%$^&&+-~~~~~";
        byte[] base64 = Base64.decodeBase64(key);
        String encrypt = encrypt_Inner(base64, mes);
        System.out.println(encrypt);
        String decrypt = decrypt_Inner(encrypt, base64);
        System.out.println(decrypt);
    }

    /**
     * 加密，无秘钥，生成
     *
     * @param seed     种子数据
     * @param clearPwd 明文字符串
     * @return
     */
    public static String encrypt(String seed, String clearPwd) {
        byte[] bs = null;
        try {
            bs = getRawKey_Byte(seed);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (bs == null) {
            return null;  // 严重错误。
        } else {
            return encrypt_Inner(bs, clearPwd);
        }
    }

    /**
     * 解密，无秘钥，生成
     *
     * @param encrypted 密文字节数组
     * @param seed      种子数据
     * @return
     */
    public static String decrypt(String encrypted, String seed) {
        byte[] bs = null;
        try {
            bs = getRawKey_Byte(seed);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (bs == null) {
            return null;  // 严重错误。
        } else {
            return decrypt_Inner(encrypted, bs);
        }
    }

    /**
     * 加密，已有秘钥使用
     *
     * @param rawKey   密钥
     * @param clearPwd 明文字符串
     * @return 密文字节数组
     */
    public static String encrypt_Inner(byte[] rawKey, String clearPwd) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(rawKey, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            byte[] encypted = cipher.doFinal(clearPwd.getBytes("UTF-8"));
            return Base64.encodeBase64String(encypted);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 解密，已有秘钥使用
     *
     * @param encrypted 密文字节数组,base64
     * @param rawKey    密钥
     * @return 解密后的字符串
     */
    public static String decrypt_Inner(String encrypted, byte[] rawKey) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(rawKey, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
            byte[] decodeBase64 = Base64.decodeBase64(encrypted);
            byte[] decrypted = cipher.doFinal(decodeBase64);
            return new String(decrypted, "UTF-8");
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取秘钥，string，base64，用于存储。便于使用。
     *
     * @param seed 种子数据
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String getRawKey(String seed) {
        try {
            byte[] rawKey = getRawKey_Byte(seed);
            return Base64.encodeBase64String(rawKey);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取秘钥， byte
     *
     * @param seed 种子数据
     * @return
     * @throws UnsupportedEncodingException
     */
    public static byte[] getRawKey_Byte(String seed) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        secureRandom.setSeed(seed.getBytes("UTF-8"));
        // AES加密数据块分组长度必须为128比特，密钥长度可以是128比特、192比特、256比特中的任意一个
        kgen.init(128, secureRandom);
        SecretKey secretKey = kgen.generateKey();
        return secretKey.getEncoded();
    }
}