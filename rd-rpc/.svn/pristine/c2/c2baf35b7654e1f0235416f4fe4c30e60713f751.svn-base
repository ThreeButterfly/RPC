/******************************************************************
 *
 *    Company:   成都市润东实业有限公司 软件开发部
 *
 *    Filename:    MsgRegBean.java
 *
 *    Copyright:   Copyright (c) 2001-2014
 *
 *    @author:     Tjee
 *
 *    @version:    1.0.0
 *
 *    Create at:   2017年6月27日 下午1:24:20
 *
 *    Revision:
 *
 *    2017年6月27日 下午1:24:20
 *        - first revision
 *
 *****************************************************************/
package com.test;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *<P>
 *  TODO：说明与描述
 *<P>
 * @author Tjee
 * @CreateDate 2017年6月27日 下午1:24:20
 */
@XmlRootElement( name = "msgRegBean" )
public class MsgRegBean
{
    private DataBean dataBean;
     
    private List<RegBean> regBeans;
 
     
    public DataBean getDataBean()
    {
        return dataBean;
    }
 
    @XmlElement(name = "data_bean")
    public void setDataBean(DataBean dataBean)
    {
        this.dataBean = dataBean;
    }
 
    @XmlElementWrapper(name = "reg_beans") 
    @XmlElement(name = "reg_bean")
    public List<RegBean> getRegBeans()
    {
        return regBeans;
    }

	/**
	 * @param regBeans the regBeans to set
	 */
	public void setRegBeans(List<RegBean> regBeans) {
		this.regBeans = regBeans;
	}
    
}