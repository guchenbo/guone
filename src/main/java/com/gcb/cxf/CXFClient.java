package com.gcb.cxf;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

/**
 * @author Cre.Gu
 * 
 */
public class CXFClient {
	public static void main(String[] args) {
		System.out.println("Starting Client");
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.setServiceClass(HelloWorld.class);
		factory.setAddress("http://localhost:9000/HelloWorld");
		HelloWorld hw = (HelloWorld) factory.create();
		System.out.println(hw.sayHello("demo"));
	}
}
