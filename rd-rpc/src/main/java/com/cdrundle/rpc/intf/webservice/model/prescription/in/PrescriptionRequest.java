package com.cdrundle.rpc.intf.webservice.model.prescription.in;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.cdrundle.rpc.intf.webservice.model.Identify;

/**
 * Created by tjee on 2017/6/27.
 */
@XmlRootElement(name = "Request")
public class PrescriptionRequest {
	private Identify identify;
	private PrescriptionInParams params;

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
	public PrescriptionInParams getParams() {
		return params;
	}

	/**
	 * @param params
	 *            the params to set
	 */
	@XmlElement(name = "Params")
	public void setParams(PrescriptionInParams params) {
		this.params = params;
	}

}
