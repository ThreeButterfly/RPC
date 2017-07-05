package com.cdrundle.rpc.soap;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.Map;

import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Service;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.cdrundle.rpc.soap.pojo.Element;
import com.cdrundle.rpc.soap.pojo.Message;
import com.cdrundle.rpc.soap.pojo.WsdlEntity;
import com.cdrundle.rpc.soap.reflect.ClassFieldException;
import com.cdrundle.rpc.soap.reflect.ClassSetterOrGetterException;
import com.cdrundle.rpc.soap.reflect.ClassUtils;
import com.cdrundle.rpc.util.ObjectUtils;
import com.cdrundle.rpc.util.StringUtils;

/**
 * 
 * <P>
 * TODO：ws客户端
 * <P>
 * 
 * @author Tjee
 * @CreateDate 2017年6月14日 上午9:02:08
 */
public class SoapClient {
	/**
	 * 连接超时
	 */
	private long CONNECTION_TIMEOUT = 0;
	/**
	 * 请求超时
	 */
	private long RECIVE_TIMEOUT = 0;
	/**
	 * SoapClient 单例
	 */
	private static SoapClient client = null;


	/**
	 * 受保护的构造方法，外部无法实例化。可以通过类的 newInstance()实例化
	 */
	private SoapClient() {
	}
	/**
	 * 获取SoapClient 
	 */
	public synchronized static SoapClient newInstance() {
		if (null == client) {
			client = new SoapClient();
		}
		return client;
	}
	/**
	 * 同步调用服务操作。 客户端负责确保在编组 msg 对象时根据所用协议绑定的要求形成它们。
	 * 
	 * @author tjee
	 * @param wsdl
	 *            wsdlEntity.xml配置信息
	 * @param message
	 *            一个对象，将形成用来调用操作的消息或消息负载
	 */
	public Document doInvoke(WsdlEntity wsdl, Message message) throws SoapClientException {

		try {
			// 1、创建服务(Service)
			URL url = new URL(wsdl.getWsdlUrl().toString());
			QName sname = new QName(wsdl.getServiceNamespace(), wsdl.getServiceName());
			Service service = Service.create(url, sname);

			// 2、创建Dispatch
			Dispatch<SOAPMessage> dispatch = service.createDispatch(
					new QName(wsdl.getServiceNamespace(), wsdl.getServicePort()), SOAPMessage.class,
					Service.Mode.MESSAGE);
			// 设置超时时间
			Map<String, Object> requestContext = dispatch.getRequestContext();

			// 设置连接超时时间
			if (CONNECTION_TIMEOUT != 0) {
				requestContext.put("com.sun.xml.internal.ws.connect.timeout", CONNECTION_TIMEOUT);
			}
			// 设置请求超时时间
			if (RECIVE_TIMEOUT != 0) {
				requestContext.put("com.sun.xml.internal.ws.request.timeout", RECIVE_TIMEOUT);
			}
			// 3、创建SOAPMessage
			SOAPMessage msg = MessageFactory.newInstance().createMessage();
			SOAPEnvelope envelope = msg.getSOAPPart().getEnvelope();
			SOAPBody body = envelope.getBody();
			setSOAPBody(body, message);
			// 5、通过Dispatch传递消息,会返回响应消息
			SOAPMessage response = dispatch.invoke(msg);
			// 将响应的消息转换为dom对象
			Document doc = response.getSOAPPart().getEnvelope().getBody().extractContentAsDocument();
			return doc;
		} catch (SOAPException e) {
			e.printStackTrace();
			throw new SoapClientException("[SoapClient invoke failed, the Exception is SOAPException;]", e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new SoapClientException("[SoapClient invoke failed, the Exception is IOException;]", e);
		} catch (DOMException e) {
			e.printStackTrace();
			throw new SoapClientException("[SoapClient invoke failed, the Exception is DOMException;]", e);
		} catch (ClassSetterOrGetterException e) {
			e.printStackTrace();
			throw new SoapClientException("[SoapClient invoke failed, the Exception is ClassSetterOrGetterException;]",
					e);
		}
	}

	/**
	 * 同步调用服务操作。 客户端负责确保在编组 msg 对象时根据所用协议绑定的要求形成它们。 返回clzz类型的对象数据
	 * 
	 * @param wsdl
	 *            wsdlEntity.xml配置信息
	 * @param message
	 *            一个对象，将形成用来调用操作的消息或消息负载
	 * @param clzz
	 *            返回值类型
	 */
	public Object invoke(WsdlEntity wsdl, Message message, Class<?> clzz) throws SoapClientException {
		try {
			// 将响应的消息转换为dom对象
			Document doc = doInvoke(wsdl, message);

			Node node = doc.getElementsByTagName(message.getResult()).item(0);
			return nodeToObject(node, clzz);
		} catch (DOMException e) {
			e.printStackTrace();
			throw new SoapClientException("[SoapClient invoke failed, the Exception is DOMException;]", e);
		} catch (InstantiationException e) {
			e.printStackTrace();
			throw new SoapClientException("[SoapClient invoke failed, the Exception is InstantiationException;]", e);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			throw new SoapClientException("[SoapClient invoke failed, the Exception is IllegalAccessException;]", e);
		} catch (ClassSetterOrGetterException e) {
			e.printStackTrace();
			throw new SoapClientException("[SoapClient invoke failed, the Exception is ClassSetterOrGetterException;]",
					e);
		} catch (SecurityException e) {
			e.printStackTrace();
			throw new SoapClientException("[SoapClient invoke failed, the Exception is ClassSetterOrGetterException;]",
					e);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
			throw new SoapClientException("[SoapClient invoke failed, the Exception is ClassSetterOrGetterException;]",
					e);
		}
	}

	/**
	 * 
	 * @Title: invoke
	 * @Description: 同步调用服务操作。 客户端负责确保在编组 msg 对象时根据所用协议绑定的要求形成它们。 返回clzz类型的对象数据
	 * @param wsdl
	 *            wsdlEntity.xml配置信息
	 * @param message
	 *            一个对象，将形成用来调用操作的消息或消息负载
	 * @param @return
	 * @param @throws
	 *            SoapClientException 参数
	 * @return String 返回类型
	 * @throws @author
	 *             Tjee
	 * @date 2017年6月14日
	 */
	public String invoke(WsdlEntity wsdl, Message message) throws SoapClientException {
		return (String) this.invoke(wsdl, message, String.class);
	}

	/**
	 * 设置连接超时
	 */
	public void setConnectionTimeout(long value) {
		CONNECTION_TIMEOUT = value;
	}

	/**
	 * 设置请求超时
	 */
	public void setReceiveTimeout(long value) {
		RECIVE_TIMEOUT = value;
	}

	/**
	 * 根据message设置SOAPEnvelope中BODY请求消息
	 * 
	 * @author tjee
	 * @param body
	 *            SOAPEnvelope 中 body中的信息
	 * @param message
	 *            请求消息
	 * @throws ClassFieldException
	 * @throws ClassSetterOrGetterException
	 * @date 2017年6月16日
	 */
	protected void setSOAPBody(SOAPBody body, Message message) throws SOAPException, ClassSetterOrGetterException {
		// 4、创建QName来指定消息中传递数据
		QName ename = new QName(message.getNamespace(), message.getName(), message.getPrefix());// <nn:add
																								// xmlns="xx"/>
		SOAPBodyElement ele = body.addBodyElement(ename);
		for (Element arg : message.getArgument()) {
			if (arg.getValue() == null) {
				ele.addChildElement(arg.getName());
				continue;
			}
			if (ObjectUtils.isBaseType(arg.getValue())) {
				ele.addChildElement(arg.getName()).setValue(arg.getValue().toString());
			} else {
				Class<?> clzz = arg.getClzz();
				SOAPElement soapObj = ele.addChildElement(arg.getName());
				Field[] fields = clzz.getDeclaredFields();
				for (Field field : fields) {
					if (ClassUtils.CONSTANT_SERIALVERSIONUID.equals(field.getName())) {
						continue;
					}
					String value = ClassUtils.getter(arg.getValue(), field.getName()).toString();
					soapObj.addChildElement(field.getName()).setValue(value);
				}
			}
		}

	}

	/**
	 * 根据Node 转换成简单对象和基本对象类型，不支持有嵌套对象类型
	 * 
	 * @Title: nodeToObject
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param
	 *            node
	 * @param @param
	 *            clzz
	 * @param @return
	 * @param @throws
	 *            InstantiationException
	 * @param @throws
	 *            IllegalAccessException
	 * @param @throws
	 *            DOMException
	 * @param @throws
	 *            SecurityException
	 * @param @throws
	 *            NoSuchFieldException
	 * @param @throws
	 *            ClassSetterOrGetterException 参数
	 * @return Object 返回类型
	 * @throws @author
	 *             Tjee
	 * @date 2017年6月16日
	 */
	protected Object nodeToObject(Node node, Class<?> clzz) throws InstantiationException, IllegalAccessException,
			DOMException, SecurityException, NoSuchFieldException, ClassSetterOrGetterException {
		NodeList nodeList = node.getChildNodes();
		Node n = null;
		Object val = StringUtils.parseValueForClass(node.getTextContent(), clzz);
		if (val == null) {
			val = clzz.newInstance();
			for (int i = 0; i < nodeList.getLength(); i++) {
				n = nodeList.item(i);
				ClassUtils.setter(val, n.getNodeName(), n.getTextContent());
			}
		}
		return val;
	}

}
