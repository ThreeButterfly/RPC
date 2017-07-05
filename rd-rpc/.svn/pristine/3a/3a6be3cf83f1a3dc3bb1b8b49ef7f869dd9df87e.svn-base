package com.cdrundle.rpc.controller;

import com.cdrundle.rpc.intf.service.IReportService;
import com.cdrundle.rpc.local.intf.service.IlocalReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	@Autowired
	IReportService reportService;
   @Autowired
    IlocalReportService ilocalReportService;
    @RequestMapping("test")
    public String getInfo( )  {
     System.out.println(ilocalReportService);
        ilocalReportService.queryReportlocal("q");
        return reportService.queryReport("1").toString();
    }

}
