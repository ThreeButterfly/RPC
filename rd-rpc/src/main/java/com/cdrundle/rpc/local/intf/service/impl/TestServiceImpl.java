package com.cdrundle.rpc.local.intf.service.impl;

import com.cdrundle.rpc.intf.service.IReportService;
import com.cdrundle.rpc.local.intf.service.ITestService;
import com.cdrundle.rpc.web.HessianService;

import javax.annotation.Resource;

/**
 * Created by tjee on 2017/6/22.
 */
@HessianService(Interface = "com.cdrundle.rpc.local.intf.service.ITestService")
public class TestServiceImpl implements ITestService {

    @Resource
    IReportService reportService;
    @Override
    public String test(String s) {
        return reportService.test(s);
    }
}
