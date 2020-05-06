package i.am.whp.util;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.Sha1Hash;

/**
 * @author wuhepeng
 * @date 2020/5/6
 */
public class HashUtil {

    /**
     * use sha1
     *
     * @param username
     * @return
     */
    public static String getSaltByUsername(String username) {
        return new Sha1Hash(username).toString();
    }

    /**
     * use md5，salt， hash5次
     *
     * @param password
     * @param salt
     * @return
     */
    public static String getCryptPassword(String password, String salt) {
        return new Md5Hash(password, salt, 5).toString();
    }
}
