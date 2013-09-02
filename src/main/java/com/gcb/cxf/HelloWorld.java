package com.gcb.cxf;

import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * @author Cre.Gu
 * 
 */
@WebService
public interface HelloWorld {
	public String sayHello(@WebParam(name = "text") String text);
}
