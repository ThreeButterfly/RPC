package com.cdrundle.rpc.intf.webservice;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * Created by tjee on 2017/6/27.
 */
@WebService(targetNamespace = "http://www.cdrundle.com/IARTService/")
public interface IARTService {
	@WebMethod(operationName = "queryReigsterRecord")
	public String queryReigsterRecord(@WebParam(name = "xml") String xml);

	@WebMethod(operationName = "queryExamineReport")
	public String queryExamineReport(@WebParam(name = "xml") String xml);
	
	@WebMethod(operationName = "queryPrescription")
	public String queryPrescription(@WebParam(name = "xml") String xml);
}
