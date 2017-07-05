package com.cdrundle.rpc.intf.webservice.impl;  
  
import com.cdrundle.rpc.intf.webservice.IGetInfoService;

import javax.jws.WebService;
import java.util.Date;
  /**
   * 
   *<P>
   *  TODO：说明与描述
   *<P>
   * @author Tjee
   * @CreateDate 2017年6月14日 下午4:36:22
   */
@WebService(targetNamespace="http://www.cdrundle.com/IGetInfoService/",endpointInterface = "com.cdrundle.rpc.intf.webservice.IGetInfoService")
public class GetInfoServiceImpl implements IGetInfoService  
{  
  
    @Override  
    public int add(int num1, int num2)  
    {  
        return num1 + num2;  
    }  
  


	/* (非 Javadoc)
	 * Description:
	 * @see com.sysware.demo.app.service.inf.IGetInfoService#getName()
	 */
	@Override
	public String getName() {
		return "调用无参webservice:getName，服务成功，"+new Date();
	}
  
}  