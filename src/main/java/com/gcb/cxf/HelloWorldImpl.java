package com.gcb.cxf;

import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * @author Cre.Gu
 * 
 */
@WebService(serviceName = "HelloWorld")
public class HelloWorldImpl implements HelloWorld {

	@Override
	public String sayHello(@WebParam(name = "text") String text) {
		System.out.println("sayHello called");
		return "Hello, " + text;
	}
}
