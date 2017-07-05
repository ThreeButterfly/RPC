package com.webservice;

import com.cdrundle.rpc.intf.webservice.model.reigster.in.ReigsterInParams;
import com.cdrundle.rpc.intf.webservice.model.reigster.in.ReigsterRequest;
import com.cdrundle.rpc.intf.webservice.model.reigster.out.ReigsterResponse;
import com.cdrundle.rpc.util.JaxbUtil;
import com.cdrundle.rpc.util.WebServiceClientUtil;
import com.google.gson.Gson;

/**
 * Created by tjee on 2017/6/28.
 */
public class Test {
    private static String wsdl = "http://localhost:8180/rpc/webservice/ARTService?wsdl";
    private static String targetNamespace = "http://www.cdrundle.com/IARTService/";
    private static String method = "queryReigsterRecord";


    static void callReigster() {
    	String parm=getRequestParm();
    	System.out.println("parm:"+parm);
        Object rtn = WebServiceClientUtil.call(targetNamespace, wsdl, method,parm);
        ReigsterResponse S= JaxbUtil.xml2Object(rtn.toString(),ReigsterResponse.class);
        
        System.out.println(new Gson().toJson( S));
    }

	/**
	* @Title: setRequestParm
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param     参数
	* @return void    返回类型
	* @throws
	* @author Tjee
	* @date 2017年6月28日
	 */
	private static String getRequestParm() {
		ReigsterRequest request=new ReigsterRequest();
    	ReigsterInParams reigsterInParams =new ReigsterInParams();
    	
    	reigsterInParams.setEndDate("2017-06-28");
    	reigsterInParams.setBeginDate("2017-06-28");
    	reigsterInParams.setDeptCode(1001);
    	request.setParams(reigsterInParams);
		return JaxbUtil.object2Xml(request);
	}

    public static void main(String[] args) {
        callReigster();

    }
}
