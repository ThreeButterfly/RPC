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
package com.cdrundle.rpc.util;

import com.cdrundle.rpc.intf.webservice.model.Row;
import com.google.gson.Gson;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;
import java.io.ByteArrayOutputStream;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * <P>
 * TODO：说明与描述
 * <P>
 * 
 * @author Tjee
 * @CreateDate 2017年6月27日 下午1:29:21
 */
public class JaxbUtil {

	/**
	 * @param xmlStr
	 *            字符串
	 * @param c
	 *            对象Class类型
	 * @return 对象实例
	 */
	@SuppressWarnings("unchecked")
	public static <T> T xml2Object(String xmlStr, Class<T> c) {
		try {
			JAXBContext context = JAXBContext.newInstance(c);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			T t = (T) unmarshaller.unmarshal(new StringReader(xmlStr));

			return t;

		} catch (JAXBException e) {
			e.printStackTrace();
			return null;
		}

	}
	/**
	 * @param object
	 *            对象
	 * @return 返回xmlStr
	 */
	public static String object2Xml(Object object) {
		try {
			StringWriter writer = new StringWriter();
			JAXBContext context = JAXBContext.newInstance(object.getClass());
			Marshaller marshal = context.createMarshaller();

			marshal.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, false); // 格式化输出
			marshal.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");// 编码格式,默认为utf-8
			marshal.setProperty(Marshaller.JAXB_FRAGMENT, true);// 是否省略xml头信息
			marshal.setProperty("jaxb.encoding", "utf-8");
			marshal.marshal(object, writer);

			return new String(writer.getBuffer());

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	/**
	 * @param object
	 *            对象
	 * @return 返回xmlStr
	 */
	public static String object2Xml2(Object object) {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(object.getClass());
			XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newFactory();
			  Marshaller marshal = jaxbContext.createMarshaller();
				marshal.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true); // 格式化输出
				marshal.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");// 编码格式,默认为utf-8
				marshal.setProperty(Marshaller.JAXB_FRAGMENT, true);// 是否省略xml头信息
			    ByteArrayOutputStream baos = new ByteArrayOutputStream();
			    XMLStreamWriter xmlStreamWriter = xmlOutputFactory.createXMLStreamWriter(
			        baos, (String) marshal.getProperty(Marshaller.JAXB_ENCODING));
			    xmlStreamWriter.writeStartDocument(
			        (String) marshal.getProperty(Marshaller.JAXB_ENCODING), "1.0");
			    marshal.marshal(object, xmlStreamWriter);
			    xmlStreamWriter.writeEndDocument();
			    xmlStreamWriter.close();
			    return new String(baos.toByteArray());

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	public static void main(String[] args) {
	 /*
		ReigsterResponse response = new ReigsterResponse();
		response.setMessage("编码错误:code-erro");
		response.setResultCode("001");
		response.setRowCount(11);
		List<ReigsterOutParams> li=	new ArrayList<ReigsterOutParams>();
		ReigsterOutParams reigsterRecord1=new ReigsterOutParams();
		ReigsterOutParams reigsterRecord2=new ReigsterOutParams();
		reigsterRecord1.setReigsterNo("oooo1");
		reigsterRecord2.setReigsterNo("oooo2");
		li.add(reigsterRecord1);
		li.add(reigsterRecord2);
		response.setRows(li);*/
		Row row=new Row();
		row.setAge("12");
		row.setName("rrr");
		String xmlStr = JaxbUtil.object2Xml(row);// 构造报文 XML 格式的字符串
		System.out.println("对象转xml报文： \n" + xmlStr);

		Row msgBean2 = JaxbUtil.xml2Object(xmlStr,
				Row.class);
		  System.out.println("报文转xml转： \n"+new Gson().toJson(msgBean2));
	}
}