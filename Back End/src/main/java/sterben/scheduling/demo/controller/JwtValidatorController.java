package sterben.scheduling.demo.controller;


import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import sterben.scheduling.demo.entity.User;
import sterben.scheduling.demo.jwt.JwtAuthenticationFilter;
import sterben.scheduling.demo.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin
public class JwtValidatorController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private JwtAuthenticationFilter jwtAu;

    @PostMapping(path = "/jwt", consumes = "application/json")
    public Long getIdRoleFromJwt(HttpServletRequest request) {
        String jwt = jwtAu.getJwtFromRequest(request);
        String JWT_SECRET = "PhanMinhChuan";
        try {
            Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(jwt);
            Claims claims = Jwts.parser()
                    .setSigningKey(JWT_SECRET)
                    .parseClaimsJws(jwt)
                    .getBody();

            long idUser =  Integer.parseInt(claims.getSubject());
            User userJwt = userRepository.findById(idUser);
            return idUser;

        } catch (MalformedJwtException ex) {
            System.out.println("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            System.out.println("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            System.out.println("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            System.out.println("JWT claims string is empty.");
        }
        return null;
    }
}
