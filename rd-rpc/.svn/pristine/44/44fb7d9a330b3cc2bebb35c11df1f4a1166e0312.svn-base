package com.cdrundle.rpc.intf.webservice;


import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService(targetNamespace = "http://www.cdrundle.com/IGetInfoService/")
public interface IGetInfoService {
    @WebMethod(operationName = "add")
    @WebResult(name = "result")
    public int add(@WebParam(name = "num1") int num1,
                   @WebParam(name = "num2") int num2);



    @WebMethod(operationName = "getName")
    @WebResult(name = "result")
    public String getName();
}  