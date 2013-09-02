package com.gcb.cxf;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.soap.SOAPBinding;

/**
 * 
 * @author Cre.Gu
 * 
 */
public class Client {
	private static final QName SERVICE_NAME = new QName("http://cxf.gcb.com",
			"HelloWorld");
	private static final QName PORT_NAME = new QName("http://cxf.gcb.com",
			"HelloWorldPort");

	public static void main(String[] args) {
		System.out.println("Start Client");
		Service service = Service.create(SERVICE_NAME);
		String endpointAddress = "http://localhost:9000/HelloWorld?wsdl";
		service.addPort(PORT_NAME, SOAPBinding.SOAP11HTTP_BINDING,
				endpointAddress);
		HelloWorld hw = service.getPort(HelloWorld.class);
		String str = hw.sayHello("demo");
		System.out.println(str);
	}
}
