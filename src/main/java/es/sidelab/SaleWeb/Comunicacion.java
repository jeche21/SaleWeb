package es.sidelab.SaleWeb;

import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class Comunicacion {
	
	private static final String URL="http://100.114.226.45:8083/pedido";
	
	public void enviar(String direccion, String subject, String body){
		RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, String> email= new LinkedMultiValueMap<String, String>();
        email.add("email", direccion);
        email.add("subject", subject);
        email.add("body", body);
        ResponseEntity<String> response =  restTemplate.postForEntity(URL,email,String.class);
	}
}
