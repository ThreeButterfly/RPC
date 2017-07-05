/******************************************************************
 *
 *    Company:   成都市润东实业有限公司 软件开发部
 *
 *    Filename:    a.java
 *
 *    Copyright:   Copyright (c) 2001-2014
 *
 *    @author:     Tjee
 *
 *    @version:    1.0.0
 *
 *
 *    Revision:
 *
 *    2017年6月27日 下午9:12:52
 *        - first revision
 *
 *****************************************************************/
package com.cdrundle.rpc.intf.webservice.model.reigster.out;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * <P>
 * TODO：说明与描述
 * <P>
 * 
 * @author Tjee
 * @CreateDate 2017年6月27日 
 */
@XmlRootElement(name = "row")
public class ReigsterOutParams {

	private String reigsterNo;
	private String	date;
	private String	status;
	private String deptid;
	private String deptname;
	private String doctorid;
	private String doctorName;
	private String patientId;
	private String name;
	private String sex;
	private String age;
	private String birthday;
	private String address;
	private String phone;
	private String idCard;
	private String nation;

	 
	public String getReigsterNo() {
		return reigsterNo;
	}

	@XmlElement(name = "ReigsterNo")
	public void setReigsterNo(String reigsterNo) {
		this.reigsterNo = reigsterNo;
	}

	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	@XmlElement(name = "Date")
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	@XmlElement(name = "Status")
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the deptid
	 */
	public String getDeptid() {
		return deptid;
	}

	@XmlElement(name = "Deptid")
	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}

	/**
	 * @return the deptname
	 */
	public String getDeptname() {
		return deptname;
	}

	@XmlElement(name = "Deptname")
	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}

	/**
	 * @return the doctorid
	 */
	public String getDoctorid() {
		return doctorid;
	}

	@XmlElement(name = "Doctorid")
	public void setDoctorid(String doctorid) {
		this.doctorid = doctorid;
	}

	/**
	 * @return the doctorName
	 */
	public String getDoctorName() {
		return doctorName;
	}

	@XmlElement(name = "DoctorName")
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	/**
	 * @return the patientId
	 */
	public String getPatientId() {
		return patientId;
	}

	@XmlElement(name = "PatientId")
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	@XmlElement(name = "Name")
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the sex
	 */
	public String getSex() {
		return sex;
	}

	@XmlElement(name = "sex")
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * @return the age
	 */
	public String getAge() {
		return age;
	}

	@XmlElement(name = "age")
	public void setAge(String age) {
		this.age = age;
	}

	/**
	 * @return the birthday
	 */
	public String getBirthday() {
		return birthday;
	}

	@XmlElement(name = "Birthday")
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	@XmlElement(name = "Address")
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	@XmlElement(name = "Phone")
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the idCard
	 */
	public String getIdCard() {
		return idCard;
	}

	@XmlElement(name = "IdCard")
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	/**
	 * @return the nation
	 */
	public String getNation() {
		return nation;
	}

	@XmlElement(name = "Nation")
	public void setNation(String nation) {
		this.nation = nation;
	}
	
}
