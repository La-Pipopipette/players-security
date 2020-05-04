package fr.pipopipette.players.service;

import io.smallrye.jwt.build.Jwt;
import io.smallrye.jwt.build.JwtClaimsBuilder;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.jwt.Claims;

import javax.enterprise.context.ApplicationScoped;
import java.util.Map;


@ApplicationScoped
public class JwtService {

    @ConfigProperty(name = "mp.jwt.verify.issuer") String issuer;
    @ConfigProperty(name = "pipopipette.jwt.duration") Integer duration;

    public String generateTokenString(Integer userId, String userName) {

        JwtClaimsBuilder claims = Jwt.claims(Map.of(
                "userId", userId
        ));
        long currentTimeInSecs = currentTimeInSecs();
        long exp = currentTimeInSecs + duration;

        claims.issuedAt(currentTimeInSecs);
        claims.claim(Claims.auth_time.name(), currentTimeInSecs);
        claims.issuer(issuer);
        claims.preferredUserName(userName);
        claims.groups("identified");
        claims.expiresAt(exp);

        return claims.jws().signatureKeyId("privateKey.pem").sign();
    }

    /**
     * @return the current time in seconds since epoch
     */
    public int currentTimeInSecs() {
        long currentTimeMS = System.currentTimeMillis();
        return (int) (currentTimeMS / 1000);
    }

}