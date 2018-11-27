package com.amod.di.tester;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.amod.di.bean.PenBean;

public class AppTester {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
		PenBean penBean = context.getBean(PenBean.class);
		penBean.write();
	}
}
