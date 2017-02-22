package com.wxjfkg.activemq;

import java.util.Enumeration;

import javax.jms.BytesMessage;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {
	
	private static final Logger logger = LoggerFactory.getLogger(Consumer.class);

	@JmsListener(destination = "${queue.name}")
	public void consume(Message message) {
		try {
			if (message instanceof TextMessage) {
				TextMessage msg = (TextMessage) message;
				logger.debug("received text message: {}", msg.getText());
			} else if(message instanceof ObjectMessage) {
				ObjectMessage msg = (ObjectMessage) message;
				Object obj = msg.getObject();
				logger.debug("received object message: {}", obj);
			} else if(message instanceof BytesMessage) {
				BytesMessage msg = (BytesMessage) message;
				logger.debug("received byte message: {} bytes", msg.getBodyLength());
			} else if(message instanceof MapMessage) {
				MapMessage msg = (MapMessage) message;
				Enumeration<String> names = msg.getMapNames();
				while (names.hasMoreElements()) {
					String name = names.nextElement();
					logger.debug("received map message: key => {}, value => {}",
							name, msg.getObject(name));
				}
			}
		} catch (JMSException e) {
			logger.error("receive jms message error.", e);
		}
	}
	
}
