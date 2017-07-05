package com.cdrundle.rpc.soap.pojo;

import java.io.Serializable;
/**
 * 
 *<P>
 *  TODO：soap 访问服务描述
 *<P>
 * @author Tjee
 * @CreateDate 2017年6月14日 上午9:06:35
 */
public class WsdlEntity implements Serializable {

	private static final long serialVersionUID = -6483931466179236361L;
	
	private String id;
	private String wsdlUrl;
	private String serviceNamespace;
	private String serviceName;
	private String servicePort;
	
	protected WsdlEntity() {
	}

	protected WsdlEntity(String wsdlUrl, String serviceNamespace,
			String serviceName, String servicePort) {
		super();
		this.wsdlUrl = wsdlUrl;
		this.serviceNamespace = serviceNamespace;
		this.serviceName = serviceName;
		this.servicePort = servicePort;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getWsdlUrl() {
		return wsdlUrl;
	}

	public void setWsdlUrl(String wsdlUrl) {
		this.wsdlUrl = wsdlUrl;
	}

	public String getServiceNamespace() {
		return serviceNamespace;
	}

	public void setServiceNamespace(String serviceNamespace) {
		this.serviceNamespace = serviceNamespace;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getServicePort() {
		return servicePort;
	}

	public void setServicePort(String servicePort) {
		this.servicePort = servicePort;
	}
	
}
