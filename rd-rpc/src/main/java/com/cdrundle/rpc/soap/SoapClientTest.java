package com.cdrundle.rpc.soap;

import org.junit.Test;

import com.cdrundle.rpc.soap.pojo.Element;
import com.cdrundle.rpc.soap.pojo.InputParm;
import com.cdrundle.rpc.soap.pojo.Message;
import com.cdrundle.rpc.soap.pojo.WsdlEntity;
import com.cdrundle.rpc.soap.pojo.WsdlEntitys;



public class SoapClientTest {
	private  SoapClient client = SoapClient.newInstance();
	/**
	 * 
	* @Title: call
	* @Description: JDK webservice调用事例（严格按照接第三方口文档需编写或对WSDL文档结构熟悉，按照wsdl结构编写） 
	* @param @param targetNamespace
	* @param @param wsdl
	* @param @param serviceName
	* @param @param port
	* @param @param method
	* @param @param po
	* @param @throws SoapClientException    参数
	* @return void    返回类型
	* @throws
	* @author Tjee
	* @date 2017年6月16日
	 */
	@Test
	public  void call(String targetNamespace, String wsdl, String serviceName,String port,String method, InputParm po) throws SoapClientException {
		WsdlEntity  wsdlEntity = WsdlEntitys.newInstance().createWsdlEntity(wsdl,
				targetNamespace,serviceName, port);
		//InputParm 输入参数自己根据第三方文档定义，或根据wsdl文档自己编写
		InputParm ri = new InputParm( );
		ri.setName("www");
		//arg0 输入参数 xs:element minOccurs="0" name="arg0" type="tns:retInfo"/> 
		Element[] argument = {new Element("arg0", ri, InputParm.class)};
		//return: 输出参数 对应服务点 @WebResult wsdl 文档的 XXXResponse
		//nn:  文档空间前缀
		//method方法名
		InputParm retInfo = (InputParm)client.invoke(wsdlEntity, new Message("return", targetNamespace, method, "nn", argument), InputParm.class);
		System.out.println(retInfo.getName());
	}
	
	@Test
	public void test02 () throws SoapClientException {
		WsdlEntity  wsdlEntity = WsdlEntitys.newInstance().createWsdlEntity("http://localhost:8888/soap-server/ws/getInfoService?wsdl",
				"http://impl.service.app.demo.sysware.com/", "GetInfoServiceImplService", "GetInfoServiceImplPort");
		InputParm ri = new InputParm( );
		ri.setName("www");
		Element[] argument = {new Element("arg0", ri, InputParm.class)};
		InputParm retInfo = (InputParm)client.invoke(wsdlEntity, new Message("return", "http://inf.service.app.demo.sysware.com/", "getRetInfoObj", "nn", argument), InputParm.class);
		System.out.println(retInfo.getName());
	}
}
