package main;

import spring.*;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import config.Appctx;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(Appctx.class);
		
		Client client = ctx.getBean(Client.class);
		client.send();
		
		Client2 client2 = ctx.getBean(Client2.class);
		client2.send();
		
		ctx.close();
	}

}
