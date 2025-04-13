package spring_boot_with_external_server.spring_boot_with_external_server.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring_boot_with_external_server.spring_boot_with_external_server.entities.MessageType;

@Configuration
public class MessageDefinition {

    @Bean
    public MessageType messageType(){
        return new MessageType();
    }

    @Bean
    public String messageType2(){
        return "message type is TEXT";
    }
}
