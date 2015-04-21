package com.ockart.test.ockart;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ockart.util.amazon.FlipkartUtils;

public class FlipkartTest {

	public static void main (String args[]) throws Exception {
		try {
			ApplicationContext context = new ClassPathXmlApplicationContext("xml-configuration/spring-hibernate-integration.xml");
			FlipkartUtils flipkartUtils = context.getBean(FlipkartUtils.class);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
}
