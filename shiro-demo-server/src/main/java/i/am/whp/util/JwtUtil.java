package i.am.whp.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * @author wuhepeng on 2020/5/13
 */
@Slf4j
public class JwtUtil {

    private JwtUtil() {
    }

    // 过期时间 10 min
    private static final long EXPIRE_TIME = 10 * 60 * 1000;
    // 签名密钥
    private static final String SECRET = "q!w@e#r$";

    /**
     * 生成 jwt
     *
     * @param username 用户名
     * @return jwt
     */
    public static String createToken(String username) {
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        Algorithm algorithm = Algorithm.HMAC256(SECRET);
        return JWT.create()
                .withClaim("username", username)// Payload 存入 username
                .withExpiresAt(date)// 设置过期时间
                .sign(algorithm);// 签名
    }

    /**
     * 验证 token 是否有效
     *
     * @param token jwt
     */
    public static void verify(String token) {
        Algorithm algorithm = Algorithm.HMAC256(SECRET);
        JWTVerifier verifier = JWT.require(algorithm).build();
        verifier.verify(token);
    }

    /**
     * 获取 Payload 中的用户名
     *
     * @param token jwt
     * @return 用户名
     */
    public static String getUsername(String token) {
        return JWT.decode(token).getClaim("username").asString();
    }

}
