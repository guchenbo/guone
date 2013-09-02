package com.gcb.cxf;

import javax.xml.ws.Endpoint;

/**
 * @author Cre.Gu
 * 
 */
public class Server {
	public Server() {
		System.out.println("Starting Server");
		HelloWorld hw = new HelloWorldImpl();
		String address = "http://localhost:9000/HelloWorld";
		Endpoint.publish(address, hw);
	}

	public static void main(String[] args) throws Exception {
		new Server();
		System.out.println("Server ready...");
		Thread.sleep(5 * 60 * 1000);
		System.out.println("Server exiting");

	}
}
