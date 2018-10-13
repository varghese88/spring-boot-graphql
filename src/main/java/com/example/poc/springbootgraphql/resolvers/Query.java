package com.example.poc.springbootgraphql.resolvers;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.example.poc.springbootgraphql.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class Query implements GraphQLQueryResolver {

    private static final String APP_URL = "app.common.url";

    @Autowired
    Environment env;

    @Autowired
    RestTemplate restTemplate;

    public List<User> users(){

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> requestEntity = new HttpEntity<>(httpHeaders);
        String url = env.getProperty(APP_URL);

        ResponseEntity<List<User>> responseEntity = restTemplate.exchange(
            url+"/users",
            HttpMethod.GET,
            requestEntity,
            new ParameterizedTypeReference<List<User>>(){});
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            return responseEntity.getBody();
        }
        return new ArrayList<>();
    }
}
