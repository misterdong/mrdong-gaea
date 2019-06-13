package com.mrdong.gaeasso.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.jsonwebtoken.*;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Map;

/**
 * @description: Jwt token工具集
 * @author: liudong
 * @date: 2019-06-13 16:37
 **/
public class JwtTokenUtils {

    /**
     * 加密
     * @param payLoad
     * @return
     */
    public static String generatorToken(Map<String,Object> payLoad){
           String s = JSON.toJSONString(payLoad);
            return Jwts.builder().setPayload(s).signWith(SignatureAlgorithm.HS256,generatorKey()).compact();
    }

    /**
     * 解密
     * @param token
     * @return
     */
    public static Claims phaseToken(String token){
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(generatorKey()).parseClaimsJws(token);
        return claimsJws.getBody();
    }

    /**
     * 生成KEY
     * @return
     */
    private static Key generatorKey(){
        SignatureAlgorithm hs256 = SignatureAlgorithm.HS256;
        byte[] bin = DatatypeConverter.parseBase64Binary("ab501e8a725946cda3d55a524b1a4cdd");
        Key key = new SecretKeySpec(bin, hs256.getJcaName());
        return key;
    }
}

