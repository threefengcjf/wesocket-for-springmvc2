package pro.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import pro.main.model.User;

@Controller
public class IndexController {
	
	private SimpMessagingTemplate template;
	
	@Autowired
    public IndexController(SimpMessagingTemplate t) {
        template = t;//实现发送模板类
    }
	

	@MessageMapping("/userChat/chat")
	//@SendTo("/message")
	public void seedMessage(User record){//接收通过send(,,)函数传递过来的链接
		System.out.println("----------userChat----------------");
		System.out.println("name : "+record.getName()+",message : "+record.getContent());
		//下面这个是用来转发的，不过在转发之前先在客户端subscribe()订阅一下，不让他会报错的，
		//他就是一个分发器，将数据转发到相对应的地址（qq群）里去
		this.template.convertAndSend("/message", record);
		//return record;//更简单的就是用@SendTo("/message")，将void改为User 在return 就ok了，效果是一样的
	}
	
	
}
