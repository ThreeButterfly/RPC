/******************************************************************
 *
 *    Company:   成都市润东实业有限公司 软件开发部
 *
 *    Filename:    ReportHLBE.java
 *
 *    Copyright:   Copyright (c) 2001-2014
 *
 *    @author:     Tjee
 *
 *    @version:    1.0.0
 *
 *    Create at:   2017年6月14日 下午3:20:08
 *
 *    Revision:
 *
 *    2017年6月14日 下午3:20:08
 *        - first revision
 *
 *****************************************************************/
package com.cdrundle.rpc.intf.remote.client;

import org.springframework.stereotype.Component;

import com.cdrundle.rpc.intf.remote.IRemoteIntfCall;
import com.cdrundle.rpc.util.WebServiceClientUtil;

/**
 * <P>
 * TODO：远程调用接口实现 可以说post 可以是webservice
 * <P>
 * 
 * @author Tjee
 * @CreateDate 2017年6月14日 下午3:20:08
 */
@Component
public class ReportHLBE implements IRemoteIntfCall {

	private String wsdl = "http://localhost:8080/rpc/webservice/getInfoService?wsdl";
	private String targetNamespace = "http://www.cdrundle.com/IGetInfoService/";
	private String method = "getName";
	/*
	 * (非 Javadoc) Description:具体实现 例子为webservice其中调用的CXF的动态
	 * 
	 * @see
	 * com.cdrundle.intf.remote.IRemoteIntfCall#callService(java.lang.Object)
	 */
	@Override
	public Object callService(Object obj) {
		//获取远程调用返回报文
		Object rtn = WebServiceClientUtil.call(targetNamespace, wsdl, method);
		//解析远程报文
		return convert(rtn);
	}

	public Object convert(Object obj) {

		return obj;
	}

}
