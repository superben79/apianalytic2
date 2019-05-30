package com.prudential.gbts.apigeemon;

import com.prudential.gbts.apigeemon.Apilog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class Receiver {

  private int count = 1;

  @JmsListener(destination = "APILogQueue", containerFactory = "myFactory")
  public void receiveMessage(Apilog apilog) {
    System.out.println("<" + count + "> Received <" + apilog + ">");
    count++;
    RestTemplate rt = new RestTemplate();
    String uri = new String("http://10.0.0.89:8080");
    ResponseEntity<String> responseEntity = rt.postForEntity(uri, apilog, String.class);
    System.out.println(responseEntity.getStatusCodeValue());
  }
}
