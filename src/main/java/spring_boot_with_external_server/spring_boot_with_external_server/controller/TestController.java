package spring_boot_with_external_server.spring_boot_with_external_server.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring_boot_with_external_server.spring_boot_with_external_server.entities.MessageType;

@RestController
@RequestMapping("/test")
public class TestController {

    private final MessageType message;

    public TestController(MessageType message){
        this.message = message;
    }

    @GetMapping("/say-hello")
    public MessageType sayHello(){

        message.setId(1);
        message.setContent("no content");

        return message;
    }
}
