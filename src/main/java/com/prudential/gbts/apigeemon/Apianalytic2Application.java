package com.prudential.gbts.apigeemon;

import com.prudential.gbts.apigeemon.Apilog;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQMessageConsumer;
import org.apache.activemq.ActiveMQSession;
import org.apache.activemq.RedeliveryPolicy;
import org.apache.activemq.command.ActiveMQDestination;
import org.apache.activemq.command.ActiveMQQueue;
import org.omg.PortableInterceptor.ACTIVE;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import org.springframework.jms.support.destination.BeanFactoryDestinationResolver;
import org.springframework.util.ErrorHandler;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Session;
import java.math.BigDecimal;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Apianalytic2Application {

	public static void main(String[] args) {
		SpringApplication.run(Apianalytic2Application.class, args);
	}
	
	
	  @Bean
	  public JmsListenerContainerFactory<?> myFactory(
	      ConnectionFactory connectionFactory,
	      DefaultJmsListenerContainerFactoryConfigurer configurer) {
	    DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
	    factory.setSessionAcknowledgeMode(javax.jms.Session.CLIENT_ACKNOWLEDGE);
	    factory.setErrorHandler(
	        new ErrorHandler() {
	          @Override
	          public void handleError(Throwable t) {
	            System.err.println("An error has occurred in the transaction");
	          }
	        });

	    factory.setErrorHandler(t -> System.out.println("An error has occurred in the transaction"));

	    configurer.configure(factory, connectionFactory);
	    return factory;
	  }
	  @Bean
	  public MessageConverter jacksonJmsMessageConverter() {
	    MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
	    converter.setTargetType(MessageType.TEXT);
	    converter.setTypeIdPropertyName("_type");
	    return converter;
	  }
}
