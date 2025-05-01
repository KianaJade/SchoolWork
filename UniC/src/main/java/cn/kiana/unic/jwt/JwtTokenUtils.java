package cn.kiana.unic.jwt;

import cn.hutool.core.date.DateUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;

public class JwtTokenUtils {

    private JwtTokenUtils() {
        throw new IllegalStateException("Utility class");
    }
    /**
     * 生成token
     */
    public static String getToken(String username,String sign){
        return  JWT.create()
                //签收者
                .withAudience(username)
                //主题
                .withSubject("token")
                //2小时候token过期
                .withExpiresAt(DateUtil.offsetHour(new Date(),240))
                //以password作为token的密钥
                .sign(Algorithm.HMAC256(sign));
    }
}