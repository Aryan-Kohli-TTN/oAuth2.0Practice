package com.example.demo.converter;


import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class KeyCloakConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

    private final JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();

    @Override
    public Collection<GrantedAuthority> convert(Jwt source) {
        Collection<GrantedAuthority> authorities = new ArrayList<>(jwtGrantedAuthoritiesConverter.convert(source));

        Map<String, Object> realmAccess = source.getClaim("realm_access");
        if (realmAccess != null && !realmAccess.isEmpty() && realmAccess.get("roles") != null) {
            List<String> roles = (List<String>) realmAccess.get("roles");
            roles.stream()
                    .map(role -> "ROLE_" + role)
                    .map(SimpleGrantedAuthority::new)
                    .forEach(authorities::add);
        }
        return authorities;
    }
}
