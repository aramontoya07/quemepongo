package utils;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import spark.Request;
import usuario.Usuario;

import java.security.Key;
import java.util.Date;

public class JwtManager {
    private Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private long SESSION_EXPIRATION_TIME = 1200000;
    private static JwtManager instancia = null;

    public static JwtManager instancia(){
        if(instancia == null){
            instancia = new JwtManager();
        }
        return instancia;
    }

    //TODO Puedo crear una clase singleton como esta de JWT, con un generarToken que funcione similar a este. De
    //esa forma, puedo acceder de todos los controllers
    public String generarToken(Usuario usuarioBuscado) {
        return Jwts.builder()
                .signWith(key) //Clave
                .setSubject(((Integer) usuarioBuscado.getId()).toString()) //Id de usuario para validar luego
                .claim("type", usuarioBuscado.getClass().getSimpleName()) //tipo de usuario
                .setIssuedAt(new Date(System.currentTimeMillis())) //Momento de creacion del jwt
                .setExpiration(new Date(System.currentTimeMillis()+SESSION_EXPIRATION_TIME)) //Tiempo de expiracion de sesion
                .compact();
    }

    public int userDeToken(Request request) throws ExpiredJwtException, IllegalArgumentException, JwtException {
        String token = request.headers("token");
        Integer id = Integer.parseInt(Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token)
                .getBody().getSubject());
        return id;
    }

}
