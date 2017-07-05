/******************************************************************
 *
 *    Company:   成都市润东实业有限公司 软件开发部
 *
 *    Filename:    Row.java
 *
 *    Copyright:   Copyright (c) 2001-2014
 *
 *    @author:     Tjee
 *
 *    @version:    1.0.0
 *
 *    Create at:   2017年6月27日  
 *
 *    Revision:
 *
 *    2017年6月27日 下午9:31:01
 *        - first revision
 *
 *****************************************************************/
package com.cdrundle.rpc.intf.webservice.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *<P>
 *  TODO：说明与描述
 *<P>
 * @author Tjee
 * @CreateDate 2017年6月27日  
 */
@XmlRootElement(name = "Row")
@XmlAccessorType(XmlAccessType.PROPERTY)
public   class Row {
    @XmlElement(nillable = )
    String name;
    String age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
