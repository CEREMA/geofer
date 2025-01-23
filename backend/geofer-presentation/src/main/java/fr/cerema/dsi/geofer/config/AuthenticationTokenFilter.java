package fr.cerema.dsi.geofer.config;

import fr.cerema.dsi.commons.security.auth.jwt.exceptions.InvalidTokenException;
import fr.cerema.dsi.geofer.services.CustomUserDetailsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.core.env.Environment;

import java.io.IOException;

@Component
public class AuthenticationTokenFilter extends OncePerRequestFilter {
  @Autowired
  private Environment env;

  // Nommé différemment de "logger" car celui-ci existe déjà dans le GenericBeanFilter (JCL)
  private static final Logger slfLogger = LoggerFactory.getLogger(AuthenticationTokenFilter.class);

  private static final String JWT_EMAIL = "email";
  private static final String JWT_ORIONID = "sub";
  private static final String JWT_BEARER = "Bearer";
  public static final String AUTHORIZATION_HEADER = "Authorization";

  private static final String JWT_TYPE = "typ";

  @Value("${info.app.issuer}")
  private String urlSSO;

  @Value("${info.app.audience}")
  private String tokenAudience;


  private final CustomUserDetailsService userDetailsService;

  public AuthenticationTokenFilter(CustomUserDetailsService userDetailsService) {
    this.userDetailsService = userDetailsService;
  }


  @Override
  protected void doFilterInternal(HttpServletRequest request, @NonNull HttpServletResponse response,
                                  @NonNull FilterChain filterChain) throws ServletException, IOException {
    final String requestTokenHeader = request.getHeader(AUTHORIZATION_HEADER);
    if (requestTokenHeader != null && !StringUtils.isEmpty(requestTokenHeader)) {
      String jwtToken = this.resolveToken(requestTokenHeader);
      if (jwtToken != null) {
        String[] splitToken = jwtToken.split("\\.");
        String unsignedToken = splitToken[0] + "." + splitToken[1] + ".";
        Jwt<Header, Claims> jwsClaims = Jwts.parserBuilder().build().parseClaimsJwt(unsignedToken);
        String username = (String) jwsClaims.getBody().get(JWT_EMAIL);
        String orionId = (String) jwsClaims.getBody().get(JWT_ORIONID);

        if (orionId != null && username != null) {
          UserDetails userDetails = this.userDetailsService.loadAndMajInfoUser(orionId, username);
          this.isValidToken(jwsClaims);
          UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
            new UsernamePasswordAuthenticationToken(
              userDetails, null, userDetails.getAuthorities());
          usernamePasswordAuthenticationToken
            .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
          SecurityContextHolder.getContext()
            .setAuthentication(usernamePasswordAuthenticationToken);
        }
      }
    }

    filterChain.doFilter(request, response);
  }

  private String resolveToken(String requestTokenHeader) {
    if (requestTokenHeader.startsWith(JWT_BEARER)) {
      return requestTokenHeader.substring(7);
    }
    return null;
  }


  private void isValidToken(Jwt<Header, Claims> jwtToken) {
    /*String devMode = env.getProperty("CADRICIEL_DEV");
    this.checkType(jwtToken.getBody());
    if (devMode == null || !devMode.equalsIgnoreCase("true")) {
      this.checkIssuer(jwtToken.getBody());
      this.checkAudience(jwtToken.getBody());
    }*/
  }

  private void checkType(Claims jwsClaims) {
    String type = jwsClaims.get(JWT_TYPE, String.class);

    if (!StringUtils.equalsIgnoreCase(type, JWT_BEARER)) {
      slfLogger.warn("Type non conforme, type: {}, attendu: {}", type, JWT_BEARER);
      throw new InvalidTokenException("Le jeton d'authentification n'est pas du bon type.");
    }
  }

  private void checkIssuer(Claims jwsClaims) {
    String issuer = jwsClaims.getIssuer();

    if (!StringUtils.equalsIgnoreCase(issuer, urlSSO)) {
      slfLogger.warn("Issuer non conforme, issuer: {}, attendu: {}", issuer, urlSSO);
      throw new InvalidTokenException(
        "Le jeton d'authentification ne provient pas de la bonne source.");
    }
  }

  private void checkAudience(Claims jwsClaims) {
    String audience = jwsClaims.getAudience();

    if (!StringUtils.containsAnyIgnoreCase(audience, tokenAudience)) {
      slfLogger.warn(
        "Audience non conforme, attendue: {}, fournie: {}", tokenAudience, audience);
      throw new InvalidTokenException("Le jeton d'authentification ne nous est pas destiné.");
    }
  }

}
