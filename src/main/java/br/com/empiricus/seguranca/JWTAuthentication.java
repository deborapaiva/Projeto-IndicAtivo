package br.com.empiricus.seguranca;

import br.com.empiricus.model.UserLogin;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class JWTAuthentication extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
	
	// tempo de expiração do token em milissegundos - 9 dias
	private static final long expiration = 864000000;
	
	// segredo: substituir para uma string codificada
	private static final String secret = "SEGREDO";

    public JWTAuthentication(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        // busca as credenciais digitadas pelo usuário
        UserLogin u = null;
        try {
            u = new ObjectMapper().readValue(request.getInputStream(), UserLogin.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        UsernamePasswordAuthenticationToken authResult = new UsernamePasswordAuthenticationToken(
                u.getUsername(), u.getPassword()
        );

        // realiza a autenticação
        return authenticationManager.authenticate(authResult);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) {
        String username = ((UserDetails) authResult.getPrincipal()).getUsername();

        // criação do token
        String jwtToken = JWT.create()
                .withSubject(username)
                .withExpiresAt(new Date(System.currentTimeMillis() + expiration))
                .sign(Algorithm.HMAC512(secret.getBytes()));

        String headerBody = username + " " + jwtToken;

        response.setHeader(HttpHeaders.AUTHORIZATION , headerBody);
    }
}