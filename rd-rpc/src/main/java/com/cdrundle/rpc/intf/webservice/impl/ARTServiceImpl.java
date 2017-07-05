package com.cdrundle.rpc.intf.webservice.impl;

import com.cdrundle.rpc.intf.webservice.IARTService;
import com.cdrundle.rpc.intf.webservice.model.reigster.in.ReigsterRequest;
import com.cdrundle.rpc.intf.webservice.model.reigster.out.ReigsterOutParams;
import com.cdrundle.rpc.intf.webservice.model.reigster.out.ReigsterResponse;
import com.cdrundle.rpc.util.JaxbUtil;

import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tjee on 2017/6/27.
 */
@WebService(targetNamespace = "http://www.cdrundle.com/IARTService/", endpointInterface = "com.cdrundle.rpc.intf.webservice.IARTService")
public class ARTServiceImpl implements IARTService {

	@Override
	public String queryReigsterRecord(String xml) {
		ReigsterRequest obj= JaxbUtil.xml2Object(xml, ReigsterRequest.class) ;
		String rtn=null;
		if(null!=obj){
			if(null==obj.getParams().getBeginDate()){
				rtn=error();
			}else {
				rtn = reigsterOut();
			}
		}

		return rtn;
	}

	private String reigsterOut() {
		ReigsterResponse response = new ReigsterResponse();
		response.setMessage("成功！");
		response.setResultCode("001");
		response.setRowCount(1);
		List<ReigsterOutParams> li = new ArrayList<ReigsterOutParams>();
		ReigsterOutParams reigsterRecord = new ReigsterOutParams();
		reigsterRecord.setReigsterNo("0001");
		reigsterRecord.setAddress("地址");
		reigsterRecord.setBirthday("1990-01-01");
		reigsterRecord.setAge("17");
		reigsterRecord.setDeptid("dpt001");
		reigsterRecord.setDeptname("项目部门");
		li.add(reigsterRecord);
		response.setRows(li);
		String xmlStr = JaxbUtil.object2Xml(response);
		return xmlStr;
	}
	private String error() {
		ReigsterResponse response = new ReigsterResponse();
		response.setMessage("开始时间不能为空！");
		response.setResultCode("003");
		response.setRowCount(0);
		String xmlStr = JaxbUtil.object2Xml(response);
		return xmlStr;
	}

	/* (非 Javadoc)
	 * Description:
	 * @see com.cdrundle.rpc.intf.webservice.IARTService#queryExamineReport(java.lang.String)
	 */
	@Override
	public String queryExamineReport(String xml) {
		return null;
	}

	/* (非 Javadoc)
	 * Description:
	 * @see com.cdrundle.rpc.intf.webservice.IARTService#queryPrescription(java.lang.String)
	 */
	@Override
	public String queryPrescription(String xml) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
