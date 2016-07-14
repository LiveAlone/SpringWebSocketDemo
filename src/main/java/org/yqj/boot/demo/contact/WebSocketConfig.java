package org.yqj.boot.demo.contact;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * Created by yaoqijun.
 * Date:2016-07-14
 * Email:yaoqj@terminus.io
 * Descirbe:
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer{

    @Bean
    public WebSocketHandler systemWebSocketHandler(){
        return new SystemWebSocketHandler();
    }

    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {

        // 自定应配置 Handler 处理方式,
        // Interceptor 对应的过滤方式。
        webSocketHandlerRegistry.addHandler(systemWebSocketHandler(),"/webSocketServer")
                .addInterceptors(new WebSocketHandshakeInterceptor());

        webSocketHandlerRegistry.addHandler(systemWebSocketHandler(), "/sockjs/webSocketServer")
                .addInterceptors(new WebSocketHandshakeInterceptor())
                .withSockJS();
    }
}
