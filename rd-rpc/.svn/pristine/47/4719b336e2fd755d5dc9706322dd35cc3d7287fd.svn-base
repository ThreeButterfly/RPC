/******************************************************************
 *
 *    Company:   成都市润东实业有限公司 软件开发部
 *
 *    Filename:    WebServiceClientUtil.java
 *
 *    Copyright:   Copyright (c) 2001-2014
 *
 *    @author:     Tjee
 *
 *    @version:    1.0.0
 *
 *    Create at:   2017年6月16日 上午9:52:09
 *
 *    Revision:
 *
 *    2017年6月16日 上午9:52:09
 *        - first revision
 *
 *****************************************************************/
package com.cdrundle.rpc.util;

import javax.xml.namespace.QName;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;


/**
 * <P>
 * TODO：说明与描述
 * <P>
 * 
 * @author Tjee
 * @CreateDate 2017年6月16日 上午9:52:09
 */
public class WebServiceClientUtil {

	private static JaxWsDynamicClientFactory factory = JaxWsDynamicClientFactory.newInstance();
	private static JaxWsProxyFactoryBean proxyFactory = new JaxWsProxyFactoryBean();

	/**
	 * @Title: call
	 * @Description: TODO(获取无参方法的返回值,如queryName())
	 * @param @param
	 *            targetNamespace wsdl命名空间
	 * @param @param
	 *            wsdl wsdl地址
	 * @param @param
	 *            method 调用的方法名字
	 * @param @return
	 *            String namespace="http://inf.service.app.demo.sysware.com/";
	 *            String
	 *            wsdl="http://localhost:8888/soap-server/ws/getInfoService?wsdl";
	 *            String
	 *            adress="http://localhost:8888/soap-server/ws/getInfoService";
	 *            String method="getName"; //Object
	 *            r1=call(namespace,wsdl,method); 参数
	 * @return Object 返回类型
	 * @throws @author
	 *             Tjee
	 * @date 2017年6月15日
	 */
	public static Object call(String targetNamespace, String wsdl, String method) {
		Client client = factory.createClient(wsdl);
		QName opName = new QName(targetNamespace, method);
		Object[] result = null;
		try {
			result = client.invoke(opName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result[0];
	}

	/**
	 * @Title: call
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param
	 *            targetNamespace wsdl命名空间
	 * @param @param
	 *            wsdl wsdl地址
	 * @param @param
	 *            method 调用的方法名字
	 * @param @param
	 *            String namespace="http://inf.service.app.demo.sysware.com/";
	 *            String
	 *            wsdl="http://localhost:8888/soap-server/ws/getInfoService?wsdl";
	 *            String
	 *            adress="http://localhost:8888/soap-server/ws/getInfoService";
	 *            String method="getRetInfo"; parm 服务端接受参数 按照接口文档或WSDL文档定义顺序传入 如
	 *            姓名，性别 ：new Object[] { "张三",2} Object
	 *            r1=call(namespace,wsdl,method,new Object[] { "张三", 2}); "男" }
	 * @param @return
	 *            参数
	 * @return Object 返回类型
	 * @throws @author
	 *             Tjee
	 * @date 2017年6月15日
	 */
	public static Object call(String targetNamespace, String wsdl, String method, Object... parm) {
		Client client = factory.createClient(wsdl);
		QName opName = new QName(targetNamespace, method);
		Object[] res = null;
		try {
			res = client.invoke(opName, parm);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res[0];
	}

	/**
	 * @Title: call
	 * @Description: TODO(根据接口类型class 和 接口地址：WSDL-地址去掉?wsdl 注意： 此调用方法适合于第三方提供接口
	 * @param @param
	 *            address
	 * @param @param
	 *            clazz
	 * @param @return
	 *            参数
	 * @return Object 返回类型 用的时候需强转为接口类型 String
	 *         namespace="http://inf.service.app.demo.sysware.com/"; String
	 *         wsdl="http://localhost:8888/soap-server/ws/getInfoService?wsdl";
	 *         String
	 *         adress="http://localhost:8888/soap-server/ws/getInfoService";
	 *         String method="getRetInfo"; 如：(IGetInfoService)service
	 *         IGetInfoService service= (IGetInfoService)
	 *         call(adress,IGetInfoService.class); Object r1= service.getName();
	 * @throws @author
	 *             Tjee
	 * @date 2017年6月16日
	 */
	public static Object call(String address, Class clazz) {
		proxyFactory.setServiceClass(clazz);
		proxyFactory.setAddress(address);
		Object service = proxyFactory.create();
		return service;
	}
	/*
	 * <wsdl:service name="GetInfoServiceImplService">
<wsdl:port binding="tns:GetInfoServiceImplServiceSoapBinding" name="GetInfoServiceImplPort">
<soap:address location="http://localhost:8888/soap-server/ws/getInfoService"/>
</wsdl:port>
</wsdl:service>
	 * */

	
	
	public static void main(String[] args) throws Exception {
		/*String namespace = "http://inf.service.app.demo.sysware.com/";
		String wsdl = "http://localhost:8888/soap-server/ws/getInfoService?wsdl";
		String adress = "http://localhost:8888/soap-server/ws/getInfoService";
		String method = "getRetInfo";
		String parm = "";
		 Object r1=call(namespace,wsdl,method);
		 System.out.println(r1);
		 Object r1=call(namespace,wsdl,method,new Object[] { "张三", 2});
		 IGetInfoService service= (IGetInfoService)
		 call(adress,IGetInfoService.class);
		 Object r1= service.getName();
		 System.out.println(r1);*/
	}

}
