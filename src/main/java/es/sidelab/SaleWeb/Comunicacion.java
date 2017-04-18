package es.sidelab.SaleWeb;

import es.sidelab.Rest.RespuestaEmail;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import java.io.*;
import java.net.UnknownHostException;

public class Comunicacion {
    public  void main(String email,String subject,String body) throws UnknownHostException, IOException{
        RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
        map.add("email", email);
        map.add("subject", subject);
        map.add("body", body);
        ResponseEntity<RespuestaEmail> response =  restTemplate.postForEntity("https://localhost:8080/sendEmail",map,RespuestaEmail.class);

    }
}