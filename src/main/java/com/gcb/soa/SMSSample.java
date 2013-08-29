package com.gcb.soa;
import java.io.IOException;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPConstants;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;

/**
 * 调用短信接收接口Sample
 */
public class SMSSample {

	/**
	 * 服务前缀
	 */
	private static final String SMS_NAMESPACE_PREFIX = "SMSService";
	/**
	 * 短信服务URL地址，为保证程序的灵活性建议该地址从properties配置文件中获取
	 */
	private static final String WEBSERVICE_INSECURE_URL = "http://115.236.191.137:8080/APL-SMSService/SMSService?wsdl";

	public SMSSample() {

	}

	/**
	 * 测试主函数
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SMSSample webServiceSample = new SMSSample();
		try {
			long id = (long) (Math.random() * 100000);
			System.out.println(id);
			String appKey = "7a3220d779b905d6";
			// String appKey = "0eb1e0a9c9b01a2a";
			String result = webServiceSample.sendSms(id, appKey, "13656665970",
					"短信服务URL地址", System.currentTimeMillis());
			System.out.println(result);
		} catch (SOAPException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 创建 SOAP Connection
	 * 
	 * @return
	 * @throws UnsupportedOperationException
	 * @throws SOAPException
	 */
	private static SOAPConnection getSoapConnection()
			throws UnsupportedOperationException, SOAPException {
		// -- 使用SOAP连接工厂创建连接对象
		final SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory
				.newInstance();
		final SOAPConnection soapConnection = soapConnectionFactory
				.createConnection();

		return soapConnection;
	}

	/**
	 * 创建 SOAP Message
	 * 
	 * @return
	 * @throws SOAPException
	 */
	private SOAPMessage getSoapMessage() throws SOAPException {
		// final MessageFactory messageFactory =
		// MessageFactory.newInstance(SOAPConstants.SOAP_1_1_PROTOCOL);
		// 指定jdk1.6下面的SOAPMessageFactory1_1Impl
		final MessageFactory messageFactory = com.sun.xml.internal.messaging.saaj.soap.ver1_1.SOAPMessageFactory1_1Impl
				.newInstance();
		final SOAPMessage soapMessage = messageFactory.createMessage();

		// -- 创建 SOAP 消息体
		final SOAPPart soapPart = soapMessage.getSOAPPart();
		final SOAPEnvelope envelope = soapPart.getEnvelope();

		envelope.addNamespaceDeclaration("xsd",
				"http://www.w3.org/2001/XMLSchema");
		envelope.addNamespaceDeclaration("xsi",
				"http://www.w3.org/2001/XMLSchema-instance");
		envelope.addNamespaceDeclaration("enc",
				"http://schemas.xmlsoap.org/soap/encoding/");
		envelope.addNamespaceDeclaration("env",
				"http://schemas.xmlsoap.org/soap/envelop/");

		// -- 添加服务命名空间 ，如： "SMSService"
		envelope.addNamespaceDeclaration(SMS_NAMESPACE_PREFIX,
				"http://ws.sms.zjapl.com");
		envelope.setEncodingStyle("http://schemas.xmlsoap.org/soap/encoding/");

		return soapMessage;
	}

	/**
	 * 发送短信
	 * 
	 * @param id
	 *            唯一标识一次短信发送的流水号。由年月日 +
	 *            序号组成,序号由0开始顺序累加，步长为1。如：201208010，201208011
	 * @param appKey
	 *            由集中短信平台为各业务系统发行的授权key
	 * @param phoneNums
	 *            手机号码（多个手机号码之间用半角空格分隔）
	 * @param content
	 *            短信正文
	 * @param time
	 *            时间戳，发送短信时的时间
	 * @return 状态码（0:成功,1:非法授权,2:手机号不正确,3:短信正文包含非法字符,4:发送数据超出上限,5:系统错误）
	 * @throws SOAPException
	 * @throws IOException
	 */
	public String sendSms(long id, String appKey, String phoneNums,
			String content, Long time) throws SOAPException, IOException {

		// -- 创建SOAP传输对象
		final SOAPMessage soapMessage = getSoapMessage();
		soapMessage.getSOAPHeader().detachNode();
		final SOAPBody soapBody = soapMessage.getSOAPBody();
		final SOAPElement getMessage = soapBody.addChildElement("sendSms",
				SMS_NAMESPACE_PREFIX);

		// -- 添加参数节点
		getMessage.setEncodingStyle(SOAPConstants.URI_NS_SOAP_ENCODING);
		getMessage.addChildElement("id").addTextNode(String.valueOf(id))
				.setAttribute("type", "xs:long");
		getMessage.addChildElement("appKey").addTextNode(appKey)
				.setAttribute("type", "xs:string");
		getMessage.addChildElement("phoneNums").addTextNode(phoneNums)
				.setAttribute("type", "xs:string");
		getMessage.addChildElement("content").addTextNode(content)
				.setAttribute("type", "xs:string");
		getMessage.addChildElement("time").addTextNode(time.toString())
				.setAttribute("type", "xs:long");

		soapMessage.saveChanges();

		// -- 连接服务并获得返回的状态码
		final SOAPConnection soapConnection = getSoapConnection();
		final SOAPMessage soapMessageReply = soapConnection.call(soapMessage,
				WEBSERVICE_INSECURE_URL);
		final String statusCode = soapMessageReply.getSOAPBody()
				.getFirstChild().getTextContent();

		soapConnection.close();

		return statusCode;
	}

}
