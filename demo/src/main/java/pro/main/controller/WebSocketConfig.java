package pro.main.controller;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer{
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
    	/*
    	 * 添加这个Endpoint，这样在网页中就可以通过websocket连接上服务了
    	 * 如： new SockJS("http://"+window.location.host+"/demo/websocket")
    	 * demo是我的项目名
    	 * */
        registry.addEndpoint("/websocket").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
    	/*
    	 * userChat 可以用"/app/userChat"的路径进行socket访问
    	 * 要订阅的地址也必须在这两个里面，注：我也没完全懂，不过这样理解应该没问题
    	 * 
    	 * */
        config.enableSimpleBroker("/userChat","/message");
        config.setApplicationDestinationPrefixes("/app");
    }

	@Override
	public void configureWebSocketTransport(
			WebSocketTransportRegistration registry) {
		
	}

	@Override
	public void configureClientInboundChannel(ChannelRegistration registration) {
		
	}

	@Override
	public void configureClientOutboundChannel(ChannelRegistration registration) {
		
	}

	@Override
	public boolean configureMessageConverters(
			List<MessageConverter> messageConverters) {
		return true;
	}

   
}