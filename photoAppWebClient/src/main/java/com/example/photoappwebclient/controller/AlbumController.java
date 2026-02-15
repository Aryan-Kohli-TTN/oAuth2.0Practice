package com.example.photoappwebclient.controller;


import com.example.photoappwebclient.response.AlbumRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.lang.reflect.Array;
import java.util.Arrays;

@Controller
public class AlbumController {

    @Autowired
    OAuth2AuthorizedClientService authorizedClientService;

    @GetMapping("/albums")
    public String getAlbums(Model model, @AuthenticationPrincipal OidcUser principal){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        OAuth2AuthenticationToken auth2AuthenticationToken =(OAuth2AuthenticationToken) authentication;
        OAuth2AuthorizedClient auth2AuthorizedClient= authorizedClientService.loadAuthorizedClient(auth2AuthenticationToken.getAuthorizedClientRegistrationId(),auth2AuthenticationToken.getName());
        System.out.println(auth2AuthorizedClient.getAccessToken().getTokenValue());
        System.out.println(principal.getIdToken().getTokenValue());
        AlbumRest album = new AlbumRest();
        album.setAlbumId("ID1");
        album.setAlbumTitle("Album 1");

        AlbumRest album2 = new AlbumRest();
        album2.setAlbumId("ID2");
        album2.setAlbumTitle("Album 2");

        model.addAttribute("albums", Arrays.asList(album,album2));
        return "albums";
    }
}
