package org.yqj.boot.demo.doc;

import org.springframework.core.env.SystemEnvironmentPropertySource;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.yqj.boot.demo.doc.Greeting;
import org.yqj.boot.demo.doc.HelloMessage;

/**
 * Created by yaoqijun.
 * Date:2016-07-14
 * Email:yaoqj@terminus.io
 * Descirbe:
 */

//@Controller
public class GreetingController {

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {
        System.out.println("********getdata");
        Thread.sleep(3000); // simulated delay
        System.out.println("********toSend");
        return new Greeting("Hello, " + message.getName() + "!");
    }

}
