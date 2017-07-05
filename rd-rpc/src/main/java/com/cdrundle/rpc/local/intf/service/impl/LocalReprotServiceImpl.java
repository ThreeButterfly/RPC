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
package com.cdrundle.rpc.local.intf.service.impl;

import com.cdrundle.rpc.intf.service.IReportService;
import com.cdrundle.rpc.local.intf.service.IlocalReportService;
import com.cdrundle.rpc.web.HessianService;

import javax.annotation.Resource;

/**
 *<P>
 *  TODO：业务接口实现
 *<P>
 * @author Tjee
 * @CreateDate 2017年6月14日 下午3:14:37
 */

@HessianService(Interface ="com.cdrundle.rpc.local.intf.service.IlocalReportService",URL = "showService")
public class LocalReprotServiceImpl implements IlocalReportService {

	@Resource
	IReportService reportService;

	@Override
	public String queryReportlocal(String id) {

		return "hessian 注解成功"+reportService.queryReport(id);
		
	}
}
