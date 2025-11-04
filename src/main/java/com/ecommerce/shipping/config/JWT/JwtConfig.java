package com.ecommerce.shipping.config.JWT;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
//Anotation Component đánh dấu class này là một bean ==> Giúp cho Spring quản lý bởi spring container, tự động tạo một instance cho class đó
// Cho phép inject vào để sử dụng @Autowired
public class JwtConfig {

    @Value("${jwt.secret}") //@Value("${}") ==> Dùng để gọi các config bên trong file application.properties.
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration;

    @Value("${jwt.issuer}")
    private String issuer;

    public String getSecret() {
        return secret;
    }

    public long getExpiration() { // Thời gian hết hạn
        return expiration;
    }

    public String getIssuer() {
        return issuer; // tổ chức phát hành token JWT
    }
}
