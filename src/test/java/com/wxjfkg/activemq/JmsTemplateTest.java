package com.wxjfkg.activemq;

import java.util.HashMap;
import java.util.Map;

import javax.jms.JMSException;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wxjfkg.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class JmsTemplateTest {

	@Autowired
	private Producer producer;

	@BeforeClass
	public static void setUpBeforeClass() {
		System.setProperty("org.apache.activemq.SERIALIZABLE_PACKAGES",
				"com.wxjfkg");
	}

	@Test
	public void testProducer() throws JMSException {
		this.producer.send("Hello world!");
	}

	@Test
	public void testSendObject() throws JMSException {
		User user = new User();
		user.setName("Tom");
		user.setAge(20);
		user.setGender("男");
		this.producer.send(user);
	}

	@Test
	public void testSendMap() throws JMSException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", "Tom");
		map.put("age", 20);
		map.put("married", false);
		this.producer.send(map);
	}

}
