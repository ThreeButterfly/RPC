package com.cdrundle.rpc.intf.webservice.model.examinereport.out;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by tjee on 2017/6/27.
 */
@XmlRootElement(name = "Response")
public class ExamineReportResponse {
	private String resultCode;
	private String message;
	private Integer rowCount;
	private List<ExamineReportOutParams> rows;

	/**
	 * @return the resultCode
	 */
	public String getResultCode() {
		return resultCode;
	}

	@XmlElement(name = "ResultCode")
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	@XmlElement(name = "Message")
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the rowCount
	 */
	public Integer getRowCount() {
		return rowCount;
	}

	@XmlElement(name = "RowCount")
	public void setRowCount(Integer rowCount) {
		this.rowCount = rowCount;
	}

	/**
	 * @return the rows
	 */
	public List<ExamineReportOutParams> getRows() {
		return rows;
	}

	@XmlElementWrapper(name = "Rows")
	public void setRows(List<ExamineReportOutParams> rows) {
		this.rows = rows;
	}

}
