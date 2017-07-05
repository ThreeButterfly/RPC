package com.cdrundle.rpc.intf.webservice.model.reigster.in;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.cdrundle.rpc.intf.webservice.model.Identify;

/**
 * Created by tjee on 2017/6/27.
 */
@XmlRootElement(name = "Request")
public class ReigsterRequest {
	private Identify identify;
	private ReigsterInParams params;

	public Identify getIdentify() {
		return identify;
	}

	@XmlElement(name = "Identify")
	public void setIdentify(Identify identify) {
		this.identify = identify;
	}

	/**
	 * @return the params
	 */
	public ReigsterInParams getParams() {
		return params;
	}

	/**
	 * @param params
	 *            the params to set
	 */
	@XmlElement(name = "Params")
	public void setParams(ReigsterInParams params) {
		this.params = params;
	}

}
