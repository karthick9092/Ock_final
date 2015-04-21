package com.ockart.test.ockart;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ockart.util.amazon.AmazonUtils;


public class AmazonTest {

	public static void main (String args[]) {
		try {	
			ApplicationContext applicationContext = new ClassPathXmlApplicationContext("xml-configuration/spring-hibernate-integration.xml");
			AmazonUtils amazonUtils = applicationContext.getBean(AmazonUtils.class);
			amazonUtils.itemSearch("Electronics", "LG L70 Dual(White)");
		} catch (Exception e) {			
			e.printStackTrace();
		}
	}
}

	