/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.ordersoauthwebclient;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class OrdersController {

	@GetMapping("/orders")
	public String getOrders(Model model, @RegisteredOAuth2AuthorizedClient("users-client-oidc") OAuth2AuthorizedClient auth2AuthorizedClient){


		String jwtToken = auth2AuthorizedClient.getAccessToken().getTokenValue();

		HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization","Bearer "+jwtToken);

		HttpEntity<Object> entity = new HttpEntity<>(httpHeaders);
        RestTemplate restTemplate = new RestTemplate();
		String url ="http://127.0.0.1:8093/orders";
        ResponseEntity<List<OrdersRest>> response= restTemplate.exchange(url, HttpMethod.GET, entity, new ParameterizedTypeReference<List<OrdersRest>>() {});
		List<OrdersRest> orderRests = response.getBody();
		model.addAttribute("orders", orderRests);

		return "orders-page";

	}
}
