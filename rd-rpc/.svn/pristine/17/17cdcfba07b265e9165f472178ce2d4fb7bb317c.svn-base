/******************************************************************
 *
 *    Company:   成都市润东实业有限公司 软件开发部
 *
 *    Filename:    JaxbObjectAndXmlUtil.java
 *
 *    Copyright:   Copyright (c) 2001-2014
 *
 *    @author:     Tjee
 *
 *    @version:    1.0.0
 *
 *    Create at:   2017年6月27日 下午1:29:21
 *
 *    Revision:
 *
 *    2017年6月27日 下午1:29:21
 *        - first revision
 *
 *****************************************************************/
package com.test;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 *<P>
 *  TODO：说明与描述
 *<P>
 * @author Tjee
 * @CreateDate 2017年6月27日 下午1:29:21
 */
public class JaxbObjectAndXmlUtil
{ 
       
    /**
     * @param xmlStr 字符串
     * @param c 对象Class类型
     * @return 对象实例
     */
    @SuppressWarnings("unchecked")
    public static <T> T xml2Object(String xmlStr,Class<T> c)
    { 
        try
        { 
            JAXBContext context = JAXBContext.newInstance(c); 
            Unmarshaller unmarshaller = context.createUnmarshaller(); 
             
            T t = (T) unmarshaller.unmarshal(new StringReader(xmlStr)); 
             
            return t; 
             
        } catch (JAXBException e) {  e.printStackTrace();  return null; } 
         
    } 
       
    /**
     * @param object 对象
     * @return 返回xmlStr
     */
    public static String object2Xml(Object object)
    { 
        try
        {   
            StringWriter writer = new StringWriter();
            JAXBContext context = JAXBContext.newInstance(object.getClass()); 
            Marshaller  marshal = context.createMarshaller();
             
            marshal.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true); // 格式化输出 
            marshal.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");// 编码格式,默认为utf-8 
            marshal.setProperty(Marshaller.JAXB_FRAGMENT, false);// 是否省略xml头信息 
            marshal.setProperty("jaxb.encoding", "utf-8"); 
            marshal.marshal(object,writer);
             
            return new String(writer.getBuffer());
             
        } catch (Exception e) { e.printStackTrace(); return null;}    
         
    } 
     
    public static void main(String[] args)
    {
         /** 构造测试报文头对象 */
        DataBean dataBean = new DataBean();
        dataBean.setData_type("000001");
        dataBean.setVersion("v1.0");
        dataBean.setUser_name("xx");
 
        /** 构造测试报文体对象 */
        RegBean regBean = new RegBean();
        regBean.setReg_sn("REG20150204");
         
        MsgRegBean msgBean = new MsgRegBean();
        List<RegBean> regBeans = new ArrayList<RegBean>();
        regBeans.add(regBean);
         
        msgBean.setRegBeans(regBeans);
        msgBean.setDataBean(dataBean);
         
        String xmlStr = JaxbObjectAndXmlUtil.object2Xml(msgBean);//构造报文 XML 格式的字符串
        System.out.println("对象转xml报文： \n"+xmlStr);
         
     //   MsgRegBean msgBean2 = JaxbObjectAndXmlUtil.xml2Object(xmlStr, MsgRegBean.class);
       // System.out.println("报文转xml转： \n"+JSON.toJSONString(msgBean2));
    }
}