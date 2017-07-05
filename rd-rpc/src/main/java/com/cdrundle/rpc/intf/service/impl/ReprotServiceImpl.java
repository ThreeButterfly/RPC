/******************************************************************
 *
 *    Company:   成都市润东实业有限公司 软件开发部
 *
 *    Filename:    ReprotServiceImpl.java
 *
 *    Copyright:   Copyright (c) 2001-2014
 *
 *    @author:     Tjee
 *
 *    @version:    1.0.0
 *
 *    Create at:   2017年6月14日 下午3:14:37
 *
 *    Revision:
 *
 *    2017年6月14日 下午3:14:37
 *        - first revision
 *
 *****************************************************************/
package com.cdrundle.rpc.intf.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdrundle.rpc.intf.remote.IRemoteIntfCall;
import com.cdrundle.rpc.intf.service.IReportService;

/**
 *<P>
 *  TODO：业务接口实现
 *<P>
 * @author Tjee
 * @CreateDate 2017年6月14日 下午3:14:37
 */
@Service
public class ReprotServiceImpl implements IReportService {

	/**
	 * 
	 */
    @Autowired
	private IRemoteIntfCall call;
	
	/* (非 Javadoc)
	 * Description:
	 * @see com.cdrundle.intf.IReportService#queryReport(java.lang.String)
	 */
	@Override
	public Object queryReport(String id) {
		return call.callService(id);
	}

	@Override
	public String test(String id) {
		return id;
	}

}
