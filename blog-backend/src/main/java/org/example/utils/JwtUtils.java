package org.example.utils;

import ch.qos.logback.core.util.TimeUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.xml.crypto.Data;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * JwtUtils 类提供了与 JSON Web Token (JWT) 相关的实用方法。
 * 它包括创建、验证、失效和解析 JWT 的方法，以及将 JWT 转换为用户信息的功能。
 */
@Component
public class JwtUtils {

    @Value("${spring.security.jwt.key}")
    String key;

    @Value("${spring.security.jwt.expire}")
    int expire;

    @Resource
    StringRedisTemplate template;

    /**
     * 使 JWT 失效。
     *
     * @param headerToken 包含 JWT 的请求头字符串。
     * @return 如果 JWT 成功失效，返回 true；否则返回 false。
     */
    public boolean invalidateJwt(String headerToken){
        String token = convertToken(headerToken);
        if(token == null) return  false;
        Algorithm algorithm = Algorithm.HMAC256(key);
        JWTVerifier jwtVerifier = JWT.require(algorithm).build();
        try{
            DecodedJWT jwt = jwtVerifier.verify(token);
            String id = jwt.getId();
            return deleteToken(id,jwt.getExpiresAt());

        } catch (JWTVerificationException e){
            return false;
        }
    }

    /**
     * 将 JWT 标记为失效，并存储在 Redis 中。
     *
     * @param uuid JWT 的唯一标识符。
     * @param time JWT 的过期时间。
     * @return 如果 JWT 成功标记为失效，返回 true；否则返回 false。
     */
    private boolean deleteToken(String uuid,Date time){
        if(this.isInvalidToken(uuid))
            return  false;
        Date now = new Date();
        long expire = Math.max(time.getTime()-now.getTime(),0);
        template.opsForValue().set(Const.JWT_BLACK_LIST + uuid,"",expire, TimeUnit.MICROSECONDS);
        return true;
    }

    /**
     * 判断 JWT 是否已经失效。
     *
     * @param uuid JWT 的唯一标识符。
     * @return 如果 JWT 已经失效，返回 true；否则返回 false。
     */
    private boolean isInvalidToken(String uuid){
        return Boolean.TRUE.equals(template.hasKey(Const.JWT_BLACK_LIST + uuid));
    }

    /**
     * 解析 JWT 并检查其有效性。
     *
     * @param headerToken 包含 JWT 的请求头字符串。
     * @return 如果 JWT 有效，返回解析后的 JWT；否则返回 null。
     */
    public DecodedJWT resolveJwt(String headerToken){
        String token = convertToken(headerToken);
        if(token == null) return  null;
        Algorithm algorithm = Algorithm.HMAC256(key);
        JWTVerifier jwtVerifier = JWT.require(algorithm).build();
        try{
            DecodedJWT verify = jwtVerifier.verify(token);
            if(this.isInvalidToken(verify.getId()))
                return  null;
            Date expiresAt = verify.getExpiresAt();
            return new Date().after(expiresAt)? null : verify;
        } catch (JWTVerificationException e){
            return null;
        }
    }


    /**
     * 创建一个新的 JWT。
     *
     * @param details 用户详细信息。
     * @param id 用户 ID。
     * @param username 用户名。
     * @return 创建的 JWT 字符串。
     */
    public String createJwt(UserDetails details,int id,String username){
        Algorithm algorithm = Algorithm.HMAC256(key);
        Date expire = this.expireTime();
        return JWT.create()
               .withJWTId(UUID.randomUUID().toString())
               .withClaim("id",id)
               .withClaim("name",username)
               .withClaim("authorities",details.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList())
               .withIssuedAt(new Date())
               .withExpiresAt(expire)
               .sign(algorithm);
    }
    /**
     * 计算 JWT 的过期时间。
     * @return JWT 的过期时间。
     */
    public Date expireTime(){
        //日历
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR,expire *24);
        return  calendar.getTime();
    }

    /**
     * 将解析后的 JWT 转换为 UserDetails 对象。
     *
     * @param jwt 解析后的 JWT。
     * @return 对应的 UserDetails 对象。
     */
    public UserDetails toUser(DecodedJWT jwt){
        Map<String, Claim> claims = jwt.getClaims();
        return User
               .withUsername(claims.get("name").asString())
               .password("******")
               .authorities(claims.get("authorities").asArray(String.class))
               .build();
    }

    /**
     * 从解析后的 JWT 中获取用户 ID。
     *
     * @param jwt 解析后的 JWT。
     * @return 用户 ID。
     */
    public Integer toId(DecodedJWT jwt){
        Map<String, Claim> claims = jwt.getClaims();
        return claims.get("id").asInt();
    }


    /**
     * 从请求头中提取 JWT 字符串。
     *
     * @param headerToken 包含 JWT 的请求头字符串。
     * @return 提取的 JWT 字符串，如果请求头中不存在 JWT，返回 null。
     */
    private String convertToken(String headerToken){
        if(headerToken == null ||!headerToken.startsWith("Bearer "))
            return null;
        return headerToken.substring(7);
    }
}
