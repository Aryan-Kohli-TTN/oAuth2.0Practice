package com.example.remoteiuserstorageprovide;

import jakarta.ws.rs.PathParam;
import org.keycloak.http.simple.SimpleHttp;
import org.keycloak.http.simple.SimpleHttpRequest;
import org.keycloak.models.KeycloakSession;

public class UserApiLegacyService {
    private final KeycloakSession keycloakSession;

    public UserApiLegacyService(KeycloakSession keycloakSession) {
        this.keycloakSession = keycloakSession;
    }
    public User getUserbyUsername(String username){
        User user = null;
        try {
            user= SimpleHttp.create(keycloakSession).doGet("http://localhost:8099/users/"+username).asJson(User.class);
        }
        catch (Exception e){
            System.out.println("Exception occured while fetching username from database "+e);
        }
        return user;
    }

    public VerifyPasswordResponse verifyuserPassword(@PathParam("username")String username,String password){
        SimpleHttpRequest simpleHttp = SimpleHttp.create(keycloakSession)
                .doPost("http://localhost:8099/users/"+username+"/verify-password");

        simpleHttp.param("password",password);
        simpleHttp.param("Content-Type","application/x-www-form-urlencoded");
        VerifyPasswordResponse verifyPasswordResponse=null;
        try {
            verifyPasswordResponse=simpleHttp.asJson(VerifyPasswordResponse.class);
        }
        catch (Exception e){
            System.out.println("Exception occured while fetching verifying password from database "+e);
        }

        return verifyPasswordResponse;
    }
}
